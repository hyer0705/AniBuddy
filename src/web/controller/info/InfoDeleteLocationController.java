package web.controller.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Info;
import web.service.face.InfoService;
import web.service.impl.InfoServiceImpl;

@WebServlet("/info/deleteLocation")
public class InfoDeleteLocationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InfoService infoService = new InfoServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("/anibuddy/info/deleteLocation - [GET]");
		
		Info info = infoService.getiNo(req);
		
		infoService.delete(info);
		
		resp.sendRedirect( req.getContextPath() + "/info/location");
	}
	
}
