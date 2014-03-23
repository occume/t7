package com.hy.wo.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import static com.hy.wo.util.Constants.States.*;

import com.hy.wo.po.FileUpLoad;
import com.hy.wo.po.User;
import com.hy.wo.po.WorkOrder;
import com.hy.wo.service.PrivateCenterService;
import com.hy.wo.service.impl.PrivateCenterServiceImpl;
import com.hy.wo.util.Constants.GlobalNodeName;
import com.you9.base.Globe;
import com.you9.base.util.HashUtil;

/**
 * 工具类
 */
public class MyUtil {
	private static final Logger LOGGER = Logger.getLogger(MyUtil.class);

	public static final int DEFAULT_WAIT_SEC = 5;
	
	private static MessageDigest digest = null;
	/**
	 * 获取非配给WOCS的客户端ID
	 */
	public static String getserviceInfo(String serviceName, String infoType) {
		//LOGGER.debug("getserviceInfo[serviceId="+serviceName + "|infoType" + infoType + "|path:"+(Constants.GlobalNodeName.GENERAL + serviceName + infoType)+"]");
		return Globe.getProperty(Constants.GlobalNodeName.GENERAL + serviceName + infoType);
	}
	/**
	 * 判断字符串是否存在于这个字符串数组里
	 * 
	 * @param columns
	 * @param type
	 * @return boolean [true 存在，false 不存在]
	 */
	public static boolean indexOfArray(String[] columns, String type) {
		boolean isInColumnList = false;
		for (int i = 0; i < columns.length; i++) {
			if (type.equals(columns[i])) {
				isInColumnList = true;
				break;
			}
		}
		return isInColumnList;
	}
	
	 /**
     * 获取配置文件中的常量信息
     * @param regPath
     * @return
     */
    public static String getGlobeString(String regPath){
    	return Globe.getProperty(regPath);
    }
    
	public static String[] splitString(String oldStr, String separators){
		String[] resultStr = {oldStr};
		if(oldStr.indexOf(separators)!= -1){//包含标识符
			resultStr = oldStr.split(separators);
		}
		return resultStr;
	}
	/**
	 * 正则表达式校验
	 * @param reg 正则表达式
	 * @param str 待校验字符串
	 * @return false代表被校验的字符串符合正则表达式 true代表被校验的字符串不符合正则表达式
	 */
	public static boolean doRegularExpression(String reg, String str) {
		//LOGGER.debug("reg = "+reg);
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		LOGGER.debug("m matches = "+m.matches());
		return m.matches();
	}
	
	/**
     * 获取配置文件中的正则表达式
     * @return 用户名的正则表达式
     */
    public static String getRegExpress(String regPath) {
    	//LOGGER.debug("get reg path = " + regPath);
        return Globe.getProperty(regPath);
    }
    
    /**
	 * 返回state对应的描述信息
	 * 
	 * @param state
	 *            状态
	 * @return String 状态描述
	 */
	public static String getStateDesc(String state) {
		return Globe.getProperty(Constants.GlobalNodeName.STATE + state);
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public static Object isNull(Object object){
		return object == null?null:object;
	}
	
	/**
	 * 检查提交的日期是否大于当前日期
	 * @param happenDate 提交的日期
	 * @return [提交的日期小于当前日期为true,否则为false]
	 */
    public static boolean happenDateThan(String happenDate){
    	boolean dateState = false;
    	try {
    		//LOGGER.debug(" happenDateThan happendate = "+happenDate);
			Date date = (Date)Constants.sdf.parse(happenDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
            long dateLong = calendar.getTimeInMillis();
            if(System.currentTimeMillis()>dateLong){//当前日期大于提交的日期
            	dateState = true;
            }else{
            	dateState = false;
            }
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//LOGGER.debug("happenDateThan="+dateState);
		return dateState;
    }
    /**
     * 验证带固定格式的日期
     * @param 日期
     * @param 格式
     * @return true
     */
	public static boolean dateCheck(String dateValue,String pattern){
		//LOGGER.debug("*************dateValue:"+dateValue+"*********");
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		try {
			sdf.parse(dateValue);
			return true;
		} catch (java.text.ParseException e) {
			return false;
		}
		
	}
	
	/**
	 * 根据操作人的权限判断其是否有批量操作权限
	 * @param userLevel
	 * @return 
	 */
	public static boolean isBatchOperationPer(int userLevel){
		boolean result = false;
		if(userLevel >Constants.RoleLevel.MANAGER_ROLE){
			result = true;
		}
		return result;
	}
	/**
	 * 截取字符串并且格式化
	 * @param s
	 * @param 截取长度
	 * @param 超出制定长度后符号
	 * @param 如果为空默认显示字符
	 * @return
	 */
	public static String SubString(String s,int length,String tail,String emptyMsg){
		
		if(s == null || s.isEmpty())
		{
			return emptyMsg;
		}
		
		if (s.length()<length)
		{
			return s;
		}
		
		if (s.length()>length)
		{
			if (tail == null || tail.isEmpty())
			{
				return s.substring(0,length);
			}else
			{
				return s.substring(0,length)+tail;
			}
		}		
		return s;
	}
	
	/**
	 * 检查玩家是否有补充资料的权限
	 * [说明：工单状态为“处理中”玩家的补充资料的次数在3次，工单状态为“待补充资料”玩家的补充资料的次数在4次]
	 * @param states	工单状态
	 * @param addMemoNumber 玩家补充资料的次数
	 * @return
	 */
	public static boolean checkAddMemoStates(String states, int addMemoNumber){
		boolean addMemoState = false;
		if(((DEALING.equals(states)||UNDEAL.equals(states))&&addMemoNumber<=3)||LACKINFO.equals(states)||RESPONSED.equals(states)){
			addMemoState = true;
		}
		return addMemoState;
	}
	public  static boolean Toboolean(String name)
	{
		return ((name != null) && name.equalsIgnoreCase("true"));

	}
	public static Date getHiddenDate(){
		long gap=15*24*3600*1000;
		long hide=System.currentTimeMillis()-gap;
		Date hideDate=new Date(hide);
		return hideDate;
	}
	
	/**
	 * 获得SSO客户端ID
	 * @return
	 */
//	public static String getSSOId(){
//		try{
//            return Globe.getProperty("ssoInfo/clientId");
//        }catch(Exception e){
//            return "SSO_PASSWORD";
//        }
//	}
	/**
   * 对指定字符串进行MD5加密
   * @param data
   * @return string: MD5加密后的字符串
   */
	public static final synchronized String hash(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			}
			catch (NoSuchAlgorithmException nsae) {
				nsae.printStackTrace();
			}
		}
		try {
			digest.update(data.getBytes("utf-8"));
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeHex(digest.digest());
  	}
	/**
	   * 转换byte数组至16进制
	   * @param bytes
	   * @return string
	   */
	  	public static final String encodeHex(byte bytes[]) {
	  		StringBuffer buf = new StringBuffer(bytes.length * 2);
	  		for (int i = 0; i < bytes.length; i++) {
	  			if ( (bytes[i] & 0xff) < 16) {
	  				buf.append("0");
	  			}
	  			buf.append(Long.toString(bytes[i] & 0xff, 16));
	  		}

	  		return buf.toString();
	  	}
	
	 /**
	 * 获胜访问SSO的路径
	 * @param reqType 接口类型 
	 * @return
	 */
	public static String getSSOURL(String reqType){
		try{
			return Globe.getProperty("ssoInfo/postPath/" +reqType);
		}catch(Exception e){
			System.out.println("得不到请求SSO的域名.");
			return null;
		}
	}
	/**
    * 获得SSO客户端ID密钥
    * @return
    */
	public static String getSSOKey(){
		 try{
	            return Globe.getProperty("ssoInfo/paramKey");
	        }catch(Exception e){
	            return "OPWEIDCNVBEFYUHCLKFN";
	        }
    }
	/**
	    * 获得CallCenter客户端ID密钥
	    * @return
	    */
	public static String getCallCenterKey() {
		
		 try{
			 	return 	getserviceInfo(Constants.GlobalNodeName.WO_CALL_CENTER, Constants.GlobalNodeName.PARAM_KEY);
	           // return Globe.getProperty("WoCallCenter/paramKey");
	        }catch(Exception e){
	            return "OPWEIDCNVBEFYUHCLKFN";
	        }
	}
	/**
	 * 判断是否要用SSO登录登出
	 * @return
	 */
	public static boolean useSSO(){
        try{
        	return Boolean.parseBoolean(Globe.getProperty(GlobalNodeName.LOGIN_SSO_SWITCH));
        }catch(Exception e){
        	LOGGER.debug("得不到是否需要SSO验证功能参数");
            return false;
        }
    }
	/**
   * 判断字符串是否为 null 或 空字符串
   * @param str 源字符串
   * @return 为 null 或 空字符串返回 true, 否则 false
   */
  	public static boolean isBlankOrNull(String str) {
  		if (str == null || str.trim().equals("")||str.trim().equals("null")) {
  			return true;
  		}
  		else {
  			return false;
  		}
  	}
  	/**
  	 * 返回string的byte字节长度
  	 * @param string
  	 * @return int
  	 */
  	public static int strlen(String string) {
  		byte[] b = null;
  		b = string.getBytes();
  		return b.length;
  	}
  	/**
  	 * 获取IP
  	 * @param request
  	 * @return
  	 */
  	public static String getIPAddress(HttpServletRequest request){
  		
  	       String ip = request.getHeader("x-forwarded-for");
  	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
  	           ip = request.getHeader("Proxy-Client-IP");
  	       }
  	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
  	           ip = request.getHeader("WL-Proxy-Client-IP");
  	       }
  	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
  	           ip = request.getRemoteAddr();
  	       }
  	       return ip;
  	   }
  	/**
	 * 打印所有参数信息
	 * 
	 * @param req
	 *            Http请求
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void logAllParams(HttpServletRequest req) {
		String params = "";
		Enumeration<String> paramNamesEnum = req.getParameterNames();
		while (paramNamesEnum.hasMoreElements()) {
			String paramName = (String) paramNamesEnum.nextElement();
			String[] paramValues = req.getParameterValues(paramName);
			for (int i = 0; i < paramValues.length; i++) {
				String paramValue = paramValues[i];
				params += "[" + paramName + ":" + paramValue + "]";
			}
		}
		LOGGER.debug(" allParams:" + params);
	}
  	/**
  	 * 获取Mac地址
  	 * @return
  	 */
  	public static String getMacAddress(String IP){
  		String str="";
  		String macStr="";
  		String macAddress="";
  		
		try {
			Process process = Runtime.getRuntime().exec("nbtstat -a " + IP);
			InputStreamReader in=new InputStreamReader(process.getInputStream());
	  		LineNumberReader lnr=new LineNumberReader(in);
	  		for(int i=0;i<100;i++){
	  			str=lnr.readLine();
	  			if(str!=null){
	  				if(str.indexOf("MAC Address")>1){
	  					macStr = str.substring(str.indexOf("MAC Address") + 14,str.length());
	  					break;
	  				}
	  			}
	  		}
		} catch (IOException e) {
			return "Can't Get MAC Address!"; 
		}
		if (macStr.length() < 17) {   
			return "Error!";   
			}  
			  
			macAddress = macStr.substring(0, 2) + ":" + macStr.substring(3, 5)   
			+ ":" + macStr.substring(6, 8) + ":" + macStr.substring(9, 11)   
			+ ":" + macStr.substring(12, 14) + ":"   
			+ macStr.substring(15, 17);   
			//   
			return macAddress;   
  	}
  	/**
  	 * 验证是否为合法客户端
  	 */
  	public static boolean isValidateClient(String s,String accName,String id){
  		String hashStr = HashUtil.getMD5(accName+ getParamKey(id));
        return hashStr.equals(s);
	}
	private static String getParamKey(String id) {
		 String clientParamKey = getClientParamKey(id);
	       return clientParamKey;
	}
	public static String getClientParamKey(String id) {
		 try {
	            return Globe.getProperty("service/client/" + id + "/paramKey");
	        } catch (NullPointerException e) {
	            return null;
	        }
	}
	//				得到privatecenter的userinfo读的域名
	public static String getUserinfoURLRead(){
		try{
			return Globe.getProperty("privateCenter/userinfoURL/read");
		}catch(Exception e){
			System.out.println("得不到privatecenter的userinfo读的域名.");
			return null;
		}
	}
//				得到privatecenter的passwordinfo读的域名
	public static String getPasswordInfoURLRead(){
		try{
			return Globe.getProperty("privateCenter/passwordinfoURL/read");
		}catch(Exception e){
			System.out.println("得不到privatecenter的passwordinfo读的域.");
			return "http://privatecenter.2211.com:8080/passwordinfo";
		}
	}
	/**
	 * 比较指定时间与当前时间是否相差指定值
	 * @param date
	 * @param gap
	 * @return
	 */
	public static boolean isWaitEnough(Date date,long gap){
		long now=System.currentTimeMillis();
		long comp=date.getTime();
		if((comp+gap)<now){
			return true;
		}
		return false;
	}
	/**
	 * 验证账号是否存在
	 * @return
	 * @throws Exception 
	 */
	public static boolean isExsitAccount(HttpServletRequest request) throws Exception{
		PrivateCenterService privateService=new PrivateCenterServiceImpl();
		String accName=request.getParameter("userInfo.accountname");
		boolean exist=true;
		    String ip=MyUtil.getIPAddress(request);
		    User user=null;
		    user=privateService.getUserInfo(accName, ip);//请求PrivateCenter
		    
		    if(user==null){//确认此游戏帐号不存在
				exist=false;
			}else{
				if(MyUtil.isBlankOrNull(user.getUsername())){
					exist=false;
				}
			}
		    return exist;
	}
	/**
	 * 获取默认游戏服务器用户名
	 * @return
	 */
	public static String getDefaultServerUser()	{
		return Globe.getProperty("gameserver/user");
	}
	
	/**
	 * 获取默认游戏服务器密码
	 * @return
	 */
	public static String getDefaultServerPass(){
		return Globe.getProperty("gameserver/pass");
	}
	/**
	 * 验证账号是否存在
	 * @return
	 * @throws Exception 
	 */
	public static User isAccountExsit(HttpServletRequest request) throws Exception{
		PrivateCenterService privateService=new PrivateCenterServiceImpl();
		String accName=request.getParameter("userInfo.accountname");
		    String ip=MyUtil.getIPAddress(request);
		    User user=null;
		    user=privateService.getUserInfo(accName, ip);//请求PrivateCenter
		    
		    if(user==null){//确认此游戏帐号不存在
				return null;
			}else{
				if(MyUtil.isBlankOrNull(user.getUsername())){
					return null;
				}
			}
		    return user;
	}
	public static Object[] arrayGrow(Object[] o,Object obj) {
		try{
		   Class cl = o.getClass();
		   Class componentType = cl.getComponentType();
		   int length = Array.getLength(o);
		  int newLength = length +1;
		   Object newArray = Array.newInstance(componentType, newLength);
		   System.arraycopy(o, 0, newArray, 0, length);
		   Object[] arr=(Object[]) newArray;
		   arr[length]=obj;
		   //System.out.println(arr.length);
		    return arr;
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
		}
		
		  } 
   	/**
	 * 转义HTML代码
	 * @param aTagFragment
	 * @return
	 */
	public static String HTMLEncode(String aTagFragment){
		if (isBlankOrNull(aTagFragment)) return "";
		
		final StringBuffer result = new StringBuffer();
		final StringCharacterIterator iterator = new StringCharacterIterator(aTagFragment);
		char character = iterator.current();
		while (character != StringCharacterIterator.DONE ){
		if (character == '<') {result.append("&lt;");
		}
		else if (character == '>') {
		result.append("&gt;");
		}
		else if (character == '\"') {
		result.append("&quot;");
		}
		else if (character == '\'') {
		result.append("&#039;");
		}
		else if (character == '\\') {
		result.append("&#092;");
		}
		else if (character == '&') {
		result.append("&amp;");
		}
		else {
		//the char is not a special one
		//add it to the result as is
		result.append(character);
		}
		character = iterator.next();
		}
		return result.toString();
	}
	public static String paramEncode(String param){
		if(isBlankOrNull(param)){
			return "99999999";
		}
		String[][] seed={{"8","9","1","6","1","2","7","3","4","5"},
										{"6","2","1","1","7","4","5","6","8","9"},
										{"8","9","1","6","1","2","7","3","4","5"},
										{"1","3","4","5","2","3","4","9","1","4"},
										{"2","1","1","7","4","5","6","8","9","1"},
										{"6","3","4","5","6","2","7","8","9","1"},
										{"1","3","4","5","8","3","4","9","1","9"},
										{"6","3","4","5","6","3","7","8","9","1"},
										{"1","3","4","5","6","3","4","9","1","7"},
										{"2","1","1","7","4","5","6","8","9","1"}};
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<5;i++){
				sb.append(seed[getSeed().i][getSeed().j]);
				if(i<param.length()){
					sb.append(param.charAt(i));
				}
		}
		String result=sb.toString();
		result=param.length()+result;
		//result=result.substring(0,3)+param.substring(0,1)+result.substring(3)+param.substring(1);
			return result;
	}
	public static  Seed getSeed(){
			Random r=new Random();
			int i=r.nextInt(10);
			int j=r.nextInt(10);
			return new Seed(i,j);
	}
	public static int paramDedoce(String param){
		String str=param.substring(0,1);
		int len=Integer.valueOf(str);
		StringBuffer sb=new StringBuffer();
		int j=0;
		for(int i=2;i<param.length();i++,i++){
				if(j>=len)break;
				sb.append(param.charAt(i));
				j++;
		}
		return Integer.valueOf(sb.toString());
	}
	private static class Seed{
		Seed(int i,int j){
			this.i=i;
			this.j=j;
		};
		 int i,j;
	}
	public static String[] createCbArray(String content) {
		String[] rows=content.trim().split("\n");
//		for(int i=0; i< rows.length; i++)
//		{
//			txt+="<item><id>"+rows[i].split("\t")[0]+"</id><question><![CDATA["+rows[i].split("\t")[1]+"]]></question><option><![CDATA["+rows[i].split("\t")[2]+"]]>";
//		}
		return rows;
	}
	/**
	 * 文件上传
	 * @param wo
	 * @param upload
	 * @param uploadFileName
	 * @param rp
	 * @param name
	 */
	public static String[] uploadFileHandle(File[] upload,
			String[] uploadFileName,String rp,String name) {
		String[] fileList=new String[]{};
		File parent=new File(rp);
		File toFile=null;
		for(int i=0;i<upload.length;i++){
				//上传文件重命名
				String str=String.valueOf(new Date().getTime()+i);
				str=str.substring(str.length()-8);
				String suffix=uploadFileName[i].substring(uploadFileName[i].length()-4);
				String fileName=name+str+suffix;
				toFile=new File(parent,fileName);
				if(!toFile.getParentFile().exists()){
					toFile.getParentFile().mkdirs();
				}
				try {
					FileUtils.copyFile(upload[i], toFile);//文件写入保存路径
				} catch (IOException e) {
					e.printStackTrace();
				}
				//FileUpLoad uploadFile=new FileUpLoad();
					
					int back=rp.lastIndexOf("/");
					String backPath=rp.substring(back);
					//uploadFile.setPath("/upload"+backPath+"/"+fileName);
				
				//uploadFile.setUserinfo(wo.getUserInfo());
				fileList=(String[]) MyUtil.arrayGrow(fileList,"/upload"+backPath+"/"+fileName);
		}
		return fileList;
	}
	/**
	 * 获取文件下载路径
	 */
	public static String getXLSDownLoadPath() {
		//LOGGER.debug("getserviceInfo[serviceId="+serviceName + "|infoType" + infoType + "|path:"+(Constants.GlobalNodeName.GENERAL + serviceName + infoType)+"]");
		return Globe.getProperty("service/general/file/xlsDownload");
	}
	/**
	 * 获取是否使用view配置
	 */
	public static String getLaunchWOView() {
		//LOGGER.debug("getserviceInfo[serviceId="+serviceName + "|infoType" + infoType + "|path:"+(Constants.GlobalNodeName.GENERAL + serviceName + infoType)+"]");
		return Globe.getProperty("workOrderView/switch");
	}
	/**
	 * 获取是否使用Monitor配置
	 */
	public static String getLaunchMonitor() {
		//LOGGER.debug("getserviceInfo[serviceId="+serviceName + "|infoType" + infoType + "|path:"+(Constants.GlobalNodeName.GENERAL + serviceName + infoType)+"]");
		return Globe.getProperty("monitorLock/switch");
	}
	/**
	 * 获取使用Monitor运行时间间隔
	 */
	public static String getMonitorInterval() {
		//LOGGER.debug("getserviceInfo[serviceId="+serviceName + "|infoType" + infoType + "|path:"+(Constants.GlobalNodeName.GENERAL + serviceName + infoType)+"]");
		return Globe.getProperty("monitorLock/delay");
	}
	/**
	 * 获取是否使用延时评价配置
	 */
	public static String getLaunchDelayRate() {
		//LOGGER.debug("getserviceInfo[serviceId="+serviceName + "|infoType" + infoType + "|path:"+(Constants.GlobalNodeName.GENERAL + serviceName + infoType)+"]");
		return Globe.getProperty("writeRate/switch");
	}
	/**
	 * 获取使用延时运行时间间隔
	 */
	public static String getRateInterval() {
		//LOGGER.debug("getserviceInfo[serviceId="+serviceName + "|infoType" + infoType + "|path:"+(Constants.GlobalNodeName.GENERAL + serviceName + infoType)+"]");
		return Globe.getProperty("writeRate/delay");
	}
	public static Map<String, String> getParameterMap(HttpServletRequest request) {
		List<String> names = new ArrayList<String>();
		for (Enumeration<String> enumd = request.getParameterNames(); enumd
				.hasMoreElements();) {
			names.add(enumd.nextElement());
		}
		Collections.sort(names);
		Map<String, String> params = new LinkedHashMap<String, String>();
		for (String name : names) {
			params.put(name, request.getParameter(name));
		}
		params.put("_Method", request.getMethod());
		params.put("_RemoteAddr", request.getRemoteAddr());
		return params;
	}
	/**
	 * 打印出MAP里所有的数据
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String showMapData(Map map){
		Iterator it = map.entrySet().iterator(); 
		StringBuffer sbf = new StringBuffer();
		while(it.hasNext()){
			 Map.Entry e = (Map.Entry) it.next();
			 sbf.append("KEY:" + e.getKey() + "\n");
			 sbf.append("----->VALUE:" + e.getValue() + "\n");
		}
		LOGGER.debug(sbf.toString());
		return sbf.toString();
	}
}
