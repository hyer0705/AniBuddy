package web.controller.share;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.ShareService;
import web.service.impl.ShareServiceImpl;

/**
 * Servlet implementation class ShareBoardWriteController
 */
@WebServlet("/share/write")
public class ShareBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ShareService shareService = new ShareServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/share/shareWrite.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//작성글 삽입
		shareService.write(req);
		//목록으로 리다이렉션
		resp.sendRedirect("/anibuddy/share/list");
	}
}
