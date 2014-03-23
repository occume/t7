<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.you9.base.Globe"%>
<%@ page import="com.hy.wo.util.Constants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>在线自助服务专区-账号被封</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<link href="../css/basestyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/userstyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/customtab.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="../js/jquery.js"></script>
		<script language="javascript" type="text/javascript"
			src="/include/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="../js/common.js?ver=0.926"></script>
		<script type="text/javascript" src="../include/jquery.validate.min.js"></script>
		<script type="text/javascript" src="../include/additional-methods.js"></script>
		<script type="text/javascript" src="/include/view.js?v=4"></script>
		<script type="text/javascript" src="../js/accountlock.js"></script>
		<link href="../css/dialog.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="../include/dialog.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			BindSelectAjax($("#server"),'/index/grab_serverJson');
		});
	</script>
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
									<a href="/selfService/userWOList?preURL=accountlock">我的咨询记录</a>
								</li>
							</ul>

							<ul class="cont">
								<!--提问表单  开始-->
								<li>
									<form name="accountlockForm" id="accountlockForm"
										action="/wo/save_accountlock" method="post"
										enctype="multipart/form-data">
										<span><s:fielderror />
										</span>
										<div class="formcont">
											<h1>
												在线自助服务专区－账号问题－
												<span>账号被封</span>
											</h1>
											<h1>
												（火游网络致力于为广大玩家提供一个公平、健康、完善的游戏平台。）
											</h1>
											<span id="inputErr"><s:fielderror></s:fielderror>
											</span>
											<br />
											<div></div>
											<div class="formlefttitle2">
												游戏账号：
											</div>
											<div class="formrigcont">
												<input name="userInfo.accountname" id="accountname"
													class="input180" type="text" maxlength="16" tabindex="1" />
												<span>*</span>

											</div>
											<div class="formrigcontbz">
												请确认，这是您的问题账号。
											</div>
											<div class="clear"></div>
											<div class="baocuo1"></div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												角色名称：
											</div>
											<div class="formrigcont">
												<input name="userInfo.username" id="username" type="text"
													class="input180" maxlength="16" tabindex="2" />
												<span>*</span>
											</div>
											<div class="formrigcontbz">
												准确填写角色名称，有助于我们更快为您解决问题。
											</div>
											<div class="clear"></div>
											<div class="baocuo1"></div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												被封时间：
											</div>
											<div class="formrigcont">
												<input name="userInfo.happendate" id="happendate"
													type="text"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})"
													class="input180" readonly="readonly" tabindex="3" />
												<span>*</span>
											</div>
											<div class="formrigcontbz">
												准确选择问题发生时间，有助于我们更快为您解决问题。
											</div>
											<div class="clear"></div>
											<div class="baocuo1"></div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												服务器：
											</div>
											<div class="formrigcont">
												<select class="select180" name="userInfo.server.id"
													id="server" tabindex="4">
													<option></option>
												</select>
												<span>*</span>
											</div>
											<div class="clear"></div>
											<div class="baocuo1"></div>
										</div>

										<%@ include file="qqAndPhone.jsp"%>
										<script type="text/javascript">
						$(".memoBottom").html("<span>*</span>（如您对游戏账号或角色被封停，存有疑问！请在此处详细说明情况以及申请解封的理由。"+
						"我们将会有专人对您的问题再次进行审核，如理由充分合理，我们将会酌情处理。）");
				</script>

										<div class="formcont">
											<div class="formlefttitle2">
												上传截图一：
											</div>
											<div class="forminputfile">
												<input name="upload" type="file" class="input500"
													tabindex="8" />
											</div>
											<div class="clear"></div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												上传截图二：
											</div>
											<div class="forminputfile">
												<input name="upload" type="file" class="input500"
													tabindex="8" />
											</div>
											<div class="clear"></div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												上传截图三：
											</div>
											<div class="forminputfile">
												<input name="upload" type="file" class="input500"
													tabindex="9" />
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
												<a href="javascript:void(0)" onclick="register(this);"
													tabindex="10" id="submit_btn"> 提交</a>
											</div>
											<div class="buttonNo">
												<!--  a href="javascript:void(0)" onclick="javascript:document.accountlockForm.reset();return false;" tabindex="11">重填</a -->
											</div>
											<div class="clear"></div>
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
				<script src="../include/userfooter.js" type=""></script>
				<!--foot end here-->
			</div>
		</div>
	</body>
</html>
