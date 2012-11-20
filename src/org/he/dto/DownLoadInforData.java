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
	private Map<String, DonwloadInfor> donwloadInforMap = new Hashtable<String, DonwloadInfor>();
	private String downLoadFilePath;
	private String downLoadFileName;
	private String downlowdURLAdr;

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
