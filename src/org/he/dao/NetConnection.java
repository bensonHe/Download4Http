package org.he.dao;

import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;

import org.he.util.DLog;

/**
 * @author BenSon He
 * @email qing878@gmail.com ,qq 277803242
 * @since 19/11/2012
 */
public class NetConnection {
	private String urlAdress = null;
	private long timeOut = 1000l;
	private boolean isProxy = false;

	public NetConnection(String urlAdress) {
		// TODO Auto-generated constructor stub
		this.urlAdress = urlAdress;
	}

	public URLConnection getNewURLConnect() {
		URL url;
		try {
			url = new URL(urlAdress);
			/**
			 * SocketAddress sa = new Socket("10.224.10.252", 808).getRemoteSocketAddress(); 
			 * Proxy poxy = new Proxy(Proxy.Type.HTTP, sa);
			 * URLConnection con = url.openConnection(poxy);
			 **/
			URLConnection con = url.openConnection();
			con.setConnectTimeout(60 * 1000);
			con.setReadTimeout(60 * 1000);
			return con;
		} catch (Exception e) {
			DLog.log(DLog.ERROR, e);
		}
		return null;
	}
}
