<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/community/checkLogin.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title>上海火游科技网络有限公司-我要咨询</title>
		<link href="../css/basestyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/userstyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/customtab.css" type=text/css rel=stylesheet />
		<script type="text/javascript" src="../js/jquery.js"></script> 
		<script language="javascript" type="text/javascript" src="/include/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="../js/common.js?ver=0.926"></script> 	 
		<script type="text/javascript" src="../include/jquery.validate.min.js"></script>
		<script type="text/javascript" src="../include/additional-methods.js"></script>	
		<script type="text/javascript" src="../js/advisory.js"></script> 
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
								<li><a href="/selfService/userWOList?preURL=advisory">我的咨询记录</a></li>
							</ul>
							<ul class="cont">
								<!--提问表单  开始-->
								<li>
									<form name="advisoryForm" id="advisoryForm" action="/wo/save_advisory" method="post" enctype="multipart/form-data">
										 <input type="hidden" name="userInfo.accountname"
										 				 value="<s:property value="#session.member.username"/>"/>
										<div class="formcont">
											<h1>
												在线自助服务专区－
												<span>咨询问题</span>
											</h1>
											<h1>
												（此类仅限问题咨询，不做问题处理,您如有问题需要解决，请至其它问题分类处提交，敬请配合。谢谢！）
											</h1>
											<span id="inputErr"><s:fielderror></s:fielderror></span>
											<br />
											<div class="formlefttitle2">
												咨询类型：
											</div>
											<div class="formrigcont">	
												<s:select id="name" cssClass="select180" name="userInfo.lvlTwo.id" list="#session.advisoryInfo" listKey="key" listValue="value" tabindex="1"></s:select>
											</div>
											<div class="clear"></div>
											<div class="baocuo1"></div>
										</div>
										
										<div class="formcont">
											<div class="formlefttitle2">
												联系人：
											</div>
											<div class="formrigcont">
												<input type="text" id="" name="userInfo.username"  maxlength="20" class="input180" tabindex="2" />
											</div>
											<div class="formrigcontbz">
												填写联系人，您将会享受到更快捷方便的服务。
											</div>
											<div class="clear"></div>
											<div class="baocuo1" id="qqErr"></div>
										</div>
										
										<%@ include file="qqAndPhone.jsp" %>
										<script type="text/javascript">
											$(".memoLeft").html("咨询内容");
											$(".memoBottom").html("<span>*</span>（请填写您的咨询内容，我们很乐意为您解答！）");
										</script>

										
										<div class="buttonBox">
											<div class="buttonYes">
												<a href="javascript:void(0)" onclick="register(this);">我要咨询</a>
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