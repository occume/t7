package com.hy.wo.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hy.wo.service.CallBackService;
import com.hy.wo.service.CallerService;
import com.hy.wo.service.FaqService;
import com.hy.wo.service.PrivateCenterService;
import com.hy.wo.service.StaffService;
import com.hy.wo.service.WorkOrderPFService;
import com.hy.wo.service.WorkOrderService;
import com.hy.wo.util.CheckParamManagerNotStatic;
import com.opensymphony.xwork2.ActionSupport;
import com.you9.base.Globe;
/**
 * 本项目所有action的公共父类
 * @author dong_jin
 * @version 创建时间：2011-10-20 上午10:21:15
 */
@Controller @Scope("prototype")
public class BaseAction extends ActionSupport{
	
	@Resource 
	protected WorkOrderService woService;
	@Resource
	protected StaffService staffService;
	@Resource 
	protected WorkOrderPFService woPfService;
	@Resource
	protected CheckParamManagerNotStatic woCheckParamManager;
	@Resource
	protected PrivateCenterService privateService;
	@Resource
	protected FaqService faqService;
	@Resource
	protected CallerService callerService;
	@Resource
	protected CallBackService cbService;
	
	protected HttpSession session=ServletActionContext.getRequest().getSession();
	
	protected HttpServletResponse response=ServletActionContext.getResponse();
	protected HttpServletRequest request=ServletActionContext.getRequest();
	
	protected Map<Integer, String> serverMap;//服务器名称MAP
	String path=Globe.getProperty("service/general/file/upload");
	
	protected String rp=ServletActionContext.getServletContext().getRealPath(path);//获取 上传文件保存路径

	/****************Result Constants S**********************/
	public static final String ABNORMAL="abnormal";	//角色异常问题
	public static final String USERWOLIST="userwolist";//问题列表
	public static final String DETAIL="detail";//问题详细信息
	public static final String ACC_INDEX="accIndex";//工单首页
	public static final String ACC_LOGON="accLogon";//客服登录
	public static final String ACC_MANAGE="accManage";//账号管理页面
	public static final String MODIFY_SELF="modifySelf";//修改本人信息页面
	public static final String STAFFINFOLIST="staffInfoList";//员工信息列表
	public static final String STAFFEDITLIST="staffEditList";//查看本部门员工
	public static final String EDITPAGE="editStaff";
	public static final String ADDPAGE="addStaff";
	public static final String ERROR="error";
	public static final String MANAGESELECT="manaegSelect";//工单管理页面
	public static final String OPERWORKORDER="operateWO";//工单操作页面
	public static final String LOOKUPWORKORDER="lookupWO";//工单查看页面
	public static final String JSRESULT="JsResult";//异步提交脚本输出页
	
	/****************Result Constants E**********************/
	
	
	/*****************setters and getters*******************/
	public Map<Integer, String> getServerMap() {
		return woService.initServerMap();
	}
	public void setServerMap(Map<Integer, String> serverMap) {
		this.serverMap = serverMap;
	}
	private static final long serialVersionUID = 1L;
}
