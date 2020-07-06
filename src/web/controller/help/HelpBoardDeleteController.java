package web.controller.help;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.HelpPost;
import web.service.face.HelpService;
import web.service.impl.HelpServiceImpl;

/**
 * Servlet implementation class HelpBoardDeleteController
 */
@WebServlet("/help/delete")
public class HelpBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//BoardService 객체
			private HelpService helpService = new HelpServiceImpl();

			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

				HelpPost post = helpService.getPostno(req);

				helpService.delete(post);

				//목록으로 리다이렉트
				resp.sendRedirect( req.getContextPath() + "/help/list");

			}
}
