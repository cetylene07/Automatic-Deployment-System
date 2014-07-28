



package com.plaync.nshop.common.aop;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @FileName : LoggingAOP.java
 * @Project : Deployment-Server
 * @Date : 2014. 6. 27.
 * @작성자 : nklee
 * @프로그램설명 :
 */
@Component
@Slf4j
public class LoggingAOP {

	/**
	 * Around advice.
	 *
	 * @param pjp the pjp
	 * @return the object
	 * @throws Throwable the throwable
	 */
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		String className = pjp.getTarget().getClass().getSimpleName();
		String methodName = pjp.getSignature().getName();
		Object[] params = pjp.getArgs();
		String values = StringUtils.EMPTY;

		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				values += params[i] + ",";
			}
		}

		if (values != null && values.length() > 0) {
			values = values.substring(0, values.length() - 1);
		}

		StopWatch watch = new StopWatch();
		log.info("#IN#[" + className + "." + methodName + "] [" + values + "]");

		Object obj = null;
		try {
			watch.start();
			obj = pjp.proceed();
		} finally {
			watch.stop();
			log.info("#OUT#[" + className + "." + methodName + "] " + "(" + watch.getTotalTimeMillis() + "ms)");
		}

		return obj;
	}
}
