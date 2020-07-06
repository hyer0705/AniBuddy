package web.controller.hangout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.BookMarkHangOut;
import web.service.face.HangOutService;
import web.service.impl.HangOutServiceImpl;

@WebServlet("/hangout/bmDeleteLocation")
public class HangOutDeleteLocationBookMarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HangOutService hangoutService = new HangOutServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("hangout/bmDeleteLocation - [POST]");
		
		req.setCharacterEncoding("utf-8");
		
		HttpSession session = req.getSession();
		
		BookMarkHangOut bm = new BookMarkHangOut();
		
		bm.sethNo(Integer.parseInt(req.getParameter("hNo")));
		bm.setUserNo((int)session.getAttribute("userno"));
		
		hangoutService.deleteBookMark(bm);
		
		resp.sendRedirect(req.getContextPath() + "/hangout/location");
		
	}
	
}
