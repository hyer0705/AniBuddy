package web.dto;

import java.util.Date;

public class ExpertBoard {

	private int postno;
	private int boardno;
	private String title;
	private String content;
	private Date writedate;
	private int hit;
	private String range;
	private int userno;
	private int groupno; //답글을 쓸 글번호
	private int groupord; //들여쓰기
	private int depth; //글 순서
	
	// 여기는 바꾸기
	// 테이블이랑 같은 컬럼만 사용할 것
	private String userid;
	private String usernick;
	private String isexpert;
	
	

	public String getIsexpert() {
		return isexpert;
	}

	public void setIsexpert(String isexpert) {
		this.isexpert = isexpert;
	}

	public String getUsernick() {
		return usernick;
	}

	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "ExpertBoard [postno=" + postno + ", boardno=" + boardno + ", title=" + title + ", content=" + content
				+ ", writedate=" + writedate + ", hit=" + hit + ", range=" + range + ", userno=" + userno + ", groupno="
				+ groupno + ", groupord=" + groupord + ", depth=" + depth + "]";
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

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setId(String attribute) {
		// TODO Auto-generated method stub
		
	}
	
}

