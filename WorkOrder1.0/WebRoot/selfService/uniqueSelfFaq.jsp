<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title>在线自助服务专区－ 常见问题FAQ</title>
		<link href="../css/basestyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/userstyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/customtab.css" type="text/css" rel="stylesheet" />
	
	</head>

	<body>
		<div class="main">
			<!--head start here-->
			<jsp:include flush="true" page="/include/head.jsp" />
			<!--head end here-->

			<div id="contentbox2">

				<!--左边滑动菜单 start here-->
				<jsp:include flush="true" page="/include/servicemenu.jsp"/>
				<!--左边滑动菜单 end here-->


<!--右边内容 start here-->
<div id="changpasswdright">
<!--gm问题  开始-->
<div class="faqcont">
		<h3>常见问题FAQ</h3>
		<s:push value="faq">
			<ul>
				<li>
					<span class="k"><b>类型:</b></span>
					<span class="v"><s:property value="type"/></span>
				</li>
				<li>
					<span class="k"><b>游戏:</b></span>
					<span class="v"><s:property value="gameName"/></span>
				</li>
				<li>
					<span class="k"><b>时间:</b></span>
					<span class="v"><s:date name="createTime" format="yyyy-MM-dd hh:mm:ss"/></span>
				</li>
				<li>
						<span class="k"><b>正文:</b></span><br />
						<span class="descrip">&nbsp;&nbsp;Q:<s:property value="title"/></span><br />
						<span class="descrip" id="down50">&nbsp;&nbsp;A:<s:property value="descrip"/></span>
				</li>
			</ul>	
		</s:push>
</div>
<script >
		document.getElementById("down50").style.height="600px";
</script>
<!--gm问题  结束-->
</div>

<!--右边内容 end here-->
<div class="clear"></div>

				<!--foot start here-->
				<script src="../include/userfooter.js" type=""></script>
				<!--foot end here-->
			</div>
		</div>
	</body>
</html>