package web.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.PostMessageDao;
import web.dao.face.UserDao;
import web.dao.impl.PostMessageDaoImpl;
import web.dao.impl.UserDaoImpl;
import web.dto.PostMessage;
import web.dto.UserTB;
import web.service.face.PostMessageService;
import web.util.Paging;

public class PostMessageServiceImpl implements PostMessageService{

	private PostMessageDao pmDao = new PostMessageDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		// 한글 인코딩
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 현재 페이지
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param != null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		
		// 현재 사용자의 번호로 자신이 받은 쪽지함 조회
		int userno = (int)req.getSession().getAttribute("userno");
		UserTB currUser = new UserTB();
		currUser.setUserNo(userno);
		// POST_MESSAGE 테이블의 총 게시글 수를 조회한다
		int totalCnt = pmDao.selectCntAllByUserno(currUser);
		
		// Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수 (totalCnt) 활용
		Paging paging = new Paging(totalCnt, curPage);
		
		return paging;
	}

	@Override
	public List<PostMessage> getPmList(Paging paging, UserTB currUser) {
		return pmDao.selectPmByRecipientNo(paging, currUser);
	}
	
	@Override
	public PostMessage getParamPmNo(HttpServletRequest req) {
		
		// 한글 인코딩
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 결과 객체
		PostMessage pm = new PostMessage();
		
		String param = req.getParameter("pm_no");
		if(param != null && !"".equals(param)) {
			pm.setPmNo(Integer.parseInt(param));
		}
		
		return pm;
	}
	
	@Override
	public PostMessage detail(PostMessage pm) {
		return pmDao.selectPMByNo(pm);
	}
	
	@Override
	public UserTB getSender(PostMessage pm) {
		return pmDao.selectPmSender(pm);
	}
	
	@Override
	public void updateIsChk(PostMessage pm) {
		pmDao.updateIsChk(pm);
		
	}

	@Override
	public UserTB getUserByRecipientId(HttpServletRequest req) {
		
		// 한글 인코딩
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 결과 반환 객체
		UserTB recipient = null;
		String param = req.getParameter("pm_recipient_id");
		if(param != null && !"".equals(param)) {
			recipient = new UserTB();
			recipient.setUserNo(Integer.parseInt(param));
		}
		
		return recipient;
	}
	
	@Override
	public void write(HttpServletRequest req) {

		// 한글 인코딩
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 사용자 아이디를 입력했을 경우
		UserTB recipient = null;
		
		// 쪽지 정보를 저장할 객체
		PostMessage pm = new PostMessage();
		pm.setTitle(req.getParameter("title"));
		pm.setContent(req.getParameter("content"));
		pm.setPmSenderId((int)req.getSession().getAttribute("userno"));
		
		String recipientNo = req.getParameter("recipient_no");
		System.out.println("recipientNo: " + recipientNo);
		if( recipientNo != null && !"".equals(recipientNo)) {
			pm.setPmRecipientId(Integer.parseInt(recipientNo));
		} 
		
		if ("".equals(recipientNo) && !"".equals(req.getParameter("recipient_id"))) {
			// recipientId 존재 한다면
			// recipientId 로 조회
			recipient = new UserTB();
			recipient.setUserId(req.getParameter("recipient_id"));
			recipient =  userDao.selectUserByUserid(recipient);
			pm.setPmRecipientId(recipient.getUserNo());
		}
		
		System.out.println("PostMessageServiceImpl pm: " + pm);	
		
		// insert 쪽지
		pmDao.insert(pm);
		
	}
	
	@Override
	public void delete(PostMessage pm) {
		pmDao.delete(pm);
	}
	
	@Override
	public void delete(String names) {
		pmDao.delete(names);
		
	}
	
}
