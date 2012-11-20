package org.he.bin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.he.dto.DownLoadInforData;

public class StartConsole {
	public static void main(String[] args) throws Exception {
		StartConsole console=new StartConsole();
		System.out.println("1 . download new task");
		System.out.println("2 . continue unfinished task");
		System.out.println("3 . show task list");
		System.out.println("4 . quit");
		System.out.print("pleas select operating :");
		BufferedReader rd=new BufferedReader(new InputStreamReader(System.in));
		String redline=rd.readLine();
		int command=Integer.parseInt(redline);
		switch (command) {
		case 1:
			console.DownloadNewTask();
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			System.exit(0);
			break;
		default:
			break;
		}
	}
	private NewDownload download;
	private BufferedReader rd;
	public StartConsole(){
		rd=new BufferedReader(new InputStreamReader(System.in));
	}
	private void DownloadNewTask() throws IOException{
		System.out.print("please input your download URL :");
		String redline=rd.readLine();
		//Verification URL adress.
		if("".equals(redline))
			DownloadNewTask();
		DownLoadInforData downLoadInforData=new DownLoadInforData();
		downLoadInforData.setDownlowdURLAdr(redline);
		System.out.println("please input your save path (like as 'D:/Download/') :");
		redline=rd.readLine();
		downLoadInforData.setDownLoadFilePath(redline);
		new NewDownload(downLoadInforData,5).startDownLoad();
	}
	private void DownloadContinueTask(){
		
	}
}
