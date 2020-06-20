package web.dao.face;

import web.dto.Admin;

public interface AdminDao {

	/**
	 * 입력된 관리자 아이디, 비밀번호로 admin 테이블 조회
	 * 
	 * @param admin - 아이디, 비밀번호 정보를 가지고 있는 Admin 객체
	 * @return int - 1(존재함), 0(존재하지 않음), -1(에러)
	 */
	int selectAdminByAdminidAdminpw(Admin admin);

	/**
	 * 관리자 아이디로 관리자 조회
	 * 
	 * @param admin - 관리자 아이디를 가진 Admin 객체
	 * @return Admin - 조회된 Admin 객체
	 */
	Admin selectAdminByAdminid(Admin admin);

}
