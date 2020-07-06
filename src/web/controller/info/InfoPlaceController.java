package web.controller.info;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.BookMarkInfo;
import web.dto.Info;
import web.service.face.InfoService;
import web.service.impl.InfoServiceImpl;

@WebServlet("/info/place")
public class InfoPlaceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InfoService infoService = new InfoServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("/anibuddy/info/place - [GET]");
		
		List<Info> list = infoService.list();
		List<BookMarkInfo> bmList = infoService.bmList();

		req.setAttribute("list", list);
		req.setAttribute("bmList", bmList);
		
		req.getRequestDispatcher("/WEB-INF/views/info/placeSearch.jsp").forward(req, resp);;
	}
	
}
