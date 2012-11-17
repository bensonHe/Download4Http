package org.he.listener;

import org.he.dto.DonwloadInfor;
import org.he.dto.DownLoadInforData;

public class DownloadStatus extends Thread {
	private long curSize = 0;
	private long totalSize = 0;
	private boolean isChanged = false;
	private boolean isComplete = false;
	private DownLoadInforData downLoadInforData;
	public DownloadStatus() {
		downLoadInforData = new DownLoadInforData();
	}

	public void addElements(DonwloadInfor donwloadInfor) {
		downLoadInforData.addDwonLoadInforElement(donwloadInfor);
	}

	public void removeCurElement() {
		downLoadInforData.removeCurDwonLoadInforElement();
	}

	public boolean isChanged() {
		return isChanged;
	}

	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}

	public long getCurSize() {
		return curSize;
	}

	public void setCurSize(long curSize) {
		this.curSize = curSize;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public synchronized void notifyDownloadStatus(DonwloadInfor donwloadInfor) {
		curSize += donwloadInfor.getBufferSize();
		System.out.println("now have download " + (curSize * 100) / totalSize + "%");
		if (curSize == totalSize)
			this.isComplete = true;
	}

	@Override
	public void run() {
		while (this.isComplete) {
			if (this.isChanged) {

			}
		}
	}
}
