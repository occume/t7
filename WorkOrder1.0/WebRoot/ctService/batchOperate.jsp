<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.hy.wo.po.WorkOrder" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="common/checkLogin.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--<meta http-equiv="x-ua-compatible" content="ie=7" />-->
<title>上海火游网络有限公司-客服后台管理中心</title>
<link href="/css/style.css" type=text/css rel=stylesheet />
<link rel="stylesheet" type="text/css" href="/css/dialog.css"/>
<script type="text/javascript" src="/include/jquery.min.js"></script>
<script language="javascript" type="text/javascript" src="/include/dialog.js"></script>
<script language="javascript" type="text/javascript" src="/js/batchOprate.js"></script>
<script language="javascript" type="text/javascript" src="/include/jquery.livequery.js"></script>

<script type="text/javascript">
	
</script>
</head>

<body>
<!--title start -->
<%@include file="common/exit.jsp"%>
<!--title end -->
<div class=location>当前位置：<a href="/ctService/htIndex.jsp">首页</a> 〉工单批量处理 </div>
<div class=alllistcont>
<div class=title></div>


</div>
<!--工单广场  结束-->

<!--我的工单广场  开始-->
<div class=mylistcont>
<div class=mylistbox>

<br /><br />

<h1>批量处理清单</h1>
<div class=mylisttitle>
<div class=boxtitle1>工单号</div>
<div class=boxtitle5>玩家账号</div>
<div class=boxtitle6>问题类型</div>
<div class=boxtitle7>创建时间</div>
<div class=boxtitle7>问题描述</div>
<div class=boxtitle10>批量回复类型</div>
<div class=clear ></div>
</div>
<div id="mygdcxlist"></div>

<!--翻页 开始-->
<div class=titleline></div>

<div class="turnpage" id="mygdgclistpage">
  <div class="buttonselect">第 
   <select name="" class="select40">
   </select>
  页 总共<span id="tp"></span>页 每页<span id="ts"></span>条 共<span id="tc"></span>条
  </div>
  <div class="buttonend"><a href="javascript:void(0)"></a></div>
  <div class="buttonnext"><a href="javascript:void(0)"></a></div>
  <div class="buttonfront"><a href="javascript:void(0)"></a></div>
  <div class="buttonfirst"><a href="javascript:void(0)"></a></div>
<div class=clear ></div>
</div>

<!--翻页 结束-->



<div id="btn_batch">
	<ul>
		<li><a class="batchReply" href="javascript:void(0)">批量回复</a></li>
		<li><a class="batchReturn" href="javascript:void(0)">批量退单</a></li>
		<li><a class="batchDelete" href="javascript:void(0)">批量删除</a></li>
	</ul>
</div>
</div>
</div>

<!--我的工单广场  结束-->


<!--foot  开始-->
<div class=foot>2010 All Rights Reserved 版权所有 上海火游网络科技有限公司</div>
</body>
</html>

