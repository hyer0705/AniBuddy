package web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Admin;
import web.service.face.AdminService;
import web.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class AdminLoginController
 */
@WebServlet("/admin/login")
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 되어 있으면 메인화면으로 리다이렉트
		if(req.getSession().getAttribute("login") != null ) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		System.out.println("/anibuddy/admin/login - [GET]");
		
		// VIEW 지정 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/admin_login.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/anibuddy/admin/login - [POST]");
		
		// 전달파라미터 얻기 - 로그인 정보
		Admin admin = adminService.getLoginAdmin(req);
//		System.out.println("AdminLoginController admin: " + admin);
		
		// 관리자 로그인 인증
		boolean adminLogin = adminService.login(admin);
//		System.out.println("AdminLoginController adminLogin: " + adminLogin);
		
		if(adminLogin) {
			
			// 로그인 관리자의 정보 얻어오기
			admin = adminService.info(admin);
//			System.out.println("AdminLoginController admin: " + admin);
			
			// 세션 정보 저장하기
			HttpSession session = req.getSession();
//			session.setAttribute("login", false);
			session.setAttribute("adminLogin", adminLogin);
			session.setAttribute("adminid", admin.getAdminId());
			session.setAttribute("adminno", admin.getAdminNo());
			
			session.setMaxInactiveInterval(21600);
			
			// 메인 화면으로
			resp.sendRedirect(req.getContextPath() + "/");
			return;
		}
		
		resp.sendRedirect( req.getContextPath() + "/admin/login");
		
	}
	
}
