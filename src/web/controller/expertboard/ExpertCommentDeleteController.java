package web.controller.expertboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.ExpertComment;
import web.service.face.ExpertBoardService;
import web.service.impl.ExpertBoardServiceImpl;

/**
 * Servlet implementation class ExpertCommentDeleteController
 */
@WebServlet("/expertcomment/delete")
public class ExpertCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ExpertBoardService boardService = new ExpertBoardServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ExpertComment comment = new ExpertComment();
		
		String commentNo = (String) request.getParameter("commentNo");
		
		comment.setCommentno( Integer.parseInt(commentNo) );
		
		boolean success = boardService.deleteComment(comment);
		
		response.getWriter().append("{\"success\":"+success+"}");
		
	}
	
}
