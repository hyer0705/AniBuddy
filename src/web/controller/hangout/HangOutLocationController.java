package web.controller.hangout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HangOutLocationController
 */
@WebServlet("/hangout/location")
public class HangOutLocationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/anibuddy/hangout/location - [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/hangout/locationSearch.jsp")
			.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/anibuddy/hangout/location - [POST]");
		
	}

}
