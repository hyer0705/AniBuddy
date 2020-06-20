package web.controller.expertboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.UserID;
import web.service.face.ExpertBoardService;
import web.service.impl.ExpertBoardServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class ExpertBoardListController
 */
@WebServlet("/expertboard/list")
public class ExpertBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ExpertBoardService expertboardService = new ExpertBoardServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//요청파라미터를 전달하여 Paging 객체 생성하기
		Paging paging = expertboardService.getPaging(req);
		
//		게시글 페이징 처리 조회
		List<UserID> boardList = expertboardService.list(paging);  
		
		//페이징계산결과 MODEL값 전달
		req.setAttribute("paging", paging);
		
		//조회결과 MODEL값 전달
		req.setAttribute("list", boardList);
		
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/expertboard/expertList.jsp").forward(req, resp);
		
	}
	
}
