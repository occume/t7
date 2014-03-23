package com.hy.wo.util.constants;

import  static com.hy.wo.util.Constants.DepartmentId.*;

/**
 * 小组枚举类
 * @author dong_jin
 *
 */
public enum Department {
	NO(NODEPARTMENT,"无部门"),
	CTS(CTDEPARTMENT,"客服部"),
	YW(YWDEPARTMENT,"运维部"),
	CHP(CHPDEPARTMENT,"产品部"),
	OHTERS(OTHDEPARTMENT,"其他部门");
	
	public static  String getName(int id){
		String reVal="";
		for(Department d:Department.values()){
			if(d.getDid()==id){
				reVal=d.getDname();
			}
		}
		return reVal;
	}
	
	Department(int id,String dname){
		this.did=id;
		this.dname=dname;
	}
	
	private String dname;
	private int did;
	
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	
}
