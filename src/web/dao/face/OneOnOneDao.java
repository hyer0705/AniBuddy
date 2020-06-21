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

}
