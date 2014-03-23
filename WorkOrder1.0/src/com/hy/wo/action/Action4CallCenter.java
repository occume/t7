package com.hy.wo.action;

import static com.hy.wo.util.Constants.SessionKey.DEPARTMENT;
import static com.hy.wo.util.Constants.SessionKey.GROUP;
import static com.hy.wo.util.Constants.SessionKey.ROLE;
import static com.hy.wo.util.Constants.SessionKey.STAFF;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hy.wo.cache.RateJCSManager;
import com.hy.wo.dao.AsistDao;
import com.hy.wo.exception.WoCenterException;
import com.hy.wo.po.Caller;
import com.hy.wo.po.Games;
import com.hy.wo.po.ServerInfo;
import com.hy.wo.po.Servers;
import com.hy.wo.po.Staff;
import com.hy.wo.po.SubCallBack;
import com.hy.wo.po.WorkOrder;
import com.hy.wo.util.CheckParamManager;
import com.hy.wo.util.Constants;
import com.hy.wo.util.DepictXmlManager;
import com.hy.wo.util.MyUtil;
import com.hy.wo.util.Pages;
import com.hy.wo.util.Constants.ParamName;
import com.hy.wo.util.Constants.PromptMessage;
import com.hy.wo.util.Constants.StateList;
import com.hy.wo.util.Constants.States;

@Controller @Scope("prototype")
public class Action4CallCenter extends BaseAction {
	/**
	 * CallCenter登录
	 */
	public void login(){
		LOG.debug("A Request From CallCenter");
		Staff staf=null;
		try {
			CheckParamManager.checkCallCenterParam(request);
			staf=staffService.logon(new Staff(username,password));
			if(staf == null ){
				setState(StateList.PASSWORD_ERR, PromptMessage.PASSWORD_ERROR);
			}
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}
		finally{
			DepictXmlManager.outputStr(
					DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
					DepictXmlManager.oneRowXML( staf == null ? "" : staf.getAccName() ),
					response);
		}
	}
	/**
	 * 获取玩家信息
	 */
	public void callerInfos(){
		LOG.debug("A Request From CallCenter (get_callerInfos)");
		List<Caller> list =null;
		try {
			CheckParamManager.getCallerInfoValidate(request);
			list=callerService.getCallerInfo(caller);
			if(list==null||list.size()<=0){
				setState(StateList.DATA_NOT_FOUND, PromptMessage.DATA_NOT_FOUND);
			}
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}finally{
			DepictXmlManager.outputStr(
					DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
					DepictXmlManager.makeCallerXml(list),
					response);
		}
	}
	/**
	 * 记录玩家信息
	 */
	public void callerInfo(){
		LOG.debug("A Request From CallCenter");
		try {
			CheckParamManager.saveCallerInfoValidate(request);
			LOG.debug("accoutname="+accountname);
			callerService.saveCallerInfo(new Caller(accountname,username,caller,usertype));
			LOG.debug("Save over");
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}finally{
			DepictXmlManager.outputStr(
					DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID)),
					response);
		}
	}
	/**
	 * 记录CallCenter工单
	 */
	public void saveWorkOrder(){
		LOG.debug("A Request From CallCenter  Request SaveWorkORder");
		try {
			CheckParamManager.getCallerInfoValidate(request);
			LOG.debug("gameId = " + gameId);
			int immediate=0;
			if(gid!=0){
				immediate=3;
			}
			WorkOrder wo=new WorkOrder(request);
			LOG.debug("wo = " + wo);
			LOG.debug("wo.userInfo.game = " + wo.getUserInfo().getGame());
			wo.getUserInfo().setHappendate(new Date());
			Games game = new Games();
			game.setId(Integer.valueOf(gameId));
			wo.getUserInfo().setGame(game);
			LOG.debug("game id = " + wo.getUserInfo().getGame().getId());
			if(Boolean.valueOf(willhf[0])){
				wo.setIshf(true);
			}
			if(Boolean.valueOf(isdhgd)){
				wo.setSource("CallCenter电");
				wo.setIsdhgd(true);
			}else{
				wo.setSource("CallCenter平");
			}
			/*** 如果没有该服务器信息，增加一条 ***/
			int serverId=wo.getUserInfo().getServer().getId();
			if(serverId>0){
				Servers server=woService.getServer(serverId);
				if(server==null){
					ServerInfo serverInfo=AsistDao.getServerInfo(serverId);
					woService.addServer(serverInfo.getServerId(), serverInfo.getAreaName()+"--"+serverInfo.getServerName());
				}
			}
			LOG.debug("go to save");
			/******/
			Staff staff=staffService.getStaffByAccName(accName);
			woService.createWorkOrder(wo,
																	staff,
																	immediate, gid, sid);
		//	DWRHelper.showScriptSession(session.getServletContext(), makeMap(wo,staff));
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}finally{
		DepictXmlManager.outputStr(
				DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID)),
				response);
		}
	}
	
	/**
	 * 用户评价
	 */
	public void rate(){
		LOG.debug("A Request From CallCenter  Request Rate");
		try {
			CheckParamManager.getCallerInfoValidate(request);
			
			RateJCSManager.getInstance().put(request.getParameter("callid"),
											request.getParameter("evalua"));
		}  catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}finally{
		DepictXmlManager.outputStr(
				DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID)),
				response);
		}
	}
	/**
	 * 获取玩家历史工单信息
	 */
	public void workOrders(){
		Pages<WorkOrder> alldata=null;
		try {
			CheckParamManager.getCallerInfoValidate(request);
			alldata = woService.getAllWorkOrderOfUserByStatus(currentPage, len,accountname, States.DEALED);
			if(alldata.getResultList()==null||alldata.getResultList().size()<=0){
				setState(StateList.DATA_NOT_FOUND, PromptMessage.DATA_NOT_FOUND);
			}
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}finally{
		DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
				DepictXmlManager.makePagesXML(alldata, "all", alldata.getResultList().size()), response);
		}
	}
	/**
	 * 根据ID查找工单
	 */
	public void workOrderById(){
		Pages<WorkOrder> data=null;
		try {
			CheckParamManager.getCallerInfoValidate(request);
			WorkOrder workOrder=woService.getWorkOrderDetail(woId);
			if(workOrder==null){
				setState(StateList.DATA_NOT_FOUND, PromptMessage.DATA_NOT_FOUND);
			}else{
				data=new Pages<WorkOrder>();
				data.setCurrentPage(1);
				data.setLastPage(1);
				data.setPageSize(len);
				data.setTotalCount(1);
				data.setTotalPage(1);
				List<WorkOrder> list=new ArrayList<WorkOrder>();
				list.add(workOrder);
				data.setResultList(list);
			}
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}finally{
		DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
				DepictXmlManager.makePagesXML(data, "all", data==null?0:1), response);
		}
	}
	/**
	 * 需电话回访工单
	 */
	public void getWorkOrdersWillHF(){
		Pages<WorkOrder> data=null;
		try {
			CheckParamManager.getCallerInfoValidate(request);
			Staff staff=staffService.getStaffByAccName(accName);
			data=woPfService.searchWorkOrderIshf(staff, currentPage, len);
			if(data.getResultList()==null||data.getResultList().size()<=0){
				setState(StateList.DATA_NOT_FOUND, PromptMessage.DATA_NOT_FOUND);
				DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID)), response);
				return;
			}
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}//finally{
		DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
				DepictXmlManager.makePagesXML(data, "all", data==null?0:(data.getResultList()==null?0:data.getResultList().size())), response);
		//}
	}
	/**
	 * 根据电话号码查工单
	 */
	public void getWorkOrdersByTel(){
		Pages<WorkOrder> data=null;
		try {
			//CheckParamManager.getCallerInfoValidate(request);
			//Staff staff=staffService.getStaffByAccName(accName);
			String tel=request.getParameter("tel");
			LOG.debug("A Request From CallCenter |tel="+ tel);
			data=woPfService.searchWorkOrderByTel(tel, currentPage, len);
			if(data.getResultList()==null||data.getResultList().size()<=0){
				setState(StateList.DATA_NOT_FOUND, PromptMessage.DATA_NOT_FOUND);
				DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID)), response);
				return;
			}
		//} catch (WoCenterException e) {
		//	setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}//finally{
		DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
				DepictXmlManager.makePagesXML(data, "all", data==null?0:(data.getResultList()==null?0:data.getResultList().size())), response);
		//}
	}
	/**
	 * 获取问题类型信息
	 */
	public void issueTypes(){
		Map<Integer,String> map=null;
		try {
			CheckParamManager.getCallerInfoValidate(request);
			if(pid==Constants.ClassCategory.GAMEISSUE){
				map=(Map<Integer, String>) session.getAttribute("issue1_Map");
				if(map==null){
					map=woService.initIssueOneMap(pid);
					session.setAttribute("issue1_Map", map);
				}
			}else{
				map=woService.initIssueOneMap(pid);
			}
			
			if(map==null||map.size()<=0){
				setState(StateList.DATA_NOT_FOUND, PromptMessage.DATA_NOT_FOUND);
			}
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}finally{
		DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
				DepictXmlManager.makeMapXml(map), response);
		}
	}
	/**
	 * 获取服务器信息
	 */
	public void servers(){
		Map<String,String> map=null;
		try {
			//CheckParamManager.getCallerInfoValidate(request);
			map=(Map<String, String>) session.getAttribute("server_Map");
			if(map==null){
				map=callerService.makeServerMap();
				session.setAttribute("server_Map", map);
			}
			map.put("1", "630不删档测试-电信");
			map.put("2", "630不删档测试-网通");
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}finally{
		DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
				DepictXmlManager.makeMapXml(map), response);
		}
	}
	/**
	 * 获取派单小组
	 */
	public void assignGroups(){
		Map<Integer,String> map=null;
		try {
			CheckParamManager.getCallerInfoValidate(request);
			map=(Map<Integer, String>) session.getAttribute("assign_Map");
			if(map==null){
				map=staffService.getCallCenterGroups(accName);
				session.setAttribute("assign_Map", map);
			}
			
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}finally{
		DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
				DepictXmlManager.makeMapXml(map), response);
		}
	}
	/**
	 * 获取派单员工
	 */
	public void assignStaffs(){
		LOG.debug("A Request From CallCenter |assignStaffs >>gId="+gid );
		Map<Integer,String> map=null;
		try {
			CheckParamManager.getCallerInfoValidate(request);
			map=staffService.getCallCenterStaffs(gid);
			if(map==null||map.size()<=0){
				setState(StateList.DATA_NOT_FOUND, PromptMessage.DATA_NOT_FOUND);
			}
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}finally{
		DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
				DepictXmlManager.makeMapXml(map), response);
		}
	}
	/********** 回访 ***********/
	/**
	 * 导入回访清单
	 */
	public void callBacks(){
		long cbId=0;
		try {
			CheckParamManager.getCallerInfoValidate(request);
			cbId=callerService.saveCallBack(request);
			//System.out.println(cbId);
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}finally{
		DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
				DepictXmlManager.makeCallBackId(cbId), response);
		}
	}
	/**
	 * 获取历史导入清单
	 */
	@SuppressWarnings("unchecked")
	public void pastCallBacks(){
		List<Object[]> list=null;
		try {
			CheckParamManager.getCallerInfoValidate(request);
			list=callerService.getCallBacksByDate(fromDate, toDate);
			if(list==null||list.size()<=0){
				setState(StateList.DATA_NOT_FOUND, PromptMessage.DATA_NOT_FOUND);
			}
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}finally{
		DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
				DepictXmlManager.makeCallBackList(list), response);
		}
	}
	/**
	 * 获取回访记录清单
	 */
	public void subCallBackList(){
		Pages<SubCallBack> pages=null;
		try {
			CheckParamManager.getCallerInfoValidate(request);
			pages=cbService.getSubCallBackList(new SubCallBack(request),cbIds, currentPage, len);
			if(pages.getResultList()==null||pages.getResultList().size()<=0){
				setState(StateList.DATA_NOT_FOUND, PromptMessage.DATA_NOT_FOUND);
				DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID)), response);
				return;
			}
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}//finally{
		DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
				DepictXmlManager.makeSubCallBackList(pages), response);
		//}
	}
	/**
	 * 获取一条回访记录
	 */
	public void callBackById(){
		LOG.debug("A Request From CallCenter callBackById");
		SubCallBack scb=null;
		try {
			CheckParamManager.getCallerInfoValidate(request);
			
				scb=cbService.get(Integer.valueOf(request.getParameter("callbackid")));
			
			if(scb==null){
				setState(StateList.DATA_NOT_FOUND, PromptMessage.DATA_NOT_FOUND);
			}
			LOG.debug("A Request From CallCenter callbackid="+scb.getId());
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}finally{
		DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
				DepictXmlManager.makeSubCallBack(scb), response);
		}
	}
	/**
	 * 更新回访记录
	 */
	public void doCallBack(){
		SubCallBack scb=null;
		try {
			CheckParamManager.getCallerInfoValidate(request);
			scb=new SubCallBack(request,"");
			scb=cbService.doCallBack(scb);
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}finally{
		DepictXmlManager.outputStr(
				DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
				DepictXmlManager.makeSubCallBack(scb),
				response);
		}
	}
	/**
	 * 根据账号，所在服务器查询 角色名，职业，等级
	 */
	public void detailByAccount(){
		LOG.debug("A Request From CallCenter  | detailByAccount()");
		String result="";
		try {
			CheckParamManager.getCallerInfoValidate(request);
			result=callerService.makeUserInfo(request);
			if(MyUtil.isBlankOrNull(result)){
				setState(StateList.DATA_NOT_FOUND, PromptMessage.DATA_NOT_FOUND);
			}
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
		}finally{
		DepictXmlManager.outputStr(
				DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID))+
				result,
				response);
		}
	}
	/**
	 * CallCenter 跳转到 工单处理页面
	 */
	public String toProcessPage(){
		LOG.debug("CallCenter 请求工单处理页面");
	//	try {
			//CheckParamManager.getCallerInfoValidate(request);
			
			Staff staf=(Staff) session.getAttribute(STAFF);
			if(staf==null){//if not login
				LOG.debug("initing...");
				staf=staffService.getStaffByAccName(accName);
				session.setAttribute(STAFF, staf);
				session.setAttribute(DEPARTMENT, staf.getDepartment());
				//session.setAttribute(PERMISSION, getPermissions(staf));
				session.setAttribute(ROLE, staf.getRole());
				session.setAttribute(GROUP, staf.getGroup());
				session.setAttribute("issueInfo", woService.initIssueOneMap(9));
				session.setAttribute("groupInfo", staffService.getGroupMap());
			}
			
		//} catch (WoCenterException e) {
		//	setState(e);
		//}
		
		workOrder=woService.get(woId);
		LOG.debug("lookOrOperate获取工单 ID="+workOrder.getId());
		
		return OPERWORKORDER;
	}
	/**
	*
	 */
//	private List<String> getPermissions(Staff staff){
//		List<String> list=new ArrayList<String>();
//		Role role=staff.getRole();
//		Set<Permission> pset=role.getPermissions();
//		for(Permission p : pset){
//			if(p.getOperation().equals("button"))
//				list.add(p.getName());
//		}
//		return list;
//	}
	
	public void isVip(){
		
		try {
			CheckParamManager.getCallerInfoValidate(request);
			
			callerService.isVip(caller);
		} catch (WoCenterException e) {
			setState(e);
		}catch(Exception e){
			setState(StateList.DATA_NOT_FOUND, PromptMessage.DATA_NOT_FOUND);
		}
		finally{
			DepictXmlManager.outputStr(
					DepictXmlManager.organizeBasicXML(state, stateDesc,request.getParameter(ParamName.CLIENT_ID)),
					response);
		}
	}
	
	
	private  String state=StateList.SUCCESS;
	private  String stateDesc=PromptMessage.SUCCESS;
	WorkOrder workOrder=null;
	private String username;						//用户名，角色名
	private String password;							//密码
	private String caller;									//电话号码
	private String accountname;					//玩家账号
	private String usertype;							//用户类型
	private String accName;							//员工账号
	private String realname;							//员工账号
	private int pid=0;											//问题父类型
	private boolean xyhf;								//是否需要回访
	private String level;										//等级
	private String vocation;							//职业
	private int gid=0;											//小组id
	private int sid=0;											//员工id
	private String[] willhf;								//是否需要回访
	private String cbIds;
	private Date happendate;
	private Date fromDate;
	private Date toDate;
	private long woId;
	private String isdhgd;
	private String gameId;
	
	public long getWoId() {
		return woId;
	}
	public void setWoId(long woId) {
		this.woId = woId;
	}

	private int currentPage=1;//当前页
	private int len=5;//每页显示条数
	private static final long serialVersionUID = 1L;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getHappendate() {
		return happendate;
	}
	public void setHappendate(Date happendate) {
		this.happendate = happendate;
	}
	
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public WorkOrder getWorkOrder() {
		return workOrder;
	}
	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}
	public String getIsdhgd() {
		return isdhgd;
	}
	public void setIsdhgd(String isdhgd) {
		this.isdhgd = isdhgd;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String[] getWillhf() {
		return willhf;
	}
	public void setWillhf(String[] willhf) {
		this.willhf = willhf;
	}
	public boolean isXyhf() {
		return xyhf;
	}
	public void setXyhf(boolean xyhf) {
		this.xyhf = xyhf;
	}
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getVocation() {
		return vocation;
	}
	public void setVocation(String vocation) {
		this.vocation = vocation;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getCaller() {
		return caller;
	}
	public void setCaller(String caller) {
		this.caller = caller;
	}
	private void setState(WoCenterException e){
		state=e.getState();
		stateDesc=e.getStateDesc();
	}
	private void setState(String _state,String _stateDesc){
		state=_state;
		stateDesc=_stateDesc;
	}
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public String getCbIds() {
		return cbIds;
	}
	public void setCbIds(String cbIds) {
		this.cbIds = cbIds;
	}

	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}

	private static final Logger LOG = Logger.getLogger(Action4CallCenter.class);//定义日志
	
	public void callBackTest(){
			System.out.println("Enter in CallBackTest");
			//callerService.saveCallBackList();
	}
}
