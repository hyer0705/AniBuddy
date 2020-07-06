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

@WebServlet("/hangout/citySearch")
public class HangOutCitySearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HangOutService hangoutService = new HangOutServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("/anibuddy/hangout/citySearch - [POST]");
		
		req.setCharacterEncoding("utf-8");
		
		HangOut hangout = new HangOut();
		
		hangout.sethCity1(req.getParameter("city1"));
		
		List<HangOut> placeList = hangoutService.viewCity(hangout);
		List<BookMarkHangOut> bmList = hangoutService.bmList();

		
		if(placeList==null || placeList.size()==0) {
			System.out.println("검색 결과가 없음");
			
			String msg = "검색 결과가 없습니다";
			
			List<HangOut> list = hangoutService.list();
			
			req.setAttribute("list", list);
			req.setAttribute("bmList", bmList);
			req.setAttribute("msg", msg);
			
		} else {
			
			req.setAttribute("placeList", placeList);
			req.setAttribute("bmList", bmList);
		}
		
		req.getRequestDispatcher("/WEB-INF/views/hangout/locationSearchResult.jsp").forward(req, resp);
	
	}
	
}
