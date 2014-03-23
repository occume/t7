package com.hy.wo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hy.wo.exception.InputIllegalArgumentException;
import com.hy.wo.po.Areas;
import com.hy.wo.po.ClassCategory;
import com.hy.wo.po.Department;
import com.hy.wo.po.Games;
import com.hy.wo.po.Group;
import com.hy.wo.po.Issue;
import com.hy.wo.po.Permission;
import com.hy.wo.po.Role;
import com.hy.wo.po.Servers;
import com.hy.wo.po.Staff;
import static com.hy.wo.util.Constants.SessionKey.*;
import com.hy.wo.util.CheckParamManager;
import com.hy.wo.util.Constants;
import com.hy.wo.util.MyUtil;
import com.hy.wo.util.Pages;
import com.hy.wo.util.Constants.OperAlert;
import com.opensymphony.xwork2.ModelDriven;
import com.you9.base.util.StringUtil;

@Controller @Scope("prototype")
public class StaffAction extends BaseAction implements ModelDriven<Staff>{
	
	private static final long serialVersionUID = -9143008611164095465L;
	private static final Logger LOG = Logger.getLogger(StaffAction.class);//定义日志
	
	/**
	 * 工单登录
	 * @return	登录成功到工单首页，登录失败到登录页
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws InputIllegalArgumentException 
	 */
	public String logon() throws ServletException, IOException {
		if(session.getAttribute(STAFF)!=null){//已经登录
			return ACC_INDEX;
		}
		
		String accName=staff.getAccName();
		String password=staff.getPassword();
		
		try {
			CheckParamManager.checkStaffLoginParam(accName, password);
		} catch (InputIllegalArgumentException e) {
			addFieldError("",e.getMessage());
			return ACC_LOGON;
		}
		
		//用户名和密码验证
		Staff staf=staffService.logon_wo(staff);
		if(staf==null){
			addFieldError("","该用户名不存在或密码错误~");
			return ACC_LOGON;
		}else{
			session.setAttribute(STAFF, staf);
			session.setAttribute(DEPARTMENT, staf.getDepartment().getName());
			session.setAttribute("department_", staf.getDepartment());
			session.setAttribute(PERMISSION, getPermissions(staf));
			session.setAttribute(ROLE, staf.getRole());
			session.setAttribute(GROUP, staf.getGroup());
			session.setAttribute("issueInfo", woService.initIssueOneMap(9));
			session.setAttribute("groupInfo", staffService.getGroupMap());
			
			if(!StringUtil.isBlankOrNull(preURL)){//如果是重登陆
				RequestDispatcher dispat=request.getRequestDispatcher(preURL);
				dispat.forward(request, response);
				
			}
			return ACC_INDEX;
		}
	}
	
	/**
	 * 
	 * @param staff
	 * @return
	 */
	private List<String> getPermissions(Staff staff){
		List<String> list=new ArrayList<String>();
		Role role=staff.getRole();
		Set<Permission> pset=role.getPermissions();
		for(Permission p : pset){
			if(p.getOperation().equals("button"))
				list.add(p.getName());
		}
		return list;
	}
	
	/**
	 * 登出
	 * @return
	 */
	public String logout(){
		if(session.getAttribute(STAFF)!=null)
			session.removeAttribute(STAFF);	
		return ACC_LOGON;
	}
	
	/**
	 * 管理菜单
	 * @return
	 */
	public String modifySelf(){
		Staff staf=isOnline();//获取此员的信息
		if(staf==null){//if not login
			return ACC_LOGON;
		}
		staff=staf;
		return ACC_MANAGE;
	}
	
	/**
	 * 管理所有员工[获取所有员工信息]
	 * @return
	 */
	public String manageStaff(){
		
		Staff staf=isOnline();
		if(staf==null){//if not login
			return ACC_LOGON;
		}
		LOG.debug("登录中");
		Role role=(Role) session.getAttribute(ROLE);
		LOG.debug("获取角色");
		LOG.debug("小组："+staff.getGroup()==null?true:false);
		
		pages=staffService.searchStaff(currentPage, len, staff, role);
		return STAFFEDITLIST;
	}
	/**
	 * 根据部门查找员工
	 * @return
	 */
	public String searchStaffByDepart(){
		return STAFFEDITLIST;
	}
	
	public String manage(){
		if(isOnline()==null){
			return ACC_LOGON;
		}
		if(manageId==0){//
			staff=isOnline();
			return ACC_MANAGE;
		}else if(manageId==1){//修改本人信息
			return MODIFY_SELF;
		}else if(manageId==2){//查看本部门员工
			manageMyDepartment();
			return STAFFINFOLIST;
		}else if(manageId==3){//管理本部门员工
			manageMyDepartment();
			return STAFFEDITLIST;
		}else if(manageId==4){
			//manageAllStaff();
			return STAFFINFOLIST;
		}else if(manageId==5){
			//manageAllStaff();
			return STAFFEDITLIST;
		}else if(manageId==6){//管理工单界面
			//manageSelect();
			return MANAGESELECT;
		}
		return "";
	}
	//查看和管理本部门 @客服主管操作
	private void manageMyDepartment(){
		int id=staff.getDepartment().getId();
		pages=staffService.findStaffByDepartmentId(id, currentPage, len);
	}

	/**
	 * 添加员工 ￥客服主管以上
	 * @return
	 */
	public String addStaff(){
		staff=staffService.addStaff(staff);
		int id=staff.getDepartment().getId();
		pages=staffService.findStaffByDepartmentId(id, currentPage, len);
		return SUCCESS;
	}
	public String addStaffToDepartment(){
		Staff loginStaff=(Staff) session.getAttribute("staff");
		int departmentId=loginStaff.getDepartment().getId();
		staffService.addStaffToDepartment(staff, departmentId);
		//返回继续添加
		pages=staffService.findStaffByDepartmentName("无部门",currentPage,len);
		return ADDPAGE;
	}
	/**
	 * 修改员工信息
	 * @return
	 */
	public String edit(){
		staff=staffService.editStaffInfo(staff);
		//System.out.println(staff.getAccName());
		setReturnJsStr("parent.window.location.href=parent.window.location.href;");
		setMessage("员工信息编辑成功！");
		return "note";
	}
	/**
	 * 删除本部门员工信息
	 * @return
	 */
//	public String delete(){
//		staffService.deleteStaffOfDepartment(staff);
//		staff=(Staff)session.getAttribute("staff");
//		int id =staff.getDepartment().getId();
//		pages=staffService.findStaffByDepartmentId(id, currentPage, len);
//		return STAFFINFOLIST;
//	}
	public String delete(){
		staffService.deleteStaff(staff);
		sendJsonData( OperAlert.DELETE_SUCCESS );
		
		return null;
	}
	private void sendJsonData(String mes){
		response.setContentType( Constants.DEFAULT_CONTENT_TYPE );
	    response.setCharacterEncoding( Constants.DEFAULT_CHARSET );
	    JSONObject jobj=new JSONObject();
	    jobj.element("message",mes);
		PrintWriter out=null; 
		try
		{
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		out.print(jobj.toString());
		out.flush();
		out.close();
	}
	/**
	 * 根据工号查找员工
	 * @return
	 */
	public String findStaffByAccName(){
		String accName=staff.getAccName();
		pages=staffService.findStaffByAccName(accName);
		return STAFFEDITLIST;
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public String modifyPassword(){
		Staff staf=isOnline();//获取此员工信息
		if(staf==null){
			return ACC_LOGON;
		}
		try {
			if(!isRight()){
				return "modifyPsw";
			}
		} catch (InputIllegalArgumentException e) {
			e.printStackTrace();
			addFieldError("passwork", e.getMessage());
			return "modifyPsw";
		}
		
		if(!staf.getPassword().equals(MyUtil.hash(oldPassword))){
			addFieldError("oldPasswork", "旧密码输入错误~");
		}else{
			staf.setPassword(MyUtil.hash(rePassword));			
			staffService.update(staf);
			setMessage("修改密码成功~");
			setReturnJsStr("parent.window.location.href=parent.window.location.href;");
		}
		return "note";
		
	}
	/**
	 * 修改昵称
	 * @return
	 */
	public String modifyNickName(){
		Staff staf=isOnline();
		if(staf==null){
			return ACC_LOGON;
		}
		String nickName=staff.getNickName();
		if(nickName==null||nickName.trim().equals("")){
			addFieldError("nickName", "昵称不能为空~");
			return "modifyNick";
		}
		
		staf.setNickName(nickName);
		staffService.update(staf);
		setMessage("修改昵称成功~");
		setReturnJsStr("parent.window.location.href=parent.window.location.href;");
		return "note";
	}
	/******************小组操作**************************/
	public String toCreateGroup(){
		departmentMap=staffService.getDepartmentMap();
		return "createGroup";
	}
	public String toEditGroup(){
		departmentMap=staffService.getDepartmentMap();
		proup=staffService.searchGoupById(staff.getGroup().getId());
		LOG.debug("groupName="+proup.getName());
		return "editGroup";
	}
	public String manageGroup(){
		LOG.debug("grab group pages");
		pages=staffService.getGroupPages(getRole(), getDepart(), currentPage, len);
		return "groupList";
	}
	public String editGroup() throws IOException{
		if(StringUtil.isBlankOrNull(staff.getGroup().getName())){
			addFieldError("groupName", "组名不能为空");
		}else{
			int groupId=staff.getGroup().getId();
			String toName=staff.getGroup().getName();
			int departId=staff.getDepartment().getId();
			//System.out.println(departId);
		    staffService.editGroupName(groupId, toName,departId);
		    setMessage(" 修改成功~");
			setReturnJsStr("parent.window.location.href=parent.window.location.href;");
		}
	    return "note";
	}
	/**
	 * 创建小组
	 * @return
	 */
	public String createGroup(){
		String groupName=staff.getGroup().getName();
		if(StringUtil.isBlankOrNull(groupName)){
			addFieldError("groupName", "组名不能为空");
			return toCreateGroup();
		}else{
			int departId=staff.getDepartment().getId();
			staffService.createGroup(groupName,departId);
			setMessage(" 创建成功~");
			setReturnJsStr("parent.window.location.href=parent.window.location.href;");
			return "note";
		}
	}
	public String deleteGroup(){
		staffService.deleteGroup(Integer.valueOf(String.valueOf(staff.getId())));
		sendJsonData("删除成功！");
		return null;
	}
	//跳转到员工权限，职位修改页面
	public String toEdit(){
		roleMap=staffService.getRoleMap();
		departmentMap=staffService.getDepartmentMap();
		groupMap=staffService.getGroupMap();
		staff=staffService.get(staff.getId());
		return EDITPAGE;
	}
	//跳转到添加员工页面@客服主管以上
	public String toAddStaff(){
		roleMap=staffService.getRoleMap();
		departmentMap=staffService.getDepartmentMap();
		groupMap=staffService.getGroupMap();
		return ADDPAGE;
	}
	//跳转到添加部门员工页面@客服主管
	public String toAddStaffToDepartment(){
		pages=staffService.findStaffByDepartmentName("无部门",currentPage,len);
		return ADDPAGE;
	}
	//跳转到小组管理页面 
	public String toGroupManage(){
		groupList=staffService.getAllGroups();
		return "groupList";
	}
	private Role getRole(){
		return (Role) session.getAttribute(ROLE);
	}
	private Department getDepart(){
		return (Department) session.getAttribute("department_");
	}
	/**
	 * 获取员工信息
	 */
	private Staff isOnline(){
		long id=0;
		if(session.getAttribute("staff")==null){//未登录
			return null;
		}else{//此员工已经登录
			id=((Staff)session.getAttribute("staff")).getId();//获取此员工的ID
			Staff staf=staffService.get(id);//根据此ID获取此员工全部信息
			LOG.debug("获取员工");
			return staf;
		}
	}
	/**
	 * 非空验证
	 * @return
	 * @throws InputIllegalArgumentException 
	 */
	private boolean isRight() throws InputIllegalArgumentException{
		if(staff.getPassword().trim().equals("")){
			addFieldError("password", "密码不能为空~");
			return false;
		}
		if(rePassword.trim().equals("")){
			addFieldError("rePassword", "重复密码不能为空~");
			return false;
		}
		if(oldPassword.trim().equals("")){
			addFieldError("rePassword", "旧密码不能为空~");
			return false;
		}
		if(!rePassword.trim().equals(staff.getPassword().trim())){
			addFieldError("rePassword", "两次输入不一致~");
			return false;
		}
		CheckParamManager.checkPassword(staff.getPassword());
		return true;
	}
	private Staff staff=new Staff();
	private Group proup=new Group();
	private int manageId;//账号管理定位
	private String oldPassword;//旧密码
	private String rePassword;//重复密码
	private Pages<?> pages;//分页类
	private int currentPage=1;
	private int len=10;
	private String preURL;
	private String message;
	private String returnJsStr;
	
	public Group getProup() {
		return proup;
	}
	public void setProup(Group proup) {
		this.proup = proup;
	}
	public String getReturnJsStr() {
		return returnJsStr;
	}
	public void setReturnJsStr(String returnJsStr) {
		this.returnJsStr = returnJsStr;
	}
	private List<Group> groupList;
	private Pages<Group> groupPages;
	private Pages<Servers> serverPages;
	private Pages<Games> gamePages;
	private Pages<Areas> areaPages;
	private Pages<Issue> issuePages;
	private Pages<ClassCategory> classCategoryPages;
	
	public Pages<Group> getGroupPages() {
		return groupPages;
	}
	public void setGroupPages(Pages<Group> groupPages) {
		this.groupPages = groupPages;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPreURL() {
		return preURL;
	}
	public void setPreURL(String preURL) {
		this.preURL = preURL;
	}
	public List<Group> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
	public Pages<Servers> getServerPages() {
		return serverPages;
	}
	public void setServerPages(Pages<Servers> serverPages) {
		this.serverPages = serverPages;
	}
	public Pages<Games> getGamePages() {
		return gamePages;
	}
	public void setGamePages(Pages<Games> gamePages) {
		this.gamePages = gamePages;
	}
	public Pages<Areas> getAreaPages() {
		return areaPages;
	}
	public void setAreaPages(Pages<Areas> areaPages) {
		this.areaPages = areaPages;
	}
	public Pages<Issue> getIssuePages() {
		return issuePages;
	}
	public void setIssuePages(Pages<Issue> issuePages) {
		this.issuePages = issuePages;
	}
	public Pages<ClassCategory> getClassCategoryPages() {
		return classCategoryPages;
	}
	public void setClassCategoryPages(Pages<ClassCategory> classCategoryPages) {
		this.classCategoryPages = classCategoryPages;
	}
	private Map<Integer,String> roleMap;
	private Map<Integer,String> departmentMap;
	private Map<Integer,String> groupMap;
	
	public Map<Integer, String> getRoleMap() {
		return roleMap;
	}
	public void setRoleMap(Map<Integer, String> roleMap) {
		this.roleMap = roleMap;
	}
	public Map<Integer, String> getDepartmentMap() {
		return departmentMap;
	}
	public void setDepartmentMap(Map<Integer, String> departmentMap) {
		this.departmentMap = departmentMap;
	}
	public Map<Integer, String> getGroupMap() {
		return groupMap;
	}
	public void setGroupMap(Map<Integer, String> groupMap) {
		this.groupMap = groupMap;
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
	
	
	public Pages<?> getPages() {
		return pages;
	}

	public void setPages(Pages<?> pages) {
		this.pages = pages;
	}

	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public int getManageId() {
		return manageId;
	}
	public void setManageId(int manageId) {
		this.manageId = manageId;
	}
	public Staff getModel() {
		return staff;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}
