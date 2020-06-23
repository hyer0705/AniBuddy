package web.controller.admin.oneonone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.OneOnOneService;
import web.service.impl.OneOnOneServiceImpl;

/**
 * Servlet implementation class OneOnOneDelete
 */
@WebServlet("/oneonone/delete")
public class OneOnOneDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OneOnOneService oneOnOneService = new OneOnOneServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String names = req.getParameter("names");

		if( !"".equals(names) && names != null) {
			oneOnOneService.oneOnOneDelete(names);
		}
		
		// 일반 사용자 일 때
		if( req.getSession().getAttribute("login") != null) {
			resp.sendRedirect("/anibuddy/mypage/oneononelist");
			return;
		}

		resp.sendRedirect( req.getContextPath() + "/oneonone/reply");

	}
	
}
