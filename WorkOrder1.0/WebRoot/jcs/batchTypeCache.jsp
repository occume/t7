<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String batchTypeCacheKey = (String)request.getParameter("batchTypeCacheKey");
	String batchType = request.getParameter("batchType");
	String batchTypeName = request.getParameter("batchTypeName");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
  <body>
    <table width="70%" border="1" align="center" cellpadding="5" cellspacing="3">
  		<tr>
	   		<td width="20%">主键</td>
	   		<td width="20%">批量操作类型</td>
	   		<td width="20%">批量操作类型名</td>
	   		<td>操作</td>
	   	</tr>
	   	<tr>
	   		<td width="20%"><%=batchTypeCacheKey%></td>
	   		<td width="20%"><%=batchType%></td>
	   		<td width="20%"><%=batchTypeName%></td>
	   		<td>
	   			<a href="<%=request.getContextPath()%>/jcs/idCardInfoCacheDetail.jsp?action=remove&operate=single&cacheName=userCache&key=<%=batchTypeCacheKey%>">删除
	   		</td>
	   	</tr>
	   	<tr>
	   		<td colspan="5" align="center">
	   			<input type="button" onclick="window.history.go(-1)" value="返回"/><br>
	   			<a href="JCSAdminDefault.jsp">返回主页</a>
	   		</td>
	   	</tr>
	  </table>
  </body>
</html>
