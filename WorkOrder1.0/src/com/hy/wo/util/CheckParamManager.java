package com.hy.wo.util;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.hy.wo.exception.InputErrorException;
import com.hy.wo.exception.InputIllegalArgumentException;
import com.hy.wo.exception.WoCenterException;
import com.hy.wo.po.WorkOrder;
import com.hy.wo.util.Constants.ParamName;
import com.hy.wo.util.Constants.PromptMessage;
import com.hy.wo.util.Constants.StateList;
import com.hy.wo.util.Constants;
import com.you9.base.Globe;
import com.hy.wo.util.MyUtil;
import com.you9.base.util.StringUtil;

import static com.hy.wo.util.Constants.GlobalNodeName.*;


/**
 * 参数验证管理类
 * @author hongying_guan
 *
 */
public class CheckParamManager {
	private static final Logger LOGGER = Logger.getLogger(CheckParamManager.class);//定义日志
	/**
	 * 账号解封参数验证
	 * @param wo WorkOrder对象
	 * @throws IllegalArgumentException 参数异常
	 * @throws WoCenterException 
	 */
	public static void checkAccountLockParam(WorkOrder wo,HttpServletRequest request)throws IllegalArgumentException, WoCenterException{
		//LOGGER.debug("********账号解封参数验证******");
		vipBlessValidate(wo, request);
	}
	
	/**
	 * 角色恢复参数验证
	 * @param wo WorkOrder对象
	 * @throws WoCenterException 
	 * @throws IllegalArgumentException 参数异常
	 */
	public static void checkRecoveryParam(WorkOrder wo,HttpServletRequest request) throws WoCenterException{
		baseValidate(request);
		checkUsername(wo.getUserInfo().getUsername());//验证角色名
		//checkHappendate(wo.getUserInfo().getHappendate().toString());//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	
	/**
	 * 角色异常参数验证
	 * @param wo WorkOrder对象
	 * @throws WoCenterException 
	 * @throws IllegalArgumentException 参数异常
	 */
	public static void checkRoleOfAbnormalParam(WorkOrder wo,HttpServletRequest request) throws WoCenterException{
		baseValidate(request);
		checkUsername(wo.getUserInfo().getUsername());//验证角色名
		checkHappendate(Constants.sdf.format(wo.getUserInfo().getHappendate()));//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	/**
	 * 游戏运行
	 * @param wo WorkOrder对象
	 * @throws WoCenterException 
	 * @throws IllegalArgumentException 参数异常
	 * 
	 */
	public static void checkGameRunParam(WorkOrder wo,HttpServletRequest request) throws WoCenterException{
		baseValidate(request);
		checkUsername(wo.getUserInfo().getUsername());//验证角色名
		checkHappendate(Constants.sdf.format(wo.getUserInfo().getHappendate()));//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkServer(Integer.toString(wo.getUserInfo().getLvlTwo().getId()));//验证小类问题
		//checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	public static void baseValidate(HttpServletRequest request) throws WoCenterException {
		String id=request.getParameter(ParamName.CLIENT_ID);//客户端ID
		String s=request.getParameter(ParamName.S);
		
		String thisId=MyUtil.getserviceInfo(Constants.GlobalNodeName.WO_CENTER_SERVICE, Constants.GlobalNodeName.CLIENT_ID);
		String paramKey=MyUtil.getserviceInfo(Constants.GlobalNodeName.WO_CENTER_SERVICE, Constants.GlobalNodeName.PARAM_KEY);
		
		String thisS=MyUtil.hash(
				thisId+
				paramKey);
		
		if(!(id.equals(thisId)&&thisS.equals(s))){		//验证客户端
			LOGGER.debug("非法的客户端center1.0");
			throw new WoCenterException(StateList.Illegal_OPERATION_ERROR, PromptMessage.ID_IS_ILLEGAL);
		}
	}
	public static void vipBlessValidate(WorkOrder wo, HttpServletRequest request) throws WoCenterException {
//		wo.getUserInfo().setAccountname(username);
//		wo.getUserInfo().setMemo(msg);
//		wo.getUserInfo().setMail(from);
//		wo.getUserInfo().setRealname(to);
//		wo.getUserInfo().setHappendate(tm);
//		wo.getUserInfo().setTel(type);
		
		String id=request.getParameter(ParamName.CLIENT_ID);//客户端ID
		String s=request.getParameter(ParamName.S);
		
		System.out.println("id = " + id);
		System.out.println("s = " + s);
		System.out.println("from = " + wo.getUserInfo().getMail());
		System.out.println("to = " + wo.getUserInfo().getRealname());
		
		String thisId=MyUtil.getserviceInfo(Constants.GlobalNodeName.WO_CENTER_SERVICE, Constants.GlobalNodeName.CLIENT_ID);
		String paramKey=MyUtil.getserviceInfo(Constants.GlobalNodeName.WO_CENTER_SERVICE, Constants.GlobalNodeName.PARAM_KEY);
		
		String thisS=MyUtil.hash(
				wo.getUserInfo().getMail() +
				wo.getUserInfo().getRealname() +
				paramKey);
		
		System.out.println("thisId = " + thisId);
		System.out.println("thisS = " + thisS);
		
		if(!(id.equals(thisId)&&thisS.equals(s))){		//验证客户端
			LOGGER.debug("非法的客户端center1.0");
			throw new WoCenterException(StateList.Illegal_OPERATION_ERROR, PromptMessage.ID_IS_ILLEGAL);
		}
	}


	/**
	 * 物品丢失参数验证
	 * @param wo WorkOrder对象
	 * @throws WoCenterException 
	 * @throws IllegalArgumentException 参数异常
	 * 
	 */
	public static void checkLoseItemsParam(WorkOrder wo,HttpServletRequest request) throws WoCenterException{
		baseValidate(request);
		checkUsername(wo.getUserInfo().getUsername());//验证角色名
		checkHappendate(Constants.sdf.format(wo.getUserInfo().getHappendate()));//发生时间验证
	//	checkHappendate(wo.getAdditional().getOfflineTime().toString());//
//		checkHappendate(wo.getUserInfo().getHappendate());
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}


	/**
	 * 服务器故障参数验证
	 * @param wo WorkOrder对象
	 * @throws WoCenterException 
	 * @throws IllegalArgumentException 参数异常
	 */
	public static void checkServerFailureParam(WorkOrder wo,HttpServletRequest request) throws WoCenterException{
		LOGGER.debug("******checkServerFailureParam****");
		baseValidate(request);
		//checkUsername(wo.getUserInfo().getUsername());//验证角色名
		checkHappendate(Constants.sdf.format(wo.getUserInfo().getHappendate()));//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	
	/**
	 * 游戏BUG参数验证
	 * @param wo WorkOrder对象
	 * @throws WoCenterException 
	 * @throws IllegalArgumentException 参数异常
	 */
	public static void checkGameBugParam(WorkOrder wo,HttpServletRequest request) throws WoCenterException{
		baseValidate(request);
		checkUsername(wo.getUserInfo().getUsername());//验证角色名
		checkHappendate(Constants.sdf.format(wo.getUserInfo().getHappendate()));//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		//checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	public static void checkInnerGameBugParam(WorkOrder wo,HttpServletRequest request){
		//checkUsername(wo.getUserInfo().getUsername());//验证角色名
	//	checkHappendate(Constants.sdf.format(wo.getUserInfo().getHappendate()));//发生时间验证
		//checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		//checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	
	/**
	 * 违规举报参数验证
	 * @param wo WorkOrder对象
	 * @throws WoCenterException 
	 * @throws IllegalArgumentException 参数异常
	 */
	public static void checkPlugAbuseParam(WorkOrder wo,HttpServletRequest request) throws WoCenterException{
		baseValidate(request);
		checkUsername(wo.getAdditional().getUsername());//验证举报角色名
		checkServer(Integer.toString(wo.getUserInfo().getLvlTwo().getId()));//验证举报类别
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	
	/**
	 * 处理其他问题参数验证
	 * @param wo WorkOrder对象
	 * @throws WoCenterException 
	 * @throws IllegalArgumentException 参数异常
	 */
	public static void checkOthersParam(WorkOrder wo,HttpServletRequest request) throws WoCenterException{
		baseValidate(request);
		checkUsername(wo.getUserInfo().getUsername());//验证角色名
		//checkHappendate(Constants.sdf.format(wo.getUserInfo().getHappendate()));//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	
	/**
	 * 意见建议处理参数验证
	 * @param wo WorkOrder对象
	 * @throws WoCenterException 
	 * @throws IllegalArgumentException 参数异常
	 */
	public static void checkSuggestionParam(WorkOrder wo,HttpServletRequest request) throws WoCenterException{
		baseValidate(request);
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	
	/**
	 * 咨询问题参数验证
	 * @param wo WorkOrder对象
	 * @throws WoCenterException 
	 * @throws IllegalArgumentException 参数异常
	 */
	public static void checkAdvisoryParam(WorkOrder wo,HttpServletRequest request) throws WoCenterException{
		baseValidate(request);
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	/**
	 * 充值问题参数验证
	 * @param wo WorkOrder对象
	 * @throws WoCenterException 
	 * @throws IllegalArgumentException 参数异常
	 */
	public static void checkRechargeParam(WorkOrder wo,HttpServletRequest request) throws WoCenterException{
		baseValidate(request);
		checkHappendate(Constants.sdf.format(wo.getUserInfo().getHappendate()));//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkServer(wo.getRecharge().getRechargeType());//验证充值卡类型
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
		LOGGER.debug("over");
	}
	/**
	 * 游戏账号验证
	 * @param accountname	游戏账号
	 * @throws IllegalArgumentException 参数异常
	 */
	private  static void  checkAccountName(String accountname){
		LOGGER.debug("验证游戏帐号");
		LOGGER.debug("account name="+accountname);
		if(StringUtil.isBlankOrNull(accountname)){//为空
			throw new IllegalArgumentException("游戏账号不能为空");
		}else{
			if(!MyUtil.doRegularExpression(MyUtil.getRegExpress(ACCOUNTNAME_REG), accountname)){//游戏账号不符合规则
				throw new IllegalArgumentException("请输入正确的游戏账号");
			}
		}
	} 
	
	/**
	 * 验证角色名
	 * @param username 角色名称
	 * @throws IllegalArgumentException
	 */
	private static void checkUsername(String username){		
		if(StringUtil.isBlankOrNull(username)){//验证是否为空
			throw new IllegalArgumentException("角色名不能为空！");
		}
		int len=username.length();
		if(len<2||len>16){
			throw new IllegalArgumentException("角色名称为2-8位汉字或4-15为英文字符！");
		}
	}
	
	/**
	 * 验证发生时间
	 * @param happendate
	 * @throws IllegalArgumentException
	 */
	private static void checkHappendate(String happendate){
		LOGGER.debug("********checkHappenddate******");
//		if(StringUtil.isBlankOrNull(happendate)){
//			throw new IllegalArgumentException("时间不能为空！");
//		}else if(!MyUtil.doRegularExpression(MyUtil.getRegExpress(HAPPDATE_REG), happendate)){
//			throw new IllegalArgumentException("时间格式不符，正确的格式为年-月-日  小时：分：秒！");
//		}//else if(!MyUtil.happenDateThan(happendate)){
		//	throw new IllegalArgumentException("填写的时间不能大于当前时间！");
		//}
		LOGGER.debug("*************日期验证结束");
//		if(StringUtil.isBlankOrNull(happendate)){
//			throw new IllegalArgumentException("时间不能为空！");
//		}else if(!MyUtil.happenDateThan(happendate)){
//			throw new IllegalArgumentException("填写的时间不能大于当前时间！");
//		}
	}
	
	/**
	 * 验证服务器
	 * @param serverId 服务器ID
	 * @throws IllegalArgumentException
	 */
	private static void checkServer(String serverId){
		if(StringUtil.isBlankOrNull(serverId)){
			throw new IllegalArgumentException("服务器不能为空！");
		}
	}
	/**
	 * 验证QQ和联系电话
	 * @param qq
	 * @param phoneNumber
	 * @throws IllegalArgumentException
	 */
	private static void checkQQAndPhone(String qq, String phoneNumber){
		if(StringUtil.isBlankOrNull(qq)&&StringUtil.isBlankOrNull(phoneNumber)){//QQ和联系电话全为空
			throw new IllegalArgumentException("联系电话和QQ必须选填一！");
		}else{
			if(!StringUtil.isBlankOrNull(qq)){//QQ不为空
				checkQQ(qq.trim());
			}
			if(!StringUtil.isBlankOrNull(phoneNumber)){//联系电话不为空
				checkPhoneNumber(phoneNumber.trim());
			}
		}
	}
	/**
	 * 验证QQ
	 * @param qq
	 * @throws IllegalArgumentException
	 */
	private static void checkQQ(String qq){
		if(StringUtil.isBlankOrNull(qq)){
			throw new IllegalArgumentException("QQ不能为空！");
		}else if(!MyUtil.doRegularExpression(MyUtil.getRegExpress(QQ_REG), qq)){
			throw new IllegalArgumentException("QQ格式不正确！");
		}
	}
	/**
	 * 验证联系电话
	 * @param phoneNumber 
	 * @throws IllegalArgumentException
	 */
	private static void checkPhoneNumber(String phoneNumber){
		if(StringUtil.isBlankOrNull(phoneNumber)){
			throw new IllegalArgumentException("联系电话不能为空！");
		}else if(!MyUtil.doRegularExpression(MyUtil.getRegExpress(PHONENUMBER_REG), phoneNumber)
				&&!MyUtil.doRegularExpression(MyUtil.getRegExpress(TELNUMBER_REG), phoneNumber)){
			throw new IllegalArgumentException("联系电话格式不正确！");
		}
	}
	
	/**
	 * 验证内容描述
	 * @param memo
	 * @throws IllegalArgumentException
	 */
	private static void checkMemo(String memo){
		if(!StringUtil.isBlankOrNull(memo)){
			if(memo.trim().length()<3||memo.trim().length()>300){
				throw new IllegalArgumentException("请将描述内容控制在3到300个汉字之间！");
			}
		}
	}
	
	/**
	 * 登录参数验证
	 * @param request
	 * @return
	 * @throws InputErrorException
	 */
	public static Map<String, String> checkLoginParam(HttpServletRequest request) throws Exception{
//		String sourceURL = request.getParameter(ParamName.SOURCE_URL); // 得到源登录页面
//		System.out.println("surceULR:::"+sourceURL);
//		if (MyUtil.isBlankOrNull(sourceURL) || sourceURL.indexOf("index") < 0) {//源登录的参数为空，指定默认值
//			sourceURL = Constants.UrlInfo.DEFAULT_LOGIN_URL;//默认的登录页面
//		}
//		request.getSession().setAttribute(Constants.SOURCE_URL_KEY, sourceURL); // 取得SESSION的值,以进行跳转到相应的登录页面/
		
		String userName = request.getParameter(ParamName.USERNAME);//用户名
		String password = request.getParameter(ParamName.PASSWORD);//密码
		String checkCode = request.getParameter(ParamName.IDENTIFYING_CODE);//验证码
		String userIp = request.getRemoteAddr();// 用户登录的ip
		System.out.println(userIp);
		checkLoginUsername(userName);
		checkPassword(password);
		
		if(MyUtil.isBlankOrNull(checkCode)){
			throw new InputErrorException("验证码不能为空");
		}
		checkCode = checkCode.trim();
		String sessionCode = (String)request.getSession().getAttribute(Globe.getProperty(Constants.GlobalNodeName.SESSION_CHECKCODE_KEY));//从session中获取验证码
		if(checkCode.length() != 4){
			throw new InputErrorException("验证码错误");
		}else if (!checkCode.equalsIgnoreCase(sessionCode)){
			throw new InputErrorException("验证码错误");
		}
		
		//将参数添加进Map
		Map<String, String> map = new HashMap<String, String>();
		map.put(ParamName.USERNAME, userName);
		map.put(ParamName.PASSWORD, password);
		map.put(ParamName.IDENTIFYING_CODE, checkCode);
		map.put(ParamName.USER_IP, userIp);
		
		LOGGER.debug("PASSPORT MAP PASSWORD = "+map.get(ParamName.PASSWORD));
		return map;
	}
	/**
	 * 验证火游账号
	 * @param username
	 * @throws InputIllegalArgumentException
	 */
	private static void checkLoginUsername(String username)throws InputIllegalArgumentException{
		if (StringUtil.isBlankOrNull(username)) {//用户名为空
			throw new InputIllegalArgumentException(PromptMessage.USERNAME_IS_NULL);
		}
		username = username.trim();
		if (!MyUtil.doRegularExpression(MyUtil.getRegExpress(Constants.GlobalNodeName.ACCOUNTNAME_REG), username)) {//用户不合法
			throw new InputIllegalArgumentException(PromptMessage.USERNAME_IS_NOT_LEGAL);
		}
	}
	
	public static void checkPassword(String password) throws InputIllegalArgumentException{
		if (StringUtil.isBlankOrNull(password)) {
			throw new InputIllegalArgumentException("密码不能为空");
		}
		password = password.trim();
		if (MyUtil.strlen(password) > 20 || MyUtil.strlen(password) < 6) {
			throw new InputIllegalArgumentException("请输入(6-16)位长度的密码");
		}
	}
	
	/**
	 * 员工登录参数验证
	 * @param username	用户名
	 * @param password	密码
	 * @throws InputIllegalArgumentException
	 */
	public static void checkStaffLoginParam(String username, String password) throws InputIllegalArgumentException{
		checkLoginUsername(username);//用户名验证
		checkPassword(password);//密码验证
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}
	/**
	 * CallCenter登录参数验证
	 * @param request
	 * @throws WoCenterException 
	 * @throws WoCenterException 
	 */
	public static void callCenterBasicValidate(HttpServletRequest request) throws WoCenterException{
		String id=request.getParameter(ParamName.CLIENT_ID);//客户端ID
		String s=request.getParameter(ParamName.S);
		
		String thisId=MyUtil.getserviceInfo(Constants.GlobalNodeName.WO_CALL_CENTER, Constants.GlobalNodeName.CLIENT_ID);
		String paramKey=MyUtil.getCallCenterKey();//.getCallCenterKey();
		String thisS=MyUtil.hash(request.getParameter(ParamName._USERNAME)+
				request.getParameter(ParamName.PASSWORD)+
				thisId+
				paramKey);
//		System.out.println("+++++++ s="+thisS);
//		System.out.println("+++++++ id="+thisId);
//		System.out.println("+++++++ username="+request.getParameter(ParamName._USERNAME));
//		System.out.println("+++++++ password="+request.getParameter(ParamName.PASSWORD));
		
		if(!(id.equals(thisId)&&thisS.equals(s))){		//验证客户端
			LOGGER.debug("非法的客户端");
			throw new WoCenterException(StateList.Illegal_OPERATION_ERROR, PromptMessage.ID_IS_ILLEGAL);
		}
	}
	public static void checkCallCenterParam(HttpServletRequest request) throws WoCenterException {
		try {
			callCenterBasicValidate(request);
			checkLoginUsername(request.getParameter(ParamName._USERNAME));
			checkPassword(request.getParameter(ParamName.PASSWORD));//密码验证
		} catch (InputIllegalArgumentException e) {
			throw new WoCenterException(StateList.PARAM_ERR,e.getMessage());
		}catch(WoCenterException e){
			throw e;
		}
	}
	/**
	 * CallCenter  ClientId，S验证
	 * @param request
	 * @throws WoCenterException 
	 * @throws WoCenterException 
	 */
	public static void getCallerInfoValidate(HttpServletRequest request) throws WoCenterException{
		LOGGER.debug("start validate");
		String id=request.getParameter(ParamName.CLIENT_ID);//客户端ID
		String s=request.getParameter(ParamName.S);
		if(MyUtil.isBlankOrNull(id) || MyUtil.isBlankOrNull(s)){
			throw new WoCenterException(StateList.Illegal_OPERATION_ERROR, PromptMessage.ID_IS_ILLEGAL);
		}
		
		String thisId=MyUtil.getserviceInfo(Constants.GlobalNodeName.WO_CALL_CENTER, Constants.GlobalNodeName.CLIENT_ID);
		String paramKey=MyUtil.getCallCenterKey();//.getCallCenterKey();
		String thisS=MyUtil.hash(
				thisId+
				paramKey);
		
		if(!(id.equals(thisId)&&thisS.equals(s))){		//验证客户端
			//LOGGER.debug("非法的客户端");
			throw new WoCenterException(StateList.Illegal_OPERATION_ERROR, PromptMessage.ID_IS_ILLEGAL);
		}
	}
	/**
	 * 保存CallerINfo 参数验证
	 * @param request
	 * @throws WoCenterException
	 */
	public static void saveCallerInfoValidate(HttpServletRequest request) throws WoCenterException {
		getCallerInfoValidate(request);
		//try {
			//checkLoginUsername(request.getParameter(ParamName.USERNAME));
			//if(MyUtil.isBlankOrNull(request.getParameter(ParamName._USERNAME)))
				//throw new WoCenterException(StateList.PARAM_ERR,PromptMessage.PARAM_IS_NULL);
		//} catch (InputIllegalArgumentException e) {
		//	throw new WoCenterException(StateList.PARAM_ERR,e.getMessage());
		//}
	}
}