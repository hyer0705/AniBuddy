package web.controller.admin.oneonone;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.OneOnOne;
import web.dto.UserTB;
import web.service.face.OneOnOneService;
import web.service.impl.OneOnOneServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class OneOnOneReplyController
 */
@WebServlet("/oneonone/reply")
public class OneOnOneReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OneOnOneService oneOnOneService = new OneOnOneServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 관리자 로그인이 아니면 메인페이지로 리다이렉트
		if(req.getSession().getAttribute("adminLogin") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		// 페이징 정보 설정
		Paging paging = oneOnOneService.getPaging(req);

		List<OneOnOne> oneonone = oneOnOneService.getOneOnOne(paging);
		
		List<Map<UserTB, OneOnOne>> testList = oneOnOneService.getUserAndOneOnOne(paging);
//		Iterator<Map<UserTB, OneOnOne>> testIter = testList.iterator();
//		while(testIter.hasNext()) {
//			Map<UserTB, OneOnOne> m = testIter.next();
//			Set<UserTB> set = m.keySet();
//			Iterator<UserTB> key = set.iterator();
//			
//			while(key.hasNext()) {
//				UserTB userKey = key.next();
//				System.out.println(userKey + ": " + m.get(userKey));
//			}
//		}
		
		
		req.setAttribute("paging", paging);
		req.setAttribute("oneonone", oneonone);
//		req.setAttribute("testList", testList);

		req.getRequestDispatcher("/WEB-INF/views/admin/oneonone.jsp").forward(req, resp);

	}
	
}
