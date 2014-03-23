<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hy.wo.util.Constants" %>
<%@ page import="com.hy.wo.po.*" %>

<%
	Staff staff = (Staff)request.getSession().getAttribute(Constants.SessionKey.STAFF);//获取用户信息
	String classification_second = request.getParameter("classification_second");
	if(staff == null){
		request.getRequestDispatcher("/ctService/htLogon.jsp").forward(request, response);
		return;
	}
%>