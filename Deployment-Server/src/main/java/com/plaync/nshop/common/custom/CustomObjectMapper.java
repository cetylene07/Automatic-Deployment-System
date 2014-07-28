/*
 * Copyright (c) 2014 namkyu.
 * All right reserved.
 *
 */
package com.plaync.nshop.common.custom;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * @FileName : CustomObjectMapper.java
 * @Project : Deployment-Server
 * @Date : 2014. 6. 27.
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class CustomObjectMapper extends ObjectMapper {

	/**
	 * Instantiates a new custom object mapper.
	 */
	public CustomObjectMapper() {
		configure(org.codehaus.jackson.map.SerializationConfig.Feature.USE_ANNOTATIONS, false);
	}
}
