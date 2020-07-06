package web.dto;

public class Help_calls {
	private int calldibs_no;
	private int user_id;
	private int post_no;
	@Override
	public String toString() {
		return "Help_calls [calldibs_no=" + calldibs_no + ", user_id=" + user_id + ", post_no=" + post_no + "]";
	}
	public int getCalldibs_no() {
		return calldibs_no;
	}
	public void setCalldibs_no(int calldibs_no) {
		this.calldibs_no = calldibs_no;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	
	
}
