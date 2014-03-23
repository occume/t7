<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/community/checkLogin.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>在线自助服务专区-服务器故障</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="../css/basestyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/userstyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/customtab.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="../js/jquery.js"></script>
		<script language="javascript" type="text/javascript" src="/include/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="../js/common.js?ver=0.926"></script>
		<script type="text/javascript" src="../include/jquery.validate.min.js"></script>
		<script type="text/javascript" src="../include/additional-methods.js"></script>
		<script type="text/javascript" src="../include/serverFailure.js"></script>
		<link href="../css/dialog.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="../include/dialog.js"></script>
	</head>
	<body>
		<div class="main">
			<!--head start here-->
			<jsp:include flush="true" page="/include/head.jsp" />
			<!--head end here-->

			<div id="contentbox2">

				<!--左边滑动菜单 start here-->
				<jsp:include flush="true" page="/include/servicemenu.jsp"/>
				<!--左边滑动菜单 end here-->


				<!--右边内容 start here-->
				<div id="changpasswdright">

					<div class="box">
						<div class="mF_tab">
							<!--载入画面-->
							<ul class="btn">
								<li class="current">
									我要提问
								</li>
								<li><a href="/selfService/userWOList?preURL=serverFailure">我的咨询记录</a></li>
							</ul>

							<ul class="cont">
								<!--提问表单  开始-->
								<li>
									<form name="serverFailureForm" id="serverFailureForm"
										action="/wo/save_serverDefault" method="post"
										enctype="multipart/form-data" >
										<div class="formcont">
											<h1>
												在线自助服务专区－游戏问题－
												<span>服务器故障</span>
											</h1>
											<span id="inputErr"><s:fielderror></s:fielderror>
											</span>
											<br />
											<div class="formlefttitle2">
												游戏账号：
											</div>
											<div class="formrigcont">
												[
												<s:property value="#session.member.username" />
												]
												<input type="hidden" name="userInfo.accountname"
													value='<s:property value="#session.member.username"/>' />
											</div>
											<div class="formrigcontbz">
												请确认，这是您的问题账号。
											</div>
											<div class="clear"></div>
											<div class="baocuo1"></div>
											<!-- <DIV class="baocuo1">
												报错
											</DIV> -->
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												发生时间：
											</div>
											<div class="formrigcont">
												<input type="text" id="happendate"
													name="userInfo.happendate" readonly="readonly"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})"  class="input180"
													tabindex="1" />
												<span>*</span>
											</div>
											<div class="formrigcontbz">
												准确选择问题发生时间，有助于我们更快为您解决问题。
											</div>
											<div class="clear"></div>
											<div class="baocuo1" id="happendateErr">
											</div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												服务器：
											</div>
											<div class="formrigcont">
												<s:select id="name" name="userInfo.server.id"
													list="#session.serviceInfo" listKey="key" listValue="value"
													headerKey="0" headerValue="请选择服务器" tabindex="2"></s:select>
												<span>*</span>
											</div>
											<div class="clear"></div>
											<div class="baocuo1" id="serviceErr">

											</div>
										</div>

										<%@ include file="qqAndPhone.jsp" %>

										<div class="buttonBox">
											<div class="buttonYes">
												<a href="javascript:void(0)"
													onclick="register(this);" tabindex="6">快点解决</a>
											</div>
											<div class="buttonNo">
												<!-- a href="javascript:void(0)"
													onclick="javascript:document.serverFailureForm.reset();return false;" tabindex="7">重填</a>  -->
											</div>
											<div class="clear"></div>
										</div>
									</form>
								</li>
								<!--提问表单  结束-->
							</ul>
						</div>
					</div>


				</div>

				<!--右边内容 end here-->
				<div class="clear"></div>

				<!--foot start here-->
				<script src="../include/userfooter.js" type=""></script>
				<!--foot end here-->
			</div>
		</div>
	</body>
</html>