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
 * Servlet implementation class HangOutPlaceSearchController
 */
@WebServlet("/hangout/placeSearch")
public class HangOutPlaceSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HangOutService hangoutService = new HangOutServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// doPost 호출
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/anibuddy/hangout/placeSearch - [POST] ");
		
		// 한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
		HangOut hangout = new HangOut();
		
		hangout.sethCity1(req.getParameter("city1"));
		hangout.sethCity2(req.getParameter("city2"));
		
		String filter = req.getParameter("h_filters");
		
		String[] filters = filter.split(" ");
		
		List<HangOut> placeList = hangoutService.viewPlaceList(hangout, filters);
		
		
		if(placeList==null || placeList.size()==0) {
			System.out.println("검색결과가 없음");
			
			String msg = "검색결과가 없습니다";
			
			List<HangOut> list = hangoutService.list();
			List<HangOutFile> fileList = hangoutService.fileList();

			req.setAttribute("list", list);
			req.setAttribute("fileList", fileList);
			req.setAttribute("msg", msg);
			
		} else {
			List<HangOutFile> fileList = hangoutService.fileList();
			
			req.setAttribute("placeList", placeList);
			req.setAttribute("fileList", fileList);
			
		}
		
		
		req.getRequestDispatcher("/WEB-INF/views/hangout/placeSearchResult.jsp")
			.forward(req, resp);
		
	}

}
