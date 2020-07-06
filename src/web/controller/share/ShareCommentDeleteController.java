package web.controller.share;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.ShareComment;
import web.service.face.ShareService;
import web.service.impl.ShareServiceImpl;

/**
 * Servlet implementation class ShareCommentDeleteController
 */
@WebServlet("/sharecomment/delete")
public class ShareCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ShareService shareService = new ShareServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ShareComment comment = new ShareComment();
		
		String commentNo = (String) request.getParameter("commentNo");
		
		comment.setComment_no( Integer.parseInt(commentNo) );
		
		boolean success = shareService.deleteComment(comment);
		
		response.getWriter().append("{\"success\":"+success+"}");
		
	}
}
