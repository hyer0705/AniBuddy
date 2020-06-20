package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.ExpertBoard;
import web.dto.ExpertBoardFile;
import web.dto.ExpertComment;
import web.dto.UserID;
import web.util.Paging;

public interface ExpertBoardService {

	/**
	 * 게시글 목록 하단에 페이징 생성
	 * 
	 * @param req - 요청 객체
	 * @return
	 */
	Paging getPaging(HttpServletRequest req);

	/**
	 * 리스트 조회
	 * 
	 * @param paging
	 * @return
	 */
	List<UserID> list(Paging paging);

	/**
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보 객체
	 * @return ExpertBoard - 전달파라미터 postno를 포함한 객체
	 */
	ExpertBoard getPostno(HttpServletRequest req);

	/**
	 * 주어진 postno를 이용하여 게시글을 조회한다
	 * 조회된 게시글의 조회수를 1 증가시킨다
	 * 
	 * @param board - postno를 가지고 있는 객체
	 * @return Board - 조회된 게시글
	 */
	public ExpertBoard view(ExpertBoard postno);

	/**
	 * 첨부파일의 정보 얻기
	 * 
	 * @param result - 첨부파일 포함된 게시글 번호
	 * @return BoardFile - 첨부파일 정보 객체
	 */
	ExpertBoardFile viewFile(ExpertBoard result);

	/**
	 * 댓글 리스트
	 * 
	 * @param board - 댓글이 조회될 게시글
	 * @return List - 댓글 리스트
	 */
	List getCommentList(ExpertBoard board);

	/**
	 * 게시글 작성
	 * 
	 * 입력한 게시글 내용을 DB에 저장
	 * 
	 * @param req - 요청정보 객체(게시글내용 + 첨부파일)
	 */
	void write(HttpServletRequest req);

	/**
	 * 글쓴이 아이디 체크
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
	void delete(ExpertBoard board);

	/**
	 * 답변 글 작성
	 * 
	 * @param req
	 */
	void replywrite(HttpServletRequest req);

	/**
	 * 댓글 가져오기
	 * 
	 * @param req
	 * @return
	 */
	ExpertComment getComment(HttpServletRequest req);

	/**
	 * 댓글 작성
	 * 
	 * @param comment
	 */
	void insertComment(ExpertComment comment);

	/**
	 * 댓글삭제
	 * 
	 * @param comment
	 * @return
	 */
	boolean deleteComment(ExpertComment comment);

}
