package org.he.bin;
import java.net.URLConnection;
import org.he.dto.DonwloadInfor;
import org.he.listener.DownloadStatus;

public class Download {
	private String downloadPath = null;
	private String downlowdURLAdr = null;
	private Resource resource = null;
	private URLConnection connect = null;
	private String downLoadFileName = null;
	private int threadCount = 5;
	private String tempName = "temp_%s.task";
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
			downlowdURLAdr = "http://localhost:8080/Test4Download/DownloadSource/hbwyj3D.mkv";// test download adress
		return downlowdURLAdr;
	}

	public String getTempName() {
		if(tempName==null)
			tempName=String.format(tempName, getDownLoadFileName());
		return tempName;
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
		downloadSt = new DownloadStatus();
	}

	public String getDownLoadFileName() {
		if (downLoadFileName == null) {
			downLoadFileName = getDownlowdURLAdr().substring(getDownlowdURLAdr().lastIndexOf("/") + 1);
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
	}

	public static void main(String[] args) {
		new Download().startDownLoad();
	}
}
