<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>\
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" type="text/css" href="../../css/dialog.css"/>
<div align="center">
	 <div class="popTop">
	  	<span class="adel" onclick="parent.dialog.close();"></span>修改昵称：
	  </div>
	  <form action="/acc/acc_modifyNickName" method="post">
	  <div class="clearfix popErrorContent" >
	  <span style="color:red;font-size:15px;list-style:none;"><s:fielderror></s:fielderror>  </span>
		<div style="height:180px; width:360px; padding:20px;">
		 	昵称：<input type="text" name="nickName"/><br/>
		</div>
		</div>
		<div class="popBottom">
			<input type="submit"  value="确 定" />
		</div>
		</form>
</div>