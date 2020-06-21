package web.controller.freeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.FreeBoard;
import web.dto.FreeBoardFile;
import web.service.face.FreeBoardService;
import web.service.impl.FreeBoardServiceImpl;

/**
 * Servlet implementation class FreeBoardViewController
 */
@WebServlet("/freeboard/view")
public class FreeBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FreeBoardService freeboardService = new FreeBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		System.out.println("view get");

		FreeBoard board = freeboardService.getPostno(req);

		FreeBoard result = freeboardService.view(board);

		//첨부파일 정보 view에 전달
		FreeBoardFile boardFile = freeboardService.viewFile(result);
		req.setAttribute("boardFile", boardFile);


		req.setAttribute("viewBoard", result);

		board.setPostno(Integer.parseInt(req.getParameter("postno")));
		List commentList = freeboardService.getCommentList(board);

		req.setAttribute("commentList", commentList);

		req.getRequestDispatcher("/WEB-INF/views/freeboard/freeView.jsp").forward(req, resp);

	}
	
}
