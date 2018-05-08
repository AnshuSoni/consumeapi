package com.tcs.returns;

import java.util.Arrays;
import java.util.Date;

import javax.xml.ws.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.tcs.apiresponse.ErrorResponse;
import com.tcs.auth.datasource.ReturnDataSource;
import com.tcs.config.HeaderConfig;

public class ReturnServiceImpl implements ReturnService{

	private static final Logger log = LoggerFactory.getLogger(ReturnServiceImpl.class);
	@Autowired
	ReturnDataSource returnDataSource;
	
	@Autowired
	HeaderConfig headerConfig;
	
	
	private FileCountRequest prepareRequest(String action, String type, String state_cd, String date){
		
		FileCountRequest request = new FileCountRequest(action, type, state_cd, date);
		
		return request;
	}
	
	@Override
	public FileCountResponse processFileCountAPI(ReturnDataSource dataSource, HttpHeaders headers,String returnType){
		
		FileCountResponse fileCountResponse = null;
		RestTemplate restTemplate = new RestTemplate();
		log.debug(dataSource.getFileCountUrl());
		FileCountRequest request =  prepareRequest(dataSource.getAction(), returnType, dataSource.getStateCd(), "27-08-2017");
		HttpEntity<FileCountRequest> entity = new HttpEntity<>(request, headers);
		log.debug("Request Header For ReturnFileCount : "+entity.toString());
		
		HttpEntity<String> stringEntity = new HttpEntity<>(headers);
		String returnCountURL = dataSource.getFileCountUrl()+"?action="+dataSource.getAction()+"&type=R1"+"&state_cd="+dataSource.getStateCd()+"&date=21-08-2017";
		log.debug(returnCountURL);
		HttpEntity<FileCountResponse> response;
		try {

			//String abc = restTemplate.getForObject(returnCountURL, String.class, stringEntity);
			//log.debug(abc);
			response =  restTemplate.exchange(dataSource.getFileCountUrl(), HttpMethod.GET, entity, FileCountResponse.class);
			log.debug(response.getBody().toString());
			
			fileCountResponse = response.getBody();
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
		return fileCountResponse;
	
	}

	public String getFileCount(ReturnDataSource dataSource,String authToken,HttpHeaders header) {
		String url = dataSource.getFileCountUrl()+"?action=FILECNT&type=R1&state_cd=20&date=21-08-2017";
		
		HttpEntity<String> entity = new HttpEntity<String>(header);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class); 
		
		log.debug(response.getBody());
		
		return response.getBody();
	}
}
