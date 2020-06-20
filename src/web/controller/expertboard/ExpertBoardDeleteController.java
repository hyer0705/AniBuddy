package web.controller.expertboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.ExpertBoard;
import web.service.face.ExpertBoardService;
import web.service.impl.ExpertBoardServiceImpl;

/**
 * Servlet implementation class ExpertBoardDeleteController
 */
@WebServlet("/expertboard/delete")
public class ExpertBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//BoardService 객체
	private ExpertBoardService expertboardService = new ExpertBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ExpertBoard board = expertboardService.getPostno(req);

		expertboardService.delete(board);

		resp.sendRedirect( req.getContextPath() + "/expertboard/list");

	}


}
