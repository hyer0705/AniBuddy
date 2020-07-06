package web.dao.face;

import java.util.List;

import web.dto.ShareComment;
import web.dto.SharePost;
import web.dto.UserID;

public interface ShareCommentDao {

	public List<ShareComment> selectComment(UserID board);

	public void insertComment(ShareComment comment);

	public void deleteComment(ShareComment comment);

	public int countComment(ShareComment comment);

	public List<ShareComment> selectAdminComment(UserID post);

}
