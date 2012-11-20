package org.he.bin;

import java.net.URLConnection;
import org.he.dto.DonwloadInfor;
import org.he.listener.DownloadStatus;
import org.he.util.DLog;
import org.he.util.ResourceUtil;

/**
 * @author BenSon He
 * @email qing878@gmail.com ,qq 277803242
 * @since 19/11/2012
 */
public class Download {
	private String downloadPath = null;
	private String downlowdURLAdr = null;
	private ResourceUtil resource = null;
	private URLConnection connect = null;
	private String downLoadFileName = null;
	private int threadCount = 5;
	private DownloadStatus downloadSt;

	public String getDownloadLocalPath() {
		if (downloadPath == null)
			downloadPath = new String("D://Download//"); // default value
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public String getDownlowdURLAdr() {
		if (downlowdURLAdr == null)
			downlowdURLAdr = "http://localhost:8080/Test4Download/DownloadSource/testForDownload.rar";// test download adress
		return downlowdURLAdr;
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public Download(String downlowdURLAdr) {
		this.downlowdURLAdr = downlowdURLAdr;
		resource = new ResourceUtil(getDownlowdURLAdr());
		connect = resource.getNewURLConnect();
		downloadSt = new DownloadStatus(getDownloadLocalPath(), getDownLoadFileName());
	}

	public String getDownLoadFileName() {
		if (downLoadFileName == null) {
			if (getDownlowdURLAdr().lastIndexOf("?") == -1)
				downLoadFileName = getDownlowdURLAdr().substring(getDownlowdURLAdr().lastIndexOf("/") + 1);// to get down file name.
			else
				downLoadFileName = getDownlowdURLAdr().substring(getDownlowdURLAdr().lastIndexOf("/") + 1,
						getDownlowdURLAdr().lastIndexOf("?"));// to get down file name.
		}
		return downLoadFileName;
	}

	public void startDownLoad() {
		long totalContentLength = connect.getContentLength();
		long tempSize = totalContentLength / threadCount; // according thread count to divide file's parts
		long lastParts = totalContentLength % threadCount; // the last parts should be add to last thread
		downloadSt.setTotalSize(totalContentLength);
		DLog.log(this.getClass(), "try to download " + getDownLoadFileName() + " the file size is "
				+ totalContentLength / 1024.00 / 1024.00 + " MB");
		for (int i = 1; i <= threadCount; i++) {
			DonwloadInfor infor = new DonwloadInfor();
			infor.setTotalSize(totalContentLength); // in order to save status
			infor.setDownloadFilePath(getDownloadLocalPath());
			infor.setDownloadFileName(getDownLoadFileName());
			infor.setStartIndex(tempSize * (i - 1));
			if (i == threadCount)
				infor.setEndIndex(tempSize * i + lastParts);
			else
				infor.setEndIndex(tempSize * i);
			new DownLoadProcess(infor, resource.getNewURLConnect(), downloadSt).start();
		}
		downloadSt.start();// Record Thread start
	}

	public static void main(String[] args) {
		new Download(null).startDownLoad();
	}
}
