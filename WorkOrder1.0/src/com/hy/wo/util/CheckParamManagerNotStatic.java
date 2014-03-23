package com.hy.wo.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hy.wo.po.WorkOrder;
import static com.hy.wo.util.Constants.GlobalNodeName.*;
import com.you9.base.util.StringUtil;

/**
 * 参数验证管理类
 * @author hongying_guan
 *
 */
@Component
public class CheckParamManagerNotStatic {
	private static final Logger LOGGER = Logger.getLogger(CheckParamManagerNotStatic.class);//定义日志
	/**
	 * 账号解封参数验证
	 * @param wo WorkOrder对象
	 * @throws IllegalArgumentException 参数异常
	 */
	public void checkAccountLockParam(WorkOrder wo)throws IllegalArgumentException{
		LOGGER.debug("********账号解封参数验证******");
		checkAccountName(wo.getUserInfo().getAccountname());//验证游戏账号
		checkUsername(wo.getUserInfo().getUsername());//验证角色名
		checkHappendate(wo.getUserInfo().getHappendate().toString());//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
		
	}
	
	/**
	 * 角色恢复参数验证
	 * @param wo WorkOrder对象
	 * @throws IllegalArgumentException 参数异常
	 */
	public void checkRecoveryParam(WorkOrder wo){
		checkUsername(wo.getUserInfo().getUsername());//验证角色名
		checkHappendate(wo.getUserInfo().getHappendate().toString());//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	
	/**
	 * 角色异常参数验证
	 * @param wo WorkOrder对象
	 * @throws IllegalArgumentException 参数异常
	 */
	public void checkRoleOfAbnormalParam(WorkOrder wo){
		checkUsername(wo.getUserInfo().getUsername());//验证角色名
		checkHappendate(wo.getUserInfo().getHappendate().toString());//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	/**
	 * 游戏运行
	 * @param wo WorkOrder对象
	 * @throws IllegalArgumentException 参数异常
	 * 
	 */
	public void checkGameRunParam(WorkOrder wo){
		checkUsername(wo.getUserInfo().getUsername());//验证角色名
		checkHappendate(wo.getUserInfo().getHappendate().toString());//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	/**
	 * 物品丢失参数验证
	 * @param wo WorkOrder对象
	 * @throws IllegalArgumentException 参数异常
	 * 
	 */
	public void checkLoseItemsParam(WorkOrder wo){
		checkUsername(wo.getUserInfo().getUsername());//验证角色名
		checkHappendate(wo.getUserInfo().getHappendate().toString());//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	/**
	 * 服务器故障参数验证
	 * @param wo WorkOrder对象
	 * @throws IllegalArgumentException 参数异常
	 */
	public void checkServerFailureParam(WorkOrder wo){
		LOGGER.debug("******checkServerFailureParam****");
		//checkUsername(wo.getUserInfo().getUsername());//验证角色名
		//checkHappendate(wo.getUserInfo().getHappendate().toString());//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	
	/**
	 * 游戏BUG参数验证
	 * @param wo WorkOrder对象
	 * @throws IllegalArgumentException 参数异常
	 */
	public void checkGameBugParam(WorkOrder wo){
		checkUsername(wo.getUserInfo().getUsername());//验证角色名
		checkHappendate(wo.getUserInfo().getHappendate().toString());//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	
	/**
	 * 违规举报参数验证
	 * @param wo WorkOrder对象
	 * @throws IllegalArgumentException 参数异常
	 */
	public void checkPlugAbuseParam(WorkOrder wo){
		checkUsername(wo.getUserInfo().getUsername());//验证角色名
		
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	
	/**
	 * 处理其他问题参数验证
	 * @param wo WorkOrder对象
	 * @throws IllegalArgumentException 参数异常
	 */
	public void checkOthersParam(WorkOrder wo){
		checkUsername(wo.getUserInfo().getUsername());//验证角色名
		//checkHappendate(wo.getUserInfo().getHappendate().toString());//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	
	/**
	 * 意见建议处理参数验证
	 * @param wo WorkOrder对象
	 * @throws IllegalArgumentException 参数异常
	 */
	public void checkSuggestionParam(WorkOrder wo){
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	
	/**
	 * 咨询问题参数验证
	 * @param wo WorkOrder对象
	 * @throws IllegalArgumentException 参数异常
	 */
	public void checkAdvisoryParam(WorkOrder wo){
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	/**
	 * 充值问题参数验证
	 * @param wo WorkOrder对象
	 * @throws IllegalArgumentException 参数异常
	 */
	public void checkRechargeParam(WorkOrder wo){
		checkHappendate(wo.getUserInfo().getHappendate().toString());//发生时间验证
		checkServer(Integer.toString(wo.getUserInfo().getServer().getId()));//验证服务器
		checkQQAndPhone(wo.getUserInfo().getQq(), wo.getUserInfo().getTel());//验证QQ和联系电话号码
		checkMemo(wo.getUserInfo().getMemo());//内容描述验证
	}
	/**
	 * 游戏账号验证
	 * @param accountname	游戏账号
	 * @throws IllegalArgumentException 参数异常
	 */
	private  void  checkAccountName(String accountname){
		LOGGER.debug("验证游戏帐号");
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
	private void checkUsername(String username){		
		if(StringUtil.isBlankOrNull(username)){//验证是否为空
			throw new IllegalArgumentException("角色名不能为空！");
		}
		int len=username.getBytes().length;
		if(len<4||len>16){
			throw new IllegalArgumentException("角色名称为2-8位汉字或4-15为英文字符！");
		}
	}
	
	/**
	 * 验证发生时间
	 * @param happendate
	 * @throws IllegalArgumentException
	 */
	private void checkHappendate(String happendate){
		if(StringUtil.isBlankOrNull(happendate)){
			throw new IllegalArgumentException("时间不能为空！");
		}else if(!MyUtil.doRegularExpression(MyUtil.getRegExpress(HAPPDATE_REG), happendate)){
			throw new IllegalArgumentException("时间格式不符，正确的格式为年-月-日  小时：分：秒！");
		}
	}
	
	/**
	 * 验证服务器
	 * @param serverId 服务器ID
	 * @throws IllegalArgumentException
	 */
	private void checkServer(String serverId){
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
	private void checkQQAndPhone(String qq, String phoneNumber){
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
	private void checkQQ(String qq){
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
	private void checkPhoneNumber(String phoneNumber){
		if(StringUtil.isBlankOrNull(phoneNumber)){
			throw new IllegalArgumentException("联系电话不能为空！");
		}else if(!MyUtil.doRegularExpression(MyUtil.getRegExpress(PHONENUMBER_REG), phoneNumber)){
			throw new IllegalArgumentException("联系电话格式不正确！");
		}
	}
	
	/**
	 * 验证内容描述
	 * @param memo
	 * @throws IllegalArgumentException
	 */
	private void checkMemo(String memo){
		if(!StringUtil.isBlankOrNull(memo)){
			if(memo.trim().length()<3||memo.trim().length()>100){
				throw new IllegalArgumentException("请将描述内容控制在3到100个字符之间！");
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
