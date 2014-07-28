package com.plaync.nshop.common.util.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @FileName : ProcessStreamThread.java
 * @Project : Deployment-Server
 * @Date : 2014. 6. 27.
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class ProcessStreamThread extends Thread {

	/** stream */
	private final InputStream inputStream;

	/** 결과 message */
	private final StringBuilder msg;

	/**
	 * @param inputStream
	 * @param msg
	 */
	public ProcessStreamThread(InputStream inputStream, StringBuilder msg) {
		this.inputStream = inputStream;
		this.msg = msg;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			msg.append(getStreamString(inputStream));

		} catch (Exception ex) {
			System.out.println("##run failed## msg=" + msg);
			ex.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {}
			}
		}
	}

	/**
	 * <pre>
	 * getStreamString
	 * 스트림에 있는 메세지 읽기
	 * <pre>
	 * @param inputStream
	 * @return
	 */
	private String getStreamString(InputStream inputStream) throws Exception {
		BufferedReader reader = null;
		StringBuilder msg = null;
		try {
			reader = new BufferedReader(new InputStreamReader(inputStream));
			msg = new StringBuilder();
			String msgLine = null;

			while ((msgLine = reader.readLine()) != null) {
				msg.append(msgLine);
			}

			return msg.toString();

		} catch (Exception ex) {
			System.out.println("##getStreamString failed## msg=" + msg);
			ex.printStackTrace();
			throw ex;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ex) {}
			}
		}
	}
}
