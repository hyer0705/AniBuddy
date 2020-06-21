package web.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.UserManageDao;
import web.dao.impl.UserManageDaoImpl;
import web.dto.UserTB;
import web.service.face.UserManageService;
import web.util.Paging;

public class UserManageServiceImpl implements UserManageService{

	private UserManageDao userDao = new UserManageDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		String search = (String)req.getParameter("search");
		
		//Board 테이블의 총 게시글 수를 조회한다
		int totalCount = userDao.selectCntAll();

		//Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
		Paging paging = new Paging(totalCount, curPage);
		
		paging.setSearch(search);
		
		//Paging 객체 반환
		return paging;
	}

	@Override
	public List<UserTB> getUser(Paging paging) {
		return userDao.selectUser(paging);
	}

	@Override
	public void deleteUser(String param) {
		
		userDao.deleteUser(param);
		
	}
	
}
