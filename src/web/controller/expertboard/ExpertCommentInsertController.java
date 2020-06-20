package web.controller.expertboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.ExpertBoard;
import web.dto.ExpertComment;
import web.service.face.ExpertBoardService;
import web.service.impl.ExpertBoardServiceImpl;

/**
 * Servlet implementation class ExpertCommentInsertController
 */
@WebServlet("/expertcomment/insert")
public class ExpertCommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ExpertBoardService expertboardService = new ExpertBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		session.getAttribute("userno");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ExpertComment comment = expertboardService.getComment(req);
		ExpertBoard board = new ExpertBoard();
		
		expertboardService.insertComment(comment);
		
		board.setPostno(Integer.parseInt(req.getParameter("postno")));
		List commentList = expertboardService.getCommentList(board);
		
		req.setAttribute("commentList", commentList);
		
		
		resp.sendRedirect( req.getContextPath() + "/expertboard/view?postno="+board.getPostno());
	}

	
}
