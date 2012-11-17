package org.he.bin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.net.URLConnection;
import java.util.Date;

import org.he.dto.DonwloadInfor;
import org.he.listener.DownloadStatus;
import org.he.util.DLog;

//use Serializable for save object in the file
public class DownLoadProcess extends Thread {
	private DonwloadInfor donwloadInfor = null;
	private URLConnection connection = null;
	private DownloadStatus downloadSt;
	private String threadName = null;

	public DownLoadProcess(DonwloadInfor donwloadInfor, URLConnection connection, DownloadStatus downloadSt) {
		this.donwloadInfor = donwloadInfor;
		this.connection = connection;
		this.downloadSt = downloadSt;
		if (threadName == null)
			this.setThreadName(String.valueOf(new Date().getTime()));
	}
	@Override
	public void run() {
		try {
			Thread.currentThread().setName(this.getThreadName()); // define a thread name
			RandomAccessFile donwFile = new RandomAccessFile(new File(donwloadInfor.getDownloadFilePath()
					+ donwloadInfor.getDownloadFileName()), "rw");
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
				downloadSt.notifyDownloadStatus(donwloadInfor);
				donwFile.write(buffer);
			}
			System.out.println("Thread " + this.getName() + " complete");
			donwFile.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			DLog.log(DLog.ERROR, e);
		}
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
}
