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

@WebServlet("/info/placeSearch")
public class InfoPlaceSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InfoService infoService = new InfoServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// doPost 호출
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("/anibuddy/info/placeSearch - [POST] ");
		
		// 한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
		Info info = new Info();
		
		info.setiCity1(req.getParameter("city1"));
		info.setiCity2(req.getParameter("city2"));
		
		String filter = req.getParameter("i_filters");
		
		String[] filters = filter.split(" ");
		
		List<Info> placeList = infoService.viewPlaceList(info, filters);
		List<BookMarkInfo> bmList = infoService.bmList();
		
		
		if(placeList==null || placeList.size()==0) {
//			System.out.println("검색결과가 없음");
			
			String msg = "검색 결과가 없습니다";
			
			List<Info> list = infoService.list();

			req.setAttribute("list", list);
			req.setAttribute("bmList", bmList);
			req.setAttribute("msg", msg);
			
		} else {
			
			req.setAttribute("placeList", placeList);
			req.setAttribute("bmList", bmList);
			
		}
		
		
		req.getRequestDispatcher("/WEB-INF/views/info/placeSearchResult.jsp")
			.forward(req, resp);
		
	}
	
}
