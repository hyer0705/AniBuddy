package web.controller.help;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.HelpFile;
import web.dto.HelpPost;
import web.service.face.HelpService;
import web.service.impl.HelpServiceImpl;

/**
 * Servlet implementation class HelpBoardUpdateController
 */
@WebServlet("/help/update")
public class HelpBoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HelpService helpService = new HelpServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		//로그인한 사람의 글이 아니면 중단하고 목록으로 리다이렉트
		if( !helpService.checkId(req) ) {
			resp.sendRedirect( req.getContextPath() + "/freeboard/list");
			return;
		}

		//게시글 번호 파싱
		HelpPost viewBoard = helpService.getPostno(req);

		//게시글 조회
		viewBoard = helpService.view(viewBoard);

		//MODEL로 게시글 전달
		req.setAttribute("viewBoard", viewBoard);

		//첨부파일 전달
		HelpFile helpFile = helpService.viewFile(viewBoard);
		req.setAttribute("boardFile", helpFile);

		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/help/helpupdate.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		helpService.update(req);

		resp.sendRedirect( req.getContextPath() + "/help/list");
	
	}
}
