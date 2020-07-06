package web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Email;
import web.service.face.AdminService;
import web.service.impl.AdminServiceImpl;

@WebServlet("/admin/mailview")
public class AdminMailViewController extends HttpServlet {
	private AdminService adminService = new AdminServiceImpl();
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Email email = adminService.getMailno(req);
		
		Email result = adminService.mailview(email);
		
		req.setAttribute("mail", result);
		
		email.setEmailno(Integer.parseInt(req.getParameter("emailno")));
		
		req.getRequestDispatcher("/WEB-INF/views/admin/admin_mail_view.jsp").forward(req, resp);
	}
}
