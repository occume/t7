<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core"%>
<%@ page import="com.hy.wo.cache.*"%>
<%
JCSManager jcs=JCSManager.getInstance();
String key=request.getParameter("deleteId");
if(key!=null){
	if(key.equals("all")){
		jcs.removeAll();
	}else{
		jcs.remove(key);
	}
}

int currentPage=1;
if(request.getParameter("currentPage")!=null){
	currentPage=Integer.valueOf(request.getParameter("currentPage"));
}

int pageSize=5;	
int totalCount=jcs.size();
int totalPage=(totalCount+pageSize-1)/pageSize;

int from=(currentPage-1)*pageSize;
int to=currentPage*pageSize;
if(to>totalCount){
	to=totalCount;
}

Map list=null;
		try {
			list=jcs.getSubMap(from,to);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("CacheElementInfo", list);
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("totalPage",totalPage);
		request.setAttribute("totalCount",totalCount);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 
    <title>My JSP 'cacheQuery.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    	<div class="cache-info-box">
    	<h3>当前缓存共有[${totalCount}]条数据</h3> 
    	<h4><a href="/cacheQuery.jsp?deleteId=all">一键解锁</a></h4>
    			<table border="1" cellpadding="0" cellspacing="0">
    				<tr>
    					<td>工单号</td>
    					<td>操作人</td>
    					<td>操作</td>
    					<td></td>
    				</tr>
    				<c:forEach items="${CacheElementInfo}" var="obj" varStatus="index">
    					<tr>
    					<td>${obj.key}</td>
    					<td>${obj.value}</td>
    					<td><a href="/cacheQuery.jsp?deleteId=${obj.key}&currentPage=${currentPage}">删除</a></td>
    					<td></td>
    				</tr>
    				</c:forEach>
    				<tr>
    					<td>
    							<c:if test="${currentPage>1}">
    								<a href="/cacheQuery.jsp?currentPage=1">首页</a>
    							</c:if> 
    							<c:if test="${currentPage<=1}">
    								第一页
    							</c:if>
    					</td>
    					<td>
    							<c:if test="${currentPage>1}">
    								<a href="/cacheQuery.jsp?currentPage=${currentPage-1}">上一页</a>
    							</c:if> 
    							<c:if test="${currentPage<=1}">
    								上一页
    							</c:if>
    					</td>
    					<td>
    							<c:if test="${currentPage<totalPage}">
    								<a href="/cacheQuery.jsp?currentPage=${currentPage+1}">下一页</a>
    							</c:if> 
    							<c:if test="${currentPage>=totalPage}">
    								下一页
    							</c:if>
    					</td>
    					<td>
    							<c:if test="${currentPage<totalPage}">
    								<a href="/cacheQuery.jsp?currentPage=${totalPage}">尾页</a>
    							</c:if> 
    							<c:if test="${currentPage>=totalPage}">
    								尾页
    							</c:if>
    					</td>
    				</tr>
    			</table>
    			<c:url value=""></c:url>	
			</div>
  </body>
</html>
