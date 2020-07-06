package web.controller.share;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.ShareComment;
import web.dto.SharePost;
import web.dto.UserID;
import web.service.face.ShareService;
import web.service.impl.ShareServiceImpl;

/**
 * Servlet implementation class ShareCommentInsertController
 */
@WebServlet("/sharecomment/insert")
public class ShareCommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ShareService shareService = new ShareServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Object obj = req.getSession().getAttribute("userno");
		if( obj == null ) {
			obj = req.getSession().getAttribute("adminno");
		}
		String tmpno = (String)obj;
		
		HttpSession session = req.getSession();
		session.getAttribute(tmpno);
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ShareComment comment = shareService.getComment(req);
		shareService.insertComment(comment);
		UserID post = new UserID();
		
		
		
		post.setPostno(Integer.parseInt(req.getParameter("postno")));
			
		List<ShareComment> commentList =null;
		
			commentList = shareService.getCommentList(post);
		
		
		req.setAttribute("commentList", commentList);
		
		
		resp.sendRedirect( req.getContextPath() + "/share/view?postno="+post.getPostno());
	}
	
	
}
