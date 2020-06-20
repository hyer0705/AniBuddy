package web.controller.hangout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.HangOut;
import web.service.face.HangOutService;
import web.service.impl.HangOutServiceImpl;

/**
 * Servlet implementation class HangOutDeleteController
 */
@WebServlet("/hangout/delete")
public class HangOutDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HangOutService hangoutService = new HangOutServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String hNo = req.getParameter("hNo");
		
		HangOut hangout = new HangOut();
		hangout.sethNo(Integer.parseInt(hNo));
		
		hangoutService.delete(hangout);
		
		resp.sendRedirect( req.getContextPath() + "/hangout/place");
	
	}
	
}
