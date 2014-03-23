<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>\
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<div>
	<form action="" method="post">
		请输入旧密码：<input type="password" name="oldPassword"/><br/>
		请输入新密码：<input type="password" name="password"/><br/>
		请确认新密码：<input type="password" name="rePassword"/><br/>
		<input type="submit" value="commit"/>
	</form>
</div>