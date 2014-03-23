package com.hy.wo.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.hibernate.Session;

import com.hy.wo.dao.BaseDao;
import com.hy.wo.exception.WoCenterException;
import com.hy.wo.po.FileUpLoad;
import com.hy.wo.po.Member;
import com.hy.wo.po.Servers;
import com.hy.wo.po.Staff;
import com.hy.wo.po.WorkOrder;
import com.hy.wo.util.Pages;

public interface WorkOrderService extends BaseDao<WorkOrder>{
	/*** 初始化服务器名称Map**/
	public Map<Integer, String> initServerMap();
	/*** 初始化一级问题类型**/
	public Map<Integer,String> initIssueOneMap(int pid);
	/*** 初始化二级问题类型**/
	public Map<Integer,String> initIssueTwoMap(int pid);
	/*** 初始化违规类型**/
	public Map<Integer,String> initReportKindsMap();
	/***初始工单来源类型***/
	public Map<Integer,String> initSourceMap();
	/***初始紧急程度类型***/
	public Map<Integer,String> initUrgencyMap();
	/***初始化角色职业类型***/
	public Map<Integer,String> initClassCategoryMap();
	/***初始化游戏名称类型***/
	public Map<Integer,String> initGameMap();
	/***初始化咨询问题类型***/
	public Map<Integer,String> initAdvisoryMap();
	/***初始化游戏大区类型***/
	public Map<Integer,String> initAreaMap();
	/***初始化充值类型类型***/
	public Map<Integer,String> initRechargeTypeMap();
	public String initGoodsItems(String keyWord);
	public List initList(Class clazz);
	
	
	/**
	 * 获取玩家提交所有问题的方法带状态
	 * @param start
	 * @param len
	 * @param accountname
	 * @param status
	 * @return
	 */
	public Pages<WorkOrder> getAllWorkOrderOfUserByStatus(int start,int len,String accountname,String status);
	public int getAllWorkOrderCountOfUserByStatus(String accountname,String status);
	
	/**
	 * 回去工单详细信息
	 * @param id
	 * @return
	 */
	public WorkOrder getWorkOrderDetail(long id);
	/**
	 * 保存玩家信息
	 * @param wo
	 * @param session
	 */
	public void saveDefaultMemberInfo(WorkOrder wo);
	/**
	 * 处理账号恢复问题
	 * @param wo
	 * @return
	 */
	public void  recoveryHandle(WorkOrder wo,String[] uploadFileName)throws WoCenterException;
	/**
	 * 处理角色异常问题
	 * @param wo
	 * @return
	 */
	public void abnormalHandle(WorkOrder wo,String[] uploadFileName) throws WoCenterException;
	/**
	 * 账号解封问题
	 * @param wo
	 * @return
	 */
	public void accountnamelockHandle(WorkOrder wo,String[] uploadFileName)throws WoCenterException;
	/**
	 * 处理游戏运行问题
	 * @param wo
	 * @return
	 */
	public void gameRunHandle(WorkOrder wo,String[] uploadFileName)throws WoCenterException;
	/**
	 * 处理物品丢失问题
	 * @param wo
	 * @return
	 */
	public void goodsLostHandle(WorkOrder wo,String[] uploadFileName)throws WoCenterException;
	/**
	 * 处理服务器故障问题
	 * @param wo
	 * @return
	 */
	public void serverDefaultHandle(WorkOrder wo,String[] uploadFileName)throws WoCenterException;
	/**
	 * 处理游戏bug问题
	 */
	public void gameBugHandle(WorkOrder wo,String[] uploadFileName)throws WoCenterException;
	/**
	 * 处理违规举报问题
	 * @param wo
	 * @return
	 */
	public void illegalReportHandle(WorkOrder wo,String[] uploadFileName)throws WoCenterException;
	public void rechargeHandle(WorkOrder wo,String[] uploadFileName)throws WoCenterException;
	/**
	 * 处理其他问题
	 * @param wo
	 * @return
	 */
	public void othersHandle(WorkOrder wo,String[] uploadFileName)throws WoCenterException;
	public void suggestionHandle(WorkOrder wo)throws WoCenterException;
	public void vipCompHandle(WorkOrder wo)throws WoCenterException;
	public void advisoryHandle(WorkOrder wo)throws WoCenterException;
	/**
	 * 处理文件上传的方法
	 * @param wo
	 * @param upload
	 * @param uploadFileName
	 */
	public void uploadFileHandle(WorkOrder wo,String[] uploadFileName);
	public void uploadFileHandle(WorkOrder wo, File[] upload,String[] uploadFileName,String rp,String name);
	/**
	 * 验证游戏帐号密码是否正确并获取玩家的电话号码和联系QQ信息
	 * @param accountname	游戏帐号
	 * @param password		游戏密码
	 * @return Member对象
	 */
	public Member checkPassword(String accountname, String password);
	/**
	 * 验证游戏帐号是否存在
	 * @param accountname 游戏帐号
	 * @return	true:存在|false:不存在
	 */
	public boolean checkAcccountName(String accountname);;
	/**
	 * 处理玩家的评价
	 * @return
	 */
	public void saveEvaluation(WorkOrder wo,long id)throws WoCenterException;
	/**
	 * 处理玩家问题补充
	 * @param wo
	 * @param id
	 * @return
	 */
	public String saveIssueAdditional(long id,String add,Staff staff,boolean fromUser,String[] uploadFileName);
	/**
	 * 获取用户提交问题当前处理情况
	 * @param accName
	 * @return
	 */
	public String makeDealInfo(String accName);
	/**
	 * 处理客服创建的工单
	 * @return
	 */
	public long createWorkOrder(WorkOrder wo,Staff staff,int immediate,int groupId,long staffid);
	public JSONObject readContactInfoOfUser(String accName);
	public boolean updateWorkORder(WorkOrder wo,long workOrderId);
	/***工单广场排行榜***/
//	public Map<String, Integer> getWorkOrderMap();
//	public Map<String, Integer> dealWorkOrderMap();
//	public Map<String, Integer> replyWorkOrderMap();
	public Document getRangeDocument(String type);
	public JSONArray getRangeObjectOfToday();
	public JSONArray getRangeObjectOfMonth();
	public JSONObject getJsonObject(Class clazz,int pid);
	//public List<Servers> getServerList();
	//工单界面表格格式管理
	public Servers getServer(int id);
	public String addServer(int id,String name);
	public void updateServersInfo(Map<Integer,String> map);
	public String deleteServer(int id);
	public String editGame(int id,String name);
	public String addGame(String name);
	public String deleteGame(int id);
	public String addReportKind(String name);
	public String editReportKind(int id, String name);
	public String deleteReportKind(int id);
	public String addRechargeType(String name);
	public String editRechargeType(int id, String name);
	public String deleteRechargeType(int id);
	public String addIssue(int id,String name);
	public String editIssue(int id, String name);
	public String deleteIssue(int id);
	public String addAdvisory(String name);
	public String editAdvisory(int id, String name);
	public String deleteAdvisory(int id);
	public void innerGameBugHandle(WorkOrder wo, String[] uploadFileName) throws WoCenterException;
	public void editPtReply(Staff staff,long id,String content,String reason)throws WoCenterException;
	public String makeServerMap();
	public void deletePtReply(Staff staff, long id, String comment)throws WoCenterException;
	public String makerechargeTypeMap();
	public void vipBlessHandle(WorkOrder wo, String[] uploadFileName);
	
}
