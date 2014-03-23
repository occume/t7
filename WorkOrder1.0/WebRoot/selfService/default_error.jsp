<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
    <title>访问页面不存在</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="x-ua-compatible" content="ie=7" />
	<link href="../css/basestyle.css" type=text/css rel=stylesheet />
	<link href="../css/userstyle.css" type=text/css rel=stylesheet />
	<link href="../css/customtab.css" type=text/css rel=stylesheet />
	
	<script type="text/javascript" src="/js/jquery.js"></script>
	
	<style>
	#sec{ color:white;}
	
	</style>
	
	<script type="text/javascript" >
	    $(document).ready(function() {
		    var seconds =4;  
	    	var t=setInterval(function(){ 
				if (seconds<=0)
				{
				//alert("go to ");
					location.href="/selfService/userWOList";
					clearInterval(t);
					return ;
				}
		    	$("#sec").html(seconds);
	    		seconds=seconds-1;
	    	},1000);
	       //setTimeout("location='../selfServiceMain.jsp'",5000);
	    });  
	</script>	
  </head>
  
  <body>
    <div class=main>
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
				<li class="current">提交问题</li>
				<li><a href="/selfService/userWOList">我的咨询记录</a></li>
			</ul>
			<ul class="cont">
				<li>
				<!--使用登陆密码修改  start here-->
				
						  <div class=formcont>
						    	<div style="margin:50px 0px 0px 210px;font-size:16px;"><img src="../images/face.png" alt="success" />抱歉，您访问的页面不存在 ^-^!</div>
						    	
						    	
						  	<div class="buttonNotes"><a href="/selfService/userWOList"><span id="sec">5</span>秒后自动返回我的咨询页面</a></div>
						  </div>
				 
				<!--使用登陆密码修改  end here-->
				</li>
			</ul>
		</div>
		</div>
	</div>
	<!--右边内容 end here-->
	<div class="clear"></div>
	<!--foot start here-->
	<script src="../include/userfooter.js"></script>
	<!--foot end here-->
	</div></div></body></html>