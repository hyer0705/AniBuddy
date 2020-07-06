package web.controller.mypage.activity;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MypageActivityPostListController
 */
@WebServlet("/activity/postlist")
public class MypageActivityPostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 안됐을 때 메인 페이지로 리다이렉트
		if( req.getSession().getAttribute("login") == null ) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		System.out.println("/anibuddy/activity/postlist");
		
		// VIEW 지정 - forward
		req.getRequestDispatcher("/WEB-INF/views/mypage/activity_post_list.jsp")
			.forward(req, resp);
		
	}
	
}
