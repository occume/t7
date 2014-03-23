package com.hy.wo.servlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.NameValuePair;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
import com.hy.wo.po.Faq;
import com.hy.wo.po.Staff;
import com.hy.wo.util.Constants.DWR;
import com.hy.wo.util.Constants.ParamName;
import com.hy.wo.util.Constants.SessionKey;

public class DWRHelper {
		
		/**
		 * 
		 */
		public void startDwr(){
			WebContext wctx = WebContextFactory.get();
			ServletContext context=wctx.getServletContext();
			ScriptSession theSession=wctx.getScriptSession();
			Staff staf=(Staff) wctx.getSession().getAttribute( SessionKey.STAFF );
			Map<Long, ScriptSession> scriptMap=(Map<Long, ScriptSession>) context.getAttribute(DWR.SCRIPT_MAP);
			if(scriptMap==null){
				scriptMap=new HashMap<Long, ScriptSession>();
				context.setAttribute(DWR.SCRIPT_MAP, scriptMap);
			}
			scriptMap.put(staf.getId(), theSession);
			
			if(context.getAttribute(DWR.WCTX)==null)
				context.setAttribute(DWR.WCTX, wctx);
		}
		
		/**
		 * 
		 * @param src
		 * @param map
		 */
		public static void showScriptSession(ServletContext src,Map<String,String> map){
			WebContext wctx = (WebContext) src.getAttribute(DWR.WCTX);
			if(wctx!=null){
				Collection<ScriptSession> sessions = wctx.getAllScriptSessions();
				JSONObject jobj=new JSONObject();
				 Util utilAll = new Util(sessions);
				 utilAll.addFunctionCall(DWR.SHOW_M, jobj.element(DWR.WO, map));
			}
			LinkedList<Map<String,String>> viewList=(LinkedList<Map<String, String>>) src.getAttribute(DWR.VIEW_LIST);
			if(viewList!=null){
				if(viewList.size()<=10){
					viewList.addLast(map);
				}else{
					viewList.removeFirst();
					viewList.addLast(map);
				}
			}
		}
		
		/**
		 * 通知
		 * @param src
		 * @param map
		 */
		public static void showNotice(ServletContext src,Faq faq){
			WebContext wctx = (WebContext) src.getAttribute(DWR.WCTX);
			if(wctx!=null){
				Collection<ScriptSession> sessions = wctx.getAllScriptSessions();
				JSONObject jobj=new JSONObject();
				jobj.element(DWR.FAQ, faq);
				 Util utilAll = new Util(sessions);
				 utilAll.addFunctionCall(DWR.SHOW_NOTICE,jobj);
			}

		}
		
		/**
		 * 拉单
		 * @param src
		 * @param map
		 */
		public static void showGetOrder(ServletContext src,Staff staff){
			String gName=staff.getGroup().getName();
			Map<String,Integer> map=(Map<String, Integer>) src.getAttribute(DWR.GET_WORK_ORDER_MAP);
			if(map!=null){
				if(map.containsKey(gName)){
					map.put(gName, map.get(gName)+1);
				}else{
					map.put(gName, 1);
				}
			}
			WebContext wctx = (WebContext) src.getAttribute( DWR.WCTX );
			if(wctx!=null){
				Collection<ScriptSession> sessions = wctx.getAllScriptSessions();//workOrderPlatform.jsp
				JSONObject jobj=new JSONObject();
				jobj.element(DWR.GET_ORDER_COUNT, getOrderCount++);
				jobj.element(DWR.ACC_NAME, staff.getNickName());
				jobj.element(DWR.GROUP_NAME, gName);
				jobj.element(DWR.GROUP_COUNT, map.get(gName));
				//jobj.element("timeMargin", staff.getGroup().getName());
				 Util utilAll = new Util(sessions);
				 utilAll.addFunctionCall(DWR.SHOW_GET_ORDER,jobj);
			}

		}
		
		/**
		 * 派单通知
		 * @param src
		 * @param map
		 */
		public static void showAssignNotice(ServletContext src,List<Long> idList,NameValuePair nvp){
			WebContext wctx = (WebContext) src.getAttribute( DWR.WCTX );
			Map<Long, ScriptSession> scriptMap=(Map<Long, ScriptSession>) src.getAttribute(DWR.SCRIPT_MAP);
			if(scriptMap==null||scriptMap.size()==0) return;
			if(wctx!=null){
				Collection<ScriptSession> sessions = new ArrayList<ScriptSession>();
				for(Long id:idList){
					ScriptSession session=scriptMap.get(id);
					if(scriptMap.containsKey(id) && !(session.isInvalidated())){
						sessions.add(session);
					}
				}
				if(sessions.size()<1) return;	
				JSONObject jobj=new JSONObject();
				jobj.element( ParamName.ID, nvp.getName());
				jobj.element( ParamName.DESCRIP, nvp.getValue());
				jobj.element( ParamName.TICKET, new Date());
				
				 Util utilAll = new Util(sessions);
				 utilAll.addFunctionCall(DWR.SHOW_ASSIGN_NOTICE,jobj);
			}

		}
		/**
		 * 发送消息
		 * @param src
		 * @param map
		 */
		public static void sendIM(ServletContext src, NameValuePair nvp){
			WebContext wctx = (WebContext) src.getAttribute( DWR.WCTX );
			
			if(wctx!=null){
				Collection<ScriptSession> sessions = wctx.getAllScriptSessions();
				
				if(sessions.size()<1) return;	
				JSONObject jobj=new JSONObject();
				jobj.element( ParamName.ID, nvp.getName());
				jobj.element( ParamName.MESSAGE, nvp.getValue());
				jobj.element( ParamName.TICKET, new Date());
				
				 Util utilAll = new Util(sessions);
				 utilAll.addFunctionCall(DWR.SHOW_IM,jobj);
			}

		}
		public static int getOrderCount=1;
}
