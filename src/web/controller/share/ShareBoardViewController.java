package web.controller.share;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.ShareComment;
import web.dto.ShareFile;
import web.dto.SharePost;
import web.dto.Share_calls;
import web.dto.UserID;
import web.service.face.ShareService;
import web.service.impl.ShareServiceImpl;

/**
 * Servlet implementation class ShareBoardViewController
 */
@WebServlet("/share/view")
public class ShareBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		System.out.println("view get");

		UserID board = shareService.getPostno(req);

		UserID result = shareService.view(board);

		//첨부파일 정보 view에 전달
		ShareFile shareFile = shareService.viewFile(result);
		req.setAttribute("shareFile", shareFile);

		req.setAttribute("viewShare", result);

		//추천
		Share_calls calls = new Share_calls();
		
		calls.setPost_no(result.getPostno());

		
		Object obj = req.getSession().getAttribute("userno");
		if( obj == null ) {
			obj = req.getSession().getAttribute("adminno");
		}
		if( obj == null ) {
			System.out.println("로그인 안함");
			return;
		}
		
		int tmpno = (int)obj;
		calls.setUser_id(tmpno);
		
		boolean isRecommend = shareService.isRecommend(calls);

		req.setAttribute("isRecommend", isRecommend);


		//		board.setPostno(Integer.parseInt(req.getParameter("post_no")));
		List<ShareComment> commentList = shareService.getCommentList(board);

		req.setAttribute("commentList", commentList);

		req.getRequestDispatcher("/WEB-INF/views/share/shareview.jsp").forward(req, resp);

	}
}
