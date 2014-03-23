<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hy.wo.util.constants.*" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="common/checkLogin.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>上海火游网络有限公司-客服后台管理中心</title>
<link href="/css/htstyle1.css" type="text/css" rel="stylesheet"/>
<link href="/css/editstyle1.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="/css/dialog.css"/>
<link rel="stylesheet" type="text/css" href="/css/workOrder_jd.css"/>
<script type="text/javascript" src="/include/jquery.min.js"></script>
<script type="text/javascript" src="/include/view.js"></script>
<script type="text/javascript" src="/include/raphael.js"></script>
<script type="text/javascript" src="/include/dialog.js"></script>
<script type="text/javascript" src="/include/jquery.livequery.js"></script>
<script type="text/javascript" src="/js/drag.js"></script>
<script type="text/javascript">
	$(function(){
		recentmygdcx.init();
	});
</script> 
</head>
<body >
<!--title start -->
<%@include file="common/exit.jsp"%>
<!--title end -->
<div class=location>当前位置：<a href="/ctService/htIndex.jsp">首页</a> 〉查看/操作工单 </div>
<s:set  name="roleId" value="#session.role.Id"></s:set>
<s:set  name="roleLevel" value="#session.role.level"></s:set>
<s:set  name="staffId" value="#session.staff.Id"></s:set>
<s:set  name="groupId" value="#session.group.Id"></s:set>
<!--查询 开始 -->
<s:push value="workOrder">
<input type="hidden" name="woId" id="workOrderId" value="<s:property value="id"/>"/>
<input type="hidden" name="accountname" id="accName" value="<s:property value="userInfo.accountname"/>"/>
<input type="hidden" name="states" id="states" value="<s:property value="states"/>"/>

<div class=wrappercheck>
<div class=editboxleft>
<form id="ediggdform">
<br />
<h1 id="woInfo">工单信息
		<span style="margin-left:10px;color:#7EC0EE;font-size:15px;" class="currState">
			工单ID :<s:property value="id"/>
		</span>
		<span style="margin-left:10px;color:red;font-size:12px;" class="currState">
		状态：<s:property value="states"/>
				<s:if test="states==@com.hy.wo.util.Constants$States@DEALING">
					<s:if test="assign.statu=='ASSIGNING'">
						<span style="color:#444444;font-size:12px;">[<s:property value="assign.from"/>]</span>做了个艰难的决定，派给<span style="color:#444444;font-size:12px;">[<s:property value="assign.to"/>]</span>，你懂的
					</s:if>
					<s:elseif test="assign.statu=='ASSIGNED'">
						<span style="color:#000000;font-size:12px;">[派单回复]</span>
					</s:elseif>
					<s:else></s:else>
				</s:if>
 			</span>
 			
 </h1>
 
<!-- 工单详情 -->
	<%@ include  file="common/IssueFromUser.jsp" %>
</form>
</div>

<!--工单处理记录  开始-->
<div class=editboxright>
<div class="history">
<h1>该账号近期工单处理记录</h1>

<div class=hiscont>
<div class=histop></div>
<div class=histitle1>玩家账号</div>
<div class=histitle2>问题类型</div>
<div class=histitle3>处理时间</div>
<div class=histitle4>回复描述</div>
<div class=clear></div>
</div>
<div id="recentmygdcxlist">
<div class=hiscont>
<div class=hiscont1></div>
<div class=hiscont2></div>
<div class=hiscont3></div>
<div class=hiscont4></div>
<div class=clear></div>
</div>
</div>
<!--翻页 开始-->

<div class="turnpage" id="mygdgclistpage">
  <div class="buttonselect">第 
   <select name="" class="select40">
   </select>
  页 总共<span id="tp"></span>页 每页<span id="ts"></span>条
  </div>
  <div class="buttonend"><a href="javascript:void(0)"></a></div>
  <div class="buttonnext"><a href="javascript:void(0)"></a></div>
  <div class="buttonfront"><a href="javascript:void(0)"></a></div>
  <div class="buttonfirst"><a href="javascript:void(0)"></a></div>
<div class=clear ></div>

</div>

<!--翻页 结束-->

</div>
</div>
<!--工单处理记录  结束-->


<!--工单处理记录  开始-->

<!--工单处理记录  结束-->


<div class=clear></div>

</div>
</s:push>

<!--foot  开始-->
<div class=foot>2010 All Rights Reserved 版权所有 上海火游网络科技有限公司</div>

</body>
</html>

