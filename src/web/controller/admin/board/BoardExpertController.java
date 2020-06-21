package web.controller.admin.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.ExpertBoard;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class BoardExpertController
 */
@WebServlet("/board/expert")
public class BoardExpertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService = new BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Paging paging = boardService.getPaging(req);

		List<ExpertBoard> expert = boardService.getExpert(paging);		

		req.setAttribute("paging", paging);

		req.setAttribute("expert", expert);


		req.getRequestDispatcher("/WEB-INF/views/admin/Expert.jsp").forward(req, resp);

	}
	
}
