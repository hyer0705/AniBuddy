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
import web.service.face.UserService;
import web.service.impl.OneOnOneServiceImpl;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class OneOnOneReplyUpdateController
 */
@WebServlet("/oneonone/replyupdate")
public class OneOnOneReplyUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OneOnOneService o3Service = new OneOnOneServiceImpl();
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 관리자 로그인 안되어 있으면 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("adminLogin") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		System.out.println("/anibuddy/oneonone/replyupdate - [GET]");
		
		// 1:1 문의 내용 얻어오기 - 제목, 내용
		//	1) 문의 번호 얻기
		OneOnOne o3 = o3Service.getparam(req);
		System.out.println("O3ReplyController o3: " + o3);

		// 1:1 문의 답변 얻어오기
		OneOnOne q = o3Service.getOneOnOneByNo(o3);
		System.out.println("O3ReplyupdateController q: " + q);
		
		//	2) 문의 번호를 통해서 해당 문의글 조회하기
		//	지금은 답변글의 번호를 통해 질문글을 찾는 방법을 사용
		o3 = o3Service.getOneOnOneByReplyNo(o3);
		System.out.println("O3ReplyupdateController o3: " + o3);

		//	3) 문의글 객체를 통해 사용자 아이디 얻기 
		// 답변글인 경우 다른 식으로 사용자 검색!
		UserTB user = new UserTB();
		user.setUserNo(o3.getUserNo());
		user = userService.getFindUserByUserno(user);
//		System.out.println("O3ReplyController user: " + user);
		

		// req 객체 설정 문의 글, 문의 글 작성자, 문의 글의 답변 글
		req.setAttribute("o3", o3);
		req.setAttribute("q", q);
		req.setAttribute("userid", user.getUserId());

		req.getRequestDispatcher("/WEB-INF/views/admin/oneonone_reply_update_new.jsp")
		.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 관리자 로그인 안되어 있으면 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("adminLogin") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		System.out.println("/anibuddy/oneonone/replyupdate - [POST]");
		
		// 해당 글 수정
		o3Service.update(req);
		
		// 수정 된 답변 상세보기 페이지로 리다이렉트
		resp.sendRedirect("/anibuddy/oneonone/answer?oneonone_no=" + req.getParameter("oneonone_no"));
		
	}
	
}
