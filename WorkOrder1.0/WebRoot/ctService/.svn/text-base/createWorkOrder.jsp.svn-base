<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="common/checkLogin.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!--<meta http-equiv="x-ua-compatible" content="ie=7" />-->
		<title>上海火游网络有限公司-客服后台管理中心</title>
		<link href="/css/style.css" type="text/css" rel="stylesheet" />
		<link href="/css/htstyle.css" type="text/css" rel="stylesheet" />
		<link href="/css/editstyle.css" type="text/css" rel="stylesheet" />
		<link href="/css/validationEngine.jquery.css" rel="stylesheet"type="text/css" />
		<link href="/css/dialog.css" rel="stylesheet"type="text/css" />
		<link href="/css/utils.css" rel="stylesheet"type="text/css" />
		<script language="javascript" type="text/javascript"src="/include/jquery.min.js"></script>
		<script language="javascript" type="text/javascript"src="/include/My97DatePicker/WdatePicker.js"></script>
		<script language="javascript" type="text/javascript"src="/include/jquery.validationEngine.js"></script>
		<script language="javascript" type="text/javascript"src="/include/jquery.validationEngine-zh_TW.js"></script>
		<script language="javascript" type="text/javascript"src="/include/jquery.livequery.js"></script>
		<script language="javascript" type="text/javascript"src="/js/commonInit.js"></script>
		<script language="javascript" type="text/javascript"src="/include/dialog.js"></script>
		<script language="javascript" type="text/javascript" src="/js/utils.js"></script>

<script type="text/javascript">
	String.prototype.getBytes=function(){
	var cArr= this.match(/[^x00-xff]/ig);
	return this.length + (cArr == null ? 0 : cArr.length);
}
$(function(){
	$("#newForm").validationEngine();
});	
$(function(){
	readContact($(".readContact"));//自动读取玩家联系方式

	var readMail=new matchArrFun("input[name='userInfo.mail']");
	readMail.init()
})

function checkJueSe(field, rules, i, options){
				var val=field.val();
				if (val=="")
					return "游戏角色不能为空";
				if (val.getBytes()<4||val.getBytes()>16)
					return "请输入正确的游戏角色名";
				
				var c=/^[\-\+]?(([0-9]+)([\.,]([0-9]+))?|([\.,]([0-9]+))?)$/; 
				if (c.test(val.substring(0,1)))
					return "请输入正确的游戏角色名";
            }
function checkTextArea(field, rules, i, options){
	var val=field.val();
	if (val.length<3)
	return "至少需要输入3个以上字符";
}			


var tempradio= null;    
function check(checkedRadio)    
{    
	if(tempradio== checkedRadio){  
		tempradio.checked=false;  
		tempradio=null;  
	  }   
	  else{  
		  tempradio= checkedRadio;    
	   }  
 }    

		
</script>
		<style type="text/css">
.error-info li {
	float: left;
	color: red;
	list-style: none;
}
</style>
	</head>
	<body>
		<!--title start -->
		<%@include file="common/exit.jsp"%>
		<!--title end -->
		<div class="location">
			当前位置：<a href="/ctService/htIndex.jsp">首页</a>  〉工单广场 〉创建工单
		</div>


		<!--查询 开始 -->

		<div class="wrappercheck">
			<form id="newForm" action="/wo/save_workOrder"
				enctype="multipart/form-data" method="post">
				<div class="newbox">
					<span class="error-info"><s:fielderror></s:fielderror>
					</span>
					<br />

					<div class="edit">
						<h2>
							工单信息
						</h2>
						<div class="edit1">
							工单来源：
						</div>
						<div class="edit2">
							<s:select id="source.id" name="source" list="#session.sourceInfo"
								listKey="value" listValue="value" headerKey="0"
								headerValue="请选择来源" cssClass="input180"></s:select>
						</div>
						<div class="edit3">
							紧急程度：
						</div>
						<div class="edit4">
							<s:select id="urgency.id" name="urgency"
								list="#session.urgencyInfo" listKey="value" listValue="value"
								headerKey="0" headerValue="请选择紧急程度" cssClass="input180"></s:select>
						</div>
						<div class="edit5">
							结案时间：
						</div>
						<div class="edit6">
							<input class="input200" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext'})"
								name="prefinishTime" id="prefinishTime" />
								<span></span>
						</div>
						<div class="clear"></div>
					</div>

					<div class="edit">
						<h2>
							用户信息
						</h2>
						<div class="edit1">
							玩家姓名：
						</div>
						<div class="edit2">
							<s:textfield cssClass="validate[required] input200"
								name="userInfo.realname" id="userInfo.realname"  value="无名玩家"/>
						</div>

						<div class="edit3">
							联系电话：
						</div>
						<div class="edit4">
							<s:textfield
								cssClass="input200"
								name="userInfo.tel" id="userInfo.tel" />
							<span></span>
						</div>
						<div class="edit5">
							联系方式：
						</div>
						<div class="edit6">
							<s:textfield
								cssClass="input200"
								name="userInfo.mail" id="userInfo.mail" />
							<span></span>
						</div>
						<div class="clear"></div>
					</div>

					<div class="edit">
						<h2>
							问题信息
						</h2>
						<div class="edit1">
							玩家账号：
						</div>
						<div class="edit2">
							<s:textfield cssClass="validate[required] validate[ajax[ajaxNameCall]] input200 readContact"
							   name="userInfo.accountname" />
							<span>*</span>
						</div>
						<div class="edit3">
							角色名称：
						</div>
						<div class="edit4">
							<s:textfield cssClass="input200"
								 name="userInfo.username" id="userInfo.username" />
							<span></span>
						</div>
						<div class="edit5">
							角色职业：
						</div>
						<div class="edit6">
							<s:select id="userInfo.classCategory.id"
								name="userInfo.classCategory.id"
								list="#session.classCategoryInfo" listKey="key"
								listValue="value" headerKey="0" headerValue="请选择职业" tabindex="2"
								cssClass="input180"></s:select>
							<span></span>
						</div>
						<div class="edit7">
							游戏名称：
						</div>
						<div class="edit8">
							<s:select id="userInfo.game.id" name="userInfo.game.id"
								list="#session.gameInfo" listKey="key" listValue="value"
								headerKey="0" headerValue="请选游戏" tabindex="2"
								cssClass="input180"></s:select>
						</div>
						<div class="clear"></div>
					</div>

					<div class="edit">
						<div class="edit1">
							游戏大区：
						</div>
						<div class="edit2">
							<s:select name="userInfo.area.id" list="#session.areaInfo"
								listKey="key" listValue="value" cssClass="input180"
								headerKey="0" headerValue="请选择大区"></s:select>
						</div>
						<div class="edit3">
							服务器名：
						</div>
						<div class="edit4">
							<s:select id="userInfo.server.id" name="userInfo.server.id"
								list="#session.serverInfo" listKey="key" listValue="value"
								headerKey="0" headerValue="请选择服务器" cssClass="input180"></s:select>
						</div>
						<div class="edit5">
							问题大类：
						</div>
						<div class="edit6" id="lvlone">
							<s:select id="userInfo.lvlOne.id" name="userInfo.lvlOne.id"
								list="#session.issueInfo" listKey="key" listValue="value"
								headerKey="0" headerValue="请选择问题大类型" cssClass="validate[required] input180"></s:select>
						</div>
						<div class="edit7">
							二级分类：
						</div>
						<div class="edit8" id="lvltwo">
						<select id="userInfo.lvlTwo.id" name="userInfo.lvlTwo.id" class="input180">
							<option value="0">请选择问题二级类型</option>
						</select>
							
						</div>
						<div class="clear"></div>
					</div>

					<div class="edit">
						<div class="edit1">
							发生时间：
						</div>
						<div class="edit2">
							<s:textfield cssClass="validate[required] input200" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext'})"
								name="userInfo.happendate" id="happenddate" />
						</div>
						<div class="edit3">
							电话回访：
						</div>
						<div class="edit4">
							<input type="checkbox"  name="needhf" value="1"/>
						</div>
						<div class="clear"></div>
					</div>

					<div class="edit">
						<div class="edit1">
							问题描述：
						</div>
						<div class="textarea">
							<s:textarea
								cssClass="validate[funcCall[checkTextArea]] textarea1175"
								cols="" rows="20" style="height:200px;" name="userInfo.memo"
								id="userInfo.memo"></s:textarea>
						</div>
						<div class="clear"></div>
					</div>

					<div class="edit">
						<div class="edit1">
							处理建议：
						</div>
						<div class="textarea">
							<input type="radio" name="immediate" value="1"
								onclick="check(this)" />
							<span style="color: red;">即时解答(已回复)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<input type="radio" name="immediate" value="2"
								onclick="check(this)" />
							<span style="color: blue;">即时解决(已处理)</span>
							<input id="imeAssign" type="radio" name="immediate" value="3"
								 />
							<span style="color: green;">即时派单</span>
							
							<span style="display:none;">
							<select id="cGroup" name="toGroupId"><option value="0">请选择小组</option></select>
							<select id="cStaff" name="toStaffId"><option value="0">请选择员工</option></select>
							</span>
							
							<br />
							<s:textarea
								cssClass="validate[funcCall[checkTextArea]] textarea1175"
								cols="" rows="20" style="height:100px;" name="suggestion"
								id="suggestion"></s:textarea>
						</div>
						<div class="clear"></div>
					</div>

					<div class="edit">
						<div class="edit1">
							截图一：
						</div>
						<div class="editpic1">
							<input class="inputfile850" name="upload" type="file" />
						</div>
						<div class="clear"></div>
					</div>

					<div class="edit">
						<div class="edit1">
							截图二：
						</div>
						<div class="editpic1">
							<input class="inputfile850" name="upload" type="file" />
						</div>
						<div class="clear"></div>
					</div>

					<div class="edit">
						<div class="edit1">
							截图三：
						</div>
						<div class="editpic1">
							<input class="inputfile850" name="upload" type="file" />
						</div>
						<div class="clear"></div>
					</div>


				</div>



				<!--操作按钮  开始-->
				<div class="loginbutton">

					<button class="btnl_mouseout"
						onmouseover="this.className='btnl_mouseover'"
						onmouseout="this.className='btnl_mouseout'" type="submit">
						创建
					</button>
					<button class="btnr_mouseout"
						onmouseover="this.className='btnr_mouseover'"
						onmouseout="this.className='btnr_mouseout'" type="reset">
						重填
					</button>
				</div>
				<!--操作按钮  结束-->

			</form>
		</div>


		<!--查询 end -->


		<!--foot  开始-->
		<div class="foot">
			2010 All Rights Reserved 版权所有 上海火游网络科技有限公司
		</div>
		<script type="text/javascript" src="include/usertab.js"></script>
	</body>
</html>