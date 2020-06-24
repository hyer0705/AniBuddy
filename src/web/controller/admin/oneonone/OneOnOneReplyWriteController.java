package web.controller.admin.oneonone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.OneOnOne;
import web.dto.UserTB;
import web.service.face.OneOnOneService;
import web.service.face.UserOneOnOneService;
import web.service.face.UserService;
import web.service.impl.OneOnOneServiceImpl;
import web.service.impl.UserOneOnOneServiceImpl;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class OneOnOneReplyWriteController
 */
@WebServlet("/oneonone/replywrite")
public class OneOnOneReplyWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OneOnOneService o3Service = new OneOnOneServiceImpl();
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 관리자가 아니면 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("adminLogin") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		System.out.println("/anibuddy/oneonone/replywrite - [GET]");
		
		// 1:1 문의 내용 얻어오기 - 제목, 내용
		//	1) 문의 번호 얻기
		OneOnOne o3 = o3Service.getparam(req);
//		System.out.println("O3ReplyController o3: " + o3);
		
		//	2) 문의 번호를 통해서 해당 문의글 조회하기
		o3 = o3Service.getOneOnOneByNo(o3);
//		System.out.println("O3ReplyController o3: " + o3);
		
		//	3) 문의글 객체를 통해 사용자 아이디 얻기
		UserTB user = new UserTB();
		user.setUserNo(o3.getUserNo());
		user = userService.getFindUserByUserno(user);
//		System.out.println("O3ReplyController user: " + user);
		
		
		// req 객체 설정 문의 글, 문의 글 작성자
		req.setAttribute("o3", o3);
		req.setAttribute("userid", user.getUserId());
		
		
		req.getRequestDispatcher("/WEB-INF/views/admin/oneonone_reply_write.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 관리자가 아니면 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("adminLogin") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		System.out.println("/anibuddy/oneonone/replywrite - [POST] ");
		
		
		// 글 삽입 -> 답변한 글의 부모글 업데이트
		o3Service.write(req);

		// 질문 글 상세보기로 리다이렉트
		resp.sendRedirect("/anibuddy/oneonone/answer?oneonone_no="+req.getParameter("oneonone_no"));
		
	}
	
}
