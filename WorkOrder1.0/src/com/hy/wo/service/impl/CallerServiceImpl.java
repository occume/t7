package com.hy.wo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hy.wo.cache.RateJCSManager;
import com.hy.wo.dao.AsistDao;
import com.hy.wo.dao.BaseDaoSupport;
import com.hy.wo.dao.GameServersDao;
import com.hy.wo.exception.WoCenterException;
import com.hy.wo.po.CallBack;
import com.hy.wo.po.ServerInfo;
import com.hy.wo.po.SubCallBack;
import com.hy.wo.po.Caller;
import com.hy.wo.po.WorkOrder;
import com.hy.wo.po.WorkOrderUserInfo;
import com.hy.wo.service.CallerService;
import com.hy.wo.util.DepictXmlManager;
import com.hy.wo.util.MyUtil;
import com.hy.wo.util.Constants.ParamName;
import com.hy.wo.util.Constants.PromptMessage;
import com.hy.wo.util.Constants.StateList;
import com.hy.wo.util.constants.Evaluations;

@Service @Transactional
public class CallerServiceImpl extends BaseDaoSupport<Caller> implements CallerService{
	/**
	 * 获取CallCenter玩家信息
	 */
	public List<Caller> getCallerInfo(String caller){
		String hql="select o.accName,o.userName,o.userType from Caller o where o.caller=? group by o.accName order by createTime desc";
		Query query=getSession().createQuery(hql).setParameter(0, caller);
		List list=query.list();
		//System.out.println(list.size());
		//System.out.println(list);
		if(list.size()<=0) return null;
		return makeCallerList(list);
	}
	private List<Caller> makeCallerList(List<Object[]> list) {
		LOG.debug("make caller");
		List<Caller> cl=null;
		try{
		cl=new ArrayList<Caller>();
		for(Object[] o:list){
			cl.add(new Caller(o));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return cl;
	}
	/**
	 * 记录CallCenter玩家信息
	 */
	public void saveCallerInfo(Caller c){
		getSession().saveOrUpdate(c);
	}
	/**
	 * 导入回访清单
	 */
	public long saveCallBack(HttpServletRequest request){
		CallBack cb=new CallBack(request);
		
		String[] callBacks=/**new String[]{"mac\tcac"};*/MyUtil.createCbArray(cb.getContent());
		for(int i=0;i<callBacks.length;i++){
			SubCallBack scb=new SubCallBack(callBacks[i]);
			scb.setCallBack(cb);
			cb.getCallBacks().add(scb);
		}
		save(cb);
		return cb.getId();
	}
	/**
	 * 获取回访清单
	 */
	public List getCallBacksByDate(Date from,Date to){
	//	System.out.println(from);
	//	System.out.println(to);
		Query q=null;
		String hql="select o.id,o.title,o.attime,o.operator from CallBack o ";
		if(from==null){
			if(to!=null){
				hql+=" where o.attime < ?  order by attime";
				q=getSession().createQuery(hql).setParameter(0, to);
			}else{
				hql+="  order by attime";
				q=getSession().createQuery(hql);
			}
		}else{
			if(to!=null){
				hql+=" where o.attime >? and o.attime < ? order by attime";
				if(from.after(to)){
					Date temp=from;
					from=to;
					to=temp;
				}
				q=getSession().createQuery(hql).setParameter(0, from).setParameter(1, to);
			}else{
				hql+=" where o.attime >? order by attime";
				q=getSession().createQuery(hql).setParameter(0, from);
			}
		}
		List list=q.list();
		return list;
	}
	/**
	 * 获取服务器信息
	 * @throws WoCenterException 
	 */
	public Map<String, String> makeServerMap() throws WoCenterException {
		Map<String,String> map=null;
		List<ServerInfo> list=AsistDao.getGameServers();
		if(list!=null&&list.size()>0){
			map=new HashMap<String, String>();
			for(ServerInfo server:list){
				map.put(String.valueOf(server.getServerId()), server.getServerName());
			}
		}
		return map;
	}
	public String makeUserInfo(HttpServletRequest request) throws NumberFormatException, WoCenterException{
		//LOG.debug("A Request From CallCenter  | makeUserInfo");
		String result="";
		String serverId=request.getParameter( ParamName.SERVER_ID );
		//LOG.debug("A Request From CallCenter  | makeUserInfo |serverId="+serverId);
		NameValuePair nv=AsistDao.getGameServerUrl(Integer.valueOf(serverId));
		String dns=nv.getName();
		//LOG.debug("A Request From CallCenter  | makeUserInfo |dns="+dns);
		String DNS=dns.substring(0, dns.lastIndexOf("/"))+"/fy_manager";
		String USER="selectuser";//MyUtil.getDefaultServerUser();
		String PASS="g7A&hN";//MyUtil.getDefaultServerPass();
		//LOG.debug("A Request From CallCenter  | makeUserInfo |DNS="+DNS);
		//LOG.debug("A Request From CallCenter  | makeUserInfo |User="+USER);
		//LOG.debug("A Request From CallCenter  | makeUserInfo |Pass="+PASS);
		String account=request.getParameter( ParamName.ACCOUNT );
		List<WorkOrderUserInfo> list=GameServersDao.getInstance().doQuery(DNS, USER, PASS, account);
		//LOG.debug("A Request From CallCenter  | makeUserInfo |size="+list.size());
		nv.setName(serverId);
		result=DepictXmlManager.makeWorkOrderUserInfoXml(list,nv);
		return result;
	}
	public String getUserName(String serverId, String roleId) throws NumberFormatException, WoCenterException{
		//LOG.debug("A Request From CallCenter  | makeUserInfo");
		String result="";
	//	String serverId=request.getParameter( ParamName.SERVER_ID );
		//LOG.debug("A Request From CallCenter  | makeUserInfo |serverId="+serverId);
		NameValuePair nv=AsistDao.getGameServerUrl(Integer.valueOf(serverId));
		String dns=nv.getName();
		//LOG.debug("A Request From CallCenter  | makeUserInfo |dns="+dns);
		String DNS=dns.substring(0, dns.lastIndexOf("/"))+"/fy_manager";
		String USER="selectuser";//MyUtil.getDefaultServerUser();
		String PASS="g7A&hN";//MyUtil.getDefaultServerPass();
		//LOG.debug("A Request From CallCenter  | makeUserInfo |DNS="+DNS);
		//LOG.debug("A Request From CallCenter  | makeUserInfo |User="+USER);
		//LOG.debug("A Request From CallCenter  | makeUserInfo |Pass="+PASS);
		//String account=request.getParameter( ParamName.ACCOUNT );
		result = GameServersDao.getInstance().doUserNameQuery(DNS, USER, PASS, roleId);
		
		return result;
	}
	/**
	 * 用户评价
	 * @throws WoCenterException 
	 */
	public void doRate(String cid,String ev) throws WoCenterException{
		LOG.debug("do rate");
		String callid=cid.trim();
		String eva=ev.trim();
		Evaluations e=Evaluations.getEvaByName(eva);
		//LOG.debug("e="+e.getName());
		LOG.debug("callid="+callid);
		String hql="select o.id from WorkOrder o where o.callid=?";
		Query q=null;
		try{
		q=getSession().createQuery(hql);
		q.setParameter(0, callid);
		List<Object> list=q.list();
		if(list.size()>0){
			Object a=(Object)list.get(0);
			long id=Long.valueOf(a.toString());
			//System.out.println(id);
			WorkOrder wo=(WorkOrder) getSession().get(WorkOrder.class, id);
			//System.out.println(wo.getStates());
			wo.setComment("用户已评价");
			wo.setEvaluation(e);
			RateJCSManager.getInstance().remove(callid);
		}else{
			//如果评价出错 ，放入缓存
			RateJCSManager.getInstance().put(callid, eva);
			//throw new WoCenterException(StateList.DATA_NOT_FOUND,PromptMessage.DATA_NOT_FOUND);
		}
		
		LOG.debug("list.size="+q.list().size());
		}catch(HibernateException e1){
			RateJCSManager.getInstance().put(callid, eva);
			e1.printStackTrace();
		}
		
	}
	/**
	 * 查询用户是否为Vip
	 * @throws WoCenterException 
	 */
	public void isVip(String caller) throws WoCenterException{
		
		String _caller=caller.trim();
		String hql = "select o.id from Caller o where o.caller=? and o.userType != '' and o.userType !='null'";
		
		Query q = getSession().createQuery(hql);
		q.setParameter(0, _caller);
		
		if(q.list() == null || q.list().size() == 0){
			throw new WoCenterException(StateList.DATA_NOT_FOUND,PromptMessage.DATA_NOT_FOUND);
		}
	}
	
	private static final Logger LOG = Logger.getLogger(CallerServiceImpl.class);//定义日志
}
