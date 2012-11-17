package org.he.dto;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

public class DownLoadInforData implements Serializable {
	private Map<String, DonwloadInfor> donwloadInforMap = new Hashtable<String, DonwloadInfor>();

	public void addDwonLoadInforElement(DonwloadInfor donwloadInfor) {
		String key = Thread.currentThread().getName();
		if (donwloadInforMap.containsKey(key))
			donwloadInforMap.put(key, donwloadInfor);
	}

	public void removeCurDwonLoadInforElement() {
		donwloadInforMap.remove(Thread.currentThread().getName());
	}
}
