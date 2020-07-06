package web.controller.help;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.HelpComment;
import web.dto.HelpFile;
import web.dto.HelpPost;
import web.dto.Help_calls;
import web.service.face.HelpService;
import web.service.impl.HelpServiceImpl;

/**
 * Servlet implementation class HelpViewController
 */
@WebServlet("/help/view")
public class HelpViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HelpService helpService = new HelpServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//		System.out.println("view get");

		HelpPost board = helpService.getPostno(req);

		HelpPost result = helpService.view(board);

		//첨부파일 정보 view에 전달
		HelpFile helpFile = helpService.viewFile(result);
		req.setAttribute("helpFile", helpFile);

		req.setAttribute("viewhelp", result);

		//추천
		Help_calls calls = new Help_calls();
		calls.setPost_no(result.getPostNo());
		
		Object obj = req.getSession().getAttribute("userno");
		if( obj == null ) {
			obj = req.getSession().getAttribute("adminno");
		}
		if( obj == null ) {
			System.out.println("로그인 안함");
			return;
		}
		
		int tmpno = (int)obj;
		calls.setUser_id(tmpno);
		
		boolean isRecommend = helpService.isRecommend(calls);
		
		req.setAttribute("isRecommendhelp", isRecommend);
		

		board.setPostNo(Integer.parseInt(req.getParameter("postno")));
		List<HelpComment> commentList = helpService.getCommentList(board);

		req.setAttribute("commentList", commentList);

		req.getRequestDispatcher("/WEB-INF/views/help/helpview.jsp").forward(req, resp);

	}
}
