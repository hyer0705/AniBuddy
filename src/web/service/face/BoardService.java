package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.Email;
import web.dto.ExpertBoard;
import web.dto.FreeBoard;
import web.dto.HelpPost;
import web.dto.SharePost;
import web.dto.UserID;
import web.util.Paging;

public interface BoardService {

	/**
	 * 페이징~
	 * 
	 * @param req
	 * @return
	 */
	Paging getPaging(HttpServletRequest req);
	/**
	 * 페이징~
	 * 
	 * @param req
	 * @return
	 */
	Paging getPagingExpert(HttpServletRequest req);
	/**
	 * 페이징~
	 * 
	 * @param req
	 * @return
	 */
	Paging getPagingFree(HttpServletRequest req);
	/**
	 * 페이징~
	 * 
	 * @param req
	 * @return
	 */
	Paging getPagingHelp(HttpServletRequest req);

	/**
	 * 나눔 게시판 글 조회
	 * 
	 * @param paging
	 * @return
	 */
	List<UserID> getShare(Paging paging);

	/**
	 * 전문가 게시판 글 조회
	 * 
	 * @param paging
	 * @return
	 */
	List<ExpertBoard> getExpert(Paging paging);

	/**
	 * 자유 게시판 글 조회
	 * 
	 * @param paging
	 * @return
	 */
	List<FreeBoard> getFree(Paging paging);

	/**
	 * 나눔 게시판 글 삭제
	 * 
	 * @param param
	 */
	void deleteShare(String param);

	/**
	 * 전문가 게시판 글 삭제
	 * 
	 * @param param
	 */
	void deleteExpert(String param);

	/**
	 * 자유 게시판 글 삭제
	 * 
	 * @param param
	 */
	void deleteFree(String param);

	/**
	 * 봉사 게시판 글 조회
	 * 
	 * @param paging
	 * @return
	 */
	public List<HelpPost> gethelp(Paging paging);

	/**
	 * 봉사 게시판 글 삭제
	 * 
	 * @param param
	 */
	public void deleteHelp(String param);
	
	  /**
	    * 메일 조회
	    * @param paging
	    * @return
	    */
	  public List<Email> getEmail(Paging paging);
	Paging getPagingMail(HttpServletRequest req);

}
