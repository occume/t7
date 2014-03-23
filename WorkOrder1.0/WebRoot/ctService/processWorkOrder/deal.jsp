<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>处理工单</title>    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link rel="stylesheet" type="text/css" href="../../css/dialog.css"/>
	<script type="text/javascript">
		function radioBack(radio){
			if( radio['cked'] == 0){
                 radio.checked = false;
                 radio['cked'] = 1;
            }else{
                 radio.checded = true;
                 radio['cked'] = 0;    
            }
		}
	</script>
  </head>
  <body style="margin:0px; padding:0px;width:400px;">
	  <div class="popTop">
	  	<span class="adel" onclick="parent.dialog.close();"></span>请填写处理情况
	  </div>
	  <form action="/json/query_dealWorkOrder" method="post">
	  <div class="clearfix popErrorContent" >
	 	 <div>
	  		处理完毕：<input type="radio"  name="extraCondition"  value="w" onclick="radioBack(this)"/>
	  	</div>
		<div style="height:180px; width:360px; padding:20px;">
		 	<input type="hidden" name="id" value="${param.woId}"/>
		 	<textarea name="description" style="width:350px;height:150px;"></textarea>
		</div>
		</div>
		<div class="popBottom">
			<input type="submit" id="btn_submit"  value="确 定" />
		</div>
		</form>
</body>
</html>