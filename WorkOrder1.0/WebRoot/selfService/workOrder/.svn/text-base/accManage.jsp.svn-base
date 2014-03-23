<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>\
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>账号管理</title>    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="../css/common.css"></link>	
	
	<script type="text/javascript" src="${context_path}/js/jquery.js"></script>
	<script type="text/javascript" src="../js/common.js"></script> 
  </head>
  
  <body>
  <h3>当前位置：首页>账号管理</h3>
  <s:push value="staff">
   <h2>欢迎您：[<s:property value="accName"/>]，祝工作愉快！</h2>
   <h3>当前登录角色：[<s:property value="role.name"/>]</h3>
   
  	<div id="acc-manage-left">
  		您可以进行以下操作：<br/>
  		<ul>
	  		<s:iterator value="role.permissions">
		  		<s:if test="operation.equals('account')">
		  			<li>
		  				<a href="/wo/acc_manage?manageId=${id}" target="manage"><s:property value="name"/></a>
		  			</li>
		  		</s:if>
  			</s:iterator>
  		</ul>	
  	</div>
  	<div id="acc-manage-right">
  		<iframe name="manage" width="100%" height="auto" scrolling="no" frameborder="0" ></iframe>
  	</div>
  </s:push>	
  </body>
</html>