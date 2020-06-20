package web.service.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.Admin;

public interface AdminService {

	/**
	 * 관리자 로그인 정보
	 * 
	 * @param req - 요청 객체
	 * @return Admin - 관리자 로그인 아이디, 패스워드 정보를 가진 Admin 객체
	 */
	Admin getLoginAdmin(HttpServletRequest req);

	/**
	 * 관리자 로그인
	 * 
	 * @param admin - 로그인 정보를 갖고있는 Admin 객체
	 * @return true: 로그인 성공, false: 로그인 실패
	 */
	boolean login(Admin admin);

	/**
	 * 관리자 정보 가져오기
	 * 
	 * @param admin - 관리자 아이디를 가진 객체
	 * @return Admin - 조회된 관리자 정보
	 */
	Admin info(Admin admin);
	
}
