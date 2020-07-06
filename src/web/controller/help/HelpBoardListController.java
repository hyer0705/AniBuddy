package web.controller.help;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.UserID;
import web.service.face.HelpService;
import web.service.impl.HelpServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class HelpBoardListController
 */
@WebServlet("/help/list")
public class HelpBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HelpService helpService = new HelpServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//요청파라미터를 전달하여 Paging 객체 생성하기
		Paging paging = helpService.getPaging(req);
		
		
//		게시글 페이징 처리 조회
		List<UserID> helpList = helpService.list(paging);  
		
		//페이징계산결과 MODEL값 전달
		req.setAttribute("paging", paging);
		
		//조회결과 MODEL값 전달
		req.setAttribute("list", helpList);
		
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/help/helplist.jsp").forward(req, resp);
		
	}
}
