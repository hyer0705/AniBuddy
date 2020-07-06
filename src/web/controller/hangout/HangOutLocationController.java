package web.controller.hangout;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.BookMarkHangOut;
import web.dto.HangOut;
import web.service.face.HangOutService;
import web.service.impl.HangOutServiceImpl;

/**
 * Servlet implementation class HangOutLocationController
 */
@WebServlet("/hangout/location")
public class HangOutLocationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HangOutService hangoutService = new HangOutServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("/anibuddy/hangout/location - [GET]");
		
		List<HangOut> list = hangoutService.list();
		List<BookMarkHangOut> bmList = hangoutService.bmList();
		
		req.setAttribute("list", list);
		req.setAttribute("bmList", bmList);
		
		req.getRequestDispatcher("/WEB-INF/views/hangout/locationSearch.jsp")
			.forward(req, resp);
	}


}
