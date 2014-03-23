package com.hy.wo.po;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.hy.wo.util.MyUtil;
/**
 * 回访记录表
 * @author dong_jin
 *
 */
public class SubCallBack implements Comparable{
	
	private int id;
	private int tid;
	private String account;
	private String username;
	private int state;
	private String content;
	private String operator;
	private String caller;
	private Date callbacktime;
	private CallBack callBack;
	
	public SubCallBack(){}
	
	public SubCallBack(String str){
		String[] attrs=str.trim().split("\t");
		int m=attrs.length;
		this.account=attrs[0];
		this.caller=attrs[1];
		if(m==3){
			this.username=attrs[2];
		}
	}
	
	public SubCallBack(HttpServletRequest r){
		//this.tid=Integer.valueOf(r.getParameter("callbackid"));
		
		String sta=r.getParameter("callbackstate");
		if(!MyUtil.isBlankOrNull(sta)){
			this.state=Integer.valueOf(sta);
		}else{
			this.state=-1;
		}
		
		String ope=r.getParameter("operator");
		if(!MyUtil.isBlankOrNull(ope)){
			this.operator=ope;
		}
	}
	public SubCallBack(HttpServletRequest r,String str){
		this.id=Integer.valueOf(r.getParameter("callbackid"));
		this.content=r.getParameter("content");
		this.caller=r.getParameter("caller");
		this.account=r.getParameter("account");
		this.username=r.getParameter("username");
		
		
		String sta=r.getParameter("state");
		if(!MyUtil.isBlankOrNull(sta)){
			this.state=Integer.valueOf(sta);
		}else{
			this.state=-1;
		}
		
		String ope=r.getParameter("operator");
		if(!MyUtil.isBlankOrNull(ope)){
			this.operator=ope;
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getCallbacktime() {
		return callbacktime;
	}
	public void setCallbacktime(Date callbacktime) {
		this.callbacktime = callbacktime;
	}
	public CallBack getCallBack() {
		return callBack;
	}
	public void setCallBack(CallBack callBack) {
		this.callBack = callBack;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public int compareTo(Object o) {
		return 1;
	}
	
}
