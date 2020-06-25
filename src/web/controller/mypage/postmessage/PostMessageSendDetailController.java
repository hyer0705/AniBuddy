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
 * Servlet implementation class PostMessageSendDetailController
 */
@WebServlet("/mypage/sendpmdetail")
public class PostMessageSendDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PostMessageService pmService = new PostMessageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 안되어 있으면 메인페이지로 리다이렉트
		if( req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		System.out.println("/anibuddy/mypage/sendpmdetail - [GET]");
		
		// pm_no 값 얻기
		PostMessage pm = pmService.getParamPmNo(req);
		System.out.println("PMSendDetailController pm: " + pm);
		
		// pm_no 값으로 쪽지 조회
		pm = pmService.detail(pm);
		System.out.println("PMDetailController pm: " + pm);
		
		// 조회된 쪽지로 받은 사람 조회
		UserTB recipient = pmService.getRecipient(pm);
		System.out.println("PMDetailController recipient: " + recipient);

		// 조회된 쪽지 req 값 설정
		req.setAttribute("pm", pm);
		req.setAttribute("recipient", recipient.getUserId());
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/postmessage_send_detail.jsp")
			.forward(req, resp);
	}

}
