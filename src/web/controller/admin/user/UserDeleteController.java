package web.controller.admin.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.UserManageService;
import web.service.impl.UserManageServiceImpl;

/**
 * Servlet implementation class UserDeleteController
 */
@WebServlet("/user/delete")
public class UserDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserManageService user = new UserManageServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String param = req.getParameter("names");

		if(param != null && !"".equals(param)) {

			user.deleteUser(param);

		}

		resp.sendRedirect( req.getContextPath() + "/manage/user");

	}

}
