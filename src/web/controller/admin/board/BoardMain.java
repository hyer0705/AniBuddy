package web.controller.admin.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.SharePost;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class BoardMain
 */
@WebServlet("/board/main")
public class BoardMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService = new BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		Paging paging = boardService.getPaging(req);

		List<SharePost> share = boardService.getShare(paging);		

		req.setAttribute("paging", paging);
		req.setAttribute("share", share);

		req.getRequestDispatcher("/WEB-INF/views/admin/board.jsp").forward(req, resp);

	}

}
