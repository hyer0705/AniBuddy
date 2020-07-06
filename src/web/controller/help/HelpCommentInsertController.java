package web.controller.help;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.HelpComment;
import web.dto.HelpPost;
import web.service.face.HelpService;
import web.service.impl.HelpServiceImpl;

/**
 * Servlet implementation class HelpCommentInsertController
 */
@WebServlet("/helpcomment/insert")
public class HelpCommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HelpService helpService = new HelpServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		session.getAttribute("userno");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HelpComment comment = helpService.getComment(req);
		HelpPost post = new HelpPost();
		
		
		
		helpService.insertComment(comment);
		
		post.setPostNo(Integer.parseInt(req.getParameter("postno")));
		List<HelpComment> commentList = helpService.getCommentList(post);
		
		req.setAttribute("commentList", commentList);
		
		
		resp.sendRedirect( req.getContextPath() + "/help/view?postno="+post.getPostNo());
	}
}
