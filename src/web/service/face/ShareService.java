package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.ShareComment;
import web.dto.ShareFile;
import web.dto.SharePost;
import web.dto.Share_calls;
import web.dto.UserID;
import web.util.Paging;

public interface ShareService {

	public Paging getPaging(HttpServletRequest req);

	public List<UserID> list(Paging paging);

	public UserID getPostno(HttpServletRequest req);

	public UserID view(UserID board);

	public ShareFile viewFile(UserID result);

	public List<ShareComment> getCommentList(UserID board);

	public void write(HttpServletRequest req);

	public ShareComment getComment(HttpServletRequest req);

	public void insertComment(ShareComment comment);

	public boolean checkId(HttpServletRequest req);

	public void update(HttpServletRequest req);

	public void delete(UserID post);

	public boolean deleteComment(ShareComment comment);

	public boolean isRecommend(Share_calls calls);

	public Share_calls getRecommend(HttpServletRequest req);

	public boolean recommend(Share_calls calls);

	public List<UserID> list();



}
