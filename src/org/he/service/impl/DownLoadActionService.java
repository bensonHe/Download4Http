package org.he.service.impl;

import java.io.File;
import java.net.URLConnection;

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
public class DownLoadActionService {
	private URLConnection connect = null;
	private DownLoadInforData downLoadInforData;
	private int threadCount; // together to work thread count
	private DownloadStatus downloadSt;

	public DownLoadActionService(DownLoadInforData downLoadInforData) {
		this.threadCount = downLoadInforData.getThreadCount();
		this.downLoadInforData = downLoadInforData;
		connect = new NetConnection(downLoadInforData.getDownlowdURLAdr()).getNewURLConnect();
		downloadSt = new DownloadStatus(downLoadInforData);
	}

	public String getDownLoadFileName() {
		String downLoadFileName = downLoadInforData.getDownLoadFileName();
		String downlowdURLAdr = downLoadInforData.getDownlowdURLAdr();
		if (downLoadFileName == null) {
			if (downlowdURLAdr.lastIndexOf("?") == -1)
				downLoadFileName = downlowdURLAdr.substring(downlowdURLAdr.lastIndexOf("/") + 1);// to get down file name.
			else
				downLoadFileName = downlowdURLAdr.substring(downlowdURLAdr.lastIndexOf("/") + 1, downlowdURLAdr
						.lastIndexOf("?"));// to get down file name.the name is equas string that start with '/' end with '?'
		}
		return downLoadFileName;
	}

	public String getDownLoadFilePath() {
		String downLoadFilePath = downLoadInforData.getDownLoadFilePath();
		if (downLoadFilePath == null) {
			downLoadFilePath = "C:\\Download4Http\\"; //set default path
			new File(downLoadFilePath).mkdir(); //create default path
			downLoadInforData.setDownLoadFilePath(downLoadFilePath); //save data file path
		}
		return downLoadFilePath;
	}

	public String getDownlowdURLAdr() {
		return downLoadInforData.getDownlowdURLAdr();
	}

	public void startDownLoad() {
		long totalContentLength = connect.getContentLength();
		long tempSize = totalContentLength / threadCount; // according thread count to divide file's parts
		long lastParts = totalContentLength % threadCount; // the last parts should be add to last thread
		DownLoadProcess downLoadProcess = null;
		downloadSt.setTotalSize(totalContentLength);
		downLoadInforData.setDownLoadFileName(getDownLoadFileName());
		DLog.log(this.getClass(), "try to download " + downLoadInforData.getDownLoadFileName() + " the file size is "
				+ totalContentLength / 1024.00 / 1024.00 + " MB");
		for (int i = 1; i <= threadCount; i++) {
			DonwloadInfor donwloadInfor = new DonwloadInfor();
			donwloadInfor.setStartIndex(tempSize * (i - 1));
			if (i == threadCount)
				donwloadInfor.setEndIndex(tempSize * i + lastParts);
			else
				donwloadInfor.setEndIndex(tempSize * i);
			downLoadInforData.addDwonLoadInforElement(donwloadInfor);
			downLoadProcess = new DownLoadProcess(donwloadInfor, downloadSt, this.getDownLoadFilePath(), this
					.getDownLoadFileName(), this.getDownlowdURLAdr());
			downLoadProcess.start();
		}
		downloadSt.start();// Record Thread start
	}

	public static void main(String[] args) {
		DownLoadInforData downLoadInforData = new DownLoadInforData();
		downLoadInforData.setDownlowdURLAdr("http://localhost:8080/Test4Download/DownloadSource/hbwyj3D.mkv");
		new DownLoadActionService(downLoadInforData).startDownLoad();
	}
}
