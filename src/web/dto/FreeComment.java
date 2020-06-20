package web.dto;

import java.util.Date;

public class FreeComment {

	private int commentno;
	private int postno;
	private int userno;
	private String content;
	private Date writedate;
	private String isreply;
	private int parentcomment;
	private int isdelete;
	
	// 이거 지우기
	// FreeComment에 있는 컬럼만 가져와서 사용할 것
	private String userid;
	private int rnum;

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	@Override
	public String toString() {
		return "FreeComment [commentno=" + commentno + ", postno=" + postno + ", userno=" + userno + ", content="
				+ content + ", writedate=" + writedate + ", isreply=" + isreply + ", parentcomment=" + parentcomment
				+ ", isdelete=" + isdelete + ", userid=" + userid + "]";
	}

	public int getCommentno() {
		return commentno;
	}

	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}

	public int getPostno() {
		return postno;
	}

	public void setPostno(int postno) {
		this.postno = postno;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWritedate() {
		return writedate;
	}

	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}

	public String getIsreply() {
		return isreply;
	}

	public void setIsreply(String isreply) {
		this.isreply = isreply;
	}

	public int getParentcomment() {
		return parentcomment;
	}

	public void setParentcomment(int parentcomment) {
		this.parentcomment = parentcomment;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}
