package web.controller.freeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.FreeBoard;
import web.dto.FreeComment;
import web.service.face.FreeBoardService;
import web.service.impl.FreeBoardServiceImpl;

/**
 * Servlet implementation class FreeCommentInsertController
 */
@WebServlet("/freecomment/insert")
public class FreeCommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FreeBoardService freeboardService = new FreeBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		session.getAttribute("userno");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		FreeComment comment = freeboardService.getComment(req);
		FreeBoard board = new FreeBoard();
		
		
		
		freeboardService.insertComment(comment);
		
		board.setPostno(Integer.parseInt(req.getParameter("postno")));
		List commentList = freeboardService.getCommentList(board);
		
		req.setAttribute("commentList", commentList);
		
		
		resp.sendRedirect( req.getContextPath() + "/freeboard/view?postno="+board.getPostno());
	}
	
}
