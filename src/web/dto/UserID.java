package web.dto;

import java.util.Date;

// join 연산 map 대신 dto로 사용
public class UserID {

	private int postno;
	private String title;
	private String userid;
	private int hit;
	private Date writedate;
	private String range;
	private String content;
	private int userno;
	private int usernick;
	private String isexpert;
	private int groupno; //답글을 쓸 글번호
	private int groupord; //들여쓰기
	private int depth; //글 순서
	
	public int getGroupno() {
		return groupno;
	}
	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}
	public int getGroupord() {
		return groupord;
	}
	public void setGroupord(int groupord) {
		this.groupord = groupord;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getIsexpert() {
		return isexpert;
	}
	public void setIsexpert(String isexpert) {
		this.isexpert = isexpert;
	}
	public int getUsernick() {
		return usernick;
	}
	public void setUsernick(int usernick) {
		this.usernick = usernick;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public int getPostno() {
		return postno;
	}
	public void setPostno(int postno) {
		this.postno = postno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	@Override
	public String toString() {
		return "UserID [postno=" + postno + ", title=" + title + ", userid=" + userid + ", hit=" + hit + ", writedate="
				+ writedate + "]";
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	
}
