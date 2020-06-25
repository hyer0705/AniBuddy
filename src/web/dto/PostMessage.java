package web.dto;

import java.util.Date;

public class PostMessage {

	private int pmNo;
	private int pmSenderId;
	private String title;
	private String content;
	private Date sendDate;
	private int pmRecipientId;
	private String isCheck;
	
	@Override
	public String toString() {
		return "PostMessage [pmNo=" + pmNo + ", pmSenderId=" + pmSenderId + ", title=" + title + ", content=" + content
				+ ", sendDate=" + sendDate + ", pmRecipientId=" + pmRecipientId + ", isCheck=" + isCheck + "]";
	}

	public int getPmNo() {
		return pmNo;
	}

	public void setPmNo(int pmNo) {
		this.pmNo = pmNo;
	}

	public int getPmSenderId() {
		return pmSenderId;
	}

	public void setPmSenderId(int pmSenderId) {
		this.pmSenderId = pmSenderId;
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

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public int getPmRecipientId() {
		return pmRecipientId;
	}

	public void setPmRecipientId(int pmRecipientId) {
		this.pmRecipientId = pmRecipientId;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	

}
