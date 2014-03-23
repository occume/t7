<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/checkLogin.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上海火游网络有限公司-客服后台管理中心</title>

<link rel="stylesheet" href="/css/accManage.css"></link>	
<link href="/css/htstyle.css" type=text/css rel=stylesheet />
<link href="/css/common.css" type=text/css rel=stylesheet />
<link rel="stylesheet" type="text/css" href="/css/dialog.css"/>
<script type="text/javascript" src="/include/jquery.min.js"></script>
<script type="text/javascript" src="/js/faq.js"></script>
<script type="text/javascript" src="/include/dialog.js"></script>

</head>
	<body>
		<!--title start -->
		<%@include file="../common/exit.jsp"%>
		<!--title end -->
		<div class="location">
			当前位置：<a href="/ctService/htIndex.jsp">首页</a>  > FAQ
			<hr/>
		</div>
		<div id="acc-manage">
		<div id="acc-manage-left"> 
			<h3>操作导航：</h3> 
			<ul>
					<li>
						<span><a href="/acc/acc_modifySelf" class="xx">修改个人信息</a></span>
					</li>
					<s:if test="#session.role.level==2">
							<li class="accDepart"><a href="/acc/acc_manageStaff">管理本部门员工</a></li>
							<li class="accGroup"><a href="/acc/acc_manageGroup">管理小组</a></li>
							<li class="accPage"><a href="/ctService/accManage/pageManage.jsp">管理页面</a></li>
					</s:if>
					<s:if test="#session.role.level==3">
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
		<div id="acc-manage-right" style="height:auto;">
			<h3>客服知识题库：</h3>
			<div id="faq-search-box">
				<form action="/faq/getFaqsByDescrip" method="get">
					<label id="emp">关键字</label> <input id="name" type="text" name="descrip" />
					<input  id="send" type="submit"  value="GO!"/>
				</form>	
			</div>
			<div id="faq-unique">
			<s:push value="faq">
			<ul>
				<li>
					<span class="k"><b>类型:</b></span>
					<span class="v"><s:property value="type"/></span>
				</li>
				<li>
					<span class="k"><b>游戏:</b></span>
					<span class="v"><s:property value="gameName"/></span>
				</li>
				<li>
					<span class="k"><b>时间:</b></span>
					<span class="v"><s:date name="createTime" format="yyyy-MM-dd hh:mm:ss"/></span>
				</li>
				<li class="zhengwen">
					<span class="k"><b>正文:</b></span>
					<p id="descrip">&nbsp;&nbsp;<s:property value="descrip"/>	</p>
				</li>
			</ul>
			<div id="reps">
				<s:iterator value="reps">
					<div><s:property value="desc"/></div>
					<div><s:property value="guy"/></div>
				</s:iterator>
			</div>	
			</s:push>
			</div>
		</div>
		<div id="fooot">2010 All Rights Reserved 版权所有 上海火游网络科技有限公司</div>
		</div>
	
</body>
</html>