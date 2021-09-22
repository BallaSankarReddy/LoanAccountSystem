package com.loan.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.loan.common.JdbcEntity;

public class LoanAccount implements JdbcEntity {

	private Integer id;
	private Integer empId;
	private Integer loanTerms;
	private Integer isActive;
	private Integer orignalLoanAmount;
	private String monthlyAmount;
	private String totalPaidAmount;
	private String OutStandingAmount;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date createTimeStamp;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date modificationTimeStamp;
	private String companyName;
	
	

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getLoanTerms() {
		return loanTerms;
	}

	public void setLoanTerms(Integer loanTerms) {
		this.loanTerms = loanTerms;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
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

	public Date getCreateTimeStamp() {
		return createTimeStamp;
	}

	public void setCreateTimeStamp(Date createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}

	public Date getModificationTimeStamp() {
		return modificationTimeStamp;
	}

	public void setModificationTimeStamp(Date modificationTimeStamp) {
		this.modificationTimeStamp = modificationTimeStamp;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "LOAN_ACCOUNT";
	}

	@Override
	public String getEntityIdColumn() {
		// TODO Auto-generated method stub
		return "id";
	}

}
