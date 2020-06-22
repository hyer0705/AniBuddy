package web.dao.face;

import web.dto.UserFile;
import web.dto.UserTB;

public interface UserDao {

	/**
	 * userid와 userpw가 일치하는 회원의 수를 조회
	 * 
	 * @param user - 조회할 회원의 정보
	 * @return int - 1(존재함), 0(존재하지 않음), -1(에러)
	 */
	int selectCntUserByUseridUserpw(UserTB user);

	/**
	 * userid를 이용해 회원정보 조회
	 * 
	 * @param user - 조회할 회원
	 * @return User - 조회된 결과 객체
	 */
	UserTB selectUserByUserid(UserTB user);

	/**
	 * 회원가입정보 삽입하기
	 * 
	 * @param user - 회원가입 정보 객체
	 */
	void insert(UserTB user);

	/**
	 * 닉네임을 이용해 회원정보 조회
	 * 
	 * @param user - 조회할 회원
	 * @return User - 조회된 결과 객체
	 */
	UserTB selectUserByUserNick(UserTB user);

	/**
	 * 이메일을 이용해 회원정보 조회 후 조회된 결과 반환
	 * 
	 * @param userEmail - 조회할 이메일
	 * @return 0 이상: 이미 존재하는 이메일, 0 : 존재하지 않는 이메일
	 */
	int selectUserByUserEmail(String userEmail);

	/**
	 * 이름, 이메일을 이용해 회원정보 조회
	 * 
	 * @param user - 이름, 이메일 정보를 가지고 있는 조회할 회원
	 * @return UserTB - 조회된 결과 객체
	 */
	UserTB selectUserByNameEmail(UserTB user);

	/**
	 * 이름, 이메일, 아이디를 이용해 회원정보 조회
	 * 
	 * @param user - 조회할 때 사용할 정보를 가진 회원 객체
	 * @return UserTB - 조회된 결과 객체
	 */
	UserTB selectUserByUserIdEmailName(UserTB user);

	/**
	 * update user_pw
	 * 
	 * @param user - 사용자 번호와 비밀번호를 갖고 있는 user 객체
	 * @return 0 이상 : 업데이트 성공, 0 업데이트 실패
	 */
	int updateUserPw(UserTB user);

	/**
	 * 사용자 번호로 사용자 조회하기
	 * 
	 * @param user - 사용자 번호를 갖고 있는 UserTB 객체
	 * @return UserTB - 조회된 사용자 객체 반환
	 */
	UserTB selectUserByUserno(UserTB user);

	/**
	 * 회원 정보 수정
	 * 
	 * @param user
	 */
	void update(UserTB user);

	/**
	 * 회원 이미지 파일 업로드
	 * 
	 * @param userFile
	 */
	void insertFile(UserFile userFile);

	/**
	 * 유저 프로필 파일 조회
	 * 
	 * @param user - 유저 정보를 갖고 있는 UserTB 객체
	 * @return UserFile - 조회된 파일 반환
	 */
	UserFile selectUserFile(UserTB user);

	/**
	 * 유저 핸드폰 번호 업데이트
	 * 
	 * @param user
	 */
	void updateUserTel(UserTB user);

	/**
	 * 반려동물 업데이트
	 * 
	 * @param user
	 */
	void updateAnimal(UserTB user);


}
