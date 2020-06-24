package web.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.UserOneOnOneDao;
import web.dao.impl.UserOneOnOneDaoImpl;
import web.dto.OneOnOne;
import web.dto.UserTB;
import web.service.face.UserOneOnOneService;
import web.util.Paging;

public class UserOneOnOneServiceImpl implements UserOneOnOneService{

	private UserOneOnOneDao usero3Dao = new UserOneOnOneDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param != null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		String search = (String)req.getParameter("search");
		
		
		// Board 테이블의 총 게시글 수를 조회한다
//		int totalCnt = usero3Dao.selectCntAll();
//		int totalCnt = usero3Dao.selectCntAll(search);
		
		// 현재 사용자의 번호로 자신이 작성한 문의 내역 보여주기
		int userno = (int)req.getSession().getAttribute("userno");
		UserTB currUser = new UserTB();
		currUser.setUserNo(userno);
		int totalCnt = usero3Dao.selectCntByUserno(search, currUser);
//		System.out.println("getPaging() totalCnt: " + totalCnt);
		
		//Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCnt) 활용
		Paging paging = new Paging(totalCnt, curPage);
		
		// 검색어가 있을시
		if(search != null && !"".equals(search)) {
			paging.setSearch(search);
		}
		
		// Paging 객체 반환
		return paging;
	}
	
	@Override
	public List<OneOnOne> getOneOnOneList(Paging paging, UserTB currUser) {
		return usero3Dao.selectOneOnOne(paging, currUser);
	}
	
	@Override
	public void write(HttpServletRequest req) {
		
		// 한글 인코딩
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 1:1문의글 정보 저장할 객체
		OneOnOne o3 = new OneOnOne();
		o3.setTitle(req.getParameter("title"));
		o3.setContent(req.getParameter("content"));
		o3.setUserNo((int)req.getSession().getAttribute("userno"));
		
//		System.out.println("UserOneOnOneServiceImpl - o3: " + o3);
		
		usero3Dao.insert(o3);
		
	}
	
	@Override
	public OneOnOne getParam(HttpServletRequest req) {
		
		// 결과 객체
		OneOnOne o3 = new OneOnOne();
		
		String param = req.getParameter("oneonone_no");
		if(param != null && !"".equals(param)) {
			o3.setOneononeNo(Integer.parseInt(param));
		}
		
		return o3;
	}
	
	@Override
	public OneOnOne detail(OneOnOne detailO3) {
		OneOnOne o3 = usero3Dao.selectOneOnOneByNo(detailO3);
		
		return o3;
	}
	
	@Override
	public void update(HttpServletRequest req) {

		// 한글 인코딩
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// update 할 글의 정보를 갖는 OneOnOne 객체 생성
		OneOnOne o3 = new OneOnOne();
		o3.setTitle(req.getParameter("title"));
		o3.setContent(req.getParameter("content"));
		String param = req.getParameter("oneonone_no");
		if( param != null && !"".equals(param)) {
			o3.setOneononeNo(Integer.parseInt(param));
		}
		
//		System.out.println("UserO3ServiceImpl o3: " + o3);
		usero3Dao.update(o3);
		
	}
	
	@Override
	public void delete(OneOnOne o3) {
		usero3Dao.delete(o3);
		
	}

	@Override
	public OneOnOne detailQ(OneOnOne detailO3) {
		OneOnOne result = usero3Dao.selectReplyByNo(detailO3);
		
		return result;
	}
	
}
