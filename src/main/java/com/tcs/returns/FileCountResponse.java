package com.tcs.returns;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FileCountResponse {
/*
 * {
  "file_num": 100,
  "eod_closed": "Y",
  "date": "14-07-2016"
}
 */
	
	private Integer file_num;
	private String eod_closed;

	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date date;

	
	public FileCountResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileCountResponse(Integer file_num, String eod_closed, Date date) {
		super();
		this.file_num = file_num;
		this.eod_closed = eod_closed;
		this.date = date;
	}

	public Integer getFile_num() {
		return file_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public String getEod_closed() {
		return eod_closed;
	}

	public void setEod_closed(String eod_closed) {
		this.eod_closed = eod_closed;
	}

	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "FileCountResponse [file_num=" + file_num + ", eod_closed=" + eod_closed + ", date=" + date + "]";
	}
	
}
