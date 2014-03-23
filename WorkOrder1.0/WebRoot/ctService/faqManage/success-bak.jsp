<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>\
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>
<link rel="stylesheet" href="${context_path}/css/common.css"></link>	
<link rel="stylesheet" href="${context_path}/css/dialog.css"></link>	
<script type="text/javascript" src="${context_path}/js/jquery.js"></script>
<script type="text/javascript" src="${context_path}/include/dialog.js"></script>
<script type="text/javascript" src="${context_path}/js/faq.js"></script>

<div align="center">
	 <div class="popTop">
	  	<span class="adel" onclick="parent.dialog.close();"></span>提示：${message}
	  </div>
	  <div class="clearfix popErrorContent" >
	  
			<div id="add-box" style="height:180px; width:360px; padding:20px;">
			
					<span style="color:#008080;font-size:30px;list-style:none;">
							<s:fielderror></s:fielderror>
							${message} 
					 </span>
					 <script type="text/javascript">
					 if(window.parent != window){
					 		setTimeout(function(){
					 				parent.dialog.close();
					 				window.parent.location.href=window.parent.location.href;
					 		},2000);
					 	}
					 </script>
		</div>
		</div>
		<div class="popBottom">
			<input id="btn_success" type="button" onclick="parent.dialog.close();"  value="确 定" />
		</div>
</div>