package com.loan.common.util;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public abstract class BaseProxy {
	
	@Autowired
	public RestTemplate restTemplate;
	
	public HttpHeaders getHttpHeaders() {
		HttpHeaders handler = new HttpHeaders();
		handler.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		return handler;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	
	

}
