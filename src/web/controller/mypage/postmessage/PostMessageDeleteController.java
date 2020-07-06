package web.controller.mypage.postmessage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.PostMessage;
import web.service.face.PostMessageService;
import web.service.impl.PostMessageServiceImpl;

/**
 * Servlet implementation class PostMessageDeleteController
 */
@WebServlet("/mypage/deletepm")
public class PostMessageDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PostMessageService pmService = new PostMessageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 안되어 있을 시 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
//		System.out.println("/anibuddy/mypage/deletepm - [GET]");
		
		doPost(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 안되어 있을 시 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
//		System.out.println("/anibuddy/mypage/deletepm - [POST]");
		
		String names = req.getParameter("names");
		if(!"".equals(names) && names != null) {
			pmService.delete(names);
			
		} else {
			// pm_no 정보 얻기
			PostMessage pm = pmService.getParamPmNo(req);
			
			// 삭제
			pmService.delete(pm);
		}
		
		// 쪽지리스트로 리다이렉트
		resp.sendRedirect("/anibuddy/mypage/pmlist");
		
	}

}
