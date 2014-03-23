package com.hy.wo.po;

import java.util.Date;

/**
 * 工单操作记录类
 * @author dong_jin
 *
 */
public class WorkOrderOper {
	private long id;
	private WorkOrder workOrder;//所属工单
	private String workerParent;//处理人小组或部门
	private String worker;//处理人
	private String content;//处理内容
	private Date operTime;//处理时间
	private OperType operType;//操作类型
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public WorkOrder getWorkOrder() {
		return workOrder;
	}
	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}
	public String getWorkerParent() {
		return workerParent;
	}
	public void setWorkerParent(String workerParent) {
		this.workerParent = workerParent;
	}
	public String getWorker() {
		return worker;
	}
	public void setWorker(String worker) {
		this.worker = worker;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public OperType getOperType() {
		return operType;
	}
	public void setOperType(OperType operType) {
		this.operType = operType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((operTime == null) ? 0 : operTime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkOrderOper other = (WorkOrderOper) obj;
		if (operTime == null) {
			if (other.operTime != null)
				return false;
		} else if (!operTime.equals(other.operTime))
			return false;
		return true;
	}
	
}
