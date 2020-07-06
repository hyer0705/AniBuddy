package web.dto;

import java.util.Date;

public class ShareComment {
	private int comment_no;
	private int post_no;
	private int user_no;
	private int admin_no;
	private String content;
	private Date write_date;
	private String userid;
	private int rnum;
	@Override
	public String toString() {
		return "ShareComment [comment_no=" + comment_no + ", post_no=" + post_no + ", user_no=" + user_no + ", content="
				+ content + ", write_date=" + write_date + ", userid=" + userid + ", rnum=" + rnum + "]";
	}
	public int getAdmin_no() {
		return admin_no;
	}
	public void setAdmin_no(int admin_no) {
		this.admin_no = admin_no;
	}
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	
	
	
	
}
