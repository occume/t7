<%@ page contentType="text/html;charset=UTF-8"%>
<html>
  <head>
    <title>JCSAdmin Login</title>  
    <style type="text/css">
		.text1{				
			border:1px solid #999999; width:180pxcolor:red;
		}
		.jsText1{
			color:red;font-size:12px;
		}
		.labFont{
			color:#808080;font-size:12px;
		}
	</style>
	<script language="JavaScript" type="text/JavaScript">
	<!--
			function loadFocus(){
				document.getElementById("username").focus();
			}
	  		function checkInput(fm){
	  			var username = document.getElementById("username").value;
	  			var password = document.getElementById("password").value;
	  			if(username.length <= 0){
					alert("用户名不能为空!");
					document.getElementById("username").focus();
					return false;
	  			}else if(password.length <= 0){
	  				alert("密码不能为空!");
	  				document.getElementById("password").focus();
	  				return false;
	  			}
	  			
	  		}
	  //-->
	</script>	
  </head>
  
  <body onload="loadFocus()"> 
  	<%
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String cause = "";
		if(username!=null){
			if(!username.equals("admin")){
				cause = "用户名不正确!";
			}else if(!password.equals("jcsAdmin")){
				cause ="密码不正确!";
			}else{
				response.sendRedirect("JCSAdminDefault.jsp");
			}
		}
					
	 %>
    <center>
    	<p>缓存信息查询</p>
    	<form action="login.jsp" method="post" onSubmit="return checkInput(this)">
    		<table border="1" cellpadding="5">
    			<tr>
    				<td colspan="2">
    					请输入个人信息&nbsp;&nbsp;
    					<%=cause%>
    				</td>
    			</tr>
    			<tr>
    				<td>用户名:</td>
    				<td>
    					<input name="username" id="username" type="text" size="20">
    				</td>
    			</tr>
    			<tr>
    				<td>密 码:</td>
    				<td>
    					<input name="password" id="password" type="password" size="20">
    				</td>
    			</tr>
    			<tr>
    				<td colspan="2" align="center">
    					<input type="submit" value="查 询">
    				</td>
    			</tr>
    		</table>
    	</form>
    </center>
  </body>
</html>
