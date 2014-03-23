<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>\
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>
<link rel="stylesheet" href="${context_path}/css/common.css"></link>	
<link rel="stylesheet" href="${context_path}/css/dialog.css"></link>	
<script type="text/javascript" src="${context_path}/js/jquery.js"></script>
<script type="text/javascript" src="${context_path}/include/dialog.js"></script>
<script type="text/javascript" src="${context_path}/js/faq.js"></script>
<s:action name="initIssueSelect" namespace="/faq" executeResult="true"></s:action>
<div align="center">
	 <div class="popTop">
	  	<span class="adel" onclick="parent.dialog.close();"></span>添加一条Faq：
	  </div>
	  <form id="saveFaq" action="/faq/saveFaq" method="post">
	  <div class="clearfix popErrorContent" >
	  <span style="color:red;font-size:15px;list-style:none;"><s:fielderror></s:fielderror>  </span>
		<div id="add-box" style="height:285px; width:380px; ">
			<ul>
				<li><label>标题：</label><input type="text" name="title" style="width:200px;"/></li>
				<li><label>游戏：</label><s:select name="gameName" list="{'风云传奇'}" tabindex="2"></s:select></li>
				<li><label>标签：</label><s:select name="sortType" list="{'常见问答','通知','临时问答','游戏知识'}"  tabindex="2"></s:select></li>
				<li><label>类型：</label><s:select name="type" list="#session.issueInfo" listKey="value" listValue="value"  tabindex="2"></s:select></li>
				<li><label>版本号：</label><input type="text" name="version" style="width:200px;"/></li>
				<li><label>来源：</label><input type="text" name="src" style="width:200px;"/></li>
				<li><label>可见者：</label>玩家 <input style="width:20px;" type="radio" name="touser" value="user"/> 客服<input style="width:20px;" type="radio" name="touser" value="cs"/></li>
				
				<li>
						描述:
						<s:textarea name="descrip" id="faqDescrip"/>
				</li>
			</ul>

		</div>
		</div>
		<div class="popBottom">
			<input type="submit"  value="确 定" />
		</div>
		</form>
</div>