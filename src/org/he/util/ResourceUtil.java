package org.he.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;


/**
 * @author BenSon He
 * @email qing878@gmail.com ,qq 277803242
 * @since 19/11/2012
 */
public class ResourceUtil {
	private String urlAdress = null;

	public ResourceUtil(String urlAdress) {
		// TODO Auto-generated constructor stub
		this.urlAdress = urlAdress;
	}

	public URLConnection getNewURLConnect() {
		URL url;
		try {
			url = new URL(urlAdress);
			/**
			 * if you  have to use poxy to connect Internet. add below code
			 *  *  **/
//			SocketAddress sa = new Socket("10.224.10.252", 808)
//					.getRemoteSocketAddress();
//			Proxy poxy = new Proxy(Proxy.Type.HTTP, sa);
//			URLConnection con = url.openConnection();
			URLConnection con = url.openConnection();
			con.setConnectTimeout(60*1000);
			con.setReadTimeout(60*1000);
			return con;
		} catch (Exception e) {
			DLog.log(DLog.ERROR, e);
		}
		return null;
	}
}
