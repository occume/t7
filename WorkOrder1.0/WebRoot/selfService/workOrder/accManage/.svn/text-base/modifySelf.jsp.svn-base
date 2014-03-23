<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>\
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>
<script type="text/javascript" src="${context_path}/js/jquery.js"></script>
<script type="text/javascript" src="${context_path}/js/roleRecovery.js"></script>
<div>
	<s:push value="staff">
		您的当前信息：<br/>
		工号：<s:property value="accName"/><br/>
		昵称：<s:property value="nickName"/><br/>
		权限级别：<s:property value="role.name"/><br/>
		所在小组：<s:property value="group.name"/><br/>
		所在部门：<s:property value="department.name"/><br/>
	</s:push>
	<form action="/wo/acc_modifyPassword" method="post">
		<div id="modifyP">
			修改密码
		</div>
	</form>
</div>