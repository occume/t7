<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>error</title>
<script>
<s:if test="js!=null">
	${js}
</s:if>
<s:else>
	parent.dialog.Alert('<div class=\"errorMsgPanel\">上传图片错误<br/>1.请检查图片格式(JPG,JPEG,PNG,GIF)<br/>2.图片大小不能超过2M</div>');
	setTimeout(function(){parent.dialog.close();},3000);
	parent.enabledsubmit_btn();
</s:else>
</script>

</head>
<body>
<s:debug></s:debug>
</body>
</html>