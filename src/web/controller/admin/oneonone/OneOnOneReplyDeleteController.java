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
 * Servlet implementation class OneOnOneReplyDeleteController
 */
@WebServlet("/oneonone/replydelete")
public class OneOnOneReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OneOnOneService o3Service = new OneOnOneServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getSession().getAttribute("adminLogin") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		System.out.println("/anibuddy/oneonone/replydelete - [GET]");
		
		// oneonone_no 정보 얻기
		OneOnOne o3 = o3Service.getparam(req);
		System.out.println("delete controller o3: " + o3);
		
		// 삭제~
		o3Service.delete(o3);
		
		// QnA관리 메인으로 리다이렉트
		resp.sendRedirect("/anibuddy/oneonone/reply");
		
	}
	
}
