package com.loan.email.dao;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EmailEnitity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String empName;
	private String emailId;
	private String phoneNumber;
	private String companyName;
	private String loanNumber;

	private Integer orgId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date createdTimeStamp;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date modifiedTimeStamp;

	private Integer loanTerms;
	private Integer orignalLoanAmount;
	private String monthlyAmount;
	private String totalPaidAmount;
	private String OutStandingAmount;
	private String emilSubject;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public Date getModifiedTimeStamp() {
		return modifiedTimeStamp;
	}

	public void setModifiedTimeStamp(Date modifiedTimeStamp) {
		this.modifiedTimeStamp = modifiedTimeStamp;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getLoanTerms() {
		return loanTerms;
	}

	public void setLoanTerms(Integer loanTerms) {
		this.loanTerms = loanTerms;
	}

	public Integer getOrignalLoanAmount() {
		return orignalLoanAmount;
	}

	public void setOrignalLoanAmount(Integer orignalLoanAmount) {
		this.orignalLoanAmount = orignalLoanAmount;
	}

	public String getMonthlyAmount() {
		return monthlyAmount;
	}

	public void setMonthlyAmount(String monthlyAmount) {
		this.monthlyAmount = monthlyAmount;
	}

	public String getTotalPaidAmount() {
		return totalPaidAmount;
	}

	public void setTotalPaidAmount(String totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
	}

	public String getOutStandingAmount() {
		return OutStandingAmount;
	}

	public void setOutStandingAmount(String outStandingAmount) {
		OutStandingAmount = outStandingAmount;
	}

	public String getEmilSubject() {
		return emilSubject;
	}

	public void setEmilSubject(String emilSubject) {
		this.emilSubject = emilSubject;
	}

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

}
