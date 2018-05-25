package com.tcs.utils;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.apiresponse.GetResponse;

@Component
public class DataParser<T> {

	private T t;
	
    private Class<T> className;


	public DataParser() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DataParser(Class<T> className) {
		super();
		this.className = className;
	}


	public Class<T> getClassName() {
		return className;
	}


	public void setClassName(Class<T> className) {
		this.className = className;
	}


	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	

	@SuppressWarnings("unchecked")
	public T parseData(String decodedSek, GetResponse response){
		byte [] decyptedRek;
		try {
			decyptedRek = GstUtil.decrypt(response.getRek(),GstUtil.decodeBase64StringTOByte(decodedSek));
			String genHmac = GstUtil.generateHmac(response.getData(), decyptedRek);
			
			if(genHmac.equalsIgnoreCase(response.getHmac())) {
				byte[] data = GstUtil.decodeBase64StringTOByte(response.getData());
				ObjectMapper objectMapper = new ObjectMapper();
				t = objectMapper.readValue(data, className);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}