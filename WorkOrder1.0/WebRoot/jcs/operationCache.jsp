<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hy.wo.po.BatchData"  %>
<%@ page import="com.hy.wo.cache.BatchOperationCacheManager"  %>
<%
		String action = request.getParameter("action");
		String key = request.getParameter("key");		
		String timeType = request.getParameter("timeType");
		String cacheName = request.getParameter("cacheName");		
		BatchOperationCacheManager WO_CM = BatchOperationCacheManager.getInstance();
		if("input".equals(action)){//把数据放入缓存中的操作			
			if("batchTypeCache".equals(cacheName)){	//把数据放入userCache缓存里
				//User user = FCM_M.getUserFromDB(key);
				//FCM_CM.putUserToJCS(key+"FCM_PAL", user);
				response.sendRedirect("/jcs/JCSAdminDefault.jsp?cause=此操作无效");
			}else if("batchDataCache".equals(cacheName)){//把数据放入idCardInfoCache缓存里
				BatchData batchData = WO_CM.getBatchDataFromJCS(key);
				Long value = Long.valueOf(request.getParameter("timeLong"));
				if(batchData == null){
					response.sendRedirect("/jcs/JCSAdminDefault.jsp?cause=cacheDataNull");	
					return;
				}else{
						
					//WO_CM.putIdcardInfoToJCS(key, idCardInfo);
				}				
			}
			response.sendRedirect("/jcs/JCSAdminDefault.jsp?cause=success");	
			return;
		}else if("query".equals(action)){	//根据你输入的缓存区域名和键值显示相应的具体信息
			response.sendRedirect("/jcs/"+cacheName+"Detail.jsp?action=detail&operate=single&cacheName="+cacheName+"&key="+key);
			return;
		}else if("remove".equals(action)){
			response.sendRedirect("/jcs/"+cacheName+"Detail.jsp?action=remove&operate=single&cacheName="+cacheName+"&key="+key);
		}else if("listGameServerIP".equals(action)){
			response.sendRedirect("/jcs/gameServerIpList.jsp?gameServieceID="+request.getParameter("gameServerIDList"));
		}else if("listUsernameByGameId".equals(action)){
			response.sendRedirect("/jcs/"+cacheName+"Detail.jsp?action=detail&operate=getUserByGameId&cacheName="+cacheName+"&gameServieceID="+request.getParameter("gameServerIDList"));
		}
%>

