package com.hy.wo.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import com.hy.wo.cache.JCSManager;
import com.hy.wo.dao.BaseDaoSupport;
import com.hy.wo.exception.WoCenterException;
import com.hy.wo.po.Assign;
import com.hy.wo.po.BatchData;
import com.hy.wo.po.Group;
import com.hy.wo.po.OperType;
import com.hy.wo.po.Role;
import com.hy.wo.po.WorkOrder;
import com.hy.wo.po.Staff;
import com.hy.wo.po.WorkOrderOper;
import com.hy.wo.service.WorkOrderPFService;
import com.hy.wo.util.AreaJson;
import com.hy.wo.util.CountResult;
import com.hy.wo.util.MyUtil;
import com.hy.wo.util.Pages;
import com.hy.wo.util.WorkOrderNearNow;
import com.hy.wo.util.Constants.ClassCategory;
import com.hy.wo.util.Constants.DepartmentId;
import com.hy.wo.util.Constants.OperTypeId;
import com.hy.wo.util.Constants.RoleLevel;
import com.hy.wo.util.Constants.States;
import static com.hy.wo.util.Constants.States.*;
import static com.hy.wo.util.Constants.RoleLevel.*;
import static com.hy.wo.util.Constants.DepartmentId.*;
import com.you9.base.Globe;
import com.you9.base.util.StringUtil;

@Service @Transactional
public class WorkOrderPFServiceImpl extends BaseDaoSupport<WorkOrder> implements
		WorkOrderPFService {
	private static final Logger LOGGER = Logger.getLogger(WorkOrderPFServiceImpl.class);//定义日志
	public boolean pullWorkOrder(WorkOrder workOrder,long staffId)throws IllegalArgumentException {
		LOGGER.debug("拉单成功，修改工单信息");
		String LDreason=workOrder.getReply();
		Staff staff=(Staff) getSession().get(Staff.class, staffId);
		WorkOrder wo=get(workOrder.getId());
		
		if(staff.getRole().getId() == RoleLevel.ZUZHANG_ROLE){
			WorkOrderOper oper=new WorkOrderOper();
			oper.setContent(LDreason);
			oper.setOperTime(new Date());
			oper.setOperType((OperType) getSession().get(OperType.class, OperTypeId.LADAN));
			oper.setWorker(staff.getAccName());
			oper.setWorkerParent(staff.getGroup().getName());
			oper.setWorkOrder(wo);
			wo.getOpers().add(oper);
			
			if(wo.isGetState()==false){
				wo.setGetTime(new Date());
				wo.setGetWorker(staff);
				wo.setStates(DEALING);
			}
		}
		else{
				wo.setGetTime(new Date());
				wo.setGetWorker(staff);
				wo.setStates(DEALING);
		}
		wo.setGroup(staff.getGroup());//所属小组
		wo.setGetState(true);
		getSession().update(wo);
		return true;
	}
	
	/**
	 * 随机获取一条尚未被拉单的工单ID
	 */
	public String getWorkOrderIdOfPullState(Staff staff){
		String HQL="select count(o) from WorkOrder o where o.group.id=? and o.states=? and o.isdelete=false";
		Query query=getSession().createQuery(HQL).setParameter(0, staff.getGroup().getId())
							.setParameter(1, DEALING);
		int total=((Long)query.uniqueResult()).intValue();
		LOGGER.debug("处理中数量："+total);
		if(total<=500){
			String hql="select o from WorkOrder o where o.getState = false and o.isdelete=false order by o.createTime asc";//  limit 1";			
			query=getSession().createQuery(hql);
			query.setMaxResults(1);
			List list=query.list();
			if(list.size()>0){
				WorkOrder wo=(WorkOrder) list.get(0);
				//LOGGER.debug("数量："+list.size());
				LOGGER.debug("获取到一条工单："+wo.getId());
				pullWorkOrder(wo,staff.getId());
				return "Q-拉单成功，工单号："+wo.getId();
			}else{
				return "尚无可拉单工单！";
			}
		}
		else{
			return "您的小组处理中工单过多，请先处理！";
		}
	}

	public int getAllWorkOrderOfGetState(int start, int len, boolean getState) {
		String hql="select count(o) from WorkOrder o where o.getState=? and o.isdelete = false";
		
		Query query=getSession().createQuery(hql);
		query.setParameter(0, getState);
		Object obj=query.uniqueResult();
	
		return convertQueryResult(obj);
	}
	
	/**
	 * 任意条件工单查询
	 */
	public Pages<WorkOrder> searchWorkOrder(Group group,Role role,WorkOrder workOrder, int start,
			int len,Date startTime,Date endTime) {
		LOGGER.debug("开始查询！");
		int roleLevel=role.getLevel();
		int roleId=role.getId();
		int groupId=group.getId();
		Pages<WorkOrder> pages=null;
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		String hql="";
		Object[] param=null;
		
		if(createHQL(workOrder,startTime,endTime,roleId)==null){//if  no condition
			if(roleLevel >= RoleLevel.ADMIN_ROLE){
				hql="";
			}
			else{
				if( roleId == RoleLevel.GENARA_ROLE || 
					roleId == RoleLevel.GM_ROLE ||
					roleId == RoleLevel.ZUZHANG_ROLE){
					hql+=" o.states != ? and";
					param=new Object[]{States.UNDEAL};
				}else{
					hql+=" o.tobeGroup.id=? and";
					param=new Object[]{groupId};
				}
			}
			hql+=" o.isdelete=false";
			LOGGER.debug("---"+hql);
			try{
			pages=getPageData(start, len, hql, param, orderby);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{//if conditions is not null
			hql=createHQL(workOrder,startTime,endTime,roleId);
			if(role.getLevel() >= RoleLevel.ADMIN_ROLE){
				hql+="";
			}else{
				if( roleId == RoleLevel.GENARA_ROLE || 
					roleId == RoleLevel.GM_ROLE ||
					roleId == RoleLevel.ZUZHANG_ROLE){
					if(workOrder.getStates().equals(States.UNDEAL)){
						hql=hql.replace("o.states=?", "o.states!=?");
					}
				}else{
					hql+="";
				}
			}
			hql+=" o.isdelete=false";
			LOGGER.debug("---"+hql);
			param=createParams(workOrder,startTime,endTime);
			pages=getPageData(start, len, hql, param, orderby);
		}
		LOGGER.debug("查询完毕！");
		return pages;
	}
	private String createHQL(WorkOrder wo,Date startTime,Date endTime,int roleId){
		//System.out.println("creatHQL");
		String hql="";
		hql+=StringUtil.isBlankOrNull(wo.getCreateWorker())?"":" o.createWorker=? and";
		hql+=(startTime==null?"":" o.createTime>=? and o.createTime<=? and");
		hql+=StringUtil.isBlankOrNull(wo.getUserInfo().getAccountname())?"":" o.userInfo.accountname=? and";
		hql+=isEmpty(String.valueOf(wo.getUserInfo().getLvlOne().getId()))?"":" o.userInfo.lvlOne.id=? and";
		//hql+=isEmpty(String.valueOf(wo.getUserInfo().getGame().getId()))?"":" o.userInfo.game.id=? and";
		hql+=isEmpty(String.valueOf(wo.getUserInfo().getServer().getId()))?"":" o.userInfo.server.id=? and";
		hql+=isEmpty(wo.getStates())?"":" o.states=? and";
		hql+=isEmpty(wo.getSource())?"":" o.source=? and";
		hql+=isEmpty(String.valueOf(wo.getId()))?"":" o.id=? and";
		if(roleId > RoleLevel.GM_ROLE){
			hql+=isEmpty(String.valueOf(wo.getGroup().getId()))?"":" o.tobeGroup.id=? and";
		}else{
			hql+=isEmpty(String.valueOf(wo.getGroup().getId()))?"":" o.group.id=? and";
		}
		hql+=(wo.getAssign()==null?"":" upper(wo.assign.from) like ?  and");

		LOGGER.debug("HQL完毕！--"+hql);
		return hql.equals("")?null:hql;
	}
	
	private Object[] createParams(WorkOrder wo,Date startTime,Date endTime){

		List<Object> list=new ArrayList<Object>();
		list.add(StringUtil.isBlankOrNull(wo.getCreateWorker())?null:wo.getCreateWorker());
		list.add(startTime==null?null:startTime);
		list.add(endTime==null?null:endTime);
		list.add(StringUtil.isBlankOrNull(wo.getUserInfo().getAccountname())?null:wo.getUserInfo().getAccountname());
		list.add(isEmpty(String.valueOf(wo.getUserInfo().getLvlOne().getId()))?null:wo.getUserInfo().getLvlOne().getId());
		//list.add(isEmpty(String.valueOf(wo.getUserInfo().getGame().getId()))?null:wo.getUserInfo().getGame().getId());
		list.add(isEmpty(String.valueOf(wo.getUserInfo().getServer().getId()))?null:wo.getUserInfo().getServer().getId());
		list.add(isEmpty(wo.getStates())?null:wo.getStates());
		list.add(isEmpty(wo.getSource())?null:wo.getSource());
		list.add(isEmpty(String.valueOf(wo.getId()))?null:wo.getId());
		
		list.add(isEmpty(String.valueOf(wo.getGroup().getId()))?null:wo.getGroup().getId());
		list.add(wo.getAssign()==null?null:"%"+wo.getAssign().getFrom().toUpperCase()+"%");
		
		List<Object> list1=new ArrayList<Object>();
		for(Object o:list){
			if(o!=null){
				list1.add(o);
			}
		}
		Object[] para=new Object[list1.size()];
		for(int i=0;i<para.length;i++){
			para[i]=list1.get(i);
		}
		//System.out.println(para.length);
		LOGGER.debug("Params完毕！--"+list);
		return para.length==0?null:para;
	}
	private boolean isEmpty(String str){
		if(StringUtil.isBlankOrNull(str)||"".equals(str)||"0".equals(str)){
			return true;
		}
		return false;
	}
	/**
	 * 本组工单广场当前处理情况
	 */
	public CountResult getCountResult(int groupId) {
		int countDealing=0;
		int countLackInfo=0;
		int countAssigning=0;
		int countAssigned=0;
		int countDealed=0;
		String hql="select count(o) from WorkOrder o where o.group.id=? and o.states=? and o.isdelete=false";
		Query query=getSession().createQuery(hql);
		//处理中数量
		query.setParameter(0, groupId).setParameter(1, DEALING);
		countDealing=convertQueryResult(query.uniqueResult());
		
		//已处理数量
		query.setParameter(0, groupId).setParameter(1, DEALED);
		countDealed=convertQueryResult(query.uniqueResult());
		
		query.setParameter(0, groupId).setParameter(1, LACKINFO);
		countLackInfo=convertQueryResult(query.uniqueResult());
		hql="select count(o) from WorkOrder o where o.group.id=? and o.states=? and o.assign.statu=? and o.isdelete =false";
		query=getSession().createQuery(hql);
		query.setParameter(0, groupId).setParameter(1, DEALING).setParameter(2, "ASSIGNING");
		countAssigning=convertQueryResult(query.uniqueResult());
		
		query.setParameter(0, groupId).setParameter(1, DEALING).setParameter(2, "ASSIGNED");
		countAssigned=convertQueryResult(query.uniqueResult());
		
		CountResult cr=new CountResult();
		cr.setCountDealing(countDealing);
		cr.setCountLackInfo(countLackInfo);
		cr.setCountAssigning(countAssigning);
		cr.setCountAssigned(countAssigned);
		cr.setCountDealed(countDealed);
		cr.setCountNoAssign(countDealing-countAssigned-countAssigning);
		return cr;
	}

	/************* 工单 按钮快捷查询 S**************/
	//CallCenter等回访
	public Pages<WorkOrder> searchWorkOrderIshf(Staff staff, int start,int len){
		//Staff staf=null;
//		if(staff.getAccName()==null){
//			staf=(Staff) getSession().get(Staff.class, staff.getId());
//		}else{
//			staf=staff;
//		}
		int roleLevel=staff.getRole().getLevel();
		int roleId=staff.getRole().getId();
		String hql = " o.ishf=? and o.states =?";
		Object[] param = new Object[]{true,RESPONSED};
		
		
			if(roleId==RoleLevel.GENARA_ROLE || roleId==RoleLevel.GM_ROLE){//if 普通客服
				hql += " and o.userInfo.vipGrade is null";
			}else if(roleId==RoleLevel.VIP_ROLE){//if vip客服
				hql += " and o.userInfo.vipGrade is not null";
			}
		
		hql+=" and o.isdelete=false";
		LOGGER.debug("hql="+hql);
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("o.userInfo.vipGrade", "asc");
		orderby.put("createTime", "asc");
		Pages<WorkOrder> pages=getPageData(start, len, hql, param, orderby);
		LOGGER.debug("查询完毕！");
		return pages;
	}
	/**
	 * 根据电话号码查工单
	 */
	public Pages<WorkOrder> searchWorkOrderByTel(String tel, int start,	int len) {
		LOGGER.debug("Enter search");
		String hql="";
		Object[] param=null;

		hql=" o.userInfo.tel = ?  and o.isdelete=false ";
		param=new Object[]{tel};
		
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("createTime", "asc");
		Pages<WorkOrder> pages=getPageData(start, len, hql, param, orderby);
		LOGGER.debug("查询完毕！");
		return pages;
	}
	/**
	 * vip投诉工单
	 */
	public Pages<WorkOrder> searchWorkOrderVipComp(Staff staff,
			int start, int len){
//		Staff staf=null;
//		if(staff.getAccName()==null){
//			staf=(Staff) getSession().get(Staff.class, staff.getId());
//		}else{
//			staf=staff;
//		}
	//	int roleLevel=staf.getRole().getLevel();
	//	int roleId=staf.getRole().getId();
		String hql=" o.userInfo.lvlOne.id=?";
		Object[] param = new Object[]{ClassCategory.VIP_BLESS};
		
		hql+=" and o.isdelete=false";
		LOGGER.debug("hql=" + hql);
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("o.userInfo.vipGrade", "asc");
		orderby.put("createTime", "asc");
		
		Pages<WorkOrder> pages=getPageData(start, len, hql, param, orderby);
		LOGGER.debug("查询完毕！");
		return pages;
	}
	//等待处理 belongTo 客服部 其他
	public Pages<WorkOrder> searchWorkOrderWaitForDeal(Staff staff,int start,int len){
		//Staff staf=(Staff) getSession().get(Staff.class, staff.getId());
		int roleLevel=staff.getRole().getLevel();
		int roleId=staff.getRole().getId();
		String hql="";
		Object[] param = null;
		
		if(roleLevel>0){
			if(roleId == RoleLevel.ZUZHANG_ROLE ){//if 客服组长
				hql=" ( (o.states=? and (o.assign.statu =? or  o.assign.statu is null) ) or o.states=?)";
				param=new Object[]{DEALING,"ASSIGNED",LACKINFO};
			}else{//if 客服主管 ADMIN
				hql="((o.states=? and (o.assign.statu =? or  o.assign.statu is null) ) or o.states=?)";
				param=new Object[]{DEALING,"ASSIGNBACK",LACKINFO};
			}
		}else{
			if(roleId==RoleLevel.GENARA_ROLE || roleId==RoleLevel.GM_ROLE){//if 普通客服
				hql=" o.states=? and  o.assign.statu is null and o.userInfo.vipGrade is null";
				param=new Object[]{DEALING};
			}else if(roleId==RoleLevel.VIP_ROLE){
				hql=" o.states=? and  o.assign.statu is null and o.userInfo.vipGrade is not null";
				param=new Object[]{DEALING};
			}
			else{//if 其他部门
				hql=" o.tobeGroup.id=? and (o.states=? or o.states=?)";
				param=new Object[]{staff.getGroup().getId(),DEALING,LACKINFO};
			}
		}
		hql+=" and o.isdelete=false"; 
		LOGGER.debug("hql="+hql);
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("userInfo.vipGrade", "asc");
		orderby.put("createTime", "asc");
		Pages<WorkOrder> pages=getPageData(start, len, hql, param, orderby);
		LOGGER.debug("查询完毕！");
		return pages;
	}
	/**
	 * 待补充资料
	 * 2012.08.08 增加vip客服处理情况
	 */
	public Pages<WorkOrder> searchWorkOrderLackInfo(Staff staff,
			int currentPage, int len) {
		//Staff staf = (Staff) getSession().get(Staff.class, staff.getId());
		int roleId = staff.getRole().getId();
		String hql=" o.states=?";
		Object[] param=new Object[]{LACKINFO};
		
		if(roleId==RoleLevel.GENARA_ROLE || roleId==RoleLevel.GM_ROLE){//if 普通客服
			hql += " and o.userInfo.vipGrade is null";
		}else if(roleId==RoleLevel.VIP_ROLE){//if vip客服
			hql += " and o.userInfo.vipGrade is not null";
		}
		
		hql+=" and o.isdelete=false";
		LOGGER.debug("hql="+hql);
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("userInfo.vipGrade", "asc");
		orderby.put("createTime", "asc");
		Pages<WorkOrder> pages=getPageData(currentPage, len, hql, param, orderby);
		return pages;
	}
	/**
	 * 派单跟踪  belongTo 客服部
	 * 2012.08.08 增加vip客服处理情况
	 */
	public Pages<WorkOrder> searchWorkOrderAssinged(Staff staff,int start,int len){
		//Staff staf = (Staff) getSession().get(Staff.class, staff.getId());
		int roleId = staff.getRole().getId();
		String hql = " o.states=?  and  o.assign is not null";
		Object[] param = new Object[]{DEALING};//2011.09.28 To Here
		
		if(roleId==RoleLevel.GENARA_ROLE || roleId==RoleLevel.GM_ROLE){//if 普通客服
			hql += " and o.userInfo.vipGrade is null";
		}else if(roleId==RoleLevel.VIP_ROLE){
			hql += " and o.userInfo.vipGrade is not null";
		}
		
		hql+=" and  o.isdelete=false";
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("userInfo.vipGrade", "asc");
		orderby.put("assign.statu", "asc");
		orderby.put("createTime", "asc");
		Pages<WorkOrder> pages=getPageData(start, len, hql, param, orderby);
		return pages;
	}
	//等待回访 belongTo  普通客服 客服组长 客服主管
	public Pages<WorkOrder> searchWorkOrderWaitForResponse(Staff staff,int start,int len){
		//Staff staf=(Staff) getSession().get(Staff.class, staff.getId());
		int roleId=staff.getRole().getId();
		String hql=" o.states=?";
		Object[] param =  new Object[]{DEALED};
		
		if(roleId==RoleLevel.GENARA_ROLE || roleId==RoleLevel.GM_ROLE){//if 普通客服
			hql += " and o.userInfo.vipGrade is null";
		}else if(roleId==RoleLevel.VIP_ROLE){
			hql += " and o.userInfo.vipGrade is not null";
		}
		
		hql+=" and o.isdelete=false";
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("userInfo.vipGrade", "asc");
		orderby.put("createTime", "asc");
		Pages<WorkOrder> pages=getPageData(start, len, hql, param, orderby);
		return pages;
	}
	
	//回访跟踪 belongTo 客服主管 客服组长
	public Pages<WorkOrder> searchWorkOrderResponsed(Staff staff,int start,int len){
	//	Staff staf=(Staff) getSession().get(Staff.class, staff.getId());

		int days=Integer.valueOf(Globe.getProperty("workOrderQeury/respond/ceilTime"));
		Date now=new Date();
		long gap=now.getTime()-days*24*60*60*1000;
		Date result=new Date(gap);
		int roleId=staff.getRole().getId();
		String hql="";
		Object[] param=null;
		
		if( roleId==RoleLevel.ADMIN_ROLE ){
			hql="o.states=? ";
			param=new Object[]{RESPONSED};
		}else if( roleId==RoleLevel.MANAGE_ROLE){
			hql="o.states=? and o.finishTime > ? ";
			param=new Object[]{RESPONSED,result};
		}else{
			hql=" o.states=? and o.finishTime > ? ";
			param=new Object[]{RESPONSED,result};
		}
		
		hql+=" and  o.isdelete=false";
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		Pages<WorkOrder> pages=getPageData(start, len, hql, param, orderby);
		return pages;
	}
	
	//技术GM 由我处理
	public Pages<WorkOrder> searchWorkOrderWaitForMe(Staff staff, int start,
			int len) {
		//Staff staf=(Staff) getSession().get(Staff.class, staff.getId());
		
		String hql="o.tobeGroup.id=? and o.states=? and o.assign is not null";
		Object[] param=new Object[]{staff.getGroup().getId(),DEALING};
		hql+=" and o.isdelete=false";
		
		Pages<WorkOrder> pages=getPageData(start, len, hql, param, getOrderBy());
		return pages;
	}
	
	private LinkedHashMap<String, String> getOrderBy(){
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("createTime", "asc");
		return orderby;
	}
	
	/**
	 * 其他 处理记录  两周之内
	 */
	public Pages<WorkOrder> searchWorkOrderAssignMe(Staff staff, int start,
			int len) {
		String hql="o.tobeGroup.id=? and o.isdelete=false";
		Object[] param=new Object[]{staff.getGroup().getId()};
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		Pages<WorkOrder> pages=getPageData(start, len, hql, param, orderby);
		return pages;
	}
	
	/***************查询账号近期提交的工单***************/
	public Pages<WorkOrderNearNow> searchWorkOrderNearNow(String accountname,
			int start, int len) {
		String hql=" o.userInfo.accountname=? and o.states=? and o.isdelete = false";
		Object[] param=new Object[]{accountname,RESPONSED};
		LOGGER.debug("获取近期处理工单：");
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("createTime","asc");
		Pages<WorkOrder> pages=getPageData(start, len, hql, param, orderby);
	//	LOGGER.debug("获取近期处理工单："+pages.getResultList());
		Pages<WorkOrderNearNow> nnPages=new Pages<WorkOrderNearNow>();
		nnPages.setPageSize(pages.getPageSize());
		nnPages.setTotalPage(pages.getTotalPage());
		nnPages.setResultList(buildNNReslutList(pages));
		return nnPages;
	}
	
	private List<WorkOrderNearNow> buildNNReslutList(Pages<WorkOrder> pages){
		List<WorkOrder> woList=pages.getResultList();
		List<WorkOrderNearNow> nnList=new ArrayList<WorkOrderNearNow>();
		for(WorkOrder wo:woList){
			WorkOrderNearNow woNN=new WorkOrderNearNow();
			woNN.setId(wo.getId());
			woNN.setAccName(wo.getUserInfo().getAccountname());
			woNN.setIssueType(wo.getUserInfo().getLvlOne().getName());
			woNN.setOperTime(wo.getFinishTime());
			woNN.setReply(wo.getReply());
			nnList.add(woNN);
			woNN=null;
		}
		return nnList;
	}
	/**********----------操作工单 S----------**********/
	//处理
	public WorkOrder dealWorkOrder(Staff staff, long woId, String content,String extraCondition) {
		WorkOrder wo=(WorkOrder) getSession().get(WorkOrder.class, woId);
		if(wo.getStates().equals(DEALED) || wo.getStates().equals(RESPONSED)){
			return null;
		}
		String state="";
		if("w".equals(extraCondition)){
			state=DEALED;
			content="处理完毕 |"+content;
		}else{
			content="未完毕 |"+content;
			state=DEALING;
		}
		operateWorkOrder(wo,content, staff, OperTypeId.DEAL, state,null,null);
		return wo;
	}
	
	//回复
	public WorkOrder replyWorkOrder(Staff staff, long woId, String content,String extraCondition) throws WoCenterException {
		WorkOrder wo=(WorkOrder) getSession().get(WorkOrder.class, woId);
		if(wo.getStates().equals(RESPONSED)){
			return null;
		}
		if(wo.getStates().equals(DEALING)&&(wo.getAssign()!=null&&wo.getAssign().getStatu().equals("ASSIGNING"))){
			throw new WoCenterException("", "该工单正在派单中，无法进行回复操作");
		}
		String state="";
		if("d".equals(extraCondition)){
			state=DEALING;
			content="待处理 |"+content;
			wo.setInnerMark(1);
		}else if("b".equals(extraCondition)){
			state=LACKINFO;
			content="补充资料 |"+content;
		}else{
			state=RESPONSED;
			content="结案 |"+content;
		}
		operateWorkOrder(wo,content, staff, OperTypeId.REPLY, state,null,null);
		
		//工单解锁
		if(Globe.getProperty("monitorLock/switch").equals("true")){
			String id=String.valueOf(wo.getId());
			if(staff.getRole().getLevel()<1){
					if(JCSManager.getInstance().get(id)!=null){
							JCSManager.getInstance().remove(id);
					}
			}
		}
		return wo;
	}
	
	//回访
	public WorkOrder respnseWorkOrder(Staff staff,long woId,String content){
		WorkOrder wo=(WorkOrder) getSession().get(WorkOrder.class, woId);
		if(wo.getStates().equals(RESPONSED)){
			operateWorkOrder(wo,content, staff, OperTypeId.RESPONSE, RESPONSED,null,null);
			return wo;
		}
		return null;
	}
	
	//删除
	public String deleteWorkOrder(Staff staff,long woId,String content){
		WorkOrder wo=(WorkOrder) getSession().get(WorkOrder.class, woId);
		operateWorkOrder(wo, content, staff, OperTypeId.DELETE, null,null,null);
		return null;
	}
	
	//派转
	public NameValuePair assignWorkOrder(Staff staff,Role role,long woId,String content,
																					Integer toGroupId,Long toStaffId){
		WorkOrder wo=(WorkOrder) getSession().get(WorkOrder.class, woId);
		String statu=wo.getAssign()==null?null:wo.getAssign().getStatu();
		
		LOGGER.debug(wo.getStates().equals(DEALING)&&statu!=null&&statu.equals("ASSIGNING"));
		if(role.getId()==GENARA_ROLE){
			if(wo.getStates().equals(DEALING)&&(statu==null||statu.equals("ASSIGNED"))){
				//LOGGER.debug("---Pass");
				int gn=operateWorkOrder(wo, content, staff, OperTypeId.ASSIGN, DEALING,toGroupId,toStaffId);
				return new NameValuePair(String.valueOf(wo.getId()),String.valueOf(gn));
			}else{
				return null;
			}
		}else if(role.getId()==CHP_ROLE||role.getId()==YW_ROLE||role.getId()==OTHER_ROLE){
			if(wo.getStates().equals(DEALING)&&statu.equals("ASSIGNING")){
				//LOGGER.debug("---Pass");
				int gn=operateWorkOrder(wo, content, staff, OperTypeId.ASSIGN, DEALING,toGroupId,toStaffId);
				return new NameValuePair(String.valueOf(wo.getId()),String.valueOf(gn));
			}else{
				return null;
			}
		}
		else{
			if(wo.getStates().equals(DEALING)){
				int gn=operateWorkOrder(wo, content, staff, OperTypeId.ASSIGN, DEALING,toGroupId,toStaffId);
				return new NameValuePair(String.valueOf(wo.getId()),String.valueOf(gn));
			}else{
				return null;
			}
			
		}
	}
	

	private int operateWorkOrder(WorkOrder wo,String content,Staff staff,
																		int operT,String state,Integer toGroupId,Long toStaffId){
		LOGGER.debug("start to operate=");
		int groupName = toGroupId == null?0:toGroupId;
		Session s=getSession();
		
		Staff staf=(Staff) s.get(Staff.class, staff.getId());
		int roleId=staf.getRole().getId();
		//if 回复
		if(operT==OperTypeId.REPLY){
			wo.setIsreaded(false);
			wo.setWorker(staff);
		}
		if(operT==OperTypeId.RESPONSE){
			wo.setIshf(false);
		}
		//if 处理
		if(operT==3){
			wo.setWorker(staff);//处理人
			if((roleId==5||roleId==6||roleId==7||roleId==8)&&state.equals(DEALING)){
				wo.getAssign().setStatu("ASSIGNED");
			}
		} 
		if(operT==1&&state.equals(LACKINFO)){//如果发回，清空派转信息
			wo.setTobeGroup(null);//MODIFY @ 2011.09.22
			wo.setAssign(null);//MODIFY @2011.10.17
		}
		//if 派转
		Assign assign=null;
		if(toGroupId!=null){//设置派转人
			assign=new Assign();//派转信息设置
			if(toStaffId!=0){//if 选择员工
				Staff sta=(Staff) getSession().get(Staff.class, toStaffId);
				wo.setTobeGroup(sta.getGroup());
				assign.setFrom(staf.getGroup().getName()+"滴"+staf.getAccName());
				assign.setTo(sta.getGroup().getName()+"滴"+sta.getNickName());
				assign.setStatu("ASSIGNING");
				
				//groupName=staf.getGroup().getId();
			}else{//没有选择员工
				Group tobe=(Group) s.get(Group.class, toGroupId);
				wo.setTobeGroup(tobe);
				LOGGER.debug("Assign to ="+tobe.getName());
				//Assign assign=new Assign();//派转信息设置
				assign.setFrom(staf.getAccName());
				assign.setTo(tobe.getName());
				assign.setStatu("ASSIGNING");
				
				//groupName=tobe.getId();
			}
			wo.setAssign(assign);
			LOGGER.debug("Assign finished!");
		}
		LOGGER.debug("in operate=");
		OperType operType=null;
		//操作类型记录表
			WorkOrderOper oper=new WorkOrderOper();
			operType= (OperType) s.get(OperType.class, (Serializable) operT);
			oper.setOperType(operType);
			oper.setWorker(staf.getNickName());
			oper.setWorkerParent(staf.getDepartment().getName());
			oper.setWorkOrder(wo);
			oper.setOperTime(new Date());//设置操作时间
			if(assign!=null){
				content=assign.getFrom()+"派单到"+assign.getTo()+" | "+content;
			}
			oper.setContent(content);
			if(operT == OperTypeId.REPLY || operT == OperTypeId.RESPONSE){
					wo.setReply(content);
			}
			wo.getOpers().add(oper);
			LOGGER.debug("操作类型="+operType.getName());
			//LOGGER.debug("what is wrong?before  setfinishitime");
		if(state!=null&&state.equals(RESPONSED)){
			wo.setFinishTime(new Date());
		}
		if(state==null){
			wo.setIsdelete(true);//设置删除状态
			update(wo);
			return -1;
		}
		wo.setStates(state);//设置处理状态
		update(wo);
		return groupName;
	}
	
	//退单
	public void backWorkorder(Staff staff,long woId,String content){
		Session s=getSession();
		Staff staf=(Staff) s.get(Staff.class, staff.getId());
		WorkOrder wo=(WorkOrder) s.get(WorkOrder.class, woId);
		OperType operType=null;
		//操作类型记录表
			WorkOrderOper oper=new WorkOrderOper();
			operType= (OperType) s.get(OperType.class, (Serializable) OperTypeId.SENDBACK);
			oper.setOperType(operType);
			oper.setWorker(staf.getNickName());
			oper.setWorkerParent(staf.getDepartment().getName());
			oper.setWorkOrder(wo);
			oper.setOperTime(new Date());//设置操作时间
			
			oper.setContent(content);
			wo.setGroup(null);
			wo.setGetWorker(null);
			wo.setGetState(false);
			wo.getOpers().add(oper);
	}
	
	//内部确认
	public String returnWorkOrder(Staff staff,long woId,String content){
		WorkOrder wo=(WorkOrder) getSession().get(WorkOrder.class, woId);
		wo.setInnerMark(1);
		
		return null;
	}
	
	//改变电话回访状态
	public String alterIshf(Staff staff,long woId,String content){
		WorkOrder wo=(WorkOrder) getSession().get(WorkOrder.class, woId);
		if(wo.isIshf()){
			wo.setIshf(false);
		}else{
			wo.setIshf(true);
		}
		
		return null;
	}
	
	//改变工单处理状态
	public String alterState(Staff staff,long woId,String content,String reason){
		Staff staf=(Staff) getSession().get(Staff.class, staff.getId());
		if(staf.getRole().getLevel() < RoleLevel.ADMIN_ROLE){
			return "您的权限不够";
		}
		WorkOrder wo=(WorkOrder) getSession().get(WorkOrder.class, woId);
		wo.setStates(content);
		WorkOrderOper oper=new WorkOrderOper();
		oper.setOperType((OperType) getSession().get(OperType.class, OperTypeId.ALTER_STATE));
		oper.setContent(reason);
		oper.setOperTime(new Date());
		oper.setWorker(staf.getNickName());
		oper.setWorkerParent(staf.getDepartment().getName());
		oper.setWorkOrder(wo);
		wo.getOpers().add(oper);
		return "操作成功！";
	}

	public int getWorkOrderTotalByGetState(boolean getState) {
		Query query=sessionFactory.getCurrentSession().createQuery("from WorkOrder wo where wo.getState =?");
		query.setParameter(0, getState);
		List<WorkOrder> list = query.list();
		return list.size()> 0?list.size():0;
	}

	/**
	 * 根据工单ID回复工单
	 * @param id 工单ID
	 * @param comment 回复内容
	 */
	public void replyWorkOrderById(Staff staff,long id, String comment){
		LOGGER.debug("*************根据工单ID回复工单*********");
		WorkOrder workOrder=get(id);
		WorkOrderOper oper=new WorkOrderOper();
		OperType operType=(OperType) sessionFactory.getCurrentSession().get(OperType.class, 1);
		oper.setContent(comment);
		oper.setOperTime(new Date());
		oper.setWorker(staff.getAccName());
		oper.setWorkerParent(staff.getDepartment().getName());
		oper.setOperType(operType);
		oper.setWorkOrder(workOrder);
		
		workOrder.getOpers().add(oper);
		update(workOrder);
	}
	
	//派单操作选择小组
	public Map<Integer, String> initGroupMap(Staff staff) {
		Map<Integer, String> groupMap=new HashMap<Integer, String>();
		Staff staf=(Staff) getSession().get(Staff.class, staff.getId());
		int level=staf.getRole().getLevel();
		int roleId=staf.getRole().getId();
		String hql="";
		Query query=null;
		if(level > RoleLevel.ADMIN_ROLE){
			hql = "from Group o where o.departId!=1";
			query = getSession().createQuery(hql);
		}else if(roleId==GENARA_ROLE){
			hql="select o from Group o where o.departId = ?  and o.departId!=1";
			query=getSession().createQuery(hql).setParameter(0,CTDEPARTMENT);
		}else if(roleId==ZUZHANG_ROLE || roleId==GM_ROLE){
			hql="select o from Group o where o.departId !=?  and o.departId!=1";
			query=getSession().createQuery(hql).setParameter(0,CTDEPARTMENT);
		}else if(roleId==CHP_ROLE||roleId==YW_ROLE||roleId==OTHER_ROLE){
			hql="select o from Group o where o.departId !=? and o.departId!=? and o.departId!=1";
			query=getSession().createQuery(hql).setParameter(0,CTDEPARTMENT)
																					.setParameter(1, staf.getDepartment().getId());
		}
		List<Group> groupList=query.list();
		
		for(Group group:groupList){
			groupMap.put(group.getId(), group.getName());
		}
		if(roleId==ZUZHANG_ROLE||roleId==GENARA_ROLE){
			groupMap.put(staf.getGroup().getId(), staf.getGroup().getName());
		}
		return groupMap;
	}
	
	//选择员工
	public JSONObject getStaffOfGroup(int groupId){
		JSONObject  jobj=new JSONObject();
		List<AreaJson> staffList=new ArrayList<AreaJson>();
		Group group=(Group) getSession().get(Group.class, groupId);
		
		String hql="select o from Staff o where o.group.id=? and o.isdelete=false" ;
		Query query=getSession().createQuery(hql);
		List<Staff> list=query.setParameter(0, groupId).list();
		if(list.size()>0){
			if(group.getDepartId() == DepartmentId.CTDEPARTMENT){//if 派单到客服
				
				for(Staff staff:list){
					if(staff.getRole().getId() == RoleLevel.GM_ROLE || 
						staff.getRole().getId() == RoleLevel.ZUZHANG_ROLE){
						AreaJson aj=new AreaJson();
						aj.setId(Integer.parseInt(String.valueOf(staff.getId())));
						aj.setName(staff.getNickName());
						//
						aj.setAccName(staff.getAccName());
						staffList.add(aj);
						aj=null;
					}
				}
			}else{
				
				for(Staff staff:list){
					AreaJson aj=new AreaJson();
						aj.setId(Integer.parseInt(String.valueOf(staff.getId())));
						aj.setName(staff.getNickName());
						//
						aj.setAccName(staff.getAccName());
						staffList.add(aj);
						aj=null;
				}
			}
			if(staffList.size() > 0){
				jobj.element("staffList", staffList);
			}else{
				jobj.element("empty", "该小组无可派单成员~");
			}
			return jobj;
		}else{
			jobj.element("empty", "该小组无成员~");
			return jobj;
		}
	}
	
	//选择RTX员工
	public String getRTXStaff(int groupId, Staff staf){
		
		//List<AreaJson> staffList=new ArrayList<AreaJson>();
		Group group=(Group) getSession().get(Group.class, groupId);
		String result=""; 	
		try{
		String hql="select o from Staff o where o.group.id=? and o.isdelete=false" ;
		Query query=getSession().createQuery(hql);
		
		List<Staff> list=query.setParameter(0, groupId).list();
		LOGGER.debug("小组员工数量：" + list.size());
		if(list.size()>0){
			if(group.getDepartId() == DepartmentId.CTDEPARTMENT){//if 派单到客服
				
				for(Staff staff:list){
					if(staff.getRole().getId() == RoleLevel.GM_ROLE || 
					   staff.getRole().getId() == RoleLevel.ZUZHANG_ROLE){
						result += staff.getAccName();
						result += ",";
					}
				}
				
			}else{
				
				for(Staff staff:list){
					result += staff.getAccName();
					result += ",";
				}
			}
		}
		if(staf != null){
			result += staf.getAccName();
		}
		else{
			result = result.substring(0, result.length()-1);
		}
		}catch(Exception e){e.printStackTrace();}
		return result;
	}
	
	/**
	 * 工单数据统计
	 */
	public Pages<WorkOrder> countWorkOrder(WorkOrder workOrder, int start,int len,
			Date startTime,Date endTime) {
		LOGGER.debug("工单数据统计");
			Pages<WorkOrder> pages=null;
			LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
			
			if(workOrder.getCreateWorker()!=null){
				orderby.put("o.createWorker", "desc");
				
				orderby.put("createTime", "desc");
			}else if(workOrder.getUserInfo()!=null&&workOrder.getUserInfo().getLvlOne()!=null){
				orderby.put("o.userInfo.lvlOne.id", "desc");
				orderby.put("createTime", "desc");
			}
			else{
				orderby.put("createTime", "desc");
			}
			String hql="";
			Object[] param=null;
			hql=createCountHQL(workOrder,startTime,endTime);
			hql=(hql==null?" o.isdelete=false ":hql+" o.isdelete=false");
			LOGGER.debug("---"+hql);
			param=createCountParams(workOrder,startTime,endTime);
			pages=getPageData(start, len, hql, param, orderby);
			LOGGER.debug("工单数据统计完毕：all="+pages.getTotalCount());
			return pages;
	}
	
	private String createCountHQL(WorkOrder wo,Date startTime,Date endTime){
		String hql="";
		hql+=StringUtil.isBlankOrNull(wo.getCreateWorker())?"":" o.createWorker=? and";
		hql+=(startTime==null?"":" o.createTime>=? and o.createTime<=? and");
		if(wo.getUserInfo()!=null&&wo.getUserInfo().getLvlOne()!=null)
			hql+=isEmpty(String.valueOf(wo.getUserInfo().getLvlOne().getId()))?"":" o.userInfo.lvlOne.id=? and";
		if(wo.getGroup()!=null)
			hql+=isEmpty(String.valueOf(wo.getGroup().getId()))?"":" o.group.id=? and";
		if(wo.getAssign()!=null)
			hql+=isEmpty(wo.getAssign().getFrom())?"":" upper(o.assign.from) like ?  and";
		
		LOGGER.debug("HQL完毕！--"+hql);
//		if(!hql.equals(""))
//			hql = hql.substring(0,hql.indexOf("and"));
		return hql.equals("")?null:hql;
	}
	
	private Object[] createCountParams(WorkOrder wo,Date startTime,Date endTime){
		List<Object> list=new ArrayList<Object>();
		list.add(StringUtil.isBlankOrNull(wo.getCreateWorker())?null:wo.getCreateWorker());
		list.add(startTime==null?null:startTime);
		if(startTime!=null)
			list.add(endTime==null?new Date():endTime);
		if(wo.getUserInfo()!=null&&wo.getUserInfo().getLvlOne()!=null)
			list.add(isEmpty(String.valueOf(wo.getUserInfo().getLvlOne().getId()))?null:wo.getUserInfo().getLvlOne().getId());
		if(wo.getGroup()!=null)
			list.add(isEmpty(String.valueOf(wo.getGroup().getId()))?null:wo.getGroup().getId());
		if(wo.getAssign()!=null)
			list.add(isEmpty(wo.getAssign().getFrom())?null:"%"+wo.getAssign().getFrom().toUpperCase()+"%");
		
		List<Object> list1=new ArrayList<Object>();
		for(Object o:list){
			if(o!=null){
				list1.add(o);
			}
		}
		Object[] para=new Object[list1.size()];
		for(int i=0;i<para.length;i++){
			para[i]=list1.get(i);
		}
		LOGGER.debug("Params完毕！--"+Arrays.asList(para));
		return para.length==0?null:para;
	}
	
	public List<WorkOrderOper> getCountByResponsed(String accName,Date startTime,Date endTime,int oper){
		LOGGER.debug("工单数据统计  回访人");
		
		String str=makeHqlString(accName, startTime, endTime, oper);
		String hql="select o from WorkOrderOper o "+(str.equals("")?"":"where "+str +" order by o.worker desc,o.operTime desc");
		Object[] param=makeHqlParam(accName, startTime, endTime, oper);
		Query query=getSession().createQuery(hql);
		
		matchHqlParam(query,param);
		List<WorkOrderOper> list2 = query.list();
		
		LOGGER.debug("工单数据统计完毕：all="+list2.size());
		return list2;
	}	private void matchHqlParam(Query query,Object[] param) {
		if(param!=null&&param.length>0)
		for(int i=0;i<param.length;i++){
			if(param[i]!=null)
				query.setParameter(i, param[i]);
		}
	}

	private String makeHqlString(String accName,Date startTime,Date endTime,int oper){
		String hql="";
		hql+=isEmpty(accName)?"":"o.worker=? and ";
		hql+=(startTime==null?"":" o.operTime>=? and o.operTime<=? and");
		hql+=(oper==0?"":" o.operType.id=?");
		return hql;
	}
	
	private Object[] makeHqlParam(String accName,Date startTime,Date endTime,int oper){
		List<Object> list=new ArrayList<Object>();
		list.add(StringUtil.isBlankOrNull(accName)?null:accName);
		list.add(startTime==null?null:startTime);
		if(startTime!=null)
			list.add(endTime==null?new Date():endTime);
		list.add(oper==0?null:Integer.valueOf(oper));
		
		List<Object> list1=new ArrayList<Object>();
		for(Object o:list){
			if(o!=null){
				list1.add(o);
			}
		}
		Object[] para=new Object[list1.size()];
		for(int i=0;i<para.length;i++){
			para[i]=list1.get(i);
		}
		LOGGER.debug("Params完毕！--"+Arrays.asList(para));
		return para.length==0?null:para;
	}
	
	/**
	 * 根据问题类型分类统计
	 */
	public List countWorkOrderByType(WorkOrder workOrder,Date startTime,Date endTime) {
		LOGGER.debug("工单数据统计 countWorkOrderByType");
			String str=createCountHQL(workOrder, startTime, endTime);
			String hql="select u.lvlOne.name, count(u.lvlOne.id) from WorkOrder o join o.userInfo u where"+
										(str==null?"":str)+" o.isdelete = false  group by u.lvlOne.id ";
			LOGGER.debug("---"+hql);
			Object[] param=createCountParams(workOrder, startTime, endTime);
			Query query=getSession().createQuery(hql);
			matchHqlParam(query, param);
			
			List list=query.list();
			LOGGER.debug("工单数据统计完毕：all="+list.size());
			return list;
	}
	
	/**
	 * 在预期时间内完成的工单数量
	 */
	public List  countFinishRate(WorkOrder workOrder,Date startTime,Date endTime){
			LOGGER.debug("完成率统计");
			String str=createCountHQL(workOrder, startTime, endTime);
			String hql="select o from WorkOrder o where o.prefinishTime is not null and "
								+"o.finishTime is not null and o.finishTime < o.prefinishTime and "+(str==null?"":str)+" o.isdelete = false";
			LOGGER.debug("---"+hql);
			Object[] param=createCountParams(workOrder, startTime, endTime);
			Query query=getSession().createQuery(hql);
			matchHqlParam(query, param);
			List list=query.list();
			LOGGER.debug("完成率统计完毕：all="+list.size());
			return list;
	}
	/**
	 * 有预期完成时间的工单数量
	 */
	public List havingFinishTime(WorkOrder workOrder,Date startTime,Date endTime){
			LOGGER.debug("完成率统计");
			String str=createCountHQL(workOrder, startTime, endTime);
			String hql="select o from WorkOrder o where o.prefinishTime is not null and "+(str==null?"":str)+" o.isdelete = false";
			LOGGER.debug("---"+hql);
			Object[] param=createCountParams(workOrder, startTime, endTime);
			Query query=getSession().createQuery(hql);
			matchHqlParam(query, param);
			List list=query.list();
			LOGGER.debug("完成率统计完毕：all="+list.size());
			return list;
	}
	/**
	 * 关键字统计工单
	 */
	public List  countWorkOrderByKeyWord(String keyWord,Date startTime,Date endTime){
		LOGGER.debug("关键字统计");
		String hql="select o from WorkOrder o where upper(o.userInfo.memo) like ? and " +
				""+(startTime==null?"":" o.createTime>=? and o.createTime<=? and")+" o.isdelete = false";
		LOGGER.debug("---"+hql);
		Query query=getSession().createQuery(hql);
		query.setParameter(0, "%"+keyWord.toUpperCase()+"%");
		if(startTime!=null){
			query.setParameter(1, startTime);
			query.setParameter(2, endTime==null?new Date():endTime);
		}
		List list=query.list();
		LOGGER.debug("关键字统计完毕：all="+list.size());
		return list;
}
	public List countWorkOrderBySatisfaction(WorkOrder workOrder,Date startTime,Date endTime){
		LOGGER.debug("满意度统计");
		String str=createCountHQL(workOrder, startTime, endTime);
		String hql="select date(o.createTime),w.nickName,o.evaluation,count(o.evaluation) " +
		"from WorkOrder o join o.worker w " +
		"where  o.evaluation is not null and "+(str==null?"":str)+" o.isdelete = false  "+
		"group by date(o.createTime),w.nickName,o.evaluation";
		Object[] param=createCountParams(workOrder, startTime, endTime);
		Query query=getSession().createQuery(hql);
		matchHqlParam(query, param);
		List list=query.list();
		LOGGER.debug("满意度统计完毕：all="+list.size());
		return list;
	}
	
	public void removeCacheDataByKey(String cacheName, String cacheKey) {
		String hql=" SELECT DATE(woo.oper_time) AS operDate,"+
			"woo.worker ,"+
			"oot.type_name,"+
			"COUNT(woo.id) AS operNum"+
			"FROM WorkOrderOper woo"+
			"INNER JOIN OrderOperType oot ON oot.id=woo.oper_id"+
			"WHERE woo.worker=?"+
			"GROUP BY  DATE(woo.oper_time),woo.worker,woo.oper_id";
		
		String h="select o.operTime,o.worker,count(o.id) " +
				"from WorkOrderOper" +
				"where o.content like ? " +
				"group by o.worker";
	}
	
	/**
	 * 统计客服员工每天操作数量
	 */
	public List<WorkOrderOper> getCountByAccName(String accName,Date startTime,Date endTime){
		LOGGER.debug("工单数据统计  每天每人");
		
		String str=makeHqlString(accName, startTime, endTime,0);
		String hql=" select o.operTime, "+
		"o.worker, "+
		"o.operType.name, "+
		"count(o.id) "+
		"from WorkOrderOper o "+
		(str.equals("")?"":" where "+str.substring(0,str.lastIndexOf("and")) )+
		" group by o.worker,o.operType.id";
		
		Object[] param=makeHqlParam(accName, startTime, endTime,0);
		Query query=null;
		
		List list2 =null;
		try{
			query=getSession().createQuery(hql);
			matchHqlParam(query,param);
			list2 = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		LOGGER.debug("工单数据统计完毕：all="+list2.size());
		return list2;
	}
	
	/**
	 * 统计客服员工每天回复数量
	 */
	public List<WorkOrderOper> getCountByAccNameReply(String accName,Date startTime,Date endTime){
		LOGGER.debug("工单数据统计  每天每人");
		
		String str=makeHqlString("", startTime, endTime,0);
		String hql="select  date(o.operTime),o.worker,count(o.worker) " +
		"from WorkOrderOper o " +
		"where "+str+" upper(o.content) like ? and operType.id=1 "+
		"group by date(o.operTime),o.worker";
			
		Object[] param=makeHqlParam(accName, startTime, endTime,0);
		if(param==null) param=new Object[]{};
		param=MyUtil.arrayGrow(param, "%结案 |%");
		Query query=null;
		
		List list2 =null;
		try{
			query=getSession().createQuery(hql);
			
			matchHqlParam(query,param);
			list2 = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		LOGGER.debug("工单数据统计完毕：all="+list2.size());
		return list2;
	}
	
	/**
	 * 统计其他 员工每天处理数量
	 */
	public List<WorkOrderOper> getCountByAccNameDeal(String accName,String parent,Date startTime,Date endTime){
		LOGGER.debug("工单数据统计  每天每人");
		
		String str=makeHqlString("", startTime, endTime,0);

		String hql="select o.operTime,o.worker,count(o.id) " +
		"from WorkOrderOper o " +
		"where "+str+" upper(o.content) like ? and o.workerParent=?"+
		"group by o.worker";

		Object[] param=makeHqlParam(accName, startTime, endTime,0);
		if(param==null) param=new Object[]{};
		param=MyUtil.arrayGrow(param, "%处理完毕%");
		param=MyUtil.arrayGrow(param, parent);
		Query query=null;
		
		List<WorkOrderOper> list2 =null;
		try{
			query=getSession().createQuery(hql);
			
			matchHqlParam(query,param);
			list2 = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		LOGGER.debug("工单数据统计完毕：all="+list2.size());
		return list2;
	}
	
	public List<BatchData> getBatchTypeList(String cacheName) {
		return null;
	}

	public List<BatchData> getCacheDataByBatchType(String cacheName,
			String batchType) {
		return null;
	}
	
	public static void main(String...strings){
	}

	

	
}
