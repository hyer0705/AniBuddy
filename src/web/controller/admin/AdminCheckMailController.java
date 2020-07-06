package web.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Email;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class AdminCheckMailController
 */
@WebServlet("/admin/mailcheck")
public class AdminCheckMailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Paging paging = boardService.getPagingMail(req);
		
		req.setAttribute("paging", paging);
		
		List<Email> email = boardService.getEmail(paging);
		req.setAttribute("email", email);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/admin_mailcheck.jsp").forward(req, resp);
	}

}
