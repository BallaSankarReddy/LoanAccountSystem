package com.loan.common;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public interface SimpleJdbcUpdateOperations {

	
	SimpleJdbcUpdateOperations withTableName(String tableName);
	SimpleJdbcUpdateOperations withSchenaName(String schenaName);
	
	SimpleJdbcUpdateOperations withCatalogName(String catalogName);
	
	SimpleJdbcUpdateOperations updatingColumns(List<String> columnNames);
	

	SimpleJdbcUpdateOperations restrictedColoumns(List<String> columnNames);
	SimpleJdbcUpdateOperations restrictedColoumns(Map<String,Operator> columnNamesToOperators);
	int execute(Map<String,Object> updatingValues,Map<String,Object> restrictingValues);
	int execute(SqlParameterSource updatingValues,SqlParameterSource restrictingValues);
}
