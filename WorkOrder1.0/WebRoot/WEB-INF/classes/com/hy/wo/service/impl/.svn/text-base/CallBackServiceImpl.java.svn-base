package com.hy.wo.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hy.wo.dao.BaseDaoSupport;
import com.hy.wo.po.SubCallBack;
import com.hy.wo.service.CallBackService;
import com.hy.wo.util.MyUtil;
import com.hy.wo.util.Pages;

@Service @Transactional
public class CallBackServiceImpl extends BaseDaoSupport<SubCallBack> implements CallBackService{
	
	private static final Logger LOG = Logger.getLogger(CallBackServiceImpl.class);//定义日志

	public Pages<SubCallBack> getSubCallBackList(SubCallBack scb,String cbIds,
			int currentPage, int len) {
		LOG.debug("Enter into getSubCallBackList");
		String hql="";
		
		Object[] param=null;
		
		if(scb.getState()>=0){
			if(!MyUtil.isBlankOrNull(scb.getOperator())){
				hql+="  o.state = ? and o.operator = ? and";
				param=new Object[]{scb.getState(),scb.getOperator()};
			}else{
				hql+=" o.state = ? and";
				param=new Object[]{scb.getState()};
			}
		}else{
			if(!MyUtil.isBlankOrNull(scb.getOperator())){
				hql+=" o.operator = ? and ";
				param=new Object[]{scb.getOperator()};
			}else{
				param=new Object[]{};
			}
		}
		
		hql+="(";
		for(String str:cbIds.split(",")){
			//System.out.println(str);
			hql+=" o.callBack.id=? or";
			param=MyUtil.arrayGrow(param, Integer.valueOf(str.trim()));
		}
		
		hql=hql.substring(0,hql.lastIndexOf("or"));
		hql+=")";
		
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
	//	orderby.put("callbacktime", "desc");
		Pages<SubCallBack> pages=null;
		try{
			pages=getPageData(currentPage, len, hql, param, orderby);
		}catch(HibernateException e){
			e.printStackTrace();
		}
		//LOG.debug("size="+pages.getResultList().size());
		return pages;
	}
	
	public SubCallBack get(int id){
		return (SubCallBack) getSession().get(SubCallBack.class, id);
	}
	/**
	 * 回访
	 */
	public SubCallBack doCallBack(SubCallBack scb){
	
		SubCallBack scb1=get(scb.getId());
		scb1.setContent(scb.getContent());
		scb1.setState(scb.getState());
		if(!MyUtil.isBlankOrNull(scb.getAccount()))
			scb1.setAccount(scb.getAccount());
		scb1.setCallbacktime(new Date());
		if(!MyUtil.isBlankOrNull(scb.getCaller()))
			scb1.setCaller(scb.getCaller());
		scb1.setOperator(scb.getOperator());
		scb1.setUsername(scb.getUsername());
		
		return scb1;
	}
}
