package web.controller.mypage.postmessage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.PostMessage;
import web.dto.UserTB;
import web.service.face.PostMessageService;
import web.service.impl.PostMessageServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class PostMessageSendListController
 */
@WebServlet("/mypage/sendpmlist")
public class PostMessageSendListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PostMessageService pmService = new PostMessageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 안되어있을 시 메인페이지로 리다이렉션
		if(req.getSession().getAttribute("login") == null ) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
//		System.out.println("/anibuddy/mypage/sendpmlist - [GET]");
		
		// 페이징 정보 설정
		Paging paging = pmService.getSendPaging(req);
//		System.out.println("PMSendListController paging: " + paging);
		
		// 유저정보 얻기
		UserTB currUser = new UserTB();
		int userno = (int)req.getSession().getAttribute("userno");
		currUser.setUserNo(userno);
//		System.out.println("PMSendListController currUser: " + currUser );
		
		List<PostMessage> pmSendList = pmService.getPmSendList(paging, currUser);
//		for(PostMessage p : pmSendList) {
//			System.out.println(p);
//		}
		
		// 요청 페이지에 paging 정보와 pmList 정보 반환
		req.setAttribute("paging", paging);
		req.setAttribute("pmSendList", pmSendList);
		
		// VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/mypage/postmessage_send_list.jsp")
			.forward(req, resp);
	}

}
