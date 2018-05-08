package com.tcs.config;

//import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.tcs.auth.datasource.AuthDataSource;

@Component
public class HeaderConfig {

	private static final Logger log = LoggerFactory.getLogger(HeaderConfig.class);

	public HttpHeaders getAuthenticatedHeaders(AuthDataSource authDataSource,String authToken,String encodedKey) {
		HttpHeaders header = new HttpHeaders();

		header.setContentType(MediaType.APPLICATION_JSON);
		header.setAccept(
				Arrays.asList(MediaType.APPLICATION_JSON)
				);
		header.set("Media Type", "application/json");
		header.set("clientid", authDataSource.getClientId());
		header.set("client-secret", authDataSource.getClientSecret());
		header.set("username", authDataSource.getUsername());
		header.set("state-cd", authDataSource.getStateCode());
		log.debug("State Code : "+authDataSource.getStateCode());
		header.set("auth-token", authToken);
		
		return header;
	}
}
