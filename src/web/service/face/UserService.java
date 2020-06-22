package web.service.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.UserTB;

public interface UserService {

	/**
	 * 로그인 유저 정보 얻어오기
	 * 
	 * @param req - 요청 객체
	 * @return UserTB - 로그인 정보를 저장하고 있는 객체 반환
	 */
	UserTB getLoginUser(HttpServletRequest req);

	/**
	 * 로그인 처리
	 * 
	 * @param user - 로그인 정보
	 * @return boolean - true: 로그인 됨, false: 로그인 인증 안됨
	 */
	boolean login(UserTB user);

	/**
	 * 유저정보 가져오기
	 * 
	 * @param user - 회원 아이디를 가진 객체
	 * @return UserTB - 조회된 회원 정보
	 */
	UserTB info(UserTB user);

	/**
	 * 회원가입 정보 추출하기
	 * 
	 * @param req - 요청정보 객체
	 * @return UserTB - 추출한 회원가입 정보
	 */
	UserTB getJoinUser(HttpServletRequest req);

	/**
	 * 회원가입 처리
	 * 
	 * @param user - 회원가입 정보 객체
	 */
	void join(UserTB user);

	/**
	 * 아이디 중복 확인
	 * 
	 * @param user - 아이디 정보를 갖고있는 User_TB 객체
	 * @return true: 아이디 존재, false: 아이디 없음
	 */
	boolean chkId(UserTB user);

	/**
	 * 닉네임 중복 확인
	 * 
	 * @param user - 닉네임 정보를 갖고있는 User_TB 개겣
	 * @return true: 닉네임 존재, false: 닉네임 없음
	 */
	boolean chkNick(UserTB user);

	/**
	 * 이메일 중복 확인
	 * 
	 * @param userEmail - 사용자 이메일 값
	 * @return true: 이메일 존재, false: 이메일 없음
	 */
	boolean chkEmail(String userEmail);

	/**
	 * 아이디 찾기에서 입력한 정보를 가진 유저 얻기
	 * 
	 * @param req - 요청 객체
	 * @return UserTB - 아이디 찾기에서 입력한 정보를 갖고 있는 객체
	 */
	UserTB getFindUser(HttpServletRequest req);

	/**
	 * 아이디 찾기
	 * 
	 * @param user - 아이디를 찾을 정보를 가진 UserTB 객체
	 * @return UserTB - 조회된 회원
	 */
	UserTB findId(UserTB user);

	/**
	 * 비밀번호 찾기
	 * 
	 * @param user - 비밀번호를 찾을 정보를 가진 UserTB 객체 
	 * @return UserTB - 조회된 회원
	 */
	UserTB findPw(UserTB user);

	/**
	 * 사용자 번호로 사용자 얻어오기
	 * 
	 * @param req - 요청객체
	 * @return UserTB - 비밀번호, 비밀번호 확인, userno 정보를 갖고 있는 사용자 객체 반환
	 */
	UserTB getFindUserByUserno(HttpServletRequest req);
	
	/**
	 * 사용자 번호로 사용자 조회하기
	 * 
	 * @param user - 사용자 번호를 갖고 있는 사용자 객체
	 * @return UserTB - 사용자 번호로 조회된 사용자 객체
	 */
	UserTB getFindUserByUserno(UserTB user);

	/**
	 * 비밀번호 재설정
	 * 
	 * @param user - 사용자 번호, 재설정할 비밀번호 정보를 갖고 있는 사용자 객체
	 * @return true: 업데이트 완료, false: 업데이트 오류
	 */
	boolean updatePw(UserTB user);

	/**
	 * session.getAttribute("userno") 사용하여 사용자 번호 얻기
	 *  
	 * @param req - 요청 객체
	 * @return UserTB - 사용자 번호 정보를 갖고 있는 UserTB 객체
	 */
	UserTB getLoginUserByUserno(HttpServletRequest req);




}
