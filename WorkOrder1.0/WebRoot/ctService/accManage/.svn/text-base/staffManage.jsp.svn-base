<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/checkLogin.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>上海火游网络有限公司-客服后台管理中心</title>


<link href="/css/htstyle.css" type=text/css rel=stylesheet />
<link href="/css/common.css" type=text/css rel=stylesheet />
<link rel="stylesheet" type="text/css" href="/css/dialog.css"/>
<script language="javascript" type="text/javascript" src="/include/jquery.min.js"></script>
<script language="javascript" type="text/javascript" src="/js/common.js"></script>
<script language="javascript" type="text/javascript" src="/include/dialog.js"></script>
<script type="text/javascript">
	
</script>
</head>
	<body>
		<!--title start -->
		<%@include file="../common/exit.jsp"%>
		<!--title end -->
		<div class="location">
			当前位置：<a href="/ctService/htIndex.jsp">首页</a> >账号管理
			<hr/>
		</div>
		<div id="acc-manage">
	<s:push value="staff">
		<div id="acc-manage-left"> 
			<h3>操作导航：</h3> 
			<ul>
					<li>
						<span><a href="/acc/acc_modifySelf" class="xx">修改个人信息</a></span>
					</li>
					<s:if test="#session.role.level==2">
							<li class="accDepart">
								<span class="topicMenu">管理本部门员工</span>
								<ul class="alter">
									<li id="addStaff">增添员工</li>
								</ul>
							</li>
							<li class="accGroup"><a href="/acc/acc_manageGroup">管理小组</a></li>
							<li class="accPage"><a href="/ctService/accManage/pageManage.jsp">管理页面</a></li>
							<li class="accFaq"><a href="/faq/getAllFaq">管理FAQ</a></li>
					</s:if>
					<s:if test="#session.role.level==3">
							<li class="accAll">
								<span class="topicMenu">管理所有员工</span>
								<ul class="alter">
									<li id="addStaff">增添员工</li>
								</ul>
							</li>
							<li class="accGroup"><a href="/acc/acc_manageGroup">管理小组</a></li>
							<li class="accPage"><a href="/ctService/accManage/pageManage.jsp">管理页面</a></li>
							<li class="accFaq"><a href="/faq/getAllFaq">管理FAQ</a></li>
					</s:if>
					<s:if test="#session.role.level==1">
					<li class="accFaq"><a href="/faq/getAllFaq">管理FAQ</a></li>
					</s:if>
					
			</ul>
		</div>
		<div id="acc-manage-right" style="height:auto;">
			<h3>员工信息列表：</h3>
			<div id="search-box" class="defaultHint">
				<form action="/acc/acc_findStaffByAccName" method="get">
					<label id="emp">员工账号：</label> <input id="name" type="text" name="accName" />
					<input  id="send" type="submit"  value="GO!"/>
				</form>	
			</div>
			<div id="info-list">
				<table border="1" cellpadding="0" cellspacing="0">
					<tr>
						<th>工号</th>
						<th>昵称</th>
						<th>坐席</th>
						<th>职务</th>
						<th>所属小组</th>
						<th>所属部门</th>
						<th>编辑信息</th>
						<th>删除</th>
					</tr>
					<s:if test="pages.resultList.size()>0">
					<s:iterator value="pages.resultList" status="step">
					<input type="hidden" value="<s:property value="id" />" id="hid${ step.index}"/>
					<s:if test="#session.role.level==3"><!-- if  管理员 -->
							<tr>
								<td><s:property value="accName"/></td>
								<td><s:property value="nickName"/></td>
								<td><s:property value="zuoXi"/></td>
								<td><a href="/acc/acc_manageStaff?role.id=<s:property value="role.id"/>"><s:property value="role.name"/></a></td>
								<td><a href="/acc/acc_manageStaff?group.id=<s:property value="group.id"/>"><s:property value="group.name"/></a></td>
								<td><a href="/acc/acc_manageStaff?department.id=<s:property value="department.id"/>"><s:property value="department.name"/></a></td>
								<td><a onclick="dialog.openUrl('/acc/acc_toEdit?id=<s:property value="id"/>','auto','auto');" href="javascript:void(0)">Edit</a></td>
								<td><a id="delete"  title="${step.index}" href="javascript:void(0)">Delete</a></td>
							</tr>
						</s:if>
						<s:else>  <!-- if 主管 -->
								<tr>
								<td><s:property value="accName"/></td>
								<td><s:property value="nickName"/></td>
								<td><s:property value="zuoXi"/></td>
								<td><a href="/acc/acc_manageStaff?role.id=<s:property value="role.id"/>"><s:property value="role.name"/></a></td>
								<td><a href="/acc/acc_manageStaff?group.id=<s:property value="group.id"/>"><s:property value="group.name"/></a></td>
								<td><s:property value="department.name"/></td>
								<s:if test="role.level<2">
									<td><a onclick="dialog.openUrl('/acc/acc_toEdit?id=<s:property value="id"/>','auto','auto');" href="javascript:void(0)">Edit</a></td>
									<td><a id="delete"  title="${step.index}" href="javascript:void(0)">Delete</a></td>
								</s:if>
								<s:else>
									<td>Edit</td>
									<td>Delete</td>
								</s:else>
							</tr>
							
						</s:else>
					</s:iterator>
					</s:if>
					<s:else><tr><td>暂无信息</td></tr></s:else>
	</table>
	
			<s:url var="first">
				<s:param name="currentPage" value="1"></s:param>
				<s:param name="department.id" value="department.id"></s:param>
				<s:param name="group.id" value="group.id"></s:param>
				<s:param name="role.id" value="role.id"></s:param>
			</s:url>
			<s:url var="previous">
				<s:param name="currentPage" value="pages.currentPage-1"></s:param>
				<s:param name="department.id" value="department.id"></s:param>
				<s:param name="group.id" value="group.id"></s:param>
				<s:param name="role.id" value="role.id"></s:param>
			</s:url>
			<s:url var="last">
				<s:param name="currentPage" value="pages.lastPage"></s:param>
				<s:param name="department.id" value="department.id"></s:param>
				<s:param name="group.id" value="group.id"></s:param>
				<s:param name="role.id" value="role.id"></s:param>
			</s:url>
			<s:url var="next">
				<s:param name="currentPage" value="pages.currentPage+1"></s:param>
				<s:param name="department.id" value="department.id"></s:param>
				<s:param name="group.id" value="group.id"></s:param>
				<s:param name="role.id" value="role.id"></s:param>
			</s:url>
	
</div>
<%@ include file="../common/page.jsp" %>
		</div>
	</s:push>
	<div id="fooot">2010 All Rights Reserved 版权所有 上海火游网络科技有限公司</div>
	</div>
</body>
</html>