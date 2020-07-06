package web.dao.face;

import web.dto.Admin;
import web.dto.Email;

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

	/**
	 * 전체 메일 정보
	 * @param email
	 */
	public void mail(Email email);

	/**
	 * 메일 하나
	 * @param email
	 */
	public void onemail(Email email);

	/**
	 * 메일 정보 보기
	 * @param emailno
	 * @return
	 */
	public Email selectEmailByEmailno(Email emailno);

}
