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
		<h3>常见问题FAQ<span style="font-size:13px;color:#444;">&nbsp;—&nbsp;<s:property value="@com.hy.wo.util.constants.Issue@getInameByClassName(itype)"/></span>  </h3>
	<div id="com-faq-list">
		<ul>
				<s:if test="pages.resultList.size>0">
					<s:iterator value="pages.resultList">
						
							<li class="wu">
									<a href="/faq/unique?id=<s:property value="id"/>&orient=self&itype=${itype}">
											<s:if test="title.length()>20">
													<s:property value="title.substring(0,16)"/>...
											</s:if>
											<s:else>
													<s:property value="title"/>
											</s:else>
									</a>
							</li>
							
					</s:iterator>
				</s:if>
				<s:else>
					<ul>
						<li>暂无此类问题</li>
					</ul>
				</s:else>
		</ul>
		<s:url var="first">
				<s:param name="currentPage" value="1"></s:param>
				<s:param name="itype" value="itype"></s:param>
			</s:url>
			<s:url var="previous">
				<s:param name="currentPage" value="pages.currentPage-1"></s:param>
				<s:param name="itype" value="itype"></s:param>
			</s:url>
			<s:url var="last">
				<s:param name="currentPage" value="pages.lastPage"></s:param>
				<s:param name="itype" value="itype"></s:param>
			</s:url>
			<s:url var="next">
				<s:param name="currentPage" value="pages.currentPage+1"></s:param>
				<s:param name="itype" value="itype"></s:param>
			</s:url>
			
</div>
<%@ include file="/ctService/common/page.jsp" %>
		
</div>

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