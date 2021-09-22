package com.loan.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
public interface JdbcEntity {

	@JsonIgnore
	public String getTableName();

	@JsonIgnore
	public String getEntityIdColumn();
}
