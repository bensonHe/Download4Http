package org.he.util;
/**
 * @author BenSon He
 * @email qing878@gmail.com ,qq 277803242
 * @since 19/11/2012
 */
public class DLog {
	public static final int INFOR = 0;
	public static final int DEBUG = 1;
	public static final int WARN = 2;
	public static final int ERROR = 3;
	public static final int FITAL = 4;

	public static void log(int level, Exception logInfor) {
		System.out.println(logInfor.getMessage());
		logInfor.printStackTrace();
	}
	public static void log(Class clazz,String logInfor) {
		System.out.println(clazz.getName()+":"+logInfor);
	}
}
