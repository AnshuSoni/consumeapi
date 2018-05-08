package com.tcs.apiresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FileResponse {
	
	@JsonProperty(value="url")
	private String url;
	
	@JsonProperty(value="file_num")
	private String fileNum;
	
	//Count of records in the file
	@JsonProperty(value="cnt")
	private String cnt;
	
	//Sha256 hash of the file. to check intigrity
	@JsonProperty(value="hash")
	private String hash;

	public FileResponse(String url, String fileNum, String cnt, String hash) {
		super();
		this.url = url;
		this.fileNum = fileNum;
		this.cnt = cnt;
		this.hash = hash;
	}

	public FileResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public String toString() {
		return "FileResponse [url=" + url + ", fileNum=" + fileNum + ", cnt=" + cnt + ", hash=" + hash + "]";
	}
	
}
