package web.controller.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Info;
import web.service.face.InfoService;
import web.service.impl.InfoServiceImpl;

@WebServlet("/info/insert")
public class InfoInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InfoService infoService = new InfoServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		session.getAttribute("login");
		session.getAttribute("loginid");

		req.getRequestDispatcher("/WEB-INF/views/info/addPlace.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
//		System.out.println("hangout/insert - [POST]");

		HttpSession session = req.getSession();
		
		int userno = (int)session.getAttribute("userno");
				
		Info info = new Info();
		
		info.setUserNo(userno);
		info.setiName((String)req.getParameter("name"));
		info.setiFilter((String)req.getParameter("filter"));
		info.setStartTime((String)req.getParameter("startTime"));
		info.setEndTime((String)req.getParameter("endTime"));
		info.setiCity1((String)req.getParameter("city1"));
		info.setiCity2((String)req.getParameter("city2"));
		info.setAddress((String)req.getParameter("address"));
		info.setTel1((String)req.getParameter("tel1"));
		info.setTel2((String)req.getParameter("tel2"));
		info.setTel3((String)req.getParameter("tel3"));
		info.setiDomain((String)req.getParameter("domain"));
		info.setiContent((String)req.getParameter("content"));
		
		
		infoService.addPlace(info);
		
		resp.sendRedirect( req.getContextPath() + "/info/place");
	}
	
}
