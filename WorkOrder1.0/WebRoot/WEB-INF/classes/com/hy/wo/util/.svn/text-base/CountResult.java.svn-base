package com.hy.wo.util;

/**
 * 排行榜数据
 * @author dong_jin
 *
 */
public class CountResult implements Comparable<CountResult>{
	public CountResult(){};
	public CountResult(String name){
		this.groupName=name;
	}
	public CountResult(int id){
		this.groupId=id;
	}
	public CountResult(int id,String name){
		this.groupId=id;
		this.groupName=name;
	}
	private int groupId;
	private String groupName;//组名称
	private int countGet=0;//拉单数量
	private int countDeal=0;//已处理数量
	private int countReply=0;//已回复数量
	private int countDealing;
	private int countLackInfo;
	private int countAssigning;
	private int countAssigned;
	private int countDealed;
	private int countNoAssign;
	
	
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getCountGet() {
		return countGet;
	}
	public void setCountGet(int countGet) {
		this.countGet = countGet;
	}
	public int getCountDeal() {
		return countDeal;
	}
	public void setCountDeal(int countDeal) {
		this.countDeal = countDeal;
	}
	public int getCountReply() {
		return countReply;
	}
	public void setCountReply(int countReply) {
		this.countReply = countReply;
	}
	public int getCountDealing() {
		return countDealing;
	}
	public void setCountDealing(int countDealing) {
		this.countDealing = countDealing;
	}
	public int getCountLackInfo() {
		return countLackInfo;
	}
	public void setCountLackInfo(int countLackInfo) {
		this.countLackInfo = countLackInfo;
	}
	public int getCountAssigning() {
		return countAssigning;
	}
	public void setCountAssigning(int countAssigning) {
		this.countAssigning = countAssigning;
	}
	public int getCountAssigned() {
		return countAssigned;
	}
	public void setCountAssigned(int countAssigned) {
		this.countAssigned = countAssigned;
	}
	public int getCountDealed() {
		return countDealed;
	}
	public void setCountDealed(int countDealed) {
		this.countDealed = countDealed;
	}
	public int getCountNoAssign() {
		return countNoAssign;
	}
	public void setCountNoAssign(int countNoAssign) {
		this.countNoAssign = countNoAssign;
	}
	public int compareTo(CountResult o) {
		if(o.countGet==0)
			return -1;
		if(this.countGet==0)
			return 1;
//		DecimalFormat format=new DecimalFormat("0.#######");
//		String douO=format.format(o.countReply/o.countGet);
//		String thisO=format.format(this.countReply/this.countGet);
//		double dbO=Double.valueOf(douO)*10000000;
//		double dbthis=Double.valueOf(thisO)*10000000;
//		int returnVal=(int)(dbthis-dbO);
		//return returnVal;
		return o.countReply-this.countReply;
	}
	
}
