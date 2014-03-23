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
  </head>
 
  <body style="margin:0px; padding:0px;width:400px;">
	  <div class="popTop">
	  	<span class="adel" onclick="parent.dialog.close();"></span>回访内容简述：
	  </div>
	  <form action="/json/query_responseWorkOrder" method="post">
	  <div class="clearfix popErrorContent" >
		<div style="height:180px; width:400px; padding:20px;">
		 	<input type="hidden" name="id" value="${param.woId}"/>
		 	<textarea name="description" style="width:350px;height:150px;"></textarea>
		</div>
		</div>
		<div class="popBottom">
			<input type="submit"  value="确 定" />
		</div>
		</form>
</html>