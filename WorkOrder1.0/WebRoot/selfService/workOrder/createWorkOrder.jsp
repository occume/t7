<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>创建工单</title>
	<link type="text/css" rel="stylesheet" href="/css/workOrder.css"/>
	<script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="/js/date.js"></script>
  </head>
  <body>
  	<div id="head"><h1>HEAD</h1></div>
  	<div id="create-box">
  		<h3>当前位置：首页>工单广场>创建工单</h3>
  		<form action="/wo/save_workOrder" method="post" enctype="multipart/form-data">
  		<table cellspacing="0">
			<tr><td>工单信息</td></tr>
			<tr>
				<td>工单来源：
					<s:select name="source.id" list="#{1:'电话',2:'邮箱'}" listKey="key" listValue="value"></s:select>
				</td>
				<td>紧急程度：
					<s:select name="urgency.id" list="#{1:'紧急',2:'一般'}" listKey="key" listValue="value"></s:select>
				</td>
				<td>结案时间：<input id="textfield" type="text" name="prefinishTime" ></td>
			</tr>
			<tr><td>用户信息</td></tr>
			<tr>
				<td>玩家姓名：<input type="text" name="userInfo.realname"/></td>
				<td>联系电话：<input type="text" name="userInfo.tel"/></td>
				<td>联系邮箱：<input type="text" name="userInfo.email"/></td>
			</tr>
			<tr><td>问题信息</td></tr>
			<tr>
				<td>玩家账号：<input type="text" name="userInfo.accountname"/></td>
				<td>角色名称：<input type="text" name="userInfo.username"/></td>
				<td>角色职业：
					<s:select name="userInfo.classCategory.id" list="#{1:'半人马酋长',2:'德鲁伊',3:'敌法师'}" listKey="key" listValue="value"></s:select>
				</td>
			</tr>
			<tr>
				<td>游戏名称：
					<s:select name="userInfo.game.id" list="#{1:'风云传奇',2:'红色警戒',3:'DOTA'}" listKey="key" listValue="value"></s:select>
				</td>
				<td>游戏大区：
					<s:select name="userInfo.area.id" list="#{1:'西北',2:'华东'}" listKey="key" listValue="value"></s:select>
				</td>
				<td>服务器名：
					<s:select name="userInfo.server.id" list="#{1:'电信一区',2:'风云电信一区'}" listKey="key" listValue="value"></s:select>
				</td>
			</tr>
			<tr>
				<td>问题大类
					<s:select name="userInfo.lvlOne.id" list="#{3:'游戏问题'}" listKey="key" listValue="value"></s:select>
				</td>
				<td>二级分类
					<s:select name="userInfo.lvlTwo.id" list="#{1:'角色恢复',2:'服务器故障'}" listKey="key" listValue="value"></s:select>
				</td>
				<td>发生时间:</td>
			</tr>
  		</table>
		<h4>问题描述</h4>
		<textarea class="memo" name="userInfo.memo"></textarea>
		<h4>处理建议</h4>
		<textarea id="suggestion" name="suggestion"></textarea>
		<h4>上传附件</h4>
		<s:file name="upload"></s:file>
		<s:file name="upload"></s:file>
		<s:file name="upload"></s:file>
		<s:submit value="Send!"></s:submit>
		</form>
  	</div>
  </body>
</html>