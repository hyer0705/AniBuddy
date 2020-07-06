package web.controller.mypage.activity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.BookMarkHangOut;
import web.service.face.HangOutService;
import web.service.impl.HangOutServiceImpl;

/**
 * Servlet implementation class MypageActivityBookMarkDeleteController
 */
@WebServlet("/activity/hbookmarkdelete")
public class MypageActivityHangOutBookMarkDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HangOutService hangoutService = new HangOutServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 안되어 있을 시 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 안되어 있을 시 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		String names = req.getParameter("names");
		if(!"".equals(names) && names != null) {
			hangoutService.delete(names);
			
		} else {
			// bm_no 정보 얻기
			BookMarkHangOut bm = hangoutService.getParamBmNo(req);
			
			// 삭제
			hangoutService.deleteBookMarkNo(bm);
		}
		
		// 쪽지리스트로 리다이렉트
		resp.sendRedirect("/anibuddy/activity/hbookmarklist");
		
	}
		
}
