package com.hy.wo.service.impl;

import java.util.Map;
import java.util.Set;
import java.util.TimerTask;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

import com.hy.wo.cache.RateJCSManager;
import com.hy.wo.service.CallerService;

public class RateService extends TimerTask{
//	
	private static ServletContext cont=null;
	
	public RateService(ServletContext context){
		cont=context;
	};
//	
	@Override
	public void run() {
			try {
				doRate();
				RateJCSManager.getInstance().clearDataOutTime();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private void doRate(){
		RateJCSManager rjcs=RateJCSManager.getInstance();
		
		try {
			Map<String,String> evaMap=rjcs.getMap();
			Set<Entry<String,String>> entrySet=evaMap.entrySet();
			WebApplicationContext wac=(WebApplicationContext)cont.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
			//ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
			//context.get
			
			CallerService callerService=(CallerService) wac.getBean("callerServiceImpl");
			
			for(Entry<String,String> e:entrySet){
				callerService.doRate(e.getKey(), e.getValue());
				
			}
		} catch (Exception e) {
		}
	}
}
