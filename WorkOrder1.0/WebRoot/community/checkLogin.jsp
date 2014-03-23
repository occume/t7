<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hy.wo.util.Constants" %>
<%@ page import="com.hy.wo.po.User" %>
<%@ page import="com.hy.wo.util.MyUtil" %>
<%
	User member = (User)request.getSession().getAttribute("member");//获取用户信息
	//String classification_second = request.getParameter("classification_second");
	if(member == null){//session未取到用户信息
		if(MyUtil.useSSO()){//使用SSO登录的
			//member = LoginUtil.getUserInfoFromSession(request,response);//检查COOKIE重新获取用户信息
		}
	}
	if(member == null){
			response.sendRedirect("/selfService/login.jsp");//+request.getQueryString());
			return;
		
	}
%>

