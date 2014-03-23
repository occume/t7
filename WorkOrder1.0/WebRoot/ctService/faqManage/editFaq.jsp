<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>\
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>
<link rel="stylesheet" href="${context_path}/css/common.css"></link>	
<link rel="stylesheet" href="${context_path}/css/dialog.css"></link>	
<script type="text/javascript" src="${context_path}/js/jquery.js"></script>
<script type="text/javascript" src="${context_path}/include/dialog.js"></script>
<script type="text/javascript" src="${context_path}/js/faq.js"></script>
<script type="text/javascript" src="../include/My97DatePicker/WdatePicker.js"></script>
<s:action name="initIssueSelect" namespace="/faq" executeResult="true"></s:action>
<div align="center">
 <form id="saveFaq" action="/faq/editFaq" method="post">
	 <div class="popTop">
	  	<span class="adel" onclick="parent.dialog.close();"></span> 编辑Faq：
	  </div>
	 
	  
	  <div class="clearfix popErrorContent" >
	  <span style="color:red;font-size:15px;list-style:none;"><s:fielderror></s:fielderror>  </span>
	  <s:push value="faq">
	  <input type="hidden" name="id" value="<s:property value="id"/>"/>
		<div id="add-box" style="height:350px;width:380px; ">
			<ul style="height:100%;">
				<li><label>标题：</label><input type="text" name="title" style="width:200px;" value="<s:property value="title"/>"/></li>
				<li><label>游戏：</label><s:select name="gameName" list="{'风云传奇'}" tabindex="2"></s:select></li>
				<li><label>标签：</label><s:property value="sortType"/></li>
				<li><label>类型：</label><s:property value="type"/></li>
				<li><label>版本号：</label><input type="text" name="version" style="width:200px;"/></li>
				<li><label>来源：</label><input type="text" name="src" style="width:200px;"/></li>
				<li><label>可见者：</label>
						<s:if test="toUser==true">玩家</s:if>
						<s:else>客服</s:else>
					</li>
				<li><label>修改时间：</label><input type="text" value="<s:property value="createTime"/>" name="createTime" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})" /></li>
				<li>
						描述:
						<s:textarea name="descrip" id="faqDescrip"/>
				</li>
			</ul>

		</div>
		</s:push>
		</div>
		<div class="popBottom">
			<input type="submit"  value="确 定" />
		</div>
		</form>
</div>