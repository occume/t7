package com.hy.wo.service;

import com.hy.wo.dao.BaseDao;
import com.hy.wo.po.SubCallBack;
import com.hy.wo.util.Pages;

public interface CallBackService extends BaseDao<SubCallBack> { 
	public Pages<SubCallBack> getSubCallBackList(SubCallBack scb,String cbIds,int currentPage,int len);
	public SubCallBack get(int id);
	public SubCallBack doCallBack(SubCallBack scb);
}
