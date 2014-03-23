<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>账号管理首页</title>    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="/css/accManage.css"></link>	
	<link rel="stylesheet" href="/css/dialog.css"></link>	
	<script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="/js/accManage.js"></script>
	<script type="text/javascript" src="/include/dialog.js"></script>
  </head>
  
  <body>
 	${role.name}
	<div>
		<div id="server">
			<div id="title"><span class="zhankai">服务器管理</span>
							<span class="add">|添加</span>
							<span class="flush">|刷新</span></div>
			<div id="content"></div>
			<div id="oper"></div>
		</div>
		<div id="games">
			<div id="title"><span class="zhankai">游戏管理</span>
							<span class="add">|添加</span>
							<span class="flush">|刷新</span></div>
			<div id="content"></div>
			<div id="oper"></div>
		</div>
		<div id="reportKind">
			<div id="title"><span class="zhankai">违规类型管理</span>
							<span class="add">|添加</span>
							<span class="flush">|刷新</span></div>
			<div id="content"></div>
			<div id="oper"></div>
		</div>
		<div id="recharge">
			<div id="title"><span class="zhankai">充值类型管理</span>
							<span class="add">|添加</span>
							<span class="flush">|刷新</span></div>
			<div id="content"></div>
			<div id="oper"></div>
		</div>
		<div id="issue">
			<div id="title"><span class="zhankai">问题小类型管理</span>
							<span class="add">|添加</span>
							<span class="flush">|刷新</span></div>
			<div id="content"></div>
			<div id="oper"></div>
		</div>
		<div id="advisory">
			<div id="title"><span class="zhankai">咨询类型管理</span>
							<span class="add">|添加</span>
							<span class="flush">|刷新</span></div>
			<div id="content"></div>
			<div id="oper"></div>
		</div>
		<div id="batch">
			<div id="title"><span class="zhankai">批量操作类型管理</span>
							<span class="add">|添加</span>
							<span class="flush">|刷新</span></div>
			<div id="content"></div>
			<div id="oper"></div>
		</div>
	</div>
  </body>
</html>