package com.hy.wo.service.impl;

import static com.hy.wo.util.Constants.Source.*;
import static com.hy.wo.util.Constants.Games.*;
import static com.hy.wo.util.Constants.States.*;
import static com.hy.wo.util.Constants.Urgency.*;
import static com.hy.wo.util.Constants.ClassCategory.*;
import static com.hy.wo.util.Constants.OperTypeId.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hy.wo.dao.BaseDaoSupport;
import com.hy.wo.exception.WoCenterException;
import com.hy.wo.po.Advisory;
import com.hy.wo.po.Areas;
import com.hy.wo.po.Assign;
import com.hy.wo.po.ClassCategory;
import com.hy.wo.po.FileUpLoad;
import com.hy.wo.po.GameGoods;
import com.hy.wo.po.Games;
import com.hy.wo.po.Group;
import com.hy.wo.po.Issue;
import com.hy.wo.po.IssueAdditional;
import com.hy.wo.po.Member;
import com.hy.wo.po.OperType;
import com.hy.wo.po.RechargeType;
import com.hy.wo.po.ReportKinds;
import com.hy.wo.po.Servers;
import com.hy.wo.po.Staff;
import com.hy.wo.po.Urgency;
import com.hy.wo.po.WorkOrder;
import com.hy.wo.po.WorkOrderOper;
import com.hy.wo.po.WorkOrderSource;
import com.hy.wo.po.WorkOrderUserInfo;
import com.hy.wo.service.WorkOrderService;
import com.hy.wo.util.AreaJson;
import com.hy.wo.util.Constants;
import com.hy.wo.util.CountResult;
import com.hy.wo.util.DepictXmlManager;
import com.hy.wo.util.FtpClientUtil;
import com.hy.wo.util.MyUtil;
import com.hy.wo.util.Pages;
import com.hy.wo.util.Constants.OperTypeId;
import com.hy.wo.util.Constants.PromptMessage;
import com.hy.wo.util.Constants.Source;
import com.hy.wo.util.Constants.StateList;
import com.hy.wo.util.Constants.States;
import com.you9.base.Globe;
import com.you9.base.util.StringUtil;

@Service @Transactional 
public class WorkOrderServiceImpl extends BaseDaoSupport<WorkOrder> implements WorkOrderService{
	/**
	 * 初始化服务器名称Map
	 * @return
	 */
	public Map<Integer, String>  initServerMap(){
		//LOGGER.debug("获取初始化服务器信息");
		Map<Integer, String> map=new HashMap<Integer, String>();
		String hql="from Servers o where o.useId=1000";
		List<Servers> list=getSession().createQuery(hql).list();
		for(int i=1;i<=list.size();i++){
			map.put(list.get(i-1).getId(), list.get(i-1).getName());
		}
		return map;
	}
	
	
	
	/**
	 * 获取玩家提交的所有问题按状态
	 * @param start
	 * @param len
	 * @param accountname
	 * @return
	 */
	public Pages<WorkOrder> getAllWorkOrderOfUserByStatus(int start,int len,String accountname,String status){
		String fields="select o.id,o.createTime,o.isreaded,u.memo,u.lvlOne,u.username  from  ";
		String hql="o.userInfo.accountname=? and ";//已处理和处理中都归为处理中
		Object[] param=null;
		if(status.equals(DEALING)){
			hql+="(o.states=? or o.states=? ) and";
			param=new Object[]{accountname,DEALED,DEALING};
		}else if(status.equals(DEALED)){//所有工单
			hql+="";
			param=new Object[]{accountname};
		}
		else{//其他状态
			hql+="o.states=? and";
			param=new Object[]{accountname,status};
		}
		hql+="  o.isdelete=false";
		
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("o.isreaded", "asc");
		orderby.put("o.createTime", "desc");
		
		List<WorkOrder> wl=new ArrayList<WorkOrder>();
		Pages pages=null;
		try{
			pages=getFieldPageData(fields,start, len, hql, param, orderby);
		}catch(Exception e){
			e.printStackTrace();
		}
		List li=pages.getResultList();
		//System.out.println("查询结果："+li.size());
		Iterator it=li.iterator();
		while(it.hasNext()){
			WorkOrder wo=new WorkOrder();
			WorkOrderUserInfo userInfo=new WorkOrderUserInfo();
			Object[] obj=(Object[])it.next();
			long id=(Long)obj[0];
			Date date=(Date)obj[1];
			boolean isreaded=Boolean.parseBoolean(obj[2].toString());
			String memo=obj[3]==null?"":obj[3].toString();

			Issue one=(Issue)obj[4];
			wo.setId(id);
			wo.setCreateTime(date);
			wo.setIsreaded(isreaded);
			userInfo.setMemo(memo);
			
			userInfo.setLvlOne(one);
			userInfo.setAccountname(accountname);
			userInfo.setUsername(obj[5]==null?"":obj[5].toString());
			wo.setUserInfo(userInfo);
			wl.add(wo);
			wo = null;///////////////////////
		}
		pages.setResultList(wl);
		wl = null;//////////////////////////
		return pages;
	}
	/**
	 * 获取玩家提交的所有问题按状态
	 * @param start
	 * @param len
	 * @param accountname
	 * @return
	 */
//	public Pages<WorkOrder> getAllWorkOrderOfUserByStatus(int start,int len,String accountname,String status){
//
//		String hql="o.userInfo.accountname=? and ";
//		Object[] param=null;
//		if(status.equals(DEALING)){
//			hql+="(o.states=? or o.states=? ) and";
//			param=new Object[]{accountname,DEALED,DEALING};
//		}else if(status.equals(DEALED)){
//			hql+="";
//			param=new Object[]{accountname};
//		}
//		else{
//			hql+="o.states=? and";
//			param=new Object[]{accountname,status};
//		}
//		hql+=" o.isdelete=false";
//		
//		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
//		orderby.put("o.isreaded", "asc");
//		orderby.put("o.createTime", "desc");
//		
//		
//		Pages pages=null;
//		pages=getPageData(start, len, hql, param, orderby);
////		pages=getPageData(-1, -1, null, null, orderby);
////		List<WorkOrder> list=pages.getResultList();
////		for(WorkOrder wo:list){
////			System.out.print(wo.getUserInfo().getMemo());
////			System.out.println("--------"+wo.getCreateTime());
////		}
//		return pages;
//	}
	/**
	 * 玩家不同状态工单数量
	 */
	public int getAllWorkOrderCountOfUserByStatus(String accountname, String status) {
		
		String hql="select count(o) from WorkOrder o where o.userInfo.accountname=? and ";//已处理和处理中都归为处理中
		Query query=null;
		int sum=0;
		if(status.equals(DEALING)){
			hql+="(o.states=? or o.states=? ) and o.isdelete=false";
			query=getSession().createQuery(hql).setParameter(0, accountname)
													.setParameter(1, DEALED).setParameter(2, DEALING);
		}else if(status.equals(DEALED)){//所有工单
			hql+=" o.isdelete=false";
			query=getSession().createQuery(hql).setParameter(0, accountname);
		}
		else{//其他状态
			hql+="o.states=? and o.isdelete=false";
			query=getSession().createQuery(hql).setParameter(0, accountname).setParameter(1, status);
		}
		sum=convertQueryResult(query.uniqueResult());
		return sum;
	}
	
	/**
	 * 获取问题详细信息
	 */
	public WorkOrder getWorkOrderDetail(long id) {
		WorkOrder wo=get(id);
		if(wo!=null){
			if(wo.getStates().equals(RESPONSED)){
				wo.setIsreaded(true);
			}
		}else{
			return null;
		}
		return wo;
	}
	
	/**
	 * 处理角色恢复问题的方法
	 * @throws WoCenterException 
	 */
	public void recoveryHandle(WorkOrder wo,String[] uploadFileName) throws WoCenterException {
		try{
				Issue issue=(Issue) getSession().get(Issue.class, Constants.ClassCategory.RECOVERY);
				wo.getUserInfo().setLvlOne(issue);
				wo.setUrgency(GENARA);
				commonHandle(wo,uploadFileName);
				save(wo);
		}catch(HibernateException e){
			throw new WoCenterException(StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
	}
	}
	/**
	 * 处理游戏运行问题
	 * @throws WoCenterException 
	 */
	public void gameRunHandle(WorkOrder wo,String[] uploadFileName) throws WoCenterException{
		try{
			LOGGER.debug("******wo center game RUN=");
				commonHandle(wo,uploadFileName);
				Issue issue1=(Issue) getSession().get(Issue.class, Constants.ClassCategory.GAMERUNNING);
				Issue issue2=(Issue) getSession().get(Issue.class,wo.getUserInfo().getLvlTwo().getId());
				wo.getUserInfo().setLvlTwo(issue2);
				wo.getUserInfo().setLvlOne(issue1);
				wo.getDevice().setWorkOrder(wo);
				wo.getAdditional().setWorkOrder(wo);
				wo.setUrgency(URGENT);
				save(wo);
				//LOGGER.debug("******wo center gaame RUN= OVER");
		}catch(Exception e){
			e.printStackTrace();
				throw new WoCenterException(StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
		}
	};
	/**
	 * 处理角色异常问题
	 * @throws WoCenterException 
	 */
	public void abnormalHandle(WorkOrder wo,String[] uploadFileName) throws WoCenterException{
		try{
				commonHandle(wo,uploadFileName);
				Issue issue=(Issue) getSession().get(Issue.class, Constants.ClassCategory.ABNORMAL);
				wo.getUserInfo().setLvlOne(issue);
				wo.setUrgency(GENARA);
				save(wo);
		}catch(HibernateException e){
			throw new WoCenterException(StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
	}
	};
	/**
	 * 处理物品丢失问题
	 * @throws WoCenterException 
	 */
	public void goodsLostHandle(WorkOrder wo,String[] uploadFileName) throws WoCenterException{
		try{
				Issue issue=(Issue)getSession().get(Issue.class, Constants.ClassCategory.GOODSLOST);
				wo.getUserInfo().setLvlOne(issue);
				wo.setUrgency(URGENT);
				commonHandle(wo,uploadFileName);
				//LOGGER.debug("********common 处理完毕******");
				wo.getAdditional().setWorkOrder(wo);
				save(wo);
		}catch(HibernateException e){
			throw new WoCenterException(StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
	}
	}
	/**
	 * 处理服务器故障问题
	 * @throws WoCenterException 
	 */
	public void serverDefaultHandle(WorkOrder wo,String[] uploadFileName) throws WoCenterException{
		try{
		Issue issue=(Issue) sessionFactory.getCurrentSession().get(Issue.class, Constants.ClassCategory.SERVERDEFAULT);
		wo.getUserInfo().setLvlOne(issue);
		wo.setUrgency(EXTRAURGENT);
		commonHandle(wo,uploadFileName);
		save(wo);
		}catch(HibernateException e){
			throw new WoCenterException(StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
		}
	};
	/**
	 * 处理游戏bug问题
	 * @throws WoCenterException 
	 */
	public void gameBugHandle(WorkOrder wo,String[] uploadFileName) throws WoCenterException{
		try{
				Issue issue=(Issue) sessionFactory.getCurrentSession().get(Issue.class, Constants.ClassCategory.GAMEBUG);
				wo.getUserInfo().setLvlOne(issue);
				String source=wo.getSource();
				Games game=(Games) getSession().get(Games.class, FENGYUN);//设置游戏名称
				wo.getUserInfo().setGame(game);
				wo.getUserInfo().setWorkOrder(wo);
				wo.setCreateTime(new Date());//增加提问时间
				wo.setGetState(false);//拉单状态
				wo.setIsdelete(false);
				wo.setStates(UNDEAL);
				if(wo.getAdditional()!=null)
					wo.getAdditional().setWorkOrder(wo);
				commonHandle(wo,uploadFileName);
				if(!StringUtil.isBlankOrNull(source)){
					wo.setSource(source);
				}else{
					wo.setSource(PINGTAI);//工单来源
				}
				wo.setUrgency(URGENT);
				save(wo);
		}catch(HibernateException e){
			throw new WoCenterException(StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
		}
	}
	/**
	 * 处理游戏内bug问题
	 */
	public WorkOrder innerGameBugHandle(WorkOrder wo){
		Issue issue=(Issue) getSession().get(Issue.class, Constants.ClassCategory.GAMEBUG);
		wo.getUserInfo().setLvlOne(issue);
		
		String source=wo.getSource();
		
		Games game=(Games) getSession().get(Games.class, FENGYUN);//设置游戏名称
		wo.getUserInfo().setGame(game);
	
		wo.getUserInfo().setWorkOrder(wo);
		//wo.setCreateWorker(wo.getUserInfo().getAccountname());//MODIFTY @ 2011.09.29 
		wo.setCreateTime(new Date());//增加提问时间
		wo.setGetState(false);//拉单状态
		wo.setIsdelete(false);
		wo.setIsreaded(false);
	
		wo.setStates(UNDEAL);
		if(wo.getAdditional()!=null)
			wo.getAdditional().setWorkOrder(wo);

		if(source.equals("2")){
			wo.setSource(Source.INNERGAME);
		}
		wo.setUrgency(URGENT);
		save(wo);
		return wo;
	}
	/**
	 * 处理违规举报问题
	 * @throws WoCenterException 
	 */
	public void illegalReportHandle(WorkOrder wo,String[] uploadFileName) throws WoCenterException{
		try{
				Issue issue1=(Issue) getSession().get(Issue.class, Constants.ClassCategory.REPORT);
				wo.getUserInfo().setLvlOne(issue1);
				int reportId=wo.getUserInfo().getLvlTwo().getId();
				Issue issue2=(Issue) getSession().get(Issue.class,reportId);
				wo.getUserInfo().setLvlTwo(issue2);
				wo.getAdditional().setWorkOrder(wo);//remember to relate with WorkOrder
				wo.setUrgency(EXTRAURGENT);
				commonHandle(wo,uploadFileName);
				save(wo);
		}catch(HibernateException e){
					throw new WoCenterException(StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
		}
	}
	/**
	 * 处理充值问题
	 * @param wo
	 * @throws WoCenterException 
	 */
	public void rechargeHandle(WorkOrder wo,String[] uploadFileName) throws WoCenterException {
		try{
		Issue issue=(Issue) getSession().get(Issue.class, Constants.ClassCategory.RECHARGE);
		wo.getUserInfo().setLvlOne(issue);
//		int rechargeId=wo.getRecharge().getRechargeType().getId();
//		RechargeType rechargeType=(RechargeType) getSession().get(RechargeType.class, rechargeId);
		/////////////////////////////////
		//System.out.println(wo.getRecharge().getRechargeType());
		/////////////////////////////////
		//wo.getRecharge().setRechargeType(rechargeType);
		wo.getRecharge().setWorkOrder(wo);
		
		commonHandle(wo,uploadFileName);
		//wo.getUserInfo().setWorkOrder(wo);
		wo.setUrgency(EXTRAURGENT);
		save(wo);
	}catch(HibernateException e){
		throw new WoCenterException(StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
	}
	}
	/**
	 * 处理其他问题
	 * @param wo
	 * @throws WoCenterException 
	 */
	public void othersHandle(WorkOrder wo,String[] uploadFileName) throws WoCenterException{
		try{
				Issue issue=(Issue) sessionFactory.getCurrentSession().get(Issue.class, Constants.ClassCategory.OTHERS);
				wo.getUserInfo().setLvlOne(issue);
				commonHandle(wo,uploadFileName);
				wo.getUserInfo().setWorkOrder(wo);
				wo.setUrgency(GENARA);
				save(wo);
		}catch(HibernateException e){
			throw new WoCenterException(StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
		}
	}
	/******意见建议处理
	 * @throws WoCenterException ******/
	public void suggestionHandle(WorkOrder wo) throws WoCenterException{
		try{
		Games game=(Games) getSession().get(Games.class, FENGYUN);//设置游戏名称
		Issue one=(Issue) getSession().get(Issue.class, SUGGESTION);
		wo.getUserInfo().setGame(game);
		wo.getUserInfo().setLvlOne(one);//问题类型
		wo.getUserInfo().setWorkOrder(wo);
		wo.setCreateTime(new Date());
		wo.setUrgency(GENARA);
		wo.setSource(PINGTAI);
		String state = wo.getStates();
		wo.setStates(MyUtil.isBlankOrNull(state) ? UNDEAL : state);
		//wo.setGetState(false);//拉单状态
		wo.setIsdelete(false);
		wo.setIsreaded(false);
		
		save(wo);
		}catch(HibernateException e){
			throw new WoCenterException(StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
		}
	}
	/******vip投诉
	 * @throws WoCenterException ******/
	public void vipCompHandle(WorkOrder wo) throws WoCenterException{
		try{
		Games game=(Games) getSession().get(Games.class, FENGYUN);//设置游戏名称
		Issue one=(Issue) getSession().get(Issue.class, VIP_COMPLAINT);
		wo.getUserInfo().setGame(game);
		wo.getUserInfo().setLvlOne(one);//问题类型
		wo.getUserInfo().setWorkOrder(wo);
		wo.setCreateTime(new Date());
		wo.setUrgency(EXTRAURGENT);
		wo.setSource(PINGTAI);
		String state = wo.getStates();
		wo.setStates(MyUtil.isBlankOrNull(state) ? UNDEAL : state);
		//wo.setGetState(false);//拉单状态
		wo.setIsdelete(false);
		wo.setIsreaded(false);
		
		save(wo);
		}catch(HibernateException e){
			throw new WoCenterException(StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
		}
	}
	/******咨询问题处理
	 * @throws WoCenterException ******/
	public void advisoryHandle(WorkOrder wo) throws WoCenterException{
		try{
		Issue issue1=(Issue) getSession().get(Issue.class, ADVISORY);
		wo.getUserInfo().setLvlOne(issue1);
		Issue issue2=(Issue) getSession().get(Issue.class, wo.getUserInfo().getLvlTwo().getId());
		wo.getUserInfo().setLvlTwo(issue2);
		Games game=(Games) getSession().get(Games.class, FENGYUN);//设置游戏名称
		wo.getUserInfo().setGame(game);
		
		wo.getUserInfo().setWorkOrder(wo);
		wo.setCreateTime(new Date());
		wo.setUrgency(GENARA);
		wo.setSource(PINGTAI);
		String state = wo.getStates();
		wo.setStates(MyUtil.isBlankOrNull(state) ? UNDEAL : state);
		//wo.setGetState(false);//拉单状态
		wo.setIsdelete(false);
		wo.setIsreaded(false);
		
		save(wo);
		}catch(HibernateException e){
			throw new WoCenterException(StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
		}
	}
	/**
	 * 账号解封问题
	 * @param wo
	 * @return
	 */
	public void accountnamelockHandle(WorkOrder wo,String[] uploadFileName){
//		Issue issue=(Issue) getSession().get(Issue.class, Constants.ClassCategory.ACCOUNTNAME_LOCK);
//		wo.getUserInfo().setLvlTwo(issue);
		
		accountHandle(wo,uploadFileName);
		wo.setUrgency(URGENT);
		save(wo);
		
	}
	/**
	 * vip祝福申请
	 * @param wo
	 * @return
	 */
	public void vipBlessHandle(WorkOrder wo,String[] uploadFileName){
//		Issue issue=(Issue) getSession().get(Issue.class, Constants.ClassCategory.ACCOUNTNAME_LOCK);
//		wo.getUserInfo().setLvlTwo(issue);
		
		blessHandle(wo);
		wo.setUrgency(URGENT);
		save(wo);
		
	}
	/**
	 * 
	 * @param wo
	 */
	private void blessHandle(WorkOrder wo){
		if(MyUtil.isBlankOrNull(wo.getUserInfo().getAccountname())){
			wo.getUserInfo().setAccountname(wo.getAdditional()==null?"":wo.getAdditional().getUsername());
		}
		if(wo.getAdditional()!=null){
			wo.getAdditional().setWorkOrder(wo);
		}
		
		Issue issue=(Issue) sessionFactory.getCurrentSession().get(Issue.class, Constants.ClassCategory.VIP_BLESS);//根据问题二级类型获取问题一级类型
		wo.getUserInfo().setLvlOne(issue);//设置问题的一级类型

		Games game=(Games) getSession().get(Games.class, FENGYUN);//设置游戏名称
		wo.getUserInfo().setGame(game);
		
		wo.getUserInfo().setWorkOrder(wo);
		wo.setCreateTime(new Date());//增加提问时间
		//wo.setGetState(false);//拉单状态
		//wo.setIsdelete(false);
		//wo.setIsreaded(false);
		wo.setSource(PINGTAI);//工单来源
		String state = wo.getStates();
		wo.setStates(MyUtil.isBlankOrNull(state) ? UNDEAL : state);
	};
	private void commonHandle(WorkOrder wo,String[] uploadFileName){
		
		if(uploadFileName!=null&&uploadFileName.length>0){
			uploadFileHandle(wo, uploadFileName);
		}
		//增加服务器
		int serverId=wo.getUserInfo().getServer().getId();
		LOGGER.debug("******Handle@serverId="+serverId);
		Servers server=(Servers) getSession().get(Servers.class, serverId);
		wo.getUserInfo().setServer(server);
		Games game=(Games) getSession().get(Games.class, FENGYUN);//设置游戏名称
		wo.getUserInfo().setGame(game);
	
		wo.getUserInfo().setWorkOrder(wo);
		
		wo.setCreateTime(new Date());//增加提问时间
		//wo.setGetState(false);//拉单状态
		//wo.setIsdelete(false);
		//wo.setIsreaded(false);

		wo.setSource(PINGTAI);//工单来源
		String state = wo.getStates();
		wo.setStates(MyUtil.isBlankOrNull(state) ? UNDEAL : state);
		
		//LOGGER.debug("come to save");
	};
	/**
	 * 账号问题处理
	 * @param wo
	 */
	private void accountHandle(WorkOrder wo,String[] uploadFileName){
		if(MyUtil.isBlankOrNull(wo.getUserInfo().getAccountname())){
			wo.getUserInfo().setAccountname(wo.getAdditional()==null?"":wo.getAdditional().getUsername());
		}
		if(wo.getAdditional()!=null){
			wo.getAdditional().setWorkOrder(wo);
		}
		//上传文件处理
		if(uploadFileName!=null&&uploadFileName.length>0){
			uploadFileHandle(wo, uploadFileName);
		}
		//增加服务器
		int serverId=wo.getUserInfo().getServer().getId();
		//LOGGER.debug("******Handle@serverId="+serverId);
		Servers server=(Servers) sessionFactory.getCurrentSession().get(Servers.class, serverId);
		wo.getUserInfo().setServer(server);
		Issue issue=(Issue) sessionFactory.getCurrentSession().get(Issue.class, Constants.ClassCategory.ACCOUNTNAME_LOCK);//根据问题二级类型获取问题一级类型
		wo.getUserInfo().setLvlOne(issue);//设置问题的一级类型

		Games game=(Games) getSession().get(Games.class, FENGYUN);//设置游戏名称
		wo.getUserInfo().setGame(game);
		
		wo.getUserInfo().setWorkOrder(wo);
		wo.setCreateTime(new Date());//增加提问时间
		//wo.setGetState(false);//拉单状态
		//wo.setIsdelete(false);
		//wo.setIsreaded(false);
		wo.setSource(PINGTAI);//工单来源
		String state = wo.getStates();
		wo.setStates(MyUtil.isBlankOrNull(state) ? UNDEAL : state);
	};
	/**
	 * 处理文件上传的方法
	 */
	public void uploadFileHandle(WorkOrder wo,String[] uploadFileName) {
		LOGGER.debug("cs center file upload");
		for(int i=0;i<uploadFileName.length;i++){
				FileUpLoad uploadFile=new FileUpLoad();
				uploadFile.setUserinfo(wo.getUserInfo());
				uploadFile.setPath(uploadFileName[i]);
				uploadFile.setUserinfo(wo.getUserInfo());
				wo.getUserInfo().getFiles().add(uploadFile);
		}
	//	LOGGER.debug("******SAVE upLoadFile over=");
	}
	/**
	 * 获取用户提交问题当前处理情况
	 * @param accName
	 */
	public String makeDealInfo(String accName){
		int c1 = getAllWorkOrderCountOfUserByStatus(accName, States.DEALED);
		int c2 = getAllWorkOrderCountOfUserByStatus(accName, States.DEALING);
		int c3 = getAllWorkOrderCountOfUserByStatus(accName, States.RESPONSED);
		int c4 =getAllWorkOrderCountOfUserByStatus(accName, States.UNDEAL);
		int c5 =getAllWorkOrderCountOfUserByStatus(accName, States.LACKINFO);
		return DepictXmlManager.makeDealInfo(accName, c1, c2, c3, c4, c5);
	}
	/**
	 * 获取服务器名称
	 * @param accName
	 */
	public String makeServerMap(){
		Map map=initServerMap();
		return DepictXmlManager.makeMapXml(map);
	}
	/**
	 * 获取充值类型
	 * @param accName
	 */
	public String makerechargeTypeMap(){
		Map map=initIssueTwoMap(11);
		return DepictXmlManager.makeMapXml(map);
	}
	/**
	 * 根据用户名和密码验证用户密码是否错误
	 * @param accountname 玩家账号
	 * @param password MD5加密的玩家密码
	 * @return Member对象
	 */
	public Member checkPassword(String accountname, String password){	
		LOGGER.debug("accountname = " +accountname + "|password = " + password);
		
		Query query=getSession().createQuery("from Member o where o.username=? and o.password=?");	
		query.setParameter(0, accountname);
		query.setParameter(1, password);
		List<Member> list=query.list();
		//LOGGER.debug("LIST SIZE ="+list.size() + "return =" +(list.size()>0?true:false));
		for(int i=0;i<list.size();i++){
			Member member = (Member)list.get(0);
			LOGGER.debug("member get usrname="+member.getUserInfo().getPhone());
		}
		return list.size()>0?(Member)list.get(0):null;
	}
	/**
	 * 验证游戏帐号是否存在
	 * @param accountname 游戏帐号
	 * @return	true:存在|false:不存在
	 */
	public boolean checkAcccountName(String accountname){
		Query query=sessionFactory.getCurrentSession().createQuery("from Member o where o.username=?");	
		query.setParameter(0, accountname);
		List<Member> list=query.list();
		return list.size()>0?true:false;
	}
	
	/**
	 * 处理玩家的评价
	 */
	public void saveEvaluation(WorkOrder wo,long woId) throws WoCenterException{
		try{
			WorkOrder workOrder=get(woId);
			
			workOrder.setComment(wo.getComment());
			workOrder.setEvaluation(wo.getEvaluation());
		}catch(HibernateException e){
			e.printStackTrace();
		}
	}
	/**
	 * 处理玩家的问题补充
	 */
	public String saveIssueAdditional(long id,String add,Staff staff,boolean fromUser,String[] uploadFileName) {
		LOGGER.debug("Do  Additional");
		WorkOrder workOrder=get(id);//获取工单信息
		if(uploadFileName!=null&&uploadFileName.length>0){
			uploadFileHandle(workOrder, uploadFileName);
		}
		//Department d=session.g
		//问题补充记录
		IssueAdditional issueAdditional=new IssueAdditional();	
		issueAdditional.setContent(add);
		issueAdditional.setName(staff.getNickName());
		issueAdditional.setFromUser(fromUser);
		issueAdditional.setAddDate(new Date());
		issueAdditional.setWorkOrder(workOrder);
		//LOGGER.debug("issueAdditional over");
		workOrder.getIssueAddSet().add(issueAdditional);
		//增加一个操作记录
		
		WorkOrderOper oper=new WorkOrderOper();
		oper.setOperTime(new Date());
		oper.setOperType((OperType) getSession().get(OperType.class, 7));
		oper.setWorker(staff.getNickName());
		oper.setWorkOrder(workOrder);
		oper.setWorkerParent(fromUser?"玩家":"客服部");//:staff.getDepartment().getName());
		oper.setContent(add);
		workOrder.getOpers().add(oper);
		
		if(workOrder.getStates().equals(RESPONSED)&&fromUser){
			workOrder.setStates(DEALING);
			workOrder.setTobeGroup(null);//MODIFY @ 2011.12.01
			workOrder.setAssign(null);//MODIFY @2011.12.01
		}
		//LOGGER.debug("Opers  Over");
		if(workOrder.isGetState()==true){
			workOrder.setStates(DEALING);//MODIFY @ 2011.10.10
		}
//		
//		try{
//			update(workOrder);
//		}catch(Exception e){
//			return null;
//		}
		return "更新成功！";
	}
	/**
	 * 处理客服创建的工单
	 */
	public long createWorkOrder(WorkOrder wo,Staff staff,int immediate,int toGoupId,long toStaffId) {
		try{
		//	LOGGER.debug("Enter CreateWorkOrder");

		Session s=getSession();
		WorkOrderUserInfo uf=wo.getUserInfo();

		
		if(wo.isIsdhgd()){//电话工单
			uf.setAccountname(uf.getAccountname().trim() + "!");
		}else{
			uf.setAccountname(uf.getAccountname().trim());
		}	
		
		//set game
		int gameId=1;
		if(uf.getGame()!=null){
			gameId=wo.getUserInfo().getGame().getId();
			uf.setGame((Games) s.get(Games.class, gameId));
		}else{
			uf.setGame((Games) s.get(Games.class, 1));
		}

		int lvlOneId=uf.getLvlOne()==null?Constants.ClassCategory.OTHERS:uf.getLvlOne().getId();
		if(lvlOneId!=0){
			uf.setLvlOne((Issue) s.get(Issue.class, lvlOneId));
		}else{
			uf.setLvlOne((Issue) s.get(Issue.class, Constants.ClassCategory.OTHERS));
		}
		//set issue two
		
		if(uf.getLvlTwo()!=null){
			int lvlTwoId=uf.getLvlTwo().getId();
			if(lvlTwoId!=0){
				uf.setLvlTwo((Issue) s.get(Issue.class, lvlTwoId));
			}else{
				uf.setLvlTwo(null);
			}
		}
		//set vocation
			
		int classCategoryId=uf.getClassCategory()==null?1:uf.getClassCategory().getId();
		if(classCategoryId!=0){
			uf.setClassCategory((ClassCategory) s.get(ClassCategory.class, classCategoryId));
		}else{
			uf.setClassCategory(null);
		}
		
		//set server
		int serverId=uf.getServer()==null?11:uf.getServer().getId();
		if(serverId!=0){
			Servers server=(Servers) s.get(Servers.class, serverId);
			uf.setServer(server);
		}else{
			uf.setServer(null);
		}
		
		staff=(Staff) s.get(Staff.class, staff.getId());
		//LOGGER.debug("1");
		wo.setCreateWorker(staff.getNickName());//创建人
		wo.setCreateTime(new Date());//创建时间
		wo.getUserInfo().setWorkOrder(wo);
		wo.setGetWorker(staff);//拉单人
		wo.setGroup(staff.getGroup());//所属小组
		wo.setGetTime(new Date());
		wo.setIsdelete(false);
		wo.setIsreaded(false);
		//wo.setPrefinishTime(prefinishTime)
		//LOGGER.debug("3");
		wo.setGetState(true);//拉单状态
		if(immediate!=0){//如果即时处理
			if(immediate==1){
				wo.addOper(createOper(staff, wo.getSuggestion(),REPLY ));
				wo.setStates(RESPONSED);
			}else if(immediate==2){
				wo.addOper(createOper(staff, wo.getSuggestion(), DEAL));
				wo.setWorker(staff);//设置处理人
				wo.setStates(DEALED);
			}else{
				//wo.addOper(createOper(staff, wo.getSuggestion(), ASSIGN));
				Assign assign=new Assign();//派转信息设置
				if(toStaffId!=0){//if 选择员工
					Staff sta=(Staff) getSession().get(Staff.class, toStaffId);
					wo.setTobeGroup(sta.getGroup());
					assign.setFrom(staff.getAccName()+"@"+staff.getGroup().getName());
					assign.setTo(sta.getNickName()+"@"+sta.getGroup().getName());
					assign.setStatu("ASSIGNING");
				}else{//没有选择员工
					Group tobe=(Group) s.get(Group.class, toGoupId);
					wo.setTobeGroup(tobe);
					LOGGER.debug("Assign to ="+tobe.getName());
					//Assign assign=new Assign();//派转信息设置
					assign.setFrom(staff.getAccName());
					assign.setTo(tobe.getName());
					assign.setStatu("ASSIGNING");
				}
				//LOGGER.debug("4");
				wo.setAssign(assign);
				wo.setStates(DEALING);
				//Add a  operating record
				WorkOrderOper oper=new WorkOrderOper();
				OperType operType=(OperType) s.get(OperType.class, OperTypeId.ASSIGN);
				oper.setContent(assign.getFrom()+"派单到"+assign.getTo()+" | "+wo.getSuggestion());
				oper.setOperTime(new Date());
				oper.setOperType(operType);
				oper.setWorker(staff.getNickName());
				oper.setWorkerParent(staff.getDepartment().getName());
				oper.setWorkOrder(wo);
				wo.getOpers().add(oper);
			}
		}else{
			wo.setStates(DEALING);
		}
		if(wo.isIsdhgd()){//电话工单
			wo.setStates(RESPONSED);
			WorkOrderOper oper=new WorkOrderOper();
			OperType operType=(OperType) s.get(OperType.class, OperTypeId.REPLY);
			oper.setContent("结案|"+wo.getSuggestion());
			oper.setOperTime(new Date());
			oper.setOperType(operType);
			oper.setWorker(staff.getNickName());
			oper.setWorkerParent(staff.getDepartment().getName());
			oper.setWorkOrder(wo);
			wo.getOpers().add(oper);
			wo.setWorker(staff);
		//	uf.setLvlOne(lvlOne);
		}	
	//	LOGGER.debug("组装完毕！");
		save(wo);
		LOGGER.debug("Create a new workOrder"+wo.getId());
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return wo.getId();
	}
	private WorkOrderOper createOper(Staff staff,String content,int operTy){
		WorkOrderOper oper=new WorkOrderOper();
		
		oper.setOperTime(new Date());
		if(!StringUtil.isBlankOrNull(content))
			oper.setContent(content);
		oper.setWorker(staff.getAccName());
		oper.setWorkerParent(staff.getDepartment().getName());
		
		OperType operType=(OperType) getSession().get(OperType.class, operTy);
		oper.setOperType(operType);
		return oper;
	}
	/**
	 * 更新工单信息
	 */
	public boolean updateWorkORder(WorkOrder wo,long workOrderId) {
		WorkOrder workOrder=(WorkOrder) getSession().get(WorkOrder.class, workOrderId);
//		workOrder.setSource(wo.getSource());
//		workOrder.setUrgency(wo.getUrgency());
		
//		workOrder.getUserInfo().setAccountname(wo.getUserInfo().getAccountname());
//		workOrder.getUserInfo().setRealname(wo.getUserInfo().getRealname());
//		workOrder.getUserInfo().setUsername(wo.getUserInfo().getUsername());
//		workOrder.getUserInfo().setTel(wo.getUserInfo().getTel());
//		workOrder.getUserInfo().setMail(wo.getUserInfo().getMail());
//		workOrder.getUserInfo().setMemo(wo.getUserInfo().getMemo());
//		
//		workOrder.setStates(DEALING);//MODIFY @ 2011.09.22
//		workOrder.setTobeGroup(null);//MODIFY @ 2011.09.22
		//LOGGER.debug("基本类型信息修改完毕！");
		//Areas area=(Areas) getEntryObject(Areas.class, wo.getUserInfo().getArea().getName());
		//Games game=(Games) getEntryObject(Games.class, wo.getUserInfo().getGame().getName());
		//Servers server=(Servers) getEntryObject(Servers.class, wo.getUserInfo().getServer().getName());
		Issue one=(Issue) getEntryObject(Issue.class, wo.getUserInfo().getLvlOne().getName());
		//System.out.println(one.getName());
		//ClassCategory ccg=(ClassCategory) getEntryObject(ClassCategory.class, wo.getUserInfo().getClassCategory().getName());
		//LOGGER.debug("引用类型信息查询完毕！");
		//workOrder.getUserInfo().setArea(area);
		//workOrder.getUserInfo().setGame(game);
		//workOrder.getUserInfo().setServer(server);
		workOrder.getUserInfo().setLvlOne(one);
		//workOrder.getUserInfo().setClassCategory(ccg);
	//	getSession().update(workOrder);
		LOGGER.debug("引用类型信息修改完毕！");
		return true;
	}
	//工单操作信息编辑
	public void editPtReply(Staff staff,long id,String content,String reason) throws WoCenterException{
			WorkOrderOper oper=(WorkOrderOper) getSession().get(WorkOrderOper.class, id);
			
			WorkOrder wo=(WorkOrder) getSession().get(WorkOrder.class, oper.getWorkOrder().getId());
			Staff staf=(Staff) getSession().get(Staff.class, staff.getId());
			int roleId=staf.getRole().getId();
			int roleLevel=staf.getRole().getLevel();
			if(oper.getOperType().getId()==OperTypeId.DEAL){
				if(roleId!=2&&roleId!=3&&roleId!=4){
					oper.setContent(content);
					wo.addOper(createOper(staf, reason, EDITDEAL));
				}else{
					throw new WoCenterException(StateList.Illegal_OPERATION_ERROR, "不好意思，权限不够！");
				}
			}else if((oper.getOperType().getId()==OperTypeId.REPLY)){
				if(roleLevel>0||roleId==4){
					oper.setContent(content);
					wo.addOper(createOper(staf, reason, EDITREPLY));
				}else{
					throw new WoCenterException(StateList.Illegal_OPERATION_ERROR, "不好意思，权限不够！");
				}
			}
	}
	//工单操作信息删除
	public void deletePtReply(Staff staff, long id, String comment) throws WoCenterException{
		WorkOrderOper oper=(WorkOrderOper) getSession().get(WorkOrderOper.class, id);
		
		WorkOrder wo=(WorkOrder) getSession().get(WorkOrder.class, oper.getWorkOrder().getId());
		Staff staf=(Staff) getSession().get(Staff.class, staff.getId());
		int roleId=staf.getRole().getId();
		int roleLevel=staf.getRole().getLevel();
		if(oper.getOperType().getId()==OperTypeId.DEAL){
			if(roleId!=2&&roleId!=3&&roleId!=4){
				getSession().delete(oper);
				wo.addOper(createOper(staf, comment, EDITDEAL));
			}else{
				throw new WoCenterException(StateList.Illegal_OPERATION_ERROR, "不好意思，权限不够！");
			}
		}else if((oper.getOperType().getId()==OperTypeId.REPLY)){
			if(roleLevel>0){
				getSession().delete(oper);
				wo.addOper(createOper(staf, comment, EDITREPLY));
			}else{
				throw new WoCenterException(StateList.Illegal_OPERATION_ERROR, "不好意思，权限不够！");
			}
		}
	}
	/**
	 * 根据名称查找对象
	 * @param clazz
	 * @param name
	 * @return
	 */
	private Object getEntryObject(Class clazz,String name){
		String hql="from "+clazz.getSimpleName()+" o where o.name=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, name);
		
		return query.list().get(0);
	}
	/***工单广场排行榜 S***/
	//当日
	public JSONArray getRangeObjectOfMonth() {
	//	LOGGER.debug("创建当月排行对象：CountResult");
		//String hql="select w from WorkOrder w where w.getState=true and trunc(w.getTime)=trunc(sysdate-30)"; Oracle
		String hql="select  w.group,w.states   from WorkOrder w where w.getState=true and month(w.getTime)=month(now()) and w.isdelete=false";
		return  getRangeObject(hql);
	}
	//当月
	public JSONArray getRangeObjectOfToday() {
		String hql="select w.group,w.states  from WorkOrder w where w.getState=true and  date(w.getTime)  =  curdate() and w.isdelete=false";
		return  getRangeObject(hql);
	}
	private List<CountResult> getRangeList(String hql) {
		//获取当天拉单状态为true的工单
		//String deal="select w from WorkOrder w where w.getState=true and trunc(w.createTime)=trunc(sysdate-30)";
		//获取某小组的所有拉单
		//String group="select w from WorkOrder w where w.getWorker.group.id=3";
		//获取所有小组
		String getGroups="select o from Group o where o.departId=?";
		Query query=getSession().createQuery(hql);
		List<WorkOrder> list=new ArrayList<WorkOrder>();
		query.list();
		
		Iterator iterator=query.list().iterator();
		while(iterator.hasNext()){
			WorkOrder workOrder=new WorkOrder();
			Object[] wo=(Object[]) iterator.next();
			workOrder.setGroup((Group) wo[0]);
			workOrder.setStates(wo[1].toString());
			list.add(workOrder);
			workOrder=null;
		}
		LOGGER.debug("今天全部拉单数量："+list.size());
		
		Query query1=getSession().createQuery(getGroups);
		query1.setParameter(0, 2);
		List<Group> groupList=query1.list();
		//LOGGER.debug("获取所有小组"+groupList.size());			
		List<CountResult> crList=new ArrayList<CountResult>();
					
		for(Group g:groupList){
			if(!g.getName().equals("无分组"))
			crList.add(new CountResult(g.getId(),g.getName()));
		}
		//-----------------------
		for(WorkOrder w:list){
			//System.out.println(w.getGetWorker().getAccName());
			int id=w.getGroup().getId();
			for(CountResult cr:crList){
				if(id==cr.getGroupId()){
					cr.setCountGet(cr.getCountGet()+1);
					if(w.getStates().equals(DEALED)){
						cr.setCountDeal(cr.getCountDeal()+1);
					}
					if(w.getStates().equals(RESPONSED)){
						cr.setCountReply(cr.getCountReply()+1);
					}
				}
			}
		}
		return crList;
	}
	public JSONArray getRangeObject(String hql){
		//LOGGER.debug("创建排行对象：CountResult");
		List<CountResult> list=getRangeList(hql);//对前7小组排名
		if(list.size()>6){
			list=list.subList(0, 6);
		}
		Collections.sort(list);
		JSONArray jsonArray = JSONArray.fromObject(list);
		return  jsonArray;
	}
	/***@ 工单广场排行 E @***/
	
	/***工单界面管理***/
	//工单广场查询下拉单异步读取
	public JSONObject getJsonObject(Class clazz,int pid){
		LOGGER.debug("获取"+clazz.getSimpleName()+"信息");
		String simpleName=clazz.getSimpleName();
		String hql="";
		Query query=null;
		if(pid==0){
			if(simpleName.equals("Servers"))
				hql="from "+simpleName+" o where o.useId=1000";
			else
				hql="from "+simpleName;
			query=getSession().createQuery(hql);
		}else{
			hql="select o from "+simpleName+" o where o.parent.id=?";
			query=getSession().createQuery(hql).setParameter(0, pid);
		}
		
		List<?> list=query.list();
		List<AreaJson> al=new ArrayList<AreaJson>();
		JSONObject jobj=new JSONObject();
		if(clazz.getSimpleName().equals("Areas")&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Areas area1=(Areas) list.get(i);
				//Games game=(Games) sessionFactory.getCurrentSession().get(Games.class, area1.getGame().getId());
				AreaJson aj=new AreaJson();
				aj.setId(area1.getId());
				aj.setName(area1.getName());
				al.add(aj);
				aj=null;
			}
			jobj.element("confList", al);
		}else if(clazz.getSimpleName().equals("Servers")&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Servers server=(Servers) list.get(i);
				//Games game=(Games) sessionFactory.getCurrentSession().get(Games.class, area1.getGame().getId());
				AreaJson aj=new AreaJson();
				aj.setId(server.getId());
				aj.setName(server.getName());
				al.add(aj);
				aj=null;

			}
			jobj.element("confList", al);
		}else if(clazz.getSimpleName().equals("Issue")&&list.size()>0){
			
			for(Issue is:(List<Issue>)list){
				al.add(new AreaJson(is));
			}
			
			jobj.element("confList", al);
		}
		else{
			if(list.size()>0){
				//System.out.println("put"+clazz.getSimpleName());
				jobj.element("confList", list);
				//System.out.println("put success!");
			}else{
				jobj.element("empty", "暂无记录~");
			}
		}
		return jobj;
	}
	/*****************服务器相关******************/
	public Servers getServer(int id){
		Servers server=(Servers)getSession().get(Servers.class, id);
		return server;
	}
	public String addServer(int id,String name){
		LOGGER.debug("增加服务器信息");
		try{
		Servers server=new Servers();
		server.setId(id);
		server.setName(name);
		
		save(server);
		}catch(Exception e){}
		return "Edit Server Successfully~";
	}
	/**
	 *  更新服务器信息
	 */
	public void updateServersInfo(Map<Integer,String> map) {
		LOGGER.debug("更新服务器信息");
		try{
		String hql="from Servers";
		Query q=getSession().createQuery(hql);
		List<Servers> list=q.list();
	//	if(list.size()==map.size()) return;
		Set<Integer> en=map.keySet();
		for(Servers s:list){
			if(en.contains(s.getId())){
				map.remove(s.getId());
			}
		}
		Set<Entry<Integer,String>> set=map.entrySet();
		for(Entry<Integer,String> e:set){
			Servers server=new Servers();
			server.setId(e.getKey());
			server.setName(e.getValue());
			getSession().save(server);
		}
		}catch(Exception e){}
	}
	public String deleteServer(int id){
		LOGGER.debug("删除服务器");
		Session s=getSession();
		Servers server=(Servers) s.get(Servers.class, id);
		//System.out.println(server.getName());
		try{
			s.delete(server);
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return "删除服务器成功!";
	};
	/*******************游戏相关***********************/
	public String editGame(int id,String name){
		LOGGER.debug("修改游戏信息");
		Games game=(Games) getSession().get(Games.class, id);
		game.setName(name);
		update(game);
		return "Edit Game Successfully~";
	}
	public String addGame(String name){
		LOGGER.debug("增加游戏信息");
		Games game=new Games();
		game.setName(name);
		save(game);
		return "添加游戏信息成功~";
	}
	public String deleteGame(int id){
		Games game=(Games) getSession().get(Games.class, id);
		delete(game);
		return "删除游戏成功~";
	}
	/*************************违规类型相关*************************/
	public String addReportKind(String name){
		ReportKinds rk=new ReportKinds();
		rk.setName(name);
		save(rk);
		return "Save ReportKinds Success~";
	}
	public String editReportKind(int id,String name){
		ReportKinds rk=(ReportKinds) getSession().get(ReportKinds.class, id);
		rk.setName(name);
		update(rk);
		return "Edit ReportKinds Success~";
	}
	public String deleteReportKind(int id){
		ReportKinds rk=(ReportKinds) getSession().get(ReportKinds.class, id);
		delete(rk);
		return "Delete ReportKinds Success~";
	}
	/*************************充值类型相关*************************/
	public String addRechargeType(String name){
		RechargeType rt=new RechargeType();
		rt.setName(name);
		save(rt);
		return "保存充值类型成功~";
	}
	public String editRechargeType(int id,String name){
		RechargeType rt=(RechargeType) getSession().get(RechargeType.class, id);
		rt.setName(name);
		update(rt);
		return "修改充值类型成功~";
	}
	public String deleteRechargeType(int id){
		RechargeType rt=(RechargeType) getSession().get(RechargeType.class, id);
		delete(rt);
		return "删除充值类型成功~";
	}
	/*************************问题小类型相关*************************/
	public String addIssue(int pid,String name){
		Issue p=(Issue) getSession().get(Issue.class, pid);
		//System.out.println(p.getId());
		Issue rt=new Issue();
		rt.setParent(p);
		rt.setName(name);
		save(rt);
		return "保存问题小类型成功~";
	}
	public String editIssue(int id,String name){
		Issue issue=(Issue) getSession().get(Issue.class, id);
		issue.setName(name);
		//update(issue);
		return "修改问题小类型成功~";
	}
	public String deleteIssue(int id){
		Issue issue=(Issue) getSession().get(Issue.class, id);
		issue.setParent(null);
		return "删除问题小类型成功~";
	}
	/*************************咨询类型相关*************************/
	public String addAdvisory(String name){
		Advisory adv=new Advisory();
		adv.setName(name);
		save(adv);
		return "保存咨询类型成功~";
	}
	public String editAdvisory(int id,String name){
		Advisory adv=(Advisory) getSession().get(Advisory.class, id);
		adv.setName(name);
		update(adv);
		return "修改咨询类型成功~";
	}
	public String deleteAdvisory(int id){
		Advisory adv=(Advisory) getSession().get(Advisory.class, id);
		delete(adv);
		return "删除咨询类型成功~";
	}
	
//	private List getList(Class clazz){
//		String hql="from"+clazz.getSimpleName();
//		Query query=sessionFactory.getCurrentSession().createQuery(hql);
//		List list=query.list();
//		return list;
//	}
	
	private Map<String, Integer> getWorkOrderMap(){
		Map<String, Integer> map =new HashMap<String, Integer>();
		map.put("Group1", 30);
		map.put("Group2", 20);
		map.put("Group3", 10);
		return map;
	}
	private Map<String, Integer> replyWorkOrderMap() {
		Map<String, Integer> map =new HashMap<String, Integer>();
		map.put("Group1", 30);
		map.put("Group2", 20);
		map.put("Group3", 10);
		return map;
	}
	public Document getRangeDocument(String type){
		Map<String, Integer> map;
							//System.out.println("getRangeDocument");
		if(type.equals("get")){
			map=getWorkOrderMap();
							//System.out.println(map);
		}else if(type.equals("real")){
			map=replyWorkOrderMap();
		}else{
			map=replyWorkOrderMap();
		}
		Document document=null;
		document=DocumentHelper.createDocument();
		Element returnVal=DocumentHelper.createElement("retuenVal");
		document.setRootElement(returnVal);
		
		for(Entry<String,Integer> entry:map.entrySet()){
			Element range=returnVal.addElement("range");
			Element group=range.addElement("group");
			group.setText(entry.getKey());
			Element count=range.addElement("count");
			count.setText(entry.getValue().toString());
		}
		return document;
	}
	
	public Map<Integer, String> initAreaMap() {
		LOGGER.debug("初始化游戏大区信息");
		Map<Integer, String> map=new HashMap<Integer, String>();
		String hql="from Areas";
		List<Areas> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		for(Areas area:list){
			map.put(area.getId(),area.getName());
		}
		return map;
	}
	public Map<Integer, String> initClassCategoryMap() {
		LOGGER.debug("初始化角色职业信息");
		Map<Integer, String> map=new HashMap<Integer, String>();
		String hql="from ClassCategory";
		List<ClassCategory> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		for(ClassCategory cc:list){
			map.put(cc.getId(),cc.getName());
		}
		return map;
	}
	public Map<Integer, String> initGameMap() {
		LOGGER.debug("初始化游戏名称信息");
		Map<Integer, String> map=new HashMap<Integer, String>();
		String hql="from Games";
		List<Games> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		for(Games game:list){
			map.put(game.getId(),game.getName());
		}
		return map;
	}
	public Map<Integer,String> initSourceMap() {
		LOGGER.debug("初始化工单来源信息");
		Map<Integer,String> m=new HashMap<Integer,String>();
		String hql="from WorkOrderSource";
		List<WorkOrderSource> list=getSession().createQuery(hql).list();
		for(WorkOrderSource ws:list){
			m.put(ws.getId(), ws.getName());
		}
		return m;
	}
	public Map<Integer,String> initUrgencyMap() {
		LOGGER.debug("初始化紧急程度信息");
		Map<Integer,String> urgMap=new HashMap<Integer,String>();
		String hql="from Urgency";
		List<Urgency> list=getSession().createQuery(hql).list();
		for(Urgency urgency:list){
			urgMap.put(urgency.getId(), urgency.getName());
		}
		return urgMap;
	}
	public Map<Integer, String> initIssueOneMap(int pid) {
		LOGGER.debug("初始化一级问题类型信息");
		Map<Integer, String> map=new HashMap<Integer, String>();
		String hql="select o from Issue o where o.parent.id=?";
		
		List<Issue> list=getSession().createQuery(hql).setParameter(0, pid)
							.list();
		for(Issue issue:list){
			map.put(issue.getId(),issue.getName());
		}
//		if(pid==9){
//			map.put(10, "账号问题");
//		}
		return map;
	}
	public Map<Integer, String> initIssueTwoMap(int pid) {
		LOGGER.debug("初始化二级问题类型信息");
		Map<Integer, String> map=new HashMap<Integer, String>();
		String hql="select o from Issue o where o.parent.id=?";
		
		List<Issue> list=getSession().createQuery(hql).setParameter(0, pid)
							.list();
		for(Issue issue:list){
			map.put(issue.getId(),issue.getName());
		}
		return map;
	}
	public Map<Integer, String> initReportKindsMap() {
		LOGGER.debug("初始违规类型信息");
		Map<Integer, String> map=new HashMap<Integer, String>();
		String hql="from ReportKinds";
		List<ReportKinds> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		for(ReportKinds rk:list){
			map.put(rk.getId(),rk.getName());
		}
		return map;
	}
	public Map<Integer, String> initAdvisoryMap() {
		LOGGER.debug("初始咨询问题类型信息");
		Map<Integer, String> map=new HashMap<Integer, String>();
		String hql="from Advisory";
		List<Advisory> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		for(Advisory ad:list){
			map.put(ad.getId(),ad.getName());
		}
		return map;
	}
	public Map<Integer, String> initRechargeTypeMap() {
		LOGGER.debug("初始咨询问题类型信息");
		Map<Integer, String> map=new HashMap<Integer, String>();
		String hql="from RechargeType";
		List<RechargeType> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		for(RechargeType ad:list){
			map.put(ad.getId(),ad.getName());
		}
		return map;
	}
	public List initList(Class clazz){
		String name=clazz.getSimpleName();
		String hql="from "+clazz.getSimpleName();
		if(name.equals("Issue")){
			hql="select o from Issue o where o.parent.id=2";
		}
		List list=null;
		try{
			list=sessionFactory.getCurrentSession().createQuery(hql).list();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(name.equals("Games")){
			List<Games> gameList=list;
			List<AreaJson> jList=new ArrayList<AreaJson>();
			for(Games game:gameList){
				//System.out.println(game.getName());
				AreaJson aj=new AreaJson();
				aj.setId(game.getId());
				aj.setName(game.getName());
				jList.add(aj);
				aj=null;
			}
			return jList;
		}
		
		if("Areas".equals(name)){
			List<Areas> areaList=list;
			//System.out.println(areaList);
			List<AreaJson> jList=new ArrayList<AreaJson>();
			for(Areas area:areaList){
				AreaJson aj=new AreaJson();
				aj.setId(area.getId());
				aj.setName(area.getName());
				jList.add(aj);
				aj=null;
			}
			//System.out.println(jList);
			return jList;
		}
		else if("Servers".equals(name)){
			List<Servers> serverList=list;
			List<AreaJson> jList=new ArrayList<AreaJson>();
			for(Servers server:serverList){
				AreaJson aj=new AreaJson();
				aj.setId(server.getId());
				aj.setName(server.getName());
				jList.add(aj);
				aj=null;
			}
			return jList;
		}
		else if("ClassCategory".equals(name)){
			List<ClassCategory> ccgList=list;
			List<AreaJson> jList=new ArrayList<AreaJson>();
			for(ClassCategory ccg:ccgList){
				AreaJson aj=new AreaJson();
				aj.setId(ccg.getId());
				aj.setName(ccg.getName());
				jList.add(aj);
				aj=null;
			}
			return jList;
		}
		else if("Issue".equals(name)){
			List<Issue> issueList=list;
			List<AreaJson> jList=new ArrayList<AreaJson>();
			for(Issue issue:issueList){
				AreaJson aj=new AreaJson();
				aj.setId(issue.getId());
				aj.setName(issue.getName());
				jList.add(aj);
				aj=null;
			}
			return jList;
		}
		else {
			return list;
		}
	}
	public String initGoodsItems(String keyWord) {
		LOGGER.debug("do search");
//		list.add("hahaha");
//		list.add("popopo");
//		list.add("yiyiyi");
		String hql="select o from GameGoods o where upper(o.name) like ?";
		Query query=null;
		List<GameGoods> list=null;
		try{
		query=getSession().createQuery(hql).setParameter(0, "%"+keyWord.toUpperCase()+"%");
		list=query.list();
		}catch(HibernateException e){
			e.printStackTrace();
		}
		
		
		LOGGER.debug("size="+list.size());
		StringBuffer sb=new StringBuffer();
		sb.append("<itemList>");
		for(GameGoods g:list){
			sb.append(g.getName()+",");
		}
		sb.append("</itemList>");
			return sb.toString();
	}
	private static final Logger LOGGER = Logger.getLogger(WorkOrderServiceImpl.class);
	private static final boolean isFtp = MyUtil.Toboolean(Globe.getProperty("ftp/isremote"));
	/**
	 * 读取玩家的联系信息
	 */
	public JSONObject readContactInfoOfUser(String accName) {
		JSONObject jobj=new JSONObject();
		String hql="select m from Member m where m.username=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, accName);
		List<Member> mList=query.list();
		if(mList.size()>0){
			Member m=mList.get(0);
			jobj.element("tel", m.getUserInfo().getPhone());
			jobj.element("qq", m.getUserInfo().getQq());
		}else{
			jobj.element("empty","nothing");
		}
		return jobj;
	}
	public void innerGameBugHandle(WorkOrder wo, String[] uploadFileName) throws WoCenterException {
		try{
				if(uploadFileName!=null&&uploadFileName.length>0){
					uploadFileHandle(wo, uploadFileName);
				}
				
				Issue issue=(Issue) getSession().get(Issue.class, Constants.ClassCategory.GAMEBUG);
				wo.getUserInfo().setLvlOne(issue);
				
				String source=wo.getSource();
				
				int serverId = wo.getUserInfo().getServer().getId();
				Object obj = getSession().get(Servers.class, serverId);
				Servers server = obj == null ? null : (Servers)obj;
				wo.getUserInfo().setServer(server);
				
				Games game=(Games) getSession().get(Games.class, FENGYUN);//设置游戏名称
				wo.getUserInfo().setGame(game);
			
				wo.getUserInfo().setWorkOrder(wo);
				//wo.setCreateWorker(wo.getUserInfo().getAccountname());//MODIFTY @ 2011.09.29 
				wo.setCreateTime(new Date());//增加提问时间
				wo.setGetState(false);//拉单状态
				wo.setIsdelete(false);
				wo.setIsreaded(false);
			
				wo.setStates(UNDEAL);
				if(wo.getAdditional()!=null)
					wo.getAdditional().setWorkOrder(wo);
		
				if(source.equals("2")){
					wo.setSource(Source.INNERGAME);
				}
				wo.setUrgency(URGENT);
				save(wo);
		}catch(HibernateException e){
			throw new WoCenterException(StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
		}
	}
	public void saveDefaultMemberInfo(WorkOrder wo) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 处理文件上传的方法
	 */
	public void uploadFileHandle(WorkOrder wo, File[] upload,
			String[] uploadFileName,String rp,String name) {
		File parent=new File(rp);
		File toFile=null;
		System.out.println("***********上传文件开始:);");
		//System.out.println(upload.length);
		for(int i=0;i<upload.length;i++){
				//上传文件重命名
				String str=String.valueOf(new Date().getTime()+i);
				str=str.substring(str.length()-8);
				String suffix=uploadFileName[i].substring(uploadFileName[i].length()-4);
//				System.out.println("***********suffix:"+suffix+"**********");
//				System.out.println("***********str:"+str+"**********");
//				System.out.println("***********name:"+name+"**********");
				String fileName=name+str+suffix;
				toFile=new File(parent,fileName);
//				System.out.println("***********toFileParent:"+toFile.getParentFile());
//				System.out.println("***********toFile:"+toFile.getName());
				if(!toFile.getParentFile().exists()){
					toFile.getParentFile().mkdirs();
				}
				try {
					FileUtils.copyFile(upload[i], toFile);//文件写入保存路径
				} catch (IOException e) {
					e.printStackTrace();
				}
//				System.out.println("***********xxxtoFileParent:"+toFile.getParentFile());
//				System.out.println("***********xxxtoFile:"+toFile.getName());
				FileUpLoad uploadFile=new FileUpLoad();
//				System.out.println("***********isFtp:"+isFtp+"*************");
//				System.out.println("***********rp:"+rp+"*************");
				if (isFtp)//如果需要FTP上传
				{
					String s= toFile.getParentFile()+"/"+toFile.getName();
//					System.out.println("***********rp.indexOf:"+rp.indexOf("/")+"*************");
//					System.out.println("***********rp.length():"+rp.length()+"*************");
					//获取虚拟目录
					String vpath = rp.substring(rp.indexOf("/"),rp.length());
//					System.out.println("***********vpath:"+vpath+"*************");
					try {
						String remotefile = FtpClientUtil.getInstance().upload(s,vpath);
						FtpClientUtil.getInstance().close();
						if (remotefile!=null)
							uploadFile.setPath(remotefile);
//						System.out.println("***********remotefile:"+remotefile+"***********");
						
					} catch (Exception e) {
						
						e.printStackTrace();
					}
				}else
				{
					//uploadFile.setPath(rp+"/"+fileName);  FTP
					int back=rp.lastIndexOf("/");
					String backPath=rp.substring(back);
					uploadFile.setPath("/upload"+backPath+"/"+fileName);
				}
				uploadFile.setUserinfo(wo.getUserInfo());
				wo.getUserInfo().getFiles().add(uploadFile);
		}
	}
}
