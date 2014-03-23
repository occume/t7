<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/community/checkLogin.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>在线自助服务专区－ 游戏运行 </title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<link href="../css/basestyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/userstyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/dialog.css" type="text/css" rel="stylesheet" />
		<link href="../css/customtab.css" type="text/css" rel="stylesheet" />
		
		<script type="text/javascript" src="../js/jquery.js"></script>
		<script type="text/javascript" src="../include/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="../js/common.js?ver=0.926"></script>
		<script type="text/javascript" src="../js/selfService.js"></script>
		<script type="text/javascript" src="../include/jquery.validate.min.js"></script>
		<script type="text/javascript" src="../include/additional-methods.js"></script>
		<script type="text/javascript" src="../include/gameRunning.js"></script>
		<script type="text/javascript" src="../include/dialog.js"></script>
		<script type="text/javascript" src="../js/commonInit.js"></script>
<script>
	function toggleClick()
		{
			if ($("#detailinfo").css("display")=="none")
			{	
				$("#detailinfo").slideDown('slow');
				$("#zkicon").removeClass("ui-icon-triangle-1-s");
				$("#zkicon").addClass("ui-icon-triangle-1-d");
				$("#zktext").html("收起");	
			}else
				{
					$("#detailinfo").slideUp('slow');
					$("#zkicon").removeClass("ui-icon-triangle-1-d");
					$("#zkicon").addClass("ui-icon-triangle-1-s");
					$("#zktext").html("展开");		
				}
				
			}
</script>



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
								<li><a href="/selfService/userWOList?preURL=gameRunning">我的咨询记录</a></li>
							</ul>

							<ul class="cont">
								<!--提问表单  开始-->
								<li>
									<form name="roleAbnormalForm" id="roleAbnormalForm" action="/wo/save_gameRun" method="post" enctype="multipart/form-data" >
										<div class="formcont">
											<h1>
												在线自助服务专区－游戏问题－
												<span>游戏运行</span>
											</h1>
											<h1>
												（请尽量提供您电脑的详细配置信息，以便我们能更好地为您分析解决问题。）
											</h1>
											 <span id="inputErr"><s:fielderror></s:fielderror></span>
											<br />
											<div class="formlefttitle2">
												游戏账号：
											</div>
											<div class="formrigcont">
												[
												 <s:property value="#session.member.username"/>
												]
		  <input type="hidden" name="userInfo.accountname" value="<s:property value="#session.member.username"/>"/>
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
												角色名称：
											</div>
											<div class="formrigcont">
												 <input type="text" id="username" name="userInfo.username" class="input180" maxlength="16" tabindex="1" />
												<span>*</span>
											</div>
											<div class="formrigcontbz">
												准确填写角色名称，有助于我们更快为您解决问题。
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
												<input type="text" id="happendate" name="userInfo.happendate" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})"  class="input180" tabindex="2"/>
												<span>*</span>
											</div>
											<div class="formrigcontbz">
												准确选择问题发生时间，有助于我们更快为您解决问题。
											</div>
											<div class="clear"></div>
											<div class="baocuo1">
											</div>
										</div>


										<div class="formcont">
											<div class="formlefttitle2">
												服务器：
											</div>
											<div class="formrigcont">
												<s:select id="name" cssClass="select180" name="userInfo.server.id" list="#session.serviceInfo" listKey="key" listValue="value" headerKey="0" headerValue="请选择服务器" tabindex="3"></s:select>
												<span>*</span>
											</div>
											<div id="lvltwo" class="formrigcont3" style="margin-right:50px">
												<select class="select180" name="userInfo.lvlTwo.id" tabindex="4">
													<option value="0">请选择问题小类</option>
												</select>
												<span>*</span>
											</div>
											<div class="formlefttitle3">
												问题小类：
											</div>
											<div class="clear"></div>
										</div>

										<div class="baocuo">
											<div class="baocuoleft">
											</div>
											<div class="baocuoright">
											</div>
											<div class="clear"></div>
										</div>



										<div class="formcontbox2">
											<div class="toptext">
												请点击展开，填写您电脑的硬件网络信息，有助于我们为您更好地解决问题。（选填）
											</div>

											<div class="top" style="height: 18px; cursor: pointer;"
												id="zkbtn" onclick="toggleClick();">
												<div class="ui-icon-triangle-1-s" id="zkicon"></div>
												<div class="ui-icon-triangle-1-s-text" id="zktext">
													展开
												</div>
												<div class="clear"></div>
											</div>


											<div id="detailinfo" style="display: none;">


												<div class="formcont2">
													<div class="formlefttitle2">
														当前游戏版本：
													</div>
													<div class="formrigcont">
														 <input type="text" class="input180" name="aditional.currentVersion" maxlength="12" tabindex="5" />
													</div>
													<div class="formrigcont3">
														 <input type="text" class="input180" name="device.cpu" tabindex="6" />
													</div>
													<div class="formlefttitle3">
														CPU型号：
													</div>
													<div class="clear"></div>
												</div>

												<div class="baocuo2">
													<div class="baocuo3">
													</div>
													<div class="baocuo4">
													</div>
													<div class="clear"></div>
												</div>

												<div class="formcont2">
													<div class="formlefttitle2">
														显卡型号：
													</div>
													<div class="formrigcont">
														<input class="input180" type="text" name="device.displaycard" maxlength="12" tabindex="7" />
													</div>
													<div class="formrigcont3">
														<input class="input180" type="text" name="device.memory" maxlength="12" tabindex="8" />
													</div>
													<div class="formlefttitle3">
														内存大小：
													</div>
													<div class="clear"></div>
												</div>

												<div class="baocuo2">
													<div class="baocuo3">
													</div>
													<div class="baocuo4">
													</div>
													<div class="clear"></div>
												</div>

												<div class="formcont2">
													<div class="formlefttitle2">
														操作系统：
													</div>
													<div class="formrigcont">
														<input class="input180" type="text" name="device.os" maxlength="12" tabindex="9" />
													</div>
													<div class="formrigcont3">
														<input class="input180" type="text" name="device.netinfo" maxlength="12" tabindex="8" />
													</div>
													<div class="formlefttitle3">
														网络供应商：
													</div>
													<div class="clear"></div>
												</div>

												<div class="baocuo2">
													<div class="baocuo3">
													</div>
													<div class="clear"></div>
												</div>


											</div>

											<div class="bottom"></div>
										</div>

										<%@ include file="qqAndPhone.jsp" %>

										

										<div class="formcont">
											<div class="formlefttitle2">
												上传截图一：
											</div>
											<div class="forminputfile">
												<input name="upload" type="file" class="input500" tabindex="13" />
											</div>
											<div class="clear"></div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												上传截图二：
											</div>
											<div class="forminputfile">
												<input name="upload" type="file" class="input500" tabindex="14" />
											</div>
											<div class="clear"></div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												上传截图三：
											</div>
											<div class="forminputfile">
												<input name="upload" type="file" class="input500" tabindex="15" />
											</div>
											<div class="clear"></div>
											<div class=formnotestext2><span>友情提示：</span><br/>1.上传图片的格式,支持 jpg - jpeg - gif 格式的图片,图片大小允许2MB以内<br/>2.您可以在游戏中使用PRINTSCREEN键进行截图，然后可以在您的游戏安装目录下FengYunOLGame\ScreenShots找到该截图） </div>
										</div>


										<div class="buttonBox">
											<div class="buttonYes">
												<a onclick="register(this);" tabindex="16" id="submit_btn">怎么办？</a>
											</div>
											<div class="buttonNo">
												<!-- a onclick="javascript:roleAbnormalForm.reset();return false;" tabindex="17">重填</a  -->
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