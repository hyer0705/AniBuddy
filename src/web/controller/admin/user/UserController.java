package web.controller.admin.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.UserTB;
import web.service.face.UserManageService;
import web.service.impl.UserManageServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/manage/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserManageService userService = new UserManageServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Paging paging = userService.getPaging(req);

		List<UserTB> userinfo = userService.getUser(paging);		

		req.setAttribute("userinfo", userinfo);

		req.setAttribute("paging", paging);

		req.getRequestDispatcher("/WEB-INF/views/admin/user.jsp").forward(req, resp);


	}
}
