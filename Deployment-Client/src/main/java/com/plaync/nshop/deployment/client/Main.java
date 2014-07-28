package com.plaync.nshop.deployment.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.plaync.nshop.deployment.client.handler.WarFileSender;
import com.plaync.nshop.deployment.client.vo.ParamData;


/**
 * @FileName : Main.java
 * @Project : Deployment-Client
 * @Date : 2014. 6. 30.
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class Main {

	/**
	 * <pre>
	 * main
	 *
	 * <pre>
	 * @param args
	 */
	public static void main(String[] args) {
		WarFileSender sender = new WarFileSender();

		// 파라미터 파싱 후 셋팅
		Map<String, String> param = parseConfigParams(args);
		ParamData data = new ParamData();
		data.putAll(param);

		// process sending
		sender.send(data);
	}

	/**
	 * <pre>
	 * parseConfigParams
	 *
	 * <pre>
	 * @param args
	 * @return
	 */
	public static Map<String, String> parseConfigParams(String[] args) {
		Map<String, String> paramMap = new HashMap<>();
		for (String pair : args) {
			String[] keyAndValue = StringUtils.split(pair, '=');
			paramMap.put(keyAndValue[0], keyAndValue[1]);
		}
		return paramMap;
	}
}
