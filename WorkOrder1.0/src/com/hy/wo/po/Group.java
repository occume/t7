package com.hy.wo.po;

import java.util.Date;

/**
 * 员工分组类
 * @author dong_jin
 *
 */
public class Group {
	private int id;
	private String name;
	private Date createDate;
	private int departId;
	
	public int getDepartId() {
		return departId;
	}
	public void setDepartId(int departId) {
		this.departId = departId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
