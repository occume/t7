<%@ page import="com.hy.wo.cache.BatchOperationCacheManager,java.util.Set,java.util.Date,java.text.SimpleDateFormat,java.util.HashMap,java.util.List,java.util.ArrayList,java.util.Iterator,org.apache.jcs.admin.*" contentType="text/html;charset=utf-8"%>
<%@ page import="com.hy.wo.po.BatchData"  %>
<html>
  <head>   
	<title>Region Detail Cache Records</title>
  </head>
 
  <body>
  <br> 	 
  	 <%
  	 	if(request.getHeader("Referer")!=null){
	  	 	String cacheName = request.getParameter("cacheName");
	  	 	String action = request.getParameter("action");
	  	 	String operate = request.getParameter("operate");
	  	 	JCSAdminBean jcsBean = new JCSAdminBean();
		 	HashMap context = new HashMap();	
		 	if(action != null && "remove".equals(action) && operate !=null && "all".equals(operate)){//根据KEY值删除缓存中相应的数据
	 			String key = request.getParameter("key");
	 			jcsBean.removeItem(cacheName,key);
		 	}
  	%>
  	<center> 
    	UtilCache 缓 存 块 区 域 操 作 
    </center>
  	<br>
  	
  	<hr>
	  	<%
	  		List list = (List)jcsBean.buildElementInfo(cacheName);
	  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:s");
	  		String userInfo = null;
	  		if(operate !=null && "single".equals(operate)){	//根据主键显示缓存块中具体的一条数据
	  			int count = 0;
	  			String reqKey = request.getParameter("key");
	  			if(reqKey == null || "".equals(reqKey)){
	  				response.sendRedirect("/jcs/JCSAdminDefault.jsp?cause=keyNull");//如果请求的主键为空,跳转到主页,提示其主键不能为空
	  				return;
	  			}else{//得到请求的主键,如果主键不为空,显示其相应的数据
	  				BatchOperationCacheManager WO_CM = BatchOperationCacheManager.getInstance();
	  				if("detail".equals(action)){//根据缓存主键取回其相应的信息
	  					BatchData batchData = WO_CM.getBatchTypeFromJCS(reqKey);
	  					if(batchData == null){
	  					}else{
	  						response.sendRedirect("/jcs/batchData.jsp?idCardInfoCacheKey=aa");//如果请求的主键为空,跳转到主页,提示其主键不能为空
	  					}	
	  				}else if("remove".equals(action)){	  	
	  					WO_CM.removeBatchTypeFromJCS(reqKey);
	  					++count;
	 		   	    }
 		   	 	}
 		   	 	if(count == 0){
	  	 			response.sendRedirect("/jcs/JCSAdminDefault.jsp?cause=dataNull");//如果请求的主键为空,跳转到主页,提示其主键不能为空
	  	 		}else if(count == 1 && "remove".equals(action)){		// 如果删除成功
 		   			response.sendRedirect("/jcs/JCSAdminDefault.jsp?cause=removeSuccess");
 		   	 	}
		  	 }else if(operate !=null &&"all".equals(operate)){
		  	 
		  	 %>
		  	 	<table width="85%" border="1" align="center" cellpadding="5" cellspacing="3">
		  		<tr>
			   		<td>主键</td>
			   		<td>一个对象在JCS中是否是永久存在?</td>
			   		<td>创建时间</td>
			   		<td> 最大生命时间 (s)</td>
			   		<td>还有多长时间过期(s)</td>
			   		<td>操作</td>
			   	</tr>			  
		  	 <%
		  	 	int intPageSize; //一页显示的记录数
				int intRowCount; //记录总数
				int intPageCount; //总页数
				int intPage; //待显示页码 
				String strPage; 
				intPageSize = 6;  //设置一页显示的记录数
							
				strPage = request.getParameter("page");//取得待显示页码
				if(strPage==null){//表明在QueryString中没有page这一个参数，此时显示第一页数据
					intPage = 1;
				}else{//将字符串转换成整型
					intPage = Integer.parseInt(strPage);
					if(intPage<1) intPage = 1;
				} 
				
		  		intRowCount = list.size();//获取记录总数	  		
				intPageCount = (intRowCount+intPageSize-1)/intPageSize;//记算总页数
				
				//调整待显示的页码
				if(intPage>intPageCount){
					intPage = intPageCount; 
				}
				List lst = new ArrayList();
				int pageMaxSize = 0;
				if(intPage*intPageSize>=intRowCount){		//得到每页最后的一条数据数
					pageMaxSize = intRowCount;				//,假如已经是最后一条记录的时候就把最后一条记录数设置为最大数
				}else{
					pageMaxSize = intPage*intPageSize;		//每页的最后一记录的数
				}
				if(intPageCount>0){//将记录指针定位到待显示页的第一条记录上
					for(int j=intPage*intPageSize-intPageSize;j<pageMaxSize;j++){
						lst.add(list.get(j));	//得到每页显示的数据
					}
				}
		  		Iterator it = lst.iterator();
		  		 
		  		int i = 0;		
	  			while(i<intPageSize && it.hasNext()){
			  		CacheElementInfo elementInfo = (CacheElementInfo)it.next();
		  			try{
		  				BatchData batchData = (BatchData)org.apache.jcs.JCS.getInstance(request.getParameter("cacheName")).get(elementInfo.getKey());
			  		//	userInfo = " |idCardInfoOnlineTime: "+idCardInfo.getOnlineTime()/(1000*60*1.0)+"|idCardInfoLastAccessTime : "+(sdf.format(new Date(idCardInfo.getLastAccessTime())))
					  							//+" |idCardInfoOfflineTime : "+idCardInfo.getOfflineTime()/(1000*60*1.0); 			  		
				   %>  
			   		<tr>
				   	 	 <td><a href="<%=request.getContextPath()%>/jcs/batchTypeCache.jsp?batchTypeCacheKey=<%=elementInfo.getKey()%>&batchType=<%=batchData.getBatchType() %>&batchTypeName=<%=batchData.getBatchTypeName()%>"><%=elementInfo.getKey()%></a></td>
				   	 	 <td><%=elementInfo.isEternal()%> </td>
				         <td> <%=elementInfo.getCreateTime()%> </td>
				         <td> <%=elementInfo.getMaxLifeSeconds()%> </td>
				         <td> <%=elementInfo.getExpiresInSeconds()%> </td>    
				         <td>
				         	<a href="<%=cacheName%>Detail.jsp?action=remove&operate=all&cacheName=<%=cacheName%>&key=<%=elementInfo.getKey()%>"> Remove </a>
				         </td>    	
				   	 </tr>	
		  			<%
		  			}catch(ClassCastException e){ //从缓存中取数据进行强行转换出现异常时的处理
		  			%>	
		  				<tr>
				   	 	 <td><%=elementInfo.getKey()%></td>
				   	 	 <td><%=elementInfo.isEternal()%> </td>
				         <td> <%=elementInfo.getCreateTime()%> </td>
				         <td> <%=elementInfo.getMaxLifeSeconds()%> </td>
				         <td> <%=elementInfo.getExpiresInSeconds()%> </td>    
				         <td>
				         	缓存数据块名: <%=cacheName%><br>
				         	主键: <%=elementInfo.getKey()%><br>
				         	<a href="<%=cacheName%>Detail.jsp?action=remove&operate=all&cacheName=<%=cacheName%>&key=<%=elementInfo.getKey()%>"> Remove </a>
				         </td>    	
				   	 </tr>	
		  			<%
		  			}  
		  			i++; 	 
				}
				%>
	  		第<%=intPage%>页 共<%=intPageCount%>页
			<%if(intPage<intPageCount){%>
				<a href="<%=cacheName%>Detail.jsp?action=detail&operate=all&cacheName=<%=cacheName%>&page=<%=intPage+1%>">下一页</a>
			<%}if(intPage>1){%>
				<a href="<%=cacheName%>Detail.jsp?action=detail&operate=all&cacheName=<%=cacheName%>&page=<%=intPage-1%>">上一页</a>
			<%}if(intPageCount >0 && intPage>1 ){%>
				<a href="<%=cacheName%>Detail.jsp?action=detail&operate=all&cacheName=<%=cacheName%>&page=<%=1%>">跳转到第一页</a>
			<%}if(intPageCount >0 && intPage<intPageCount){%>
				<a href="<%=cacheName%>Detail.jsp?action=detail&operate=all&cacheName=<%=cacheName%>&page=<%=intPageCount%>">跳转到最后一页</a>
			<%}
		  	 }else if(operate !=null &&"getUserByGameId".equals(operate)){
		  	 
		  	 
		  	 %>
		  	 	<table width="85%" border="1" align="center" cellpadding="5" cellspacing="3">
		  		<tr>
			   		<td>主键</td>
			   		<td>一个对象在JCS中是否是永久存在?</td>
			   		<td>创建时间</td>
			   		<td> 最大生命时间 (s)</td>
			   		<td>还有多长时间过期(s)</td>
			   		<td>操作</td>
			   	</tr>			  
		  	 <%
		  	 	int intPageSize; //一页显示的记录数
				int intRowCount; //记录总数
				int intPageCount; //总页数
				int intPage; //待显示页码 
				String strPage; 
				intPageSize = 6;  //设置一页显示的记录数
							
				strPage = request.getParameter("page");//取得待显示页码
				if(strPage==null){//表明在QueryString中没有page这一个参数，此时显示第一页数据
					intPage = 1;
				}else{//将字符串转换成整型
					intPage = Integer.parseInt(strPage);
					if(intPage<1) intPage = 1;
				} 
				
		  		intRowCount = list.size();//获取记录总数	  		
				intPageCount = (intRowCount+intPageSize-1)/intPageSize;//记算总页数
				
				//调整待显示的页码
				if(intPage>intPageCount){
					intPage = intPageCount; 
				}
				List lst = new ArrayList();
				int pageMaxSize = 0;
				if(intPage*intPageSize>=intRowCount){		//得到每页最后的一条数据数
					pageMaxSize = intRowCount;				//,假如已经是最后一条记录的时候就把最后一条记录数设置为最大数
				}else{
					pageMaxSize = intPage*intPageSize;		//每页的最后一记录的数
				}
				if(intPageCount>0){//将记录指针定位到待显示页的第一条记录上
					for(int j=intPage*intPageSize-intPageSize;j<pageMaxSize;j++){
						lst.add(list.get(j));	//得到每页显示的数据
					}
				}
		  		Iterator it = lst.iterator();
		  		 
		  		int i = 0;		
		  		String gameServieceID= request.getParameter("gameServieceID");
	  			while(i<intPageSize && it.hasNext()){
			  		CacheElementInfo elementInfo = (CacheElementInfo)it.next();
		  			try{
		  				String cacheKey = elementInfo.getKey();
		  				if(cacheKey.indexOf(gameServieceID)!=-1){
		  				BatchData batchData = (BatchData)org.apache.jcs.JCS.getInstance(request.getParameter("cacheName")).get(cacheKey);
			  			//userInfo = " |idCardInfoOnlineTime: "+idCardInfo.getOnlineTime()/(1000*60*1.0)+"|idCardInfoLastAccessTime : "+(sdf.format(new Date(idCardInfo.getLastAccessTime())))
					  						//	+" |idCardInfoOfflineTime : "+idCardInfo.getOfflineTime()/(1000*60*1.0); 			  		
				   %>  
			   		<tr>
				   	 	 <td><a href="<%=request.getContextPath()%>/jcs/idCard.jsp?idCardInfoCacheKey="> </a></td>
				   	 	 <td><%=elementInfo.isEternal()%> </td>
				         <td> <%=elementInfo.getCreateTime()%> </td>
				         <td> <%=elementInfo.getMaxLifeSeconds()%> </td>
				         <td> <%=elementInfo.getExpiresInSeconds()%> </td>    
				         <td>
				         	<a href="<%=cacheName%>Detail.jsp?action=remove&operate=all&cacheName=<%=cacheName%>&key=<%=elementInfo.getKey()%>"> Remove </a>
				         </td>    	
				   	 </tr>	
		  			<%}else{}
		  			}catch(ClassCastException e){ //从缓存中取数据进行强行转换出现异常时的处理
		  			%>	
		  				<tr>
				   	 	 <td><%=elementInfo.getKey()%></td>
				   	 	 <td><%=elementInfo.isEternal()%> </td>
				         <td> <%=elementInfo.getCreateTime()%> </td>
				         <td> <%=elementInfo.getMaxLifeSeconds()%> </td>
				         <td> <%=elementInfo.getExpiresInSeconds()%> </td>    
				         <td>
				         	缓存数据块名: <%=cacheName%><br>
				         	主键: <%=elementInfo.getKey()%><br>
				         	<a href="<%=cacheName%>Detail.jsp?action=remove&operate=all&cacheName=<%=cacheName%>&key=<%=elementInfo.getKey()%>"> Remove </a>
				         </td>    	
				   	 </tr>	
		  			<%
		  			}  
		  			i++; 	 
				}
				%>
	  		第<%=intPage%>页 共<%=intPageCount%>页
			<%if(intPage<intPageCount){%>
				<a href="<%=cacheName%>Detail.jsp?action=detail&operate=all&cacheName=<%=cacheName%>&page=<%=intPage+1%>">下一页</a>
			<%}if(intPage>1){%>
				<a href="<%=cacheName%>Detail.jsp?action=detail&operate=all&cacheName=<%=cacheName%>&page=<%=intPage-1%>">上一页</a>
			<%}if(intPageCount >0 && intPage>1 ){%>
				<a href="<%=cacheName%>Detail.jsp?action=detail&operate=all&cacheName=<%=cacheName%>&page=<%=1%>">跳转到第一页</a>
			<%}if(intPageCount >0 && intPage<intPageCount){%>
				<a href="<%=cacheName%>Detail.jsp?action=detail&operate=all&cacheName=<%=cacheName%>&page=<%=intPageCount%>">跳转到最后一页</a>
			<%}
		  	 
		  	 }else{
		   		response.sendRedirect("/jcs/JCSAdminDefault.jsp?cause=limitVisit");//如果得不到具体的操作项,跳转到主页面,提示其无权操作
		   }%>
   </table>
    <P align="center">
   		<a href="JCSAdminDefault.jsp">返回主页</a>
   </P>
   <%} else{
   		out.println("<center>你无权访问此页!</center>");
   } %>
  </body>
</html>
