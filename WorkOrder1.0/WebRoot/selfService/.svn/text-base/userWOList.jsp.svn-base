<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.hy.wo.util.MyUtil"%>
<%@ include file="/community/checkLogin.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title>上海火游网络有限公司-客户服务中心</title>
		<link href="../css/basestyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/userstyle.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="../include/jquery.min.js"></script>
		<script type="text/javascript" src="../js/common.js"></script>
		<script type="text/javascript" src="../js/selfService.js"></script>
		<link href="../css/customtab.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="../include/userWOList.js"></script>
	</head>

	<body>
<script type="text/javascript">
		function switchTb(i){
		  	$("#btn_inner>li").removeClass("selected");
			$("#btn_inner>li:eq("+i+")").addClass("selected");
		  	$("#btn_inner_content>li").hide();
			$("#btn_inner_content>li:eq("+i+")").show();
		  }
		  
</script>

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

					<div class="box">
						<div class="mF_tab">
							<!--载入画面-->
							<ul class="btn">
								<li>
									<s:if test="#parameters.preURL[0]!=null">
										<a href="/selfService/${param.preURL}.jsp">我要提问</a>
									</s:if>
									<s:else>
										<a href="/selfServiceMain.jsp">我要提问</a>
									</s:else>
								</li>
								<li class="current">
									<a href="/selfService/userWOList" style="color:#fff;">我的咨询记录</a>
								</li>
								
							</ul>
							<ul class="cont">
								<!--提问表单  开始-->
								<li>
								<!--
									<div id="questionbox">
										<div id="questionboxtop"></div>

										<h3>
											我的咨询记录(<s:property value="userwolist.totalCount" />)
										</h3>
										<div class="totle">
											总咨询数：
											<s:property value="userwolist.totalCount" />
										</div>
										<div class="totle1">
											未处理：
											<s:property value="userwolist.undealCount" />
										</div>
										<div class="totle2">
											已回复：
											<s:property value="userwolist.replyCount" />
										</div>
										<div class="totle3">
											处理中：
											<s:property value="userwolist.delingCount" />
										</div>
										<div class="totle4">
											处理完毕：
											<s:property value="userwolist.dealCount" />
										</div>
										<div class="totle5">
											待补充资料：
											<s:property value="userwolist.lackinfoCount" />
										</div>
										<div class="clear"></div>

									</div>

								-->

									<div class="wtlistbox">

										<h3>
											我最近的提问(<s:property value="userwolist.totalCount" />)
										</h3>

										<ul class="btn_inner" id="btn_inner">
											<li onclick="switchTb(0);" class="selected"
												style="padding: 0px; line-height: 32px;">
												已接收(<s:property value="userwolist.undealCount" />)
											</li>
											<li onclick="switchTb(1);"
												style="padding: 0px; line-height: 32px;">
												受理中(<s:property value="userwolist.delingCount" />)
											</li>
											<li onclick="switchTb(2);"
												style="padding: 0px; line-height: 32px;">
												受理完毕(<s:property value="userwolist.replyCount" />)
											</li>
											<li onclick="switchTb(3);"
												style="padding: 0px; line-height: 32px;">
												请补充信息(<s:property value="userwolist.lackinfoCount" />)
											</li>
											<li onclick="switchTb(4);"
												style="padding: 0px; line-height: 32px;">
												所有记录(<s:property value="userwolist.totalCount"/>)
											</li>
										</ul>
										<ul class="btn_inner_content" id="btn_inner_content">
											<li style="padding: 0px" id="undeal">
												<div class="wtlisttitle">
													<div class="wtlisttitle1">
														问题简述
													</div>
													<div class="wtlisttitle2">
														提问时间
													</div>
													<div class="wtlisttitle3">
														状态
													</div>
													<div class="clear"></div>
												</div>
												<div class="list">
												<s:iterator value="userwolist.undealList.resultList" var="undealL">
													
													<div class="wtlistcont">
														<div class="wtlistcont1">
															<a
																href='/wo/get_workOrderDetail?woId=<s:property value="@com.hy.wo.util.MyUtil@paramEncode(id)"/>'
																title='<s:property value="userInfo.memo"/>
															'>
															(工单号:<s:property
																	value="id" />)<span style="color:red;">(类型：<s:property value="userInfo.lvlOne.name"/>)</span>
																<s:property
																	value="@com.hy.wo.util.MyUtil@SubString(userInfo.memo,16,'...','无问题描述')" />
															
															
														</a>
														</div>
														<div class="wtlistcont2">
															<s:date name="createTime" format="yyyy-MM-dd hh:mm:ss" />
														</div>
														<div class="wtlistcontwcl"></div>
														<div class="clear"></div>
													</div>
												</s:iterator>
												</div>
												<!--翻页 开始-->

												<div id="turnpage_undeal" class="turnpage">
													<div class="buttonselect">
														第
														<select name="s" class="select40" onchange="changepage('undeal');">
														<c:forEach begin="1" end="${userwolist.undealList.totalPage}" var="p" >
															<option  value="${p}">${p}</option>
														</c:forEach>
														</select>
														页 总共<s:property value="userwolist.undealList.totalPage"></s:property> 页 每页<s:property value="userwolist.undealList.pageSize"></s:property>条
													</div>
													<div class="buttonend">
														<a href="javascript:void(0)"></a>
													</div>
													<div class="buttonnext">
														<a href="javascript:void(0)"></a>
													</div>
													<div class="buttonfront">
														<a href="javascript:void(0)"></a>
													</div>
													<div class="buttonfirst">
														<a href="javascript:void(0)"></a>
													</div>
													<input type="hidden" value="${userwolist.undealList.totalPage}" class="totalpage" />
													<input type="hidden" value="1" class="currentpage" />
												</div>
												
												<!--翻页 结束-->
											</li>
											
											<li style="display: none; padding: 0px;" id="deling">
												<div class="wtlisttitle">
													<div class="wtlisttitle1">
														问题简述
													</div>
													<div class="wtlisttitle2">
														提问时间
													</div>
													<div class="wtlisttitle3">
														状态
													</div>
													<div class="clear"></div>
												</div>
												<div class="list">
												<s:iterator value="userwolist.delingList.resultList">
													<div class="wtlistcont">
														<div class="wtlistcont1">
															<a
																href='/wo/get_workOrderDetail?woId=<s:property value="@com.hy.wo.util.MyUtil@paramEncode(id)"/>'
																title='<s:property value="userInfo.memo"/>'>
																(工单号:<s:property
																	value="id" />)<span style="color:red;">(类型：<s:property value="userInfo.lvlOne.name"/>)</span>
																 <s:property
																	value="@com.hy.wo.util.MyUtil@SubString(userInfo.memo,16,'...','无问题描述')" />
															
															
														</a>
														</div>
														<div class="wtlistcont2">
															<s:date name="createTime" format="yyyy-MM-dd hh:mm:ss" />
														</div>
														<div class="wtlistcontclz"></div>
														<div class="clear"></div>
													</div>
												</s:iterator>
												</div>
												<!--翻页 开始-->

												<div id="turnpage_deling" class="turnpage">
													<div class="buttonselect">
														第
														<select name="s" class="select40" onchange="changepage('deling');">
														<c:forEach begin="1" end="${userwolist.delingList.totalPage}" var="p" >
															<option  value="${p}">${p}</option>
														</c:forEach>
														</select>
														页 总共<s:property value="userwolist.delingList.totalPage"></s:property> 页 每页<s:property value="userwolist.delingList.pageSize"></s:property>条
													</div>
													<div class="buttonend">
														<a href="javascript:void(0)"></a>
													</div>
													<div class="buttonnext">
														<a href="javascript:void(0)"></a>
													</div>
													<div class="buttonfront">
														<a href="javascript:void(0)"></a>
													</div>
													<div class="buttonfirst">
														<a href="javascript:void(0)"></a>
													</div>
													<input type="hidden" value="${userwolist.delingList.totalPage}" class="totalpage" />
												<input type="hidden" value="1" class="currentpage" />
												</div>
												
												<!--翻页 结束-->
											</li>
											
											<li style="display: none; padding: 0px;" id="reply">
												<div class="wtlisttitle">
													<div class="wtlisttitle1">
														问题简述
													</div>
													<div class="wtlisttitle2">
														提问时间
													</div>
													<div class="wtlisttitle3">
														状态
													</div>
													<div class="clear"></div>
												</div>
												<div class="list">
												<s:iterator value="userwolist.replyList.resultList">
													<div class="wtlistcont">
														<div class="wtlistcont1">
															<a
																href='/wo/get_workOrderDetail?woId=<s:property value="@com.hy.wo.util.MyUtil@paramEncode(id)"/>'
																title='<s:property value="userInfo.memo"/>'> 
																(工单号:<s:property
																	value="id" />)<span style="color:red;">(类型：<s:property value="userInfo.lvlOne.name"/>)</span>
																<s:property
																	value="@com.hy.wo.util.MyUtil@SubString(userInfo.memo,16,'...','无问题描述')" />
															
														</a>
														</div>
														<div class="wtlistcont2">
															<s:date name="createTime" format="yyyy-MM-dd hh:mm:ss" />
														</div>
														
														<s:if test="!isreaded">
															<div class="wtlistcontyhf" title="未读"></div>
														</s:if>
														<s:else>
															<div class="wtlistcontyhfReaded" title="已读"></div>
														</s:else>
														<div class="clear"></div>
													</div>
												</s:iterator>
												</div>
												<!--翻页 开始-->

											<div id="turnpage_reply" class="turnpage">
													<div class="buttonselect">
														第
														<select name="s" class="select40" onchange="changepage('reply');">
														<c:forEach begin="1" end="${userwolist.replyList.totalPage}" var="p" >
															<option  value="${p}">${p}</option>
														</c:forEach>
														</select>
														页 总共<s:property value="userwolist.replyList.totalPage"></s:property> 页 每页<s:property value="userwolist.replyList.pageSize"></s:property>条
													</div>
													<div class="buttonend">
														<a href="javascript:void(0)"></a>
													</div>
													<div class="buttonnext">
														<a href="javascript:void(0)"></a>
													</div>
													<div class="buttonfront">
														<a href="javascript:void(0)"></a>
													</div>
													<div class="buttonfirst">
														<a href="javascript:void(0)"></a>
													</div>
													<input type="hidden" value="${userwolist.replyList.totalPage}" class="totalpage" />
												<input type="hidden" value="1" class="currentpage" />
												</div>
												

												<!--翻页 结束-->
											</li>
											
											
											<li style="display: none; padding: 0px;" id="lackinfo">
												<div class="wtlisttitle">
													<div class="wtlisttitle1">
														问题简述
													</div>
													<div class="wtlisttitle2">
														提问时间
													</div>
													<div class="wtlisttitle3">
														状态
													</div>
													<div class="clear"></div>
												</div>
												<div class="list">
												<s:iterator value="userwolist.lackinfoList.resultList">
													<div class="wtlistcont">
														<div class="wtlistcont1">
															<a
																href='/wo/get_workOrderDetail?woId=<s:property value="@com.hy.wo.util.MyUtil@paramEncode(id)"/>'
																title='<s:property value="userInfo.memo"/>'> 
																(工单号:<s:property
																	value="id" />)<span style="color:red;">(类型：<s:property value="userInfo.lvlOne.name"/>)</span>
																<s:property
																	value="@com.hy.wo.util.MyUtil@SubString(userInfo.memo,16,'...','无问题描述')" />
															
														</a>
														</div>
														<div class="wtlistcont2">
															<s:date name="createTime" format="yyyy-MM-dd hh:mm:ss" />
														</div>
														<div class="wtlistcontbczl"></div>
														<div class="clear"></div>
													</div>
												</s:iterator>
												</div>
												<!--翻页 开始-->

												<div id="turnpage_lackinfo" class="turnpage">
													<div class="buttonselect">
														第
														<select name="s" class="select40" onchange="changepage('lackinfo');">
														<c:forEach begin="1" end="${userwolist.replyList.totalPage}" var="p" >
															<option  value="${p}">${p}</option>
														</c:forEach>
														</select>
														页 总共<s:property value="userwolist.lackinfoList.totalPage"></s:property> 页 每页<s:property value="userwolist.lackinfoList.pageSize"></s:property>条
													</div>
													<div class="buttonend">
														<a href="javascript:void(0)"></a>
													</div>
													<div class="buttonnext">
														<a href="javascript:void(0)"></a>
													</div>
													<div class="buttonfront">
														<a href="javascript:void(0)"></a>
													</div>
													<div class="buttonfirst">
														<a href="javascript:void(0)"></a>
													</div>
													<input type="hidden" value="${userwolist.lackinfoList.totalPage}" class="totalpage" />
												<input type="hidden" value="1" class="currentpage" />
												</div>
												
												<!--翻页 结束-->
											</li>
											<li style="display: none; padding: 0px;" id="deal">
												<div class="wtlisttitle">
													<div class="wtlisttitle1">
														问题简述
													</div>
													<div class="wtlisttitle2">
														提问时间
													</div>
													<div class="wtlisttitle3">
														状态
													</div>
													<div class="clear"></div>
												</div>
												<div class="list">
												<s:iterator value="userwolist.dealList.resultList">
													<div class="wtlistcont">
														<div class="wtlistcont1">
															<a
																href='/wo/get_workOrderDetail?woId=<s:property value="@com.hy.wo.util.MyUtil@paramEncode(id)"/>'
																title='<s:property value="userInfo.memo"/>'> 
																(工单号:<s:property
																	value="id" />)<span style="color:red;">(类型：<s:property value="userInfo.lvlOne.name"/>)</span>
																<s:property
																	value="@com.hy.wo.util.MyUtil@SubString(userInfo.memo,16,'...','无问题描述')" />
															</a>
															
														</div>
														<div class="wtlistcont2">
															<s:date name="createTime" format="yyyy-MM-dd hh:mm:ss" />
														</div>
														<div class="wtlistcontwb"></div>
														<div class="clear"></div>
													</div>
												</s:iterator>
												</div>
												<!--翻页 开始-->

												<div id="turnpage_deal" class="turnpage">
													<div class="buttonselect">
														第
														<select name="s" class="select40" onchange="changepage('deal');">
														<c:forEach begin="1" end="${userwolist.dealList.totalPage}" var="p" >
															<option  value="${p}">${p}</option>
														</c:forEach>
														</select>
														页 总共<s:property value="userwolist.dealList.totalPage"></s:property> 页 每页<s:property value="userwolist.dealList.pageSize"></s:property>条
													</div>
													<div class="buttonend">
														<a href="javascript:void(0)"></a>
													</div>
													<div class="buttonnext">
														<a href="javascript:void(0)"></a>
													</div>
													<div class="buttonfront">
														<a href="javascript:void(0)"></a>
													</div>
													<div class="buttonfirst">
														<a href="javascript:void(0)"></a>
													</div>
													<input type="hidden" value="${userwolist.dealList.totalPage}" class="totalpage" />
												<input type="hidden" value="1" class="currentpage" />
												</div>
												
												<!--翻页 结束-->
											</li>
										</ul>
<script type="text/javascript">
	switchTb(<s:property value="currentTag"/>);
</script>

									</div>

								</li>
								<!--提问表单  结束-->


							</ul>
						</div>
					</div>


				</div>

				<!--右边内容 end here-->
				<div class="clear"></div>

				<!--foot start here-->
				<script src="../include/userfooter.js" ></script>
				<!--foot end here-->
			</div>
		</div>
	</body>
</html>