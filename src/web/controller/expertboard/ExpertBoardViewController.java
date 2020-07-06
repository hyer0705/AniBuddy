package web.controller.expertboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.ExpertBoard;
import web.dto.ExpertBoardFile;
import web.dto.ExpertComment;
import web.service.face.ExpertBoardService;
import web.service.impl.ExpertBoardServiceImpl;

/**
 * Servlet implementation class ExpertBoardViewController
 */
@WebServlet("/expertboard/view")
public class ExpertBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

private ExpertBoardService expertboardService = new ExpertBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getSession().getAttribute("login") == null && req.getSession().getAttribute("adminLogin") == null) {
			
			resp.sendRedirect("/anibuddy/expertboard/list");
			return;
		}
		
		ExpertBoard board = expertboardService.getPostno(req);
		
		ExpertBoard result = expertboardService.view(board);
		
		//첨부파일 정보 view에 전달
		ExpertBoardFile boardFile = expertboardService.viewFile(result);
		req.setAttribute("boardFile", boardFile);
		
		
		req.setAttribute("viewBoard", result);

		board.setPostno(Integer.parseInt(req.getParameter("postno")));
		List commentList = expertboardService.getCommentList(board);
		
		req.setAttribute("commentList", commentList);
		
		req.getRequestDispatcher("/WEB-INF/views/expertboard/expertView.jsp").forward(req, resp);
		
	}
}
