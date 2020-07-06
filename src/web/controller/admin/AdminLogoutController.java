package web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminLogoutController
 */
@WebServlet("/admin/logout")
public class AdminLogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("/adnibuddy/admin/logout - [GET]");
		
		// 세션 객체 얻기
		HttpSession session = req.getSession();
		
		// 세션 정보 삭제
		session.invalidate();
		
		// 로그아웃 후 메인페이지로 리다이렉트
		resp.sendRedirect( req.getContextPath() + "/");
		
	}
	
}
