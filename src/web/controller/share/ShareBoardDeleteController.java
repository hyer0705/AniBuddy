package web.controller.share;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.SharePost;
import web.dto.UserID;
import web.service.face.ShareService;
import web.service.impl.ShareServiceImpl;

/**
 * Servlet implementation class ShareBoardDeleteController
 */
@WebServlet("/share/delete")
public class ShareBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//BoardService 객체
		private ShareService shareService = new ShareServiceImpl();

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			UserID post = shareService.getPostno(req);

			shareService.delete(post);

			//목록으로 리다이렉트
			resp.sendRedirect( req.getContextPath() + "/share/list");

		}
}
