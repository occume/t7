<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.you9.base.Globe"%>
<%@ page import="com.hy.wo.util.Constants"%>
<%
	String getPwdMenuUrl = Globe.getProperty(Constants.GlobalNodeName.GET_PWD_MENU);//获取找回帐号的默认首页链接
	String bySecUrl = Globe.getProperty(Constants.GlobalNodeName.GET_PWD_BY_SEC_URL);//获取通过超级密码找回帐号的链接
	String byEmailUrl = Globe.getProperty(Constants.GlobalNodeName.GET_PWD_BY_EMAIL_URL);//获取通过密保邮箱找回帐号的链接
	System.out.println("bySecUrl= "+ bySecUrl);
	//request.getRequestDispatcher("/WEB-INF/file/downloadFile.jsp").forward(request, response);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title>在线自助服务专区-找回账号</title>
		<link href="../css/basestyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/userstyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/customtab.css" type=text/css rel=stylesheet />
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
				<div id="userrightcontent">
					<div id="userrighttop"></div>
					<h2>
						您可以通过以下几种方式找回您的密码。
					</h2>

					<div id="wrapperlight">
						<div id="wrapperlighttop"></div>
						<div class="losepasswdtitle">
							方法一：如果您还记得超级密码，请使用超级密码找回功能
						</div>

						<div class="losepasswdcon1">
							<ul>
								<li>
									填写超级密码设置新密码
								</li>
								<li>
									修改成功
								</li>
							</ul>
						</div>
						<div class="clear"></div>
						<div class="buttoncomeon">
							<a href="http://passport.2211.com/losepwdBySec">点击进入</a>
						</div>


					</div>

					<div id="wrapperlight">
						<div id="wrapperlighttop"></div>
						<div class="losepasswdtitle">
							方法二：如果您没有设置过超级密码或已忘记，但记得密保邮箱，请使用绑定邮箱找回功能
						</div>
						<div class="losepasswdcon2">
							<ul>
								<li>
									使用密保邮箱修改超级密码
								</li>
								<li>
									使用超级密码来修改您的账号密码
								</li>
								<li>
									修改成功
								</li>
							</ul>
						</div>
						<div class="clear"></div>
						<div class="buttoncomeon">
							<a href="http://passport.2211.com/losepwdByUserMail">点击进入</a>
						</div>
					</div>

					<div id="wrapperlight">
						<div id="wrapperlighttop"></div>
						<div class="losepasswdtitle">
							方法三：如果您设置了手机绑定功能，请使用手机密码找回功能。
						</div>

						<div class="losepasswdcon2">
							<ul>
								<li>
									填写您的手机号码并提交
								</li>
								<li>
									填写验证码进行验证
								</li>
								<li>
									找回密码
								</li>
							</ul>
						</div>
						<div class="clear"></div>
						<div class="buttoncomeon">
							<a href="http://passport.2211.com/losepwdByMobile">点击进入</a>
						</div>
					</div>
					
					<div id="wrapperlight">
						<div id="wrapperlighttop"></div>
						<div class="losepasswdtitle">
							方法四：客服人工找回账号，您可以通过以下几个步骤完成操作。
						</div>

						<div class="losepasswdcon5">
							<ul>
								<li style="line-height: 90px;">
									点击下载表单
								</li>
								<li>
									<div style="height: 10px;"></div>
									填写相应信息，并附上本人身份证复印件、联系方式等。如要修改安全邮箱地址，请注明新的地址。
								</li>
								<li>
									<br />
									填写后，发送Email 给我们。
									<br />
									Email：fykf@2211.com
								</li>
							</ul>
						</div>
						<div class="clear"></div>
						<div class="losepasswdtitle">
							说明：我们收到Email后，将会进行资料审核。审核通过后，我们会在（2个工作日内）为您处理完成。接下来您就可以通过新的安全邮箱，找回密码。
						</div>

						<div class="buttoncomeon">
							<form id="fileDownForm" name="fileDownForm" action="/index/download.action" method="post" class="NeedEnterSubmit">
							   <a href="/index/download.action" onclick="javascript:fileDown(fileDownForm);" class="submit">点击下载</a>
							</form>	
						</div>

					</div>
					
					


				</div>
				<!--右边内容 end here-->
				
				
				
				<div class="clear"></div>
				<!--foot start here-->
				<script src="../include/userfooter.js"></script>
				<!--foot end here-->
			</div>
		</div>
	</body>
</html>