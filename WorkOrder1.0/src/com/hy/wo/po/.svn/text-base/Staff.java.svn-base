package com.hy.wo.po;

import java.io.Serializable;

/**
 * 客服员工信息
 * @author dong_jin
 *
 */
public class Staff implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String zuoXi;
	private String accName;
	private String password;
	private String nickName;
	private Role role;
	private Group group;
	private Group backUp;//备用小组
	private Department department;
	private boolean isdelete;
	
	
	public Staff(){}
	
	public Staff(String accName,String password){
		this.accName=accName;
		this.password=password;
	}
	public boolean isIsdelete() {
		return isdelete;
	}
	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}
	public Group getBackUp() {
		return backUp;
	}
	public void setBackUp(Group backUp) {
		this.backUp = backUp;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getZuoXi() {
		return zuoXi;
	}

	public void setZuoXi(String zuoXi) {
		this.zuoXi = zuoXi;
	}
	
}
