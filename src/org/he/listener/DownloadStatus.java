package org.he.listener;

import java.io.FileNotFoundException;

import org.he.dto.DonwloadInfor;
import org.he.dto.DownLoadInforData;
import org.he.util.ConsoleInforUtil;
import org.he.util.DLog;
import org.he.util.ObjectFileDataUtil;
/**
 * @author BenSon He
 * @email qing878@gmail.com ,qq 277803242
 * @since 19/11/2012
 */
public class DownloadStatus extends Thread {
	private long curSize = 0;
	private long totalSize = 0;
	private boolean isChanged = false;
	private boolean isComplete = false;
	private DownLoadInforData downLoadInforData;

	public DownloadStatus(DownLoadInforData downLoadInforData) {
		this.downLoadInforData = downLoadInforData;
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

	public String getTempFileName() {
		StringBuffer tempFile = new StringBuffer(downLoadInforData.getDownLoadFilePath());
		tempFile.append(downLoadInforData.getDownLoadFileName());
		tempFile.append(".temp");// temp file name type
		return tempFile.toString();
	}

	public synchronized void notifyDownloadStatus(DonwloadInfor donwloadInfor) {
		curSize += donwloadInfor.getBufferSize();
		ConsoleInforUtil.disPlayDownLoadStatus(curSize, totalSize);
		if (curSize == totalSize)
			this.isComplete = true;
		this.isChanged = true;
	}

	@Override
	public void run() {
		ObjectFileDataUtil objectFileDataUtil = null;
		try {
			objectFileDataUtil = new ObjectFileDataUtil(this.getTempFileName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			DLog.log(DLog.ERROR, e);
		}
		while (!this.isComplete) {
			if (this.isChanged) {
				objectFileDataUtil.writeObjectToFile(downLoadInforData);
				this.isChanged=false;
			}
		}
		objectFileDataUtil.removeDataFile();

	}
}
