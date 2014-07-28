package com.plaync.nshop.common.util.process;

/**
 * @FileName : ProcessExecutor.java
 * @Project : Deployment-Server
 * @Date : 2014. 6. 27.
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class ProcessExecutor {

	/**
	 * <pre>
	 * exec
	 * 외부 프로그램 실행
	 * <pre>
	 * @param command
	 * @return
	 * @throws Exception
	 */
	public String exec(String command) throws Exception {
		Process process = null;
		StringBuilder resultMsg = null;

		try {
			process = Runtime.getRuntime().exec(command);

			resultMsg = new StringBuilder();
			ProcessStreamThread processStreamThread = new ProcessStreamThread(process.getInputStream(), resultMsg);
			processStreamThread.start();

			processStreamThread = new ProcessStreamThread(process.getErrorStream(), resultMsg);
			processStreamThread.start();

			// child process 종료 시까지 대기
			int resultCode = process.waitFor();
			System.out.println("##exec success## command=" + command + ", resultMsg=" + resultMsg + ", resultCode=" + resultCode);

		} catch (Exception ex) {
			System.out.println("##exec exception## command=" + command + ", resultMsg=" + resultMsg);
			ex.printStackTrace();
			throw ex;
		} finally {
			if (process != null) {
				try {
					process.destroy();
				} catch (Exception ex) {}
			}
		}

		return resultMsg.toString();
	}

}
