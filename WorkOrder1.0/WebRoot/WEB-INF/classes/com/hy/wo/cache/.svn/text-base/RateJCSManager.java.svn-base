package com.hy.wo.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.jcs.admin.CacheElementInfo;
import org.apache.jcs.admin.JCSAdminBean;
import org.apache.log4j.Logger;

import com.hy.wo.util.InnerGameLimit;
import com.hy.wo.util.constants.Evaluations;

/**
 * 游戏内防刷缓存管理
 * @author dong_jin
 *
 */
public class RateJCSManager {
	
	public static final Logger 		LOGGER = Logger.getLogger(RateJCSManager.class);
	
	private JCS rage;
	private static RateJCSManager  instance;
	
	private RateJCSManager(){
		try{
			rage = JCS.getInstance("rate");
		}catch(Exception e){
			LOGGER.error(" 初始化JCS出错！ ", e);
		}
	}
	
	public synchronized static RateJCSManager getInstance(){
		if (instance == null) {
            instance = new RateJCSManager();
        }
		return instance;
	}
	/**
	 * 存入缓存区
	 * @param key
	 * @param value
	 */
	public void put(String key,Object value){
		try {
			LOGGER.debug("Rate 存入一条数据 ");
			rage.put(key, value);
			LOGGER.debug("RateJcs.size= "+instance.size());
		} catch (CacheException e) {
			LOGGER.error(" JCS存入数据出错！ ", e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 取出数据
	 * @param key
	 * @return
	 */
	public Object get(String key){
		return (Object) rage.get(key);
	}
	/**
	 * 删除数据
	 * @param key
	 */
	public void remove(String key){
		try {
			rage.remove(key);
		} catch (CacheException e) {
			LOGGER.error(" JCS删除数据出错！ ", e);
		}
	}
	/**
	 * 当前缓存块存放对象数量
	 * @return
	 * @throws Exception
	 */
	public int size() throws Exception{
		JCSAdminBean jcsBean=new JCSAdminBean();
		List<?> list=jcsBean.buildElementInfo("rate");
		return list.size();
	}
	/**
	 * 获取全部对象
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getMap() throws Exception{
		Map<String,String> resultMap = new HashMap<String,String>();
		JCSAdminBean jcsBean=new JCSAdminBean();
		List<CacheElementInfo> list=jcsBean.buildElementInfo("rate");
		Iterator it=list.iterator();
		if(list.size()>0){
				while(it.hasNext()){
					CacheElementInfo info=(CacheElementInfo) it.next();
					resultMap.put(info.getKey(),(String) rage.get(info.getKey()));
				}
		}
		return resultMap;
	}
	/**
	 * 获取缓存对象子集
	 * @param from
	 * @param to
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getSubMap(int from,int to) throws Exception{
		Map<String,String> reMap = null;
		JCSAdminBean jcsBean=new JCSAdminBean();
		List<CacheElementInfo> list=jcsBean.buildElementInfo("rate");
		List<CacheElementInfo> subList=null;
		if(list.size()<from){
			subList=new ArrayList<CacheElementInfo>();
		}
		if(list.size()<to){
			subList=list.subList(from, list.size());
		}else{
			subList=list.subList(from, to);
		}
		if(subList.size()>0){
			reMap= new HashMap<String,String>();
			Iterator it=subList.iterator();
			while(it.hasNext()){
				CacheElementInfo info=(CacheElementInfo) it.next();
				reMap.put(info.getKey(),rage.get(info.getKey()).toString());
			}
		}
		return reMap;
	}
	/**
	 * 获取缓存对象子集
	 * @param from
	 * @param to
	 * @return
	 * @throws Exception
	 */
	public List<InnerGameLimit> getSubList(int from,int to) throws Exception{
		List<InnerGameLimit> reList=null;
		JCSAdminBean jcsBean=new JCSAdminBean();
		List<CacheElementInfo> list=jcsBean.buildElementInfo("rate");
		List<CacheElementInfo> subList=null;
		if(list.size()<from){
			subList=new ArrayList<CacheElementInfo>();
		}
		if(list.size()<to){
			subList=list.subList(from, list.size());
		}else{
			subList=list.subList(from, to);
		}
			
		if(subList.size()>0){
			reList=new ArrayList();
			Iterator it=subList.iterator();
			while(it.hasNext()){
				CacheElementInfo info=(CacheElementInfo) it.next();
				reList.add((InnerGameLimit) rage.get(info.getKey()));
			}
		}
		return reList;
	}
	/**
	 * 清理过期数据
	 * @throws Exception 
	 */
	public void clearDataOutTime() throws Exception{
		JCSAdminBean jcsBean=new JCSAdminBean();
		List<CacheElementInfo> list=jcsBean.buildElementInfo("rate");
		Iterator it=list.iterator();;
		while(it.hasNext()){
			CacheElementInfo info=(CacheElementInfo) it.next();
			if(info.getExpiresInSeconds()<=0){
				this.rage.remove(info.getKey());
				LOGGER.debug("清理过期数据");
			}
		}
	} 
	public void removeAll() throws CacheException{
		rage.clear();
	}
	public List<CacheElementInfo> getJCSBeanList() throws Exception{
		JCSAdminBean jcsBean=new JCSAdminBean();
		List<CacheElementInfo> list=jcsBean.buildElementInfo("rate");
		Iterator it=list.iterator();
		while(it.hasNext()){
			CacheElementInfo info=(CacheElementInfo) it.next();
			System.out.println("最大时间"+info.getMaxLifeSeconds());
			System.out.println("剩余时间"+info.getExpiresInSeconds());
		}
		return list;
	}
	
	public JCS getJCS(){
		return this.rage;
	}
}
