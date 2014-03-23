package com.hy.wo.po;

import java.util.Set;

/**
 * 员工权限类
 * @author dong_jin
 *
 */
public class Permission implements Comparable<Permission>{
	@Override
	public boolean equals(Object obj) {
		Permission per=(Permission)obj;
		return id==per.id;
	}
	private int id;
	private String name;
	private String operation;
	private Permission parent;
	private Set<Permission> children;
	private Set<Role> roles;
	
	public Set<Permission> getChildren() {
		return children;
	}
	public void setChildren(Set<Permission> children) {
		this.children = children;
	}
	public Permission getParent() {
		return parent;
	}
	public void setParent(Permission parent) {
		this.parent = parent;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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
	public int compareTo(Permission o) {
		return id-o.id;
	}
	
}
