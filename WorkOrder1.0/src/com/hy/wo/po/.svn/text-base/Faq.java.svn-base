package com.hy.wo.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * CT Faq
 * @author dong_jin
 *
 */
public class Faq {
		private int id;
		//private Faq parent ;
		private String title;//标题
		private String sortType;//类别
		private String type;//所属问题类型
		private String gameName;//所属游戏
		private String descrip;
		private Date createTime;
		private Date lastEditTime;
		private String noticeType;//通知类型
		private String status;	//状态
		private String version;//版本号
		private boolean visible=false;//玩家可见
		private boolean toUser;//
		private boolean isdelete=false;
		private String src;
		private Set<FaqRep> reps = new HashSet<FaqRep>();
		private Set<FaqAnswer> answers = new HashSet<FaqAnswer>();
		
		public void addRep(FaqRep fr){
			reps.add(fr);
		}
		
		public Date getLastEditTime() {
			return lastEditTime;
		}
		public void setLastEditTime(Date lastEditTime) {
			this.lastEditTime = lastEditTime;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
		public String getSrc() {
			return src;
		}
		public void setSrc(String src) {
			this.src = src;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public String getNoticeType() {
			return noticeType;
		}
		public void setNoticeType(String noticeType) {
			this.noticeType = noticeType;
		}
		public boolean isToUser() {
			return toUser;
		}
		public void setToUser(boolean toUser) {
			this.toUser = toUser;
		}
		public String getSortType() {
			return sortType;
		}
		public void setSortType(String sortType) {
			this.sortType = sortType;
		}
		public boolean isVisible() {
			return visible;
		}
		public void setVisible(boolean visible) {
			this.visible = visible;
		}
		public String getGameName() {
			return gameName;
		}
		public void setGameName(String gameName) {
			this.gameName = gameName;
		}
		public String getDescrip() {
			return descrip;
		}
		public void setDescrip(String descrip) {
			this.descrip = descrip;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public boolean isIsdelete() {
			return isdelete;
		}
		public void setIsdelete(boolean isdelete) {
			this.isdelete = isdelete;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Set<FaqRep> getReps() {
			return reps;
		}
		public void setReps(Set<FaqRep> reps) {
			this.reps = reps;
		}

		public Set<FaqAnswer> getAnswers() {
			return answers;
		}

		public void setAnswers(Set<FaqAnswer> answers) {
			this.answers = answers;
		}
		
}
