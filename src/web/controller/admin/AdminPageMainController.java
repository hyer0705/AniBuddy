package web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminPageMainController
 */
@WebServlet("/admin/main")
public class AdminPageMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/anibuddy/admin/main - [GET] ");
		
		req.getRequestDispatcher("/WEB-INF/views/admin/admin_main.jsp")
			.forward(req, resp);
	}
	
}
