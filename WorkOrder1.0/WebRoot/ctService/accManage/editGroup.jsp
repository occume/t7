<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>\
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>
<link rel="stylesheet" href="${context_path}/css/common.css"></link>	
<link rel="stylesheet" href="${context_path}/css/dialog.css"></link>	
<script type="text/javascript" src="${context_path}/js/jquery.js"></script>
<script type="text/javascript" src="${context_path}/js/common.js"></script>

<s:push  value="proup">
<div align="center">
	 <div class="popTop">
	  	<span class="adel" onclick="parent.dialog.close();"></span>修改小组名称：
	  </div>
	  <form action="/acc/acc_editGroup" method="post">
	  <input type="hidden" name="group.id" value="<s:property value="id"/>"/>
	  <div class="clearfix popErrorContent" >
	  <span style="color:red;font-size:15px;list-style:none;"><s:fielderror></s:fielderror>  </span>
		<div id="add-box" style="height:180px; width:360px; padding:20px;">
			<ul>
			
			<s:property value="group.name"/>
			
				<li><label>小组原名称：</label><s:property value="name"/></li>
				<li><label>修改为：</label><input type="text" name="group.name"/></li>
				<s:if test="#session.role.level==@com.hy.wo.util.Constants$PermissionLevel@ZHUGUAN">
						<li><label>部门：</label><s:property value="@com.hy.wo.util.constants.Department@getName(departId)"/></li>
						<input type="hidden" name="department.id" value="<s:property value="@com.hy.wo.util.Constants$DepartmentId@CTDEPARTMENT"/>"/>
				</s:if>
				<s:else>
						<li><label>部门：</label><s:select  name="department.id" list="departmentMap" listKey="key" listValue="value" tabindex="2"></s:select></li>
				</s:else>
			</ul>
		</div>
		</div>
		<div class="popBottom">
			<input type="submit"  value="确认修改" />
		</div>
		</form>
</div>
</s:push>