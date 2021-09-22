package com.loan.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public abstract class BaseRepository<T extends JdbcEntity> extends NamedParameterJdbcDaoSupport
		implements JdbcRespository<T> {
	

	private static final String SELECT_BY_ID="SELECT * FROM %s WHERE %s = :%s";
	private static final String SELECT_ALL_QUERY="SELECT * FROM %s ";
	
	private static final String DELETE_BY_ID="DELETE FROM %s WHERE %s = :%s ";
	

	@Autowired
	DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	

	@Override
	public int create(T entity) {

		String idColumn = null;
		try {

			idColumn = entity.getEntityIdColumn();
			SqlParameterSource parameter = new BeanPropertySqlParameterSource(entity);
			String table = entity.getTableName();
			SimpleJdbcInsert genericJdbcInsert = new SimpleJdbcInsert(getDataSource()).withTableName(table);
			if (idColumn != null) {
				return genericJdbcInsert.usingGeneratedKeyColumns(idColumn).executeAndReturnKey(parameter).intValue();
			} else {
				int execute = genericJdbcInsert.execute(parameter);
				return execute;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return 0;
	}

	public List<T> findAll(Class<T> entity) {

		try {
			String query = String.format(SELECT_ALL_QUERY, entity.newInstance().getTableName());
			System.out.println(query);
			return getNamedParameterJdbcTemplate().query(query, new BeanPropertyRowMapper<T>(entity));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}
	@Override
	public T findById(Class<T> entity,Object id) {
		try {
			String columnId = entity.newInstance().getEntityIdColumn();
			String query = String.format(SELECT_BY_ID, entity.newInstance().getTableName(),columnId,columnId);
			MapSqlParameterSource source = new MapSqlParameterSource();
			
			source.addValue(columnId, id);
			System.out.println(query);
			return getNamedParameterJdbcTemplate().queryForObject(query, source,new BeanPropertyRowMapper<T>(entity));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Integer delete(Class<T> entity,Integer id) {
		
		try {
			
			String IdColumn = entity.newInstance().getEntityIdColumn();
			String tableName = entity.newInstance().getTableName();
			
			String query=String.format(DELETE_BY_ID, tableName,IdColumn,IdColumn);
			MapSqlParameterSource parameter = new MapSqlParameterSource(IdColumn, id);
			return getNamedParameterJdbcTemplate().update(query, parameter);
		}catch (Exception e) {

		System.out.println(e.getMessage());
		}
		
	
		return null;
	}
	@Override
	public int update(T entity, Map<String, Object> updatedColoumMap, Map<String, Object> restrictedColoumnsMap) {
		try {

			List<String> updatedColoums = new ArrayList<String>();
			List<String> restrictedColoumns = new ArrayList<String>();
			
			updatedColoumMap.entrySet().forEach(key ->{
				updatedColoums.add(key.getKey());
			});
			
			restrictedColoumnsMap.entrySet().forEach(key ->{
				restrictedColoumns.add(key.getKey());
			});
			
			
			String idColoumn= entity.getEntityIdColumn();
			String tableName = entity.getTableName();
			//SimpleJdbcInsert simpleJdbcUpdate = new SimpleJdbcInsert(getDataSource()).withTableName(tableName) ;
			SimpleJdbcUpdateOperations simpleJdbcUpdate = new SimpleJdbcUpdate(dataSource).withTableName(tableName).updatingColumns(updatedColoums)
					.restrictedColoumns(restrictedColoumns);
			int updatedId = simpleJdbcUpdate.execute(updatedColoumMap, restrictedColoumnsMap);
			return updatedId;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return 0;
	}

}