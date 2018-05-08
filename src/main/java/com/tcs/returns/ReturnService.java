package com.tcs.returns;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.tcs.auth.datasource.ReturnDataSource;

@Service
public interface ReturnService {
	
	public FileCountResponse processFileCountAPI(ReturnDataSource returnDataSource,HttpHeaders header,String returnType);
	public String getFileCount(ReturnDataSource dataSource,String authToken, HttpHeaders header);
}
