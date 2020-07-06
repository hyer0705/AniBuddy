package web.controller.mypage.activity;

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
import web.util.Paging;

/**
 * Servlet implementation class MypageActivityBookMarkController
 */
@WebServlet("/activity/hbookmarklist")
public class MypageActivityHangOutBookMarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HangOutService hangoutService = new HangOutServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 안됐을 때 메인 페이지로 리다이렉트
		if( req.getSession().getAttribute("login") == null ) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		// 페이징 정보 설정
		Paging paging = hangoutService.getPaging(req);
		
		List<HangOut> list = hangoutService.list();
		List<BookMarkHangOut> bmList = hangoutService.bmList();
		
		req.setAttribute("paging", paging);
		req.setAttribute("list", list);
		req.setAttribute("bmList", bmList);
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/activity_hangout_bm_list.jsp").forward(req, resp);
	}
	
}
