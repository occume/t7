<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List,java.util.ArrayList" %>
<%@ page import="com.hy.wo.po.WorkOrder"%>
<%@ page import="com.hy.wo.po.WorkOrderUserInfo"%>
<%@ page import="com.hy.wo.cache.BatchOperationCacheManager" %>
<%
	BatchOperationCacheManager 	instance = BatchOperationCacheManager.getInstance();
	String cacheName = "batchReplyCache";
	List<WorkOrder> list = new ArrayList<WorkOrder>();
	
	WorkOrder wo = null;
	WorkOrderUserInfo userInfo = null;
	for(int i=0;i<10;i++){
		wo = new WorkOrder();
		userInfo = new WorkOrderUserInfo();
		userInfo.setAccountname("woke00" + i);
		wo.setUserInfo(userInfo);
		list.add(wo);
	}
	request.getSession().setAttribute("cacheName", cacheName);
	
	//JCSAdminBean jcsBean = new JCSAdminBean();
	
	request.getSession().setAttribute("cacheDataList", list);
	response.sendRedirect("/jcs/cacheDataDetail.jsp");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'batchDataOperation.jsp' starting page</title>
  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
