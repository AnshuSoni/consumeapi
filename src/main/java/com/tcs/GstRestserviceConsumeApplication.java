package com.tcs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpHeaders;

import com.tcs.auth.AuthResponse;
import com.tcs.auth.AuthService;
import com.tcs.auth.datasource.AuthDataSource;
import com.tcs.auth.datasource.ReturnDataSource;
import com.tcs.config.HeaderConfig;
import com.tcs.returns.ReturnService;
import com.tcs.returns.ReturnServiceImpl;
import com.tcs.utils.GstUtil;

@SpringBootApplication
public class GstRestserviceConsumeApplication {

	private static final Logger log = LoggerFactory.getLogger(GstRestserviceConsumeApplication.class);
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(GstRestserviceConsumeApplication.class, args);
		AuthService service = context.getBean(AuthService.class);
		
		AuthDataSource authDataSource = (AuthDataSource)context.getBean(AuthDataSource.class);
		ReturnDataSource returnDataSource =(ReturnDataSource) context.getBean(ReturnDataSource.class);
		HeaderConfig headerConfig = new HeaderConfig();
		log.debug(authDataSource.toString());
		log.debug(returnDataSource.toString());
		
		GstUtil gstUtil = context.getBean(GstUtil.class);
		
		
		
		
		try {
			final String appkey = gstUtil.generateSecureKey();
			log.debug("Appkey : "+appkey);
			AuthResponse authResponse = service.doAuthentication(authDataSource, appkey);
			
			// now processing returnfile count
			ReturnService returnService = new ReturnServiceImpl();
			// preparing headers for Return
			//Decrypting SEK to get EK, to pass along headers for return processing
			String sek = authResponse.getSek();
			byte[] ek =  gstUtil.decrypt(sek, gstUtil.decodeBase64StringTOByte(appkey));
			String decodedSek = gstUtil.encodeBase64String(ek); //decodedSek = encoded key
			
			HttpHeaders returnHeader = headerConfig.getAuthenticatedHeaders(authDataSource, authResponse.getAuth_token(),decodedSek);
			//returnService.processFileCountAPI(returnDataSource, returnHeader,"R1");
			returnService.getFileCount(returnDataSource, authResponse.getAuth_token(),returnHeader);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
