<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>操作已成功！</title>    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link rel="stylesheet" type="text/css" href="../../css/dialog.css"/>
  </head>
  <body style="margin:0px; padding:0px;">
	  <div class="popTop">
	  	<span class="adel" onclick=""></span>Result Page
	  </div>
	  <div class="clearfix popErrorContent" >
		<div style="height:180px; width:360px; padding:20px;">
			<s:fielderror></s:fielderror>
			<s:actionerror/>
			<s:actionmessage/>
			${message}
		</div>
		</div>
		<div class="popBottom">
			<input type="button" onclick="" value="确定" />
		</div>
		<script>
			setTimeout(function(){
				<s:property value="returnJsStr"/>
			},2000);
			
		</script>
		
</body>
</html>
