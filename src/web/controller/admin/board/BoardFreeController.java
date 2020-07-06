package web.controller.admin.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.FreeBoard;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class BoardFreeController
 */
@WebServlet("/board/free")
public class BoardFreeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService = new BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Paging paging = boardService.getPagingFree(req);
//		System.out.println("BoardFreeController paging: " + paging);

		List<FreeBoard> free = boardService.getFree(paging);		

		req.setAttribute("paging", paging);

		req.setAttribute("free", free);


		req.getRequestDispatcher("/WEB-INF/views/admin/free.jsp").forward(req, resp);

	}
	
}
