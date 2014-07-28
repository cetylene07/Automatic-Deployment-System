



package com.plaync.nshop.common.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import lombok.extern.slf4j.Slf4j;

/**
 * @FileName : InitServlet.java
 * @Project : Deployment-Server
 * @Date : 2014. 6. 27.
 * @작성자 : nklee
 * @프로그램설명 :
 */
@Slf4j
public class InitServlet extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	/**
	 * <pre>
	 * init
	 *
	 * <pre>.
	 *
	 * @param config the config
	 * @throws ServletException the servlet exception
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("===================================================");
		log.info("NShop Deployment Started!!");
		log.info("===================================================");
	}

	/**
	 * <pre>
	 * destroy
	 *
	 * <pre>.
	 */
	@Override
	public void destroy() {
		log.info("===================================================");
		log.info("NShop Deployment Stopped!!");
		log.info("===================================================");
	}
}
