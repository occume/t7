package com.hy.wo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hy.wo.dao.BaseDao;
import com.hy.wo.exception.WoCenterException;
import com.hy.wo.po.Caller;

public interface CallerService extends BaseDao<Caller> {
	public List<Caller> getCallerInfo(String caller);
	public void saveCallerInfo(Caller c);
	public long saveCallBack(HttpServletRequest r);
	public List getCallBacksByDate(Date from,Date to);
	public Map<String,String> makeServerMap()throws WoCenterException;
	public String makeUserInfo(HttpServletRequest request)throws NumberFormatException, WoCenterException;
	public void doRate(String callid,String eva) throws WoCenterException;
	public void isVip(String cid) throws WoCenterException;
	public String getUserName(String serverId, String account) throws NumberFormatException, WoCenterException;
}
