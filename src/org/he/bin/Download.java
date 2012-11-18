package org.he.bin;

import java.net.URLConnection;
import org.he.dto.DonwloadInfor;
import org.he.listener.DownloadStatus;

/**
 * @author BenSon He
 * @email qing878@gmail.com ,qq 277803242
 * @since 19/11/2012
 */
public class Download {
	private String downloadPath = null;
	private String downlowdURLAdr = null;
	private Resource resource = null;
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
			downlowdURLAdr = "http://zhangmenshiting.baidu.com/data2/music/10554228/10554229133200.mp3?xcode=3fd6a2706cba33a772455c475bad385f";// test download adress
		return downlowdURLAdr;
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public void setDownlowdURLAdr(String downlowdURLAdr) {
		this.downlowdURLAdr = downlowdURLAdr;
	}

	public Download() {
		resource = new Resource(getDownlowdURLAdr());
		connect = resource.getNewURLConnect();
		downloadSt = new DownloadStatus(getDownloadLocalPath(), getDownLoadFileName());
	}

	public String getDownLoadFileName() {
		if (downLoadFileName == null) {
			downLoadFileName = getDownlowdURLAdr().substring(getDownlowdURLAdr().lastIndexOf("/") + 1,
					getDownlowdURLAdr().lastIndexOf("?"));// to get down file name.
		}
		System.out.println(downLoadFileName);
		return downLoadFileName;
	}

	public void startDownLoad() {
		long totalContentLength = connect.getContentLength();
		long tempSize = totalContentLength / threadCount; // according thread count to divide file's parts
		long lastParts = totalContentLength % threadCount; // the last parts should be add to last thread
		downloadSt.setTotalSize(totalContentLength);
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
		new Download().startDownLoad();
	}
}
