<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>\
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>
<link rel="stylesheet" href="${context_path}/css/common.css"></link>	
<link rel="stylesheet" href="${context_path}/css/dialog.css"></link>	
<script type="text/javascript" src="${context_path}/js/jquery.js"></script>
<script type="text/javascript" src="${context_path}/include/dialog.js"></script>
<script type="text/javascript" src="${context_path}/js/faq.js"></script>
<script type="text/javascript" src="../include/My97DatePicker/WdatePicker.js"></script>

<div align="center">
 
	 <div class="popTop">
	  	<span class="adel" onclick="parent.dialog.close();"></span> 回复Faq：
	  </div>
	 
	  <span style="color:red;font-size:15px;list-style:none;"><s:fielderror></s:fielderror>  </span>
	  <s:push value="faq">
	  <input type="hidden" name="id" value="<s:property value="id"/>"/>
		<div id="add-box" style="">
			<ul>
				<li><label>标题：</label><s:property value="title"/></li>
				<li><label>游戏：</label><s:property value="gameName"/></li>
				<li><label>标签：</label><s:property value="sortType"/></li>
				<li><label>类型：</label><s:property value="type"/></li>
				<li><label>可见者：</label>
						<s:if test="toUser==true">玩家</s:if>
						<s:else>客服</s:else>
					</li>
				<li><label>修改时间：</label><input type="text" value="<s:property value="createTime"/>" name="createTime" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'ext',maxDate:'%y-%M-%d'})" /></li>
				<li>
					<label>描述：</label><s:property value="descrip"/>
				</li>
				<li>
					<hr/>
				<form id="saveFaq" action="/faq/replyFaq" method="post">
				<!-- <s:radio name="status" list="#{'1':'已知（未修复）','2':'已知（已修复）'}"></s:radio><br/> -->	
				<div>
	  				<span>已知（未修复）：<input type="radio"  name="status"  value="已知（未修复）"/></span>
	  				<span>已知（已修复）：<input type="radio" name="status"  value="已知（已修复）"/></span>
	  				<span>关闭：&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  name="status" value="关闭"/></span>
	  			</div>
					<s:textarea name="descrip" id="faqDescrip"/><br/>
					<input type="submit"  value="确 定" />
				</form>
				</li>
			</ul>
			
		</div>
		
		
		</s:push>
		
		<div class="popBottom">
			
		</div>
		
</div>