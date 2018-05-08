package com.tcs.apiresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GetResponse {
	
	@JsonProperty(value="status_cd")
	private String statusCd;
	@JsonProperty(value="data")
	private String data;
	@JsonProperty(value="rek")
	private String rek;
	@JsonProperty(value="hmac")
	private String hmac;
	
	
	
	public GetResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public GetResponse(String statusCd, String data, String rek, String hmac) {
		super();
		this.statusCd = statusCd;
		this.data = data;
		this.rek = rek;
		this.hmac = hmac;
	}



	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getRek() {
		return rek;
	}
	public void setRek(String rek) {
		this.rek = rek;
	}
	public String getHmac() {
		return hmac;
	}
	public void setHmac(String hmac) {
		this.hmac = hmac;
	}

	@Override
	public String toString() {
		return "GetResponse [statusCd=" + statusCd + ", data=" + data + ", rek=" + rek + ", hmac=" + hmac + "]";
	}
	

}
