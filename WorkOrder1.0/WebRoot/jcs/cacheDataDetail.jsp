<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.Set,java.util.Date,java.text.SimpleDateFormat,java.util.HashMap,java.util.List,java.util.ArrayList,java.util.Iterator,org.apache.jcs.admin.*" %>
<%@ page import="com.hy.wo.po.WorkOrder"%>
<%@ page import="com.hy.wo.po.BatchData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	System.out.println("**************cacheDataDetail.jsp");
	String cacheName = (String)request.getSession().getAttribute("cacheName");	
	List<BatchData> list = (List<BatchData>)request.getSession().getAttribute("cacheDataList");//获取所有的工单列表集
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>批量回得工单列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="../js/jquery.js"></script> 
	<script type="text/javascript">
		//获取批量操作类型
		function getBatchTypeFromCache(objcect){
			alert("*************获取批量操作类型*************");
			var cacheName = $("#cacheName").val();
			alert("cacheName = " + cacheName);
			var url = "/index/cache_getBatchTypeList";//获取批量操作类型URLgetBatchTypeList
			$.getJSON(url,{cacheName:"batchTypeCache"},function(data){
		        $.each(data,function(i,wo){ //i为key,wo为value
		        	alert("获取到的数据");
		        	alert(data);  
	            });
		        });
		}
		//根据批量操作类型获取数据
		function getBatchDataFromCache(objcect){
			alert("*************获取批量操作类型*************");
			var cacheName = $("#cacheName").val();
			alert("cacheName = " + cacheName);
			var url = "/index/cache_getBatchTypeList";//获取批量操作类型URLgetBatchTypeList
			$.getJSON(url,{cacheName:"batchTypeCache",batchType:$(object).val()},function(data){
		        $.each(data,function(i,wo){ //i为key,wo为value
		        	alert("获取到的数据");
		        	alert(data);  
	            });
		        });
		}
	</script>
  </head>
  
  <body>
	<div id="content">
		<div id="contentbox2">
			<div class="channeltexttitle"><img src="images/title_news.jpg" /><br /><br /></div>
			<!--左边内容  开始-->
			<div id="jobleftbox">
				
				<div id="newslistcont">
				<c:if test="${cacheDataList.size()==0}">未取到符合你条件的工单数据</c:if>
				<form action="">
				<input type="text" id="cacheName" name="cacheName" value="batchTypeCache" />
				<select id="batchType" onchange="getBatchDataFromCache(this);" onclick="getBatchDataFromCache(this);" style="width:20px;">
					<option value="宕机">宕机</option>
	    			<option value="维护">维护</option>
	    			<option value="批量退回">批量退回</option>
	    		</select><br />
				<c:if test="${cacheDataList.size()>0}">
					<ul>
						<li><span>玩家账号</span><span>问题类型</span><span>创建时间</span><span>服务器</span><span>问题描述</span></li>
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
			  		//CacheElementInfo elementInfo = (CacheElementInfo)it.next();
		  			try{
		  				BatchData batchData = (BatchData)it.next();
			  				  		
				   %> 
					<li><span><%=batchData.getWorkOrder().getId() %></span><span><%=batchData.getWorkOrder().getUserInfo().getAccountname() %></span><span><%=batchData.getWorkOrder().getSuggestion() %></span><span><%=batchData.getWorkOrder().getSuggestion() %></span><span><%=batchData.getWorkOrder().getSuggestion() %></span></li> 
		  			<%
		  			}catch(ClassCastException e){ //从缓存中取数据进行强行转换出现异常时的处理
		  			 e.getMessage();
		  			}  
		  			i++; 	 
				}
				%>
			</ul>
			<div id="newslistbottom">
				<div class="buttonselect">
		  			第<%=intPage%>页 共<%=intPageCount%>页
		  		</div>		  		
				<%if(intPage<intPageCount){%>
					<div class="buttonnext"><a href="/jcs/cacheDataDetail.jsp?page=<%=intPage+1%>">下一页</a></div>
				<%}if(intPage>1){%>
					<div class="buttonfront"><a href="/jcs/cacheDataDetail.jsp?page=<%=intPage-1%>">上一页</a></div>
				<%}if(intPageCount >0 && intPage>1 ){%>
					<div class="buttonfirst"><a href="/jcs/cacheDataDetail.jsp?page=<%=1%>">跳转到第一页</a></div>
				<%}if(intPageCount >0 && intPage<intPageCount){%>
					<div class="buttonend"><a href="/jcs/cacheDataDetail.jsp?page=<%=intPageCount%>">跳转到最后一页</a></div>
				<%}%>
			</div>								
			</c:if>
			<textarea rows="20" cols="50">
				批量操作回复
			</textarea>
			</form>
		</div>
	</div> 
  </body>
</html>
