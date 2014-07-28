package com.plaync.nshop.common.util.process.test;

import java.io.OutputStream;

public class PingTest {
	public static void main(String[] args) {

		try {
			Process p = Runtime.getRuntime().exec("ping yahoo.co.kr");
			byte[] msg = new byte[128];
			int len;

			while ((len = p.getInputStream().read(msg)) > 0) {
				System.out.print(new String(msg, 0, len, System.getProperty("sun.jnu.encoding")));
			}

			byte[] rb = new byte[] { (byte) '\n' }; // rs.getBytes();
			OutputStream os = p.getOutputStream();
			os.write(rb);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
