package com.loan.common;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class SimpleJdbcUpdate extends AbstractJdbcUpdate implements SimpleJdbcUpdateOperations {

	
	public SimpleJdbcUpdate(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}
	
	protected SimpleJdbcUpdate(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public SimpleJdbcUpdateOperations withTableName(String tableName) {
		setTableName(tableName);
		return this;
	}

	@Override
	public SimpleJdbcUpdateOperations withSchenaName(String schemaName) {
		setSchemaName(schemaName);
		return this;
	}

	@Override
	public SimpleJdbcUpdateOperations withCatalogName(String catalogName) {
		setCatalogName(catalogName);
		return this;
	}

	@Override
	public SimpleJdbcUpdateOperations updatingColumns(List<String> columnNames) {
		setDeclaredUpdatingColumns(columnNames);
		return this;
	}

	@Override
	public SimpleJdbcUpdateOperations restrictedColoumns(List<String> columnNames) {
		setRestrictingColumns(columnNames);
		return this;
	}

	@Override
	public SimpleJdbcUpdateOperations restrictedColoumns(Map<String, Operator> columnNamesToOperators) {
		setRestrictingColumns(columnNamesToOperators);
		return this;
	}

	@Override
	public int execute(Map<String, Object> updatingValues, Map<String, Object> restrictingValues) {
		int id = doExecute(updatingValues, restrictingValues);
		return id;
	}

	@Override
	public int execute(SqlParameterSource updatingValues, SqlParameterSource restrictingValues) {
		int id = doExecute(updatingValues, restrictingValues);
		return id;
	}

}
