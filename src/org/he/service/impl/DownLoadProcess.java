package org.he.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.net.URLConnection;
import java.util.Date;

import org.he.dao.NetConnection;
import org.he.dto.DonwloadInfor;
import org.he.dto.DownLoadInforData;
import org.he.listener.DownloadStatus;
import org.he.util.DLog;

/**
 * @author BenSon He
 * @email qing878@gmail.com ,qq 277803242
 * @since 19/11/2012
 */
public class DownLoadProcess extends Thread {
	private DonwloadInfor donwloadInfor = null;
	private URLConnection connection = null;
	private String downLoadFilePath;
	private String downLoadFileName;
	private DownloadStatus downloadSt;
	private NetConnection netConnection;

	public DownLoadProcess(DonwloadInfor donwloadInfor, DownloadStatus downloadSt, String downLoadFilePath,
			String downLoadFileName, String urlAdress) {
		this.donwloadInfor = donwloadInfor;
		this.downLoadFilePath = downLoadFilePath;
		this.downLoadFileName = downLoadFileName;
		this.downloadSt = downloadSt;
		this.netConnection = new NetConnection(urlAdress);
		this.connection = this.netConnection.getNewURLConnect();
	}

	@Override
	public void run() {
		try {
			Thread.currentThread().setName(String.valueOf(donwloadInfor.getId())); // define a thread name
			RandomAccessFile donwFile = new RandomAccessFile(new File(this.downLoadFilePath + this.downLoadFileName),
					"rw");
			System.out.println(Thread.currentThread().getName()+",donwloadInfor.getStartIndex() = "+donwloadInfor.getStartIndex());
			donwFile.seek(donwloadInfor.getStartIndex());
			connection.setRequestProperty("Range", "bytes=" + donwloadInfor.getStartIndex() + "-"
					+ donwloadInfor.getEndIndex());
			BufferedInputStream urlInputStream = new BufferedInputStream(connection.getInputStream());
			long totalDownload = donwloadInfor.getEndIndex() - donwloadInfor.getStartIndex();
			int parts = (int) (totalDownload / donwloadInfor.getBufferSize() + 1);
			long lastPart = totalDownload % donwloadInfor.getBufferSize();
			for (int i = 1; i <= parts; i++) {
				if (i == parts)
					donwloadInfor.setBufferSize((int) lastPart);
				int nIndex = 0;
				int endIndex = donwloadInfor.getBufferSize();
				int reConect = 5;
				int nReadn = 0;
				byte buffer[] = new byte[donwloadInfor.getBufferSize()];
				while (endIndex > nIndex) {
					nReadn = urlInputStream.read(buffer, nIndex, endIndex - nIndex);
					if (nReadn > 0) {
						nIndex += nReadn;
					} else {
						// if nReadn is 0.maybe is lost network.
						// reconnect reConect times
						if (reConect == 0)
							break;
						reConect--;
					}
				}
				donwloadInfor.setStartIndex(donwloadInfor.getStartIndex() + donwloadInfor.getBufferSize());
				donwFile.write(buffer);
				//to record have writer buffer
				downloadSt.notifyDownloadStatus(donwloadInfor);
			}
			DLog.log(this.getClass(), "Thread " + this.getName() + " complete");
			donwFile.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			DLog.log(DLog.ERROR, e);
		}
	}
}
