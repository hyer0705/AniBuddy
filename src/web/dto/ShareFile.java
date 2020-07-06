package web.dto;

import java.util.Date;

public class ShareFile {
	private int fileno;
	private int post_no;
	private String origin_name;
	private String stored_name;
	private int filesize;
	private Date write_date;
	@Override
	public String toString() {
		return "ShareFile [fileno=" + fileno + ", post_no=" + post_no + ", origin_name=" + origin_name
				+ ", stored_name=" + stored_name + ", filesize=" + filesize + ", write_date=" + write_date + "]";
	}
	public int getFileno() {
		return fileno;
	}
	public void setFileno(int fileno) {
		this.fileno = fileno;
	}
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public String getOrigin_name() {
		return origin_name;
	}
	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}
	public String getStored_name() {
		return stored_name;
	}
	public void setStored_name(String stored_name) {
		this.stored_name = stored_name;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	
	
	
	

}
