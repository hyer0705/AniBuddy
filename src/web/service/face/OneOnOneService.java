package web.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dto.OneOnOne;
import web.dto.UserTB;
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

	/**
	 * 문의 번호로 해당 문의 글 조회하기
	 * 
	 * @param o3 - 문의 번호를 가진 OneOnOne 객체
	 * @return OneOnOne - 조회된 문의 글 객체 반환
	 */
	OneOnOne getOneOnOneByNo(OneOnOne o3);

	/**
	 * 관리자 1:1 글 작성
	 * 
	 * @param req - 요청 객체
	 */
	void write(HttpServletRequest req);

	/**
	 * reply_no 으로 질문글 조회하기
	 * 
	 * @param o3 - reply_no을 갖고 있는 OneOnOne 객체
	 * @return OneOnONe - 조회된 질문글 객체 반환
	 */
	OneOnOne getOneOnOneByReplyNo(OneOnOne o3);

	/**
	 * 답변 수정
	 * 
	 * @param req - 요청 객체
	 */
	void update(HttpServletRequest req);

	/**
	 * 답변 삭제
	 * 
	 * @param o3
	 */
	void delete(OneOnOne o3);

	/**
	 * 조인을 이용하여 사용자 아이디와 등록글 조회하기
	 * 
	 * @param paging
	 * @return
	 */
	List<Map<UserTB, OneOnOne>> getUserAndOneOnOne(Paging paging);

}
