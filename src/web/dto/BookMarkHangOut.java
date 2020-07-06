package web.dto;

public class BookMarkHangOut {

	private int bmNo;
	private int hNo;
	private int userNo;
	
	@Override
	public String toString() {
		return "BookMarkHangOut [bmNo=" + bmNo + ", hNo=" + hNo + ", userNo=" + userNo + "]";
	}

	public int getBmNo() {
		return bmNo;
	}

	public void setBmNo(int bmNo) {
		this.bmNo = bmNo;
	}

	public int gethNo() {
		return hNo;
	}

	public void sethNo(int hNo) {
		this.hNo = hNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
}
