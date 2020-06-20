package web.dao.face;

import java.util.List;

import web.dto.ExpertBoard;
import web.dto.ExpertComment;

public interface ExpertCommentDao {

	/**
	 *  댓글 조회
	 * 
	 * @param board
	 * @return
	 */
	List selectComment(ExpertBoard board);

	/**
	 * 댓글 삽입
	 * 
	 * @param comment
	 */
	void insertComment(ExpertComment comment);

	/**
	 * 댓글 삭제
	 * 
	 * @param comment
	 */
	void deleteComment(ExpertComment comment);

	/**
	 * 댓글 카운트
	 * 
	 * @param comment
	 * @return
	 */
	int countComment(ExpertComment comment);

}
