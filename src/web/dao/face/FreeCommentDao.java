package web.dao.face;

import java.util.List;

import web.dto.FreeBoard;
import web.dto.FreeComment;

public interface FreeCommentDao {

	/**
	 * 게시글에 해당되는 전체 댓글 조회
	 * 
	 * @param board
	 * @return
	 */
	List selectComment(FreeBoard board);

	/**
	 * 댓글 삽입
	 * 
	 * @param comment
	 */
	void insertComment(FreeComment comment);

	/**
	 * 댓글 삭제
	 * 
	 * @param comment
	 */
	void deleteComment(FreeComment comment);

	/**
	 * 조회된 댓글 갯수
	 * 
	 * @param comment
	 * @return
	 */
	int countComment(FreeComment comment);

}
