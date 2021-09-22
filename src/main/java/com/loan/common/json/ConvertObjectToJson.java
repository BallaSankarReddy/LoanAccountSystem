package com.loan.common.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertObjectToJson {
	
	
	public static String convertObjectToJsonString(Object cl) {
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			return	 mapper.writeValueAsString(cl);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
