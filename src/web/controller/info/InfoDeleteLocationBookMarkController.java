package web.controller.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.BookMarkInfo;
import web.service.face.InfoService;
import web.service.impl.InfoServiceImpl;

@WebServlet("/info/bmDeleteLocation")
public class InfoDeleteLocationBookMarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InfoService infoService = new InfoServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("info/bmDelete - [POST]");
		
		req.setCharacterEncoding("utf-8");
		
		HttpSession session = req.getSession();
		
		BookMarkInfo bm = new BookMarkInfo();
		
		bm.setiNo(Integer.parseInt(req.getParameter("iNo")));
		bm.setUserNo((int)session.getAttribute("userno"));
		
		infoService.deleteBookMark(bm);
		
		resp.sendRedirect(req.getContextPath() + "/info/location");
		
	}
	
}
