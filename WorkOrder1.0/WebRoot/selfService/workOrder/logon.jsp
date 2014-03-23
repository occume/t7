<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>\
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>员工登录</title>    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="../css/common.css"></link>	
	<script type="text/javascript" src="../js/common.js"></script> 	 	
  </head>
  
  <body>
  	<div id="logon-box">
  		<form action="/wo/acc_logon" method="post">
  			<input type="text" name="accName"/>
  			<input type="password" name="password"><br/>
  			<input type="submit"/>
  		</form>
  	</div>
  </body>
</html>
