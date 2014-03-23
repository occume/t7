<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/community/checkLogin.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>上海火游网络有限公司-意见建议</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />	
		<link href="../css/basestyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/userstyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/customtab.css" type=text/css rel=stylesheet />
		<script type="text/javascript" src="../js/jquery.js"></script> 
		<script type="text/javascript" src="../js/common.js?ver=0.926"></script> 	 	
		<script type="text/javascript" src="../include/jquery.validate.min.js"></script>
		<script type="text/javascript" src="../include/additional-methods.js"></script>	
		<script type="text/javascript" src="../js/suggestion.js"></script> 
		<link href="../css/dialog.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="../include/dialog.js"></script>
	</head>
	<body>
		<div class="main">
			<!--head start here-->
			<jsp:include flush="true" page="/include/head.jsp"/>
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
								<li><a href="/selfService/userWOList?preURL=suggestion">我的咨询记录</a></li>
							</ul>

							<ul class="cont">
								<!--提问表单  开始-->
								<li>
									<form name="suggestionForm" id="suggestionForm" action="/wo/save_suggestion" method="post">
									 <input type="hidden" name="userInfo.accountname"
										 				 value="<s:property value="#session.member.username"/>"/>
										<div class="formcont">
											<h1>
												在线自助服务专区－
												<span id="memoLeft">意见建议</span>
											</h1>
											<h1>
												（非常感谢您提的宝贵意见和建议！玩家的满意是我们工作最大的动力！）
											</h1>
											<span id="inputErr"><s:fielderror></s:fielderror></span>
											<br />
											
												
											</div>
									<%@ include file="qqAndPhone.jsp" %>
									<script type="text/javascript">
											$(".memoLeft").html("意见建议");
											$(".memoBottom").html("<span>*</span>（我们将会认真收集与整理您提的意见和建议。"+
											"对于我们游戏或工作中的不足，给您带来的困扰，我们深表歉意。再一次感谢您对我们游"+
											"戏和工作的支持，祝您在《风云传奇》的世界中快乐每一天。）");
									</script>

										<div class="buttonBox">
											<div class="buttonYes">
												<a href="javascript:void(0)" onclick="register(this);"> 加油哦</a>
											</div>
											<div class="buttonNo">
												<!--  a href="javascript:void(0)" onclick="javascript:document.suggestionForm.reset();return false;">重填</a> -->
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