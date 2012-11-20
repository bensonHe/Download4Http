package org.he.bin;

import java.net.URLConnection;
import org.he.dto.DonwloadInfor;
import org.he.dto.DownLoadInforData;
import org.he.listener.DownloadStatus;
import org.he.util.DLog;
import org.he.util.ResourceUtil;

/**
 * @author BenSon He
 * @email qing878@gmail.com ,qq 277803242
 * @since 19/11/2012
 */
public class NewDownload {
	private ResourceUtil resource = null;
	private URLConnection connect = null;
	private DownLoadInforData downLoadInforData;
	private int threadCount = 5;
	private DownloadStatus downloadSt;

	public NewDownload(DownLoadInforData downLoadInforData,int threadCount) {
		this.threadCount=threadCount;
		this.downLoadInforData = downLoadInforData;
		resource = new ResourceUtil(downLoadInforData.getDownlowdURLAdr());
		connect = resource.getNewURLConnect();
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
						.lastIndexOf("?"));// to get down file name.
		}
		return downLoadFileName;
	}

	public void startDownLoad() {
		long totalContentLength = connect.getContentLength();
		long tempSize = totalContentLength / threadCount; // according thread count to divide file's parts
		long lastParts = totalContentLength % threadCount; // the last parts should be add to last thread
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
			new DownLoadProcess(downLoadInforData, donwloadInfor, resource.getNewURLConnect(), downloadSt).start();
		}
		downloadSt.start();// Record Thread start
	}

	public static void main(String[] args) {
		DownLoadInforData downLoadInforData=new DownLoadInforData();
		downLoadInforData.setDownlowdURLAdr("http://localhost:8080/Test4Download/DownloadSource/testForDownload.rar");
		downLoadInforData.setDownLoadFilePath("D:/Download/");
		new NewDownload(downLoadInforData,1).startDownLoad();
	}
}
