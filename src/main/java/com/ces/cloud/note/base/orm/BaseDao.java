/**
 * Copyright (c) 2012-2013 Guangzhou LianYi Information Technology Co,.Ltd. All rights reserved.  
 * SHINE'S PROJECT 2013-1-10
 * 
 * 相关描述： 
 * 
 */
package com.ces.cloud.note.base.orm;

import java.io.Serializable;
import java.util.List;

import com.ces.cloud.note.base.pager.Pager;

/**
 * 相关描述：
 *
 * 文件名：BaseDao.java
 * 作者： Fu Zhaohui 
 * 完成时间：2013-1-10 下午4:00:13 
 * 维护人员：Fu Zhaohui  
 * 维护时间：2013-1-10 下午4:00:13 
 * 维护原因：  
 * 当前版本： v1.0 
 *
 */
public interface BaseDao {
	
	public <T> T getEntityById(String statement, Serializable id)
			throws Exception;
	
	public <T> T getEntity(String statement) throws Exception;
	
	public <T> T getEntity(String statement, Object parameter) throws Exception;
	
	public <T> List<T> getEntityList(String statement) throws Exception;
	
	public <T> List<T> getEntityList(String statement, Object parameter) throws Exception;
	
	public int save(String statement, Object parameter) throws Exception;
	
	public int update(String statement, Object parameter) throws Exception;
	
	public int remove(String statement, Object parameter) throws Exception;
	
	public <T> Pager<T> pagedQuery(String statementName, Object values,
			int startRecord, int pageSize) throws Exception;
	
	public <T> Pager<T> pagedQueryLimit(String statementName, String statementNameCount, Object values,
			int startRecord, int pageSize) throws Exception;
	
	

}
