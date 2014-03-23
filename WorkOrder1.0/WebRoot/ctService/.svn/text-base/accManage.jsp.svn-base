<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="common/checkLogin.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--<meta http-equiv="x-ua-compatible" content="ie=7" />-->
<title>上海火游网络有限公司-客服后台管理中心</title>
<link href="/css/htstyle.css" type=text/css rel=stylesheet />
<link href="/css/editstyle.css" type=text/css rel=stylesheet />
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
		<%@include file="common/exit.jsp"%>
		<!--title end -->
		<div class="location">
			当前位置：<a href="/ctService/htIndex.jsp">首页</a> &gt;&nbsp;账号管理
			<hr/>
		</div>
		<div id="acc-manage">
	<s:push value="staff">
		<div id="acc-manage-left"> 
			<h3>操作导航：</h3> 
			<ul>
					<li class="alterMe">
						<span class="topicMenu">修改个人信息</span>
						<ul class="alter">
							<li id="alterPsw">修改密码</li>
							<li id="alterNick">修改昵称</li>
						</ul>
					</li>
					<s:if test="#session.role.level==2">
							<li class="accDepart"><a href="/acc/acc_manageStaff">管理本部门员工</a></li>
							<li class="accGroup"><a href="/acc/acc_manageGroup">管理小组</a></li>
							<li class="accPage"><a href="/ctService/accManage/pageManage.jsp">管理页面</a></li>
					</s:if>
					<s:if test="#session.role.level>1">
							<li class="accAll"><a href="/acc/acc_manageStaff">管理所有员工</a></li>
							<li class="accGroup"><a href="/acc/acc_manageGroup">管理小组</a></li>
							<li class="accPage"><a href="/ctService/accManage/pageManage.jsp">管理页面</a></li>
							<li class="accFaq"><a href="/faq/getAllFaq">管理FAQ</a></li>
					</s:if>
					<s:if test="#session.role.level==1">
							<li class="accFaq"><a href="/faq/getAllFaq">管理FAQ</a></li>
					</s:if>
			</ul>
		</div>
		<div id="acc-manage-right">
			<h3>当前个人信息：</h3>
			<ul>
				<li>工号：<s:property value="accName"/></li>
				<li>昵称：<s:property value="nickName"/></li>
				<li>职位：<s:property value="role.name"/></li>
				<li>小组：<s:property value="group.name"/></li>
				<li>部门：<s:property value="department.name"/></li>
			</ul>
		</div>
	</s:push>
	<div id="fooot">2010 All Rights Reserved 版权所有 上海火游网络科技有限公司</div>
	</div>
	
</body>
</html>