package com.ces.cloud.note.base.orm.impl;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.builder.xml.dynamic.ForEachSqlNode;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ces.cloud.note.base.pager.Pager;

/**
 * <p> Mybatis Dao的泛型基类<p/>
 * <p>继承于Spring<code>SqlSessionDaoSupport</code>,提供分页函数和若干便捷查询方法，并对返回值作了泛型类型转.<p/>
 * 
 * @see SqlSessionDaoSupport
 * @author hai chen
 * @version 1.0, 2012-08-01
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl extends SqlSessionDaoSupport {

	/**
	 * 根据主键id获取单个对象.
	 * 
	 * @param <T>
	 * @param statement
	 *            mapper名
	 * @param id
	 *            主键id
	 * @return T对象
	 * @throws Exception
	 */
	public <T> T getEntityById(String statement, Serializable id)
			throws Exception {
		try {
			return this.getSqlSession().selectOne(statement, id);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取单个对象.
	 * 
	 * @param <T>
	 * @param statement
	 *            mapper名
	 * @return T对象
	 * @throws Exception
	 */
	public <T> T getEntity(String statement) throws Exception {
		try {
			return this.getSqlSession().selectOne(statement);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取单个对象.
	 * 
	 * @param <T>
	 * @param statement
	 *            mapper名
	 * @param parameter
	 *            传递到statement语句的参数
	 * @return T对象
	 * @throws Exception
	 */
	public <T> T getEntity(String statement, Object parameter) throws Exception {
		try {
			return this.getSqlSession().selectOne(statement, parameter);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取对象列表
	 * 
	 * @param <T>
	 * @param statement
	 *            mapper名
	 * @return T对象列表
	 * @throws Exception
	 */
	public <T> List<T> getEntityList(String statement) throws Exception {
		try {
			return this.getSqlSession().selectList(statement);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取对象列表
	 * 
	 * @param <T>
	 * @param statement
	 *            mapper名
	 * @param parameter
	 *            传递到statement语句的参数
	 * @return T对象列表
	 * @throws Exception
	 */
	public <T> List<T> getEntityList(String statement, Object parameter)
			throws Exception {
		try {
			return this.getSqlSession().selectList(statement, parameter);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 保存对象.
	 * 
	 * @param statement
	 *            mapper名
	 * @param parameter
	 *            传递到statement语句的参数
	 * @return 影响的行数
	 * @throws Exception
	 */
	public int save(String statement, Object parameter) throws Exception {
		try {
			return getSqlSession().insert(statement, parameter);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 更新对象.
	 * 
	 * @param statement
	 *            mapper名
	 * @param parameter
	 *            传递到statement语句的参数
	 * @return 影响的行数
	 * @throws Exception
	 */
	public int update(String statement, Object parameter) throws Exception {
		try {
			return getSqlSession().update(statement, parameter);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 删除对象.
	 * 
	 * @param statement
	 *            mapper名
	 * @param parameter
	 *            传递到statement语句的参数
	 * @return 影响的行数
	 * @throws Exception
	 */
	public int remove(String statement, Object parameter) throws Exception {
		try {
			return getSqlSession().delete(statement, parameter);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 分页查询函数，根据方言拦截sql处理后查询.
	 * @param <T>
	 * 
	 * @param statementName
	 *            mapper名
	 * @param values
	 *            参数
	 * @param startRecord
	 *            开始记录索引
	 * @param pageSize
	 *            每页条目数
	 * @return 含结果记录数和当前页数据的Page对象.
	 * @throws Exception
	 */
	public <T> Pager<T> pagedQuery(String statementName, Object values,
			int startRecord, int pageSize) throws Exception {
		try {
			// 总记录数
			long totalCount = getTotalCount(statementName, values);
			if (totalCount < 1)
				return new Pager<T>(totalCount, null);
			// 当前页号
			int pageNo = Pager.validPageNo(startRecord, pageSize, totalCount);
			// 实际查询返回分页对象
			List<T> list = getSqlSession().selectList(statementName, values,
					new RowBounds(startRecord, pageSize));

			return new Pager<T>(startRecord, pageNo, pageSize, totalCount, list);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 分页查询函数，根据方言拦截sql处理后查询.
	 * @param <T>
	 * 
	 * @param statementName
	 *            mapper名
	 * @param statementNameCount
	 *            查询总量的mapper名，可以添加limit
	 * @param values
	 *            参数
	 * @param startRecord
	 *            开始记录索引
	 * @param pageSize
	 *            每页条目数
	 * @return 含结果记录数和当前页数据的Page对象.
	 * @throws Exception
	 */
	public <T> Pager<T> pagedQueryLimit(String statementName, String statementNameCount, Object values,
			int startRecord, int pageSize) throws Exception {
		try {
			// 总记录数
			long totalCount = getTotalCount(statementNameCount, values);
			if (totalCount < 1)
				return new Pager<T>(totalCount, null);
			// 当前页号
			int pageNo = Pager.validPageNo(startRecord, pageSize, totalCount);
			// 实际查询返回分页对象
			List<T> list = getSqlSession().selectList(statementName, values,
					new RowBounds(startRecord, pageSize));

			return new Pager<T>(startRecord, pageNo, pageSize, totalCount, list);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取符合条件查询的记录集条目数.
	 * 
	 * @param statementName
	 * @param values
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private long getTotalCount(String statementName, Object values) {
		Map parameterMap = toParameterMap(values);
		long count = 0l;
		try {
			MappedStatement mst = getSqlSession().getConfiguration()
					.getMappedStatement(statementName);
			BoundSql boundSql = mst.getBoundSql(parameterMap);
			String sql = " select count(*) total_count from ("
					+ boundSql.getSql() + ") as tmp_count ";
			// 此处得到的是已关闭的连接，修正如下
			PreparedStatement pstmt = getSqlSession().getConnection()
					.prepareStatement(sql);
			// SqlSessionTemplate st = (SqlSessionTemplate) getSqlSession();
			// Connection connection = SqlSessionUtils.getSqlSession(
			// st.getSqlSessionFactory(), st.getExecutorType(),
			// st.getPersistenceExceptionTranslator()).getConnection();
			// PreparedStatement pstmt = connection.prepareStatement(sql);
			// BoundSql countBS = new
			// BoundSql(mst.getConfiguration(),sql,boundSql.getParameterMappings(),parameterMap);
			setParameters(pstmt, mst, boundSql, parameterMap);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getLong("total_count");
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			count = 0l;
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return count;
	}

	/**
	 * 对SQL参数(?)设值 参考<code>DefaultParameterHandler</code>
	 * 
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	private void setParameters(PreparedStatement ps,
			MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(
				mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration
					.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null
					: configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry
							.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName
							.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value)
									.getValue(
											propertyName.substring(prop
													.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject
								.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException(
								"There was no TypeHandler found for parameter "
										+ propertyName + " of statement "
										+ mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping
							.getJdbcType());
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	protected Map toParameterMap(Object parameter) {
		if (parameter == null) {
			return new HashMap();
		}

		if (parameter instanceof Map) {
			return (Map<?, ?>) parameter;
		} else {
			try {
				return PropertyUtils.describe(parameter);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}