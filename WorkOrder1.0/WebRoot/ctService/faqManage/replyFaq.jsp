<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link rel="stylesheet" href="${context_path}/css/common.css"></link>	
	<link rel="stylesheet" href="${context_path}/css/dialog.css"></link>	
	<script type="text/javascript" src="${context_path}/js/jquery.js"></script>
	<script type="text/javascript" src="${context_path}/include/dialog.js"></script>
	<script type="text/javascript" src="${context_path}/js/faq.js"></script>
	<script type="text/javascript" src="../include/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		*{margin:0;padding:0;}
	</style>
	<title>FAQ回复</title> 
</head> 

<body>
	<div id="faq-rep">
	  <div class="top">
	  	<div id="logo-box"><h1>回复Faq</h1></div>
	  </div>
	 
	  <span style="color:red;font-size:15px;list-style:none;"><s:fielderror></s:fielderror>  </span>
	  <s:push value="faq">
	  	
		<div id="content">
			
				<div class="lab"><label>标题：</label><span><s:property value="title"/></span></div>
				<div class="lab"><label>游戏：</label><span><s:property value="gameName"/></span></div>
				<div class="lab"><label>标签：</label><span><s:property value="sortType"/></span></div>
				<div class="lab"><label>类型：</label><span><s:property value="type"/></span></div>
				<div class="lab"><label>版本：</label><span><s:property value="version"/></span></div>
				<div class="lab"><label>可见者：</label>
						<s:if test="toUser==true">玩家</s:if>
						<s:else>客服</s:else>
					</div>
				<div class="lab"><label>修改时间：</label><input type="text" value="<s:property value="createTime"/>" name="createTime" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})" /></div>
				<div>
					<label>描述：</label><span><s:property value="descrip"/></span>
				</div>
				<div>
					
				<div id="fom">
					<form id="saveFaq" action="/faq/replyFaq" method="post">
						<input type="hidden" name="id" value="<s:property value="id"/>"/>
		  				<span>未修复<input type="radio"  name="status"  value="未修复"/></span>
		  				<span>已修复<input type="radio" name="status"  value="已修复"/></span>
		  				<span>关闭<input type="radio"  name="status" value="关闭"/></span><br/>
						<textarea name="repContent" rows="" cols=""></textarea> <br/>
						<input id="yes" type="submit"  value="确 定" />
					</form>
				</div>
				</div>
			
			
		</div>
		
		
		</s:push>
</div>
</body>
</html>
