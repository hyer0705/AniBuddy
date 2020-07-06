package web.dao.face;

import java.util.List;

import web.dto.HelpComment;
import web.dto.HelpPost;

public interface HelpCommentDao {

	public List<HelpComment> selectComment(HelpPost board);

	public void insertComment(HelpComment comment);

	public void deleteComment(HelpComment comment);

	public int countComment(HelpComment comment);

}
