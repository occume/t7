<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title>上海火游网络有限公司-客服后台管理中心</title>
		<link href="/css/htstyle.css" type="text/css" rel="stylesheet" />
		<link href="/css/editstyle.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		<!--title start -->
		<%@include file="../common/exit.jsp"%>
		<!--title end -->
		<div class="location">
			当前位置：<a href="/ctService/htIndex.jsp">首页 </a>>错误页面
		</div>


		<!--查询 开始 -->
		<s:set name="roleLevel" value="#session.role.level"></s:set>

		<div class="wrappercheck">

			<div class="indexbox">

				<s:property value="exception"/>

				<s:property value="exceptionStack"/>
				
			</div>

		</div>


		<!--查询 end -->

		<div id="holder"></div>
		<!--foot  开始-->
		<div class="foot">
			2010 All Rights Reserved 版权所有 上海火游网络科技有限公司
		</div>
	</body>
</html>
