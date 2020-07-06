package web.controller.help;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Help_calls;
import web.service.face.HelpService;
import web.service.impl.HelpServiceImpl;

/**
 * Servlet implementation class HelpRecommendController
 */
@WebServlet("/help/recommend")
public class HelpRecommendController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HelpService helpService = new HelpServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//추천 정보 
		Help_calls calls = helpService.getRecommend(req);
		
		//추천 정보 토글
		boolean result = helpService.recommend(calls);
		
		
		//결과 JSON응답
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().println("{\"result\": "+result+"}");
		
	}
}
