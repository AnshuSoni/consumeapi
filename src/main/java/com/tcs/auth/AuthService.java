package com.tcs.auth;

import java.security.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.tcs.apiresponse.ErrorResponse;
import com.tcs.auth.datasource.AuthDataSource;
import com.tcs.utils.GstUtil;

@Component
public class AuthService {

	private static final Logger log = LoggerFactory.getLogger(AuthService.class);
	public static String AUTH_TOKEN="";
	public static String SEK = "";
	
	@Autowired
	private GstUtil gstutil;

	public static void setAUTH_TOKEN(String aUTH_TOKEN) {
		AUTH_TOKEN = aUTH_TOKEN;
	}

	public static void setSEK(String sEK) {
		SEK = sEK;
	}

	private HttpHeaders getAuthHeaders(String clientID,String clientSecret, String stateCode) {
		
		HttpHeaders header = new HttpHeaders();
		
		header.set("clientid", clientID);
		header.set("client-secret", clientSecret);
		header.set("state-cd", stateCode);
		header.setContentType(MediaType.APPLICATION_JSON);
		return header;
	}

	private AuthRequest prepareRequest(String action,String username,String password,String appkey) throws Exception {
		gstutil = new GstUtil();
		AuthRequest request = new AuthRequest();
		
		request.setAction(action);
		request.setUsername(username);
		request.setPassword(password);
		request.setApp_key(appkey);
		
		return request;
	}
	
	/**
	 * This method is meant to call auth API
	 * @return {@link AuthResponse} object containing auth_token and sek
	 * @throws RestClientException
	 * @throws Exception
	 */
	public AuthResponse doAuthentication(AuthDataSource authDataSource,String appKey) throws RestClientException, Exception {
		
		String encodedPassword = gstutil.encryptwithPK_PEM(authDataSource.getPassword().getBytes("UTF-8"));
		String encodedAppkey = gstutil.encryptwithPK_PEM(GstUtil.decodeBase64StringTOByte(appKey));
		
		HttpHeaders headers = getAuthHeaders(authDataSource.getClientId(), authDataSource.getClientSecret(), authDataSource.getStateCode());
		AuthRequest request = prepareRequest(authDataSource.getAction(), authDataSource.getUsername(), encodedPassword, encodedAppkey);
		
		HttpEntity<AuthRequest> entity = new HttpEntity<>(request, headers);
		
		log.debug("Headers : "+entity.getHeaders().toString());
		log.debug("Post data : "+entity.getBody().toString());
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<AuthResponse> response = restTemplate.exchange(authDataSource.getAuthURL(), HttpMethod.POST,entity,AuthResponse.class);
		
		//Code to return errors if any
		
		if(response.getBody().getStatus_cd().equals("0")) {
			ResponseEntity<ErrorResponse> errorResponse = restTemplate.exchange(authDataSource.getAuthURL(), HttpMethod.POST,entity,ErrorResponse.class);
			log.debug(errorResponse.toString());
		}
		
		log.debug(response.getBody().toString());
		
		//setting static fields
		
		setAUTH_TOKEN(response.getBody().getAuth_token());
		setSEK(response.getBody().getSek());
		
		//decrypting SEK
		log.debug("Crypto Policy : "+Security.getProperty("crypto.policy"));
		
		//byte[] decrypt_auth_sek=gstutil.decrypt("317HdU61h0JGs16fS1z86bjKSm1RP259orDJ3gVjUMh30grP0n24lhrEuVmlH3xl", (gstutil.decodeBase64StringTOByte(encodedAppkey)));
//		
//		byte[] decrypt_auth_sek =  gstutil.decrypt(SEK, gstutil.decodeBase64StringTOByte(appKey));
//		log.debug("SEK : "+gstutil.encodeBase64String(decrypt_auth_sek));
		
		return response.getBody();
	}
}