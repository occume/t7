package com.hy.wo.po;

import java.util.Date;

/**
 * CallCenter的一个类
 * @author dong_jin
 *
 */
public class Caller {
	private long id;
	private String accName;					//账号
	private String userName;				//姓名
	private String caller;							//联系电话
	private String userType;					//用户类型
	private Date createTime=new Date();				//创建时间
	
	public Caller(){}
	
	public Caller(String a,String u,String c,String t){
		this.accName=a;
		this.userName=u;
		this.userType=t;
		this.caller=c;
	}
	
	
	public Caller(Object[] a) {
	//	Object[] a=(Object[]) o;
		this.accName=String.valueOf(a[0]);
		this.userName=String.valueOf(a[1]);
		this.userType=String.valueOf(a[2]);
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCaller() {
		return caller;
	}
	public void setCaller(String caller) {
		this.caller = caller;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
