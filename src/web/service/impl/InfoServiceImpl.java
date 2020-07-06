package web.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.InfoDao;
import web.dao.impl.InfoDaoImpl;
import web.dto.BookMarkHangOut;
import web.dto.BookMarkInfo;
import web.dto.Info;
import web.dto.UserTB;
import web.service.face.InfoService;
import web.util.Paging;

public class InfoServiceImpl implements InfoService {

	private InfoDao infoDao = new InfoDaoImpl();
	
	@Override
	public List<Info> list() {
		return infoDao.selectAll();
	}

	@Override
	public List<Info> viewPlaceList(Info info, String[] filters) {
		String city1 = info.getiCity1(); 
		String city2 = info.getiCity2(); 
		
		String result = "";
		for(String a : filters) {
			result += a;
		}
		
			if(result.equals("")) {
				if(!city1.equals("") && city2.equals("")) {
//					System.out.println("필터 없고 도시1 있음");
					return infoDao.selectByCity1(info);
				} else if (!city1.equals("") && !city2.equals("")){
//					System.out.println("필터 없고 도시 있음");
					return infoDao.selectByCity(info);
				}
			} else {
				if(city1.equals("") && city2.equals("")) {
//					System.out.println("필터 있고 도시 없음");
					return infoDao.selectByFilter(filters);
				} else if(!city1.equals("") && city2.equals("")) {
//					System.out.println("필터 있고 도시1 있음");
					return infoDao.selectByCity1(info, filters);
				} else if(!city1.equals("") && !city2.equals("")) {
//					System.out.println("필터 있고 도시 있음");
					return infoDao.selectByCityFilter(info, filters);
				}
			}

		
			return null;
		}

	@Override
	public void addPlace(Info info) {
		infoDao.insert(info);
		
	}

	@Override
	public List<Info> viewAddressByName(Info info) {
		return infoDao.selectByName(info);
	}

	@Override
	public Info viewAddressByNo(Info info) {
		return infoDao.selectByNo(info);
	}

	@Override
	public void delete(Info info) {
		infoDao.delete(info);
	}

	@Override
	public Info getiNo(HttpServletRequest req) {

		Info info = new Info();
		
		int iNo = Integer.parseInt((String)req.getParameter("iNo"));
		
		info.setiNo(iNo);
		
		return info;
	}

	@Override
	public Info view(Info info) {
		
		return infoDao.selectByNo(info);
	}

	@Override
	public List<Info> viewLocation(Info info) {
		String city1 = info.getiCity1();
		
		if(!city1.equals("")) {
//			System.out.println("도시1, 도시명 검색");
			return infoDao.selectByNameCity1(info);
		} else if(city1.equals("")) {
//			System.out.println("도시명 검색");
			return infoDao.selectByName(info);
		}
		
		return null;
	}

	@Override
	public List<Info> viewCity(Info info) {

		return infoDao.selectByCity1(info);
	}

	@Override
	public void insertBookMark(BookMarkInfo bm) {
		infoDao.insertBookMark(bm);
	}

	@Override
	public void deleteBookMark(BookMarkInfo bm) {
		infoDao.deleteBookMark(bm);
	}
	
	@Override
	public List<BookMarkInfo> bmList() {
		return infoDao.selectBookMarkList();
	}

	@Override
	public void delete(String names) {
		infoDao.delete(names);
	}
	
	@Override
	public BookMarkInfo getParamBmNo(HttpServletRequest req) {
		// 한글 인코딩
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// 결과 객체
		BookMarkInfo bm = new BookMarkInfo();
		
		String param = req.getParameter("bm_no");
		if(param != null && !"".equals(param)) {
			bm.setBmNo(Integer.parseInt(param));
		}
		
		return bm;
	}
	
	
	@Override
	public void deleteBookMarkNo(BookMarkInfo bm) {
		
		infoDao.deleteBookMarkNo(bm);
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {
		// 한글 인코딩
				try {
					req.setCharacterEncoding("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				// 현재 페이지
				String param = req.getParameter("curPage");
				int curPage = 0;
				if( param != null && !"".equals(param) ) {
					curPage = Integer.parseInt(param);
				}
				
				
				// 현재 사용자의 번호로 찜한 장소 목록 조회
				int userno = (int)req.getSession().getAttribute("userno");
				UserTB currUser = new UserTB();
				currUser.setUserNo(userno);
				// BookMarkInfo 테이블의 총 게시글 수를 조회한다
				int totalCnt = infoDao.selectCntAllByUserno(currUser);
				
				// Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수 (totalCnt) 활용
				Paging paging = new Paging(totalCnt, curPage);
				
				return paging;
	}
	

}
