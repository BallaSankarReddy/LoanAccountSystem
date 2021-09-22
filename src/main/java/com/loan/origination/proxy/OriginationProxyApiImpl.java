package com.loan.origination.proxy;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.loan.common.util.BaseProxy;
import com.loan.origination.dao.Employee;
import com.loan.origination.dao.Organization;
import com.loan.repository.SystemConfigData;

@Component
public class OriginationProxyApiImpl extends BaseProxy implements OriginationProxyApi {

	@Override
	public List<Organization> getOrganizationDetails() {
		HttpHeaders httpHeaders = this.getHttpHeaders();
		HttpEntity<Organization> entity = new HttpEntity<Organization>(httpHeaders);

		String url = SystemConfigData.ORIG_SYET_BASE_URL;
		url = url + SystemConfigData.ORG_BASE+"/getOrigination";
		System.out.println("ORIG_SYET_BASE_URL {}" + url);
		ResponseEntity<Organization[]> exchange = this.restTemplate.exchange(url, HttpMethod.GET, entity,
				Organization[].class);
		return Arrays.asList(exchange.getBody());
	}

	@Override
	public Employee getEmployeeById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployeeByIdWithOrgName(Integer empId, String orgName) {
		HttpHeaders httpHeaders = this.getHttpHeaders();
		HttpEntity<Organization> entity = new HttpEntity<Organization>(httpHeaders);

		String url = SystemConfigData.ORIG_SYET_BASE_URL;
		String string = url + SystemConfigData.EMP_BASE;
		url = string+"/getEmployee/"+empId+"/"+orgName;
		System.out.println("ORIG_SYET_BASE_URL {}" + url);
		ResponseEntity<Employee> exchange = this.restTemplate.exchange(url, HttpMethod.GET, entity,
				Employee.class);
		return exchange.getBody();
				
	}

}
