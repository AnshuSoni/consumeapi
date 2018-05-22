package com.tcs.returns;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
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

	@JsonFormat(shape=Shape.NUMBER_INT)
	private Integer num_files;
	private String eod_closed;

	@JsonFormat(shape=Shape.STRING.STRING, pattern="dd-MM-yyyy")
	private Date date;

	
	public FileCountResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileCountResponse(Integer num_files, String eod_closed, Date date) {
		super();
		this.num_files = num_files;
		this.eod_closed = eod_closed;
		this.date = date;
	}

	public Integer getFile_num() {
		return num_files;
	}

	public void setFile_num(Integer num_files) {
		this.num_files = num_files;
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
		return "FileCountResponse [num_files=" + num_files + ", eod_closed=" + eod_closed + ", date=" + date + "]";
	}
	
}
