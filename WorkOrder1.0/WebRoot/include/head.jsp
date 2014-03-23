<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.hy.wo.util.Constants" %>
<%@ page import="com.hy.wo.po.User" %>
<%@ page import="com.hy.wo.util.MyUtil" %>

<%
	//Member member = (Member)request.getSession().getAttribute(Constants.MEMBER_INFO_KEY);//获取用户信息
	User member = (User)request.getSession().getAttribute(Constants.GET_SESSION_KEY);

%>
	<div class="headBox">

	<div class="headBox1">
		<div class="logo">
			<a href="/selfServiceMain.jsp"><img src="../images/title_service.gif" alt="" /></a>
		</div>

		<div class="headBox2">

			<div class="menu">
				<ul>
					<li>
						<a href="http://passport.2211.com">账号中心</a>
					</li>
					<li>
						<a href="http://pay.2211.com" onclick="javascript:alert('暂未开放');return false;">充值中心</a>
					</li>
					<li>
						<a href="/selfServiceMain.jsp">客服中心</a>
					</li>
					<li>
						<a href="http://fy.2211.com">官网首页</a>
					</li>
				</ul>
				<div class="clear"></div>
			</div>

			<div class="about">
				<%//if (member!=null){ %>
				<s:if test="#session.member!=null">
				<div class="welcome">欢迎您，<s:property value="#session.member.username"/></div>
				<div class="back2"><span><a href="/selfService/logout.jsp">退出</a></span></div>
				</s:if>
				<s:else>
				<%//}else{ %>
				<!--新增   10.27-->
				<DIV class=back><SPAN><A href="/selfService/login.jsp">登录</A></SPAN></DIV>
				<DIV class=back2><SPAN><A href="http://register.2211.com/regist_hy.jsp">注册</A></SPAN></DIV>
				<%//} %>
				</s:else>
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>


</div>
 