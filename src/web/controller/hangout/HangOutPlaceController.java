package web.controller.hangout;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class HangOutPlaceController
 */
@WebServlet("/hangout/place")
public class HangOutPlaceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HangOutService hangoutService = new HangOutServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/anibuddy/hangout/place - [GET]");
		
		List<HangOut> list = hangoutService.list();
//		System.out.println("HangOutPlaceController list: " + list);
		List<HangOutFile> fileList = hangoutService.fileList();
//		System.out.println("HangOutPlaceController fileList: " + fileList);
		
		req.setAttribute("list", list);
		req.setAttribute("fileList", fileList);
		
		req.getRequestDispatcher("/WEB-INF/views/hangout/placeSearch.jsp")
			.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/anibuddy/hangout/palce - [POST]");
		
	}

}
