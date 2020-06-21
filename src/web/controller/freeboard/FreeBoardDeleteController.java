package web.controller.freeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.FreeBoard;
import web.service.face.FreeBoardService;
import web.service.impl.FreeBoardServiceImpl;

/**
 * Servlet implementation class FreeBoardDeleteController
 */
@WebServlet("/freeboard/delete")
public class FreeBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//BoardService 객체
	private FreeBoardService freeboardService = new FreeBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		FreeBoard board = freeboardService.getPostno(req);

		freeboardService.delete(board);

		//목록으로 리다이렉트
		resp.sendRedirect( req.getContextPath() + "/freeboard/list");

	}
	
}
