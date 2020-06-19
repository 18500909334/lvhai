package com.ucap.zfw.entity;

import java.io.Serializable;

public class Atach implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//附件id
	private String id;
	//附件路径
	private String filePah;
	//附件原名称
	private String fileName;
	//附件格式
	private String fileExtensionName;
	//附件大小
	private String fileSize;
	//附件格式
	private String uplodFileName;
	//备注
	private String memo;
	//是否是标题图片
	private String isTitleImg;
	//附件
	private byte[] filedata;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFilePah() {
		return filePah;
	}
	public void setFilePah(String filePah) {
		this.filePah = filePah;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExtensionName() {
		return fileExtensionName;
	}
	public void setFileExtensionName(String fileExtensionName) {
		this.fileExtensionName = fileExtensionName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getUplodFileName() {
		return uplodFileName;
	}
	public void setUplodFileName(String uplodFileName) {
		this.uplodFileName = uplodFileName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getIsTitleImg() {
		return isTitleImg;
	}
	public void setIsTitleImg(String isTitleImg) {
		this.isTitleImg = isTitleImg;
	}
	public byte[] getFiledata() {
		return filedata;
	}
	public void setFiledata(byte[] filedata) {
		this.filedata = filedata;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
