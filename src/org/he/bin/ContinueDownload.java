package org.he.bin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.he.dto.DonwloadInfor;
import org.he.dto.DownLoadInforData;

public class ContinueDownload {
	private DownLoadInforData downLoadInforData;
	private DownLoadProcess downLoadProcess;

	public ContinueDownload(DownLoadInforData downLoadInforData) {
		this.downLoadInforData = downLoadInforData;
	}

	public void downLoadUnfinishTask() {
		Map dwonLoadInforsMap = downLoadInforData.getAllDwonLoadInforElements();
		String threadName = null;
		DonwloadInfor donwloadInfor = null;
		Iterator<String> inforIterator=dwonLoadInforsMap.keySet().iterator();
		while(inforIterator.hasNext()){
			threadName=inforIterator.next();
			donwloadInfor=(DonwloadInfor) dwonLoadInforsMap.get(threadName);
			
		}
	}

	public static void main(String[] args) {
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("1", "1value");
		hashMap.put("2", "2value");
		Set<String> set = hashMap.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
