package web.dto;

public class Info {

	private int iNo;
	private String iName;
	private String iCity1;
	private String iCity2;
	private String address;
	private String iAddress;
	private String startTime;
	private String endTime;
	private String iTime;
	private String tel1;
	private String tel2;
	private String tel3;
	private String iTel;
	private String iDomain;
	private String iFilter;
	private String iContent;
	private int userNo;
	
	@Override
	public String toString() {
		return "Info [iNo=" + iNo + ", iName=" + iName + ", iCity1=" + iCity1 + ", iCity2=" + iCity2 + ", address="
				+ address + ", iAddress=" + iAddress + ", startTime=" + startTime + ", endTime=" + endTime + ", iTime="
				+ iTime + ", tel1=" + tel1 + ", tel2=" + tel2 + ", tel3=" + tel3 + ", iTel=" + iTel + ", iDomain="
				+ iDomain + ", iFilter=" + iFilter + ", iContent=" + iContent + ", userNo=" + userNo + "]";
	}

	public int getiNo() {
		return iNo;
	}

	public void setiNo(int iNo) {
		this.iNo = iNo;
	}

	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	public String getiCity1() {
		return iCity1;
	}

	public void setiCity1(String iCity1) {
		this.iCity1 = iCity1;
	}

	public String getiCity2() {
		return iCity2;
	}

	public void setiCity2(String iCity2) {
		this.iCity2 = iCity2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getiAddress() {
		return iAddress;
	}

	public void setiAddress(String iAddress) {
		this.iAddress = iAddress;
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

	public String getiTime() {
		return iTime;
	}

	public void setiTime(String iTime) {
		this.iTime = iTime;
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

	public String getiTel() {
		return iTel;
	}

	public void setiTel(String iTel) {
		this.iTel = iTel;
	}

	public String getiDomain() {
		return iDomain;
	}

	public void setiDomain(String iDomain) {
		this.iDomain = iDomain;
	}

	public String getiFilter() {
		return iFilter;
	}

	public void setiFilter(String iFilter) {
		this.iFilter = iFilter;
	}

	public String getiContent() {
		return iContent;
	}

	public void setiContent(String iContent) {
		this.iContent = iContent;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
}
