package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.FreeBoard;
import web.dto.FreeBoardFile;
import web.dto.FreeComment;
import web.dto.UserID;
import web.util.Paging;

public interface FreeBoardService {

	/**
	 * 
	 * 
	 * @param req
	 * @return
	 */
	Paging getPaging(HttpServletRequest req);

	/**
	 * 
	 * 
	 * @param paging
	 * @return
	 */
	List<UserID> list(Paging paging);

	/**
	 * 게시글 번호 얻기
	 * 
	 * @param req
	 * @return
	 */
	FreeBoard getPostno(HttpServletRequest req);

	/**
	 * 게시글 상세보기
	 * 
	 * @param postno
	 * @return
	 */
	FreeBoard view(FreeBoard postno);

	/**
	 * 게시글 첨부파일
	 * 
	 * @param result
	 * @return
	 */
	FreeBoardFile viewFile(FreeBoard result);

	/**
	 * 게시글 댓글 리스트
	 * 
	 * @param board
	 * @return
	 */
	List getCommentList(FreeBoard board);

	/**
	 * 글 작성
	 * 
	 * @param req
	 */
	void write(HttpServletRequest req);

	/** 
	 * 게시글 작성자인지 확인
	 * 
	 * @param req
	 * @return
	 */
	boolean checkId(HttpServletRequest req);

	/**
	 * 글 수정
	 * 
	 * @param req
	 */
	void update(HttpServletRequest req);

	/**
	 * 글 삭제
	 * 
	 * @param board
	 */
	void delete(FreeBoard board);

	/**
	 * 작성한 댓글 얻어오기
	 * 
	 * @param req
	 * @return
	 */
	FreeComment getComment(HttpServletRequest req);

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
	 * @return
	 */
	boolean deleteComment(FreeComment comment);

}
