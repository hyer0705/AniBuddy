package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.UserTB;
import web.util.Paging;

public interface UserManageService {

	/**
	 * paging
	 * 
	 * @param req
	 * @return
	 */
	Paging getPaging(HttpServletRequest req);

	/**
	 * 전체 회원 조회
	 * 
	 * @param paging
	 * @return
	 */
	List<UserTB> getUser(Paging paging);

	/**
	 * 회원 강제 탈퇴
	 * 
	 * @param param
	 */
	void deleteUser(String param);

}
