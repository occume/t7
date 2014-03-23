<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>\
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>
<link rel="stylesheet" href="${context_path}/css/common.css"></link>	
<link rel="stylesheet" href="${context_path}/css/dialog.css"></link>	
<script type="text/javascript" src="${context_path}/js/jquery.js"></script>
<script type="text/javascript" src="${context_path}/js/common.js"></script>

<div align="center">
	 <div class="popTop">
	  	<span class="adel" onclick="parent.dialog.close();"></span>创建小组：
	  </div>
	  <form action="/acc/acc_createGroup" method="post">
	  <div class="clearfix popErrorContent" >
	  <span style="color:red;font-size:15px;list-style:none;"><s:fielderror></s:fielderror>  </span>
		<div id="add-box" style="height:180px; width:360px; padding:20px;">
			<ul>
				<li><label>组名：</label><input name="group.name" type="text"/></li>
				<s:if test="#session.role.level>2">
					<li><label>部门：</label><s:select name="department.id" list="departmentMap" listKey="key" listValue="value" tabindex="2"></s:select></li>
				</s:if>
				<s:else>
					<input type="hidden" name="department.id" value="<s:property value="#session.department.id"/>"/>
				</s:else>
			</ul>

		</div>
		</div>
		<div class="popBottom">
			<input type="submit"  value="确 定" />
		</div>
		</form>
</div>