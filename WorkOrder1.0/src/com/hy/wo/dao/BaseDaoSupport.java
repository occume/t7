package com.hy.wo.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import com.hy.wo.util.GenericsUtils;
import com.hy.wo.util.Pages;
/**
 * 通用操作的基础父类,实现了BaseDao接口
 * @author dong_jin
 * @version 2011.08.08
 */
@Transactional
public class BaseDaoSupport<T> implements BaseDao<T>{
	@Resource
	protected SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	protected Class entityClass=GenericsUtils.getSuperClassGenricType(this.getClass());
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	/**
	 * 通用保存方法
	 */
	public void save(Object entity) {
		try{
			sessionFactory.getCurrentSession().persist(entity);
		}catch(HibernateException h){
			h.printStackTrace();
		}
	}
	/**
	 * 通用获取方法
	 */
	@SuppressWarnings("unchecked")
	public T get(long id) {
		Object obj=sessionFactory.getCurrentSession().get(entityClass, id);
		return (T) obj;
	}
	/**
	 * 通用更新方法
	 */
	public void update(Object entity) {
		sessionFactory.getCurrentSession().update(entity);
	}
	/**
	 * 通用删除方法
	 */
	public void delete(Object entity) {
		//System.out.println(entity);
		sessionFactory.getCurrentSession().delete(entity);
	}
	/**
	 * 通用分页方法
	 */
	public Pages<T> getPageData(int start, int len, String hql, Object[] param,
			LinkedHashMap<String, String> orderby) {
		String entityName=getEntieyName(entityClass);
		Pages<T> page=new Pages<T>();
		page.setCurrentPage(start);
		page.setPageSize(len);
		
		List<T> list=null;
		Query query=getSession().createQuery("select o from "+entityName+" o "+(hql==null?"":"where "+hql)+buildOrder(orderby));//" order by id desc");
		setParameters(query,param);
		if(start<0&&len<0){
			list=query.list();
		}else{
			query.setFirstResult(getFirstResult(start,len));
			query.setMaxResults(len);
			list=query.list();
		}
		
		query=getSession().createQuery("select count(o) from "+entityName+" o "+(hql==null?"":"where "+hql));
		setParameters(query, param);
		int total=((Long)query.uniqueResult()).intValue();
		page.setTotalCount(total);
		int totalPage=(total+len-1)/len;
		page.setTotalPage(totalPage);
		int lastPage=(totalPage==0?1:totalPage);
		page.setLastPage(lastPage);
		
		page.setResultList(list);
		return page;
	}
	/**
	 * 通用字段分页方法
	 */
	public Pages getFieldPageData(String fields,int start, int len, String hql, Object[] param,
			LinkedHashMap<String, String> orderby) {
		String entityName=getEntieyName(entityClass);
		Pages page=new Pages();
		page.setCurrentPage(start);
		page.setPageSize(len);
		
		List list=null;
		String h = fields+entityName+" o  join o.userInfo  u  "+(hql==null?"":"where "+hql)+buildOrder(orderby);
		//System.out.println("查询语句："+h);
		//System.out.println("查询参数："+Arrays.asList(param));
		//System.out.println("start="+start+";len="+len);
		Query query=getSession().createQuery(h);//" order by id desc");
		setParameters(query,param);
		if(start<0&&len<0){
			list=query.list();
		}else{
			//System.out.println("起始位置："+(getFirstResult(start,len)));
			query.setFirstResult(getFirstResult(start,len));
			query.setMaxResults(len);
			list=query.list();
		}
	//System.out.println("取到条数："+list.size());
		query=getSession().createQuery("select count(o) from "+entityName+" o "+(hql==null?"":"where "+hql));
		setParameters(query, param);
		int total=((Long)query.uniqueResult()).intValue();
		//System.out.println(total);
		page.setTotalCount(total);
		int totalPage=(total+len-1)/len;
		page.setTotalPage(totalPage);
		int lastPage=(totalPage==0?1:totalPage);
		page.setLastPage(lastPage);
		
		page.setResultList(list);
		return page;
	}
	private int getFirstResult(int index, int length) {
		int r=(index-1)*length;
		return r<=0?0:r;
	}

	private void setParameters(Query query, Object[] params) {
		if(params!=null&&params.length>0){
			for(int i=0;i<params.length;i++){
				if(params[i]!=null)
				query.setParameter(i, params[i]);
			}
		}
	}

	private String buildOrder(LinkedHashMap<String, String> orderby) {
		StringBuffer order=new StringBuffer();
		if(orderby!=null&&orderby.size()>0){
			order.append(" order by ");
			for(Entry e:orderby.entrySet()){
				order.append(e.getKey()).append(" ").append(e.getValue()).append(",");
			}
			order=order.deleteCharAt(order.length()-1);
		}
		//System.out.println(order.toString());
		return order.toString();
	}

	private String getEntieyName(Class entityClass) {
		String name=entityClass.getSimpleName();
		return name;
	}
	/**
	 * 将获取到的count值转换为int
	 * @param obj
	 * @return
	 */
	protected int convertQueryResult(Object obj){
		return obj==null?0:Integer.valueOf(obj.toString());
	}
	
}
