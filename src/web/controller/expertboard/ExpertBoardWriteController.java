package web.controller.expertboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.ExpertBoardService;
import web.service.impl.ExpertBoardServiceImpl;

/**
 * Servlet implementation class ExpertBoardWriteController
 */
@WebServlet("/expertboard/write")
public class ExpertBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ExpertBoardService expertboardService = new ExpertBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		로그인 되어있지 않으면 리다이렉트
		if(req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/");
			return;
		}
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/expertboard/expertWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("ex write post");
		
		//작성글 삽입
		expertboardService.write(req);
		//목록으로 리다이렉션
		resp.sendRedirect( req.getContextPath() + "/expertboard/list");
	}
	
}
