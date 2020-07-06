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

@WebServlet("/hangout/deleteLocation")
public class HangOutDeleteLocationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HangOutService hangoutService = new HangOutServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("/anibuddy/hangout/deleteLocation - [GET]");
		
		HangOut hangout = hangoutService.gethNo(req);
		
		hangoutService.delete(hangout);
		
		resp.sendRedirect( req.getContextPath() + "/info/location");
	}
	
}
