package com.hy.wo.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import rtx.SendNotify;
import com.hy.wo.cache.JCSManager;
import com.hy.wo.exception.WoCenterException;
import com.hy.wo.po.*;
import com.hy.wo.servlet.DWRHelper;
import com.hy.wo.util.AreaJson;
import com.hy.wo.util.Constants;
import com.hy.wo.util.CountResult;
import com.hy.wo.util.MyUtil;
import com.hy.wo.util.OperInfo;
import com.hy.wo.util.Pages;
import com.hy.wo.util.WorkOrderNearNow;
import com.hy.wo.util.Constants.ExcelName;
import com.hy.wo.util.Constants.OperAlert;
import com.hy.wo.util.Constants.PAGE;
import com.hy.wo.util.Constants.ParamName;
import com.hy.wo.util.Constants.PromptMessage;
import com.hy.wo.util.Constants.URL;
import com.hy.wo.util.constants.Evaluations;
import static com.hy.wo.util.Constants.States.*;
import static com.hy.wo.util.Constants.SessionKey.*;
import com.opensymphony.xwork2.ModelDriven;
import com.you9.base.Globe;
import com.you9.base.util.StringUtil;

/**
 * 工单广场
 */
@Controller @Scope("prototype")
public class WorkOrderPFAction extends BaseAction implements ModelDriven<WorkOrder>{

	private static final long serialVersionUID = -1489839637159849901L;
	private static final Logger LOGGER = Logger.getLogger(WorkOrderPFAction.class);//定义日志
	private InputStream inputStream;//封装输出结果的二进制流

	/**
	 * 拉单
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String pullWorkOrder(){
		LOGGER.debug("*******拉单action*******");
		Staff staff=checkCurrentPage();//获取staff对象和当前页,以验证是否登录
		if(staff!=null){//已登录
			String m=woPfService.getWorkOrderIdOfPullState(staff);
			setMessage(m);
			//DWRHelper.showGetOrder(session.getServletContext(), staff);
			sendGetWorkOrderInfo(message);
		}else{//未登录
			sendRelogon(preUrlPF);//跳转至
		}
		return null;
	}
	
	/**
	 * 精确拉单
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public void specialPullWorkOrder(){	
		Staff staff=checkCurrentPage();
		boolean ps=woPfService.pullWorkOrder(workOrder, staff.getId());
		if(ps){
			sendGetWorkOrderInfo(PromptMessage.LADAN_SUCCESS);
		}else{
			sendGetWorkOrderInfo(PromptMessage.LADAN_FAILURE);
		}
	}
	
	/**
	 * 
	 * @param message
	 */
	private void sendGetWorkOrderInfo(String message){
		response.setContentType(Constants.DEFAULT_CONTENT_TYPE);
	    response.setCharacterEncoding(Constants.DEFAULT_CHARSET);
	    
	    JSONObject jobj=new JSONObject();
	    jobj.element("message",message);
		PrintWriter out=null; 
		try{
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		out.print(jobj.toString());
		out.flush();
		out.close();
	}
	/**
	 * 获取所有尚未被拉单的工单
	 * @return
	 * @throws IOException 
	 */
	public String getGlobalNoPull() throws IOException{
		LOGGER.debug("********获取所有尚未被拉单的工单*********");		
		checkCurrentPage();
	    int c = woPfService.getAllWorkOrderOfGetState(currentPage,len,false);
		//QueryResultHandle(pages);
	    sendGetWorkOrderInfo(String.valueOf(c));
		return null;
	}
	
	/**
	 * 任意条件工单查询
	 * @throws IOException 
	 */
	public String searchWorkOrder(){
		LOGGER.debug("********查询工单*********");
		
		Staff staff=checkCurrentPage();
		
		if(staff==null){
			LOGGER.debug(PromptMessage.PLEASE_LOGIN);
			sendRelogon(preUrlPF);
		}else{
			Role role=(Role) session.getAttribute(ROLE);
			Group group=(Group) session.getAttribute(GROUP);
			pages = woPfService.searchWorkOrder(group,role,workOrder, currentPage, len,startTime,endTime);
			
			LOGGER.debug("总数："+pages.getTotalCount());
			QueryResultHandle(pages);	
		}
		return null;
	}
	/**
	 * Excel文件导出
	 * @throws IOException 
	 */
	public  InputStream getExcelStream() throws IOException{
		LOGGER.debug("********Excel文件导出*********");
		Staff staff=checkCurrentPage();
		if(staff==null){//未登录
			LOGGER.debug(PromptMessage.PLEASE_LOGIN);
			sendRelogon(preUrlPF);
		}else{
			Role role=(Role) session.getAttribute(ROLE);
			Group group=(Group) session.getAttribute(GROUP);
			pages = woPfService.searchWorkOrder(group,role,workOrder, -1, -1,startTime,endTime);
			//QueryResultHandle(pages);	
		}
		
		HSSFWorkbook hWorkbook=new HSSFWorkbook();
		//System.out.println(hWorkbook);
		HSSFSheet hSheet=hWorkbook.createSheet("sheet1");
		int rowNum=0;
		int colNum=0;
		HSSFRow row=hSheet.createRow(rowNum++);
		
		HSSFCell cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.WORK_ORDER_ID);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.USER_ACCOUNT);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.ISSUE_TYPE);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.DEALER);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.CREATE_TIME);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.SOURCE);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.SERVER);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.DESCRIPTION);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.IMG_LINK);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.OVER_TIME);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.SATISFACTION);
		

		for(WorkOrder wo:pages.getResultList()){
			row=hSheet.createRow(rowNum++);
			colNum=0;
			//工单ID
			Long id=wo.getId();
			cell=row.createCell(colNum++);
			cell.setCellValue(new HSSFRichTextString(id.toString()));
//			
			//玩家账号
			String accName=wo.getUserInfo().getAccountname();
			accName=accName==null?"------":accName;
			cell=row.createCell(colNum++);
			cell.setCellValue(new HSSFRichTextString(accName));
			
			//问题大类
			Issue one=wo.getUserInfo().getLvlOne();
			String issueType=one==null?"------":one.getName();
			cell=row.createCell(colNum++);
			cell.setCellValue(new HSSFRichTextString(issueType));
			//处理人
			Staff staf = wo.getWorker();
			String worker = staf == null ? "---":staf.getAccName();
			cell=row.createCell(colNum++);
			cell.setCellValue(new HSSFRichTextString(worker));
			
			//创建时间
			Date cTime=wo.getCreateTime();
			
			cell=row.createCell(colNum++);
			cell.setCellValue(new HSSFRichTextString(Constants.sdf1.format(cTime)));
			
			//工单来源
			String source=wo.getSource();
			cell=row.createCell(colNum++);
			cell.setCellValue(new HSSFRichTextString(source));
			
			//服务器
			Servers server=wo.getUserInfo().getServer();
			String serverName=server==null?"------":server.getName();
			cell=row.createCell(colNum++);
			cell.setCellValue(new HSSFRichTextString(serverName));
			//问题描述
			String memo=wo.getUserInfo().getMemo();
			cell=row.createCell(colNum++);
			cell.setCellValue(new HSSFRichTextString(memo));
			//图片链接
			String path="";
			if(wo.getUserInfo().getFiles().size()>0){
				for(FileUpLoad file:wo.getUserInfo().getFiles()){
					path += URL.CS_2211_COM + file.getPath()+"\n";
				}
			}
			cell=row.createCell(colNum++);
			cell.setCellValue(new HSSFRichTextString(MyUtil.isBlankOrNull(path)?"---":path));
			//结案时间
			Date fTime=wo.getFinishTime();
			cell=row.createCell(colNum++);
			cell.setCellValue(new HSSFRichTextString(fTime==null?"------":Constants.sdf1.format(fTime)));
			//满意度
			Evaluations ev=wo.getEvaluation();
			cell=row.createCell(colNum++);
			cell.setCellValue(new HSSFRichTextString(ev==null?"------":ev.getName()));
		}
		//request.get
		String path=MyUtil.getXLSDownLoadPath();
		String rp=ServletActionContext.getServletContext().getRealPath(path);
		File file=new File(rp);
		try {
			OutputStream out=new FileOutputStream(file) ;
			hWorkbook.write(out);
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		InputStream in=null;
		try{
			in=new FileInputStream(file);
			if(file.exists())
				file.delete();
		}catch(IOException e){}
		
		return in;
	}
	/**
	 * 我组工单广场当前处理情况
	 * @return
	 */
	public String getCurrentInfo(){
		response.setContentType(Constants.DEFAULT_CONTENT_TYPE);
	    response.setCharacterEncoding(Constants.DEFAULT_CHARSET);
	    
	    Group group=(Group) session.getAttribute(GROUP);
	  
	    CountResult  countResult=woPfService.getCountResult(group.getId());
	    JSONObject jobj=new JSONObject();
	    jobj.element("countResult", countResult);
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
		return null;
	}
	
	/**
	 * vip祝福
	 * @throws IOException
	 */
	public void getWorkOrderVipComp(){
		
		Staff staff=checkCurrentPage();
		if(staff==null){
		
			sendRelogon(preUrlPF);
		}else{
			pages=woPfService.searchWorkOrderVipComp(staff, currentPage, len);
			QueryResultHandle(pages);	
		}
	}
	/**
	 * 需电话回访工单
	 * @throws IOException
	 */
	public void getWorkOrderIshf(){
		//LOGGER.debug("********需电话回访工单*********");	
		Staff staff=checkCurrentPage();
		if(staff==null){
		//	LOGGER.debug("未登录或者登录已过期~");
			sendRelogon(preUrlPF);
		}else{
			pages=woPfService.searchWorkOrderIshf(staff, currentPage, len);
			QueryResultHandle(pages);	
		}
	}
	/**
	 * 等待处理工单
	 * @return
	 * @throws IOException
	 */
	public String getWorkOrderWaitForDeal(){
		LOGGER.debug("********等待处理工单*********");	
		Staff staff=checkCurrentPage();
		if(staff==null){
			LOGGER.debug(PromptMessage.PLEASE_LOGIN);
			sendRelogon(preUrlPF);
		}else{
			pages=woPfService.searchWorkOrderWaitForDeal(staff, currentPage, len);
			QueryResultHandle(pages);	
		}
		return null;
	}
	public String getWorkOrderLackInfo(){
		//LOGGER.debug("********待补充资料工单*********");	
		Staff staff=checkCurrentPage();
		if(staff==null){
			LOGGER.debug(PromptMessage.PLEASE_LOGIN);
			sendRelogon(preUrlPF);
		}else{
			pages=woPfService.searchWorkOrderLackInfo(staff, currentPage, len);
			QueryResultHandle(pages);	
		}
		return null;
	}

	//派单跟踪
	public String getWorkOrderAssigned(){
		LOGGER.debug("********派单跟踪*********");
		Staff staff=checkCurrentPage();
		if(staff==null){
			LOGGER.debug(PromptMessage.PLEASE_LOGIN);
			sendRelogon(preUrlPF);
		}else{
			pages=woPfService.searchWorkOrderAssinged(staff, currentPage, len);
			QueryResultHandle(pages);
		}
		return null;
	}
	//等待回访
	public String getWorkOrderWaitForResponse(){
		LOGGER.debug("********等待回访*********");
		Staff staff=checkCurrentPage();
		if(staff==null){
			LOGGER.debug(PromptMessage.PLEASE_LOGIN);
			sendRelogon(preUrlPF);
		}else{
			pages=woPfService.searchWorkOrderWaitForResponse(staff, currentPage, len);
			QueryResultHandle(pages);
		}
		return null;
	}
	//回访跟踪
	public String getWorkOrderResponsed(){
		LOGGER.debug("********回访跟踪*********");
		Staff staff=checkCurrentPage();
		if(staff==null){
			LOGGER.debug(PromptMessage.PLEASE_LOGIN);
			sendRelogon(preUrlPF);
		}else{
			pages=woPfService.searchWorkOrderResponsed(staff, currentPage, len);
			QueryResultHandle(pages);
		}
		return null;
	}
	/**
	 * 技术GM 由我处理工单
	 * @return
	 */
	public String getWorkOrderWaitForMe(){
		LOGGER.debug("********由我处理*********");
		Staff staff=checkCurrentPage();
		if(staff==null){
			LOGGER.debug(PromptMessage.PLEASE_LOGIN);
			sendRelogon(preUrlPF);
		}else{
			pages=woPfService.searchWorkOrderWaitForMe(staff, currentPage, len);
			QueryResultHandle(pages);
		}
		return null;
	}
	/**
	 * 其他部门处理记录
	 * @return
	 */
	public String getWorkOrderAssignedMe(){
		Staff staff=checkCurrentPage();
		if(staff==null){
			LOGGER.debug(PromptMessage.PLEASE_LOGIN);
			sendRelogon(preUrlPF);
		}else{
			pages=woPfService.searchWorkOrderAssignMe(staff, currentPage, len);
			QueryResultHandle(pages);
		}
		return null;
	}
	//if not login
	private void sendRelogon(String preURL){
		response.setContentType(Constants.DEFAULT_CONTENT_TYPE);
	    response.setCharacterEncoding(Constants.DEFAULT_CHARSET);
	    
	    JSONObject jobj=new JSONObject();
	    jobj.element(URL.PRE_URL, URL.WORK_2211_COM_LOGIN + URL.PRE_URL + preURL);
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
	/**************** ? 登录验证 ? ***********************/
	private Staff checkCurrentPage(){
		if(!StringUtil.isBlankOrNull(request.getParameter( PAGE.CURRENT_PAGE ))){
			currentPage=Integer.valueOf(request.getParameter( PAGE.CURRENT_PAGE )); 
		}   	
		Staff staff=(Staff) session.getAttribute(STAFF);
		return staff==null?null:staff;
	}
	/**
	 * 查询员工近期处理的工单
	 * @return
	 * @throws IOException
	 */
	public String getWorkOrderNearNow() throws IOException{
		LOGGER.debug("********显示该账号近期处理工单*********");
		checkCurrentPage();
		nnPages=woPfService.searchWorkOrderNearNow(accountname, currentPage, len);
		
		response.setContentType(Constants.DEFAULT_CONTENT_TYPE);
	    response.setCharacterEncoding(Constants.DEFAULT_CHARSET);
		JSONObject obj =new JSONObject();
		
			JSONArray jsonArray = JSONArray.fromObject(nnPages.getResultList());//将LIST转换
			if(nnPages.getResultList().size()<1){
				obj.element( PAGE.EMPTY, PAGE.NO_RESULT);
			}
			obj.element( PAGE.WORK_ORDER_LIST,jsonArray);//所有的未被拉单的工单信息
			obj.element( PAGE.PAGE_SIZE, nnPages.getPageSize());//每页显示数
			obj.element( PAGE.TOTAL_PAGE, nnPages.getTotalPage());//总页数
			PrintWriter out=response.getWriter();  
			
			out.print(obj.toString());
			out.flush();
			out.close();
		return null;
	}
	/***************-----------------工单操作 ---------------------*****************/
	//处理
	/**
	 * 
	 */
	private String ReturnJsStr;
	
	public String getReturnJsStr() {
		return ReturnJsStr;
	}
	public void setReturnJsStr(String returnJsStr) {
		ReturnJsStr = returnJsStr;
	}
	public String dealWorkOrder(){
		LOGGER.debug("处理工单,ID="+workOrder.getId());
		Staff staff=checkCurrentPage();
		if(staff==null){
			setReturnJsStr(REFLUSH);
			return SUCCESS;
		}
		WorkOrder w=woPfService.dealWorkOrder(staff, workOrder.getId(), description,extraCondition);
		if(w==null){//if deal
			setMessage(OperAlert.DEALED);
			return SUCCESS;
		}
		LOGGER.debug("处理成功");
		//设置输出脚本
		setReturnJsStr(REFLUSH);
		String msg = OperAlert.DEAL_SUCCESS;
		//////////////////////////////////////
		//    		 RTX 提醒    start          //
		//////////////////////////////////////
		LOGGER.debug("小组workOrder：" + w.getGroup().getId());
		LOGGER.debug("小组staff：" + staff.getGroup().getId());
		
		String receivers = woPfService.getRTXStaff( w.getGroup().getId(), staff );
		LOGGER.debug("获取名单成功：" + receivers);
		try{
			SendNotify.procesNotify(receivers, String.valueOf(w.getId()));
			msg += "\n" + OperAlert.RTX_NOTICE_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//////////////////////////////////////
		//           RTX 提醒    end            //
		//////////////////////////////////////
		LOGGER.debug("派单完毕：");
		setMessage(msg);
		return SUCCESS;
	}
	//回复
	public String replyWorkOrder(){
		LOGGER.debug("回复工单,ID="+workOrder.getId());
		Staff staff=checkCurrentPage();
		
		if(staff==null){
			setReturnJsStr(REFLUSH);
			return SUCCESS;
		}
		
		WorkOrder w=null;
		try {
			w = woPfService.replyWorkOrder(staff, workOrder.getId(), description,extraCondition);
		} catch (WoCenterException e) {
			setReturnJsStr(getParentAlert( e.getStateDesc()));
			return SUCCESS;
		}
		if(w==null){//if response
			setReturnJsStr(getParentAlert(OperAlert.REPLIED));
			return SUCCESS;
		}
		
		setReturnJsStr(REFLUSH);
		
		return SUCCESS;
	}
	//批量回复
	public void batchReply(){
		Staff staff=checkCurrentPage();
		
		if(staff==null){
			setReturnJsStr(REFLUSH);
			return ;
		}
		
		String ids=request.getParameter(ParamName.IDS);
		description=request.getParameter(ParamName.CONTENT);
		String[] arrId=ids.split(",");
		for(String id:arrId){
			long lid=Long.valueOf(id);
			try {
				woPfService.replyWorkOrder(staff, lid, description,null);
			} catch (WoCenterException e) {
			}
		}
		sendGetWorkOrderInfo(OperAlert.BATCH_SUCCESS);
	}
	/**
	 * 客服补充资料
	 * @return
	 * @throws IOException
	 */
	public String extraUpdateWorkOrder() throws IOException{
		
	    Staff staff=checkCurrentPage();
	    if(upload!=null&&upload.length>0){
	    	uploadFileName=MyUtil.uploadFileHandle(upload, uploadFileName,
	    			rp+"/" + Constants.UrlInfo.EXTRAL_INFO, staff.getAccName());
	    }
	   
	   woService.saveIssueAdditional(woId, description,staff,false,uploadFileName);

	    setWorkOrderId(woId);
		return toSingleWorkOrder();
	}
	
	//派单
	public String assignWorkOrder(){
		//LOGGER.debug("派转工单,ID="+workOrder.getId());
	//	LOGGER.debug("派转工单到,toGroupID="+toGroupId);
		Staff staff=checkCurrentPage();
		
		if(staff==null){
			setMessage(PromptMessage.PLEASE_LOGIN);
			setReturnJsStr(REFLUSH);
			return SUCCESS;
		}
		if(toGroupId==0){
			addFieldError(ParamName.GROUP_ID, OperAlert.PLEASE_PICK_GROUP);
			setWorkOrderId(workOrder.getId());
			return toAssignPage();
		}
		Role role=(Role) session.getAttribute(ROLE);
		NameValuePair nvpp=woPfService.assignWorkOrder(staff,role, workOrder.getId(), description,toGroupId,toStaffId);
		if(nvpp==null){//if assigned
			setMessage( OperAlert.ASSING_NO_DEAL );
			//setReturnJsStr( REFLUSH) ;
			return SUCCESS;
		}
		String msg = OperAlert.ASSING_SUCCESS;
		//////////////////////////////////////
		//    		 RTX 提醒    start          //
		//////////////////////////////////////
		
		String receivers = woPfService.getRTXStaff( toGroupId, staff );
		try{
			SendNotify.notify( receivers  , nvpp.getName() );
			msg += "\n" + OperAlert.RTX_NOTICE_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//////////////////////////////////////
		//           RTX 提醒    end            //
		//////////////////////////////////////
		
		setReturnJsStr(REFLUSH);
		setMessage( msg );
		List<Long> list=staffService.getStaffOfGroup(toGroupId);
		NameValuePair nvp=new NameValuePair(String.valueOf(workOrder.getId()), description);
		DWRHelper.showAssignNotice(session.getServletContext(),
									list,nvp);
		//LOGGER.debug("派转完毕");
		return SUCCESS;
	}
//	//派单GM
//	public String assignGM(){
//		LOGGER.debug("派转GM,ID="+workOrder.getId());
//		Staff staff=checkCurrentPage();
//		
//		if(staff==null){
//			LOGGER.debug("未登录或者登录已过期~");
//			return "reLogon";
//		}
//		
//		message=woPfService.assignGM(staff, workOrder.getId());
//		if(StringUtil.isBlankOrNull(message)){
//			sendGetWorkOrderInfo("您无法派单给技术GM");
//		}else{
//			sendGetWorkOrderInfo("成功派单给本组技术GM");
//		}
//		LOGGER.debug("派单GM完毕");
//		return null;
//	}
	public String toAssignPage(){
		Staff staff=checkCurrentPage();
		
		if(staff==null){
			setReturnJsStr(REFLUSH);
			return SUCCESS;
		}
		groupMap=woPfService.initGroupMap(staff);
		return _ASSIGN;
	}
	//回访
	public String responseWorkOrder(){
		LOGGER.debug("回访工单,ID="+workOrder.getId());
		Staff staff=checkCurrentPage();
		
		if(staff==null){
			setReturnJsStr(REFLUSH);
			return SUCCESS;
		}
		
		WorkOrder w=woPfService.respnseWorkOrder(staff, workOrder.getId(), description);
		if(w==null){
			setMessage( OperAlert.NO_REPLY_TO_RESP );
		}
		
		setReturnJsStr(REFLUSH);
		setMessage( OperAlert.RESPONSE_SUCCESS );
		LOGGER.debug(OperAlert.RESPONSE_SUCCESS);
		return SUCCESS;
	}
	//Mark
	public String returnWorkOrder(){
		LOGGER.debug("mark工单,ID="+workOrder.getId());
		Staff staff=checkCurrentPage();
		
		if(staff==null){
			setReturnJsStr( REFLUSH );
			return SUCCESS;
		}
		
		woPfService.returnWorkOrder(staff, workOrder.getId(), null);
		sendGetWorkOrderInfo( PromptMessage.SUCCESS );
		LOGGER.debug("Mard完毕");
		return null;
	}
	//改变需电话回访状态
	public String alterIshf(){
		LOGGER.debug("alter工单,ID="+workOrder.getId());
		Staff staff=checkCurrentPage();
		
		if(staff==null){
			setReturnJsStr(REFLUSH);
			return SUCCESS;
		}
		
		woPfService.alterIshf(staff, workOrder.getId(), null);
		sendGetWorkOrderInfo( PromptMessage.SUCCESS );
		LOGGER.debug("alter完毕");
		return null;
	}
	//改变工单处理状态
	public String alterState(){
		//LOGGER.debug("alter工单,ID="+workOrder.getId());
		Staff staff=checkCurrentPage();
		
		if(staff==null){
			setReturnJsStr(REFLUSH);
			return SUCCESS;
		}
		
		message=woPfService.alterState(staff, workOrder.getId(), description,extraCondition);
		sendGetWorkOrderInfo(message);
		//LOGGER.debug("alter完毕");
		return null;
	}
	//删除
	public String deleteWorkOrder(){
		LOGGER.debug("删除工单,ID="+workOrder.getId());
		Staff staff=checkCurrentPage();
		
		if(staff==null){
			LOGGER.debug(PromptMessage.PLEASE_LOGIN);
			setReturnJsStr(REFLUSH);
			return SUCCESS;
		}
		
		woPfService.deleteWorkOrder(staff, workOrder.getId(), description);
		setReturnJsStr(REFLUSH);
		LOGGER.debug( OperAlert.DELETE_SUCCESS );
		return SUCCESS;
	}
	//批量删除
	public void batchDelete(){
		Staff staff=checkCurrentPage();
		if(staff==null){
			LOGGER.debug(PromptMessage.PLEASE_LOGIN);
			setReturnJsStr(REFLUSH);
			return ;
		}
		String ids=request.getParameter( ParamName.IDS );
		description=request.getParameter( ParamName.CONTENT );
		String[] arrId=ids.split(",");
		for(String id:arrId){
			long lid=Long.valueOf(id);
			woPfService.deleteWorkOrder(staff, lid, description);
		}
		sendGetWorkOrderInfo( OperAlert.BATCH_DELETE_SUCCESS );
	}
	//批量退单
	public void batchReturn(){
		Staff staff=checkCurrentPage();
		if(staff==null){
			LOGGER.debug(PromptMessage.PLEASE_LOGIN);
			setReturnJsStr(REFLUSH);
			return ;
		}
		String ids=request.getParameter( ParamName.IDS );
		description=request.getParameter( ParamName.CONTENT );
		String[] arrId=ids.split(",");
		for(String id:arrId){
			long lid=Long.valueOf(id);
			woPfService.backWorkorder(staff, lid, description);
		}
		sendGetWorkOrderInfo( OperAlert.BATCH_RETURN_SUCCESS );
	}
	
	/***
	 * 当日排行榜
	 * @throws IOException 
	 */
	public String getRangeJosonOfToday() throws IOException{
	    JSONArray jsonArray=woService.getRangeObjectOfToday();
	    sendRangeData(jsonArray);
		return null;
	}
	/***
	 * 当月排行榜
	 * @throws IOException 
	 */
	public String getRangeJosonOfThisMonth() throws IOException{
	    JSONArray jsonArray=woService.getRangeObjectOfMonth();
	    sendRangeData(jsonArray);
		return null;
	}
	private void sendRangeData(JSONArray jsonArray){//注意这里
		//LOGGER.debug("排行开始"+new Date());
		response.setContentType( Constants.DEFAULT_CONTENT_TYPE );  
	    response.setCharacterEncoding( Constants.DEFAULT_CHARSET ); 
	    //response.setHeader("Cache-Control", "no-cache");    
	    PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	    		
	    out.print(jsonArray.toString());
	    out.flush();
	    out.close();
	}
	@SuppressWarnings("unchecked")
	private void QueryResultHandle(Pages pages){
		LOGGER.debug("********组织展示数据**************");
		response.setContentType( Constants.DEFAULT_CONTENT_TYPE );  
	    response.setCharacterEncoding( Constants.DEFAULT_CHARSET ); 
		JSONObject obj =new JSONObject();
		
			try{
				List<WorkOrder> workOrderList = new ArrayList<WorkOrder>();
				workOrderList = (List<WorkOrder>)pages.getResultList();
				List<WorkOrder> jsonworkOrderList = new ArrayList<WorkOrder> ();
				if(pages.getResultList().size()>0){
					//WorkOrderSource source;//工单来源
					Staff getWorker;//拉单人
					Staff worker;//处理人

					Issue lvlOne;//问题第二类型
					WorkOrderUserInfo userInfo;//玩家信息
					Servers server;//游戏服务器
					String states= null;//States.UNDEAL;//处理状态
					Games game = null;
					for(WorkOrder wo:workOrderList){
//						LOGGER.debug("WOKEORDERLIST SIZE = "+workOrderList.size());
//						LOGGER.debug("ID="+wo.getId());
						
						/**拉单人信息*/
						getWorker = new Staff();
						getWorker.setAccName(wo.getGetWorker()==null?"":wo.getGetWorker().getAccName());//拉单人
						/*******************/
						worker=new Staff();
						worker.setAccName(wo.getWorker()==null?"":wo.getWorker().getAccName());
						worker.setNickName(wo.getWorker()==null?"":wo.getWorker().getNickName());
						
						
						/*************/
						/**设置工单来源*/
						String source = wo.getSource()== null?"":wo.getSource();
						//source.setName((wo.getSource()== null)?null:wo.getSource());
						/*****************/
						
						/**工单紧急程度**/
						String urgency = wo.getUrgency()== null?"":wo.getUrgency();
						//urgency.setName(wo.getUrgency()== null?null:wo.getUrgency());
						/***************/
						
						/**问题二级类型***/
						userInfo = new WorkOrderUserInfo();	
						lvlOne = new Issue();
						lvlOne.setName(wo.getUserInfo().getLvlOne()== null?"":wo.getUserInfo().getLvlOne().getName());//问题第二大类型
						
						server = new Servers();
						server.setName((wo.getUserInfo()==null?null:wo.getUserInfo().getServer())==null?"":wo.getUserInfo().getServer().getName());
						
						game = new Games();
						game.setName((wo.getUserInfo()==null?null:wo.getUserInfo().getGame())==null?"":wo.getUserInfo().getGame().getName());
						
						userInfo.setLvlOne(lvlOne);//问题类型
						userInfo.setServer(server);//服务器
						userInfo.setAccountname(wo.getUserInfo().getAccountname()==null?"":wo.getUserInfo().getAccountname());//玩家帐号
						userInfo.setGame(game);//游戏名称
						userInfo.setMemo(wo.getUserInfo().getMemo());
						
						/*********************/
						
						/****/
						states = UNDEAL;
						states = wo.getStates();
						/****************/
						
						WorkOrder jsonWo = new WorkOrder();
						jsonWo.setId(wo.getId());
						jsonWo.setGetTime(wo.getGetTime());//拉单时间			
						jsonWo.setGetWorker(getWorker);//拉单人
						jsonWo.setSource(source);//设置工单来源
						jsonWo.setUrgency(urgency);//紧急程度
						jsonWo.setUserInfo(userInfo);//设置玩家帐号,问题第二大类型
						jsonWo.setCreateTime(wo.getCreateTime());//工单创建时间
						jsonWo.setStates(states);//处理状态
						jsonWo.setWorker(worker);
						jsonWo.setInnerMark(wo.getInnerMark());
						//System.out.println("evaluation="+wo.getEvaluation());
						if(wo.getEvaluation()!=null){
							jsonWo.setComment(wo.getEvaluation().getName());
							////////////////////////////
							//System.out.println("eva="+wo.getEvaluation().getName());
							////////////////////////////
						}
						if(wo.getAssign()!=null)
							jsonWo.setAssign(wo.getAssign());

						jsonworkOrderList.add(jsonWo);
						jsonWo = null;
					}
				}else{
					LOGGER.debug("没有相应条件的数据");
				}
				Role role = (Role)session.getAttribute(Constants.SessionKey.ROLE);//从session中获取Role操作权限的对象
				boolean batchOperPer = false;
				if(role !=null){
					batchOperPer = MyUtil.isBatchOperationPer(role.getLevel());//根据玩家级别判断其是否有批量操作权限
				}
				JSONArray jsonArray = JSONArray.fromObject(jsonworkOrderList);//将工单数据转换
				
				obj.element( PAGE.WORK_ORDER_LIST,jsonArray );//所有的未被拉单的工单信息
				obj.element( PAGE.PAGE_SIZE, pages.getPageSize() );//每页显示数
				obj.element( PAGE.TOTAL_PAGE, pages.getTotalPage() );//总页数
				obj.element( PAGE.TOTAL_COUNT, pages.getTotalCount() );//总页数
				obj.element( PAGE.BATCH_PERMISSION, batchOperPer);//批量操作权限
				
				PrintWriter out=response.getWriter();  
				out.print(obj.toString());
				out.flush();
				out.close();
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	/**
	 * 工单精确定位 权限判断
	 * @return
	 */
	public String lookOrOperateWorkOrder(){
		
		Staff staf=checkCurrentPage();
		if(staf==null){//if not login
			LOGGER.debug(PromptMessage.PLEASE_LOGIN);
			return RE_LOGIN;
		}
		//LOGGER.debug("在登录状态");
		workOrder=woService.get(workOrderId);
		LOGGER.debug("lookOrOperate获取工单 ID="+workOrder.getId());
		/////////////////////////////////
		/*************************///fillOperInfo(workOrder);
		////////////////////////////////
		LOGGER.debug("分类信息");
		Role role=(Role) session.getAttribute(ROLE);
		Group group=(Group) session.getAttribute( GROUP );
		
		int roleId=role.getId();
		int roleLevel=role.getLevel();
		int groupId=group.getId();
		
		if(roleLevel>=1){
			return OPERWORKORDER;
		}else{
			if(roleId==4||roleId==5){
				//if(workOrder.getGroup()!=null&&workOrder.getGroup().getId()==groupId){
					return OPERWORKORDER;
				//}else{
					//return LOOKUPWORKORDER;
				//}
			}
			else{
				if(workOrder.getTobeGroup()!=null&&workOrder.getTobeGroup().getId()==groupId){
					return OPERWORKORDER;
				}else{
					return LOOKUPWORKORDER;
				}	
			}
		}
	}
	//根据小组查找员工
	public void getStaffOfGroup(){
		response.setContentType( Constants.DEFAULT_CONTENT_TYPE );
	    response.setCharacterEncoding( Constants.DEFAULT_CHARSET );
	    
	    JSONObject jobj=woPfService.getStaffOfGroup(toGroupId);
	 
		PrintWriter out=null; 
		try{out = response.getWriter();
		} catch (IOException e) {e.printStackTrace();}  
		out.print(jobj.toString());
		out.flush();
		out.close();
	}
	public String getAssignGroup(){
		response.setContentType( Constants.DEFAULT_CONTENT_TYPE );
	    response.setCharacterEncoding( Constants.DEFAULT_CHARSET );
	    Staff staf=checkCurrentPage();
		if(staf==null){//if not login
			LOGGER.debug(PromptMessage.PLEASE_LOGIN);
			return RE_LOGIN;
		}
	    Map<Integer,String> gMap=woPfService.initGroupMap(staf);
	   
	    JSONObject jobj=new JSONObject();
	    if(convertMapList(gMap).size()>0)
	    	jobj.element( PAGE.STAFF_LIST, convertMapList(gMap));
	    else
	    	jobj.element( PAGE.EMPTY, PAGE.NO_STAFF);
		PrintWriter out=null; 
		try{out = response.getWriter();
		} catch (IOException e) {e.printStackTrace();}  
		out.print(jobj.toString());
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 返回到特定工单操作页面
	 * @return
	 */
	public String toSingleWorkOrder(){
		Staff staf=checkCurrentPage();
		workOrder=woService.get(workOrderId);
		
		//锁定工单 放入缓存
		if(Globe.getProperty("monitorLock/switch").equals("true")){
				String id=String.valueOf(workOrder.getId());
				if(staf.getRole().getId()==4 || staf.getRole().getId()==5){
						if(JCSManager.getInstance().get(id)==null){
								//if(!workOrder.getStates().equals(RESPONSED)){
									JCSManager.getInstance().put(id, staf.getAccName());
								//}
						}
				}
		}
		LOGGER.debug("操作ID="+workOrder.getId()+"的工单~");
		return OPERWORKORDER;
	}
	
	private List<AreaJson> convertMapList(Map<Integer,String> map){
			List<AreaJson> list=new ArrayList<AreaJson>();
			
		//	Set eSet=map.entrySet();
			for(Entry<Integer,String> e:map.entrySet()){
				AreaJson aj=new AreaJson();
				aj.setId(e.getKey());
				aj.setName(e.getValue());
				list.add(aj);
				aj=null;
			}
			return list;
	}
	private String getParentAlert(String msg){
		return PARENT_ALERT+"('"+msg+"')";
	}

	/**
	 * 返回输出流直接输出给浏览者
	 * @return
	 */
	public InputStream getResult(){
		return inputStream;
	}
	private WorkOrder workOrder=new WorkOrder();
	private Pages<WorkOrder> pages;//分页
	private Pages<WorkOrderNearNow> nnPages;//近期处理工单分页
	private String accountname;
	private int currentPage=1;
	private int len=10;
	private long workOrderId;//工单id
	private Integer toGroupId;//派转组
	private Long toStaffId;//派转员工
	private Map<Integer, String> groupMap;//小组
	private int departId;//部门
	private OperInfo operInfo=new OperInfo();//操作类型集合类
	private String operGenre;//操作类型
	private String description;
	private String preURL;
	private String preUrlPF="/ctService/workOrderPlatform.jsp";
	private String REFLUSH="parent.window.location.href=parent.window.location.href;";
	private String PARENT_ALERT="parent.window.dialog.Alert";
	private String parentPath;
	private String message="empty";//错误提示信息
	private Date startTime;
	private Date endTime;
	private String extraCondition;
	private int woId;
	
	private File[] upload;//上传的文件数组
	private String[] uploadContentType;
	private String[] uploadFileName;
	
	public File[] getUpload() {
		return upload;
	}
	public void setUpload(File[] upload) {
		this.upload = upload;
	}
	public String[] getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String[] getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	/**get set 区域*/
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public String getExtraCondition() {
		return extraCondition;
	}
	public void setExtraCondition(String extraCondition) {
		this.extraCondition = extraCondition;
	}
	public Map<Integer, String> getGroupMap() {
		return groupMap;
	}
	public void setGroupMap(Map<Integer, String> groupMap) {
		this.groupMap = groupMap;
	}
	public int getWoId() {
		return woId;
	}
	public void setWoId(int woId) {
		this.woId = woId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOperGenre() {
		return operGenre;
	}
	public void setOperGenre(String operGenre) {
		this.operGenre = operGenre;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	
	public int getDepartId() {
		return departId;
	}
	public void setDepartId(int departId) {
		this.departId = departId;
	}
	
	public Integer getToGroupId() {
		return toGroupId;
	}
	public void setToGroupId(Integer toGroupId) {
		this.toGroupId = toGroupId;
	}
	
	public Long getToStaffId() {
		return toStaffId;
	}
	public void setToStaffId(Long toStaffId) {
		this.toStaffId = toStaffId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPreURL() {
		return preURL;
	}
	public void setPreURL(String preURL) {
		this.preURL = preURL;
	}
	public String getParentPath() {
		return parentPath;
	}
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}
	public OperInfo getOperInfo() {
		return operInfo;
	}
	public void setOperInfo(OperInfo operInfo) {
		this.operInfo = operInfo;
	}
	public long getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(long workOrderId) {
		this.workOrderId = workOrderId;
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
	public WorkOrder getWorkOrder() {
		return workOrder;
	}
	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
//	public String getAccName() {
//		return accName;
//	}
//	public void setAccName(String accName) {
//		this.accName = accName;
//	}
//	public void setResult(String result) {
//		this.result = result;
//	}
//	public String getResult() {
//		return result;
//	}
	public WorkOrder getModel() {
		return workOrder;
	}
	
	public static void main(String...strings){
		//SendNotify.notify("", "", "");
	}
	private String _ASSIGN = "assign";
	private String RE_LOGIN = "reLogon";
}
