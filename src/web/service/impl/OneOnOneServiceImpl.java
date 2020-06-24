package web.service.impl;

import java.io.UnsupportedEncodingException;
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
		
		
		// 검색어가 있을 시
		if( search != null && !"".equals(search)) {
			paging.setSearch(search);
		}
		
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

//		String condition = oneOnOne.getCondition();
//
//		if( "true".equals(condition)) {
//
//			oneOnOne =  oneOnOneDao.insertCondition(viewOneOnOne);
//		}
//
//		oneOnOne = oneOnOneDao.selectOneOnOneByno(viewOneOnOne);

		return oneOnOne;
	}

	@Override
	public void oneOnOneDelete(String names) {
		
		oneOnOneDao.deleteOne(names);
	}
	
	@Override
	public OneOnOne getOneOnOneByNo(OneOnOne o3) {
		return oneOnOneDao.selectOneOnOneByno(o3);
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
		String reply = req.getParameter("oneonone_no");
		if( reply != null && !"".equals(reply) ) {
			o3.setReplyNo(Integer.parseInt(reply));
		}
		o3.setTitle(req.getParameter("title"));
		o3.setContent(req.getParameter("content"));
		// 관리자가 없다면? - 추후에 생각
		o3.setAdminNo((int)req.getSession().getAttribute("adminno"));
		
		// 게시글 삽입
		System.out.println("삽입될 답변글 o3: " + o3);
		oneOnOneDao.insert(o3);
		
		// 해당 질문글 update 도 진행해야함!
		// 질문 번호를 갖는 OneOnOne 객체
		OneOnOne q = new OneOnOne();
		q.setOneononeNo(o3.getReplyNo());
		System.out.println("수정될 질문글 q: " + q);
		oneOnOneDao.updateQuestion(q);
		
	}
	
	@Override
	public OneOnOne getOneOnOneByReplyNo(OneOnOne o3) {
		return oneOnOneDao.selectOneOnOneByReplyno(o3);
	}
	
	@Override
	public void update(HttpServletRequest req) {
		
		// 한글 인코딩
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// update 할 글의 정보를 갖는 OneOnOne 객체 생성
		OneOnOne q = new OneOnOne();
		q.setTitle(req.getParameter("title"));
		q.setContent(req.getParameter("content"));
		String param = req.getParameter("oneonone_no");
		if( param != null && !"".equals(param)) {
			q.setOneononeNo(Integer.parseInt(param));
		}
		
//		System.out.println("UserO3ServiceImpl q: " + q);
		
		oneOnOneDao.update(q);
		
	}
	
	@Override
	public void delete(OneOnOne o3) {
		oneOnOneDao.deleteOne(String.valueOf(o3.getOneononeNo()));
	}
	
}
