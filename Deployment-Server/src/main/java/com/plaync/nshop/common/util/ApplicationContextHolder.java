



package com.plaync.nshop.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @FileName : ApplicationContextHolder.java
 * @Project : Deployment-Server
 * @Date : 2014. 6. 27.
 * @작성자 : nklee
 * @프로그램설명 :
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

	/** The app ctx. */
	private static ApplicationContext appCtx;

	/**
	 * Gets the app ctx.
	 *
	 * @return the app ctx
	 */
	public static ApplicationContext getAppCtx() {
		return appCtx;
	}

	/**
	 * <pre>
	 * setApplicationContext
	 *
	 * <pre>.
	 *
	 * @param applicationContext the new application context
	 * @throws BeansException the beans exception
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		appCtx = applicationContext;
	}
}
