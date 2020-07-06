package web.controller.share;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.ShareFile;
import web.dto.SharePost;
import web.dto.UserID;
import web.service.face.ShareService;
import web.service.impl.ShareServiceImpl;

/**
 * Servlet implementation class ShareBoardUpdateController
 */
@WebServlet("/share/update")
public class ShareBoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ShareService shareService = new ShareServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		//로그인한 사람의 글이 아니면 중단하고 목록으로 리다이렉트
		if( !shareService.checkId(req) ) {
			resp.sendRedirect( req.getContextPath() + "/freeboard/list");
			return;
		}

		//게시글 번호 파싱
		UserID viewBoard = shareService.getPostno(req);

		//게시글 조회
		viewBoard = shareService.view(viewBoard);

		//MODEL로 게시글 전달
		req.setAttribute("viewBoard", viewBoard);

		//첨부파일 전달
		ShareFile shareFile = shareService.viewFile(viewBoard);
		req.setAttribute("boardFile", shareFile);

		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/share/shareupdate.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		shareService.update(req);

		resp.sendRedirect( req.getContextPath() + "/share/list");
	}

}
