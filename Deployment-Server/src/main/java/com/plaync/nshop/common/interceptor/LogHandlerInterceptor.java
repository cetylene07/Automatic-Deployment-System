



package com.plaync.nshop.common.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @FileName : LogHandlerInterceptor.java
 * @Project : Deployment-Server
 * @Date : 2014. 6. 27.
 * @작성자 : nklee
 * @프로그램설명 :
 */
@Slf4j
public class LogHandlerInterceptor extends HandlerInterceptorAdapter {

	/**
	 * <pre>
	 * preHandle
	 *
	 * <pre>.
	 *
	 * @param request the request
	 * @param response the response
	 * @param handler the handler
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		String addr = request.getRemoteAddr();
		String scheme = request.getScheme();
		log.info("##############################################################################");
		log.info("## REQUEST=" + scheme + "://" + addr + ", ACTION=" + path + getParameter(request));
		log.info("##############################################################################");
		return true;
	}

	/**
	 * Gets the parameter.
	 *
	 * @param req the req
	 * @return the parameter
	 */
	@SuppressWarnings("unchecked")
	private String getParameter(HttpServletRequest req) {
		Enumeration<String> names = req.getParameterNames();
		StringBuffer sb = new StringBuffer("{");
		String key = null;
		while (names.hasMoreElements()) {
			key = names.nextElement();
			if (req.getParameterValues(key) != null) {
				sb.append(key).append("=[");
				for (String values : req.getParameterValues(key)) {
					sb.append(values).append(",");
				}
				sb.append("]");
			} else {
				sb.append(key).append("=").append(req.getParameter(key)).append(",");
			}
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * <pre>
	 * afterCompletion
	 *
	 * <pre>.
	 *
	 * @param request the request
	 * @param response the response
	 * @param handler the handler
	 * @param ex the ex
	 * @throws Exception the exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
}
