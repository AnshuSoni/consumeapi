package com.tcs.returns;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.tcs.auth.datasource.ReturnDataSource;


public interface ReturnService {
	
	public FileCountResponse processFileCountAPI(ReturnDataSource returnDataSource,HttpHeaders header,String returnType);
	public FileCountResponse getFileCount(ReturnDataSource dataSource,String authToken, HttpHeaders header);
}
