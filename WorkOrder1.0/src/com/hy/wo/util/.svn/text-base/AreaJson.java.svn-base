package com.hy.wo.util;

import com.hy.wo.po.Issue;

public class AreaJson {
	private int id;
	private String name;
	private String accName;
	private int pid;
	
	public AreaJson(){}
	
	public AreaJson(Issue is){
		this.id=is.getId();
		this.pid=is.getParent().getId();
		this.name=is.getName();
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


	public int getPid() {
		return pid;
	}


	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	
}
