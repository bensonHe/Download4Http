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

	public void addDwonLoadInforElement(DonwloadInfor donwloadInfor) {
		String key = Thread.currentThread().getName();
		if (!donwloadInforMap.containsKey(key))
			donwloadInforMap.put(key, donwloadInfor);
	}

	public void removeCurDwonLoadInforElement() {
		donwloadInforMap.remove(Thread.currentThread().getName());
	}

	public int getSize() {
		return donwloadInforMap.entrySet().size();
	}
}
