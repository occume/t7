<%@ page language="java" import="java.util.HashMap,java.util.List,java.util.Iterator,org.apache.jcs.admin.*" contentType="text/html;charset=UTF-8"%>
<%
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache"); 
	String cause = request.getParameter("cause");

	if(cause == null){
		cause = "";
	}
 %>
<html>
  <head>   
	<title>JCSAdmin Default</title>	
	<link rel="stylesheet" type="text/css" href="/css/default.css" />
	<script type="text/javascript">
	<!--
		function load(){
			
			var cause = '<%=cause%>';
			if(cause != null && cause=="success"){
				document.getElementById("causelbl").innerHTML = "<font style='color:red;'>数据放入到缓存中成功！</font>";
			}else if(cause != null && cause=="keyNull"){
				document.getElementById("causelbl").innerHTML = "<font style='color:red;'>主键不能为空！</font>";
			}else if(cause != null && cause=="limitVisit"){
				document.getElementById("causelbl").innerHTML = "<font style='color:red;'>你无权访问此页</font>";
			}else if(cause != null && cause=="dataNull"){
				document.getElementById("causelbl").innerHTML = "<font style='color:red;'>没有你所请求的数据,请输入正确的主键</font>";
			}else if(cause != null && cause=="removeSuccess"){
				document.getElementById("causelbl").innerHTML = "<font style='color:red;'>数据删除成功!</font>";
			}else if(cause != null && cause=="cacheDataNull"){
				document.getElementById("causelbl").innerHTML = "<font style='color:red;'>缓存中不存在此条数据!</font>";
			}else{
				document.getElementById("causelbl").innerHTML = "";
			}
		}
	//-->
	</script>
  </head>
 
  <body onLoad="load()">
    	 <%
  	 if(request.getHeader("Referer")!=null){
  	 		String action = request.getParameter("action");
  	 		String cacheName = request.getParameter("cacheName");
  	 		JCSAdminBean jcsBean = new JCSAdminBean();
	  		HashMap context = new HashMap();
	  		if(action != null){
	  			if(action.equals("clearRegion")){		//请求删除缓存数据时的操作
	  				if(cacheName == null){
	  				}else{
	  					jcsBean.clearRegion(cacheName);
	  				}
	  			}
	  		}
	  		context.put("cacheRegionDefault",jcsBean.buildCacheInfo());
  		
  	%>
	<center>
		<p>
    		<label id="causelbl"></label>
    	</p>
	</center>
	<div style="display:block;" id="div1">
  	<center> 
  		============================================================================<br>
    	<font style="color:red;font-size:18px;font-weight:bold;">防 沉 迷 项 目</font> 所 有 的 缓 存 区 域 信息<br>
    	============================================================================<br>			
    </center>
    </div>
  	<br>
  	<div style="display:block;" id="div2">
  	<TABLE cellSpacing=1 cellPadding=5 width="100%" align=center bgColor=#333333 border=0>
		  <tr bgColor=#ffffff>
			<th> 缓存名字 </th>
	        <th title="Gets the size attribute of the Cache object"> 缓存对象数目</th>
	        <th> 内存字节数 </th>
	        <th> 区域是否活跃 </th>
	        <th title="Number of times a requested item was found in the memory cache"> 对象在内存缓存中被找到的次数</th>
	        <th title="Number of times a requested item was found in and auxiliary cache">对象在辅助缓存中被找到的次数</th>
	        <th title="Number of times a requested element was not found"> 请求的元素没有被找到的次数</th>
	        <th title="Number of times a requested element was found but was expired"> 请求的元素因为超时没有被找到的次数</th>
	        <th> 操作 </th>
  		</tr>
	  	<%
	  		//读取到配置文件cache.ccf所有区域名
 			
	  		List list = (List)context.get("cacheRegionDefault");
	  		Iterator it = list.iterator();
	  		
	  		while(it.hasNext()){	  		    
	  			CacheRegionInfo regionDefault = (CacheRegionInfo)it.next();
	   %>  
   		<tr bgColor=#ffffff>
           <td> <%=regionDefault.getCache().getCacheName()%> </td>
           <td> <%=regionDefault.getCache().getSize()%> </td>
           <td> <%=regionDefault.getByteCount()%> </td>
           <td> <%=regionDefault.getStatus()%> </td>
           <td> <%=regionDefault.getCache().getHitCountRam()%> </td>
           <td> <%=regionDefault.getCache().getHitCountAux()%> </td>
           <td> <%=regionDefault.getCache().getMissCountNotFound()%> </td>
           <td> <%=regionDefault.getCache().getMissCountExpired()%> </td>
           <td>    
            <a href="<%=regionDefault.getCache().getCacheName()%>Detail.jsp?action=detail&operate=all&cacheName=<%=regionDefault.getCache().getCacheName()%>"> Detail(详细信息) </a>    
            <a href="JCSAdminDefault.jsp?action=clearRegion&cacheName=<%=regionDefault.getCache().getCacheName()%>"> Clear(删除)</a>           
           </td>
     	</tr>
  	 <%}%>
   </table> 
   <br>  
   <hr>  	
   </div>
   <br><br>
   <div style="display:block;" id="div3">	
	<center>
    	================================================================================<br>
    				操 作 <font style="color:red;font-size:24px;font-weight:bold;">防 沉 迷　项　目</font> 缓 存 区 域 测　试　页　面<br>
    					    					
    	================================================================================<br>
    </center>
    <form action="operationCache.jsp" method="post" onSubmit="return checkinput()">
    	<TABLE align=center>
    		<tr bgColor=#ffffff>
    			<td align="center" colspan="2">
    				&nbsp;<input name="operationData" id="inputData" type="radio" onclick="rdoClick()" value="input">&nbsp;<label for="inputData">输入数据</label>
    				&nbsp;<input name="operationData" id="queryData" type="radio" onclick="rdoClick()" value="query"><label for="queryData">根据主键获取数据</label>
    				&nbsp;<input name="operationData" id="queryDataByGameId" type="radio" onclick="rdoClick()" value="query"><label for="queryDataByGameId">根据游戏ID获取数据</label>
    				&nbsp;<input name="operationData" id="removeData" type="radio" onclick="rdoClick()" value="remove"><label for="removeData">根据键值删除缓存数据</label>
    				&nbsp;<input name="operationData" id="queryGameServerIP" type="radio" onclick="rdoClick()" value="GameServerIP"><label for="queryGameServerIP">根据gameId获取所有IP</label>
    			</td>
    		</tr>     				  		
    		<tr bgColor=#ffffff>
    			<td><span id="key1" style="display:none">主键:</span></td>
    			<td>
    				<span id="key2" style="display:none">
    					<input name="id" id="id" type="text">(注：输入的键值是不区分大小写的)
    				</span>
    			</td>
    		</tr>
    		<tr bgColor=#ffffff>
    			<td><span id="time1" style="display:none;">数据:</span></td>
    			<td>
    				<span id="time2" style="display:none;">
	    				<input name="timeType" id="onlineTime" type="radio" value="onlineTime" checked>累计在线时长
	    				<input name="timeType" id="offlineTime" type="radio" value="offlineTime">累计下线时长
    				</span>
    			</td>
    		</tr>
    		<tr bgColor=#ffffff>
    			<td>
    				<span id="timeLong1" style="display:none;">累计时长:</span>
    			</td>
    			<td>
    				<span id="timeLong2" style="display:none">
    					<input name="timeLong" id="value" type="text">
    				</span>
    			</td>
    		</tr>
    		<tr bgColor=#ffffff>
    			<td>
    				<span id="gameServerID1" style="display:none;">游戏服务器ID列表:</span>
    			</td>
    			<td>
    				<span id="gameServerID2" style="display:none">
    					<select id="gameServerIDList" name="gameServerIDList">
    						<option value="FCM_GD" selected>敢达</option>   						
    						<option value="FCM_RICH">大富翁</option>
    						<option value="FCM_SC">神兵传奇</option>
    						<option value="FCM_CG2">魔力宝贝II</option>    						   						
    						<option value="FCM_JW2">GT劲舞2</option>
    						<option value="FCM_MF">宠物森林</option>
    						<option value="FCM_FJ">风火之旅</option>
    						<option value="FCM_SDO">超级舞者</option>
    						<option value="FCM_CF">侠道飞车</option>
    						<option value="FCM_LQ">劲爆篮球</option>
    						<option value="FCM_DHO">勇士无双</option>
    						<option value="FCM_WOW">勇士无敌</option>
    						<option value="FCM_LH">蓝海战记</option>
    						<option value="FCM_LX">流星蝴蝶剑</option>  
    						<option value="FCM_AU">劲舞</option>  
    						<option value="FCM_RC">光线飞车</option>		
    						<option value="FCM_PAL">仙剑</option>				
    					</select>
    				</span>
    			</td>
    		</tr>
    		<tr bgColor=#ffffff>
    			<td>请选择缓存区域名:</td>
    			<td>
    				<select name="cacheName">
    				<%
    					list = (List)context.get("cacheRegionDefault");//从cache.ccf文件中得到所有的区域块
	  					it = list.iterator();
				  		while(it.hasNext()){	  		    
	  						CacheRegionInfo regionDefault = (CacheRegionInfo)it.next();
	  						out.println("<option value="+regionDefault.getCache().getCacheName()+">"+regionDefault.getCache().getCacheName()+"</option>");
    					} %>
    				</select>
    			</td>
    		</tr>
    		<tr bgColor=#ffffff>
    			<td colspan="2" align="center">
    				<input type="submit" value="提交">
    				<input type="hidden" name="action" id="action" value="input">
    			</td>
    		</tr>
    	</table>  
    </form>
    <br><hr>
    </div>
    <br>
   <%
  	 }
 	 	else{
 	 	out.println("<center>你无权访问此页!</center>");
 	 } 
    %>
  </body>
    <script type="text/javascript">
  <!--		
  		//提交的参数的验证
  		function checkinput(){
  			if(document.getElementById("inputData").checked == true||document.getElementById("queryData").checked == true||document.getElementById("removeData").checked == true){
	  			if(document.getElementById("key").value.length <= 0){
	  				alert("主键不能为空，请输入相应的值");
	  				return false;
	  			}
	  		}
  			if(document.getElementById("inputData").checked == true){
	  			if(document.getElementById("value").value.length<= 0){
	  				alert("值不能为空，请输入");
	  				return false;
	  			}	  			
	  		}
  		}
  		
  		//根据你选择的操作选项，显示相应的操作区域
  		function rdoClick(){
			if(document.getElementById("inputData").checked == true){	
				document.getElementById("key1").style.display = "block";
				document.getElementById("key2").style.display = "block";
				document.getElementById("timeLong1").style.display = "block";
				document.getElementById("timeLong2").style.display = "block";
				document.getElementById("time1").style.display = "block";
				document.getElementById("time2").style.display = "block";
				document.getElementById("gameServerID1").style.display = "none";
				document.getElementById("gameServerID2").style.display = "none";
				document.getElementById("action").value = "input";
			}else if(document.getElementById("queryData").checked == true){
				document.getElementById("key1").style.display = "block";
				document.getElementById("key2").style.display = "block";
				document.getElementById("timeLong1").style.display = "none";
				document.getElementById("timeLong2").style.display = "none";
				document.getElementById("time1").style.display = "none";
				document.getElementById("time2").style.display = "none";
				document.getElementById("gameServerID1").style.display = "none";
				document.getElementById("gameServerID2").style.display = "none";
				document.getElementById("action").value = "query";
			}else if(document.getElementById("removeData").checked == true){
				document.getElementById("key1").style.display = "block";
				document.getElementById("key2").style.display = "block";
				document.getElementById("timeLong1").style.display = "none";
				document.getElementById("timeLong2").style.display = "none";
				document.getElementById("time1").style.display = "none";
				document.getElementById("time2").style.display = "none";
				document.getElementById("gameServerID1").style.display = "none";
				document.getElementById("gameServerID2").style.display = "none";
				document.getElementById("action").value = "remove";
			}else if(document.getElementById("queryGameServerIP").checked == true){
				document.getElementById("key1").style.display = "none";
				document.getElementById("key2").style.display = "none";
				document.getElementById("timeLong1").style.display = "none";
				document.getElementById("timeLong2").style.display = "none";
				document.getElementById("time1").style.display = "none";
				document.getElementById("time2").style.display = "none";
				document.getElementById("gameServerID1").style.display = "block";
				document.getElementById("gameServerID2").style.display = "block";
				document.getElementById("action").value = "listGameServerIP";
			}else if(document.getElementById("queryDataByGameId").checked == true){
				document.getElementById("key1").style.display = "none";
				document.getElementById("key2").style.display = "none";
				document.getElementById("timeLong1").style.display = "none";
				document.getElementById("timeLong2").style.display = "none";
				document.getElementById("time1").style.display = "none";
				document.getElementById("time2").style.display = "none";
				document.getElementById("gameServerID1").style.display = "block";
				document.getElementById("gameServerID2").style.display = "block";
				document.getElementById("action").value = "listUsernameByGameId";
			}
  		}
  //-->
  </script>
</html>
