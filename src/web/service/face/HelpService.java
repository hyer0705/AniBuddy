package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.HelpComment;
import web.dto.HelpFile;
import web.dto.HelpPost;
import web.dto.Help_calls;
import web.dto.UserID;
import web.util.Paging;

public interface HelpService {

	public Paging getPaging(HttpServletRequest req);

	public List<UserID> list(Paging paging);

	public HelpPost getPostno(HttpServletRequest req);

	public HelpPost view(HelpPost board);

	public HelpFile viewFile(HelpPost result);

	public List<HelpComment> getCommentList(HelpPost board);

	public void write(HttpServletRequest req);

	public HelpComment getComment(HttpServletRequest req);

	public void insertComment(HelpComment comment);

	public boolean checkId(HttpServletRequest req);

	public void update(HttpServletRequest req);

	public void delete(HelpPost post);

	public boolean deleteComment(HelpComment comment);

	public boolean isRecommend(Help_calls calls);

	public Help_calls getRecommend(HttpServletRequest req);

	public boolean recommend(Help_calls calls);

	public List<UserID> list();

}
