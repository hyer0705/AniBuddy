package web.service.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.Admin;
import web.dto.Email;

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

	/**
	 * 전체메일
	 * @param req
	 * @return 
	 */
	public Email mail(HttpServletRequest req);

	/**
	 * 메일 저장
	 * @param email
	 */
	public void savemail(Email email);

	/**
	 * 메일 한개
	 * @param req
	 * @return
	 */
	public Email onemail(HttpServletRequest req);

	/**
	 * 한명 메일 저장
	 * @param email
	 */
	public void saveonemail(Email email);

	/**
	 * 메일번호 가져오기
	 * @param req
	 * @return
	 */
	public Email getMailno(HttpServletRequest req);

	/**
	 * 보낸 메일 보기
	 * @param email
	 * @return
	 */
	public Email mailview(Email email);
	

}
