package web.controller.admin.oneonone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.OneOnOne;
import web.service.face.OneOnOneService;
import web.service.face.UserOneOnOneService;
import web.service.impl.OneOnOneServiceImpl;
import web.service.impl.UserOneOnOneServiceImpl;

/**
 * Servlet implementation class OneOnOneAnswerController
 */
@WebServlet("/oneonone/answer")
public class OneOnOneAnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OneOnOneService oneOnOneService = new OneOnOneServiceImpl();
	private UserOneOnOneService userO3Service = new UserOneOnOneServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		OneOnOne viewOneOnOne = oneOnOneService.getparam(req);

		// 질문글 보여주기
		viewOneOnOne = oneOnOneService.view(viewOneOnOne);
		System.out.println("/anibuddy/oneonone/answer 질문글 viewOnOnOne: " + viewOneOnOne);
		
		// 해당 질문글에 대한 답변글 조회 
		OneOnOne q = userO3Service.detailQ(viewOneOnOne);
//		System.out.println("/anibuddy/oneonone/answer 답변글 q: " + q);

		// 조회된 질문글 req 값 설정
		req.setAttribute("detail", viewOneOnOne);
		
		// 조회된 답변글 req 값 설정
		req.setAttribute("q", q);

		req.getRequestDispatcher("/WEB-INF/views/admin/answer_new.jsp").forward(req, resp);

	}
	
}
