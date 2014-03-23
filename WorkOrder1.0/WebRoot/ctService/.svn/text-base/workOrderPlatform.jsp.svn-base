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
<link rel="stylesheet" type="text/css" href="/css/dwr.css"/>
<link rel="stylesheet" type="text/css" href="/css/Jdialog.css"/>
<script type="text/javascript" src="/include/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery.easing.min.js"></script>
<script type="text/javascript" src="/include/view.js?v=4"></script>
<script type="text/javascript" src="/js/workOrderProcess.js"></script>
<script language="javascript" type="text/javascript" src="/include/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="/include/dialog.js"></script>
<script language="javascript" type="text/javascript" src="/include/jquery.livequery.js"></script>
<script type="text/javascript" src="/js/Ddialog.js"></script>
<script type="text/javascript" src="/js/util.js"></script>
<script type="text/javascript" src="/js/sbox.js"></script>

<script type="text/javascript">
	$(function(){
		//$("#btn_xls").blur();
		gdcx.init();
		mygdcx.init();
		ryph.init();
		ladan.init();
	});
</script>
<style>
		body{margin:0;padding:0;}
		#wrap{position:absolute;padding:3px;background:#000;}
		#sb{position:relative;}
		#inbox input{width:300px;font-size:20px;padding:5px 0 3px 5px;}
	</style>
</head>

<body onload="dwr.engine.setActiveReverseAjax(true);">
		<script type='text/javascript' src='/dwr/interface/JDChat.js'></script>
		<script type='text/javascript' src='/dwr/engine.js'></script>
		<script type='text/javascript' src='/dwr/util.js'></script>
		<script type='text/javascript' src='/js/showMessage.js'></script>
		<script type='text/javascript' src='/js/showNotice.js'></script>
		<script type="text/javascript">
    function sendMessage() {
      
      JDChat.startDwr();
    }
    sendMessage();
</script>
<!--title start -->
<%@include file="common/exit.jsp"%>
<!--title end -->
<div class=location>当前位置：<a href="/ctService/htIndex.jsp">首页</a> 〉工单广场工单 </div>

<div class=alllistcont>
<div class=title></div>
<div class=topcont>
<div class=topleftbox>
<div class=top>
<h1>荣誉排行榜</h1>
<div id="todayPh">
<div class="top1">当天排名：</div>
<div class="top2 charts">杨乐韵</div>
<div class="top3 charts">杨乐韵</div>
<div class="top4 charts">杨乐韵</div>
<div class="top5 charts">杨乐韵</div>
<div class="top6 charts">杨乐韵</div>
<div class="top7 charts">杨乐韵</div>
<div class="top8 charts">杨乐韵</div>
</div>
<div class=clear ></div>
</div>

<div class=top id="monthPh">
<div class=top1>当月排名：</div>
<div class="top2 charts">杨乐韵</div>
<div class="top3 charts">杨乐韵</div>
<div class="top4 charts">杨乐韵</div>
<div class="top5 charts">杨乐韵</div>
<div class="top6 charts">杨乐韵</div>
<div class="top7 charts">杨乐韵</div>
<div class="top8 charts">杨乐韵</div>
<div class=clear ></div>
</div>

</div><br />
<s:if test="#session.role.id==4||#session.role.id==5">
	<div class=buttoncheck><a href="javascript:void(0)" id="btn_ladan">拉&nbsp;&nbsp;单</a></div>
</s:if>
<div class=clear ></div>
</div>

</div>
<!--工单广场  结束-->

<!--我的工单广场  开始-->
<div class=mylistcont>
<div class=mylistbox>

<div class=toolsbutton>


	<div class=tools1 style="">
	
		<a href="/index/grab_toCreateWorkOrder" target="blank">创建工单</a>
	</div>

<!-- if 普通客服 -->
<s:if test="#session.role.id==4 || #session.role.id==9">
	<div class="tools2 sib"><a href="javascript:void(0)" ajaxurl="/json/query_getWorkOrderVipComp" id="btn_ptsl" name="btnclick">vip祝福</a></div>
	<div class="tools3 sib"><a href="javascript:void(0)" ajaxurl="/json/query_getWorkOrderWaitForDeal" id="btn_ptsl" name="btnclick">等待处理</a></div>
	<div class="tools3 sib"><a href="javascript:void(0)" ajaxUrl="/json/query_getWorkOrderAssigned" id="btn_ypg" name="btnclick">派单跟踪</a></div>
	<div class="tools4 sib"><a href="javascript:void(0)" ajaxUrl=" /json/query_getWorkOrderLackInfo" id="btn_ddfw" name="btnclick">待补充资料</a></div>
	<div class="tools4 sib"><a href="javascript:void(0)" ajaxUrl=" /json/query_getWorkOrderWaitForResponse" id="btn_ddfw" name="btnclick">等待回访</a></div>
	<div class="tools3 sib"><a href="javascript:void(0)" ajaxurl="/json/query_getWorkOrderIshf" id="btn_ishf" name="btnclick">需电话回访</a></div>
</s:if>
<!-- if技术GM -->
<s:if test="#session.role.id==5">
	<div class="tools2 sib"><a href="javascript:void(0)" ajaxurl="/json/query_getWorkOrderWaitForDeal" id="btn_ptsl" name="btnclick">等待处理</a></div>
	<div class="tools3 sib"><a href="javascript:void(0)" ajaxUrl="/json/query_getWorkOrderAssigned" id="btn_ypg" name="btnclick">派单跟踪</a></div>
	<div class="tools3 sib"><a href="javascript:void(0)" ajaxUrl="/json/query_getWorkOrderWaitForMe" id="btn_ypg" name="btnclick">由我处理</a></div>
</s:if>
<!-- if 产品 运维 其他 -->
<s:if test="#session.role.id==6||#session.role.id==7||#session.role.id==8">
	<div class="tools2 sib"><a href="javascript:void(0)" ajaxurl="/json/query_getWorkOrderWaitForDeal" id="btn_ptsl" name="btnclick">等待处理</a></div>
	<div class="tools3 sib"><a href="javascript:void(0)" ajaxUrl="/json/query_getWorkOrderAssignedMe" id="btn_ypg" name="btnclick">处理记录</a></div>
</s:if>
<!-- if客服组长 客服主管 -->
<s:if test="#session.role.id==1||#session.role.id==2||#session.role.id==3">
	<div class="tools3 sib"><a href="javascript:void(0)" ajaxUrl="/json/query_getWorkOrderWaitForMe" id="btn_ypg" name="btnclick">由我处理</a></div>
	<div class="tools3 sib"><a href="javascript:void(0)" ajaxurl="/json/query_getWorkOrderWaitForDeal" id="btn_ptsl" name="btnclick">等待处理</a></div>
	<div class="tools3 sib"><a href="javascript:void(0)" ajaxUrl="/json/query_getWorkOrderAssigned" id="btn_ypg" name="btnclick">派单跟踪</a></div>
	<div class="tools4 sib"><a href="javascript:void(0)" ajaxUrl=" /json/query_getWorkOrderWaitForResponse" id="btn_ddfw" name="btnclick">等待回访</a></div>
	<div class="tools4 sib"><a href="javascript:void(0)" ajaxUrl=" /json/query_getWorkOrderResponsed" id="btn_ddfw" name="btnclick">回访跟踪</a></div>
	<div class="tools3 sib"><a href="javascript:void(0)" ajaxurl="/json/query_getWorkOrderIshf" id="btn_ishf" name="btnclick">需电话回访</a></div>
	<div class="tools6"><a href="javascript:void(0)" id="btn_plhf">批量操作</a></div>
</s:if>

</div>


<br /><br />

<h1><s:property value="#session.group.name"/>的工单仓库
<s:if test="#session.role.id==3||#session.role.id==4||#session.role.id==5">
<span style="margin-left:20px;">可拉单数量:<span style="color:red;" class="showUndeal"></span></span>
<span style="margin-left:20px;">处理中:<span style="color:red;" class="showDealing"></span>
												(<span style="color:#999999">未派单：</span><span style="color:green;" class="showNoAssign"></span>|
												<span style="color:#999999">派单中：</span><span style="color:green;" class="showAssigning"></span>|
												<span style="color:#999999">派单回复：</span><span style="color:green;" class="showAssigned"></span>)</span>
<span style="margin-left:20px;">待补充资料:<span style="color:red;" class="showLackInfo"></span></span>
<span style="margin-left:20px;">等待回访:<span style="color:red;" class="showDealed"></span></span>

<span style="margin-left:50px;">以下为[<span style="color:red;" class="currentTab"></span>]工单列表</span>
</s:if>
<s:else>
	<span style="margin-left:30px;">可拉单数量:<span style="color:red;" class="showUndeal"></span></span>
	<span style="margin-left:300px;">以下为[<span style="color:red;" class="currentTab"></span>]工单列表</span>
</s:else>
<s:if test="(#session.role.id==1)||(#session.role.id==2)||(#session.role.id==3)"><span class="addToBatch">加入批量操作</span></s:if>
</h1>
<div class=mylisttitle>
<div class=boxtitle1>工单号</div>
<div class=boxtitle2>处理状态</div>
<div class=boxtitle3>工单来源</div>
<div class=boxtitle4>紧急程度</div>
<div class=boxtitle5>玩家账号</div>
<div class=boxtitle6>问题类型</div>
<div class=boxtitle7>创建时间</div>
<div class=boxtitle8>服务器</div>
<div class=boxtitle9>拉单时间</div>
<div class=boxtitle10>批量回复类型</div>
<div class=boxtitle2>标记(<span class="optAll"><a href="javascript:void(0)">全</a></span>|
												<span class="optReverse"><a href="javascript:void(0)">反</a></span>)</div>
<div class=clear ></div>
</div>
<div id="mygdcxlist">
</div>


<!--翻页 开始-->
<div class=titleline></div>

<div class="turnpage" id="mygdgclistpage">
  <div class="buttonselect">第 
   <select name="" class="select40">
   </select>
  页 总共<span id="tp" style="font-family:Georgia;color:red;"></span>页 每页<span id="ts" style="font-family:Georgia;color:red;"></span>条 共<span id="tc" style="font-family:Georgia;color:red;"></span>条
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

<!--我的工单广场  结束-->

<!--工单广场  开始-->


<!--查询 开始 -->

<div class=wrappercheck>
<div class=checkbox>
<div class="checkleft">
<form id="gdcxform" action="/json/XLSDownload" method="get">
<div class="check">
<h1>全部工单查询</h1>
<div class=check1>创建人：</div>
<div class=check2><input class=input180 type="text" name="createWorker" /> </div>
<div class=check3>创建时间：</div>
<div class=check4><input class="validate[required] input100" type="text" readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})"
						name="startTime" id="prefinishTime" />
					<input class="validate[required] input100" type="text" readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'prefinishTime\')}'})"
						name="endTime" id="prefinishTime" />	
						
						
						 </div>
<div class=check5>游戏账号：</div>
<div class=check6><input class=input180 type="text" name="userInfo.accountname" /> </div>
<div class=check7>问题类型：</div>
<div class=check8><select class="select180" name="userInfo.lvlOne.id" onclick="BindSelectAjax(this,'/index/grab_issueJson',9)"><option></option></select> </div>
<div class=clear ></div>
</div>

<div class=check>
<div class=check1>游戏名称：</div>
<div class=check2><select class="select180" name="userInfo.game.id" onclick="BindSelectAjax(this,'/index/grab_gameJson')"><option></option></select> </div>
<div class=check3>服务器：</div>
<div class=check4><select class="select180" name="userInfo.server.id" onclick="BindSelectAjax(this,'/index/grab_serverJson')"><option></option></select> </div>
<div class=check5>工单状态：</div>
<div class=check6><select class="select180" name="states" onclick="BindSelectAjax(this,'/index/grab_statesJson')"><option></option></select> </div>
<div class=check7>工单来源：</div>
<div class=check8><select class="select180" name="source" onclick="BindSelectAjax(this,'/index/grab_sourceJson')"><option></option></select> </div>
<div class=clear ></div>
</div>
<div class=check>
<div class=check1> 工单号：</div>
<div class=check2><input class=input180 type="text" id="woID" name="id" />  </div>
<div class=check3>小组：</div>
<div class=check4><select class="select180" name="group.id" onclick="BindSelectAjax(this,'/index/grab_groupJson')"><option></option></select> </div>
<div class=check5></div>
<div class=check6> </div>
<div class=check7></div>
<div class=check8><input id="btn_xls"  type="button" value="导出EXCEL"></input> </div>
<div class=clear ></div>
</div>

</form>
</div>
<div class=buttoncheck>
	<a href="javascript:void(0)" id="btn_query">查&nbsp;&nbsp;询</a>
	<a href="javascript:void(0)" id="btn_clear">清&nbsp;&nbsp;空</a>
	
</div>
<div class=clear ></div>
</div>

<div class=checkbox >
<div class=alllistboxtitle><h1>工单广场</h1></div>
<div class=titleline></div>
<div class=alllistbox>
<div class=boxtitle1>工单号</div>
<div class=boxtitle1>拉单人</div>
<div class=boxtitle2>工单来源</div>
<div class=boxtitle3>紧急程度</div>
<div class=boxtitle4>玩家账号</div>
<div class=boxtitle5>游戏名称</div>
<div class=boxtitle6>问题类型</div>
<div class=boxtitle7>处理人</div>
<div class=boxtitle8>创建时间</div>
<div class=boxtitle9>服务器</div>
<div class=boxtitle10>处理状态</div>
<div class=boxtitle10>满意度</div>
<div class=clear ></div>
</div>
<div id="gdgclist">
</div>
<div class=titleline></div>

<!--翻页 开始-->

<div class="turnpage" id="gdgclistpage">
  <div class="buttonselect">第 
   <select name="" class="select40">
   </select>
  页 总共<span id="tp" style="font-family:Georgia;color:red;">0</span>页 每页<span id="ts" style="font-family:Georgia;color:red;">10</span>条 共<span id="tc" style="font-family:Georgia;color:red;"></span>条
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




<!-- sbox -->
	<div id="wrap">
		<div id="sb">
			<div id="one">
				<div id="logo"><img src="/images/ly.gif"/></div>
				<div id="inbox"><input type="text" name="keyword" id="keyword"/></div>
			</div>
		</div>
		<div id="help">HELP</div>
	</div>
	<div id="im"></div>


<!--foot  开始-->
<div class=foot>2010 All Rights Reserved 版权所有 上海火游网络科技有限公司</div>

</body>
</html>
 