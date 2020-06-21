package web.controller.admin.oneonone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.OneOnOne;
import web.service.face.OneOnOneService;
import web.service.impl.OneOnOneServiceImpl;

/**
 * Servlet implementation class OneOnOneAnswerController
 */
@WebServlet("/oneonone/answer")
public class OneOnOneAnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OneOnOneService oneOnOneService = new OneOnOneServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		OneOnOne viewOneOnOne = oneOnOneService.getparam(req);

		viewOneOnOne = oneOnOneService.view(viewOneOnOne);

		req.setAttribute("detail", viewOneOnOne);

		req.getRequestDispatcher("/WEB-INF/views/admin/answer.jsp").forward(req, resp);

	}
	
}
