package com.hy.wo.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 玩家信息
 * @author dong_jin
 *
 */
public class WorkOrderUserInfo {
	private long id;
	private WorkOrder workOrder;//所属工单
	private String accountname;//游戏账号
	private String username;//角色名称
	private String realname;//真实姓名
	private String vipGrade;//vip等级
	private ClassCategory classCategory;//角色职业
	private int level;//角色职业
	private Games game;//游戏名称
	private Areas area;//游戏大区
	private Servers server;//游戏服务器
	private String tel;//联系电话
	private String mail;//联系邮箱
	private String qq;//联系qq
	private Issue lvlOne;//问题大类型
	private Issue lvlTwo;//问题第二类型
	private String memo;//问题描述
	private Date happendate;//提问时间
	
	private Set<FileUpLoad> files=new HashSet<FileUpLoad>();
	//private FileUpLoad files;//上传文件---一对一
	
	public WorkOrderUserInfo(){}
	
	public WorkOrderUserInfo(ResultSet rs) {
	//	System.out.println("WorkOrderUserInfo");
		try {
			this.accountname=rs.getString(1);
			//System.out.println(this.accountname);
			this.username=rs.getString(2);
			//System.out.println(this.username);
			this.level=Integer.valueOf( rs.getObject(3).toString());
		//	System.out.println(this.level);
			ClassCategory cc=new ClassCategory();
			cc.setName(rs.getString(4));
			
			this.classCategory=cc;
			//System.out.println(this.classCategory.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
public Set<FileUpLoad> getFiles() {
		return files;
	}
	public void setFiles(Set<FileUpLoad> files) {
		this.files = files;
	}
	//	public FileUpLoad getFiles() {
//		return files;
//	}
//	public void setFiles(FileUpLoad files) {
//		this.files = files;
//	}
	public WorkOrder getWorkOrder() {
		return workOrder;
	}
	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public ClassCategory getClassCategory() {
		return classCategory;
	}
	public void setClassCategory(ClassCategory classCategory) {
		this.classCategory = classCategory;
	}
	
	public String getVipGrade() {
		return vipGrade;
	}

	public void setVipGrade(String vipGrade) {
		this.vipGrade = vipGrade;
	}

	public Games getGame() {
		return game;
	}
	public void setGame(Games game) {
		this.game = game;
	}
	public Areas getArea() {
		return area;
	}
	public void setArea(Areas area) {
		this.area = area;
	}
	public Servers getServer() {
		return server;
	}
	public void setServer(Servers server) {
		this.server = server;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Issue getLvlOne() {
		return lvlOne;
	}
	public void setLvlOne(Issue lvlOne) {
		this.lvlOne = lvlOne;
	}
	public Issue getLvlTwo() {
		return lvlTwo;
	}
	public void setLvlTwo(Issue lvlTwo) {
		this.lvlTwo = lvlTwo;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getHappendate() {
		return happendate;
	}
	public void setHappendate(Date happendate) {
		this.happendate = happendate;
	}
	public String toString(){
		return this.accountname+">>"+this.username+">>"+this.classCategory;
	}
}
