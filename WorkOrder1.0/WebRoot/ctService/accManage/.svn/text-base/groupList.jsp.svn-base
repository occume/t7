<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/checkLogin.jsp"%>
<%@ page import="com.hy.wo.util.constants.*;"%>
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
								<a href="/acc/acc_manageStaff">管理本部门员工</a>
							</li>
							<li class="accPage"><a href="/ctService/accManage/pageManage.jsp">管理页面</a></li>
							<li class="accGroup">
								<span class="topicMenu">管理小组</span>
								<ul class="alter">
									<li id="createGroup">创建小组</li>
								</ul>
							</li>
					</s:if>
					<s:if test="#session.role.level==3">
							<li class="accAll">
								<a href="/acc/acc_manageStaff">管理所有员工</a>
							</li>
							<li class="accGroup">
								<span class="topicMenu">管理小组</span>
								<ul class="alter">
									<li id="createGroup">创建小组</li>
								</ul>
							</li>
							<li class="accPage"><a href="/ctService/accManage/pageManage.jsp">管理页面</a></li>
							<li class="accFaq"><a href="/faq/getAllFaq">管理FAQ</a></li>
					</s:if>
			</ul>
		</div>
		<div id="acc-manage-right" style="height:auto;">
			<h3>小组信息列表：</h3>
			<div id="search-box">
				<form action="/acc/acc_findStaffByAccName" method="get">
					<label id="emp">小组编号：</label> <input id="name" type="text" name="accName" />
					<input  id="send" type="submit"  value="GO!"/>
				</form>	
			</div>
			<div id="info-list">
				<table border="1" cellpadding="0" cellspacing="0" width="90%">
					<tr style="height:30px;">
						<th>编号</th>
						<th>组名</th>
						<th>所属部门</th>
						<th style="width:180px;">创建时间</th>
						<th>编辑</th>
						<th>删除</th>
					</tr>
					<s:if test="pages.resultList.size()>0">
					<s:iterator value="pages.resultList" status="step">
				
							<tr style="height:26px;">
								<td class="id"><s:property value="id"/></td>
								<td class="name"><s:property value="name"/></td>
								<td class="departId"><s:property value="@com.hy.wo.util.constants.Department@getName(departId)"/></td>
								<td style="width:180px;"><s:date name="createDate" format="yyyy-MM-dd hh:mm:ss" /></td>
								<td class="edit"><a onclick="dialog.openUrl('/acc/acc_toEditGroup?group.id=<s:property value="id"/>','auto','auto');" >Edit</a></td>
								<td class="deleteGroup">Delete</td>
							</tr>

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
