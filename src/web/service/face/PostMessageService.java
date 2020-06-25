package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.PostMessage;
import web.dto.UserTB;
import web.util.Paging;

public interface PostMessageService {

	/**
	 * 쪽지함 리스트 페이징
	 * 
	 * @param req - 요청 객체
	 * @return Paging - 페이징 정보를 갖고 있는 정보 객체
	 */
	Paging getPaging(HttpServletRequest req);

	/**
	 * 받은 쪽지 리스트 가져오기
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param currUser - 현재 사용자(수신자 역할) 정보를 가진 UserTB 객체
	 * @return List<PostMessage> 조회된 쪽지 리스트 반환
	 */
	List<PostMessage> getPmList(Paging paging, UserTB currUser);

	/**
	 * pm_no 얻기
	 * 
	 * @param req - 요청 객체
	 * @return pm_no 정보를 가진 PostMessage 객체
	 */
	PostMessage getParamPmNo(HttpServletRequest req);

	/**
	 * pm_no 으로 쪽지 조회
	 * 
	 * @param pm - pm_no을 가진 PostMessage 객체
	 * @return PostMessage - 조회된 쪽지 반환
	 */
	PostMessage detail(PostMessage pm);

	/**
	 * 보낸사람 정보 얻기(pm_sender_id로 조회)
	 * 
	 * @param pm (pm_sender_id 정보를 가진 객체)
	 * @return UserTB - 조회된 sender 반환
	 */
	UserTB getSender(PostMessage pm);
	
	/**
	 * 
	 * 
	 * @param req
	 * @return
	 */
	UserTB getUserByRecipientId(HttpServletRequest req);

	/**
	 * 읽은 상태로 update
	 * 
	 * @param pm - 쪽지 정보를 갖는 PostMessage
	 */
	void updateIsChk(PostMessage pm);

	/**
	 * 쪽지 작성
	 * 
	 * @param req
	 */
	void write(HttpServletRequest req);

	/**
	 * 쪽지 삭제
	 * 
	 * @param pm - pm_no 정보를 갖고 있는 PostMessage 객체
	 */
	void delete(PostMessage pm);

	/**
	 * 쪽지 여러개 지우기
	 * 
	 * @param names - 여러 개의 쪽지 pm_no 저장하고 있는 String 변수
	 */
	void delete(String names);

	/**
	 * 보낸쪽지함 페이징
	 * 
	 * @param req
	 * @return
	 */
	Paging getSendPaging(HttpServletRequest req);

	/**
	 * 보낸 쪽지함 전체 조회하기
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param currUser - sender 정보를 가진 객체
	 * @return
	 */
	List<PostMessage> getPmSendList(Paging paging, UserTB currUser);

	/**
	 * 쪽지 받은 사람 조회
	 * 
	 * @param pm
	 * @return
	 */
	UserTB getRecipient(PostMessage pm);

}
