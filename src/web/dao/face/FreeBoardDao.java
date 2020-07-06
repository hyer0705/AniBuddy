package web.dao.face;

import java.util.List;

import web.dto.FreeBoard;
import web.dto.FreeBoardFile;
import web.dto.UserID;
import web.util.Paging;

public interface FreeBoardDao {

	/**
	 * 
	 * 
	 * @return
	 */
	int selectCntAll();

	/**
	 * 전체 게시글 조회
	 * 
	 * @param paging
	 * @return
	 */
	List<UserID> selectAll(Paging paging);

	/**
	 * 게시글 번호로 게시글 조회
	 * 
	 * @param postno
	 * @return
	 */
	FreeBoard selectBoardByBoardNo(FreeBoard postno);

	/**
	 * 조회수 올리기
	 * 
	 * @param board
	 */
	void updateHit(FreeBoard board);

	/**
	 * 게시글 첨부파일 조회
	 * 
	 * @param result
	 * @return
	 */
	FreeBoardFile selectFile(FreeBoard result);

	/**
	 * 게시글 번호 조회
	 * 
	 * @return
	 */
	int selectPostno();

	/**
	 * 글 삽입
	 * 
	 * @param board
	 */
	void insert(FreeBoard board);

	/**
	 * 게시글 파일 업로드
	 * 
	 * @param boardFile
	 */
	void insertFile(FreeBoardFile boardFile);

	/**
	 * 게시글 업데이트
	 * 
	 * @param board
	 */
	void update(FreeBoard board);

	/**
	 * 게시글 첨부파일 삭제
	 * 
	 * @param board
	 */
	void deleteFile(FreeBoard board);

	/**
	 * 게시글 삭제
	 * 
	 * @param board
	 */
	void delete(FreeBoard board);

	List<UserID> selectChart();

	int selectCntFreeSearch(String search);

}
