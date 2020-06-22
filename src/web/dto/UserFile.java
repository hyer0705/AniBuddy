package web.dto;

public class UserFile {
	
	private int fileno;
	private int userNo;
	private String originName;
	private String storeName;
	private int filesize;
	
	@Override
	public String toString() {
		return "UserFile [fileno=" + fileno + ", userNo=" + userNo + ", originName=" + originName + ", storeName="
				+ storeName + ", filesize=" + filesize + "]";
	}

	public int getFileno() {
		return fileno;
	}

	public void setFileno(int fileno) {
		this.fileno = fileno;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	
}
