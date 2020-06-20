package web.dto;

import java.util.Date;

public class UserTB {

	/* char(1) 을 boolean 고민중~!*/
	
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private Date birth;
	private char gender;
	private String nick;
	private String email;
	private String tel;
	private String firstAddr;
	private String secondAddr;
	private String animal;
//	private char isExpert;
	private String isExpert;
	
	@Override
	public String toString() {
		return "UserTB [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", birth=" + birth + ", gender=" + gender + ", nick=" + nick + ", email=" + email + ", tel=" + tel
				+ ", firstAddr=" + firstAddr + ", secondAddr=" + secondAddr + ", animal=" + animal + ", isExpert="
				+ isExpert + "]";
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFirstAddr() {
		return firstAddr;
	}

	public void setFirstAddr(String firstAddr) {
		this.firstAddr = firstAddr;
	}

	public String getSecondAddr() {
		return secondAddr;
	}

	public void setSecondAddr(String secondAddr) {
		this.secondAddr = secondAddr;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public String getIsExpert() {
		return isExpert;
	}
	
	public void setIsExpert(String isExpert) {
		this.isExpert = isExpert;
	}
	
//	public char getIsExpert() {
//		return isExpert;
//	}
//
//	public void setIsExpert(char isExpert) {
//		this.isExpert = isExpert;
//	}
	
}
