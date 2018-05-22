package com.tcs.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.auth.AuthResponse;
import com.tcs.auth.AuthService;
import com.tcs.auth.datasource.AuthDataSource;
import com.tcs.auth.datasource.ReturnDataSource;
import com.tcs.config.HeaderConfig;
import com.tcs.returns.FileCountRequest;
import com.tcs.returns.FileCountResponse;
import com.tcs.returns.ReturnService;
import com.tcs.returns.ReturnServiceImpl;
import com.tcs.utils.GstUtil;

@Controller
public class GstRestController {

	private static final Logger log = LoggerFactory.getLogger(GstRestController.class);
	
	//Setting appkey to session
	private String appkey="";
	
	@Autowired
	private GstUtil gstUtil;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthDataSource authDataSource;
	
	@Autowired
	private ReturnDataSource returnDataSource;
	
	@Autowired
	private ReturnServiceImpl returnService;
	
	@Autowired
	private HeaderConfig authenticatedHeaders;
	/**
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String index(Model model,HttpSession session) throws Exception{
		appkey = gstUtil.generateSecureKey();
		session.setAttribute("appkey", appkey);
		
		log.debug("appkey : "+appkey);
		
		AuthResponse authResponse = authService.doAuthentication(authDataSource, appkey);
		
		log.debug(authResponse.toString());
		// decrypting sek and passing it to session
		String sek = authResponse.getSek();
		byte[] ek =  gstUtil.decrypt(sek, gstUtil.decodeBase64StringTOByte(appkey));
		String decodedSek = gstUtil.encodeBase64String(ek); //decodedSek = encoded key
		
		
		session.setAttribute("decodedSek", decodedSek);
		session.setAttribute("authToken", authResponse.getAuth_token());
		//initiate authenticated headers and set it into session
		
		HttpHeaders header = authenticatedHeaders.getAuthenticatedHeaders(authDataSource, authResponse.getAuth_token());
		session.setAttribute("authHeaders", header);
		
		return "index";
	}
	
	@GetMapping("/return-filecount")
	public String showReturnFileCountPage(Model model) {
		
		model.addAttribute("filecnt", new FileCountRequest());
		return "return_file_count";
	}
	/**
	 * This function call for Return File Count Request and parses it output to the dummy page
	 * 
	 * @param filecnt
	 * @return
	 */
	@PostMapping(value="/return-filecount", produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<String> processR1ReturnCount(@ModelAttribute("filecnt") FileCountRequest filecnt, @SessionAttribute("authHeaders") HttpHeaders header, @SessionAttribute("appkey") String appkey, @SessionAttribute("authToken") String authToken,@SessionAttribute("decodedSek") String decodedSek,Model model ) {
		
		//FileCountResponse response = returnService.processFileCountAPI(returnDataSource, header, filecnt.getType());
		
		//model.addAttribute("fileCountResponse", response);
		
		List<String> list = new ArrayList<>();
		log.debug("this is appkey inside post return file count : "+appkey);
		log.debug("FileCountRequest Parameters : "+filecnt.toString());
		
		
		FileCountResponse response = returnService.getFileCount(returnDataSource, authToken, header, appkey, decodedSek,filecnt);
		
		model.addAttribute("returnFileCount", response);
		
		
		ObjectMapper mapper = new ObjectMapper();
		String read ="";
		try {
			read = mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.add(read);
		return list;
	}
		
}
