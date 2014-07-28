package com.plaync.nshop.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.plaync.nshop.common.util.process.ProcessHandler;
import com.plaync.nshop.common.util.process.ProcessVO;
import com.plaync.nshop.model.FileUploadForm;

/**
 * @FileName : FileUploadController.java
 * @Project : Deployment-Server
 * @Date : 2014. 6. 27.
 * @작성자 : nklee
 * @프로그램설명 :
 */
@Slf4j
@Controller
@RequestMapping("/file")
public class FileUploadController {

	/** war 파일 업로드 path */
	@Value("#{baseConfig['war.upload.path']}")
	private String warUploadPath;

	/**
	 * <pre>
	 * displayForm
	 *
	 * <pre>
	 * @return
	 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String displayForm() {
		return "file/fileUploadForm";
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
	 * save
	 *
	 * <pre>
	 * @param uploadForm
	 * @param map
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute FileUploadForm uploadForm, Model map) {
		List<MultipartFile> files = uploadForm.getFiles();
		boolean saved = false;

		File warPathDir = new File(warUploadPath);
		if (warPathDir.exists() == false) {
			new File(warUploadPath).mkdirs(); // war 파일 저장 디렉토리 생성
		}

		if (files != null && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				String fileName = multipartFile.getOriginalFilename();

				if (StringUtils.isNotEmpty(fileName)) { // 파일이 존재한다면 물리적 경로에 저장 진행
					File destFilePath = new File(warUploadPath + fileName);
					saved = writeFile(multipartFile, destFilePath);
					log.info("fileName=" + fileName + ", destFilePath=" + destFilePath + ", saved=" + saved);
				}
			}
		}

		// 외부 프로그램 실행 여부 확인
		boolean existsPostExec = checkPostExec(uploadForm);

		if (saved && existsPostExec) {
			// war 파일 저장 성공이면 외부 프로그램 실행
			ProcessHandler handler = new ProcessHandler();
			ProcessVO processVO = new ProcessVO();
			processVO.setExecProgram(uploadForm.getCommand());
			processVO.setParam(uploadForm.getCommandArg());
			processVO = handler.job(processVO);
		}

		return "file/fileUploadForm"; // 성공
	}

	/**
	 * <pre>
	 * checkPostExec
	 *
	 * <pre>
	 * @param uploadForm
	 * @return
	 */
	private boolean checkPostExec(FileUploadForm uploadForm) {
		if (StringUtils.isEmpty(uploadForm.getCommand())) {
			return false;
		}

		if (StringUtils.isEmpty(uploadForm.getCommandArg())) {
			return false;
		}

		return true;
	}


	/**
	 * <pre>
	 * writeFile
	 *
	 * <pre>
	 * @param file
	 * @param destPath
	 */
	private boolean writeFile(MultipartFile file, File destPath) {
		boolean existsFile = false;
		try {
			file.transferTo(destPath);
			existsFile = destPath.isFile();
		} catch (IllegalStateException | IOException ex) {
			log.error("##ERROR", ex);
			throw new RuntimeException(ex);
		}

		return existsFile;
	}
}
