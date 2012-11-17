package org.he.util;

public class DLog {
	public static final int INFOR = 0;
	public static final int DEBUG = 1;
	public static final int WARN = 2;
	public static final int ERROR = 3;
	public static final int FITAL = 4;

	public static void log(int level, Exception logInfor) {
		logInfor.printStackTrace();
	}
}
