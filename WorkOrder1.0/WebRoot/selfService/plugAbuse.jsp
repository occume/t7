<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/community/checkLogin.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>在线自助服务专区-违规举报</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="../css/basestyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/userstyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/customtab.css" type="text/css" rel="stylesheet" />

		<script type="text/javascript" src="../js/jquery.js"></script>
		<script language="javascript" type="text/javascript" src="/include/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="../js/common.js?ver=0.926"></script>
		<script type="text/javascript" src="../js/selfService.js"></script>
		
		<script type="text/javascript" src="../include/jquery.validate.min.js"></script>
		<script type="text/javascript" src="../include/additional-methods.js"></script>
		<script type="text/javascript" src="../include/plugAbuse.js"></script>
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
								<li  class="current">
									我要提问
								</li>
								<li><a href="/selfService/userWOList?preURL=plugAbuse">我的咨询记录</a></li>
							</ul>

							<ul class="cont">
								<!--提问表单  开始-->
								<li>
									<form name="plugAbuseForm" id="plugAbuseForm"
										action="/wo/save_illegalReport" method="post"
										enctype="multipart/form-data">
										 <input type="hidden" name="userInfo.accountname"
										 				 value="<s:property value="#session.member.username"/>"/>
										<div class="formcont">
											<h1>
												在线自助服务专区－游戏问题－
												<span>违规举报</span>
											</h1>
											<h1>
												（感谢您对我们建设绿色健康网游平台作出的努力！）
											</h1>
											<span id="inputErr"><s:fielderror></s:fielderror></span>
											<br />
											<div class="formlefttitle2">
												举报角色：
											</div>
											<div class="formrigcont">
												<input type="text" id="username" name="additional.username"
												maxlength="16"	class="input180" tabindex="1" />
												<span>*</span>

											</div>
											<div class="formrigcontbz">
												请尽量准确填写，您举报的角色名称。
											</div>
											<div class="clear"></div>
											<div class="baocuo1" id="usernameErr">
												
											</div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												举报原因：
											</div>
											<div class="formrigcont">
												<s:select id="name" cssClass="select180"
													name="userInfo.lvlTwo.id"  list="#session.reportKindsInfo"
													listKey="key" listValue="value" tabindex="2"></s:select>
												<span>*</span>
											</div>
											<div class="formrigcontbz"></div>
											<div class="clear"></div>
											<div class="baocuo1">
												
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
													headerValue="请选择服务器" tabindex="3"></s:select>
												<span>*</span>
											</div>
											<div class="clear"></div>
											<div class="baocuo1" id="serviceErr">
												
											</div>
										</div>
										
										<%@ include file="qqAndPhone.jsp" %>

										

										<div class="formcont">
											<div class="formlefttitle2">
												上传截图一：
											</div>
											<div class="forminputfile">
												<input name="upload" type="file" class="input500" tabindex="7" />
											</div>
											<div class="clear"></div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												上传截图二：
											</div>
											<div class="forminputfile">
												<input name="upload" type="file" class="input500" tabindex="8" />
											</div>
											<div class="clear"></div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												上传截图三：
											</div>
											<div class="forminputfile">
												<input name="upload" type="file" class="input500" tabindex="9" />
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
												<a href="javascript:void(0)"
													onclick="register(this)" tabindex="10" id="submit_btn">我要举报！</a>
											</div>
											<div class="buttonNo">
												<!--a href="javascript:void(0)"
													onclick="javascript:document.plugAbuseForm.reset();return false;" tabindex="11">重填</a -->
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

				<!--右边内容 end here-->
				<div class="clear"></div>

				<!--foot start here-->
				<script src="../include/userfooter.js" type="text/javascript"></script>
				<!--foot end here-->
			</div>
		</div>
	</body>
</html>