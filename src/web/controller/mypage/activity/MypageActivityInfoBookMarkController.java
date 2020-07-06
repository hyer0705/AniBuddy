package web.controller.mypage.activity;

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
import web.util.Paging;

/**
 * Servlet implementation class MypageActivityBookMarkController
 */
@WebServlet("/activity/ibookmarklist")
public class MypageActivityInfoBookMarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InfoService infoService = new InfoServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 안됐을 때 메인 페이지로 리다이렉트
		if( req.getSession().getAttribute("login") == null ) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		Paging paging = infoService.getPaging(req);
		
		List<Info> list = infoService.list();
		List<BookMarkInfo> bmList = infoService.bmList();
		
		req.setAttribute("paging", paging);
		req.setAttribute("list", list);
		req.setAttribute("bmList", bmList);
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/activity_info_bm_list.jsp").forward(req, resp);
	}
	
}
