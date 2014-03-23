<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link rel="stylesheet" href="${context_path}/css/common.css"></link>	
	<link rel="stylesheet" href="${context_path}/css/dialog.css"></link>	
	<script type="text/javascript" src="${context_path}/js/jquery.js"></script>
	<script type="text/javascript" src="${context_path}/include/dialog.js"></script>
	<script type="text/javascript" src="${context_path}/js/faq.js"></script>
	<script type="text/javascript" src="../include/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		*{margin:0;padding:0;}
	</style>
	<title>FAQ回复</title> 
</head> 

<body>
	<div id="faq-rep">
	  <div class="top">
	  	<div id="logo-box"><h1>回复Faq</h1></div>
	  </div>
	 
	  <span style="color:red;font-size:15px;list-style:none;"><s:fielderror></s:fielderror>  </span>
	  
	  	
		<div id="content">
			
			<div id="success-msg"><h1>${message}</h1></div>
			
			<script type="text/javascript">
				if(window.parent != window){
					 setTimeout(function(){
					 	parent.dialog.close();
					 	window.parent.location.href=window.parent.location.href;
					 },2000);
				}
			</script>
		</div>
		
		
		
</div>
</body>
</html>
