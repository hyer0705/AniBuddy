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

@WebServlet("/info/view")
public class InfoViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InfoService infoService = new InfoServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("/anibuddy/info/view - [GET]");
		
		Info info = infoService.getiNo(req);
		info = infoService.view(info);
		
		req.setAttribute("info", info);
		
		req.getRequestDispatcher("/WEB-INF/views/info/placeView.jsp").forward(req, resp);;
		
	}
	
}
