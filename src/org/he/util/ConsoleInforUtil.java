package org.he.util;
/**
 * @author BenSon He
 * @email qing878@gmail.com ,qq 277803242
 * @since 19/11/2012
 */
public class ConsoleInforUtil {
	public static void disPlayDownLoadStatus(long curSize,long totalSize){
		int percent=(int) ((curSize * 100) / totalSize);
		StringBuffer inforDisplay=new StringBuffer();
		inforDisplay.append("now have finish : ");
		inforDisplay.append(percent);
		inforDisplay.append("%");
		StringBuffer backspace=new StringBuffer();
		for(int i=0;i<inforDisplay.length();i++){
			backspace.append("\b");
		}
//		System.out.print(backspace.toString()+inforDisplay.toString());
	}
}
