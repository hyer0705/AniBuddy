package web.dao.face;

import java.util.List;

import web.dto.OneOnOne;
import web.util.Paging;

public interface OneOnOneDao {

	/**
	 * 
	 * 
	 * @param search
	 * @return
	 */
	int selectCntAll(String search);

	/**
	 * 문의내역 전체 조회
	 * 
	 * @param paging
	 * @return
	 */
	List<OneOnOne> selectOneOnOne(Paging paging);

	/**
	 * 문의내역 번호로 조회
	 * 
	 * @param viewOneOnOne
	 * @return
	 */
	OneOnOne selectOneOnOneByno(OneOnOne viewOneOnOne);

	/**
	 * 
	 * 
	 * @param viewOneOnOne
	 * @return
	 */
	OneOnOne insertCondition(OneOnOne viewOneOnOne);

	/**
	 * 문의글 하나 삭제
	 * 
	 * @param names
	 */
	void deleteOne(String names);

	/**
	 * 답변글 삽입
	 * 
	 * @param o3
	 */
	void insert(OneOnOne o3);

	/**
	 * 질문글에 대한 답변이 달리고 질문글의 condition, reply_progress 수정
	 * 
	 * @param q - 질문글 번호를 저장하고 있는 OneOnOne 객체
	 */
	void updateQuestion(OneOnOne q);

	/**
	 * reply_no 으로 질문글 조회하기
	 * 
	 * @param o3
	 * @return OneOnOne - 조회된 질문글 반환
	 */
	OneOnOne selectOneOnOneByReplyno(OneOnOne o3);

	/**
	 * 답변 수정하기
	 * 
	 * @param q
	 */
	void update(OneOnOne q);

}
