package web.controller.mypage.postmessage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.PostMessage;
import web.dto.UserTB;
import web.service.face.PostMessageService;
import web.service.impl.PostMessageServiceImpl;

/**
 * Servlet implementation class PostMessageDetailController
 */
@WebServlet("/mypage/pmdetail")
public class PostMessageDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PostMessageService pmService = new PostMessageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 안되어 있으면 메인 페이지로 리다이렉트
		if( req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
//		System.out.println("/anibuddy/mypage/pmdetail - [GET]");
		
		// pm_no 값 얻기
		PostMessage pm = pmService.getParamPmNo(req);
//		System.out.println("PMDetailController pm: " + pm);
		
		// pm_no 값으로 쪽지 조회
		pm = pmService.detail(pm);
//		System.out.println("PMDetailController pm: " + pm);
		
		// 조회된 쪽지로 보낸 사람 조회
		UserTB sender = pmService.getSender(pm);
//		System.out.println("PMDetailController sender: " + sender);
		
		// 조회된 쪽지 is_check update
		pmService.updateIsChk(pm);
		
		// 조회된 쪽지 req 값 설정
		req.setAttribute("pm", pm);
		req.setAttribute("sender", sender.getUserId());
		
		// VIEW 지정 - forward
		req.getRequestDispatcher("/WEB-INF/views/mypage/postmessage_detail.jsp")
			.forward(req, resp);
		
	}

}
