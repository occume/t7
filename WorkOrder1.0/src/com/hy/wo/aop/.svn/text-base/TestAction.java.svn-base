package com.hy.wo.aop;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hy.wo.action.BaseAction;

import com.hy.wo.service.WorkOrderService;
import com.hy.wo.util.DepictXmlManager;

@Controller @Scope("prototype")
public class TestAction extends BaseAction {
	@Resource WorkOrderService woService;
	
	public void Test() {
				//System.out.println(sessionFactory);
			//	Session session=sessionFactory.getCurrentSession();
		
//				Games game=new Games();
//				game.setName("风云传奇");
			//	Session session=woService.getHSession();
			//	Transaction tc=session.beginTransaction();
//				Connection conn=session.
				System.out.println(request.getParameter("name"));
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("30 seconds past,now i am to back!");
				
				DepictXmlManager.outputString("jsonpCallback({msg:'this is json data'})",response);
		//return null;
	}
	public static void removeNull(Class clazz,int level) throws ClassNotFoundException{
//		Field[] fs=clazz.getDeclaredFields();
//		for(Field f:fs){
//			System.out.println(f.getName()+"="+level);
//			String str=f.getType().getSuperclass()==null?"":f.getType().getSuperclass().getName();
//			System.out.println(str.contains("Enum"));
			
//			if(f.getType().toString().contains("com.hy.wo.po")){
			//	String str=f.getType().getName()==null?"":f.getType().getName();
				//System.out.println(str);
//				if(!Class.forName(f.getType().getName()).getSimpleName().equals("WorkOrder"))
//					removeNull(Class.forName(f.getType().getName()),level+1);
//			}
//			
//		}
//	}
//	public static void main(String...strings) throws ClassNotFoundException, IOException{
//		WorkOrderUserInfo userInfo=new WorkOrderUserInfo();
//		WorkOrder wo=new WorkOrder();
//		wo.setUserInfo(userInfo);
//		userInfo.setWorkOrder(wo);
//		JSONObject jobj= new JSONObject();
//		ObjectMapper om=new ObjectMapper();
//		JsonGenerator gene=om.getJsonFactory().createJsonGenerator(System.out,JsonEncoding.UTF8);
//		gene.writeObject(wo);
	}
	private String makeSql(String a,String b){
		String where="";
		if(a==""&&b=="") return "";
		where+=a==""?"":"a like '%"+a+"%' and";
		where+=b==""?"":"b like '%"+b+"%' and";
		where=where.substring(0,where.lastIndexOf("and"));
		return where;
	}
	
}
