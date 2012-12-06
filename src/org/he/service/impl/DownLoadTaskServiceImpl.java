package org.he.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.he.dto.DonwloadInfor;
import org.he.dto.DownLoadInforData;
import org.he.service.DownLoadTaskService;
import org.he.util.ObjectFileDataUtil;

/**
 * @author BenSon He
 * @email qing878@gmail.com ,qq 277803242
 * @since 19/11/2012
 */
public class DownLoadTaskServiceImpl implements DownLoadTaskService {
	private DownLoadActionService downLoadActionService;

	@Override
	public void continueExistTask(List<String> taskList) throws FileNotFoundException {
		DownLoadInforData downLoadInforData = null;
		for (String fileName : taskList) {
			downLoadInforData = (DownLoadInforData) new ObjectFileDataUtil(fileName).readObjectFromFile();
			 downLoadActionService = new DownLoadActionService(downLoadInforData);
			 downLoadActionService.startDownLoad();
//			System.out.println(downLoadInforData);
		}
	}

	@Override
	public void continueTask(DownLoadInforData downLoadInforData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pauseTask(DownLoadInforData downLoadInforData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startNewTask(DownLoadInforData downLoadInforData) {
		// a new task start
		downLoadActionService = new DownLoadActionService(downLoadInforData);
		downLoadActionService.startDownLoad();
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		 DownLoadInforData downLoadInforData=new DownLoadInforData();
//		 downLoadInforData.setDownlowdURLAdr("http://localhost:8080/Test4Download/DownloadSource/hbwyj3D.mkv");
//		 new DownLoadTaskServiceImpl().startNewTask(downLoadInforData);
		 new DownLoadTaskServiceImpl().continueExistTask(Arrays.asList("C:\\Download4Http\\hbwyj3D.mkv.temp"));
	}
}
