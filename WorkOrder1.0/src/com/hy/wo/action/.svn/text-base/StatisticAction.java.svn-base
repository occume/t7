package com.hy.wo.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hy.wo.po.Assign;
import com.hy.wo.po.Group;
import com.hy.wo.po.Issue;
import com.hy.wo.po.WorkOrder;
import com.hy.wo.po.WorkOrderOper;
import com.hy.wo.po.WorkOrderUserInfo;
import com.hy.wo.service.impl.HSSFService;
import com.hy.wo.util.Pages;
import com.hy.wo.util.Constants.OperTypeId;

@Controller @Scope("prototype")
public class StatisticAction extends BaseAction {
	/**
	 * 客服员工工作量统计
	 * @return
	 * @throws IOException
	 */
	public String byAccName() throws IOException{
		//创建的工单
		try{
		WorkOrder workOrder=new WorkOrder();
		workOrder.setCreateWorker(accName);
		Pages<WorkOrder> pages1= woPfService.countWorkOrder(workOrder, -1, -1, startTime, endTime);
		workOrder.setCreateWorker(null);
		//派转的工单
		Assign assign=new Assign();
		assign.setFrom(accName);
		workOrder.setAssign(assign);
		Pages<WorkOrder> pages2= woPfService.countWorkOrder(workOrder, -1, -1, startTime, endTime);
		
		List<WorkOrderOper> list=woPfService.getCountByResponsed(accName, startTime, endTime,OperTypeId.REPLY) ;
		
		HSSFWorkbook hWorkbook=HSSFService.getInstance().getSheet1ByAccName(pages1, pages2, list, accName, startTime, endTime);
		
		InputStream in=HSSFService.getInstance().getHSSFInputStream(hWorkbook);
		setExcelStream(in);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 问题类型数据统计
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public String byType() throws IOException{
		WorkOrder workOrder=new WorkOrder();
		WorkOrderUserInfo userInfo=new WorkOrderUserInfo();
		Issue issue=new Issue();
		issue.setId(issueId);
		userInfo.setLvlOne(issue);
		workOrder.setUserInfo(userInfo);
		
		List list=woPfService.countWorkOrderByType(workOrder, startTime, endTime);
		Pages<WorkOrder> pages= woPfService.countWorkOrder(workOrder, -1, -1, startTime, endTime);
		HSSFWorkbook hWorkbook=HSSFService.getInstance().getSheet1ByType(pages, accName, startTime, endTime,list);
		
		InputStream in=HSSFService.getInstance().getHSSFInputStream(hWorkbook);
		setExcelStream(in);
		return SUCCESS;
	}
	/**
	 * 问题结案率统计
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String finishRate() throws IOException{
		WorkOrder workOrder=new WorkOrder();
		Group group=new Group();
		group.setId(groupId);
		workOrder.setGroup(group);
		List finishList=woPfService.countFinishRate(workOrder, startTime, endTime);
		List allList=woPfService.havingFinishTime(workOrder, startTime, endTime);
		
		HSSFWorkbook hWorkbook=HSSFService.getInstance().getSheet1ByType(finishList,allList, startTime, endTime);
		InputStream in=HSSFService.getInstance().getHSSFInputStream(hWorkbook);
		setExcelStream(in);
		return SUCCESS;
	}
	/**
	 * 关键字统计
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String byKeyWord() throws IOException{
		List<WorkOrder> list=woPfService.countWorkOrderByKeyWord(keyWord, startTime, endTime);
		HSSFWorkbook hWorkbook=HSSFService.getInstance().getSheet1ByKeyWord(list, startTime, endTime);
		InputStream in=HSSFService.getInstance().getHSSFInputStream(hWorkbook);
		setExcelStream(in);
		return SUCCESS;
	}
	/**
	 * 满意度统计
	 * @return
	 * @throws IOException 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String bySatisfaction() throws IOException{
		LOG.debug("*********bySatisfaction****");
		WorkOrder workOrder=new WorkOrder();
		Group group=new Group();
		group.setId(groupId);
		workOrder.setGroup(group);
		List list=woPfService.countWorkOrderBySatisfaction(workOrder, startTime, endTime);
		HSSFWorkbook hWorkbook=HSSFService.getInstance().getSheet1BySatisfaction(list, startTime, endTime);
		InputStream in=HSSFService.getInstance().getHSSFInputStream(hWorkbook);
		setExcelStream(in);
		return SUCCESS;
	}
	
	/**
	 * 每天每人操作数量
	 * @return
	 * @throws IOException 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String byEveryDay() throws IOException{
		LOG.debug("*********byEveryDay****");
		
		List list=woPfService.getCountByAccName(accName, startTime, endTime);
		HSSFWorkbook hWorkbook=HSSFService.getInstance().getSheet1ByEveryDay(list, startTime, endTime);
		InputStream in=HSSFService.getInstance().getHSSFInputStream(hWorkbook);
		setExcelStream(in);
		return SUCCESS;
	}
	/**
	 * 每天每人回复数量
	 * @return
	 * @throws IOException 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String byEveryDayReply() throws IOException{
		LOG.debug("*********byEveryDay****");
		
		List list=woPfService.getCountByAccNameReply(accName, startTime, endTime);
		HSSFWorkbook hWorkbook=HSSFService.getInstance().getSheet1ByEveryDayReply(list, startTime, endTime);
		InputStream in=HSSFService.getInstance().getHSSFInputStream(hWorkbook);
		setExcelStream(in);
		return SUCCESS;
	}
	/**
	 * 其他  每天每人处理数量
	 * @return
	 * @throws IOException 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String byEveryDayDeal() throws IOException{
		LOG.debug("*********byEveryDayDeal****");
		String depart=(String)session.getAttribute("department");
		//System.out.println(depart);
		String departName=depart==null?"客服部":depart;
		List list=woPfService.getCountByAccNameDeal(accName,departName,startTime, endTime);
		HSSFWorkbook hWorkbook=HSSFService.getInstance().getSheet1ByEveryDayReply(list, startTime, endTime);
		InputStream in=HSSFService.getInstance().getHSSFInputStream(hWorkbook);
		setExcelStream(in);
		return SUCCESS;
	}
	
	private Date startTime;
	private Date endTime;
	private String accName;
	private int issueId;
	private String iType;
	private int groupId;
	private String keyWord;
	private InputStream excelStream;
	
	
	
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getIssueId() {
		return issueId;
	}
	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	
	
	public String getiType() {
		return iType;
	}
	public void setiType(String iType) {
		this.iType = iType;
	}
	public InputStream getExcelStream() {
		return excelStream;
	}
	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
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
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(StatisticAction.class);//定义日志
}
