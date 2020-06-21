package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.OneOnOne;
import web.util.Paging;

public interface OneOnOneService {


	/**
	 * 페이징
	 * 
	 * @param req
	 * @return
	 */
	Paging getPaging(HttpServletRequest req);

	/**
	 * 전체 내역 조회하기
	 * 
	 * @param paging
	 * @return
	 */
	List<OneOnOne> getOneOnOne(Paging paging);

	/**
	 * 선택한 문의내역 정보 얻어오기
	 * 
	 * @param req
	 * @return
	 */
	OneOnOne getparam(HttpServletRequest req);

	/**
	 * 게시글 상세보기
	 * 
	 * @param viewOneOnOne
	 * @return
	 */
	OneOnOne view(OneOnOne viewOneOnOne);

	/**
	 * 1:1 문의 글 삭제
	 * 
	 * @param names
	 */
	void oneOnOneDelete(String names);

}
