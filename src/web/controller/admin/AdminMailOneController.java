package web.controller.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
@WebServlet("/admin/onemail")
public class AdminMailOneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 전달파라미터 얻기 - 로그인 정보
		Admin admin = adminService.getLoginAdmin(req);
//		System.out.println("AdminLoginController admin: " + admin);
		
		String adminid = req.getParameter("adminid");
		
		
		req.getRequestDispatcher("/WEB-INF/views/admin/admin_mail_one.jsp").forward(req, resp);
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Email email = adminService.onemail(req);
		adminService.saveonemail(email); 
		resp.sendRedirect( req.getContextPath() + "/admin/mailcheck");
		
		
	}

}
