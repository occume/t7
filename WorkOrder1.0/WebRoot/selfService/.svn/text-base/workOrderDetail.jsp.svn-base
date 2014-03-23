<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.hy.wo.util.Constants" %>
<%@ page import="com.hy.wo.po.WorkOrderOper" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title>上海火游网络有限公司-客户服务中心</title>
		<link href="../css/basestyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/userstyle.css" type="text/css" rel="stylesheet" />
		<link href="../css/customtab.css" type=text/css rel=stylesheet />
		<link href="../css/dialog.css" type=text/css rel=stylesheet />
		<script type="text/javascript" src="../js/jquery.js"></script> 
		<script type="text/javascript" src="../js/common.js"></script> 	 
		<script type="text/javascript" src="../js/workorderDetail.js"></script> 
		<script type="text/javascript" src="../include/dialog.js"></script> 
	</head>
	<body>
		<div class="main">
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
								<li class="current">
									<a href="/selfServiceMain.jsp" style="color:#ffffff;">我要提问</a>
								</li>
								<li>
									<a href="/selfService/userWOList">我的咨询记录</a>
								</li>
							</ul>
							
							<ul class="cont">
								<!--提问表单  开始-->
								<!--提问表单  结束-->
								
								<li>
									<h3>
										我提交的问题
									</h3>
						<s:if test="wo==null">
						抱歉，您没有权限访问此页面！
						</s:if><s:else>
							<s:push value="wo">
									<div class="myqstcont">
										<div class="box1">
											<strong>问题标题：</strong>
										</div>
										<div class="box2">
											<s:if test="userInfo.memo.length()>10">
												<s:property value="userInfo.memo.substring(0,10)"/>
											</s:if>
											<s:else>
												<s:property value="userInfo.memo"/>
											</s:else><span id="lookDetail">[更多信息]</span>
										</div>
										<div class="clear"></div>
									</div>
									<div class="myqstcont">
										<div class="box1">
											<strong>问题类型：</strong>
										</div>
										<div class="box2">
											<s:property value="userInfo.lvlOne.name"/>
										</div>
										<div class="clear"></div>
									</div>
									
									<div class="myqstcont">
										<div class="box1">
											<strong>工单ID：</strong>
										</div>
										<div class="box2">
											<s:property value="id"/>
										</div>
										<div class="clear"></div>
									</div>

									<div class="myqstcont">
										<div class="box1">
											<strong>角色名称：</strong>
										</div>
										<div class="box2">
											<s:property value="userInfo.username"/>
										</div>
										<div class="clear"></div>
									</div>

									<div class="myqstcont">
										<div class="box1">
											<strong>提问时间：</strong>
										</div>
										<div class="box2">
											<s:date name="createTime" format="yyyy-MM-dd hh:mm:ss"/>
										</div>
										<div class="clear"></div>
									</div>
									
									
									<div class="myqstcont">
										<div class="box1">
											<strong>问题内容：</strong>
										</div>
										<div class="box3">
											<s:property value="userInfo.memo"/><br/>
										</div>
										<div class="clear"></div>
									</div>
									<s:if test="issueAddSet!=null&&issueAddSet.size()>0">
				  						<s:iterator value="issueAddSet" status="v">
				  								<s:if test="fromUser">
										  						<div class="myqstcont">
																<div class="box1">
																	<strong style="color:red">问题补充${v.index+1}：</strong>
										
																</div>
																<div class="box3">
																	<s:date name="addDate" format="yyyy-MM-dd hh:mm:ss"/><br/>
																	<strong><s:property value="content"/></strong>
																</div>
																<div class="clear"></div>
															</div>
											  		</s:if>							
					  						</s:iterator>
				  						</s:if>	


									<div id="questionbox">
										<div id="questionboxtop"></div>


										<div id="questiontitle">
											<div class="questiontitle1">
												回复人
											</div>
											<div class="questiontitle2">
												回复内容
											</div>
											<div class="questiontitle3">
												回复时间
											</div>
											<div class="questiontitle4">
												处理状态
											</div>
											<div class="clear"></div>
										</div>
										<div id="questioncon">
											<s:if test="opers.size>0">
											<div class="question-helper" style="color:#999999;">亲爱的玩家，您好：</div>
											<s:iterator id="opers" value="opers">
											<s:if test="operType.id==1">
											<div class="questioncon1 jd-questioncon1">
												<s:property value="worker"/>
											</div>
											<div class="questioncon2">
												<s:property value="content" escapeHtml="false"/>
											</div>
											<div class="questioncon3">
												<s:date name="operTime" format="yyyy-MM-dd hh:mm:ss" />
											</div>
											
											<div class="questioncon4">
												<s:property value="states"/>
											</div>
											<div class="clear"></div>
											</s:if>
											</s:iterator>
											<div class="question-helper" style="margin-left:20px;color:#999999;">如您还有其它问题需要我们提供帮助或咨询的，欢迎随时与我们取得联系， 我们会全力帮助您解答问题。 为了不断提高我们的服务水平， 当您咨询的问题收到我们的回复后。请您花一点宝贵的时间 在下方评价区对我的服务进行评价。感谢您对《风云传奇》的支持与关注！ 祝游戏愉快！O(∩_∩)O </div>
											</s:if>
										</div>
									</div>
									<script type="text/javascript">
										if($(".jd-questioncon1").length==0){
											$(".question-helper").html("");
										}
									</script>
									<s:if test="((states=='处理中'||states=='未处理')&&issueAddSet.size()<=2)||states=='待补充资料'||states=='已反馈'">
									<div class="formcont">
									
										<div class="formlefttitle2">
											<strong>问题补充：</strong>
										</div>
										<form id="addMemoForm" name="addMemoForm" action="/wo/edit_issueAdditional?woId=<s:property value="id"/>" method="post" enctype="multipart/form-data" onsubmit="return checkAddInput(this);">
										<div class="formtextareacont">
											
						  						<textarea name="addMemo" id="addMemo" cols="" rows="20" class="textarea588" style="height:200px;" onblur="checkAddMemo(this.value,2,200);"></textarea>
						  						<div class="formnotestext2_jd">
											（亲爱的玩家，您可以有<span style="color:red;">3</span>次主动补充问题的机会，每次补充限制<span style="color:red;">300</span>个汉字。 ）
										</div>
										<div class="formnotestext2" id="addMemoErr" style="color:red;"></div>
						  				
						  					<div class="clear"></div>
										</div>
										
										<div class="formcont">
											<div class="formlefttitle2_jd">
												上传截图一：
											</div>
											<div class="forminputfile1_jd">
												<input name="upload" type="file" class="input500" tabindex="13" />
											</div>
											<div class="clear"></div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2_jd">
												上传截图二：
											</div>
											<div class="forminputfile1_jd">
												<input name="upload" type="file" class="input500" tabindex="14" />
											</div>
											<div class="clear"></div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2_jd">
												上传截图三：
											</div>
											<div class="forminputfile1_jd">
												<input name="upload" type="file" class="input500" tabindex="15" />
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
										
									</form>
									</div>

									<div class="buttonBox">
										<div class="buttonYes">
											<a href="javascript:void(0)" onclick="checkAddInput(addMemoForm)">补充好了</a>
										</div>
										<div class="clear"></div>
									</div>
									
									</s:if>
									<div class="myratewarp">
									<s:if test="states==@com.hy.wo.util.Constants$States@RESPONSED&&wo.evaluation==null">
									<form id="evalForm" name="evalForm" action="/wo/edit_evaluation" method="post" onsubmit="return checkEvalInput(this);">
										<input type="hidden" name="woId" value="<s:property value="id"/>"/>
										<div class="myrate">
											<h3>
												为了不断提高我们的服务水平，
												当您咨询的问题收到我们的回复后。请您花一点宝贵的时间
												在下方评价区对我的服务进行评价。感谢您对《风云传奇》的支持与关注！
												祝游戏愉快！
											</h3>
										</div>
										
										<div class="myrate myrate6">
											<div><s:radio name="evaluation" list="@com.hy.wo.util.constants.Evaluations@getValues()"></s:radio></div>
											<div class="clear"></div>
										</div>
										<div class="myrate">
											我们十分愿意聆听您的意见：&nbsp;
											
											<textarea id="comment"  rows="" cols="" name="comment" onblur="checkComment(this.value,2,30);"></textarea>
											还能输入<span id="leftCount">100</span>字
										</div>
										<div  class="myrate" id="commentErr" style="color:red;"></div>
										<div class="myrate">
											如果您对问题处理结果仍有疑惑，欢迎您继续提问！
										</div>
										<div class="buttonNotes">
											<a href="" onclick="checkEvalInput(evalForm);return false;">评价完毕</a>
										</div>
										<br />
										</form>
										</s:if>
										<s:if test="evaluation!=null">
											<p style="text-algin:center;color:black"><strong>您已经对本次服务做过评价。</strong></p>
											<p><strong>您对我们的服务质量表示：</strong><span style="color:red;"><s:property value="evaluation"/></span></p>
											<s:if test="comment!=null">
													<strong>您的评价留言：</strong>&nbsp;&nbsp;<span style="color:#008080;"><s:property value="comment"/></span></p>
											</s:if>
										</s:if>
									</div>
										</s:push>
				</s:else>
									
								</li>
								<li></li>
							
							</ul>
						
						</div>
					</div>
				
				</div>
<div id="wo-detail-box">
<s:if test="(additional.goodsSet!=null&&additional.goodsSet.size>0)||additional.purpleGold!=0||additional.gameCoin!=0">
	<h2>物品丢失清单</h2>
	<ul>
			<li><strong>物品名称</strong></li>
			<li><strong>物品属性</strong></li>
			<li><strong>物品数量</strong></li>
			<s:iterator value="additional.goodsSet">
				<li>
					<span class="jValue"><s:property value="name==null?' ':name"/></span>
				</li>
				<li>
					<span class="jValue"><s:property value="attr==null?' ':name"/></span>
				</li>
				<li>
					<span class="jValue"><s:property value="count"/></span>
				</li>
			</s:iterator>
			<s:if test="additional.purpleGold!=0">
				<li>紫金</li>
				<li></li>
				<li><s:property value="additional.purpleGold"/></li>
			</s:if>
			<s:if test="additional.gameCoin!=0">
				<li>游戏币</li>
				<li></li>
				<li><s:property value="additional.gameCoin"/></li>
			</s:if>
				
		</ul>
</s:if>
<s:if test="device!=null">
<h2>硬件信息</h2>	
		<ul>
			<s:if test="additional.currentVersion!=null">
				<li>
					<span class="jKey">当前游戏版本：</span>
					<span class="jValue"><s:property value="additional.currentVersion"/></span>
				</li>
			</s:if>
			<s:if test="device.cpu != null">
				<li>
					<span class="jKey">CPU类型：</span>
					<span class="jValue"><s:property value="device.cpu"/></span>
				</li>
			</s:if>
			<s:if test="device.memory != null">
				<li>
					<span class="jKey">内存类型：</span>
					<span class="jValue"><s:property value="device.memory"/></span>
				</li>
			</s:if>
			<s:if test="device.displaycard != null">
				<li>
					<span class="jKey">显卡类型：</span>
					<span class="jValue"><s:property value="device.displaycard"/></span>
				</li>
			</s:if>
			<s:if test="device.os != null">
				<li>
					<span class="jKey">操作系统：</span>
					<span class="jValue"><s:property value="device.os"/></span>
				</li>
			</s:if>
			<s:if test="device.netinfo != null">
				<li>
					<span class="jKey">网络供应商：</span>
					<span class="jValue"><s:property value="device.netinfo"/></span>
				</li>
			</s:if>
		</ul>
</s:if>
<s:if test="recharge!=null">
	<h2>充值信息</h2>
		
		<s:if test="recharge.rechargeType.name != null">
				<div>
					<span class="jKey">充值类型：</span>
					<span class="jValue"><s:property value="recharge.rechargeType.name"/></span>
				</div>
			</s:if>
			<s:if test="recharge.card != null">
				<div>
					<span class="jKey">充值卡号：</span>
					<span class="jValue"><s:property value="recharge.card"/></span>
				</div>
			</s:if>
		
</s:if>

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