package web.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MypageMainController
 */
@WebServlet("/mypage/main")
public class MypageMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		System.out.println("/anibuddy/mypage/main - [GET]");
		
		// 로그인 안되어 있으면 로그인 페이지로 이동
		if(req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/user/login");
			return;
		}
		
		// VIEW 지정 - forward
		req.getRequestDispatcher("/WEB-INF/views/mypage/mymain.jsp")
			.forward(req, resp);
		
	}
	
}
