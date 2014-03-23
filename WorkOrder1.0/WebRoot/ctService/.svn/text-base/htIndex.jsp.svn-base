<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title>上海火游网络有限公司-客服后台管理中心</title>
		<link href="/css/htstyle.css" type="text/css" rel="stylesheet" />
		<link href="/css/editstyle.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="/include/raphael.js"></script>
		<script type="text/javascript" src="/js/prompt.js"></script>
	</head>
	<body>
		<!--title start -->
		<%@include file="common/exit.jsp"%>
		<!--title end -->
		<div class="location">
			当前位置：首页
		</div>


		<!--查询 开始 -->
		<s:set name="roleLevel" value="#session.role.level"></s:set>

		<div class="wrappercheck">

			<div class="indexbox">

				<div class="indexleftbox">
					<div class="indexbutton1">
						<a href="/ctService/workOrderPlatform.jsp"></a>
					</div>
					<div class="indexbutton5">
						<s:if test="#roleLevel >= 0">
							<a href="/ctService/statistic.jsp"></a>
						</s:if>
						<s:else>
							<a href="#" onclick="javascript:alert('您没有该权限！');return false;"></a>
						</s:else>
					</div>
				</div>

				<div class="indexrightbox">
					<div class="indexbutton3">
						<a href="/acc/acc_modifySelf"></a>
					</div>
					<div class="indexbutton4">
						<s:if test="#roleLevel > 0">
							<a href="/ctService/accManage/pageManage.jsp"></a>
						</s:if>
						<s:else>
							<a href="#" onclick="javascript:alert('您没有该权限！');return false;"></a>
						</s:else>
					</div>
				</div>
			</div>

		</div>


		<!--查询 end -->

		<div id="holder"></div>
		<!--foot  开始-->
		<div class="foot">
			2010 All Rights Reserved 版权所有 上海火游网络科技有限公司
		</div>
	</body>
</html>
