<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/community/checkLogin.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>在线自助服务专区-物品丢失</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="../css/basestyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/userstyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/customtab.css" type="text/css" rel="stylesheet" />
		<link href="../css/utils.css" type="text/css" rel="stylesheet" />

		<script type="text/javascript" src="../js/jquery.js"></script>
		<script type="text/javascript" src="../js/common.js"></script>
		<script type="text/javascript" src="../js/selfService.js"></script>
		<script language="javascript" type="text/javascript"
			src="/include/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="../include/jquery.validate.min.js"></script>
		<script type="text/javascript" src="../include/additional-methods.js"></script>
		<script type="text/javascript" src="../include/lostItems.js"></script>
		<link href="../css/dialog.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="../include/dialog.js"></script>
		<script type="text/javascript" src="../js/utils.js"></script>
		<script type="text/javascript">
		function toggleClick()
		{
			if ($("#detailinfo").css("display")=="none")
			{	
				$("#detailinfo").slideDown('slow');
				$("#zkicon").removeClass("ui-icon-triangle-1-s");
				$("#zkicon").addClass("ui-icon-triangle-1-d");
				$("#zktext").html("收起");
				
				
			}else
			{
				$("#detailinfo").slideUp('slow');
				$("#zkicon").removeClass("ui-icon-triangle-1-d");
				$("#zkicon").addClass("ui-icon-triangle-1-s");
				$("#zktext").html("展开");		
			}
			
		}
				
		</script>
	</head>
	<body>
		<div class="main">
			<!--head start here-->
			<jsp:include flush="true" page="/include/head.jsp" />
			<!--head end here-->
			<div id="contentbox2">

				<!--左边滑动菜单 start here-->
				<jsp:include flush="true" page="/include/servicemenu.jsp" />
				<!--左边滑动菜单 end here-->


				<!--右边内容 start here-->
				<div id="changpasswdright">

					<div class="box">
						<div class="mF_tab">

							<!--载入画面-->
							<ul  class="btn">
								<li class="current">
									我要提问
								</li>
								<li>
									<a href="/selfService/userWOList?preURL=loseItems">我的咨询记录</a>
								</li>
							</ul>

							<ul class="cont">
								<!--提问表单  开始-->
								<li>
								<form name="loseItemsForm" id="loseItemsForm" action="/wo/save_goodsLost" method="post" enctype="multipart/form-data" >
									
										<div class="formcont">
											<h1>
												在线自助服务专区－
												<span>物品丢失</span>
											</h1>
											<span id="inputErr"><s:fielderror></s:fielderror>
											</span>
											<br />
											<div class="formlefttitle2">
												游戏账号：
											</div>
											<div class="formrigcont">
												[<s:property value="#session.member.username" />]
												<input type="hidden" name="userInfo.accountname"
													value='<s:property value="#session.member.username"/>' />
											</div>
											<div class="formrigcontbz">
												请确认，这是您的问题账号。
											</div>
											<div class="clear"></div>
											<div class="baocuo1">

											</div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												角色名称：
											</div>
											<div class="formrigcont">
												<input type="text" id="username" name="userInfo.username"
													class="input180" maxlength="16" tabindex="1" />
												<span>*</span>
											</div>
											<div class="formrigcontbz">
												准确填写角色名称，有助于我们更快为您解决问题。
											</div>
											<div class="clear"></div>
											<div class="baocuo1" id="usernameErr">

											</div>
										</div>


										<div class="formcont">
											<div class="formlefttitle2">
												服务器：
											</div>
											<div class="formrigcont">
												<s:select id="name" cssClass="select180"
													name="userInfo.server.id" list="#session.serviceInfo"
													listKey="key" listValue="value" headerKey="0"
													headerValue="请选择服务器" tabindex="2"></s:select>
												<span>*</span>
											</div>
											<div class="clear"></div>
											<div class="baocuo1" id="serviceErr">

											</div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												丢失时间段：
											</div>
											<s:set name="hiDate"  value="@com.hy.wo.util.MyUtil@getHiddenDate()"></s:set>
											
											<input  type="hidden" id="hiddenDate" value="<s:date name="#hiDate" format="yyyy-MM-dd HH:mm:ss"/>"/>
											<div class="formrigcont">
												<input type="text" id="happendate"
													name="userInfo.happendate"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'hiddenDate\')}'})"
													class="input85" readonly="readonly" tabindex="3" />
												<input type="text" id="happendate"
													name="additional.happenEndDate"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'happendate\')}'})"
													class="input85" readonly="readonly" tabindex="3" />
												<span>*</span>
											</div>
											<div class="formrigcontbz">
												请您提供丢失时间段，有助于我们更快为您查找丢失原因。（注：我们只<br/>提供物品丢失15天内的查询服务）
											</div>
											<div class="clear"></div>
											<div class="baocuo1" id="serviceErr">

											</div>
										</div>


										<div class="formcontbox2">
											<div class="top"></div>

											<div class="formcont2  jd-gap">
											<h3>请至少填写一种情况</h3>
												<div class="formlefttitle2">
													<b>物品丢失清单：</b>
												</div>

												<div class="formrigcontbz">

													<div class="formrigcont4">
														<div class="formrigcont5">
															名称：
															<input class="input100 necessaryName" type="text" name="goodses" maxlength="15"/>
														</div>
														<div class="formrigcont5">
															属性：
															<input class="input100" type="text"  name="goodses" maxlength="15"/>
														</div>
														<div class="formrigcont5">
															数量：
															<input class="input100  requeidGoods" type="text"  name="goodses" maxlength="7" value="1" />
														</div>
														<div class="iconadd">
															<a href="#"></a>
														</div>
													</div>

												</div>

												<div class="clear"></div>
												<div class="baocuo5">
													<span class="goodsErr" style="margin-left:320px;"></span>
												</div>
												
											</div>

											<div class="formcont2">
												<div class="formlefttitle2">
													紫金丢失数量：
												</div>
												<div class="formrigcont">
													<input class="input180  purpleGold necessaryName" type="text" maxlength="7" name="additional.purpleGold"/>
												</div>
												<div class="formrigcontbz"></div>
												<div class="clear"></div>
												<div class="baocuo5">
													<span class="purpleErr"></span>
												</div>
											</div>


											<div class="formcont2">
												<div class="formlefttitle2">
													游戏币丢失数量：
												</div>
												<div class="formrigcont">
													<input class="input180 gameCoin necessaryName" type="text" maxlength="7" name="additional.gameCoin"/>
												</div>
												<div class="formrigcontbz"></div>
												<div class="clear"></div>
												<div class="baocuo5">
													<span class="gameCoinErr"></span>
												</div>
											</div>

											<div class="bottom"></div>
										</div>
										
											<%@ include file="qqAndPhone.jsp" %>
									
										
									

										<div class="formcont">
											<div class="formlefttitle2">
												上传截图一：
											</div>
											<div class="forminputfile">
												<input name="upload" type="file" class="input500"
													tabindex="13" />
											</div>
											<div class="clear"></div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												上传截图二：
											</div>
											<div class="forminputfile">
												<input name="upload" type="file" class="input500"
													tabindex="14" />
											</div>
											<div class="clear"></div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												上传截图三：
											</div>
											<div class="forminputfile">
												<input name="upload" type="file" class="input500"
													tabindex="15" />
											</div>
											<div class="clear"></div>
											<div class="formnotestext2">
												<span>友情提示：</span>
												<br />
												1.上传图片的格式,支持 jpg - jpeg - gif 格式的图片,图片大小允许2MB以内
												<br />
												2.您可以在游戏中使用PRINTSCREEN键进行截图，然后可以在您的游戏安装目录下FengYunOLGame\ScreenShots找到该截图）
											</div>
										</div>
															
										<div class="buttonBox">
											<div class="buttonYes">
												<a href="javascript:void(0)" onclick="register(this);"
													 tabindex="9">速度查询</a>
											</div>
											<div class="buttonNo">
												<!--a href="javascript:void(0)" onclick="javascript:document.loseItemsForm.reset();return false;" tabindex="10">重填</a -->
											</div>
											<div class="clear"></div>
										</div>
									</form>
								</li>
								<!--提问表单  结束-->
								<li>

								</li>

							</ul>
						</div>
					</div>


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