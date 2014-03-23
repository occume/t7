<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/community/checkLogin.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>在线自助服务专区-充值问题</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="../css/basestyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/userstyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/customtab.css" type="text/css" rel="stylesheet" />

		<script type="text/javascript" src="../js/jquery.js"></script>
		<script language="javascript" type="text/javascript"
			src="/include/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="../js/common.js?ver=0.926"></script>
		<script type="text/javascript" src="../js/selfService.js"></script>
		<script type="text/javascript" src="../include/jquery.validate.min.js"></script>
		<script type="text/javascript" src="../include/additional-methods.js"></script>
		<script type="text/javascript" src="../include/rechargeProblem.js"></script>
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
				<jsp:include flush="true" page="/include/servicemenu.jsp" />
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
								<li>
									<a href="/selfService/userWOList?preURL=rechargeProblem">我的咨询记录</a>
								</li>
							</ul>

							<ul class="cont">
								<!--提问表单  开始-->
								<li>
									<form name="roleRecoveryForm" id="roleRecoveryForm"
										action="/wo/save_rechargeProblem" method="post"
										enctype="multipart/form-data"
										onsubmit="return checkInput(this);">
										<div class="formcont">
											<h1>
												在线自助服务专区－游戏问题－
												<span>充值问题</span>
											</h1>

											<span id="inputErr"><s:fielderror></s:fielderror> </span>
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
											<div class="baocuo1">

											</div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												发生时间：
											</div>
											<div class="formrigcont">
												<input type="text" id="happendate" readonly="readonly"
													name="userInfo.happendate"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})"
													class="input180" tabindex="1" />
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
												<s:select id="name" cssClass="select180"
													name="userInfo.server.id" list="#session.serviceInfo"
													listKey="key" listValue="value" headerKey="0"
													headerValue="请选择服务器" tabindex="2"></s:select>
												<span>*</span>
											</div>
											<div class="clear"></div>
											<div class="baocuo1" id="serviceErr">

											</div>
										</div>

										<div class="formcontbox2">
											<div class="top"></div>
											<br/>
											<div class="formcont2">
												<div class="formlefttitle2">
													充值类型：
												</div>
												<div class="formrigcont"><!-- list="#session.recharge" -->
													<s:select id="dname" cssClass="select180"
														name="recharge.rechargeType.id" list="#{'26':'点卡充值'}"
														listKey="key" listValue="value" headerKey="0"
														onchange="rechargeTypeSelect(this)" headerValue="请选择"
														tabindex="3"></s:select>
													<span>*</span>
												</div>
												<div class="formrigcont3">
													<input id="rechargeCard" name="recharge.card"
														class="input180" type="text" tabindex="4" maxlength="15"/>
												</div>
												<div class="formlefttitle3">
													<span id="cardname" style="color: black;">充值卡号</span>：
												</div>
												<div class="clear"></div>
												<div class="baocuo1">
													<span class="rechargeServer" style="width:85px;"></span>
													<span class="rechargeCard" style="margin-left: 260px;"></span>
												</div>
											</div>


											<div class="formcont2">
												<div class="formlefttitle2">
													充值错误类型：
												</div>
												<div class="formrigcont">
													<s:select id="name" cssClass="select180"
														name="recharge.rechargeErrorType"
														list="{'充值未到帐','到账金额不足'}" headerKey="未选择"
														headerValue="请选择" tabindex="3"></s:select>

												</div>
												<div class="formrigcont3">
													<input id="rechargeSum" name="recharge.sum"
														class="input180" type="text" tabindex="4" maxlength="6"/>
													元
												</div>
												<div class="formlefttitle3">
													<span id="cardSum" style="color: black;">充值金额</span>：
												</div>
												<div class="clear"></div>
												<div class="baocuo1">
													<span class="rechargeSum" style="margin-left: 350px;"></span>
												</div>

											</div>

											<div class="bottom"></div>
										</div>

										<%@ include file="qqAndPhone.jsp"%>
										
										
										<div class="formcont">
						<div class="formlefttitle2">
							上传截图一：
						</div>
						<div class="forminputfile">
							<input name="upload" type="file" class="input500" tabindex="8" />
						</div>
						<div class="clear"></div>
					</div>

					<div class="formcont">
						<div class="formlefttitle2">
							上传截图二：
						</div>
						<div class="forminputfile">
							<input name="upload" type="file" class="input500" tabindex="9" />
						</div>
						<div class="clear"></div>
					</div>

					<div class="formcont">
						<div class="formlefttitle2">
							上传截图三：
						</div>
						<div class="forminputfile">
							<input name="upload" type="file" class="input500" tabindex="10" />
						</div>
						<div class="clear"></div>
						<div class="formnotestext2">
							<span>友情提示：</span>
							<br />
							1.上传图片的格式,支持 jpg - jpeg - gif 格式的图片,图片大小允许2MB以内
							<br />
							2.您可以在游戏中使用PRINTSCREEN键进行截图，然后可以在您的游戏安装目录下FengYunOLGame\ScreenShots找到该截图）
						</div>
					</div>


					<div class="buttonBox">
						<div class="buttonYes">
							<a onclick="register(this);" tabindex="11" id="submit_btn">提交</a>
						</div>
						<div class="buttonNo">
							<!--  a
													onclick="javascript:roleRecoveryForm.reset();return false;" tabindex="11">重填</a -->
						</div>
					</div>
										
									</form>
								</li>
								<!--提问表单  结束-->
								<li>
					</li>
							</ul>
						</div>
					</div>


				</div>
			</div>
		</div>

		<!--右边内容 end here-->
		<div class="clear"></div>

		<!--foot start here-->
		<script src="../include/userfooter.js" type="text/javascript"></script>
		<!--foot end here-->
		<div></div>
		<div></div>
	</body>
</html>