package com.hy.wo.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

/**
 * 导入回访信息
 * @author dong_jin
 *
 */
public class CallBack {
	
	private int id;
	private Date attime;
	private String content;
	private String operator;
	private String title;
	private Set<SubCallBack> callBacks=new TreeSet<SubCallBack>();
	
	public CallBack(){}
	
	public CallBack(HttpServletRequest r){
		this.content=r.getParameter("content");
		this.operator=r.getParameter("operator");
		this.title=r.getParameter("title");
		this.attime=new Date();
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAttime() {
		return attime;
	}
	public void setAttime(Date attime) {
		this.attime = attime;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Set<SubCallBack> getCallBacks() {
		return callBacks;
	}

	public void setCallBacks(Set<SubCallBack> callBacks) {
		this.callBacks = callBacks;
	}

}
