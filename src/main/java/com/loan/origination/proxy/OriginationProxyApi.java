package com.loan.origination.proxy;

import java.util.List;

import com.loan.origination.dao.Employee;
import com.loan.origination.dao.Organization;

public interface OriginationProxyApi {

	public List<Organization> getOrganizationDetails();

	public Employee getEmployeeById();
	public Employee getEmployeeByIdWithOrgName(Integer empId, String orgName);

}
