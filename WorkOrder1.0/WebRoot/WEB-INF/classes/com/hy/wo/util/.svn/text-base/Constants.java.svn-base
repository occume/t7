package com.hy.wo.util;

import java.text.SimpleDateFormat;
import com.you9.base.Globe;

/**
 * 常量类
 */
public class Constants {

	/**
	 * 请求参数常量
	 */
	public static class ParamName{
		public static final String USERNAME = "accountname";//游戏帐号
		public static final String _USERNAME = "username";//游戏帐号
		public static final String PASSWORD = "password";
		public static String IDENTIFYING_CODE = "identifyingCode";// 验证码
		public static String USER_IP 	= "userIp";// 用户请求IP
		public static String USER_ID 	= "userid";// 
		public static String REQ 		= "req";// 
		public static String USER_TYPE 	= "usertype";// 
		public static String TYPE 		= "type";// 
		public static String IP 		= "IP";// 
		
		public static final String CHECK_TICKET = "checkTicket";//从配置文件中读取验证TICKET的URL
		public static final String TICKET = "ticket";//获取TICKET
		
		public static String ID 		= "id";
		public static String IDS 		= "ids";
		public static String STATES 	= "states";
		public static String CONTENT 	= "content";
		public static String DESCRIP 	= "descrip";
		public static String MESSAGE 	= "msg";
		public static String TIME 		= "time";
		public static String GROUP_ID 	= "groupId";
		public static String CLIENT_ID 	= "clientId";
		public static String SERVER_ID 	= "serverId";
		public static String ACCOUNT 	= "account";//用户名
		public static String S 			= "s";// MD5加密字符串
		public static String SOURCE_URL = "source_url";// 请求的源页面值
		
		public static String TO_USER 	= "touser";// 请求的源页面值
		public static String USER 		= "user";// 请求的源页面值
		public static String KEY_WORD 	= "keyWord";// 请求的源页面值
		public static String CP 		= "cp";// 请求的源页面值
	}
	 /**
	 * 关于返回的xml节点的描述的定义
	 * 
	 */
	public static class ViewNode {
        public static final String HY                          = "hy";
        public static final String ID                          = "id";
        public static final String STATE                       = "state";
        public static final String STATE_DESC                  = "stateDesc";
        public static final String WO_DATA                     = "woData";
        public static final String COUNT                       = "count";
        public static final String ALL_COUNT                   = "allCount";
        public static final String DEALING_COUNT               = "dealingCount";
        public static final String RESPONSED_COUNT             = "responsedCount";
        public static final String UNDEAL_COUNT                = "undealCount";
        public static final String LACKINFO_COUNT              = "lackInfoCount";
        public static final String USER_INFO              	   = "userinfo";
        public static final String PHONE              		   = "phone";
        public static final String QQ             				= "qq";
        public static final String EMAIL              			= "email";
        public static final String ID_CARD              		= "idcard";
        
    }
    public static class SystemConst{
    	public static final String CHARSET                     = Globe.getProperty("charset");
    	public static final String CONTENT_TYPE				   = Globe.getProperty("content-type");
    	public static final String DB_ORACLE_NAME              = "oracle";//ORACLE数据源名
    	public static final String DB_MYSQL_NAME 			   = "mySQL";//MYSQL数据源名
    	public static final String IN_USERLOG_STRING		   = "IN_";//标识进入系统时记录日志的字符串
    	public static final String OUT_USERLOG_STRING 		   = "OUT_";//标识登出系统时记录日志的字符串
    	public static final String TRUE                              = "true";
        public static final String FALSE                             = "false";
    	public static final int DEFAULT_WAIT_SEC			   = 5;
    }
	/**
	 * 配置文件中节点参数名集
	 */
	public static class GlobalNodeName{
		
		public static final String SERVICE = "service/";
		public static final String GENERAL = SERVICE + "general/";
		public static final String PASSWORD =  GENERAL + "passport/";
		public static final String GET_PWD_MENU = PASSWORD + "getPwdMenu";
		public static final String GET_PWD_BY_SEC_URL = PASSWORD + "getPwdBySecureCodeUrl";
		public static final String GET_PWD_BY_EMAIL_URL = PASSWORD + "getPwdByUserEmailUrl";
		public static final String CLIENT = SERVICE + "client/_";
		
		public static final String CHECK_TICKET_PATH = SERVICE
		+ "checkTicketUrl";// 验证TICKET的URL
		
		public static final String WO_CENTER_SERVICE		= "WoCenter/";
		public static final String WO_CALL_CENTER		= "WoCallCenter/";
		public static final String CLIENT_ID				= "clientId";
		public static final String PARAM_KEY			= "paramKey";
		
		public static String IDENTIFYING_CODE = "identifyingCode";// 验证码
		public static String USER_IP = "userIp";// 用户请求IP
		public static final String PASSPORT_DEFAULT_LOGIN_URL = SERVICE	+ "defaultLoginUrl";// 获取passport默认的登录页
		
		public static final String CHECK_CODE = "checkCode";
		public static final String SESSION_CHECKCODE_KEY = CHECK_CODE
		+ "/checkcode-name";// 将验证码存储在SESSION中name
		
		/**缓存操作相关节点常量*/
		public static final String CACHE = "cache/";
		public static final String BATCH_TYPE = CACHE + "batchType/";//批量操作类型
		public static final String BATCH_TYPE_KEY = BATCH_TYPE + "key";//批量操作类型缓存区域KEY
		
		/**验证规则*/
		public static final String VALIDATE = "validate/";//验证规则的根节点
		public static final String REG_EXPRESS = VALIDATE + "reg-express/";//正则表达式节点
		public static final String ACCOUNTNAME_REG = REG_EXPRESS + "accountname";//游戏账号的正则表达式
		public static final String PHONENUMBER_REG = REG_EXPRESS + "phoneNumber";//联系电话正则表达式
		public static final String TELNUMBER_REG = REG_EXPRESS + "telNumber";//联系电话正则表达式
		public static final String QQ_REG = REG_EXPRESS + "qq";//QQ正则表达式
		public static final String HAPPDATE_REG = REG_EXPRESS + "happdate";//QQ正则表达式
		
		public static final String LOGIN_SSO_SWITCH = VALIDATE +"sso";
		
		/************database*****************/
		public static String DATABASE 				     	= "database/";
		public static String QUERY_TIMEOUT				    = DATABASE + "query-timeout";
		
		/*********常量**************/
		public static final String SWITCH                   = "switch";
		public static final String STATE                    = "state/_";
		public static final String CARDSTATE                = "CardGameState/_";
		public static final String M818STATE                = "M818GameState/_";
		
		public static final String IP_REG_EXPRESS			= REG_EXPRESS + "ip";//IP正则表达式
		public static final String USERNAME_REG 			= REG_EXPRESS + "username";//游戏账号的正则表达式
		
		/*********roles************/
		public static final String ROLES					= "roles/";
		public static final String ROLES_PULL				= ROLES + "pull";//可以拉单的角色
		
	}
	/**
	 * 标识符集
	 */
	public static class Identifier{
		public static final String COMMA = ",";
	}
	/**
	 * action中操作结果result集
	 */
	public static class ActionResultList{
		public static final String TO_WORKORDER_PLATFORM = "returnPlatform";//跳转至工单后台系统查询平台页面
		public static final String EXCEPTION = "exception";
		public static final String TO_CACHE_DATA_PLATFORM = "toCacheDataPlatform";//跳转至批量缓存数据操作页面
		public static String SUCCESS_CREATE = "createSuccess";
	}
	/**
	 * session key
	 */
	public static class SessionKey{
		public static final String STAFF 		= "staff";//操作session 中Staff的KEY
		public static final String ROLE 		= "role";//操作session 中Staff的KEY
		public static final String DEPARTMENT 	= "department";//操作session 中Staff的KEY
		public static final String GROUP 		= "group";//操作session 中Staff的KEY
		public static final String PERMISSION 	= "permission";//操作session 中Staff的KEY
	}
	/**
	 * 角色ID集[Role表数据]
	 */
	public static class RoleLevel{
		public static final int MANAGER_ROLE = 0;//主管级以上
		public static final int ADMIN_ROLE = 1;//管理员[ADMIN]
		public static final int MANAGE_ROLE = 2;//客服主管
		public static final int ZUZHANG_ROLE = 3;//客服组长
		public static final int GENARA_ROLE = 4;//普通客服
		public static final int GM_ROLE = 5;//技术GM
		public static final int CHP_ROLE = 6;//产品人员
		public static final int YW_ROLE = 7;//运维人员
		public static final int OTHER_ROLE = 8;//其他
		public static final int VIP_ROLE = 9;//其他
	}
	public static class DefaultValue{
		public static final String DEFAULT_PSW="123456";
	}
	/**
	 * 权限级别
	 */
	public static class PermissionLevel{
		public static final int MANAGE=3;
		public static final int ZHUGUAN=2;
		public static final int ZUZHANG=1;
	}
	/**
	 * 游戏ID
	 * @author dong_jin
	 *
	 */
	public static class Games{
		public static final int FENGYUN=1;
	}
	/**
	 * 问题类型
	 * @author dong_jin
	 *
	 */
	public static class ClassCategory{
		public static final int RECOVERY=1;//角色恢复
		public static final int GAMERUNNING=2;//游戏运行
		public static final int ABNORMAL=3;//游戏异常
		public static final int GOODSLOST=4;//物品丢失
		public static final int SERVERDEFAULT=5;//服务器故障
		public static final int GAMEBUG=6;//游戏BUG
		public static final int REPORT=7;//违规举报
		public static final int OTHERS=8;//其他
		public static final int GAMEISSUE=9;//游戏问题
		public static final int ACCISSUE=10;//账号问题
		public static final int RECHARGE=11;//充值问题
		public static final int ACCOUNTNAME_LOCK=13;//账号解封
		public static final int ADVISORY=30;//咨询问题
		public static final int SUGGESTION=37;//意见建议
		public static final int VIP_COMPLAINT=74;//意见建议
		public static final int VIP_BLESS=75;//vip祝福申请
	}
	/**
	 * 工单处理状态
	 * @author dong_jin
	 *
	 */
	public static class States{
		public static final String UNDEAL="未处理";
		public static final String DEALING="处理中";
		public static final String DEALED="已处理";
		public static final String REPLIED="已回复";
		public static final String RESPONSED="已反馈";
		public static final String LACKINFO="待补充资料";
	}
	/**
	 * 工单操作类型
	 * @author dong_jin
	 *
	 */
	public static class OperTypes{
		public static final String PFREPLY="回复";
		public static final String ASSIGN="派转";
		public static final String DEAL="处理";
		public static final String RESOPNSE="回访";
		public static final String RETURN="退单";
		public static final String LADAN="拉单";
	}
	public static class OperTypeId{
		public static final Integer REPLY=1;//回复
		public static final Integer ASSIGN=2;//派单
		public static final Integer DEAL=3;//处理
		public static final Integer RESPONSE=4;//回访
		public static final Integer SENDBACK=5;//退单
		public static final Integer RETURN=6;//发回
		public static final Integer EXTRA=7;//补充
		public static final Integer DELETE=8;//删除
		public static final Integer LADAN=9;//拉单
		public static final Integer EDITREPLY=10;//回复编辑
		public static final Integer EDITDEAL=11;//处理编辑
		public static final Integer ALTER_STATE=12;//处理编辑
		public static final Integer DELETE_REPLY=13;//回复删除
	}
	/**
	 * 工单来源
	 * @author dong_jin
	 *
	 */
	public static class Source{
		public static final String PINGTAI="平台";
		public static final String INNERGAME="游戏内";
	}
	/**
	 * 工单处理紧急程度
	 * @author dong_jin
	 *
	 */
	public static class Urgency{
		public static final String EXTRAURGENT="特急";
		public static final String URGENT="紧急";
		public static final String GENARA="一般";
	}
	/**
	 * 部门ID
	 * @author dong_jin
	 *
	 */
	public static class DepartmentId{
		public static final int ADMIN=0;
		public static final int NODEPARTMENT=1;//无部门
		public static final int CTDEPARTMENT=2;//客服部
		public static final int YWDEPARTMENT=3;//运维部
		public static final int CHPDEPARTMENT=4;//产品部
		public static final int OTHDEPARTMENT=5;//其他部
	}
	/**
	 * 
	 * @author dong_jin
	 *
	 */
	public static class NodeList{
		public static final String STATE="state";
		public static final String STATE_DESC="stateDesc";
	}
	
	public static class PromptMessage {
		public static String CONFIRM_IS_LOGIN = "请确认在登录状态下操作。";
		public static String SYSTEM_BUSY = "系统忙，请稍候重试。";
		public static String DATABASE_ERROR = "数据库访问异常";
		public static String PASSWORD_ERROR = "密码错误";
		public static String DATA_NOT_FOUND ="数据为空";
		public static String IS_LOGGED_TIP = "本客户端有其他用户登录，您不能修改其他用户信息，要对本用户操作，请重新登录。";

		/** 参数验证提示信息 */
		public static String USERNAME_IS_NULL = "用户名不能为空。";
		public static String USERNAME_IS_NOT_LEGAL = "用户名不合法。";
		public static String PARAM_IS_NULL = "用户名不合法。";
		
		
		public static final String ID_IS_ILLEGAL		      = "非法的的客户端";
		public static String Illegal_OPERATION_ERROR = "非法操作";// 
		
		public static String SUCCESS = "操作成功";
		public static final String PLEASE_LOGIN		      = "你还未登录或登录已过期~";
		
		public static final String EDIT_SUCCESS		      = "修改成功~";
		public static final String EDIT_FAILURE		      = "修改失败，请重试！";
		public static final String DELETE_SUCCESS		  = "删除成功~";
		public static final String DELETE_FAILURE		  = "删除失败，请重试";
		public static final String LADAN_SUCCESS		  = "拉单成功~";
		public static final String LADAN_FAILURE		  = "拉单失败，请重试";
		
	}
	public static class UrlInfo {
		/*******公用路径************/
		public static String SELF_SERVICE				= "/selfService/";
		
		/*********请求的页面路径************/
		public static String DEFAULT_LOGIN_URL 			= SELF_SERVICE + "login.jsp";// 登录页面
		public static String DEFAULT_LOGOUT_URL 		= SELF_SERVICE + "logout.jsp";// 退出页面
		public static String MAIN_URL 					= "selfServiceMain.jsp";// 主页面
		public static String EXTRAL_INFO				="ctExtraInfo";//客服补充资料 上传文件
	}
	/**
	 * state集
	 */
	public static class StateList {
		public static String SUCCESS = "00";// 成功
		public static String DATABASE_ERR = "01";// 数据库访问异常
		public static String USER_NOTFOUND_ERR = "02";// 用户不存在
		public static String PASSWORD_ERR = "03";// 密码错误
		public static String UNKNOWN_ERROR = "04";// 系统忙
		public static String PARAM_ERR = "05";// 参数错误
		public static String DATA_NOT_FOUND         = "12";// 用户未登陆
		public static String Illegal_OPERATION_ERROR = "26";// 非法操作
		

		public static String NOT_IN_SERVICE_ERR = "99";// 该服务已暂停
		
		
	}
	 /**
     * SQL语句集
     */
    public static class SQL{
    	/*********查询************/
    	public static final String GET_CHARGE_LOG			   = "SELECT * FROM paydetail WHERE UPPER(pdusername)=UPPER(?) or UPPER(pdsenduser)=UPPER(?) order by pddate desc";
    	/*********其他***************/
    	public static final String GET_DATA_BY_IP 			   = "SELECT * FROM IP_PROTECTED WHERE TYPE=2 AND VALUE=?";//查询是否有指定IP的数据
    	public static final String GET_USER_BY_USERNAME 	   = "SELECT * FROM MEMBER WHERE UPPER(username)=UPPER(?)";//查询玩家账号是否存在
    	public static final String GET_GAME_SERVER             = "SELECT ID,GAMEID,GAMENAME,AREAID,AREANAME,SERVERID,SERVERNAME FROM server_info WHERE STATE=1";
    	public static final String GET_GAME_SERVER_NAME     = "SELECT ID,GAMEID,GAMENAME,AREAID,AREANAME,SERVERID,SERVERNAME FROM server_info WHERE STATE=1 and SERVERID=?";
    	public static final String GET_GAME_SERVER_URL         = "SELECT SERVERREPURL,SERVERNAME FROM server_info WHERE STATE=1 AND SERVERID=?";//获取游戏服务器地址  STATE=1 AND
		public static final String GET_ROLE_NAME = "select a.username,nickname,level,class_category from fy_login.member a,fy_manager.`character` b ,fy_manager.character_link c,fy_manager.character_attribute d where a.id=c.member_id and b.id=c.id and b.id=d.char_id and a.username=?";
		public static final String GET_ROLE_NAME_BY_ID = "select b.nickname from fy_login.member a,fy_manager.`character` b ,fy_manager.character_link c,fy_manager.character_attribute d where a.id=c.member_id and b.id=c.id and b.id=d.char_id and  b.id=?";

    }
    /**
	 * 数据库表字段常量
	 */
	public static class TableFieldName{
		public static final String SERVER_URL				= "SERVERREPURL";
		public static final String SERVER_NAME				= "SERVERNAME";
	}
	/**
	 * RTX 常量
	 */
	public static class RTX{
		public static final String MSG_TITLE				= "客服中心通知[[您有新派单]]";
		public static final String MSG_PRE_URL				= "[点这里查看|http://work.2211.com/json/query_lookOrOperateWorkOrder?workOrderId=";
		public static final String MSG_SUF_URL				= "如果打不开，请到工单广场查询。\n工单号：";
		public static final String SERVER_URL				= "http://27.115.20.194:8012/sendnotify.cgi";
		public static final String SERVER_URL_TEST			= "http://192.168.179.2:8012/alert.cgi";
		public static final String TITLE					= "title";
		public static final String MSG						= "msg";
		public static final String RECEIVER					= "receiver";
		public static final String DELAY					= "delaytime";
		
		public static final String PROCESS_TITLE				= "[[工单已处理]]";
		public static final String PROCESS_TITLE_PRE_URL		= "！！！【 警报 】！！！\n！！！【 警报 】！！！\n！！！【 警报 】！！！\n";
		public static final String PROCESS_TITLE_SUF_URL		= "[关闭提示|http://work.2211.com/stopAlert.jsp]";
	} 
	/**
	 * ExcelName 常量
	 */
	public static class ExcelName{
		public static final String WORK_ORDER_ID			= "工单号";
		public static final String USER_ACCOUNT				= "玩家账号";
		public static final String ISSUE_TYPE				= "问题类型";
		public static final String DEALER					= "处理人";
		public static final String OPERATOR					= "操作人";
		public static final String CREATE_TIME				= "创建时间";
		public static final String SOURCE					= "工单来源";
		public static final String SERVER					= "服务器";
		public static final String DESCRIPTION				= "问题描述";
		public static final String OPER_DESCRIP				= "操作描述";
		public static final String IMG_LINK					= "图片链接";
		public static final String OVER_TIME				= "结案时间";
		
		public static final String ACC_NAME					= "员工账号";
		public static final String BEGIN_TIME				= "起始时间";
		public static final String END_TIME					= "结束时间";
		public static final String CREATE_COUNT				= "创建数量";
		public static final String ASSIGN_COUNT				= "派转数量";
		public static final String RESPONSE_COUNT			= "回访数量";
		public static final String ASSING_MSG				= "派转信息";
		public static final String OPER_TIME				= "操作时间";
		public static final String OPER_TYPE				= "操作类型";
		public static final String COUNTS					= "数量";
		public static final String CURRENT_STATE			= "当前状态";
		
		public static final String FINISH_COUNT				= "完成数量";
		public static final String TOTAL_COUNT				= "总计数量";
		public static final String RATE						= "完成率";
		public static final String RATE_TEMP				= "##.##%";
		
		public static final String EVALUA_COUNT				= "评价总数";
		public static final String DATE						= "日期";
		public static final String SATISFACTION				= "满意度";
	} 
	/**
	 * URL 常量
	 */
	public static class URL{
		public static final String PRE_URL				= "preUrl";
		public static final String CS_2211_COM			= "http://cs.2211.com";
		public static final String WORK_2211_COM_LOGIN	= "/ctService/htLogon.jsp?";
	} 
	/**
	 * 工单操作 提示
	 */
	public static class OperAlert{
		public static final String DEALED				= "该工单为已处理或已回复状态";
		public static final String REPLIED				= "该工单为已回复状态";
		public static final String ASSING_NO_DEAL		= "该工单已派单，尚未处理";
		public static final String NO_REPLY_TO_RESP		= "该工单未回复，不能回访";
		public static final String ASSING_SUCCESS		= "派单成功！";
		public static final String DEAL_SUCCESS			= "处理成功！";
		public static final String RTX_NOTICE_SUCCESS	= "RTX通知成功！";
		public static final String DELETE_SUCCESS		= "删除成功！";
		public static final String BATCH_DELETE_SUCCESS	= "批量删除成功！";
		public static final String BATCH_RETURN_SUCCESS	= "批量退单成功！";
		public static final String RESPONSE_SUCCESS		= "回访完毕！";
		public static final String BATCH_SUCCESS		= "批量回复成功！";
		public static final String PLEASE_PICK_GROUP	= "请选择一个小组！";
	} 
	/**
	 * 分页
	 */
	public static class PAGE{
		public static final String CURRENT_PAGE			= "currentPage";
		public static final String PAGE_SIZE			= "pageSize";
		public static final String TOTAL_PAGE			= "totalPage";
		public static final String TOTAL_COUNT			= "totalCount";
		public static final String BATCH_PERMISSION		= "batchOperPermission";
		public static final String EMPTY				= "empty";
		public static final String WORK_ORDER_LIST		= "wokeOrderList";
		public static final String STAFF_LIST			= "staffList";
		public static final String CURRENT_TAG			= "currentTag";
		public static final String C_TAG				= "cTag";
		public static final String NO_RESULT			= "暂无记录";
		public static final String NO_STAFF				= "该小组无派单成员";
	} 
	/**
	 * DWR 常量
	 */
	public static class DWR{
		public static final String SCRIPT_MAP			= "scriptMap";
		public static final String WCTX					= "wctx";
		public static final String SHOW_M				= "showM";
		public static final String WO					= "wo";
		public static final String VIEW_LIST			= "viewList";
		public static final String SHOW_NOTICE			= "showNotice";
		public static final String FAQ					= "faq";
		public static final String GET_WORK_ORDER_MAP	= "getWorkOrderMap";
		public static final String GET_ORDER_COUNT		= "getOrderCount";
		public static final String ACC_NAME				= "accName";
		public static final String GROUP_NAME			= "groupName";
		public static final String GROUP_COUNT			= "groupCount";
		public static final String SHOW_GET_ORDER		= "showGetOrder";
		public static final String SHOW_ASSIGN_NOTICE	= "showAssignNotice";
		public static final String SHOW_IM				= "showIM";
	} 
	public static String SOURCE_URL_KEY = "loginURL";//获取session中源页面的URL的KEY
	public static final String COOKIE_DOMAIN = ".2211.com";//COOKIE的域名区域
	public static String SESSION_COOKIE_KEY = "ss";// 用于从cookie,session中取值的KEY,以判断用户是否已登录过
	public static String DEFAULT_CHARSET = "UTF-8";// 默认的编码
	public static String DEFAULT_CONTENT_TYPE = "text/xml;charset=UTF-8";// 默认的编码
	public static String GET_SESSION_KEY = "LoginUser";// 将用户信息user保存进或从session取出的KEY
	public static final String MEMBER_INFO_KEY = "member";//操作session中的用户信息的key的
	public static  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:s");//
	public static  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//
}
