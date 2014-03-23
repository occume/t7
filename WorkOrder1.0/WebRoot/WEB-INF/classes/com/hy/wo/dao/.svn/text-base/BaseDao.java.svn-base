package com.hy.wo.dao;

import java.util.LinkedHashMap;
import com.hy.wo.util.Pages;

/**
 * 通用操作的接口
 * @author dong_jin
 * @version 2011.08.08 10.26.45
 */
public interface BaseDao<T> {
	/**
	 * 定义保存实体类的方法
	 * @param entity
	 */
	public void save(Object entity);
	/**
	 * 定义获取实体类的方法
	 * @param id
	 * @return
	 */
	public T get(long id);
	/**
	 * 更新方法
	 * @param entity
	 */
	public void update(Object entity);
	/**
	 * 删除 方法
	 * @param entity
	 */
	public void delete(Object entity);
	/**
	 * 获取分页信息
	 * @param firstIndex
	 * @param maxResult
	 * @return
	 */
	public Pages<T> getPageData(int start,int len,String hql,
											Object[] param,LinkedHashMap<String, String> orderby);
	/**
	 * 获取确定字段分页信息
	 * @param firstIndex
	 * @param maxResult
	 * @return
	 */
	public Pages<T> getFieldPageData(String fields,int start,int len,String hql,
											Object[] param,LinkedHashMap<String, String> orderby);
}