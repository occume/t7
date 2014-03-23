package com.hy.wo.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * 工单额外信息
 * @author dong_jin
 *
 */
public class WorkOrderAdditional {
	private long id;
	private WorkOrder workOrder;//所属工单
	private String additionalProblem;//补充问题
	private Date offlineTime; //最后下线时间
	private Date happenEndDate;//物品丢失结束时间
	private String ip;//常用登录ip
	private String username;//违规角色
	private ReportKinds report;//违规类型
	private Advisory advisory;//咨询类型
	private String currentVersion;//当前游戏版本
	/*物品丢失*/
	private int purpleGold=0;
	private int gameCoin=0;
	private Set<Goods> goodsSet =new HashSet<Goods>();
		
	public Set<Goods> getGoodsSet() {
		return goodsSet;
	}
	public void setGoodsSet(Set<Goods> goodsSet) {
		this.goodsSet = goodsSet;
	}
	public int getPurpleGold() {
		return purpleGold;
	}
	public void setPurpleGold(int purpleGold) {
		this.purpleGold = purpleGold;
	}
	public int getGameCoin() {
		return gameCoin;
	}
	public void setGameCoin(int gameCoin) {
		this.gameCoin = gameCoin;
	}
	public String getCurrentVersion() {
		return currentVersion;
	}
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}
	public Advisory getAdvisory() {
		return advisory;
	}
	public void setAdvisory(Advisory advisory) {
		this.advisory = advisory;
	}
	public WorkOrder getWorkOrder() {
		return workOrder;
	}
	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}
	
	public Date getHappenEndDate() {
		return happenEndDate;
	}
	public void setHappenEndDate(Date happenEndDate) {
		this.happenEndDate = happenEndDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAdditionalProblem() {
		return additionalProblem;
	}
	public void setAdditionalProblem(String additionalProblem) {
		this.additionalProblem = additionalProblem;
	}
	public Date getOfflineTime() {
		return offlineTime;
	}
	public void setOfflineTime(Date offlineTime) {
		this.offlineTime = offlineTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public ReportKinds getReport() {
		return report;
	}
	public void setReport(ReportKinds report) {
		this.report = report;
	}
}
