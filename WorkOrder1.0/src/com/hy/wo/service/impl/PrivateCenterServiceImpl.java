package com.hy.wo.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Service;

import com.hy.wo.action.WorkOrderAction;
import com.hy.wo.exception.InputErrorException;
import com.hy.wo.po.User;
import com.hy.wo.service.PrivateCenterService;
import com.hy.wo.util.Constants;
import com.hy.wo.util.MyUtil;
import com.hy.wo.util.Constants.ParamName;
import com.hy.wo.util.Constants.PromptMessage;
import com.hy.wo.util.Constants.StateList;
import com.hy.wo.util.Constants.ViewNode;

@Service 
public class PrivateCenterServiceImpl implements PrivateCenterService {
	private static final Logger LOGGER = Logger.getLogger(WorkOrderAction.class);
	 /* (non-Javadoc)
	 * @see com.hy.wo.service.impl.PrivateCenterService#getUserInfo(java.lang.String, java.lang.String)
	 */
	 public User getUserInfo(String username, String ip)  {
		 LOGGER.debug("**************获取用户扩展信息****************");
		 NameValuePair[] data = {
				 new NameValuePair(ParamName.ID, "PC_WO"),
				 new NameValuePair(ParamName.USER_ID, username),
				 new NameValuePair(ParamName.REQ, "getUserInfo"),
				 new NameValuePair(ParamName.USER_TYPE, "0"),
				 new NameValuePair(ParamName.TYPE, ViewNode.USER_INFO),
				 new NameValuePair(ParamName.S, MyUtil.hash(username + MyUtil.getClientParamKey("WO_PASSWORD"))),
				 new NameValuePair(ParamName.ID, ip)};
		 try{
				 Element root = doUserInfoPost(MyUtil.getUserinfoURLRead(), data);
				// System.out.println(root);
				 String state = root.getChildText(ViewNode.STATE);
				 User userInfo= new User();
				 if (state.equals(StateList.SUCCESS)) {
					 Element root1 = root.getChild(ViewNode.USER_INFO);
					 String phone=root1.getChildText(ViewNode.PHONE);
					 String qq=root1.getChildText(ViewNode.QQ);
					 userInfo.setEmail(root1.getChildText(ViewNode.EMAIL));
					 userInfo.setTel(MyUtil.isBlankOrNull(phone)?"":phone);//电话号码
					 userInfo.setQq(MyUtil.isBlankOrNull(qq)?"":qq);
					 userInfo.setUsername(username);
					 userInfo.setIdCard(root1.getChildText(ViewNode.ID_CARD));
				 }
				 return userInfo;
		 }catch(InputErrorException e){
			 return null;
		 } catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	 }
	 /* (non-Javadoc)
	 * @see com.hy.wo.service.impl.PrivateCenterService#doUserInfoPost(java.lang.String, org.apache.commons.httpclient.NameValuePair[])
	 */
		public Element doUserInfoPost(String targetURL, NameValuePair[] data) throws
			Exception {
			Element root = privateuserPost(targetURL, data);
			String state = root.getChildText(ViewNode.STATE);
			if (state.equals(StateList.SUCCESS)) {
				// do nothing....
			}
			else if (state.equals(StateList.USER_NOTFOUND_ERR)) {
				throw new InputErrorException(PromptMessage.USERNAME_IS_NULL);
			}
			else if (state.equals(StateList.PASSWORD_ERR)) {
				throw new InputErrorException(PromptMessage.PASSWORD_ERROR);
			}
			else {
				System.out.println("PrivateUser类 doUserInfoPost 方法 state异常发现:"+state + "    Date:"+ new Date());
				System.out.println("Target URL: "+targetURL);
//				MyUtil.showData(data);
//				MyUtil.accessElement(root);
				throw new Exception(Constants.PromptMessage.SYSTEM_BUSY);
			}
			return root;
		}
		/* (non-Javadoc)
		 * @see com.hy.wo.service.impl.PrivateCenterService#privateuserPost(java.lang.String, org.apache.commons.httpclient.NameValuePair[])
		 */
	    public Element privateuserPost(String targetURL, NameValuePair[] data) throws Exception {
	    	LOGGER.debug("*************targetURL = " + targetURL);
	    	PostMethod postMethod = null;
	    	InputStream inputstream = null;
	    	try {
	    		postMethod = new PostMethod(targetURL);
	    		postMethod.addRequestHeader( "Connection", "close");
	    		postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
	    		postMethod.setRequestBody(data);
	    		HttpClient httpclient = new HttpClient();
	    		int result = httpclient.executeMethod(postMethod);
	    		if (result == 200) {
	    			inputstream = postMethod.getResponseBodyAsStream();
	    			SAXBuilder builder = new SAXBuilder();
	    			Document doc = null;
	    			doc = builder.build(inputstream);
	    			Element root = doc.getRootElement();
					return root;
	    		}
	    		else {
	    			System.out.println("privacenter得不到正确的文件头,result:"+result+" targetURL:"+targetURL);
	    			System.out.println("at Date: "+new Date());
//	    			MyUtil.showData(data);
	    			throw new Exception("系统忙请稍候重试.");
	    		}
	    	}
	    	finally {
	    		if(inputstream!=null) {
	    			inputstream.close();
	    		}
	    		postMethod.releaseConnection();
	    	}
	    }
		 /**
		  * 更新个人资料
		  * @param map 用户信息
		  * 
		  */
		 public String UpdateUserInfo(Map<String, String> map){
			 
//			 id:xxxx					客户端 id(PC_PASSWORD)
//			req:updateUserInfo		请求类型
//			username:xxx			久游通行证
//			type:fill             	修改久游通行证详细信息
//			occupation				职业(20个英文字符,汉字的话除以2,下同)
//			education				教育(20个英文字符)	
//			marriage				婚姻(4个英文字符)	
//			constellation			星座(6个英文字符)	
//			shx						生肖(2个英文字符)		
//			city					地区(10个英文字符)		
//			address					地址(255个英文字符)		
//			phone					联系电话(20个英文字符)	
//			mobilephone				手机(20个英文字符)	
//			birthday				生日(数据库Date类型,格式为: 1983-04-19)
//			realname				真实姓名
//			qq						qq(13个英文字符)	
//			msn						msn(50个英文字符)		
//			intro					简介(1000个英文字符)
//			ip:xxx					久游通行证ip
//			s:xxxx					32位验证码, 生成方法: md5(username + 特殊字符串)
			
			 NameValuePair[] data = {
					 new NameValuePair("id", "PC_WO"),
					 new NameValuePair("req", "updateUserInfo"),
					 new NameValuePair("username", map.get("username")),
					 new NameValuePair("type", "fill"),
					 new NameValuePair("occupation", ""),
					 new NameValuePair("education", ""),
					 new NameValuePair("marriage", ""),
					 new NameValuePair("constellation", ""),
					 new NameValuePair("shx", ""),
					 new NameValuePair("city", ""),
					 new NameValuePair("address", ""),
					 new NameValuePair("phone", map.get("phone")),
					 new NameValuePair("mobilephone", ""),
					 new NameValuePair("birthday", ""),
					 new NameValuePair("realname", ""),
					 new NameValuePair("qq", map.get("qq")),
					 new NameValuePair("msn", ""),
					 new NameValuePair("instr", ""),
					 new NameValuePair("s", MyUtil.hash(map.get("username") +"58WADFE2F89SDEG")),
					 new NameValuePair("ip", "")};
//			 NameValuePair[] data = {
//					 new NameValuePair(Constants.PrivateCenterParamName.ID, PC_ID),
//					 new NameValuePair(Constants.PrivateCenterParamName.REQ, "updateUserInfo"),
//					 new NameValuePair(Constants.PrivateCenterParamName.USERNAME, map.get(ParamName.USERNAME)),
//					 new NameValuePair("type", "fill"),
//					 new NameValuePair("city", map.get("city")),
//					 new NameValuePair("address", map.get("address")),
//					 new NameValuePair("phone", map.get("phone")),
//					 new NameValuePair("mobilephone", map.get("phone")),
//					 new NameValuePair("birthday", map.get("birthday")),
//					 new NameValuePair("qq", map.get("qq")),
//					 new NameValuePair("s", MyUtil.hash(map.get(ParamName.USERNAME) + MyUtil.getPCKey(PC_ID))),
//					 new NameValuePair("ip", map.get(ParamName.USER_IP))};
			 String state = null;
			try {
				LOGGER.debug("url = " +MyUtil.getPasswordInfoURLRead());
				Element root = privateuserPost(MyUtil.getPasswordInfoURLRead(),data);
				state = root.getChildText("state");
				String stateDesc = root.getChildText("stateDesc");
				LOGGER.debug("state = "+state + "|stateDesc = " + stateDesc);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return state;
		 }
}
