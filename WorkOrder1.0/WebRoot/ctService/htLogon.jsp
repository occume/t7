<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	if(session.getAttribute("staff")!=null){//已经登录
		response.sendRedirect("/ctService/htIndex.jsp");//+request.getQueryString());
	}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title>上海火游网络有限公司-客服后台管理中心</title>
		<link href="/css/htstyle.css" type="text/css" rel="stylesheet" />
		<link href="/css/editstyle.css" type="text/css" rel="stylesheet" />
	</head>
	<body class="loginbody">
		<div class="loginhead"></div>
		<div class="logincont">
			<div class="empty">
				<s:actionerror />
			</div>
			<form action="/acc/acc_logon" method="post">
				<input type="hidden" name="preURL"
					value='<s:property value="#parameters.preURL"/>' />

				<div class="loginbox">
					<div class="box1"></div>
					<div class="box2" style="color: red;">
						<s:fielderror />
					</div>
					<div class="clear"></div>
				</div>

				<div class="loginbox">
					<div class="box1">
						账号：
					</div>
					<div class="box2">
						<input name="accName" class="input200" type="text" />
					</div>
					<div class="clear"></div>
				</div>

				<div class="loginbox">
					<div class="box1">
						密码：
					</div>
					<div class="box2">
						<input name="password" class="input200" type="password" />
					</div>
					<div class="clear"></div>
				</div>


				<div class="loginbutton">
					<button class="btnl_mouseout"
						onmouseover="this.className='btnl_mouseover'"
						onmouseout="this.className='btnl_mouseout'" type="submit">
						登录
					</button>
					<button class="btnr_mouseout"
						onmouseover="this.className='btnr_mouseover'"
						onmouseout="this.className='btnr_mouseout'" type="reset">
						重填
					</button>
					<div class="clear"></div>
				</div>
			</form>

		</div>
		<!--foot  开始-->
		<div class="loginfoot">
			2010 All Rights Reserved 版权所有 上海火游网络科技有限公司
		</div>
	</body>
</html>