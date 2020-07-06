package web.controller.hangout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.HangOut;
import web.dto.HangOutFile;
import web.service.face.HangOutService;
import web.service.impl.HangOutServiceImpl;

/**
 * Servlet implementation class HangOutViewController
 */
@WebServlet("/hangout/view")
public class HangOutViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HangOutService hangoutService = new HangOutServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("/anibuddy/hangout/view - [GET]");
		
		HangOut hangout = hangoutService.gethNo(req);
		hangout = hangoutService.view(hangout);
		
		HangOutFile hangoutFile = hangoutService.viewFile(hangout);
		
		req.setAttribute("hangoutFile", hangoutFile);
		req.setAttribute("hangout", hangout);
		
		req.getRequestDispatcher("/WEB-INF/views/hangout/placeView.jsp").forward(req, resp);
		
	}

}
