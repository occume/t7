<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>\
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>
<link rel="stylesheet" href="${context_path}/css/common.css"></link>	
<script type="text/javascript" src="${context_path}/js/jquery.js"></script>
<script type="text/javascript" src="${context_path}/js/common.js"></script>
<div>
	<table>
		<tr>
			<th>工号</th>
			<th>昵称</th>
			<th>职务</th>
			<th>所属小组</th>
			<th>所属部门</th>
			<th>编辑信息</th>
			<th>删除</th>
		</tr>
		<s:iterator value="pages.resultList">
			<tr>
				<td><s:property value="accName"/></td>
				<td><s:property value="nickName"/></td>
				<td><s:property value="role.name"/></td>
				<td><s:property value="group.name"/></td>
				<td><s:property value="department.name"/></td>
				<td><a href="/acc/acc_toEdit?id=<s:property value="id"/>">Edit</a></td>
				<td>
					<s:if test="#session.staff.role.level==2"><a href="/acc/acc_delete?id=<s:property value="id"/>">Delete</a></s:if>
					<s:if test="#session.staff.role.level==3"><a href="/acc/acc_realDelete?id=<s:property value="id"/>">Delete</a></s:if>
					
				</td>
			</tr>
			
		</s:iterator>
		<tr><td>
			<s:push value="staff">
				<s:if test="role.level==2"><a href="/acc/acc_toAddStaffToDepartment">ADD</a></s:if>
				<s:if test="role.level==3"><a href="/acc/acc_toAddStaff">ADD</a></s:if>
			</s:push></td>
		</tr>
	</table>
</div>