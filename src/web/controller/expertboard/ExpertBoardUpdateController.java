package web.controller.expertboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.ExpertBoard;
import web.dto.ExpertBoardFile;
import web.service.face.ExpertBoardService;
import web.service.impl.ExpertBoardServiceImpl;

/**
 * Servlet implementation class ExpertBoardUpdateController
 */
@WebServlet("/expertboard/update")
public class ExpertBoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private ExpertBoardService expertboardService = new ExpertBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		//로그인한 사람의 글이 아니면 중단하고 목록으로 리다이렉트
		if( !expertboardService.checkId(req) ) {
			resp.sendRedirect("/anibuddy/expertboard/list");
			return;
		}
		
		//게시글 번호 파싱
		ExpertBoard viewBoard = expertboardService.getPostno(req);
		
		//게시글 조회
		viewBoard = expertboardService.view(viewBoard);
		
		//MODEL로 게시글 전달
		req.setAttribute("viewBoard", viewBoard);
		
		//첨부파일 전달
		ExpertBoardFile boardFile = expertboardService.viewFile(viewBoard);
		req.setAttribute("boardFile", boardFile);
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/expertboard/expertupdate.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		expertboardService.update(req);
		
		resp.sendRedirect( req.getContextPath() + "/expertboard/list");
	}
	
}
