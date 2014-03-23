<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,java.text.SimpleDateFormat"%>
<%@ page import="com.hy.wo.po.WorkOrder" %>
<%@ page import="com.hy.wo.po.BatchData" %>
<%@ page import="com.you9.base.util.*" %>
<%@ page import="com.hy.wo.service.impl.WorkOrderPFServiceImpl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%	 
	WorkOrderPFServiceImpl woPfService = new WorkOrderPFServiceImpl();
	String action = request.getParameter("action");
	if(!StringUtil.isBlankOrNull(action)&&"remove".equals(action)){
		String cacheKey = request.getParameter("cacheKey");//缓存KEY
			String removeCacheName = request.getParameter("cacheName");//缓存区域
			woPfService.removeCacheDataByKey(removeCacheName, cacheKey);
	}
	
	List<BatchData> list = woPfService.getCacheDataByBatchType("batchDataCache", "all");///(List<WorkOrder>)jcsBean.buildElementInfo(batchData.getCacheName());//从指定缓存中取出所有的数据
	
	//session.invalidate();		
	List<BatchData> batchTypeList = new ArrayList<BatchData>();
	batchTypeList = woPfService.getBatchTypeList("batchTypeCache");
	System.out.println("获取所有的缓存数据list size = "+list.size()+"|获取所有的批量类型batchTypeList="+batchTypeList.size());
	session.setAttribute("cacheName", "batchDataCache");//将缓存名存储进session			
	session.setAttribute("batchTypeList", batchTypeList);
	session.setAttribute("workOrderList", list);//将缓存中所有的工单集放入到session中
	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>上海火游网络有限公司-客服后台管理中心</title>
  		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script src="/include/jquery.min.js"></script>
		<link href="/css/style.css" type="text/css" rel="stylesheet" />
		<style>
			.wrappercheck{ margin-top:10px; margin-bottom:10px;}
			.checkbox{ margin-top:10px; margin-bottom:10px; position:inherit; overflow:hidden;}
			h2{ font-size:14px;}
			.mid{ height:25px; line-height:25px; clear:both; font-size:14px;}
			.boxtitle12{float:left;width:628px;margin-left:2px;height:25px;line-height:25px;text-align:left;font-weight:bold;}
			.boxcont12{float:left;width:628px;margin-left:2px;height:25px;line-height:25px;text-align:left;}
			.btn_delete{ border:solid 1px #FF9966; display:block;width:60px; height:18px; line-height:20px; text-align:center; background-color:#fff; margin-top:2px; margin-left:35px; border-radius:5px; color:#333333}
			.btn_delete:hover{background-color:#FF9966; color:#fff;}
			
			.divinp{ margin-top:10px; clear:both; margin-bottom:10px;}
			.te{ width:1100px; height:200px; border:solid 1px #4bb5ff;border-radius:5px; font-size:14px; padding:5px;}
			.btnOk{ margin-left:40px;}
			.btnCancel{ margin-left:40px;}
			.gray{ color:#999999;}
		</style>
		<script>
			$(function(){
				$("#comment").addClass("gray");
				$("#comment").focus(function(){
					if ($(this).val()=="回复内容(限200个字)"){
						$(this).removeClass("gray");
						$(this).val("");
					}
				}).blur(function(){
						if ($(this).val()=="")
						{
							$(this).addClass("gray");
							$(this).val("回复内容(限200个字)");
						}
					});
			});	
			//表单提交参数验证
			function checkInput(){
				var commentValue = $("#comment").val();
				if(commentValue == "" ||commentValue.length == 0){
					alert("您尚未对此批量工单做出任何回复，无须提交!");
					return false;
				}
				if(commentValue.length > 200){
					alert("对此批量工单做出任何回复内容仅限200字!");
					return false;
				}
				alert("参数验证结束");
				return false;
			}	
		</script>
	</head>
	<body>
		<div class="wrappercheck">
			<div class=checkbox >
				<%if(list.size()==0){
				%>
			<span style="color:#FF0000; font-weight:100;">不存在批量操作的数据，请至操作平台选择批量操作数据！</span>
				<%}else{ %>
				<form id="batchReplyForm" name="batchReplyForm" action="/index/cache_batchReplyWorkOrder" method="post"  onsubmit="return checkInput(this);">
					<h2>待批量回复工单列表：<span style="color:#FF0000; font-weight:100;">(请谨慎操作)</span></h2>					
					<div class="mid">
						<input type="hidden" id="cacheName" name="cacheName" value="batchDataCache" /><!-- 操作的缓存类型 -->
						<select name="batchType" id="batchType" onchange="getBatchDataFromCache();">
							<c:forEach items="${batchTypeList}" var="batchData" >
								<option value="${batchData.batchType}">${batchData.batchTypeName}</option>
							</c:forEach>
						</select>				
					</div>
					<div class=titleline></div>
					<%
						int intPageSize; //一页显示的记录数
						int intRowCount; //记录总数
						int intPageCount; //总页数
						int intPage; //待显示页码 
						String strPage; 
						intPageSize = 6;  //设置一页显示的记录数
									
						strPage = request.getParameter("page");//取得待显示页码
						if(strPage==null){//表明在QueryString中没有page这一个参数，此时显示第一页数据
							intPage = 1;
						}else{//将字符串转换成整型
							intPage = Integer.parseInt(strPage);
							if(intPage<1) intPage = 1;
						} 
						
				  		intRowCount = list.size();//获取记录总数	  		
						intPageCount = (intRowCount+intPageSize-1)/intPageSize;//记算总页数
						
						//调整待显示的页码
						if(intPage>intPageCount){
							intPage = intPageCount; 
						}
						List<BatchData> lst = new ArrayList<BatchData>();
						int pageMaxSize = 0;
						if(intPage*intPageSize>=intRowCount){		//得到每页最后的一条数据数
							pageMaxSize = intRowCount;				//,假如已经是最后一条记录的时候就把最后一条记录数设置为最大数
						}else{
							pageMaxSize = intPage*intPageSize;		//每页的最后一记录的数
						}
						if(intPageCount>0){//将记录指针定位到待显示页的第一条记录上
							for(int j=intPage*intPageSize-intPageSize;j<pageMaxSize;j++){
								lst.add(list.get(j));	//得到每页显示的数据
			}
						}
					%>	
					<div class=alllistbox>
						<div class=boxtitle1>玩家账号</div>
						<div class=boxtitle2>问题类型</div>
						<div class=boxtitle3>创建时间</div>
						<div class=boxtitle12>问题描述</div>
						<div class=boxtitle2>服务器</div>
						<div class=boxtitle5>操作</div>
						<div class=clear ></div>
					</div>
					<%
						BatchData batchData = null;
						WorkOrder wo = null;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:s");
						for(int i=0;i<lst.size();i++) {
								batchData = (BatchData)lst.get(i);
								wo = batchData.getWorkOrder();
						long woId = wo.getId();
						String accountName = wo.getUserInfo().getAccountname();
						String lvlTwoName = wo.getUserInfo().getLvlTwo().getName();
						Date createTime = wo.getCreateTime();
						String createTimeStr = sdf.format(createTime);
					%>	
						<div class=alllistbox>
							<input type="hidden" name="id" id="id" value="<%=woId%>"/>
							<div class=boxcont1><%=accountName %></div>
							<div class=boxcont2><%=lvlTwoName%></div>
							<div class=orangetext><%=createTimeStr%></div>
							<div class=boxcont12>
								<%
									String memo = wo.getUserInfo().getMemo();
									memo = memo.length() >20?(memo.substring(0,20)+"..."):memo;
								 %>
								<%=memo %>
							</div>
							<div class=boxcont2><%=wo.getUserInfo().getServer().getName() %></div>
							<div class=boxcont5><a href="/ctService/batchDataOperation.jsp?action=remove&cacheName=batchDataCache&cacheKey=<%=woId%>" class="btn_delete">移除</a></div>
							<div class=clear ></div>
						</div>
					<%} %>
					<div class=titleline></div>	
										
					<!--翻页 开始-->					
					<div class="turnpage">
					  <div class="buttonselect">第 
					   <select name="" class="select40">
					   	<option><%=intPage%></option>
					   </select> 页 总共<span id="tp"><%=intPageCount%></span>页 每页<span id="ts"><%=intPageSize%></span>条
					  </div>
					  <%if(intPage<intPageCount){%>
						<div class="buttonnext"><a href="/ctService/batchDataOperation.jsp?page=<%=intPage+1%>"></a></div>
					<%}if(intPage>1){%>
						 <div class="buttonfront"><a href="/ctService/batchDataOperation.jsp?page=<%=intPage-1%>"></a></div>
					<%}if(intPageCount >0 && intPage>1 ){%>
						<div class="buttonfirst"><a href="/ctService/batchDataOperation.jsp?page=<%=1%>"></a></div>
					<%}if(intPageCount >0 && intPage<intPageCount){%>
						<div class="buttonend"><a href="/ctService/batchDataOperation.jsp?page=<%=intPageCount%>"></a></div>
					<%} %>	
					  
					  <div class=clear ></div>						
					</div>
					
					<div class="divinp">
						<textarea id="comment" name="comment" class="te">回复内容(限200个字)</textarea>
					</div>
					
					<div class="divinp">
						<input type="submit"  value="确定" class="btnOk" />
						<input type="button" onclick="javascript:window.location.href='../ctService/workOrderPlatform.jsp'" value="取消" class="btnCancel" />
					</div>
				</form>
				<%} %>
			</div>
		</div>
	</body>
</html>

