package web.dao.face;

import java.util.List;

import web.dto.ExpertBoard;
import web.dto.FreeBoard;
import web.dto.SharePost;
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
	List<SharePost> selectShare(Paging paging);

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

}
