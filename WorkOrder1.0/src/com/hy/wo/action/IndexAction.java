package com.hy.wo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hy.wo.dao.AsistDao;
import com.hy.wo.po.Advisory;
import com.hy.wo.po.Areas;
import com.hy.wo.po.Games;
import com.hy.wo.po.Group;
import com.hy.wo.po.Issue;
import com.hy.wo.po.RechargeType;
import com.hy.wo.po.ReportKinds;
import com.hy.wo.po.ServerInfo;
import com.hy.wo.po.Servers;
import com.hy.wo.po.States;
import com.hy.wo.po.WorkOrderSource;
import com.hy.wo.util.Constants;

@Controller @Scope("prototype")
public class IndexAction extends BaseAction {
	
	/**
	 * 获取创建工单所有初始化信息
	 * @return
	 */
	public String toCreateWorkOrder(){
		LOG.debug("***********获取创建工单所有初始化信息**************");
		session.setAttribute("areaInfo", woService.initAreaMap());//从数据库中获取所有游戏服务器大区初始化信息
		session.setAttribute("classCategoryInfo", woService.initClassCategoryMap());//从数据库中获取所有游戏角色职业初始化信息
		session.setAttribute("gameInfo", woService.initGameMap());//从数据库中获取所有游戏名称初始化信息
		session.setAttribute("sourceInfo", woService.initSourceMap());//从数据库中获取所有工单来源初始化信息
		session.setAttribute("urgencyInfo", woService.initUrgencyMap());//从数据库中获取紧急程度初始化信息
		session.setAttribute("serverInfo", woService.initServerMap());//从数据库中将获取服务器初始化信息添加到SESSION
		session.setAttribute("issueInfo", woService.initIssueOneMap(9));//从数据库中获取一级问题类型初始化信息
		return "createWO";
	}
	
	public String allConfigInfo(){
		
		return "confManage";
	}
	/***服务器信息相关
	 * @throws IOException ***/
	public String serverJson() throws IOException{
		sendDataOut(Servers.class,0);
		return null;
	}

	/**
	 * 更新服务器信息
	 * @return
	 * @throws IOException 
	 */
	public void  addServer() throws IOException{
		try {
			List<ServerInfo> list=AsistDao.getGameServers();
			Map<Integer,String> map=new HashMap<Integer, String>();
			for(ServerInfo info:list){
				map.put(info.getServerId(), info.getAreaName()+"--"+info.getServerName());
			}
			woService.updateServersInfo(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sendTextOut("更新成功!");
	}
	public String deleteServer(){
		String message=woService.deleteServer(id);
		try {
			sendTextOut(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 游戏信息相关
	 * @return
	 * @throws IOException
	 */
	public String  gameJson() throws IOException{
		sendDataOut(Games.class,0);
		return null;
	}
	public String addGame(){
		String message=woService.addGame(name);
		try {
			sendTextOut(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String editGame() throws IOException{
		String message=woService.editGame(id, name);
		sendTextOut(message);
		return null;
	}
	public String deleteGame() throws IOException{
		String message=woService.deleteGame(id);
		sendTextOut(message);
		return null;
	}
	/**********违规类型信息相关*********/
	public String  reportKindJson() throws IOException{
		sendDataOut(ReportKinds.class,0);
		return null;
	}
	public String addReportKind(){
		String message=woService.addReportKind(name);
		try {
			sendTextOut(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String editReportKind() throws IOException{
		String message=woService.editReportKind(id, name);
		sendTextOut(message);
		return null;
	}
	public String deleteReportKind() throws IOException{
		String message=woService.deleteReportKind(id);
		sendTextOut(message);
		return null;
	}
	/**********充值类型信息相关*********/
	public String  rechargeTypeJson() throws IOException{
		sendDataOut(RechargeType.class,0);
		return null;
	}
	public String addRechargeType() throws IOException{
		String message=woService.addRechargeType(name);
		sendTextOut(message);
		return null;
	}
	public String editRechargeType() throws IOException{
		String message=woService.editRechargeType(id, name);
		sendTextOut(message);
		return null;
	}
	public String deleteRechargeType() throws IOException{
		String message=woService.deleteRechargeType(id);
		sendTextOut(message);
		return null;
	}
	/**********问题小类型信息相关*********/
	public void  issueTypeJson() throws IOException{
		sendDataOut(Issue.class,pid);
	}
	public String addIssue() throws IOException{
		String message=woService.addIssue(pid,name);
		sendTextOut(message);
		return null;
	}
	public String editIssue() throws IOException{
		LOG.debug("Enter into edit ISsue;");
		String message=woService.editIssue(id, name);
		sendTextOut(message);
		return null;
	}
	public String deleteIssue() throws IOException{
		String message=woService.deleteIssue(id);
		sendTextOut(message);
		return null;
	}
	/********** 咨询类型信息相关*********/
	public String  advisoryTypeJson() throws IOException{
		sendDataOut(Advisory.class,0);
		return null;
	}
	public String addAdvisory() throws IOException{
		String message=woService.addAdvisory(name);
		sendTextOut(message);
		return null;
	}
	public String editAdvisory() throws IOException{
		String message=woService.editAdvisory(id, name);
		sendTextOut(message);
		return null;
	}
	public String deleteAdvisory() throws IOException{
		String message=woService.deleteAdvisory(id);
		sendTextOut(message);
		return null;
	}
	/***获取游戏大区信息
	 * @throws IOException ***/
	public String areaJson() throws IOException{
		//System.out.println("2wo shi 1.0");
		sendDataOut(Areas.class,0);
		return null;
	}
	/**
	 * 获取问题类型信息
	 * @return
	 * @throws IOException
	 */
	public String issueJson() throws IOException{
		//System.out.println("1wo shi 1.0");
		sendDataOut(Issue.class,pid);
		return null;
	}
	/**
	 * 获取工单来源信息
	 * @return
	 * @throws IOException
	 */
	public String sourceJson() throws IOException{
		sendDataOut(WorkOrderSource.class,0);
		return null;
	}
	/**
	 * 获取工单状态信息
	 * @return
	 * @throws IOException
	 */
	public String statesJson() throws IOException{
		sendDataOut(States.class,0);
		return null;
	}
	public String groupJson() throws IOException{
		sendDataOut(Group.class,0);
		return null;
	}
	@SuppressWarnings("unchecked")
	private void sendDataOut(Class clazz,int pid) throws IOException{
		response.setContentType("text/xml;charset=UTF-8");   
	    response.setCharacterEncoding("UTF-8"); 
	    JSONObject jobj=woService.getJsonObject(clazz,pid);
	    //LOG.debug(clazz.getName()+"@"+jobj);
		PrintWriter out=response.getWriter(); 
		out.print(jobj.toString());
		out.flush();
		out.close();	
	}
	private void sendTextOut(String message) throws IOException{
		response.setContentType("text/xml;charset=UTF-8");   
	    response.setCharacterEncoding("UTF-8"); 

	    JSONObject jobj=new JSONObject();
	    jobj.element("res", message);
	    
		PrintWriter out=response.getWriter(); 
		out.print(jobj.toString());
		out.flush();
		out.close();
	}
	/**
	 * 获取编辑页面下拉框修改信息
	 * @return
	 */
	public String getSelectInfo(){
		response.setContentType("text/xml;charset=UTF-8");   
	    response.setCharacterEncoding("UTF-8"); 
	    
	    Map<Integer, String> map =woService.initIssueOneMap(Constants.ClassCategory.GAMEISSUE);
		//map.put(37, "意见建议");
	    JSONObject jobj=new JSONObject();
//		jobj.element("area", woService.initList(Areas.class));
//		jobj.element("game", woService.initList(Games.class));
//		jobj.element("source",woService.initList(WorkOrderSource.class));
//		jobj.element("urgency",woService.initList(Urgency.class));
//		jobj.element("server", woService.initList(Servers.class));
		jobj.element("issue",map );
//		jobj.element("roleVaca", woService.initList(ClassCategory.class));
		
		LOG.debug("获取信息成功："+jobj.toString());
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		out.print(jobj.toString());
		out.flush();
		out.close();
		return null;
	}
	
	public String toAbnormal(){
		return ABNORMAL;
	}
	private String name;
	private int id;
	private int pid;//问题类型 父类
	private int areaId;
	private int gameId;
	private String keyWord;
	private static final Logger LOG = Logger.getLogger(IndexAction.class);//定义日志
	private static final long serialVersionUID = -6916082460727482028L;
	
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
