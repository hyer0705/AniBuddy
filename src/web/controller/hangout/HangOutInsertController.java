package web.controller.hangout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.service.face.HangOutService;
import web.service.impl.HangOutServiceImpl;

/**
 * Servlet implementation class HangOutInsertController
 */
@WebServlet("/hangout/insert")
public class HangOutInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HangOutService hangoutService = new HangOutServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if(req.getSession().getAttribute("login") == null) {
			
			resp.sendRedirect("/anibuddy/");
			
			return;
		}
		
		HttpSession session = req.getSession();

		session.getAttribute("login");
		session.getAttribute("loginid");

		req.getRequestDispatcher("/WEB-INF/views/hangout/addPlace.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if(req.getSession().getAttribute("login") == null) {

			resp.sendRedirect("/anibuddy/");

			return;
		}
		
		// 한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
		System.out.println("hangout/insert - [POST]");
		
		hangoutService.addPlace(req);
		
		resp.sendRedirect( req.getContextPath() + "/hangout/place");
		
	}
	
	
}
