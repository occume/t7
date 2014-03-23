<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String batchDataCacheKey = (String)request.getParameter("batchDataCacheKey");
	String accountname = request.getParameter("accountname");
	String lvlTw = request.getParameter("lvlTw");
	String createTime = request.getParameter("createTime");
	String server = request.getParameter("server");
	String memo = request.getParameter("memo");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
  <body>
    <table width="70%" border="1" align="center" cellpadding="5" cellspacing="3">
  		<tr>
	   		<td width="20%">主键</td>
	   		<td width="20%">玩家帐号</td>
	   		<td width="20%">问题二级类型</td>
	   		<td width="20%">创建时间</td>
	   		<td width="20%">服务器</td>
	   		<td width="20%">问题描述</td>	   		
	   		<td>操作</td>
	   	</tr>
	   	<tr>
	   		<td width="20%"><%=batchDataCacheKey%></td>
	   		<td width="20%"><%=accountname%></td>
	   		<td width="20%"><%=lvlTw%></td>
	   		<td width="20%"><%=createTime%></td>
	   		<td width="20%"><%=server%></td>
	   		<td width="20%"><%=memo%></td>	   		
	   		<td>
	   			<a href="<%=request.getContextPath()%>/jcs/idCardInfoCacheDetail.jsp?action=remove&operate=single&cacheName=userCache&key=<%=batchDataCacheKey%>">删除
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
