package web.dto;

public class HangOut {

	private int hNo;
	private String hName;
	private String hCity1;
	private String hCity2;
	private String address;
	private String hAddress;
	private String startTime;
	private String endTime;
	private String hTime;
	private String tel1;
	private String tel2;
	private String tel3;
	private String hTel;
	private String hEmail;
	private String hDomain;
	private String hContent;
	private String hFilter;
	private int userNo;
	
	@Override
	public String toString() {
		return "HangOut [hNo=" + hNo + ", hName=" + hName + ", hCity1=" + hCity1 + ", hCity2=" + hCity2 + ", address="
				+ address + ", hAddress=" + hAddress + ", startTime=" + startTime + ", endTime=" + endTime + ", hTime="
				+ hTime + ", tel1=" + tel1 + ", tel2=" + tel2 + ", tel3=" + tel3 + ", hTel=" + hTel + ", hEmail="
				+ hEmail + ", hDomain=" + hDomain + ", hContent=" + hContent + ", hFilter=" + hFilter + ", userNo="
				+ userNo + "]";
	}

	public int gethNo() {
		return hNo;
	}

	public void sethNo(int hNo) {
		this.hNo = hNo;
	}

	public String gethName() {
		return hName;
	}

	public void sethName(String hName) {
		this.hName = hName;
	}

	public String gethCity1() {
		return hCity1;
	}

	public void sethCity1(String hCity1) {
		this.hCity1 = hCity1;
	}

	public String gethCity2() {
		return hCity2;
	}

	public void sethCity2(String hCity2) {
		this.hCity2 = hCity2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String gethAddress() {
		return hAddress;
	}

	public void sethAddress(String hAddress) {
		this.hAddress = hAddress;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String gethTime() {
		return hTime;
	}

	public void sethTime(String hTime) {
		this.hTime = hTime;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}

	public String gethTel() {
		return hTel;
	}

	public void sethTel(String hTel) {
		this.hTel = hTel;
	}

	public String gethEmail() {
		return hEmail;
	}

	public void sethEmail(String hEmail) {
		this.hEmail = hEmail;
	}

	public String gethDomain() {
		return hDomain;
	}

	public void sethDomain(String hDomain) {
		this.hDomain = hDomain;
	}

	public String gethContent() {
		return hContent;
	}

	public void sethContent(String hContent) {
		this.hContent = hContent;
	}

	public String gethFilter() {
		return hFilter;
	}

	public void sethFilter(String hFilter) {
		this.hFilter = hFilter;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	
}
