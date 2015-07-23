package com.samhyun.study.board.domain;

import org.springframework.web.multipart.MultipartFile;

public class CkEditorFile {

	private String attachPath;
	private MultipartFile upload;
	private String filename;
	private String CKEditorFuncNum;

	public CkEditorFile() {
		super();
	}

	public CkEditorFile(String attachPath, MultipartFile upload,
			String filename, String cKEditorFuncNum) {
		super();
		this.attachPath = attachPath;
		this.upload = upload;
		this.filename = filename;
		CKEditorFuncNum = cKEditorFuncNum;
	}

	public String getAttachPath() {
		return attachPath;
	}

	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}

	public MultipartFile getUpload() {
		return upload;
	}

	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}

	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}
}
