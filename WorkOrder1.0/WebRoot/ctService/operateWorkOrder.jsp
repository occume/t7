<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hy.wo.util.constants.*" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="common/checkLogin.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>上海火游网络有限公司-客服后台管理中心</title>
<link href="/css/htstyle1.css" type="text/css" rel="stylesheet"/>
<link href="/css/editstyle1.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="/css/dialog.css"/>
<link rel="stylesheet" type="text/css" href="/css/workOrder_jd.css"/>
<link rel="stylesheet" type="text/css" href="/css/dwr.css"/>
<link rel="stylesheet" type="text/css" href="/css/Jdialog.css"/>
<script type="text/javascript" src="/include/jquery.min.js"></script>
<script type="text/javascript" src="/include/view.js"></script>
<script type="text/javascript" src="/include/edit.js"></script>
<script type="text/javascript" src="/include/raphael.js"></script>
<script type="text/javascript" src="/js/ctFaq.js"></script>
<script type="text/javascript" src="/include/dialog.js"></script>
<script type="text/javascript" src="/include/jquery.livequery.js"></script>
<script type="text/javascript" src="/js/drag.js"></script>
<script type="text/javascript" src="/js/Ddialog.js"></script>
<script type="text/javascript">
	$(function(){
		recentmygdcx.init();
	});
</script> 
</head>
<body onload="dwr.engine.setActiveReverseAjax(true);">
		<script type='text/javascript' src='/dwr/interface/JDChat.js'></script>
		<script type='text/javascript' src='/dwr/engine.js'></script>
		<script type='text/javascript' src='/dwr/util.js'></script>
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

<br />
<h1 id="woInfo">工单信息
		<span style="margin-left:10px;color:#7EC0EE;font-size:15px;" class="currState">
			工单ID :<s:property value="id"/>
		</span>
		<span style="margin-left:10px;color:red;font-size:12px;" class="currState">
		状态：<s:property value="states"/>
				<s:if test="states==@com.hy.wo.util.Constants$States@DEALING">
					<s:if test="assign.statu=='ASSIGNING'">
						<span style="color:#444444;font-size:12px;">[<s:property value="assign.from"/>]</span>派给<span style="color:#444444;font-size:12px;">[<s:property value="assign.to"/>]</span>
					</s:if>
					<s:elseif test="assign.statu=='ASSIGNED'">
						<span style="color:#000000;font-size:12px;">[派单回复]</span>
						<span id="innerMark" style="cursor:pointer;color:#bc8f8f;font-size:12px;">[内部确认]</span>
					</s:elseif>
					<s:else></s:else>
				</s:if>
 			</span>
 			
 </h1>
 <s:if test="#roleId==3">
 		<s:if test="getState==false">
 				<span id="innerLD">拉单</span>
 		</s:if>
 		<s:else>
 				<s:if test="#groupId != group.id">
					<s:if test="states != @com.hy.wo.util.Constants$States@RESPONSED">
 						<span id="innerLD">拉单</span>
 					</s:if>
 				</s:if>
 		</s:else>
 </s:if> 
<!-- 工单详情 -->
<s:if test="userInfo.lvl1.id == 75">
	<%@ include  file="common/IssueFromCT.jsp" %>
</s:if>
<s:else>
	<%@ include  file="common/IssueFromUser.jsp" %>
</s:else>

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
<div class="editboxright">
<div class="faq">
<h1>客服FAQ 
	<s:if test="#roleLevel>0"><a href="/faq/getAllFaq.action">去加一条</a>
	</s:if>
	 </h1>
			
<div class="hiscont">
			<div id="faq-search-box">
			
					<label id="emp">关键字</label> <input id="name" type="text" name="descrip" />
					<input  id="send" type="button"  value="搜一下"/>
				
			</div>
<div id="quickKey">
		<span id="q6" style="color:red;">通知</span>
		<span id="q1">账号问题</span>
		<span id="q2">游戏异常</span>
		<span id="q3">物品丢失</span>
		<span id="q4">意见建议</span>
		<span id="q11">游戏BUG</span>
		<hr/>
		<span id="q7">充值问题</span>
		<span id="q8">角色数据异常</span>
		<span id="q9">服务器问题</span>
		<span id="q10">游戏知识</span>
		<span id="q5">其它问题</span>
</div>
<div class="histop"></div>
<div class="histitle1">游戏名称</div>
<div class="histitle1 ttype">问题类型</div>
<div class="histitle4">问题说明</div>
<div class="histitle3">发布时间</div>

<div class=clear></div>
</div>
<div id="ctfaqlist">
<div class=hiscont>
<div class=hiscont1></div>
<div class=hiscont1></div>
<div class=hiscont4></div>
<div class=hiscont3></div>

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


<div class=clear></div>

<!-- TEST 
<h5 style="color:red;"><s:property value="tobeWorker.accName"/>
in session<s:property value="staff.role.id"/><br/>
			not in session <s:property value="#session.role.id"/>
			departmentId<s:property value="#session.staff.department.id"/>
			 </h5>
			 -->
<!--操作按钮  开始-->


<div class="edittools">
<!-- 处理 -->
<s:if test="#roleLevel>0"><!-- if 主管 组长 -->
	<button class="tools1_mouseout"
						title="对工单给出处理意见和回复"
				 		onclick="dialog.openUrl('/ctService/processWorkOrder/deal.jsp?woId=<s:property value="id"/>','auto','auto');" 
				 		onmouseover="this.className='tools1_mouseover'" 
				 		onmouseout="this.className='tools1_mouseout'" id="btn_deal">处理意见</button>
</s:if>
<s:else>
	<s:if test="#roleId==4 || #roleId==9"><!-- if 普通客服 -->
		<button class="tools1no_mouseout"
					 		onmouseover="this.className='tools1no_mouseover'" 
					 		onmouseout="this.className='tools1no_mouseout'"  id="btn_deal">处理意见</button>
	</s:if>
	<s:elseif test="#roleId==5"><!-- if 技术GM -->
		<s:if test="states==@com.hy.wo.util.Constants$States@DEALING">
				<s:if test="assign.statu=='ASSIGNED'">
						<button class="tools1_mouseout"
						title="对工单给出处理意见和回复"
				 		onclick="dialog.openUrl('/ctService/processWorkOrder/deal.jsp?woId=<s:property value="id"/>','auto','auto');" 
				 		onmouseover="this.className='tools1_mouseover'" 
				 		onmouseout="this.className='tools1_mouseout'" id="btn_deal">处理意见</button>
				</s:if>
				<s:elseif test="assign.statu=='ASSIGNING'">
						<s:if test="#groupId==tobeGroup.id">
							<button class="tools1_mouseout"
								title="对工单给出处理意见和回复"
						 		onclick="dialog.openUrl('/ctService/processWorkOrder/deal.jsp?woId=<s:property value="id"/>','auto','auto');" 
						 		onmouseover="this.className='tools1_mouseover'" 
						 		onmouseout="this.className='tools1_mouseout'" id="btn_deal">处理意见</button>
						</s:if>
						<s:else>
								<button class="tools1no_mouseout"
						 		onmouseover="this.className='tools1no_mouseover'" 
						 		onmouseout="this.className='tools1no_mouseout'"  id="btn_deal">处理意见</button>
						</s:else>
				</s:elseif>
				<s:else>
					<button class="tools1_mouseout"
						title="对工单给出处理意见和回复"
				 		onclick="dialog.openUrl('/ctService/processWorkOrder/deal.jsp?woId=<s:property value="id"/>','auto','auto');" 
				 		onmouseover="this.className='tools1_mouseover'" 
				 		onmouseout="this.className='tools1_mouseout'" id="btn_deal">处理意见</button>
				</s:else>
		</s:if>
		<s:else>
				<button class="tools1no_mouseout"
					 		onmouseover="this.className='tools1no_mouseover'" 
					 		onmouseout="this.className='tools1no_mouseout'"  id="btn_deal">处理意见</button>
		</s:else>
	</s:elseif>
	<s:else><!-- if 其他 -->

		<s:if test="states==@com.hy.wo.util.Constants$States@DEALING && #groupId==tobeGroup.id">
		
			<button class="tools1_mouseout"
						title="对工单给出处理意见和回复"
				 		onclick="dialog.openUrl('/ctService/processWorkOrder/deal.jsp?woId=<s:property value="id"/>','auto','auto');" 
				 		onmouseover="this.className='tools1_mouseover'" 
				 		onmouseout="this.className='tools1_mouseout'" id="btn_deal">处理意见</button>
		</s:if>
		<s:else>
				<button class="tools1no_mouseout"
					 		onmouseover="this.className='tools1no_mouseover'" 
					 		onmouseout="this.className='tools1no_mouseout'"  id="btn_deal">处理意见</button>
		</s:else>
	</s:else>
</s:else>

<!-- 补充资料 -->
<s:if test="#roleLevel>0">
	<button class=tools2_mouseout id="btn_edit" 
					title="由客服对工单进行资料补充，用户不可见"
					onmouseover="this.className='tools2_mouseover'" 
					onmouseout="this.className='tools2_mouseout'">补充资料</button>
</s:if>
<s:else>
	<s:if test="#roleId==4 || #roleId==9">
		<s:if test="states!=@com.hy.wo.util.Constants$States@RESPONSED">
			<button class=tools2_mouseout id="btn_edit" 
						title="由客服对工单进行资料补充，用户不可见"
						onmouseover="this.className='tools2_mouseover'" 
						onmouseout="this.className='tools2_mouseout'">补充资料</button>
		</s:if>
		<s:else>
			<button class=tools2no_mouseout id="btnno_edit" 
					onmouseover="this.className='tools2no_mouseover'" 
					onmouseout="this.className='tools2no_mouseout'">补充资料</button>
		</s:else>
	</s:if>
	<s:else>
		<button class=tools2no_mouseout id="btnno_edit" 
					onmouseover="this.className='tools2no_mouseover'" 
					onmouseout="this.className='tools2no_mouseout'">补充资料</button>
	</s:else>
</s:else>
<!-- 平台回复 -->
<s:if test="#roleLevel>0"><!-- if 主管 组长 -->
	<button class="tools3_mouseout" 
						title="由客服通过平台对用户做出回复，用户可见"
						onclick="dialog.openUrl('/ctService/processWorkOrder/reply.jsp?woId=<s:property value="id"/>','400','380');" 
						onmouseover="this.className='tools3_mouseover'"  
						onmouseout="this.className='tools3_mouseout'">平台回复</button>
</s:if>
<s:else>
<!-- 
<s:if test="source==@com.hy.wo.util.Constants$Source@PINGTAI ||source!=@com.hy.wo.util.Constants$Source@PINGTAI ">
 -->
	<s:if test="#roleId==4 || #roleId==9"><!-- if 普通客服 -->
		<s:if test="states==@com.hy.wo.util.Constants$States@DEALING">
			<s:if test="assign.statu=='ASSIGNING'"><!-- if 处理中 派单中 -->
				<button class="tools3no_mouseout" 
				onmouseover="this.className='tools3no_mouseover'"  
				onmouseout="this.className='tools3no_mouseout'">平台回复</button>
			</s:if>
			<s:else>
					<button class="tools3_mouseout" 
						title="由客服通过平台对用户做出回复，用户可见"
						onclick="dialog.openUrl('/ctService/processWorkOrder/reply.jsp?woId=<s:property value="id"/>','400','380');" 
						onmouseover="this.className='tools3_mouseover'"  
						onmouseout="this.className='tools3_mouseout'">平台回复</button>
			</s:else>
		</s:if>
		<s:elseif test="states==@com.hy.wo.util.Constants$States@DEALED"><!-- if 已处理 -->
			<button class="tools3_mouseout" 
						title="由客服通过平台对用户做出回复，用户可见"
						onclick="dialog.openUrl('/ctService/processWorkOrder/reply.jsp?woId=<s:property value="id"/>','400','380');" 
						onmouseover="this.className='tools3_mouseover'"  
						onmouseout="this.className='tools3_mouseout'">平台回复</button>
		</s:elseif>
		<s:else>
			<button class="tools3no_mouseout" 
				onmouseover="this.className='tools3no_mouseover'"  
				onmouseout="this.className='tools3no_mouseout'">平台回复</button>
		</s:else>
	</s:if>
	<s:else>
		<button class="tools3no_mouseout" 
				onmouseover="this.className='tools3no_mouseover'"  
				onmouseout="this.className='tools3no_mouseout'">平台回复</button>
	</s:else>
	<!-- 
	</s:if>
	<s:else>
		<button class="tools7no_mouseout" 
					onmouseover="this.className='tools7no_mouseover'" 
					onmouseout="this.className='tools7no_mouseout'">平台回复</button>
	</s:else>
	 -->
</s:else>
<!-- 派单协助 -->
<s:if test="#roleLevel>0">
	<button class=tools5_mouseout 
			title="把工单派转给其他部门协助处理，被派转部门可见"
			onclick="dialog.openUrl('/json/query_toAssignPage?workOrderId=<s:property value="id"/>','400','380');" 
			onmouseover="this.className='tools5_mouseover'"  
			onmouseout="this.className='tools5_mouseout'" id="btn_chanpin">派转工单</button>
</s:if>
<s:else>
	<s:if test="#roleId==4 || #roleId==9">
		<s:if test="states==@com.hy.wo.util.Constants$States@DEALING&&(assign==null || assign.statu=='ASSIGNED')">
			<button class=tools5_mouseout 
			title="把工单派转给其他部门协助处理，被派转部门可见"
			onclick="dialog.openUrl('/json/query_toAssignPage?workOrderId=<s:property value="id"/>','400','380');" 
			onmouseover="this.className='tools5_mouseover'"  
			onmouseout="this.className='tools5_mouseout'" id="btn_chanpin">派转工单</button>
		</s:if>
		<s:else>
			<button class=tools5no_mouseout 
							onmouseover="this.className='tools5no_mouseover'"  
							onmouseout="this.className='tools5no_mouseout'" id="btn_chanpin">派转工单</button>
		</s:else>
	</s:if>
	<s:elseif test="#roleId==5"><!-- if 技术GM -->
		<s:if test="states==@com.hy.wo.util.Constants$States@DEALING">    <!-- if 处理中 -->
			<s:if test="assign.statu!='ASSIGNING'">			 <!-- if 派单回复 -->
					<button class=tools5_mouseout 
						title="把工单派转给其他部门协助处理，被派转部门可见"
						onclick="dialog.openUrl('/json/query_toAssignPage?workOrderId=<s:property value="id"/>','400','380');" 
						onmouseover="this.className='tools5_mouseover'"  
						onmouseout="this.className='tools5_mouseout'" id="btn_chanpin">派转工单</button>
			</s:if>
			<s:else>
				<s:if test="tobeGroup==null||tobeGroup.id==#groupId">  <!-- if 处理中 -->
					<button class=tools5_mouseout 
						title="把工单派转给其他部门协助处理，被派转部门可见"
						onclick="dialog.openUrl('/json/query_toAssignPage?workOrderId=<s:property value="id"/>','400','380');" 
						onmouseover="this.className='tools5_mouseover'"  
						onmouseout="this.className='tools5_mouseout'" id="btn_chanpin">派转工单</button>
				</s:if>
				<s:else>
					<button class=tools5no_mouseout 
							onmouseover="this.className='tools5no_mouseover'"  
							onmouseout="this.className='tools5no_mouseout'" id="btn_chanpin">派转工单</button>
				</s:else>
			</s:else>
		</s:if>
		<s:else>
			<button class=tools5no_mouseout 
							onmouseover="this.className='tools5no_mouseover'"  
							onmouseout="this.className='tools5no_mouseout'" id="btn_chanpin">派转工单</button>
		</s:else>
	</s:elseif>
	
	<s:else>
		<s:if test="states==@com.hy.wo.util.Constants$States@DEALING && #groupId==tobeGroup.id && assign.statu=='ASSIGNING'">
			<button class=tools5_mouseout 
						title="把工单派转给其他部门协助处理，被派转部门可见"
						onclick="dialog.openUrl('/json/query_toAssignPage?workOrderId=<s:property value="id"/>','400','380');" 
						onmouseover="this.className='tools5_mouseover'"  
						onmouseout="this.className='tools5_mouseout'" id="btn_chanpin">派转工单</button>
		</s:if>
		<s:else>
				<button class=tools5no_mouseout 
							onmouseover="this.className='tools5no_mouseover'"  
							onmouseout="this.className='tools5no_mouseout'" id="btn_chanpin">派转工单</button>
		</s:else>
	</s:else>
</s:else>
<!--回访记录 -->
<s:if test="#roleLevel>0">
	<button class=tools7_mouseout 
					title="客服对已反馈的工单，做回访记录时用"
					onclick="dialog.openUrl('/ctService/processWorkOrder/response.jsp?woId=<s:property value="id"/>','400','380');" 
					onmouseover="this.className='tools7_mouseover'" 
					onmouseout="this.className='tools7_mouseout'">回访记录</button>
</s:if>
<s:else>
	<!--  s:if test="source!=@com.hy.wo.util.Constants$Source@PINGTAI" -->
			<s:if test="#roleId==4"><!-- if 普通客服 -->
				<s:if test="source==@com.hy.wo.util.Constants$Source@PINGTAI || source!=@com.hy.wo.util.Constants$Source@PINGTAI"><!-- if来自平台 -->
					<s:if test="states==@com.hy.wo.util.Constants$States@RESPONSED">
						<button class=tools7_mouseout 
									title="客服对已反馈的工单，做回访记录时用"
										onclick="dialog.openUrl('/ctService/processWorkOrder/response.jsp?woId=<s:property value="id"/>','400','380');" 
									onmouseover="this.className='tools7_mouseover'" 
									onmouseout="this.className='tools7_mouseout'">回访记录</button>
					</s:if>
					<s:else>
						<button class="tools3no_mouseout" 
									onmouseover="this.className='tools3no_mouseover'"  
									onmouseout="this.className='tools3no_mouseout'">回访记录</button>
					</s:else>
				</s:if>
					<s:else>									<!-- if 来自客服 -->
						<s:if test="states==@com.hy.wo.util.Constants$States@DEALING">
								<s:if test="assign.statu=='ASSIGNING'"><!-- if 处理中 派单中 -->
									<button class="tools3no_mouseout" 
									onmouseover="this.className='tools3no_mouseover'"  
									onmouseout="this.className='tools3no_mouseout'">回访记录</button>
								</s:if>
								<s:else>
			
								<button class=tools7_mouseout 
									title="客服对已反馈的工单，做回访记录时用"
										onclick="dialog.openUrl('/ctService/processWorkOrder/response.jsp?woId=<s:property value="id"/>','400','380');" 
									onmouseover="this.className='tools7_mouseover'" 
									onmouseout="this.className='tools7_mouseout'">回访记录</button>
							</s:else>
						</s:if>
						<s:elseif test="states==@com.hy.wo.util.Constants$States@DEALED"><!-- if 已处理 -->
						<button class=tools7_mouseout 
							title="客服对已反馈的工单，做回访记录时用"
							onclick="dialog.openUrl('/ctService/processWorkOrder/response.jsp?woId=<s:property value="id"/>','400','380');" 
								onmouseover="this.className='tools7_mouseover'" 
								onmouseout="this.className='tools7_mouseout'">回访记录</button>
						</s:elseif>
					</s:else>
				<s:else>
				<button class=tools7no_mouseout 
						onmouseover="this.className='tools7no_mouseover'" 
						onmouseout="this.className='tools7no_mouseout'">回访记录</button>
				</s:else>
		</s:if>
	<s:else>
		<button class=tools7no_mouseout 
					onmouseover="this.className='tools7no_mouseover'" 
					onmouseout="this.className='tools7no_mouseout'">回访记录</button>
	</s:else>
</s:else>
<!-- 删除 -->
<s:if test="#roleLevel>0">
	<div class=tools8>
		<s:if test="isdelete==false">
			<button class=tools8_mouseout 
					onclick="dialog.openUrl('/ctService/processWorkOrder/delete.jsp?woId=<s:property value="id"/>','400','380');" 
					onmouseover="this.className='tools8_mouseover'" 
					onmouseout="this.className='tools8_mouseout'">删除工单</button>
		</s:if>
		<s:else>
			<button class=tools8no_mouseout 
				onmouseover="this.className='tools8no_mouseover'" 
				onmouseout="this.className='tools8no_mouseout'">删除工单</button>
		</s:else>
	</div>
</s:if>
<s:else>
	<button class=tools8no_mouseout 
				onmouseover="this.className='tools8no_mouseover'" 
				onmouseout="this.className='tools8no_mouseout'">删除工单</button>
</s:else>
<!-- 发回 -->
<!-- <s:if test="#roleLevel>0">
	<button class=tools9_mouseout 
						id="btn_return"
						onmouseover="this.className='tools9_mouseover'" 
						onmouseout="this.className='tools9_mouseout'">发回</button>
</s:if>
<s:else>
	<s:if test="#roleId==4">
		<s:if test="states==@com.hy.wo.util.Constants$States@RESPONSED">
				<button class=tools9_mouseout 
						id="btn_return"
						onmouseover="this.className='tools9_mouseover'" 
						onmouseout="this.className='tools9_mouseout'">发回</button>
			</s:if>
			<s:else>
				<button class=tools9no_mouseout 
						onclick=""
						onmouseover="this.className='tools9no_mouseover'" 
						onmouseout="this.className='tools9no_mouseout'">发回</button>
			</s:else>
	</s:if>
	<s:else>
			<button class=tools9no_mouseout 
						onclick=""
						onmouseover="this.className='tools9no_mouseover'" 
						onmouseout="this.className='tools9no_mouseout'">发回</button>
	</s:else>
</s:else> -->
<div class=clear></div>
</div>
<!--操作按钮  结束-->
<!--  
<div class=mylistcont>
<div class=mylistbox>
<br />

<h1>工单流转信息
		<span style="margin-left:10px;color:blue;font-size:15px;" class="currState">
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

<div class="box">
<div id="qqTabAuto" class="mF_tab">
-->
<!--载入画面

<div class=tabcont>
<div class=tabtitle1>操作序号</div>
<div class=tabtitle2>操作时间</div>
<div class=tabtitle3>操作描述</div>
<div class=tabtitle4>操作人</div>
<div class=tabtitle5>操作类型</div>

</div>

<div class=tabcont>
<div class=tabcont1>1</div>
<div class=tabcont2><s:date name="createTime" format="yyyy-MM-dd  HH:mm:ss"/></div>
<div class=tabcont3>工单被创建</div>
<div class=tabcont4><s:if test="createWorker==null">
											<s:property value="userInfo.accountname"/>
										</s:if>
										<s:else>
											<s:property value="createWorker"/>
										</s:else></div>
<div class=tabcont5>创建</div>
</div>
<s:if test="getWorker!=null">
<div class=tabcont>
<div class=tabcont1>2</div>
<div class=tabcont2><s:date name="getTime" format="yyyy-MM-dd  HH:mm:ss"/></div>
<div class=tabcont3>工单被拉单</div>
<div class=tabcont4><s:property value="getWorker.accName"/></div>
<div class=tabcont5>拉单</div>
</div>
</s:if>
<s:if test="opers!=null">
	<s:iterator value="opers" status="index">
			<div class=tabcont>
			<div class=tabcont1>${index.index+3 }</div>
			<div class=tabcont2><s:date name="operTime" format="yyyy-MM-dd  HH:mm:ss"/></div>
			<div class=tabcont3 title="<s:property value="content"/>">
				<s:if test="operType.name==@com.hy.wo.util.Constants$OperTypes@PFREPLY">
					<s:if test="content.length()>40">
						<s:property value="content.substring(0,40)" />...
						<!--  s:property value="content.substring(10).substring(0,40).replaceAll('&nbsp;','').replaceAll('<br />','')" />...-->
<!--  					</s:if>
					<s:else>
					<s:property value="content" />
						<!--  s:property value="content.substring(10).replaceAll('&nbsp;','').replaceAll('<br />','')" />-->
<!--  					</s:else>
				</s:if>
				<s:else>
					<s:if test="content.length()>40">
						<s:property value="content.substring(0,40)" />...
					</s:if>
					<s:else>
						<s:property value="content" />
					</s:else>
				</s:else>
			</div>
			<div class=tabcont4>${worker}</div>
			<div class=tabcont5>${operType.name }</div>
</div>
	</s:iterator>
</s:if>
				
</div>
	-->
	<!--
</div>
</div>
</div>

</div>

查询 end -->

<!--我的工单广场  开始-->

</div>
</s:push>
<!--我的工单广场  结束-->

<!--foot  开始-->
<div class=foot>2010 All Rights Reserved 版权所有 上海火游网络科技有限公司</div>

</body>
</html>
