package web.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.OneOnOneDao;
import web.dao.impl.OneOnOneDaoImpl;
import web.dto.OneOnOne;
import web.service.face.OneOnOneService;
import web.util.Paging;

public class OneOnOneServiceImpl implements OneOnOneService{

	private OneOnOneDao oneOnOneDao = new OneOnOneDaoImpl();

	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		String search = (String)req.getParameter("search");
		
		//Board 테이블의 총 게시글 수를 조회한다
//		int totalCount = oneOnOneDao.selectCntAll();
		int totalCount = oneOnOneDao.selectCntAll(search);

		//Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
		Paging paging = new Paging(totalCount, curPage);
		
		paging.setSearch(search);
		
		//Paging 객체 반환
		return paging;
	}

	@Override
	public List<OneOnOne> getOneOnOne(Paging paging) {
		return oneOnOneDao.selectOneOnOne(paging);
	}

	@Override
	public OneOnOne getparam(HttpServletRequest req) {
		OneOnOne oneOnOne = new OneOnOne();
		String param = req.getParameter("oneonone_no");
		
		if(param != null && !"".equals(param)) {
			
			oneOnOne.setOneononeNo(Integer.parseInt(param));
			
		}
		return oneOnOne;
	}

	@Override
	public OneOnOne view(OneOnOne viewOneOnOne) {
		OneOnOne oneOnOne = oneOnOneDao.selectOneOnOneByno(viewOneOnOne);

		String condition = oneOnOne.getCondition();

		if( "true".equals(condition)) {

			oneOnOne =  oneOnOneDao.insertCondition(viewOneOnOne);
		}

		oneOnOne = oneOnOneDao.selectOneOnOneByno(viewOneOnOne);

		return oneOnOne;
	}

	@Override
	public void oneOnOneDelete(String names) {
		
		oneOnOneDao.deleteOne(names);
	}
	
}
