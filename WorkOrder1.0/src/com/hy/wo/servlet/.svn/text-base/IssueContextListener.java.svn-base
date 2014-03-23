package com.hy.wo.servlet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.hy.wo.action.WorkOrderAction;
import com.hy.wo.dao.AsistDao;
import com.hy.wo.po.ServerInfo;
import com.hy.wo.service.WorkOrderService;
import com.hy.wo.service.impl.MonitorLockService;
import com.hy.wo.service.impl.RateService;
import com.hy.wo.util.MyUtil;
import com.hy.wo.util.Constants.DWR;
import com.you9.base.Globe;
@Service @Transactional 
public class IssueContextListener implements ServletContextListener{

	private static final long serialVersionUID = -1185237764400980248L;
	private Timer timer1 = null;
	private TimerTask task1;
	private static final Logger LOGGER = Logger.getLogger(WorkOrderAction.class);
	//@Resource WorkOrderService woService;
	public void contextDestroyed(ServletContextEvent sce) {
		 
		 LinkedList<Map<String,String>> list=(LinkedList<Map<String, String>>) sce.getServletContext().getAttribute(DWR.VIEW_LIST);
			 if(list!=null){
				 list.clear();
			 }
		if(timer1!=null)
			timer1.cancel();
	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context=sce.getServletContext();
		
		//检查是否使用workORderView
		if (Boolean.parseBoolean(MyUtil.getLaunchWOView())) {
//			saveIPSeeker(context);
			context.setAttribute(DWR.VIEW_LIST, new LinkedList<Map<String,String>>());
			context.setAttribute(DWR.GET_WORK_ORDER_MAP, new HashMap<String,Integer>());
		}

		// 启动定时器 监视锁定工单 
		if (Boolean.parseBoolean(MyUtil.getLaunchMonitor())) {
			luncherMonitor();
		}
		// 是否使用用户延时评价
		if (Boolean.parseBoolean(MyUtil.getLaunchDelayRate())) {
			luncherRate(context);
		}
		
		//更新服务器信息
		//updateServerInfo(sce);
		
}
	/**
	 * 监控工单锁定状态
	 */
	private void luncherMonitor(){
		long delay=Long.valueOf(MyUtil.getMonitorInterval());
		timer1 = new Timer(true);
		task1 = new MonitorLockService();
		timer1.schedule(task1, 1000, delay*1000);
	}
	/**
	 * 启动用户延时评价
	 * @param context 
	 */
	private void luncherRate(ServletContext context){
		long delay=Long.valueOf(MyUtil.getRateInterval());
//		timer1 = new Timer(true);
//		task1 = new RateService(context);
		new Timer(true).schedule(new RateService(context), 1000, delay*1000);
	}
	/**
	 * 更新服务器信息
	 * @param sc
	 */
	private void updateServerInfo(ServletContextEvent sce){
		LOGGER.debug("更新服务器信息...");
		//WorkOrderService woService=new WorkOrderServiceImpl();
		try {
			List<ServerInfo> list=AsistDao.getGameServers();
			System.out.println("获取到的服务器名："+list.size());
			Map<Integer,String> map=new HashMap<Integer, String>();
			for(ServerInfo info:list){
				System.out.println(info.getServerName());
				map.put(info.getServerId(), info.getServerName());
			}

			//System.out.println(map.get);
				
			//ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
			WebApplicationContext wac=(WebApplicationContext)sce.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
			WorkOrderService woService=(WorkOrderService) wac.getBean("workOrderServiceImpl");
			//System.out.println(woService);
			woService.updateServersInfo(map);
			LOGGER.debug("更新服务器信息完毕!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void saveIPSeeker(ServletContext sc){
//			String rp=sc.getRealPath(Globe.getProperty("workOrderView/path"));
//			IPSeeker ips=IPSeeker.getInstance(rp);
			//System.out.println(ips);
//			sc.setAttribute("ips",ips);
	}
}
