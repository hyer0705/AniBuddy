package web.controller.freeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.UserID;
import web.service.face.FreeBoardService;
import web.service.impl.FreeBoardServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class FreeBoardListController
 */
@WebServlet("/freeboard/list")
public class FreeBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FreeBoardService freeboardService = new FreeBoardServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//요청파라미터를 전달하여 Paging 객체 생성하기
		Paging paging = freeboardService.getPaging(req);
		
		
//		게시글 페이징 처리 조회
		List<UserID> boardList = freeboardService.list(paging);  
		
		//페이징계산결과 MODEL값 전달
		req.setAttribute("paging", paging);
		
		//조회결과 MODEL값 전달
		req.setAttribute("list", boardList);
		
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/freeboard/freeList.jsp").forward(req, resp);
		
	}
	
}
