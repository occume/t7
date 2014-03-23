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

					<div class="zhbf">
						<h1>
							在线自助服务专区－账号问题
							<span>－找回账号</span>
						</h1>
					</div>
					<div id="wrapperlight">
						<div id="wrapperlighttop"></div>
						<div class="losepasswdtitle">
							您可以通过以下几种方式，自助找回账号：
						</div>

						<div class="losepasswdcon6">
							<ul>
								<li>
									<a href="<%=bySecUrl %>">使用超级密码找回功能</a>
								</li>
								<li>
									<a href="<%=byEmailUrl %>">使用绑定邮箱找回功能</a>
								</li>
								<li>
									<a href="javascript:alert('暂未开通');">使用手机密码找回功能</a>
								</li>
							</ul>
						</div>
						<div class="clear"></div>
						<div class="buttonNotes2">
							<a href="<%=getPwdMenuUrl %>">自助找回账号</a>
						</div>
						<br />
					</div>



					<div id="wrapperlight">
						<div id="wrapperlighttop"></div>
						<div class="losepasswdtitle">
							客服人工找回账号，您可以通过以下几个步骤完成操作：
						</div>
						<div class="losepasswdcon5">
							<ul>
								<li style="line-height: 90px;">
									点击下载传真表单
								</li>
								<li>
									<div style="height: 10px;"></div>
									填写相应信息，并附上本人身份证复印件、联系方式等。如要修改安全邮箱地址，请注名新的地址。
								</li>
								<li>
									<br />
									填写后，发送传真给我们。
									<br />
									传真号：021-33333333&nbsp;&nbsp;&nbsp;&nbsp;
								</li>
							</ul>
						</div>
						<div class="clear"></div>
						<div class="losepasswdtitle">
							说明：我们收到传真后，将会进行资料审核。审核通过后，我们会在（2个工作日内）为您处理完成。接下来您就可以通过新的安全邮箱，找回密码。
						</div>


						<div class="buttonNotes">
							<form action="/index/download.action" method="post">
							   <a href="/index/download.action">下载传真表单</a>
							</form>	
						</div>
						<br />
					</div>

					<div class="faqcont">
						<h3>
							常见问题FAQ
						</h3>
						<ul>
							<li>
								<a href="#">24数据异常，你们有没搞错，我的号全是冲的元宝练得级，没和任何人交易... </a>
							</li>
							<li>
								<a href="#">24数据异常，你们有没搞错，我的号全是冲的元宝练得级，没和任何人交易... </a>
							</li>
							<li>
								<a href="#">24数据异常，你们有没搞错，我的号全是冲的元宝练得级，没和任何人交易... </a>
							</li>
							<li>
								<a href="#">24数据异常，你们有没搞错，我的号全是冲的元宝练得级，没和任何人交易... </a>
							</li>
							<li>
								<a href="#">24数据异常，你们有没搞错，我的号全是冲的元宝练得级，没和任何人交易... </a>
							</li>
						</ul>
					</div>
				</div>
				<!--右边内容 end here-->
				<div class="clear"></div>
			</div>
		</div>
	</body>
</html>