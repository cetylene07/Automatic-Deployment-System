



package com.plaync.nshop.common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * @FileName : CustomSimpleMappingExceptionResolver.java
 * @Project : Deployment-Server
 * @Date : 2014. 6. 27.
 * @작성자 : nklee
 * @프로그램설명 :
 */
@Slf4j
public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {

	/**
	 * <pre>
	 * resolveException
	 *
	 * <pre>.
	 *
	 * @param request the request
	 * @param response the response
	 * @param handler the handler
	 * @param ex the ex
	 * @return the model and view
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		log.error("##ERROR", ex);
		return super.resolveException(request, response, handler, ex);
	}
}
