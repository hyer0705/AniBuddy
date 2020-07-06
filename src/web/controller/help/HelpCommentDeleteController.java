package web.controller.help;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.HelpComment;
import web.service.face.HelpService;
import web.service.impl.HelpServiceImpl;

/**
 * Servlet implementation class HelpCommentDeleteController
 */
@WebServlet("/helpcomment/delete")
public class HelpCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HelpService helpService = new HelpServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HelpComment comment = new HelpComment();
		
		String commentNo = (String) request.getParameter("commentNo");
		
		comment.setComment_no( Integer.parseInt(commentNo) );
		
		boolean success = helpService.deleteComment(comment);
		
		response.getWriter().append("{\"success\":"+success+"}");
		
	}
}
