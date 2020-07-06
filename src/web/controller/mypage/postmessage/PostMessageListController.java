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
 * Servlet implementation class PostMessageListController
 */
@WebServlet("/mypage/pmlist")
public class PostMessageListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PostMessageService pmService = new PostMessageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인하지 않으면 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
//		System.out.println("/anibuddy/mypage/pmlist - [GET]");
		
		// 페이징 정보 설정
		Paging paging = pmService.getPaging(req);
//		System.out.println("PostMessageListController paging: " + paging);
		
		// 유저정보 얻기
		UserTB currUser = new UserTB();
		int userno = (int)req.getSession().getAttribute("userno");
		currUser.setUserNo(userno);
		
		List<PostMessage> pmList = pmService.getPmList(paging, currUser);
		
		// 요청 페이지에 paging 정보와 pmList 정보 반환
		req.setAttribute("paging", paging);
		req.setAttribute("pmList", pmList);
		
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/postmessage_list.jsp")
			.forward(req, resp);
		
	}

}
