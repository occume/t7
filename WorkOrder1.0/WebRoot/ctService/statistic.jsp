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
<script language="javascript" type="text/javascript" src="/include/My97DatePicker/WdatePicker.js"></script>
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
			当前位置：<a href="/ctService/htIndex.jsp">首页</a> >统计数据查询
			<hr/>
		</div>
		<div id="statistic">
			<div id="via-employee">
				<form id="gdcxform" action="/statistic/search_byAccName"  method="get">
						<h3>查询指定日期范围内的，按客服工号（员工账号），所有创建工单、派转工单、回访工单的总计数量</h3>
						<div id="condition-box" class="check4">
							开始时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})"
								name="startTime" id="prefinishTime" />
							结束时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'prefinishTime\')}'})"
								name="endTime" id="prefinishTime" />	
							员工账号:<input  type="text" name="accName"/>
							<input type="submit" value="导出表格"/>
						</div>
				</form>
			</div>
			
			<div id="via-issueType">
				<form id="gdcxform" action="/statistic/search_byType"  method="get">
						<h3>查询指定日期范围内的，问题类型统计</h3>
						<div id="condition-box" class="check4">
							开始时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})"
								name="startTime" id="prefinishTime" />
							结束时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'prefinishTime\')}'})"
								name="endTime" id="prefinishTime" />	
							问题类型:<s:select id="name" cssClass="select180" name="issueId"
															 list="#session.issueInfo" listKey="key" listValue="value"
															 headerKey="0" headerValue="请选择问题类型" tabindex="3"></s:select>
							<input type="submit" value="导出表格"/>
						</div>
				</form>
			</div>
			
			<div id="via-finishRate">
				<form id="gdcxform" action="/statistic/search_finishRate"  method="get">
						<h3>查询指定日期范围内的，期望结案时间内完成率统计</h3>
						<div id="condition-box" class="check4">
							开始时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})"
								name="startTime" id="prefinishTime" />
							结束时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'prefinishTime\')}'})"
								name="endTime" id="prefinishTime" />	
							问题类型:<s:select id="name" cssClass="select180" name="groupId"
															 list="#session.groupInfo" listKey="key" listValue="value"
															 headerKey="0" headerValue="请选择小组名称" tabindex="3"></s:select>
							<input type="submit" value="导出表格"/>
						</div>
				</form>
			</div>
			
			<div id="via-satisfaction">
				<form id="gdcxform" action="/statistic/search_bySatisfaction"  method="get">
						<h3>查询指定日期范围内的，按客服工号（员工账号），满意度统计</h3>
						<div id="condition-box" class="check4">
							开始时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})"
								name="startTime" id="prefinishTime" />
							结束时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'prefinishTime\')}'})"
								name="endTime" id="prefinishTime" />	
							问题类型:<s:select id="name" cssClass="select180" name="groupId"
															 list="#session.groupInfo" listKey="key" listValue="value"
															 headerKey="0" headerValue="请选择小组名称" tabindex="3"></s:select>
							<input type="submit" value="导出表格"/>
						</div>
				</form>
			</div>
			
			<div id="via-keyWord">
				<form id="gdcxform" action="/statistic/search_byKeyWord"  method="get">
						<h3>查询指定日期范围内的，关键字</h3>
						<div id="condition-box" class="check4">
							开始时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})"
								name="startTime" id="prefinishTime" />
							结束时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'prefinishTime\')}'})"
								name="endTime" id="prefinishTime" />	
							关键字:<input type="text" name="keyWord"/>
							<input type="submit" value="导出表格"/>
						</div>
				</form>
			</div>
			
			<div id="via-keyWord">
				<form id="gdcxform" action="/statistic/search_byEveryDay"  method="get">
						<h3>查询指定日期范围内的，按客服工号（员工账号），所有创建工单、派转工单、回访工单的总计数量(每天每人)</h3>
						<div id="condition-box" class="check4">
							开始时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})"
								name="startTime" id="prefinishTime" />
							结束时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'prefinishTime\')}'})"
								name="endTime" id="prefinishTime" />	
							员工账号:<input type="text" name="accName"/>
							<input type="submit" value="导出表格"/>
						</div>
				</form>
			</div>
			
			<div id="via-keyWord">
				<form id="gdcxform" action="/statistic/search_byEveryDayReply"  method="get">
						<h3>查询指定日期范围内的，按客服工号（员工账号），每人处理工单量（结案）统计</h3>
						<div id="condition-box" class="check4">
							开始时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})"
								name="startTime" id="prefinishTime" />
							结束时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'prefinishTime\')}'})"
								name="endTime" id="prefinishTime" />	
							员工账号:<input type="text" name="accName"/>
							<input type="submit" value="导出表格"/>
						</div>
				</form>
			</div>
			
			
			<div id="via-keyWord">
				<form id="gdcxform" action="/statistic/search_byEveryDayDeal"  method="get">
						<h3>查询指定日期范围内的，其他部门处理数量(每天每人)</h3>
						<div id="condition-box" class="check4">
							开始时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})"
								name="startTime" id="prefinishTime" />
							结束时间：<input class="validate[required] input100" type="text" readonly="readonly"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'prefinishTime\')}'})"
								name="endTime" id="prefinishTime" />	
							员工账号:<input type="text" name="accName"/>
							<input type="submit" value="导出表格"/>
						</div>
				</form>
			</div>
			
		</div>
</body>
</html>