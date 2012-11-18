package org.he.bin;

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

import org.he.util.DLog;

/**
 * @author BenSon He
 * @email qing878@gmail.com ,qq 277803242
 * @since 19/11/2012
 */
public class Resource {
	private String urlAdress = null;

	public Resource(String urlAdress) {
		// TODO Auto-generated constructor stub
		this.urlAdress = urlAdress;
	}

	public URLConnection getNewURLConnect() {
		URL url;
		try {
			url = new URL(urlAdress);
			/**
			 * if you company have to use poxy to connect Internet. add below code
//			SocketAddress sa = new Socket("10.224.10.252", 808)
//					.getRemoteSocketAddress();
//			Proxy poxy = new Proxy(Proxy.Type.HTTP, sa);
 *  **/
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

//	public static void main(String[] args) throws Exception {
//		URLConnection con = new Resource().getURLConnect();
//		int buferSize = 1024 * 1024 * 50;
//		FileOutputStream out = new FileOutputStream(new File("D://test.mkv"),
//				true);
//		BufferedInputStream input = new BufferedInputStream(con
//				.getInputStream());
//		int nTotalLen = con.getContentLength();
//		int total = 0;
//		byte[] bufferBt = null;
//		int parts = nTotalLen / buferSize + 1;
//		int lastPart = nTotalLen % buferSize;
//		System.out.println("total have " + parts + " parts");
//		for (int i = 1; i <= parts; i++) {
//			int nIdx = 0;
//			int nReadLen = 0;
//			if (parts == i)
//				buferSize = lastPart;
//			bufferBt = new byte[buferSize];
//			while (nIdx < buferSize) {
//				nReadLen = input.read(bufferBt, nIdx, buferSize - nIdx);
//				if (nReadLen > 0) {
//					nIdx = nIdx + nReadLen;
//				} else {
//					continue;
//				}
//				total += nReadLen;
//				System.out.println("have finish " + ((long) total * 100)
//						/ nTotalLen + "%"); // load status
//			}
//			out.write(bufferBt);
//			bufferBt = null; // if not release memory. will out
//		}
//		out.flush();
//		out.close();
//	}
}
