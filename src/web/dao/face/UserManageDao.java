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

	
	
	List<UserTB> selectUser();

	
	
	int selectCntBySearch(String search);

}
