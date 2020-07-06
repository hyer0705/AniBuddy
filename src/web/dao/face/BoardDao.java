package web.dao.face;

import java.util.List;

import web.dto.Email;
import web.dto.ExpertBoard;
import web.dto.FreeBoard;
import web.dto.HelpPost;
import web.dto.SharePost;
import web.dto.UserID;
import web.util.Paging;

public interface BoardDao {

	/**
	 * 
	 * 
	 * @return
	 */
	int selectCntAll();

	/**
	 * 나눔 게시판 글 조회
	 * 
	 * @param paging
	 * @return
	 */
	List<UserID> selectShare(Paging paging);

	/**
	 * 전문가 게시판 글 조회
	 * 
	 * @param paging
	 * @return
	 */
	List<ExpertBoard> selectExpert(Paging paging);

	/**
	 * 자유 게시판 글 조회
	 * 
	 * @param paging
	 * @return
	 */
	List<FreeBoard> selectFree(Paging paging);

	/**
	 * delete share 게시판 글
	 * 
	 * @param param
	 */
	void deleteShare(String param);

	/**
	 * delete expert 게시판 글 삭제
	 * 
	 * @param param
	 */
	void deleteExpert(String param);

	/**
	 * delete free 게시판 글 삭제
	 * 
	 * @param param
	 */
	void deleteFree(String param);

	/**
	 * 검색어로 조회된 행의 갯수 반환
	 * 
	 * @param search - 검색어
	 * @return int - 조회된 행의 갯수 반환
	 */
	int selectCntBySearch(String search);

	/**
	 * 전문가 게시판 전체 조회 행 갯수
	 * 
	 * @return
	 */
	int selectCntExpert();

	/**
	 * 전문가 게시판 검색어에 의한 조회 행 갯수
	 * 
	 * @param search
	 * @return
	 */
	int selectCntExpert(String search);

	/**
	 * 자유 게시판 글 전체 조회 행 갯수
	 * 
	 * @return
	 */
	int selectCntFree();

	/**
	 * 자유 게시판 글 검색어에 의한 조회 행 갯수
	 * 
	 * @param search
	 * @return
	 */
	int selectCntFree(String search);

	/**
	 * 봉사 게시판 글 검색어에 의한 조회 행 갯수
	 * 
	 * @param search
	 * @return
	 */
	int selectCntHelp(String search);

	/**
	 * 봉사 게시판 글 전체 조회 행 갯수
	 * 
	 * @return
	 */
	int selectCntHelp();

	/**
	 * 봉사 게시판 게시글 조회
	 * 
	 * @param paging
	 * @return
	 */
	List<HelpPost> selectHelp(Paging paging);

	/**
	 * 
	 * 
	 * @param param
	 */
	void deleteHelp(String param);
	  /**
	    * 보낸 메일 조회
	    * @param paging
	    * @return
	    */
	List<Email> selectMail(Paging paging);

	int selectCntMailSearch(String search);

	int selectCntMail();

}
