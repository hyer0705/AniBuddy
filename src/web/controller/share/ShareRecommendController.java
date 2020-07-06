package web.controller.share;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Share_calls;
import web.service.face.ShareService;
import web.service.impl.ShareServiceImpl;

/**
 * Servlet implementation class ShareRecommendController
 */
@WebServlet("/share/recommend")
public class ShareRecommendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ShareService shareService = new ShareServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//추천 정보 
		Share_calls calls = shareService.getRecommend(req);
		
		//추천 정보 토글
		boolean result = shareService.recommend(calls);
		
		
		//결과 JSON응답
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().println("{\"result\": "+result+"}");
		
	}
	
}
