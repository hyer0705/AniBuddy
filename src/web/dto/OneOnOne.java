// 낙타봉 표기법으로 바꿈

package web.dto;

import java.util.Date;

public class OneOnOne {
	
	private int oneononeNo;
	private String title;
	private String content;
	private Date writeDate;
	private boolean replyProgress;
	private int replyNo;
	private String condition;
	private int userNo;
	@Override
	public String toString() {
		return "OneOnOne [oneononeNo=" + oneononeNo + ", title=" + title + ", content=" + content + ", writeDate="
				+ writeDate + ", replyProgress=" + replyProgress + ", replyNo=" + replyNo + ", condition=" + condition
				+ ", userNo=" + userNo + "]";
	}
	public int getOneononeNo() {
		return oneononeNo;
	}
	public void setOneononeNo(int oneononeNo) {
		this.oneononeNo = oneononeNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public boolean isReplyProgress() {
		return replyProgress;
	}
	public void setReplyProgress(boolean replyProgress) {
		this.replyProgress = replyProgress;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
}
