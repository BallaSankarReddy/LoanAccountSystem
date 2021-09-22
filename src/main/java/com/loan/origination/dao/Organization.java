package com.loan.origination.dao;

public class Organization  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String origName;
	private Integer isActive;
	private Boolean activeStatus;

	public Organization() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrigName() {
		return origName;
	}

	public void setOrigName(String origName) {
		this.origName = origName;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	

	public Boolean getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

}
