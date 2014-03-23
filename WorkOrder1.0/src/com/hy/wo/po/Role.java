package com.hy.wo.po;

import java.util.HashSet;
import java.util.Set;

/**
 * 员工角色类
 * @author dong_jin
 *
 */
public class Role {
	private int id;
	private String name;
	private int level;
	private Set<Permission> permissions=new HashSet<Permission>();
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
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
	
}
