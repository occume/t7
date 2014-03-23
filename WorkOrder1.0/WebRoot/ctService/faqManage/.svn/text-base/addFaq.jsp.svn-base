<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>
<s:action name="initIssueSelect" executeResult="true"namespace="/faq"></s:action>
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
	<title>增加一条FAQ</title> 
</head> 

<body>
	<div id="faq-rep">
	  <div class="top">
	  	<div id="logo-box"><h1>添加Faq</h1></div>
	  </div>
	 
	  <span style="color:red;font-size:15px;list-style:none;"><s:fielderror></s:fielderror>  </span>
	 
	  	<form id="saveFaq" action="/faq/saveFaq" method="post">
		<div id="content">
			<div id="fom" style="border:none;">
				<div class="lab"><label>标题：</label><span><input type="text" name="title" style="width:200px;"/></span></div>
				<div class="lab"><label>游戏：</label><span><s:select name="gameName" list="{'风云传奇'}" tabindex="2"></s:select></span></div>
				<div class="lab"><label>标签：</label><span><s:select name="sortType" list="{'常见问答','通知','临时问答','游戏知识'}"  tabindex="2"></s:select></span></div>
				<div class="lab"><label>类型：</label><span><s:select name="type" list="#session.issueInfo" listKey="value" listValue="value"  tabindex="2"></s:select></span></div>
				<div class="lab"><label>版本：</label><span><input type="text" name="version" style="width:200px;"/></span></div>
				<div class="lab"><label>来源：</label><span><input type="text" name="src" style="width:200px;"/></span></div>
				<div class="lab"><label>可见者：</label>
					<span>玩家<input type="radio"  name="touser"  value="user"/></span>
		  			<span>客服<input type="radio" name="touser"  value="cs"/></span>
				</div>
				
				
				<textarea name="descrip" rows="" cols=""></textarea> <br/>
				<input id="yes" type="submit"  value="确 定" />
			</div>	
				<div>
			</div>
		</div>
	</form>
</div>
</body>
</html>
