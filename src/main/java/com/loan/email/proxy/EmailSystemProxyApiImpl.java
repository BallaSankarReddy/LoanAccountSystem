package com.loan.email.proxy;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.loan.common.util.BaseProxy;
import com.loan.email.dao.EmailEnitity;
import com.loan.origination.dao.Organization;
import com.loan.repository.SystemConfigData;

@Component
public class EmailSystemProxyApiImpl extends BaseProxy implements EmailSystemProxyApi {

	@Override
	public String saveEmployee(EmailEnitity emailEnitity) {
		
		try {
			
			

			HttpHeaders httpHeaders = this.getHttpHeaders();
			HttpEntity<EmailEnitity> entity = new HttpEntity<EmailEnitity>(httpHeaders);

			String url = SystemConfigData.BASE_EMAIL_SYSTEM_URL;
			url = url + "save/employee";
			System.out.println("ORIG_SYET_BASE_URL {}" + url);
			//ResponseEntity<String> exchange = this.restTemplate.exchange(url, HttpMethod.POST, entity,	String.class);
			
			ResponseEntity<String> exchange = restTemplate.postForEntity(url, emailEnitity, String.class);
			if(exchange.getBody()!=null) {
				
				return exchange.getBody();
			}
					
		
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getCause().getLocalizedMessage());
		}
		return null;
	}
	

}
