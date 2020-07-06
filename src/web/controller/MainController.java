package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.UserID;
import web.service.face.ExpertBoardService;
import web.service.face.FreeBoardService;
import web.service.face.HelpService;
import web.service.face.ShareService;
import web.service.impl.ExpertBoardServiceImpl;
import web.service.impl.FreeBoardServiceImpl;
import web.service.impl.HelpServiceImpl;
import web.service.impl.ShareServiceImpl;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ExpertBoardService expertboardService = new ExpertBoardServiceImpl();
	private FreeBoardService freeboardService = new FreeBoardServiceImpl();
	private ShareService shareService = new ShareServiceImpl();
	private HelpService helpService = new HelpServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("/anibuddy/main - [GET]");
		
		   List<UserID> boardList = expertboardService.list();  
		   List<UserID> boardList2 = freeboardService.list();
		   List<UserID> boardList3 = shareService.list();
		   List<UserID> boardList4 = helpService.list();
		   
		   //조회결과 MODEL값 전달
		   req.setAttribute("list", boardList);
		   req.setAttribute("list2", boardList2);
		   req.setAttribute("list3", boardList3);
		   req.setAttribute("list4", boardList4);
		
		req.getRequestDispatcher("/main.jsp")
		.forward(req, resp);
		
		
	}

}
