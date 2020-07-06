// 낙타봉 표기법으로 다 바꿈

package web.dto;

import java.util.Date;

public class HelpPost {

	private int postNo;
	private int boardNo;
	private String title;
	private String content;
	private Date writeDate;
	private int hit;
	private String dealProgress;
	private String animalSpecies;
	private String dealType;
	private int userNo;
	private String userid;
	private String usernick;
	@Override
	public String toString() {
		return "SharePost [postNo=" + postNo + ", boardNo=" + boardNo + ", title=" + title + ", content=" + content
				+ ", writeDate=" + writeDate + ", hit=" + hit + ", dealProgress=" + dealProgress + ", animalSpecies="
				+ animalSpecies + ", dealType=" + dealType + ", userNo=" + userNo + ", userid=" + userid + ", usernick="
				+ usernick + "]";
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getDealProgress() {
		return dealProgress;
	}
	public void setDealProgress(String dealProgress) {
		this.dealProgress = dealProgress;
	}
	public String getAnimalSpecies() {
		return animalSpecies;
	}
	public void setAnimalSpecies(String animalSpecies) {
		this.animalSpecies = animalSpecies;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
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
	
	
	
	
}
