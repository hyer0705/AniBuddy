package web.controller.freeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.FreeBoardService;
import web.service.impl.FreeBoardServiceImpl;

/**
 * Servlet implementation class FreeBoardWriteController
 */
@WebServlet("/freeboard/write")
public class FreeBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FreeBoardService freeboardService = new FreeBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/freeboard/freeWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//작성글 삽입
		freeboardService.write(req);
		//목록으로 리다이렉션
		resp.sendRedirect("/anibuddy/freeboard/list");
	}
	
}
