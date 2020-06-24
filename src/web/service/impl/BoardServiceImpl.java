package web.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.BoardDao;
import web.dao.impl.BoardDaoImpl;
import web.dto.ExpertBoard;
import web.dto.FreeBoard;
import web.dto.HelpPost;
import web.dto.SharePost;
import web.service.face.BoardService;
import web.util.Paging;

public class BoardServiceImpl implements BoardService {

	private BoardDao boardDao = new BoardDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		String search = (String)req.getParameter("search");
		
		//Board 테이블의 총 게시글 수를 조회한다
		int totalCount = 0;
		if( search != null && !"".equals(search) ) {
			totalCount = boardDao.selectCntBySearch(search); 
		} else {
			totalCount = boardDao.selectCntAll(); 
		}
		

		//Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
		Paging paging = new Paging(totalCount, curPage);
		
		paging.setSearch(search);
		
		//Paging 객체 반환
		return paging;
	}

	@Override
	public List<SharePost> getShare(Paging paging) {
		return boardDao.selectShare(paging);
	}
	
	@Override
	public List<ExpertBoard> getExpert(Paging paging) {
		
		return boardDao.selectExpert(paging);
	}

	@Override
	public List<FreeBoard> getFree(Paging paging) {
		return boardDao.selectFree(paging);
	}
	
	@Override
	public void deleteShare(String param) {
		boardDao.deleteShare(param);
		
	}
	
	@Override
	public void deleteExpert(String param) {
		
		boardDao.deleteExpert(param);
		
	}
	
	@Override
	public void deleteFree(String param) {
		boardDao.deleteFree(param);
		
	}

	@Override
	public Paging getPagingExpert(HttpServletRequest req) {
		
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		String search = (String)req.getParameter("search");
		
		//Board 테이블의 총 게시글 수를 조회한다
		// 검색어에 따라 다르게 selectCnt
		int totalCount = 0;
		
		if( search != null && !"".equals(search)) {
			totalCount = boardDao.selectCntExpert(search); 
		} else {
			totalCount = boardDao.selectCntExpert(); 
		}
		
		//Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
		Paging paging = new Paging(totalCount, curPage);
		
		paging.setSearch(search);
		
		//Paging 객체 반환
		return paging;
		
	}
	
	@Override
	public Paging getPagingFree(HttpServletRequest req) {
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		String search = (String)req.getParameter("search");
		
		//Board 테이블의 총 게시글 수를 조회한다
		// 검색어가 있을 때 없을 때 구분
		int totalCount = 0;
		if( search != null && !"".equals(search) ) {
			totalCount = boardDao.selectCntFree(search);
		} else {
			totalCount = boardDao.selectCntFree();
		}
		
		//Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
		Paging paging = new Paging(totalCount, curPage);
		
		paging.setSearch(search);
		
		//Paging 객체 반환
		return paging;
	}

	@Override
	public Paging getPagingHelp(HttpServletRequest req) {
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		String search = (String)req.getParameter("search");
		
		//Board 테이블의 총 게시글 수를 조회한다
		// 검색어의 유무에 따라 cnt 다르게
		int totalCount = 0;
		if(search != null && !"".equals(search)) {
			totalCount = boardDao.selectCntHelp(search);
		} else {
			totalCount = boardDao.selectCntHelp();
		}
		
		//Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
		Paging paging = new Paging(totalCount, curPage);
		
		paging.setSearch(search);
		
		//Paging 객체 반환
		return paging;
	}

	@Override
	public List<HelpPost> gethelp(Paging paging) {
		return boardDao.selectHelp(paging);
	}
	
	@Override
	public void deleteHelp(String param) {
		boardDao.deleteHelp(param);
		
	}
	
}
