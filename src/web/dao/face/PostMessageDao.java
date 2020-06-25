package web.dao.face;

import java.util.List;

import web.dto.PostMessage;
import web.dto.UserTB;
import web.util.Paging;

public interface PostMessageDao {

	/**
	 * 현재 로그인한 사용자 번호로 받은 쪽지 갯수 조회
	 * 
	 * @param currUser - 현재 로그인한 사용자의 번호를 저장하고 있는 UserTB 객체
	 * @return int - 조회된 행의 갯수 반환
	 */
	int selectCntAllByUserno(UserTB currUser);

	/**
	 * 수신자 번호로 받은 쪽지 조회
	 * 
	 * @param paging - 페이징 객체
	 * @param currUser - 수신자 번호(현재 사용자) 정보를 가진 UserTB 객체
	 * @return List<PostMessage> - 조회된 쪽지 리스트 반환
	 */
	List<PostMessage> selectPmByRecipientNo(Paging paging, UserTB currUser);

	/**
	 * select 쪽지 by pm_no
	 * 
	 * @param pm - pm_no 정보를 가진 PostMessage 객체
	 * @return PostMessage - 조회된 쪽지 결과 반환
	 */
	PostMessage selectPMByNo(PostMessage pm);

	/**
	 * pm_sender_id 로 사용자 조회
	 * 
	 * @param pm - pm_sender_id 를 가진 PostMessage 객체
	 * @return UserTB - 조회된 사용자
	 */
	UserTB selectPmSender(PostMessage pm);

	/**
	 * 조회된 쪽지 읽은 상태(is_check) 수정하기
	 * 
	 * @param pm - 조회된 쪽지 정보를 갖는 PostMessage 객체
	 */
	void updateIsChk(PostMessage pm);

	/**
	 * 작성한 쪽지 insert
	 * 
	 * @param pm - 작성한 쪽지 정보를 가진 PostMessage 객체
	 */
	void insert(PostMessage pm);

	/**
	 * 상세보기로 보고 있는 쪽지 삭제
	 * 
	 * @param pm
	 */
	void delete(PostMessage pm);

	/**
	 * 여러 쪽지 삭제
	 * 
	 * @param names
	 */
	void delete(String names);

	/**
	 * 쪽지 발신자 정보로 조회된 쪽지 행의 갯수
	 * 
	 * @param currUser - pm_sender_id(=user_no) 정보를 갖는 UserTB 객체
	 * @return int - 조회된 쪽지 행의 갯수 반환
	 */
	int selectCntAllByPMSenderId(UserTB currUser);

	/**
	 * 쪽지 발신자 번호로 쪽지 조회하기
	 * 
	 * @param paging - 페이징 객체
	 * @param currUser - 현재 로그인한 유저 정보를 가진 객체
	 * @return List<PostMessage> - 조회된 쪽지 리스트 반환
	 */
	List<PostMessage> selectPmBySenderNo(Paging paging, UserTB currUser);

	/**
	 * 쪽지 받은 사람 조회
	 * 
	 * @param pm
	 * @return
	 */
	UserTB selectPmRecipient(PostMessage pm);

}
