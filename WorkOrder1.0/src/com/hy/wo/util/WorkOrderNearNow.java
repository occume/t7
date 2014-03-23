package com.hy.wo.util;

import java.util.Date;

/**
 * 员工近期处理的工单
 * @author dong_jin
 *
 */
public class WorkOrderNearNow {
	private long id;
	private String accName;
	private String issueType;
	private Date operTime;
	private String reply;
	public String getAccName() {
		return accName;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	
}
