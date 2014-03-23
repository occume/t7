<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.hy.wo.util.MyUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
		String accName=(String)session.getAttribute("accName");	
		String gameRoleName=(String)session.getAttribute("gameRoleName");
		String zonem=(String)session.getAttribute("zonem");
		
		if(MyUtil.isBlankOrNull(accName)){
				accName=request.getParameter("gaccount")==null?"NoName":request.getParameter("gaccount");	
				gameRoleName=request.getParameter("relver")==null?"NoRoleName":request.getParameter("relver");
				zonem=request.getParameter("zonem")==null?"1":request.getParameter("zonem");
				
				session.setAttribute("accName",accName);
				session.setAttribute("gameRoleName",gameRoleName);
				session.setAttribute("zonem",zonem);
		}
		
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title>上海火游网络有限公司-客户服务中心</title>
		<link href="../css/gameService.css" type="text/css" rel="stylesheet" />
		<link href="../css/dialog.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="../js/jquery.js"></script>
		
		<script type="text/javascript" src="../js/common.js?ver=0.926"></script>
		<link href="../css/customtab.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="../include/jquery.validate.min.js"></script>
		<script type="text/javascript" src="../include/additional-methods.js"></script>
		<script type="text/javascript" src="../include/g_gameBug.js"></script>
		
		<script type="text/javascript" src="../include/dialog.js"></script>

	</head>

	<body scroll="no">
		<DIV class=main>
<form name="roleAbnormalForm" id="roleAbnormalForm" action="/wo/save_ggameBug" method="post" enctype="multipart/form-data" >

  <input type="hidden" name="source" value="2"/>
  <input type="hidden" name="userInfo.accountname" value="${accName}"/>
  <input type="hidden" name="additional.currentVersion" value="${gameRoleName}"/>
  <input type="hidden" name="userInfo.server.id" value="${zonem}"/>
  <div class="header"><img src="../images/texttitle5.png" /></div>
  <!-- 
	  <DIV class=formcont>
	  <DIV class=formlefttitle2>发生时间： </DIV>
	  <DIV class=formrigcont>
										<input type="text" id="happendate" name="userInfo.happendate" readonly="readonly"
												 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})"  
												 class="input180" tabindex="2"/>
											<span>*</span> </DIV>
	  <DIV class="baocuo1"></DIV>
	  <DIV class=clear></DIV>
	  <DIV class=textnotes>准确选择问题发生时间，有助于我们更快为您解决问题。 </DIV>
	  </DIV>
 -->
  <DIV class=formnotestext2><span style="margin-left:120px;color:red;">*建议您填写手机号或QQ，以便我们能及时联系您！我们会严格保密您的个人资料！</span>
   </DIV>
  <DIV class=formcont>
  <DIV class=formlefttitle2>BUG描述： </DIV>
  <DIV class=formtextareacont>
		<textarea  id="memo" name="userInfo.memo" class="textarea" tabindex="12"></textarea>
</textarea></DIV>
 
  <DIV class=clear></DIV>
 <DIV class=formnotestext2></DIV>
  <DIV class=clear1></DIV>
  <DIV class=baocuo1 > </DIV>
  </DIV>
<!--  
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
-->
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
											<div class=formnotestext2><span>友情提示：</span><br/>1.上传图片的格式,支持 jpg - jpeg - gif 格式的图片,图片大小允许2MB以内 <br/>2.您可以在游戏中使用PRINTSCREEN键进行截图，然后可以在您的游戏安装目录下FengYunOLGame\ScreenShots找到该截图） </div>
										</div>


  <DIV class=buttonBox>
<div class="buttonYes">
												<a onclick="register(this);" tabindex="10" id="submit_btn">提交</a>
											</div>
<button class=buttonNo onclick="javascript:document.roleAbnormalForm.reset();return false;" onmouseover="this.className='buttonNo_mouseover'"  onmouseout="this.className='buttonNo_mouseout'" >重填</button>
  <DIV class=clear></DIV>
  </DIV>
</form>
</DIV>
		

	</body>
</html>