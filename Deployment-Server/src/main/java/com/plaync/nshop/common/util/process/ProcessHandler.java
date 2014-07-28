package com.plaync.nshop.common.util.process;

/**
 * @FileName : ProcessHandler.java
 * @Project : Deployment-Server
 * @Date : 2014. 6. 27.
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class ProcessHandler {

	/** 외부 프로그램 path와 파라미터를 구분하는 value */
	private final String RULE_SPACE = " ";

	/**
	 * <pre>
	 * job
	 *
	 * <pre>
	 * @param processVO
	 * @return
	 */
	public ProcessVO job(ProcessVO processVO) {
		String resultMsg = null;
		try {
			ProcessExecutor executor = new ProcessExecutor();
			String command = makeCommand(processVO);
			resultMsg = executor.exec(command);

			if (resultMsg != null)
				processVO.setSuccess(true); // 외부 프로그램에서 정상적으로 데이터를 받았으면 true

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

		return processVO;
	}

	/**
	 * <pre>
	 * makeCommand
	 * command 생성
	 * <pre>
	 * @param processVO
	 * @return
	 */
	private String makeCommand(ProcessVO processVO) {
		StringBuilder command = new StringBuilder();
		command.append(processVO.getExecProgram());
		command.append(RULE_SPACE);
		command.append(processVO.getParam());
		return command.toString();
	}

}
