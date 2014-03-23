package com.hy.wo.po;

import java.util.Date;
/**
 * 玩家问题补充类
 * @author dong_jin
 *
 */
public class IssueAdditional {
	private int id;
	private String name;
	private String content;
	private Date addDate;
	private boolean fromUser=true;
	private WorkOrder workOrder;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WorkOrder getWorkOrder() {
		return workOrder;
	}
	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public boolean isFromUser() {
		return fromUser;
	}
	public void setFromUser(boolean fromUser) {
		this.fromUser = fromUser;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addDate == null) ? 0 : addDate.hashCode());
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
		IssueAdditional other = (IssueAdditional) obj;
		if (addDate == null) {
			if (other.addDate != null)
				return false;
		} else if (!addDate.equals(other.addDate))
			return false;
		return true;
	}
	
}
