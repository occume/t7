<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>处理工单</title>    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="../../css/dialog.css"/>
	<script language="javascript" type="text/javascript" src="/include/jquery.min.js"></script>
	<script language="javascript" type="text/javascript" src="/include/jquery.livequery.js"></script>
	<script language="javascript" type="text/javascript" src="/js/ctBack.js"></script>
	<script language="javascript" type="text/javascript" src="/js/commonInit.js"></script>
	<script type="text/javascript" src="/include/dialog.js"></script>
	
	<script type="text/javascript">
	
	</script>
  </head>
  <body style="margin:0px; padding:0px;width:400px;">
	  <div class="popTop">
	  	<span class="adel" onclick="parent.dialog.close();"></span>派单情况简述
	  </div>
	  <form id="assing-form" action="/json/query_assignWorkOrder" method="POST">
	  <div class="clearfix popErrorContent" >
	  <s:fielderror></s:fielderror>
		<div style="height:180px; width:360px; padding:20px;">
		指定人员:
		 	<input type="hidden" name="id" value="<s:property value="workOrderId"/>"/>
 			<s:select list="groupMap" name="toGroupId" id="group" headerKey="0" headerValue="请选择小组"></s:select>
			<select id="staff" name="toStaffId"><option value="0">请选择员工</option></select>
		 	<input type="hidden" name="departId" value="${param.departId}"/>
		 	<textarea id="assign-desc" name="description" style="width:350px;height:150px;"></textarea>
		</div>
		</div>
		<div class="popBottom">
			<input type="submit"  value="确 定" />
		</div>
		</form>
</body>
</html>