package com.ces.cloud.note.base.orm;

import java.io.Serializable;
import java.util.List;

import com.ces.cloud.note.base.pager.Pager;

public interface BaseDao {

	public <T> T getEntityById(String statement, Serializable id);

	public <T> T getEntity(String statement);

	public <T> T getEntity(String statement, Object parameter);

	public <T> List<T> getEntityList(String statement);

	public <T> List<T> getEntityList(String statement, Object parameter);

	public int save(String statement, Object parameter);

	public int update(String statement, Object parameter);

	public int remove(String statement, Object parameter);

	public <T> Pager<T> pagedQuery(String statementName, Object values,
			int startRecord, int pageSize);

	public <T> Pager<T> pagedQueryLimit(String statementName,
			String statementNameCount, Object values, int startRecord,
			int pageSize);

}
