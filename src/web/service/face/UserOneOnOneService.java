package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.OneOnOne;
import web.dto.UserTB;
import web.util.Paging;

public interface UserOneOnOneService {

	/**
	 * 1:1문의 내역 화면 페이징
	 * 
	 * @param req - 요청 객체
	 * @return Paging - 페이징 정보를 가진 객체 반환
	 */
	Paging getPaging(HttpServletRequest req);

	/**
	 * 1:1 문의 내역 조회
	 * 
	 * @param paging - Paging 객체
	 * @param currUser - 사용자 번호를 가진 UserTB 객체
	 * @return List<OneOnOne> - 조회된 결과 객체
	 */
	List<OneOnOne> getOneOnOneList(Paging paging, UserTB currUser);

	/**
	 * 1:1 문의 작성 정보로 1:1문의 글 작성하기
	 * 
	 * @param req - 요청 객체
	 */
	void write(HttpServletRequest req);

	/**
	 * 문의 글 상세보기를 위해 파라미터 값(oneonone_no 얻기)
	 * 
	 * @param req - 요청객체
	 * @return - oneonone_no 값을 저장하고 있는 OneOnOne 객체
	 */
	OneOnOne getParam(HttpServletRequest req);

	/**
	 * 1:1문의 게시글 번호로 게시글 조회하기
	 * 
	 * @param detailO3 - oneonone_no 정보를 가진 OneOnOne 객체
	 * @return OneOnOne - 조회된 1:1문의글 반환
	 */
	OneOnOne detail(OneOnOne detailO3);

	/**
	 * 1:1 문의 글 수정
	 * 
	 * @param req
	 */
	void update(HttpServletRequest req);

	/**
	 * 	1:1 문의 글 삭제
	 * 
	 * @param o3 - oneonone_no 정보를 가진 OneOnOne 객체
	 */
	void delete(OneOnOne o3);

	/**
	 * 1:1문의 게시글 번호로 답변글 조회하기
	 * 
	 * @param detailO3
	 * @return OneOnOne - 게시글 번호로 조회된 답변글 반환
	 */
	OneOnOne detailQ(OneOnOne detailO3);

}
