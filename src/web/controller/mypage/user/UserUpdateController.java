package web.controller.mypage.user;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.UserTB;
import web.service.face.UserService;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserUpdateController
 */
@WebServlet("/mypage/userupdate")
public class UserUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인이 되어있지 않으면 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("login") == null ) {
			resp.sendRedirect( req.getContextPath() + "/");
			return;
		}
		
		System.out.println("/anibuddy/mypage/userupdate- - [GET]");
		
		// 사용자 정보 얻어오기
		UserTB user = userService.getLoginUserByUserno(req);
//		System.out.println("UserUpdateController user: " + user);
		
		// 사용자 번호로 검색하기
		user = userService.getFindUserByUserno(user);
//		System.out.println("UserUpdateController user: " + user);
		
		// 사용자 정보 req에 설정
		req.setAttribute("user", user);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birth = sdf.format(user.getBirth());
		
		String[] tmp = birth.split("-");
		req.setAttribute("byear", tmp[0]);
		req.setAttribute("bmonth", tmp[1]);
		req.setAttribute("bday", tmp[2]);
		
		System.out.println("user email: " + user.getEmail());
		String[] email = user.getEmail().split("@");
		req.setAttribute("email", email);
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/userupdate.jsp")
			.forward(req, resp);
	}

}
