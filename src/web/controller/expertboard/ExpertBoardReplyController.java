package web.controller.expertboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.ExpertBoard;
import web.service.face.ExpertBoardService;
import web.service.impl.ExpertBoardServiceImpl;

/**
 * Servlet implementation class ExpertBoardReplyController
 */
@WebServlet("/expertboard/reply")
public class ExpertBoardReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ExpertBoardService expertboardService = new ExpertBoardServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ExpertBoard viewBoard = expertboardService.getPostno(req);
		viewBoard = expertboardService.view(viewBoard);
		req.setAttribute("viewBoard", viewBoard);
		
		req.getRequestDispatcher("/WEB-INF/views/expertboard/expertReply.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		expertboardService.replywrite(req);

		resp.sendRedirect( req.getContextPath() + "/expertboard/list");
	}
	
}
