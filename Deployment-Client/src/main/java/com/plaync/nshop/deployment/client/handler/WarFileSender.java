package com.plaync.nshop.deployment.client.handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.plaync.nshop.deployment.client.vo.ParamData;

/**
 * @FileName : WarFileSender.java
 * @Project : Deployment-Client
 * @Date : 2014. 6. 30.
 * @작성자 : nklee
 * @프로그램설명 :
 */
@Slf4j
public class WarFileSender {

	/**
	 * <pre>
	 * send
	 *
	 * <pre>
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public void send(ParamData param) {

		CloseableHttpResponse response = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {
			// make body
			HttpPost httpPost = new HttpPost(param.get("uploadURL"));

			// make builder
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

			// add body part
			addBody(builder, param);

			// request
			HttpEntity reqEntity = builder.build();
			httpPost.setEntity(reqEntity);
			log.info("executing request " + httpPost.getRequestLine());
			response = httpclient.execute(httpPost);

			// response
			log.info("----------------------------------------");
			log.info(response.getStatusLine().toString());
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				log.info("Response content length: " + resEntity.getContentLength());
			}

			// stream close
			EntityUtils.consume(resEntity);

		} catch (IOException ex) {
			log.error("##ERROR", ex);
		} finally {
			close(httpclient);
			close(response);
		}
	}

	/**
	 * <pre>
	 * addBody
	 *
	 * <pre>
	 * @param builder
	 */
	private void addBody(MultipartEntityBuilder builder, ParamData param) {
		String filePaths = param.get("uploadFile");
		String command = param.get("command");
		String commandArg = param.get("commandArg");

		// 파일 업로드
		List<String> uploadFileList = getFileList(filePaths);
		for (String uploadFilePath : uploadFileList) {
			builder.addBinaryBody("files", new File(uploadFilePath));
		}

		// 프로그램 실행 파라미터
		builder.addTextBody("command", command);
		builder.addTextBody("commandArg", commandArg);
	}

	/**
	 * <pre>
	 * getFileList
	 *
	 * <pre>
	 * @param warFilePaths
	 * @return
	 */
	private List<String> getFileList(String warFilePaths) {
		if (warFilePaths == null) {
			throw new RuntimeException("upload file not found!!");
		}

		// 파일 path 추출 후 List add
		List<String> fileList = new ArrayList<>();
		String[] files = warFilePaths.split(",");
		for (String filePath : files) {
			fileList.add(filePath);
		}

		return fileList;
	}

	/**
	 * <pre>
	 * close
	 *
	 * <pre>
	 * @param response
	 */
	private void close(CloseableHttpResponse response) {
		try {
			if (response != null) {
				response.close();
			}
		} catch (IOException e) {}
	}

	/**
	 * <pre>
	 * close
	 *
	 * <pre>
	 * @param httpclient
	 */
	private void close(CloseableHttpClient httpclient) {
		try {
			if (httpclient != null) {
				httpclient.close();
			}
		} catch (IOException e) {}
	}

}
