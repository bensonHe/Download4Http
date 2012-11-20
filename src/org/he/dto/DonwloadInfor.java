package org.he.dto;

import java.io.Serializable;
import java.net.URLConnection;
/**
 * @author BenSon He
 * @email qing878@gmail.com ,qq 277803242
 * @since 19/11/2012
 */
public class DonwloadInfor implements Serializable {
//	private int bufferSize = 1024 * 1024 * 1; // default value 1M
	private int bufferSize = 1024 * 50; // default value 20KB
	private long totalSize = 0;
	private long startIndex = 0;
	private long endIndex = 0;
	public long getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(long endIndex) {
		this.endIndex = endIndex;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public long getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}

}
