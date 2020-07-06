package web.controller.help;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.HelpService;
import web.service.impl.HelpServiceImpl;

/**
 * Servlet implementation class HelpBoardWriteController
 */
@WebServlet("/help/write")
public class HelpBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HelpService helpService = new HelpServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/help/helpwrite.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//작성글 삽입
		helpService.write(req);
		//목록으로 리다이렉션
		resp.sendRedirect("/anibuddy/help/list");
	}
}
