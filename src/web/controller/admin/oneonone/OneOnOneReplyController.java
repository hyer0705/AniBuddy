package web.controller.admin.oneonone;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.OneOnOne;
import web.service.face.OneOnOneService;
import web.service.impl.OneOnOneServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class OneOnOneReplyController
 */
@WebServlet("/oneonone/reply")
public class OneOnOneReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OneOnOneService oneOnOneService = new OneOnOneServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Paging paging = oneOnOneService.getPaging(req);

		List<OneOnOne> oneonone = oneOnOneService.getOneOnOne(paging);		

		req.setAttribute("paging", paging);
		req.setAttribute("oneonone", oneonone);


		req.getRequestDispatcher("/WEB-INF/views/admin/oneonone.jsp").forward(req, resp);

	}
	
}
