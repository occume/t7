package com.hy.wo.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.hy.wo.po.WorkOrder;
import com.hy.wo.po.WorkOrderOper;
import com.hy.wo.util.Constants;
import com.hy.wo.util.Pages;
import com.hy.wo.util.Constants.ExcelName;
import com.hy.wo.util.constants.Evaluations;
import com.you9.base.Globe;

public class HSSFService {
	
	public HSSFWorkbook getSheet1ByAccName(Pages<WorkOrder> p1,Pages<WorkOrder> p2,List<WorkOrderOper> list,
																				String accName,Date startTime,Date endTime){
		HSSFWorkbook hWorkbook=new HSSFWorkbook();
		HSSFSheet hSheet1=hWorkbook.createSheet("sheet1");
		int rowNum=0;
		int colNum=0;
		HSSFRow row=hSheet1.createRow(rowNum++);
		
		HSSFCell cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.ACC_NAME);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.BEGIN_TIME);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.END_TIME);
		
		colNum=0;
		row=hSheet1.createRow(rowNum++);
		cell=row.createCell(colNum++);
		cell.setCellValue(accName);
		cell=row.createCell(colNum++);
		cell.setCellValue(startTime==null?"---":Constants.sdf.format(startTime));
		cell=row.createCell(colNum++);
		cell.setCellValue(Constants.sdf.format(endTime==null?new Date():endTime));
		
		colNum=0;
		row=hSheet1.createRow(rowNum++);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.CREATE_COUNT);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.ASSIGN_COUNT);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.RESPONSE_COUNT);
		
		colNum=0;
		row=hSheet1.createRow(rowNum++);
		cell=row.createCell(colNum++);
		cell.setCellValue(p1.getTotalCount());
		cell=row.createCell(colNum++);
		cell.setCellValue(p2.getTotalCount());
		cell=row.createCell(colNum++);
		cell.setCellValue(list.size());
		
		HSSFSheet hSheet2=hWorkbook.createSheet("sheet2");
		rowNum=0;
		colNum=0;
		
		row=hSheet2.createRow(rowNum++);
		
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.WORK_ORDER_ID);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.OPERATOR);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.ISSUE_TYPE);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.OPER_DESCRIP);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.CREATE_TIME);
		
		for(WorkOrder wo:p1.getResultList()){
			colNum=0;
			row=hSheet2.createRow(rowNum++);
			cell=row.createCell(colNum++);
			cell.setCellValue(wo.getId());
			cell=row.createCell(colNum++);
			cell.setCellValue(wo.getCreateWorker());
			cell=row.createCell(colNum++);
			cell.setCellValue(wo.getUserInfo().getLvlOne()==null?"---":wo.getUserInfo().getLvlOne().getName());
			cell=row.createCell(colNum++);
			cell.setCellValue(wo.getSuggestion());
			cell=row.createCell(colNum++);
			cell.setCellValue(Constants.sdf.format(wo.getCreateTime()));
			
		}
		
		HSSFSheet hSheet3=hWorkbook.createSheet("sheet3");
		rowNum=0;
		colNum=0;
		
		row=hSheet3.createRow(rowNum++);
		
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.WORK_ORDER_ID);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.OPERATOR);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.ASSING_MSG);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.OPER_DESCRIP);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.OPER_TIME);
		
		for(WorkOrder wo:p2.getResultList()){
			colNum=0;
			row=hSheet3.createRow(rowNum++);
			cell=row.createCell(colNum++);
			cell.setCellValue(wo.getId());
			cell=row.createCell(colNum++);
			cell.setCellValue(accName);
			cell=row.createCell(colNum++);
			cell.setCellValue(wo.getAssign()==null?"---":wo.getAssign().getFrom()+"-->"+wo.getAssign().getTo());
			cell=row.createCell(colNum++);
			cell.setCellValue(wo.getSuggestion());
			cell=row.createCell(colNum++);
			cell.setCellValue(Constants.sdf.format(wo.getCreateTime()));
			
		}
		
		HSSFSheet hSheet4=hWorkbook.createSheet("sheet4");
		rowNum=0;
		colNum=0;
		
		row=hSheet4.createRow(rowNum++);
		
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.WORK_ORDER_ID);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.OPERATOR);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.OPER_TYPE);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.OPER_DESCRIP);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.OPER_TIME);
		
		for(WorkOrderOper oper:list){
			colNum=0;
			row=hSheet4.createRow(rowNum++);
			cell=row.createCell(colNum++);
			cell.setCellValue(oper.getWorkOrder().getId());
			cell=row.createCell(colNum++);
			cell.setCellValue(oper.getWorker());
			cell=row.createCell(colNum++);
			cell.setCellValue(oper.getOperType().getName());
			cell=row.createCell(colNum++);
			cell.setCellValue(oper.getContent());
			cell=row.createCell(colNum++);
			cell.setCellValue(Constants.sdf.format(oper.getOperTime()));
			
		}
		
		return hWorkbook;
	}
	/**
	 * 根据问题类型统计工单数量
	 * @param pages
	 * @param accName
	 * @param startTime
	 * @param endTime
	 * @param map 
	 * @return
	 */
	public HSSFWorkbook getSheet1ByType(Pages<WorkOrder> pages, String accName,
			Date startTime, Date endTime, List list) {
		HSSFWorkbook hWorkbook=new HSSFWorkbook();
		HSSFSheet hSheet1=hWorkbook.createSheet("sheet1");
		int rowNum=0;
		int colNum=0;
		HSSFRow row=hSheet1.createRow(rowNum++);
		
		HSSFCell cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.BEGIN_TIME);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.END_TIME);
		
		colNum=0;
		row=hSheet1.createRow(rowNum++);
		cell=row.createCell(colNum++);
		cell.setCellValue(startTime==null?"---":Constants.sdf.format(startTime));
		cell=row.createCell(colNum++);
		cell.setCellValue(Constants.sdf.format(endTime==null?new Date():endTime));
		
		colNum=0;
		row=hSheet1.createRow(rowNum ++);
		cell=row.createCell(colNum ++);
		cell.setCellValue(ExcelName.ISSUE_TYPE);
		cell=row.createCell(colNum ++);
		cell.setCellValue(ExcelName.COUNTS);
//		
		Iterator it=list.iterator();
		while(it.hasNext()){
			colNum=0;
			Object[] obj=(Object[]) it.next();
			row=hSheet1.createRow(rowNum++);
			cell=row.createCell(colNum++);
			cell.setCellValue(obj[0] + "");
			cell=row.createCell(colNum++);
			cell.setCellValue(obj[1] + "");
		}
		
		HSSFSheet hSheet2=hWorkbook.createSheet("sheet2");
		rowNum=0;
		colNum=0;
		
		row=hSheet2.createRow(rowNum++);
		
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.WORK_ORDER_ID);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.CURRENT_STATE);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.ISSUE_TYPE);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.DESCRIPTION);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.CREATE_TIME);
		
		for(WorkOrder wo:pages.getResultList()){
			colNum = 0;
			row = hSheet2.createRow(rowNum++);
			cell = row.createCell(colNum++);
			cell.setCellValue(wo.getId());
			cell = row.createCell(colNum++);
			cell.setCellValue(wo.getStates());
			cell = row.createCell(colNum++);
			cell.setCellValue(wo.getUserInfo().getLvlOne()==null?"---":wo.getUserInfo().getLvlOne().getName());
			cell = row.createCell(colNum++);
			cell.setCellValue(wo.getUserInfo().getMemo());
			cell = row.createCell(colNum++);
			cell.setCellValue(Constants.sdf.format(wo.getCreateTime()));
			
		}
		
		return hWorkbook;
	}
	/**
	 * 完成率统计
	 * @param finishList
	 * @param allList
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public HSSFWorkbook getSheet1ByType(List finishList, List allList,
			Date startTime, Date endTime) {
		HSSFWorkbook hWorkbook=new HSSFWorkbook();
		HSSFSheet hSheet1=hWorkbook.createSheet("sheet1");
		int rowNum=0;
		int colNum=0;
		HSSFRow row=hSheet1.createRow(rowNum++);
		
		HSSFCell cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.BEGIN_TIME);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.END_TIME);
		
		colNum=0;
		row=hSheet1.createRow(rowNum++);
		cell=row.createCell(colNum++);
		cell.setCellValue(startTime==null?"---":Constants.sdf.format(startTime));
		cell=row.createCell(colNum++);
		cell.setCellValue(Constants.sdf.format(endTime==null?new Date():endTime));
		
		colNum=0;
		row=hSheet1.createRow(rowNum++);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.FINISH_COUNT);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.TOTAL_COUNT);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.RATE);
		
		colNum=0;
		row=hSheet1.createRow(rowNum++);
		cell=row.createCell(colNum++);
		cell.setCellValue(finishList.size());
		cell=row.createCell(colNum++);
		cell.setCellValue(allList.size());
		cell=row.createCell(colNum++);
		DecimalFormat format=new DecimalFormat(ExcelName.RATE_TEMP);
		cell.setCellValue(format.format((double)finishList.size()/allList.size()));
		
		return hWorkbook;
	}
	/**
	 * 根据关键字统计工单数量
	 * @param list
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public HSSFWorkbook getSheet1ByKeyWord(List<WorkOrder> list, Date startTime,
			Date endTime) {
		HSSFWorkbook hWorkbook=new HSSFWorkbook();
		HSSFSheet hSheet1=hWorkbook.createSheet("sheet1");
		int rowNum=0;
		int colNum=0;
		HSSFRow row=hSheet1.createRow(rowNum++);
		
		HSSFCell cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.BEGIN_TIME);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.END_TIME);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.TOTAL_COUNT);
		
		colNum=0;
		row=hSheet1.createRow(rowNum++);
		cell=row.createCell(colNum++);
		cell.setCellValue(startTime==null?"---":Constants.sdf.format(startTime));
		cell=row.createCell(colNum++);
		cell.setCellValue(Constants.sdf.format(endTime==null?new Date():endTime));
		cell=row.createCell(colNum++);
		cell.setCellValue(list.size());
		
		colNum=0;
		row=hSheet1.createRow(rowNum++);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.WORK_ORDER_ID);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.CURRENT_STATE);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.ISSUE_TYPE);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.DESCRIPTION);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.CREATE_TIME);
		
		for(WorkOrder wo:list){
			colNum=0;
			row=hSheet1.createRow(rowNum++);
			cell=row.createCell(colNum++);
			cell.setCellValue(wo.getId());
			cell=row.createCell(colNum++);
			cell.setCellValue(wo.getStates());
			cell=row.createCell(colNum++);
			cell.setCellValue(wo.getUserInfo().getLvlOne()==null?"---":wo.getUserInfo().getLvlOne().getName());
			cell=row.createCell(colNum++);
			cell.setCellValue(wo.getUserInfo().getMemo());
			cell=row.createCell(colNum++);
			cell.setCellValue(Constants.sdf.format(wo.getCreateTime()));
			
		}
		
		return hWorkbook;
	}
	/**
	 * 满意度统计
	 * @param list
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public HSSFWorkbook getSheet1BySatisfaction(List list, Date startTime,
			Date endTime) {
		HSSFWorkbook hWorkbook=new HSSFWorkbook();
		HSSFSheet hSheet1=hWorkbook.createSheet("sheet1");
		int rowNum=0;
		int colNum=0;
		HSSFRow row=hSheet1.createRow(rowNum++);
		
		HSSFCell cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.BEGIN_TIME);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.END_TIME);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.EVALUA_COUNT);
		
		colNum=0;
		row=hSheet1.createRow(rowNum++);
		cell=row.createCell(colNum++);
		cell.setCellValue(startTime==null?"---":Constants.sdf.format(startTime));
		cell=row.createCell(colNum++);
		cell.setCellValue(Constants.sdf.format(endTime==null?new Date():endTime));
		cell=row.createCell(colNum++);
		cell.setCellValue(list.size());
		
		colNum=0;
		row=hSheet1.createRow(rowNum++);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.DATE);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.ACC_NAME);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.SATISFACTION);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.COUNTS);
//		
		Iterator it=list.iterator();
		while(it.hasNext()){
			colNum=0;
			Object[] obj=(Object[]) it.next();
			row=hSheet1.createRow(rowNum++);
			cell=row.createCell(colNum++);
			cell.setCellValue(obj[0]+"");
			cell=row.createCell(colNum++);
			cell.setCellValue(obj[1]+"");
			cell=row.createCell(colNum++);
			cell.setCellValue(Evaluations.getCHName(obj[2]+""));
			cell=row.createCell(colNum++);
			cell.setCellValue(obj[3]+"");
		}
		return hWorkbook;
	}
	/**
	 * 每人每天
	 * @param list
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	
	public HSSFWorkbook getSheet1ByEveryDay(List list, Date startTime,
			Date endTime) {
		HSSFWorkbook hWorkbook=new HSSFWorkbook();
		HSSFSheet hSheet1=hWorkbook.createSheet("sheet1");
		int rowNum=0;
		int colNum=0;
		HSSFRow row=hSheet1.createRow(rowNum++);
		
		HSSFCell cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.BEGIN_TIME);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.END_TIME);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.TOTAL_COUNT);
		
		colNum=0;
		row=hSheet1.createRow(rowNum++);
		cell=row.createCell(colNum++);
		cell.setCellValue(startTime==null?"---":Constants.sdf.format(startTime));
		cell=row.createCell(colNum++);
		cell.setCellValue(Constants.sdf.format(endTime==null?new Date():endTime));
		cell=row.createCell(colNum++);
		cell.setCellValue(list.size());
		
		colNum=0;
		row=hSheet1.createRow(rowNum++);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.DATE);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.OPERATOR);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.OPER_TYPE);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.COUNTS);
//		
		Iterator it=list.iterator();
		while(it.hasNext()){
			colNum=0;
			Object[] obj=(Object[]) it.next();
			row=hSheet1.createRow(rowNum++);
			cell=row.createCell(colNum++);
			cell.setCellValue(Constants.sdf.format(obj[0]));
			cell=row.createCell(colNum++);
			cell.setCellValue(obj[1]+"");
			cell=row.createCell(colNum++);
			cell.setCellValue(obj[2]+"");
			cell=row.createCell(colNum++);
			cell.setCellValue(obj[3]+"");
		}
		return hWorkbook;
	}
	public  InputStream getHSSFInputStream(HSSFWorkbook hWorkbook) throws IOException{
		String path=Globe.getProperty("service/general/file/xlsDownload");
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
		
	private HSSFService(){}
	
	public static HSSFService getInstance(){
		if(instance==null){
			instance= new HSSFService();
		}
		return instance;
	}
	private static HSSFService instance;
	
	public static void main(String...strings){
		DecimalFormat format=new DecimalFormat(ExcelName.RATE_TEMP);
		System.out.println(format.format((double)1/2));
	}
	public HSSFWorkbook getSheet1ByEveryDayReply(List list, Date startTime,
			Date endTime) {
		HSSFWorkbook hWorkbook=new HSSFWorkbook();
		HSSFSheet hSheet1=hWorkbook.createSheet("sheet1");
		int rowNum=0;
		int colNum=0;
		HSSFRow row=hSheet1.createRow(rowNum++);
		
		HSSFCell cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.BEGIN_TIME);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.END_TIME);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.TOTAL_COUNT);
		
		colNum=0;
		row=hSheet1.createRow(rowNum++);
		cell=row.createCell(colNum++);
		cell.setCellValue(startTime==null?"---":Constants.sdf.format(startTime));
		cell=row.createCell(colNum++);
		cell.setCellValue(Constants.sdf.format(endTime==null?new Date():endTime));
		cell=row.createCell(colNum++);
		cell.setCellValue(list.size());
		
		colNum=0;
		row=hSheet1.createRow(rowNum++);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.DATE);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.OPERATOR);
		cell=row.createCell(colNum++);
		cell.setCellValue(ExcelName.COUNTS);
//		
		Iterator it=list.iterator();
		while(it.hasNext()){
			colNum=0;
			Object[] obj=(Object[]) it.next();
			row=hSheet1.createRow(rowNum++);
			cell=row.createCell(colNum++);
			cell.setCellValue(Constants.sdf.format(obj[0]));
			cell=row.createCell(colNum++);
			cell.setCellValue(obj[1]+"");
			cell=row.createCell(colNum++);
			cell.setCellValue(obj[2]+"");
		}
		return hWorkbook;
	}
	
	
}
