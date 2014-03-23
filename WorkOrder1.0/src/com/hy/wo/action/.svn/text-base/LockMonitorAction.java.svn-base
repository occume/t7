package com.hy.wo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hy.wo.cache.JCSManager;
import com.hy.wo.po.Staff;
import com.hy.wo.util.Constants;
import com.hy.wo.util.MyUtil;
import com.you9.base.Globe;

@Controller @Scope("prototype")
public class LockMonitorAction extends BaseAction{

	private static final long serialVersionUID = -2376683371347197704L;
	private static final Logger LOG = Logger.getLogger(LockMonitorAction.class);//定义日志
		/**
		 * 获取结果集是查询所有被锁定工单
		 * @throws Exception
		 */
		public void woIdInJCS() throws Exception{
			LOG.debug("***woIdInJCS***");
			if(!Boolean.parseBoolean(Globe.getProperty("monitorLock/switch")))return;
			Staff staff=(Staff) session.getAttribute("staff");
			
			if(staff.getRole().getId()==4){
					List<String> outList=new ArrayList<String>();
					String idz=request.getParameter("ids");
					String[] ids= MyUtil.splitString(idz, ",");//idz.split(",");
					
					Map<String,String> map=JCSManager.getInstance().getMap();
					Set<String> set=map.keySet();
					
					for(String id:ids){
						if(set.contains(id)){
							String name=JCSManager.getInstance().get(id).toString();
							if(!staff.getAccName().equals(name)){
								outList.add(id);
							}
						}
					}
					JSONObject jobj=new JSONObject();
					jobj.element("idList", outList);
					sendAjaxJson(jobj);
			}
		}
		/**
		 * 打开链接时查询是否锁定
		 * @throws Exception
		 */
		public void isLocked() throws Exception{
			if(!Globe.getProperty("monitorLock/switch").equals("true"))return;
			
			Staff staff=(Staff) session.getAttribute("staff");
			if(staff.getRole().getId()==4){
					String name=staff.getAccName();
					String result="NOLOCK";
					String id=request.getParameter("id");
										
					Map<String,String> map=JCSManager.getInstance().getMap();
					String _name=JCSManager.getInstance().get(id).toString();
					Set<String> set=map.keySet();
					if(set.contains(id)&&!name.equals(_name)){
						result="LOCKING";
					}
					sendAjaxMessage(result);
			}
		}
		/**
		 * 进入工单后检查锁定状态
		 * @throws Exception
		 */
		public void isLockedAfter() throws Exception{
			if(!Globe.getProperty("monitorLock/switch").equals("true"))return;
			Staff staff=(Staff) session.getAttribute("staff");
			
			String name=staff.getAccName();
			String result="NOLOCK";
			String id=request.getParameter("id").trim();
			
			Map<String,String> map=JCSManager.getInstance().getMap();
			String _name=JCSManager.getInstance().get(id).toString();
								
			Set<String> set=map.keySet();
			if(set.contains(id)&&name.equals(_name)){
				result="LOCKING";
			}
			sendAjaxMessage(result);
		}
		/**
		 * 工单解锁
		 * @throws Exception
		 */
		public void openLock() throws Exception{
			if(!Globe.getProperty("monitorLock/switch").equals("true"))return;
			String result="NOLOCK";
			String id=request.getParameter("id").trim();
			
			Map<String,String> map=JCSManager.getInstance().getMap();
								
			Set<String> set=map.keySet();
			if(set.contains(id)){
				JCSManager.getInstance().remove(id);
				result="OK";
			}
			sendAjaxMessage(result);
		}
		/**
		 * 响应异步请求  json
		 * @param message
		 */
		private void sendAjaxJson(JSONObject jobj){
			response.setContentType( Constants.DEFAULT_CONTENT_TYPE );
		    response.setCharacterEncoding( Constants.DEFAULT_CHARSET );
		    
			PrintWriter out=null; 
			try
			{
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}  
			out.print(jobj.toString());
			out.flush();
			out.close();
		}
		/**
		 * 响应异步请求 no json
		 * @param message
		 */
		private void sendAjaxMessage(String message){
			response.setContentType( Constants.DEFAULT_CONTENT_TYPE );
		    response.setCharacterEncoding( Constants.DEFAULT_CHARSET );
		    
		    JSONObject jobj=new JSONObject();
		    jobj.element("message",message);
			PrintWriter out=null; 
			try
			{
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}  
			out.print(jobj.toString());
			out.flush();
			out.close();
		}
}
