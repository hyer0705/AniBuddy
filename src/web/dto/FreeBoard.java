package web.dto;


import java.util.Date;

public class FreeBoard {

	private int postno;
	private int boardno;
	private String title;
	private String content;
	private Date writedate;
	private int hit;
	private String range;
	private int userno;
	
	// 여기 지우기
	// FreeBoard TABLE 컬럼만 사용할 것
	private String userid;
	private String usernick;
	private String isexpert;
	
	@Override
	public String toString() {
		return "FreeBoard [postno=" + postno + ", boardno=" + boardno + ", title=" + title + ", content=" + content
				+ ", writedate=" + writedate + ", hit=" + hit + ", range=" + range + ", userno=" + userno + ", userid="
				+ userid + ", usernick=" + usernick + ", isexpert=" + isexpert + "]";
	}
	public int getPostno() {
		return postno;
	}
	public void setPostno(int postno) {
		this.postno = postno;
	}
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
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
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsernick() {
		return usernick;
	}
	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}
	public String getIsexpert() {
		return isexpert;
	}
	public void setIsexpert(String isexpert) {
		this.isexpert = isexpert;
	}
	
}
