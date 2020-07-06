package web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Admin;
import web.dto.Email;
import web.service.face.AdminService;
import web.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class AdminMailController
 */
@WebServlet("/admin/mail")
public class AdminMailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 전달파라미터 얻기 - 로그인 정보
		Admin admin = adminService.getLoginAdmin(req);
//		System.out.println("AdminLoginController admin: " + admin);
		
		String adminid = req.getParameter("adminid");
		
		
		req.getRequestDispatcher("/WEB-INF/views/admin/admin_mail.jsp").forward(req, resp);
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Email email = adminService.mail(req);
		
		adminService.savemail(email);
		
		resp.sendRedirect( req.getContextPath() + "/admin/mailcheck");		
		
		
		
	}

}
