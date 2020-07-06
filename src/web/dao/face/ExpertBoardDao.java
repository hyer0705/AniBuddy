package web.dao.face;

import java.util.List;

import web.dto.ExpertBoard;
import web.dto.ExpertBoardFile;
import web.dto.UserID;
import web.util.Paging;

public interface ExpertBoardDao {

	/**
	 * ?
	 * 
	 * @return
	 */
	int selectCntAll();

	/**
	 * 게시글 전체 조회
	 * 
	 * @param paging
	 * @return
	 */
	List<UserID> selectAll(Paging paging);

	
	/**
	 * 
	 * 
	 * @param postno
	 * @return
	 */
	ExpertBoard selectBoardByBoardNo(ExpertBoard postno);

	/**
	 * 
	 * 
	 * @param board
	 */
	void updateHit(ExpertBoard board);

	/**
	 * 
	 * 
	 * @param result
	 * @return
	 */
	ExpertBoardFile selectFile(ExpertBoard result);

	/**
	 * 글번호 조회
	 * 
	 * @return
	 */
	int selectPostno();

	/**
	 * 글 삽입
	 * 
	 * @param board
	 */
	void insert(ExpertBoard board);

	/**
	 * 글 작성시 파일 업로드
	 * 
	 * @param boardFile
	 */
	void insertFile(ExpertBoardFile boardFile);

	/**
	 * 글 수정
	 * 
	 * @param board
	 */
	void update(ExpertBoard board);

	/**
	 * 글의 파일 삭제
	 * 
	 * @param board
	 */
	void deleteFile(ExpertBoard board);

	/**
	 * 게시글 삭제
	 * 
	 * @param board
	 */
	void delete(ExpertBoard board);

	/**
	 * 답글 삽입
	 * 
	 * @param board
	 */
	void replyinsert(ExpertBoard board);

	List<UserID> selectChart();

	int selectCntExpertSearch(String search);

}
