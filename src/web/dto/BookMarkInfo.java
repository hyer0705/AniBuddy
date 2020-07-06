package web.dto;

public class BookMarkInfo {
	
	private int bmNo;
	private int iNo;
	private int userNo;
	
	@Override
	public String toString() {
		return "BookMarkInfo [bmNo=" + bmNo + ", iNo=" + iNo + ", userNo=" + userNo + "]";
	}

	public int getBmNo() {
		return bmNo;
	}

	public void setBmNo(int bmNo) {
		this.bmNo = bmNo;
	}

	public int getiNo() {
		return iNo;
	}

	public void setiNo(int iNo) {
		this.iNo = iNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
}
