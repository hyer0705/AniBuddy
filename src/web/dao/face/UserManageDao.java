package web.dao.face;

import java.util.List;

import web.dto.UserTB;
import web.util.Paging;

public interface UserManageDao {

	/**
	 * 
	 * 
	 * @return
	 */
	int selectCntAll();

	/**
	 * 모든 회원 조회
	 * 
	 * @param paging
	 * @return
	 */
	List<UserTB> selectUser(Paging paging);

	/**
	 * delete 회원
	 * 
	 * @param param
	 */
	void deleteUser(String param);

	/**
	 * 검색어를 입력했을 때 페이징
	 * 
	 * @param search - 사용자가 입력한 검색어
	 * @return int - 검색어에 의해 조회된 행의 갯수 반환
	 */
	int selectCntBySearch(String search);

}
