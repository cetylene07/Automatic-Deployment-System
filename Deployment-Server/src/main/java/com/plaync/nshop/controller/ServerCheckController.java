package com.plaync.nshop.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @FileName : ServerCheckController.java
 * @Project : Rundeck Project
 * @Date : 2014. 7. 23.
 * @작성자 : yhkim
 * @프로그램설명 :
 */
@Slf4j
@Controller
@RequestMapping("/check")
public class ServerCheckController {
	/**
	 * <pre>
	 * displayForm
	 *
	 * <pre>
	 * @return
	 */
	@RequestMapping(value = "/show", method = RequestMethod.GET )
	public String displayForm(ModelMap model) throws IOException {
		
		URL url;
		HttpURLConnection conn = null;
		
		List<ServerInfo>checkList = new ArrayList<ServerInfo>();		
		
		// 서버 리스트 추출
		List<String>serverList = getServerList();
		
		for(String ip : serverList)	{
			
			ServerInfo serverInfo = new ServerInfo();
			
			
			// ip로 서버 접속
			url = new URL(ip);	
			try	{
				conn = (HttpURLConnection)url.openConnection();				
				
				
				//checkList에 값 저장
				serverInfo.setConnectStatus(conn.getResponseMessage());
				serverInfo.setUrlName(conn.getURL().toString());
				serverInfo.setBuildVersion(String.valueOf(conn.getContentLengthLong()));
				checkList.add(serverInfo);
				
				log.info("checkList size is = "+checkList.size());
			} catch(IOException e) {
				serverInfo.setConnectStatus("에러");
			} finally {
				conn.disconnect();	
			}
		}		
		// 서버 상태 및 정보
		model.addAttribute("checkList", checkList);
		
		return "check/serverCheckForm";
	}

	/**
	 * 
	 * @return
	 */
	private List<String> getServerList() {
		List<String>serverList = new ArrayList<String>();
		serverList.add("http://localhost:8080");
		serverList.add("http://192.168.56.101:8080/server");
		serverList.add("http://192.168.56.102:8080/L1");
		serverList.add("http://192.168.56.103:8080/L2");
		serverList.add("http://www.yahoo.com");
		serverList.add("http://www.naver.com");
		serverList.add("http://www.daum.net");
		
		return serverList;
	}


	/**
	 * <pre>
	 * exception
	 *
	 * <pre>
	 * @return
	 */
	@ExceptionHandler(value = {Exception.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
	public String exception() {
		return "FAIL";
	}

	/**
	 * <pre>
	 * responseView
	 *
	 * <pre>
	 * @return
	 */	
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	@ResponseBody
	public String responseView() {

		return "SUCCESS";
//		String code;
//		return "check/serverCheckResponseView";
	}	

}

