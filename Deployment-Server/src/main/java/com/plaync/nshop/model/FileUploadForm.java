package com.plaync.nshop.model;

import java.util.List;

import lombok.Data;

import org.springframework.web.multipart.MultipartFile;

/**
 * @FileName : FileUploadForm.java
 * @Project : Deployment-Server
 * @Date : 2014. 6. 27.
 * @작성자 : nklee
 * @프로그램설명 :
 */
@Data
public class FileUploadForm {

	/** The files. */
	private List<MultipartFile> files;

	private String command;

	private String commandArg;
}
