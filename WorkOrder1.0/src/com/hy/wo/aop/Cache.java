package com.hy.wo.aop;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

import com.hy.wo.cache.BatchOperationCacheManager;

public class Cache {

	/**
	 * @param args
	 * @throws CacheException 
	 */
	public static void main(String[] args) throws CacheException {
	//	BatchOperationCacheManager jcs=BatchOperationCacheManager.getInstance();
		JCS jcs=JCS.getInstance("batchDataCache");
		
		//System.out.println(jcs.);
	}

}
