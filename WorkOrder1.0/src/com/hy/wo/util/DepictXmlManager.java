package com.hy.wo.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;

import com.hy.wo.dto.userWOListDTO;
import com.hy.wo.po.Caller;
import com.hy.wo.po.Faq;
import com.hy.wo.po.Goods;
import com.hy.wo.po.IssueAdditional;
import com.hy.wo.po.SubCallBack;
import com.hy.wo.po.WorkOrder;
import com.hy.wo.po.WorkOrderOper;
import com.hy.wo.po.WorkOrderRecharge;
import com.hy.wo.po.WorkOrderUserInfo;

/**
 * DepictXmlManager
 * 组织XML格式的输出信息
 */
public abstract class DepictXmlManager {
    private static final Logger LOGGER         = Logger.getLogger(DepictXmlManager.class);

    /**
     * 组织基本的XML格式的字符串（问题提交响应）
     * @param state     操作状态
     * @param stateDesc 操作状态描述
     * @return  XML格式的字符串
     */
    public static String organizeBasicXML(String state, String stateDesc,String clientId){
    	//LOGGER.debug("输出XML格式的organizeBasicXML ");
    	StringBuffer sbf = new StringBuffer();
    	
    	sbf.append("<id>" + clientId +"</id>\n");
    	
    	return sbf.toString()+organizeBasicXML(state, stateDesc);
    }
    public static String oneRowXML(String...strings){
    	//LOGGER.debug("输出XML格式的organizeBasicXML ");
    	if(strings.length<=0) return "";
    	StringBuffer sbf = new StringBuffer();
    	for(String str:strings){
    		sbf.append("<username>" + str +"</username>\n");
    	}
    	return sbf.toString();
    }
    /**
     * 组织基本的XML格式的字符串（问题提交响应）
     * @param state     操作状态
     * @param stateDesc 操作状态描述
     * @return  XML格式的字符串
     */
    public static String organizeBasicXML(String state, String stateDesc){
    	//LOGGER.debug("输出XML格式的organizeBasicXML ");
    	StringBuffer sbf = new StringBuffer();
    	sbf.append("<" + Constants.ViewNode.STATE + ">" + state +"</" + Constants.ViewNode.STATE + ">\n");
    	sbf.append("<" + Constants.ViewNode.STATE_DESC + ">" + stateDesc + "</" + Constants.ViewNode.STATE_DESC + ">\n");
    	return sbf.toString();
    }
    public static String makeListInfoXML(userWOListDTO dto){
    	LOGGER.debug("输出XML格式的organizeBasicXML  总数："+dto.getTotalCount());
    //	if(dto.getDealCount()<=0) return "";
    	StringBuffer sbf = new StringBuffer();
    	sbf.append("<dto>");
	    	if(dto.getUndealCount()>0){
	        	sbf.append(makePagesXML(dto.getUndealList(),"undeal",dto.getUndealCount()));
	    	}
	    	if(dto.getDelingCount()>0){
	    		sbf.append(makePagesXML(dto.getDelingList(),"dealing",dto.getDelingCount()));
	    	}
	    	if(dto.getReplyCount()>0){
	    		sbf.append(makePagesXML(dto.getReplyList(),"dealed",dto.getReplyCount()));
	    	}
	    	if(dto.getLackinfoCount()>0){
	    		sbf.append(makePagesXML(dto.getLackinfoList(),"lack",dto.getLackinfoCount()));
	    	}
	    	if(dto.getDealCount()>0){
	    		sbf.append(makePagesXML(dto.getDealList(),"all",dto.getDealCount()));
	    	}
	    sbf.append("</dto>");
    	return sbf.toString();
    }
    public static String makePagesXML(Pages<WorkOrder> pages,String type,int count){
    	//LOGGER.debug("输出XML格式的organizeBasicXML ");
    	if(count<0) return "";
    	if(pages==null) return "";
    	StringBuffer sbf = new StringBuffer();
    	sbf.append("<" + type + ">");
    	sbf.append("<count>" + count+"</count>\n");
    	sbf.append("<pages>");
    	sbf.append("<currentPage>" + pages.getCurrentPage() +"</currentPage>\n");
    	sbf.append("<totalPage>" + pages.getTotalPage() +"</totalPage>\n");
    	sbf.append("<lastPage>" + pages.getLastPage() +"</lastPage>\n");
    	sbf.append("<pageSize>" + pages.getPageSize() +"</pageSize>\n");
    	sbf.append("<totalCount>" + pages.getTotalCount() +"</totalCount>\n");
    	for(WorkOrder wo:pages.getResultList()){
    		sbf.append("<workOrder>");
    		sbf.append("<id>" + wo.getId() +"</id>\n");
    		sbf.append("<accountname>" + wo.getUserInfo().getAccountname() +"</accountname>\n");
    		sbf.append("<createTime>" + wo.getCreateTime().getTime() +"</createTime>\n");
    		sbf.append("<memo>" + wo.getUserInfo().getMemo() +"</memo>\n");
    		if(wo.getUserInfo().getLvlOne()!=null)
    			sbf.append("<lvlOne>" + wo.getUserInfo().getLvlOne().getName()+"</lvlOne>\n");
    		else
    			sbf.append("<lvlOne>---</lvlOne>\n");
    		if(wo.getUserInfo().getServer()!=null)
    			sbf.append("<server>" + wo.getUserInfo().getServer().getName()+"</server>\n");
    		else
    			sbf.append("<server>---</server>\n");
    		sbf.append("<username>" + wo.getUserInfo().getUsername()+"</username>\n");
    		sbf.append("<read>" + wo.isIsreaded()+"</read>\n");
    		sbf.append("</workOrder>");
    	}
    	sbf.append("</pages>");
    	sbf.append("</" + type + ">");
    	//System.out.println(sbf);
    	return sbf.toString();
    }
    /**
     * 工单详情XML
     * @param pages
     * @param type
     * @param count
     * @return
     */
    public static String makeWorkOrderXML(WorkOrder wo){
    	//LOGGER.debug("输出XML格式的  WorkOrderXML ");
    	if(wo==null) return"";
    	StringBuffer sbf = new StringBuffer();
    
    		sbf.append("<workOrder>");
    		sbf.append("<id>" + wo.getId() +"</id>\n");
    		sbf.append("<createTime>" + wo.getCreateTime().getTime() +"</createTime>\n");
    		sbf.append("<memo>" + wo.getUserInfo().getMemo() +"</memo>\n");
    		sbf.append("<lvlOne>" + wo.getUserInfo().getLvlOne().getName()+"</lvlOne>\n");
    		sbf.append("<states>" + wo.getStates()+"</states>\n");
    		sbf.append("<accName>" + wo.getUserInfo().getAccountname()+"</accName>\n");
    		sbf.append("<userName>" + (MyUtil.isBlankOrNull(wo.getUserInfo().getUsername())?"":wo.getUserInfo().getUsername())+"</userName>\n");
    		sbf.append("<read>" + wo.isIsreaded()+"</read>\n");
    	//	LOGGER.debug("B O ");
    		if(wo.getIssueAddSet().size()>0){
    			sbf.append("<addSet>");
    			for(IssueAdditional add:wo.getIssueAddSet()){
    				if(add.isFromUser()){
    					sbf.append("<issueAdd>");
    					sbf.append("<addDate>"+add.getAddDate().getTime()+"</addDate>");
    					sbf.append("<content>"+add.getContent()+"</content>");
    					sbf.append("</issueAdd>");
    				}
    			}
    			sbf.append("</addSet>");
    		}
    		if(wo.getOpers().size()>0){
    			sbf.append("<replySet>");
    			for(WorkOrderOper oper:wo.getOpers()){
    				if(oper.getOperType().getId()==1){
    					sbf.append("<oper>");
    					sbf.append("<worker>"+oper.getWorker()+"</worker>");
    					sbf.append("<content>"+oper.getContent()+"</content>");
    					sbf.append("<operTime>"+oper.getOperTime().getTime()+"</operTime>");
    					sbf.append("<states>"+wo.getStates()+"</states>");
    					sbf.append("</oper>");
    				}
    			}
    			sbf.append("</replySet>");
    		}
    		if(wo.getAdditional()!=null&&wo.getAdditional().getGoodsSet().size()>0){
    			sbf.append("<goodsSet>");
    			for(Goods g:wo.getAdditional().getGoodsSet()){
    				
    					sbf.append("<goods>");
    					sbf.append("<name>"+g.getName()+"</name>");
    					sbf.append("<attr>"+g.getAttr()+"</attr>");
    					sbf.append("<count>"+g.getCount()+"</count>");
    					sbf.append("</goods>");
    			
    			}
    			sbf.append("</goodsSet>");
    		}
    		
    		if(wo.getAdditional()!=null){
    			sbf.append("<additional>");
    					sbf.append("<purpleGold>"+wo.getAdditional().getPurpleGold()+"</purpleGold>");
    					sbf.append("<gameCoin>"+wo.getAdditional().getGameCoin()+"</gameCoin>");
    					sbf.append("<currentVersion>"+wo.getAdditional().getCurrentVersion()+"</currentVersion>");
    			sbf.append("</additional>");
    		}
    		
    		if(wo.getDevice()!=null){
    			sbf.append("<device>");
    					sbf.append("<cpu>"+wo.getDevice().getCpu()+"</cpu>");
    					sbf.append("<memory>"+wo.getDevice().getMemory()+"</memory>");
    					sbf.append("<displaycard>"+wo.getDevice().getDisplaycard()+"</displaycard>");
    					sbf.append("<os>"+wo.getDevice().getOs()+"</os>");
    					sbf.append("<netinfo>"+wo.getDevice().getNetinfo()+"</netinfo>");
    			sbf.append("</device>");
    		}
    	//	LOGGER.debug("dO ");
    		WorkOrderRecharge rechage=wo.getRecharge();
    		if(rechage!=null){
    			sbf.append("<recharge>");
    					sbf.append("<rechargeType>"+rechage.getRechargeType()+"</rechargeType>");
    					sbf.append("<card>"+rechage.getCard()+"</card>");
    					sbf.append("<sum>"+rechage.getSum()+"</sum>");
    					sbf.append("<rechargeErrType>"+rechage.getRechargeErrorType()+"</rechargeErrType>");
    			sbf.append("</recharge>");
    		}
    	//	LOGGER.debug("rO ");
    		if(wo.getEvaluation()!=null){
    			sbf.append("<evaluation>"+wo.getEvaluation().getName()+"</evaluation>");
    		}
    		sbf.append("<comment>"+wo.getComment()+"</comment>");
    		sbf.append("</workOrder>");
    	//	LOGGER.debug("E O ");
    	return sbf.toString();
    }
    /**
     * 用户提交问题当前处理情况
     * @param accName
     * @param c1
     * @param c2
     * @param c3
     * @param c4
     * @param c5
     * @return
     */
    public static String makeDealInfo(String accName,int c1,int c2,int c3,int c4,int c5){
    	StringBuffer sbf = new StringBuffer();
    	sbf.append("<count>");
    	sbf.append("<allCount>"+c1+"</allCount>");
    	sbf.append("<dealingCount>"+c2+"</dealingCount>");
    	sbf.append("<responsedCount>"+c3+"</responsedCount>");
    	sbf.append("<undealCount>"+c4+"</undealCount>");
    	sbf.append("<lackInfoCount>"+c5+"</lackInfoCount>");
    	sbf.append("</count>");
    	return sbf.toString();
    }
    /**
     * faq
     * @param pages
     * @return
     */
    public static String makeFaqListXml(Pages<Faq> pages){
    	StringBuffer sbf = new StringBuffer();
    	sbf.append("<pages>");
    	sbf.append("<currentPage>" + pages.getCurrentPage() +"</currentPage>\n");
    	sbf.append("<totalPage>" + pages.getTotalPage() +"</totalPage>\n");
    	sbf.append("<lastPage>" + pages.getLastPage() +"</lastPage>\n");
    	sbf.append("<pageSize>" + pages.getPageSize() +"</pageSize>\n");
    	sbf.append("<totalCount>" + pages.getTotalCount() +"</totalCount>\n");
    	List<Faq> list=pages.getResultList();
    	if(list.size()>0){
	    	sbf.append("<faqList>");
	    	for(Faq faq:list){
	    		sbf.append("<faq>");
	    		sbf.append("<id>"+faq.getId()+"</id>");
	    		sbf.append("<title>"+faq.getTitle()+"</title>");
	    		sbf.append("</faq>");
	    	}
	    	sbf.append("</faqList>");
    	}
    	sbf.append("</pages>");
    	return sbf.toString();
    }
    /**
     * 发送请求结果字符串给游戏服务器
     * @param resultAuInfo 输出的字符串
     * @param response   HttpServletResponse对象
     */
    public static void outputString(String resultAuInfo, HttpServletResponse response){
  //  	LOGGER.debug("enter outputStr ");
        PrintWriter out = null;
//        StringBuffer sb = new StringBuffer();
        response.setContentType("text/xml;charset=UTF-8");   
	    response.setCharacterEncoding("UTF-8"); 
//	 //   LOGGER.debug("do outputStr ");
        try {
//        	
//            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
//            sb.append("<" + Constants.ViewNode.HY + ">\n");
//            sb.append(resultAuInfo);
//            sb.append("</" + Constants.ViewNode.HY + ">\n");
            out = response.getWriter();
        } catch (IOException e) {
//            LOGGER.error("输出XML格式的字符串出错 xml = " + resultAuInfo, e);
        }      
        
      //  System.out.println("workOrder for callCenter\n"+resultAuInfo);
        
        out.println(resultAuInfo);
        out.flush();
        out.close();
        out = null;
    }
    /**
     * 发送请求结果字符串给游戏服务器
     * @param resultAuInfo 输出的字符串
     * @param response   HttpServletResponse对象
     */
    public static void outputStr(String resultAuInfo, HttpServletResponse response){
  //  	LOGGER.debug("enter outputStr ");
        PrintWriter out = null;
        StringBuffer sb = new StringBuffer();
        response.setContentType("text/xml;charset=UTF-8");   
	    response.setCharacterEncoding("UTF-8"); 
	 //   LOGGER.debug("do outputStr ");
        try {
        	
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            sb.append("<" + Constants.ViewNode.HY + ">\n");
            sb.append(resultAuInfo);
            sb.append("</" + Constants.ViewNode.HY + ">\n");
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("输出XML格式的字符串出错 xml = " + resultAuInfo, e);
        }      
        
      //  System.out.println("workOrder for callCenter\n"+resultAuInfo);
        
        out.println(sb.toString());
        out.flush();
        out.close();
        out = null;
    }
    /**
     * 发送请求结果字符串给游戏服务器
     * @param resultAuInfo 输出的字符串
     * @param response   HttpServletResponse对象
     */
    public static String getOutputStr(String resultAuInfo){
        StringBuffer sb = new StringBuffer();
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            sb.append("<" + Constants.ViewNode.HY + ">\n");
            sb.append(resultAuInfo);
            sb.append("</" + Constants.ViewNode.HY + ">\n");
        return sb.toString();
    }
	@SuppressWarnings("unchecked")
	public static String makeMapXml(Map map) {
		if(map==null||map.size()<=0) return "";
		StringBuffer sbf = new StringBuffer();
		Set<Entry> set=map.entrySet();
		for(Entry e:set){
			sbf.append("<entry>");
			sbf.append("<key>"+e.getKey()+"</key>");
			sbf.append("<value>"+e.getValue()+"</value>");
			sbf.append("</entry>");
		}
		return sbf.toString();
	}
	public static String makeFaqXml(Faq faq) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("<faq>");
			sbf.append("<id>"+faq.getId()+"</id>");
			sbf.append("<title>"+faq.getTitle()+"</title>");
			sbf.append("<type>"+faq.getType()+"</type>");
			sbf.append("<gameName>"+faq.getGameName()+"</gameName>");
			sbf.append("<createTime>" + faq.getCreateTime().getTime() +"</createTime>\n");
			sbf.append("<descrip>" + faq.getDescrip()+"</descrip>\n");
		sbf.append("</faq>");
		return sbf.toString();
	}
	/**
	 * 电话工单用户信息
	 */
	public static String makeCallerXml(List<Caller> list){
		
		if(list==null||list.size()<1) return "";
		
		//String account=
		String accounts="";
		String userName="";
		String userType="";
		
		for(Caller c:list){
			accounts+=c.getAccName()+",";
			if(MyUtil.isBlankOrNull(userName)){
				//System.out.println("username="+userName);
				userName=c.getUserName();
				//System.out.println("username 111="+userName);
			}
			if(MyUtil.isBlankOrNull(userType)){
				userType=c.getUserType();
			}
		}
		
		StringBuffer sbf = new StringBuffer();
		sbf.append("<result>");
			sbf.append("<accounts>"+accounts+"</accounts>");
			sbf.append("<username>"+userName+"</username>");
			sbf.append("<usertype>"+userType+"</usertype>");
		sbf.append("</result>");
		return sbf.toString();
	}
	//CallBack
	public static String makeCallBackId(long cbId) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("<callbackid>");
			sbf.append(cbId);
		sbf.append("</callbackid>");
		return sbf.toString();
	}
	public static String makeCallBackList(List<Object[]> list) {
		if(list==null||list.size()<=0) return "";
		StringBuffer sbf = new StringBuffer();
		sbf.append("<result>");
			for(Object[] o:list){
				sbf.append("<callback>");
				sbf.append("<id>"+o[0]+"</id>");
				sbf.append("<title>"+o[1]+"</title>");
				sbf.append("<attime>"+o[2]+"</attime>");
				sbf.append("<operator>"+o[3]+"</operator>");
				sbf.append("</callback>");
			}
		sbf.append("</result>");
		return sbf.toString();
	}
	public static String makeSubCallBackList(Pages<SubCallBack> pages) {
		if(pages==null) return "";
		StringBuffer sbf = new StringBuffer();
    	sbf.append("<pages>");
    	sbf.append("<currentPage>" + pages.getCurrentPage() +"</currentPage>\n");
    	sbf.append("<totalPage>" + pages.getTotalPage() +"</totalPage>\n");
    	sbf.append("<lastPage>" + pages.getLastPage() +"</lastPage>\n");
    	sbf.append("<pageSize>" + pages.getPageSize() +"</pageSize>\n");
    	sbf.append("<totalCount>" + pages.getTotalCount() +"</totalCount>\n");
    	List<SubCallBack> list=pages.getResultList();
    	if(list.size()>0){
	    	sbf.append("<cblist>");
	    	for(SubCallBack scb:list){
	    		sbf.append("<scb>");
	    		sbf.append("<id>"+scb.getId()+"</id>");
	    		sbf.append("<account>"+scb.getAccount()+"</account>");
	    		sbf.append("<caller>"+(MyUtil.isBlankOrNull(scb.getCaller())?"null":scb.getCaller())+"</caller>");
	    		sbf.append("<state>"+scb.getState()+"</state>");
	    		sbf.append("<username>"+(MyUtil.isBlankOrNull(scb.getUsername())?"null":scb.getUsername())+"</username>");
	    		sbf.append("<content>"+scb.getContent()+"</content>");
	    		sbf.append("<operator>"+scb.getOperator()+"</operator>");
	    		sbf.append("<callbacktime>"+(scb.getCallbacktime()==null?"null":scb.getCallbacktime().getTime())+"</callbacktime>");
	    		sbf.append("</scb>");
	    	}
	    	sbf.append("</cblist>");
	    	sbf.append("</pages>");
    	}
    	return sbf.toString();
	}
	public static String makeSubCallBack(SubCallBack scb) {
		if(scb==null) return "";
		StringBuffer sbf = new StringBuffer();
		sbf.append("<scb>");
		sbf.append("<id>"+scb.getId()+"</id>");
		sbf.append("<account>"+scb.getAccount()+"</account>");
		sbf.append("<caller>"+(MyUtil.isBlankOrNull(scb.getCaller())?"null":scb.getCaller())+"</caller>");
		sbf.append("<state>"+scb.getState()+"</state>");
		sbf.append("<username>"+scb.getUsername()+"</username>");
		sbf.append("<content>"+scb.getContent()+"</content>");
		sbf.append("<operator>"+scb.getOperator()+"</operator>");
		sbf.append("<callbacktime>"+(scb.getCallbacktime()==null?"null":scb.getCallbacktime().getTime())+"</callbacktime>");
		sbf.append("</scb>");
    	return sbf.toString();
	}
	public static String makeWorkOrderUserInfoXml(List<WorkOrderUserInfo> list,NameValuePair nv) {
		if(list==null||list.size()<1) return "";
		StringBuffer sbf = new StringBuffer();
			sbf.append("<userInfo>");
			for(WorkOrderUserInfo scb:list){
				sbf.append("<user>");
					sbf.append("<account>"+scb.getAccountname()+"</account>");
					sbf.append("<username>"+scb.getUsername()+"</username>");
					sbf.append("<level>"+scb.getLevel()+"</level>");
					sbf.append("<class>"+(scb.getClassCategory()==null?"":scb.getClassCategory().getName())+"</class>");
					sbf.append("<serverId>"+nv.getName()+"</serverId>");
					sbf.append("<serverName>"+nv.getValue()+"</serverName>");
				sbf.append("</user>");
			}
		sbf.append("</userInfo>");
    	return sbf.toString();
	}
	
}
