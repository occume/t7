package com.hy.wo.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.hy.wo.cache.RateJCSManager;
import com.hy.wo.po.User;
import com.hy.wo.service.PrivateCenterService;
import com.hy.wo.service.impl.PrivateCenterServiceImpl;
import com.hy.wo.util.InnerGameLimit;
import com.hy.wo.util.MyUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.you9.base.Globe;

public class IssueInterceptor implements Interceptor{

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		//System.out.println("inteceptor");
		ActionContext actionContext = invocation.getInvocationContext();  
		
		HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		ServletContext servletContext=(ServletContext) actionContext.get(StrutsStatics.SERVLET_CONTEXT);
		
		request.setCharacterEncoding("UTF-8");
		
		String IssueURI=request.getRequestURI();
	
		if(IssueURI.indexOf("wo")>=0 && IssueURI.indexOf("save")>=0){
				if((!MyUtil.isBlankOrNull(request.getParameter("source")))&&(request.getParameter("source").equals("2"))){
						String accName=(String) request.getSession().getAttribute("accName");
						RateJCSManager jcs=RateJCSManager.getInstance();
						InnerGameLimit limit=(InnerGameLimit) jcs.get(accName);
						
						if(limit==null){
							InnerGameLimit limi=new InnerGameLimit();
							limi.setAccName(accName);
							jcs.put(accName, limi);
						}else{
						if(limit.getLockState().equals("LOCKING")){
								Date lastLockTDate=limit.getLastLockTime();
								long lastLong=lastLockTDate.getTime();
								long gap=System.currentTimeMillis()-lastLong;
								if(gap>60*60*1000){
									limit.setLockState("UNLOCK");
									//save
									jcs.put(accName, limit);
								}else{
									request.setAttribute("notEnough", "continue wait");//锁定
								}
						}else{
								if(limit.getCount()==0){
									limit.setLastSubmitTime(new Date());
									limit.setCount(limit.getCount()+1);
									//save
									jcs.put(accName, limit);
									System.out.println("第一次放行");
								}else{
									Date lastSubDate=limit.getLastSubmitTime();
									long lastLong=lastSubDate.getTime();
									long gap=System.currentTimeMillis()-lastLong;
									if(gap<60*1000){
											if(limit.getCount()<=3){
												limit.setCount(limit.getCount()+1);
												System.out.println("一分钟内提交次数："+limit.getCount());
											}else{
												limit.setLockState("LOCKING");
												limit.setLastLockTime(new Date());
												request.setAttribute("notEnough", "continue wait");//锁定
												limit.setCount(0);
												System.out.println("锁定："+limit.getCount());
											}
										//save
											jcs.put(accName, limit);
									}else{
										limit.setCount(0);
										//save
										jcs.put(accName, limit);
									}
								}
						}
						}
				}else{
					doUpdateUserInfo(request);//如果玩家没有保存过联系方式 ，则保存
					if(IssueURI.indexOf("accountlock")<0){
						doLookIfLimit(request,servletContext);//提交问题时间限制，2分钟间隔
					}else{
						doLookIfLimitWithoutLogin(request,servletContext);
					}
				}
		}
		
		return invocation.invoke();
	}
	
	//未登录 问题提交时间限制
	private void doLookIfLimitWithoutLogin(HttpServletRequest request,ServletContext sc){
																//	System.out.println("账号被封");
			PrivateCenterService pcService=new PrivateCenterServiceImpl();
			String accName=request.getParameter("userInfo.accountname");
			Map<String, Date> macMap=null;
			//request=sre.getServletContext().get
			User user=null;
			try {
			//	System.out.println(accName);
				user =MyUtil.isAccountExsit(request);//pcService.getUserInfo(accName, request.getRemoteAddr());
															//		System.out.println("user="+user);
			} catch (Exception e) {e.printStackTrace();}
			
			if(user==null){
					request.setAttribute("notExist", "true");
			}else{
				String IDC=user.getIdCard();
				macMap=(Map<String, Date>) sc.getAttribute("macMap");
				
				if(macMap.containsKey(IDC)){
					int timeGap=Integer.valueOf(Globe.getProperty("limitSubmit/timeGap"));
					if(!MyUtil.isWaitEnough(macMap.get(IDC), timeGap*60000)){
						//不允许提交
						LOG.debug("提交问题冷却时间未到："+IDC);
						request.setAttribute("notEnough", "continue wait");
						request.setAttribute("IDC", IDC);
					}
				}else{
					
					macMap.put(IDC, new Date());
				}
			}
			
	}
	//对需要登录提交的问题，作时间限制
	private void doLookIfLimit(HttpServletRequest request,ServletContext sc) {
		Map<String, Date> macMap=(Map<String, Date>) sc.getAttribute("macMap");
		if(macMap!=null){
		//	String ip=MyUtil.getIPAddress(request);
		//	String ip=request.getRemoteAddr();
		//	String mac=MyUtil.getMacAddress(ip);//;("192.168.179.29");
			//System.out.println("mac:::"+mac);
			String IDC=request.getParameter("IDC");
			//System.out.println("身份证号："+IDC);
			if(macMap.containsKey(IDC)){
				int timeGap=Integer.valueOf(Globe.getProperty("limitSubmit/timeGap"));
				if(!MyUtil.isWaitEnough(macMap.get(IDC), timeGap*60000)){
					//不允许提交
					LOG.debug("提交问题冷却时间未到："+IDC);
					LOG.debug("目前有："+macMap.size()+"...冷却中");
					request.setAttribute("notEnough", "continue wait");
				}
			}else{
				macMap.put(IDC, new Date());
				//System.out.println("保存一个新的IDC"+IDC);
			}
		}
	}
	//对未保存联系方式的玩家，自动保存联系方式
	private void doUpdateUserInfo(HttpServletRequest request) {
		 Map<String, String> map=new HashMap<String, String>();
		
		 if(request.getParameter("userInfo.accountname")!=null){
			 	map.put("username", request.getParameter("userInfo.accountname"));
			 	String tel=request.getParameter("userInfo.tel");
			 	
			 	if(tel!=null){
			 		map.put("phone", tel);
			 	}else{
			 		map.put("phone", "");
			 	}
			 	String qq=request.getParameter("userInfo.qq");
			 	if(qq!=null){
			 		map.put("qq", qq);
			 	}else{
			 		map.put("qq", "");
			 	}
			 	if(tel!=null && qq!=null){
				 	PrivateCenterService pcService=new PrivateCenterServiceImpl();
				 	String result=pcService.UpdateUserInfo(map);
			 	}
			 	
		 }
	}
	private static final Logger LOG = Logger.getLogger(IssueInterceptor.class);//定义日志
	private static final long serialVersionUID = 1285536698782766365L;
}
