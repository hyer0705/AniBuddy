package web.controller.mypage.oneonone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.OneOnOne;
import web.service.face.UserOneOnOneService;
import web.service.impl.UserOneOnOneServiceImpl;

/**
 * Servlet implementation class MypageOneOnOneDetailController
 */
@WebServlet("/mypage/oneononedetail")
public class MypageOneOnOneDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserOneOnOneService userO3Service = new UserOneOnOneServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인이 안되어 있으면 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		System.out.println("/anibuddy/mypage/oneononedetail - [GET]");
		
		// oneonone_no 값 얻기
		OneOnOne detailO3 = userO3Service.getParam(req);
//		System.out.println("MypageO3DetailController detailO3: " + detailO3);
		
		// oneonone_no 값으로 질문글 조회
		detailO3 = userO3Service.detail(detailO3);
//		System.out.println("MypageO3DetailController detailO3: " + detailO3);
		
		// oneonone_no 값으로 답변글 조회
		OneOnOne q = userO3Service.detailQ(detailO3);
//		System.out.println("MypageO3DetailController q: " + q);
		
		
		// 조회된 게시글 req에 값 설정
		req.setAttribute("detailO3", detailO3);
		
		// 조회된 답변글 req에 설정
		req.setAttribute("q", q);
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/oneonone_detail_new.jsp")
			.forward(req, resp);
		
	}

}
