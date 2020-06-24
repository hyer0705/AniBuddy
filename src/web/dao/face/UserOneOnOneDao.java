package web.dao.face;

import java.util.List;

import web.dto.OneOnOne;
import web.dto.UserTB;
import web.util.Paging;

public interface UserOneOnOneDao {

	/**
	 * 검색어로 조회된 행의 갯수
	 * 
	 * @param search - 검색어
	 * @return int - 검색된 행의 갯수 반환
	 */
	int selectCntAll(String search);

	/**
	 * 페이징 처리된 1:1문의 내역 조회
	 * 
	 * @param paging - 페이징 정보를 가진 Paging 객체
	 * @param currUSer - 사용자 번호를 갖고있는 UserTB 객체
	 * @return List<OneOnOne> - 조회된 1:1문의 게시글 반환
	 */
	List<OneOnOne> selectOneOnOne(Paging paging, UserTB currUSer);

	/**
	 * 검색어로 조회된 행의 갯수(userno 으로 조회해야함)
	 * 
	 * @param search - 검색어
	 * @param currUser - 사용자 번호를 갖고 있는 UserTB 객체
	 * @return int - 사용자의 아이디와 검색어로 검색된 행의 갯수
	 */
	int selectCntByUserno(String search, UserTB currUser);

	/**
	 * 1:1문의 글 insert
	 * 
	 * @param o3 - 제목, 내용, 작성자 정보를 갖고 있는 OneOnOne 객체
	 */
	void insert(OneOnOne o3);

	/**
	 * oneonone_no 로 1:1문의 select
	 * 
	 * @param detailO3 - oneonone_no 정보를 가진 OneOnOne 객체
	 * @return OneOnOne - 조회된 1:1문의 게시글 객체 반환
	 */
	OneOnOne selectOneOnOneByNo(OneOnOne detailO3);

	/**
	 * 1:1 문의글 update
	 * 
	 * @param o3 - 문의글의 번호, 제목, 내용 정보를 갖는 OneOnOne 객체
	 */
	void update(OneOnOne o3);

	/**
	 * 1:1 문의글 delete
	 * 
	 * @param o3 - 문의글의 번호 정보를 갖는 OneOnOne 객체
	 */
	void delete(OneOnOne o3);

	/**
	 * 1:1 답변글 조회
	 * 
	 * @param detailO3 - 질문글 번호를 저장하고 있는 OneOnOne 객체
	 * @return OneOnOne - 질문글 번호로 조회된 답변글 
	 */
	OneOnOne selectReplyByNo(OneOnOne detailO3);


}
