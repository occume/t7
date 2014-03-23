package com.hy.wo.util;

import java.io.Serializable;
import java.util.Date;

/**
 * 游戏内提交问题防刷 VO
 * @author dong_jin
 *
 */
public class InnerGameLimit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2035229502270014797L;
	private String accName;
	private Date LastSubmitTime;
	private Date LastLockTime;
	private int count;
	private String lockState="UNLOCK";
	private int first=0;
	
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	
	public Date getLastSubmitTime() {
		return LastSubmitTime;
	}
	public void setLastSubmitTime(Date lastSubmitTime) {
		LastSubmitTime = lastSubmitTime;
	}
	
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public Date getLastLockTime() {
		return LastLockTime;
	}
	public void setLastLockTime(Date lastLockTime) {
		LastLockTime = lastLockTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getLockState() {
		return lockState;
	}
	public void setLockState(String lockState) {
		this.lockState = lockState;
	}

}
