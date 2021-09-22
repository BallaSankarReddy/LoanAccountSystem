package com.loan.common;

import java.util.List;
import java.util.Map;

public interface JdbcRespository <T extends JdbcEntity> {


	public List<T> findAll(Class<T> entity);

	public T findById(Class<T> entity,Object id);

	public Integer delete(Class<T> entity,Integer id);

	int create(T entity);
	
	public int update(T entity, Map<String, Object> updatedColoumMap, Map<String, Object> restrictedColoumnsMap); 
	
	
	

}
