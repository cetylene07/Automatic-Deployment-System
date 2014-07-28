package com.plaync.nshop.common.util.process;

import lombok.Data;

/**
 * @FileName : ProcessVO.java
 * @Project : Deployment-Server
 * @Date : 2014. 6. 27.
 * @작성자 : nklee
 * @프로그램설명 :
 */
@Data
public class ProcessVO {

	/** 파라미터 */
	private String param;
	/** 실행 프로그램 */
	private String execProgram;
	/** 결과 메세지 */
	private String resultMsg;
	/** 실행 결과 */
	private boolean isSuccess;
}
