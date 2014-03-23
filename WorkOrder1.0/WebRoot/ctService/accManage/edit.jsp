<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>\
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>
<link rel="stylesheet" href="${context_path}/css/common.css"></link>	
<link rel="stylesheet" href="${context_path}/css/dialog.css"></link>	
<script type="text/javascript" src="${context_path}/js/jquery.js"></script>
<script type="text/javascript" src="${context_path}/js/common.js"></script>

<s:push value="staff">
<div align="center">
	 <div class="popTop">
	  	<span class="adel" onclick="parent.dialog.close();"></span>添加员工：
	  </div>
	  <form action="/acc/acc_edit" method="post">
	  <input type="hidden" name="id" value="<s:property value="id"/>"/>
	  <div class="clearfix popErrorContent" >
	  <span style="color:red;font-size:15px;list-style:none;"><s:fielderror></s:fielderror>  </span>
		<div id="add-box" style="height:180px; width:360px; padding:20px;">
			<ul>
				<li><label>工号：</label><s:property value="accName"/></li>
				<li><label>昵称：</label><s:property value="nickName"/></li>
				<li><label>职位：</label><s:select name="role.id" list="roleMap" listKey="key" listValue="value" tabindex="2"></s:select></li>
				<li><label>小组：</label><s:select name="group.id" list="groupMap" listKey="key" listValue="value" tabindex="2"></s:select></li>
				<li><label>坐席：</label><input name="zuoXi" value="<s:property value="zuoXi"/>"/></li>
				<s:if test="#session.staff.role.level==@com.hy.wo.util.Constants$PermissionLevel@ZHUGUAN">
						<li><label>部门：</label><s:property value="department.name"/></li>
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