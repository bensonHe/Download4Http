package org.he.dto;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author BenSon He
 * @email qing878@gmail.com ,qq 277803242
 * @since 19/11/2012
 */
public class DownLoadInforData implements Serializable {
	private String downLoadFilePath;
	private String downLoadFileName;
	private String downlowdURLAdr;
	private int threadCount = Runtime.getRuntime().availableProcessors(); // the default thread count value. depend on your CPU 
	private Map<String, DonwloadInfor> donwloadInforMap = new Hashtable<String, DonwloadInfor>();

	/**
	 * @param downLoadFileName
	 *            the file save path that on disk ,like 'D:\Download'
	 * @param downlowdURLAdr
	 *            the down url adress , like 'http://xx/xx.mp3'
	 */
	public DownLoadInforData(String downLoadFileName, String downlowdURLAdr) {
		this.downLoadFileName = downLoadFileName;
		this.downlowdURLAdr = downlowdURLAdr;
	}

	/**
	 * the information about down load
	 */
	public DownLoadInforData() {
	}

	/**
	 * @return the count that together work theard
	 */
	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public String getDownLoadFilePath() {
		return downLoadFilePath;
	}

	public void setDownLoadFilePath(String downLoadFilePath) {
		this.downLoadFilePath = downLoadFilePath;
	}

	public String getDownLoadFileName() {
		return downLoadFileName;
	}

	public void setDownLoadFileName(String downLoadFileName) {
		this.downLoadFileName = downLoadFileName;
	}

	public String getDownlowdURLAdr() {
		return downlowdURLAdr;
	}

	public void setDownlowdURLAdr(String downlowdURLAdr) {
		this.downlowdURLAdr = downlowdURLAdr;
	}

	public void addDwonLoadInforElement(DonwloadInfor donwloadInfor) {
		String key = Thread.currentThread().getName();
		if (!donwloadInforMap.containsKey(key))
			donwloadInforMap.put(key, donwloadInfor);
	}

	public Map getAllDwonLoadInforElements() {
		return donwloadInforMap;
	}

	public void removeCurDwonLoadInforElement() {
		donwloadInforMap.remove(Thread.currentThread().getName());
	}

	public int getSize() {
		return donwloadInforMap.entrySet().size();
	}
}
