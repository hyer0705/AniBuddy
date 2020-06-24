package web.controller.admin.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.FreeBoard;
import web.dto.HelpPost;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class BoardhelpController
 */
@WebServlet("/board/help")
public class BoardhelpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService = new BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Paging paging = boardService.getPagingHelp(req);

		List<HelpPost> help = boardService.gethelp(paging);		

		req.setAttribute("paging", paging);

		req.setAttribute("help", help);


		req.getRequestDispatcher("/WEB-INF/views/admin/help.jsp").forward(req, resp);

	}
}
