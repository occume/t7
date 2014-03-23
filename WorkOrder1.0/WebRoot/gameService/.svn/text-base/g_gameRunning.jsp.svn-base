<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title>上海火游网络有限公司-账户管理中心</title>
		<link href="../css/gameService.css" type="text/css" rel="stylesheet" />
		<link href="../css/dialog.css" type="text/css" rel="stylesheet" />
		<script src="../include/jquery.min.js" type=""></script>
		<script type="text/javascript" src="../include/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="../js/common.js?ver=0.926"></script>
		<script type="text/javascript" src="../js/selfService.js"></script>
		<script type="text/javascript" src="../include/jquery.validate.min.js"></script>
		<script type="text/javascript" src="../include/additional-methods.js"></script>
		<script type="text/javascript" src="../include/gameRunning.js"></script>
		<script type="text/javascript" src="../include/dialog.js"></script>
		<script type="text/javascript" src="../js/commonInit.js"></script>
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
<form name="roleAbnormalForm" id="roleAbnormalForm" action="/wo/save_gameRun" method="post" enctype="multipart/form-data" >
		<DIV class=main>

  <div class="header"><img src="../images/texttitle1.png" /></div>
  <DIV class=formcont>
  <DIV class=formlefttitle2>发生时间： </DIV>
  <DIV class=formrigcont>
									<input type="text" id="happendate" name="userInfo.happendate" readonly="readonly"
											 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})"  
											 class="input180" tabindex="2"/>
										<span>*</span> </DIV>
  <DIV class="baocuo1">错误提示</DIV>
  <DIV class=clear></DIV>
  <DIV class=textnotes>准确选择问题发生时间，有助于我们更快为您解决问题。 </DIV>
  </DIV>


  <DIV class=formcont>
  <DIV class=formlefttitle2>问题小类： </DIV>
  <DIV class=formrigcont id="lvltwo">
							<select class="select180" name="userInfo.lvlTwo.id" tabindex="4">
													<option value="0">请选择问题小类</option>
												</select>
							<span>*</span> </DIV>
  <DIV class=errornotes> </DIV>
  <DIV class=clear></DIV>
  <DIV class=textnotes> </DIV>
  </DIV>


<div class="formcontbox2">
<div class=toptext>请点击展开，填写您电脑的硬件网络信息，有助于我们为您更好地解决问题。（选填）</div>

<div class="top" style="height:18px; cursor:pointer;" id="zkbtn" onclick="toggleClick();">
<div class="ui-icon-triangle-1-s" id="zkicon"></div>
<div class="ui-icon-triangle-1-s-text" id="zktext">展开</div>
<DIV class=clear></DIV>
</div>


<div id="detailinfo" style="display: none;">


												<div class="formcont2">
													<div class="formlefttitle2">
														CPU型号：
													</div>
													<div class="formrigcont">
														 <input type="text" class="input180" name="device.cpu" tabindex="5" />
													</div>
													<div class="formrigcont3">
														 <input type="text" class="input180" name="device.displaycard" tabindex="6" />
													</div>
													<div class="formlefttitle3">
														显卡型号：
													</div>
													<div class="clear"></div>
												</div>

												<div class="baocuo2">
													<div class="baocuo3">
													</div>
													<div class="baocuo4">
													</div>
													<div class="clear"></div>
												</div>

												<div class="formcont2">
													<div class="formlefttitle2">
														内存大小：
													</div>
													<div class="formrigcont">
														<input class="input180" type="text" name="device.memory" tabindex="7" />
													</div>
													<div class="formrigcont3">
														<input class="input180" type="text" name="device.os" tabindex="8" />
													</div>
													<div class="formlefttitle3">
														操作系统：
													</div>
													<div class="clear"></div>
												</div>

												<div class="baocuo2">
													<div class="baocuo3">
													</div>
													<div class="baocuo4">
													</div>
													<div class="clear"></div>
												</div>

												<div class="formcont2">
													<div class="formlefttitle2">
														网络供应商：
													</div>
													<div class="formrigcont">
														<input class="input180" type="text" name="device.netinfo" tabindex="9" />
													</div>
													<div class="clear"></div>
												</div>

												<div class="baocuo2">
													<div class="baocuo3">
													</div>
													<div class="clear"></div>
												</div>


											</div>

<div class=bottom></div>
</div>


  <DIV class=formcont>
  <DIV class=formlefttitle2>联系QQ： </DIV>
  <DIV class=formrigcont>
			<input type="text" id="qq" name="userInfo.qq" value="<s:property value="#session.member.userInfo.qq"/>" 
			maxlength="20"  class="input180" tabindex="10" />
</DIV>
  <DIV class="baocuo1"></DIV>
  <DIV class=clear></DIV>
  <DIV class=textnotes>填写您的QQ号，将有机会享受到更快捷方便的服务。 </DIV>
  </DIV>


  <DIV class=formcont>
  <DIV class=formlefttitle2>联系电话： </DIV>
  <DIV class=formrigcont>
		<input type="text" id="tel" name="userInfo.tel" value="<s:property value="#session.member.userInfo.phone"/>" 
		maxlength="20" class="input180" tabindex="11" />
</DIV>
  <DIV class="baocuo1"></DIV>
  <DIV class=clear></DIV>
  <DIV class=textnotes>填写您真实电话，有助于及时得到反馈。我们会严格保密个人资料。 </DIV>
  </DIV>
	

  <DIV class=formcont>
  <DIV class=formlefttitle2>问题描述： </DIV>
  <DIV class=formtextareacont>
		<textarea rows="20" cols="" id="memo" name="userInfo.memo" class="textarea" style="height:200px;" tabindex="12"></textarea>
</textarea></DIV>
  <DIV class=clear></DIV>
  <DIV class=formnotestext2><span>*</span>（您如果还有其它信息需要补充，可以在问题描述中说明。） </DIV>
  <DIV class=baocuo1> 报错</DIV>
  </DIV>

 										<div class="formcont">
											<div class="formlefttitle2">
												上传截图一：
											</div>
											<div class="forminputfile">
												<input name="upload" type="file" class="input500" tabindex="13" />
											</div>
											<div class="clear"></div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												上传截图二：
											</div>
											<div class="forminputfile">
												<input name="upload" type="file" class="input500" tabindex="14" />
											</div>
											<div class="clear"></div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												上传截图三：
											</div>
											<div class="forminputfile">
												<input name="upload" type="file" class="input500" tabindex="15" />
											</div>
											<div class="clear"></div>
										</div>

 						<div class="buttonBox">
									<div class="buttonYes">
											<a onclick="register(this);" tabindex="16" id="submit_btn">提交</a>
									</div>
									<div class="buttonNo">
												<a onclick="javascript:roleAbnormalForm.reset();return false;" tabindex="17">重填</a>
									</div>
									<div class="clear"></div>
						</div>
			</DIV>
		</form>
	</body>
</html>