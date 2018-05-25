package com.tcs.returns;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.apiresponse.ErrorResponse;
import com.tcs.apiresponse.GetResponse;
import com.tcs.auth.datasource.ReturnDataSource;
import com.tcs.config.HeaderConfig;
import com.tcs.utils.DataParser;
import com.tcs.utils.GstUtil;

@Service
public class ReturnServiceImpl implements ReturnService{

	private static final Logger log = LoggerFactory.getLogger(ReturnServiceImpl.class);
	@Autowired
	ReturnDataSource returnDataSource;
	
	@Autowired
	HeaderConfig headerConfig;
	
	
	private FileCountRequest prepareRequest(String action, String type, String state_cd, String date) {
		
		FileCountRequest request = null;
		try {
			request = new FileCountRequest(action, type, state_cd, date);
		} catch (ParseException e) {
			log.debug("Date parse exception");
		}
		
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

	public FileCountResponse getFileCount(ReturnDataSource dataSource,String authToken,HttpHeaders header, String appKey,String decodedSek,FileCountRequest fileCountRequest) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String date = "";
		date = sdf.format(fileCountRequest.getDate());
		DataParser<FileCountResponse> dataParser = new DataParser<>(FileCountResponse.class);
		
		String url = dataSource.getFileCountUrl()+"?action="+fileCountRequest.getAction()+"&type="+fileCountRequest.getType()+ "&state_cd="+fileCountRequest.getState_cd()+"&date="+date;
		
		HttpEntity<String> entity = new HttpEntity<String>(header);
		
		FileCountResponse fileCountResponse = null;
		ResponseEntity<ErrorResponse> errResponse = null;
		
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<GetResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity,GetResponse.class); 
		
		if(response.getBody().getStatusCd().equals("1")) {
			fileCountResponse=	dataParser.parseData(decodedSek, response.getBody());
			
		}else{
			 errResponse = restTemplate.exchange(url, HttpMethod.GET, entity,ErrorResponse.class);
			 log.debug(errResponse.toString());
		}
		
		log.debug(response.toString());
		return fileCountResponse;
	}

	@Override
	public FileCountResponse getFileCount(ReturnDataSource dataSource, String authToken, HttpHeaders header) {
		// TODO Auto-generated method stub
		return null;
	}
}
