package com.hy.wo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;

import net.sf.json.JSONObject;

import com.hy.wo.dao.BaseDao;
import com.hy.wo.exception.WoCenterException;
import com.hy.wo.po.BatchData;
import com.hy.wo.po.Group;
import com.hy.wo.po.Role;
import com.hy.wo.po.Staff;
import com.hy.wo.po.WorkOrder;
import com.hy.wo.po.WorkOrderOper;
import com.hy.wo.util.CountResult;
import com.hy.wo.util.Pages;
import com.hy.wo.util.WorkOrderNearNow;
/**
 * 工单广场处理接口
 */
public interface WorkOrderPFService extends BaseDao<WorkOrder> {
	/**
	 * 拉单
	 * @param accName	拉单人
	 * @return	是否更新成功
	 */
	public boolean pullWorkOrder(WorkOrder workOrder,long id);
	
	/**
	 * 随机获取一条尚未被拉单的工单ID
	 * @return 未被拉单的工单号
	 */
	public String getWorkOrderIdOfPullState(Staff staff);
	
	/**
	 * 获取所有未被拉单的数据页信息
	 * @param start
	 * @param len
	 * @param getState
	 * @return
	 */
	public int getAllWorkOrderOfGetState(int start,int len,boolean getState);
	/**
	 * 任意条件工单查询
	 * @param workOrder
	 * @param start
	 * @param len
	 * @return
	 */
	public Pages<WorkOrder> searchWorkOrder(Group group,Role role,WorkOrder workOrder,
												int start,int len,Date startTime,Date endTime);
	public Pages<WorkOrder> countWorkOrder(WorkOrder workOrder, int start,int len,Date startTime,Date endTime);
	public List<WorkOrderOper> getCountByResponsed(String accName,Date startTime,Date endTime,int oper);
	public List countWorkOrderByType(WorkOrder workOrder,Date startTime,Date endTime);
	public List   countFinishRate(WorkOrder workOrder,Date startTime,Date endTime);
	public List   havingFinishTime(WorkOrder workOrder,Date startTime,Date endTime);
	public List   countWorkOrderByKeyWord(String keyWord,Date startTime,Date endTime);
	public List countWorkOrderBySatisfaction(WorkOrder workOrder,Date startTime,Date endTime);
	public List<WorkOrderOper> getCountByAccName(String accName,Date startTime,Date endTime);
	public List<WorkOrderOper>  getCountByAccNameReply(String accName,Date startTime,Date endTime);
	public List<WorkOrderOper> getCountByAccNameDeal(String accName,String parent,Date startTime,Date endTime);
	/**
	 * 查询本小组所有工单
	 * @param staff
	 * @param start
	 * @param len
	 * @return
	 */
	//本组工单广场当前处理情况
	public CountResult getCountResult(int id);
	
	public Pages<WorkOrder> searchWorkOrderWaitForDeal(Staff staff,int start,int len);
	
	public Pages<WorkOrder> searchWorkOrderAssinged(Staff staff,int start,int len);
	public Pages<WorkOrder> searchWorkOrderWaitForResponse(Staff staff,int start,int len);
	public Pages<WorkOrder> searchWorkOrderResponsed(Staff staff,int start,int len);
	public Pages<WorkOrder> searchWorkOrderWaitForMe(Staff staff,int start,int len);
	public Pages<WorkOrder> searchWorkOrderAssignMe(Staff staff,int start,int len);
	public Pages<WorkOrderNearNow> searchWorkOrderNearNow(String accountname,int start,int len);
	/**
	 * 处理工单
	 */
	public WorkOrder dealWorkOrder(Staff staff,long woId,String content,String extraCondition);
	public WorkOrder replyWorkOrder(Staff staff, long woId, String content,String extraCondition)throws WoCenterException;
	public WorkOrder respnseWorkOrder(Staff staff,long woId,String content);
	public String returnWorkOrder(Staff staff,long woId,String content);
	public String deleteWorkOrder(Staff staff,long woId,String content);
	public NameValuePair assignWorkOrder(Staff staff,Role role,long woId,String content,Integer toGroupId,Long toStaffId);
	//public String assignGM(Staff staff,long woId);
	public void backWorkorder(Staff staff,long woId,String content);
	/**
	 * 将数据放入缓存
	 * @param batchData	批量操作对象
	 */
//	public void putDataToCache(BatchData  batchData);
	
	/**
	 * 根据批量操作类型从缓存中取数据
	 * @param cacheName	缓存块名
	 * @param batchType	批量操作类型
	 * @return
	 */
	public List<BatchData> getCacheDataByBatchType(String cacheName, String batchType);
	
	/**
	 * 获取批量操作类型
	 * @param cacheName 缓存块名
	 * @return 批量操作类型
	 */
	public List<BatchData> getBatchTypeList(String cacheName);
	/**
	 * 根据缓存KEY删除缓存数据
	 * @param cacheName	缓存区域名
	 * @param cacheKey	缓存KEY
	 */
	public void removeCacheDataByKey(String cacheName, String cacheKey);
	
	/**
	 * 根据工单的拉单状态获取其总数据量
	 * @param getState	拉单状态
	 * @return	总数据量
	 */
	public int getWorkOrderTotalByGetState(boolean getState);
	
	/**
	 * 根据工单ID回复工单
	 * @param id 工单ID
	 * @param comment 回复内容
	 */
	public void replyWorkOrderById(Staff staff,long id, String comment);
	public Map<Integer,String> initGroupMap(Staff staff);
	public JSONObject getStaffOfGroup(int groupId);
	public String getRTXStaff(int groupId, Staff staf);

	public Pages<WorkOrder> searchWorkOrderLackInfo(Staff staff,
			int currentPage, int len);

	public Pages<WorkOrder> searchWorkOrderIshf(Staff staff, int currentPage,
			int len);
	public String alterIshf(Staff staff,long woId,String content);
	public String alterState(Staff staff,long woId,String content,String reason);

	public Pages<WorkOrder> searchWorkOrderByTel(String tel, int currentPage,
			int len);

	public Pages<WorkOrder> searchWorkOrderVipComp(Staff staff,
			int currentPage, int len);
	
}
