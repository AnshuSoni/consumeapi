package com.tcs.config;

import java.util.Enumeration;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.tcs.auth.datasource.AuthDataSource;
import com.tcs.auth.datasource.ReturnDataSource;

@Configuration
@PropertySources({
	@PropertySource("classpath:gstapi.properties")
})
public class PropertyConfig {
	
	@Value("${auth.client_id}")
	String clientId;
	@Value("${auth.client_secret}")
	String clientSecret;
	@Value("${auth.url}")
	String authURL;
	@Value("${state_cd}")
	String stateCd;
	
	@Value("${auth.username}")
	String username;
	
	@Value("${auth.password}")
	String password;
	
	@Value("${auth.action}")
	String action;
	
	//Now calling keys for returns filecount api
	
	@Value("${return.filecount.url}")
	String returnFileCountUrl;
	
	@Value("${return.filecount.action}")
	String returnFileCountAction;
	
	@Bean
	public AuthDataSource getAuthDataSource() {
		AuthDataSource authDataSource = new AuthDataSource();
		authDataSource.setClientId(clientId);
		authDataSource.setClientSecret(clientSecret);
		authDataSource.setStateCode(stateCd);
		authDataSource.setAuthURL(authURL);
		authDataSource.setAction(action);
		authDataSource.setUsername(username);
		authDataSource.setPassword(password);
		return authDataSource;
	}
	
	@Bean
	public ReturnDataSource getReturnDataSource() {
		ReturnDataSource returnDataSource = new ReturnDataSource();
		returnDataSource.setAction(returnFileCountAction);
		returnDataSource.setFileCountUrl(returnFileCountUrl);
		returnDataSource.setStateCd(stateCd);
		return returnDataSource;
	}
	@Bean
	public static PropertySourcesPlaceholderConfigurer property() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		return propertySourcesPlaceholderConfigurer;
	}
}
