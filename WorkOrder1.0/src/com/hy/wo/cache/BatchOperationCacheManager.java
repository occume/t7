package com.hy.wo.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.jcs.admin.CacheElementInfo;
import org.apache.jcs.admin.JCSAdminBean;
import org.apache.log4j.Logger;

import com.hy.wo.po.BatchData;
import com.hy.wo.po.WorkOrder;


public class BatchOperationCacheManager {

	/*此类实例的对象*/
	private static BatchOperationCacheManager 	instance;	
	public static final Logger 		LOGGER = Logger.getLogger(BatchOperationCacheManager.class);	
//    public static final Logger      LOGGERCACHE = Logger.getLogger("backUpCacheLog");
	/*JCS实例*/
	private static JCS  			batchDataCache ;//批量操作缓存
	private static JCS 				batchTypeCache;//批量操作类型缓存

	/*构造器*/
	private BatchOperationCacheManager(){
		try{
			batchDataCache = JCS.getInstance("batchDataCache");
			batchTypeCache = JCS.getInstance("batchTypeCache");
		}catch(Exception e){
			LOGGER.error(" 在对JCS对象实例化时出错 ", e);
		}
	}
	
    /**
     * 初始化JCS实例
     * @return BatchOperationCacheManager实例
     */
    public static BatchOperationCacheManager getInstance() {
        synchronized (BatchOperationCacheManager.class) {
            if (instance == null) {
                instance = new BatchOperationCacheManager();
            }
        }
        return instance;
    }
    /**
     * 把批量操作数据放入到缓存中
     * @param batchDataCacheKey   batchDataCache缓存key【工单ID】
     * @param batchData           BatchData对象
     */
    public void putBatchDataToJCS(String batchDataCacheKey, BatchData batchData){
    	LOGGER.debug("**********批量添加工单数据进缓存中************");
    	LOGGER.debug("KEY = " +batchDataCacheKey);
		try{
			batchDataCache.put(batchDataCacheKey.toUpperCase(), batchData);
		}catch(CacheException ce){
			LOGGER.error("放入缓存batchDataCache 失败 batchDataCacheKey = "+batchDataCacheKey, ce);
		} 	
    }
    /**
     * 把批量操作类型缓存放入到缓存
     * @param batchTypeCacheKey   batchTypeCache缓存key
     * @param batchData           BatchData对象
     */
    public void putBatchTypeToJCS(String batchTypeCacheKey, BatchData batchData){
		try{
			LOGGER.debug("key="+batchTypeCacheKey);
			batchTypeCache.put(batchTypeCacheKey.toUpperCase(), batchData);
		}catch(CacheException ce){
			LOGGER.error("放入batchTypeCache缓存失败 : batchTypeCacheKey = " + batchTypeCacheKey, ce);
		}
    } 
 
    /**
     * 根据KEY从批量回复数据缓存中获取WorkOrder对象
     * @param batchDataCacheKey  batchDataCache缓存key
     * @return
     */
    public BatchData getBatchDataFromJCS(String batchDataCacheKey){
    	return (BatchData)batchDataCache.get(batchDataCacheKey.toUpperCase());
    }   
    /**
     * 从batchTypeCache缓存中取数据
     * @param batchTypeCacheKey		batchTypeCache缓存key
     * @return 批量操作类型数组
     */
    public BatchData getBatchTypeFromJCS(String batchTypeCacheKey){
    	return ((BatchData) batchTypeCache.get(batchTypeCacheKey.toUpperCase()));
    }
    /**
     * 根据KEY将数据从batchDataCache[批量回复缓存]缓存中删除
     * @param batchDataCacheKey	batchDataCache缓存key
     */
    public void removeBatchDataFromJCS(String batchDataCacheKey){
		try{
			LOGGER.debug("根据KEY将数据从batchDataCache[批量回复缓存]缓存中删除.KEY="+batchDataCacheKey);
			batchDataCache.remove(batchDataCacheKey.toUpperCase());
		}catch(CacheException ce){
			LOGGER.error("从缓存batchDataCache中删除数据失败 : batchDataCacheKey = "+batchDataCacheKey, ce) ;
		}    		
    }
    /**
     * 根据KEY将数据从batchTypeCache缓存中删除
     * @param batchTypeCacheKey batchTypeCache缓存的key
     */
    public void removeBatchTypeFromJCS(String batchTypeCacheKey){
        try{
        	batchTypeCache.remove(batchTypeCacheKey.toUpperCase());
        }catch(CacheException ce){
            LOGGER.error("从缓存batchTypeCache中删除数据失败 : batchTypeCacheKey = "+batchTypeCacheKey, ce) ;
        }
    }
    
//    /**
//     * 从batchTypeCache缓存中取数据
//     * @param batchTypeCacheKey	batchTypeCache缓存key
//     * @return 
//     */
//    public WorkOrder getBackWOFromJCS(String batchTypeCacheKey){
//    	return (WorkOrder)batchTypeCache.get(batchTypeCacheKey.toUpperCase());
//    }
    /**
     * 从批量数据缓存区域中取出所有的数据
     * @param cacheName 缓存区域名
     * @return 所有缓存数据
     */
    public List<BatchData> getAllCacheBatchData(String cacheName){   
    	List<BatchData> resultList = new ArrayList<BatchData>();
    	try {
    		JCSAdminBean jcsBean = new JCSAdminBean();
			 List<CacheElementInfo> cacheList = jcsBean.buildElementInfo(cacheName);
			 LOGGER.debug("从缓存中取出所有的数据");
			 Iterator it = cacheList.iterator();	
			 BatchData batchData = null;
	  		 while(it.hasNext()){
	  			 CacheElementInfo elementInfo = (CacheElementInfo)it.next();
				// LOGGER.debug("elementInfo KEY：" +elementInfo.getKey());
				 batchData = new BatchData();
				 batchData = (BatchData)org.apache.jcs.JCS.getInstance(cacheName).get(elementInfo.getKey());
				 LOGGER.debug("key="+elementInfo.getKey());
				// LOGGER.debug("accountname = "+batchData.getWorkOrder().getUserInfo().getAccountname());
				 resultList.add(batchData);
				 batchData = null;
			 }
		} catch (Exception e) {
			LOGGER.error("从缓存区域cacheName = " +cacheName + "中取全部数据异常，异常信息为.", e) ;
		}
		return resultList;
    }
//    /**
//     * 从批量操作类型缓存中获取所有数据
//     * @param cacheName 缓存区域名
//     * @return 所有缓存数据
//     */
//    public List<String> getAllBatchType(String cacheName){
//    	List<String> list = new ArrayList<String>();
//    	try {
//    		JCSAdminBean jcsBean = new JCSAdminBean();
//			 list = jcsBean.buildElementInfo(cacheName);
//		} catch (Exception e) {
//			LOGGER.error("从缓存区域cacheName = " +cacheName + "中取全部数据异常，异常信息为.", e) ;
//		}
//		return list;
//    }
}
