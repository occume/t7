package com.hy.wo.util.constants;

import java.util.HashMap;
import java.util.Map;
/**
 * 问题类型枚举类
 * @author dong_jin
 *
 */
public enum Issue {
	Acount(1,"账号问题",null){},
	AcountLock(2,"账号被封",Acount){},
	AcountBack(2,"找回账号",Acount){},
	AcountSecurity(2,"账号安全",Acount){},
	
	Game(1,"游戏问题",null){},
	RoleRecovery(2,"角色恢复",Game),
	RoleAbnomal(2,"角色异常",Game),
	GoodsLost(2,"物品丢失",Game),
	ServerDefault(2,"服务器故障",Game),
	BUG(2,"游戏BUG",Game),
	RUN(2,"游戏运行",Game),
	GAMEKNOWLEDGE(2,"游戏知识",Game),
	GAMEABNOMAL(2,"游戏异常",Game),
	BUGSUGESTTION(2,"BUG意见",Game),
	GAMEBUG(2,"游戏BUG",Game),
	SUGESTTION(2,"意见建议",Game),
	RECHARGEISSUE(2,"充值问题",Game),
	ROLEDATA(2,"角色数据异常",Game),
	SERVERISSUE(2,"服务器问题",Game),
	MORE(2,"其它问题",Game),
	NOTICE(2,"通知",Game);
	
	public static Map IssueTree(){
		return new HashMap();
	}
	
	public static String getClassNameByIname(String name){
		Issue[] issue=values();
		for(Issue i :issue){
			if(i.getIname().equals(name)){
				return i.name();
			}
		}
		return "";
	}
	
	public static String getInameByClassName(String name){
		Issue[] issue=values();
		for(Issue i :issue){
			if(i.name().equals(name)){
				return i.getIname();
			}
		}
		return "";
	}
	
	Issue(int level,String name,Issue parent){
		this.level=level;
		this.iname=name;
		this.parent=parent;
	}
	
	public String getIname(){
		return this.iname;
	}
	public int getLevel(){
		return this.level;
	}
	public Issue getParent(){
		return this.parent;
	}
	private String iname;
	private int level;
	private Issue parent;
	
}
