package com.hy.wo.po;
/**
 * 上传的文件信息
 * @author dong_jin
 *
 */
public class FileUpLoad {
	private int id;
	private String path;
	private WorkOrderUserInfo userinfo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public WorkOrderUserInfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(WorkOrderUserInfo userinfo) {
		this.userinfo = userinfo;
	}
}
