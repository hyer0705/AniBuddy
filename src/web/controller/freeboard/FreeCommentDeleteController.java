package web.controller.freeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.FreeComment;
import web.service.face.FreeBoardService;
import web.service.impl.FreeBoardServiceImpl;

/**
 * Servlet implementation class FreeCommentDeleteController
 */
@WebServlet("/freecomment/delete")
public class FreeCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FreeBoardService boardService = new FreeBoardServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		FreeComment comment = new FreeComment();
		
		String commentNo = (String) request.getParameter("commentNo");
		
		comment.setCommentno( Integer.parseInt(commentNo) );
		
		boolean success = boardService.deleteComment(comment);
		
		response.getWriter().append("{\"success\":"+success+"}");
		
	}
	
}
