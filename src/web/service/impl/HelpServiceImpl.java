package web.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import web.dao.face.HelpCommentDao;
import web.dao.face.HelpDao;
import web.dao.impl.HelpCommentDaoImpl;
import web.dao.impl.HelpDaoImpl;
import web.dto.HelpComment;
import web.dto.HelpFile;
import web.dto.HelpPost;
import web.dto.Help_calls;
import web.dto.Share_calls;
import web.dto.UserID;
import web.service.face.HelpService;
import web.util.Paging;

public class HelpServiceImpl implements HelpService{

	private HelpDao helpDao = new HelpDaoImpl();
	private HelpCommentDao helpCommentDao = new HelpCommentDaoImpl();

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
	         totalCount = helpDao.selectCntHelpSearch(search); 
	      } else {
	         totalCount = helpDao.selectCntAll(); 
	      }

		//Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
		Paging paging = new Paging(totalCount, curPage);

		paging.setSearch(search);

		//Paging 객체 반환
		return paging;
	}

	@Override
	public List<UserID> list(Paging paging) {
		return helpDao.selectAll(paging);
	}

	@Override
	public HelpPost getPostno(HttpServletRequest req) {

		HelpPost postno = new HelpPost();

		String param = req.getParameter("postno");
		if(param!=null && !"".equals(param)) {

			//post 전달파라미터 추출
			postno.setPostNo( Integer.parseInt(param) );
		}

		return postno;
	}

	@Override
	public HelpPost view(HelpPost help) {
		HelpPost post = helpDao.selectBoardByBoardNo(help); 
		if(post!=null) {
			//게시글 조회수 증가
			helpDao.updateHit(help);
		}
		return post;
	}

	@Override
	public HelpFile viewFile(HelpPost result) {
		return helpDao.selectFile(result);
	}

	@Override
	public List<HelpComment> getCommentList(HelpPost board) {
		return helpCommentDao.selectComment(board);
	}

	@Override
	public void write(HttpServletRequest req) {

		//게시글 정보 저장할 객체
		HelpPost post = null;

		//첨부파일 정보 저장할 객체
		HelpFile helpFile = null;

		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);

		//multipart/form-data 인코딩으로 전송되지 않았을 경우
		if( !isMultipart ) {
			System.out.append("<h1>enctype이 multipart/form-data가 아닙니다</h1>");
			return; //fileupload() 메소드 실행 중지
		}

		//게시글 정보 저장할 객체 생성
		post = new HelpPost();

		//디스크기반 아이템 팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();

		//메모리 처리 사이즈 지정
		factory.setSizeThreshold(1 * 1024 * 1024); //1MB

		//임시 저장소 설정
		File repository = new File(req.getServletContext().getRealPath("tmp")); //임시 저장 폴더
		factory.setRepository(repository);

		//파일업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);

		//업로드 용량제한 설정 : 10MB
		upload.setFileSizeMax(10 * 1024 * 1024);

		//전달 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		//추출된 전달파라미터 처리 반복자
		Iterator<FileItem> iter = items.iterator();


		//모든 요청정보 처리하기
		while(iter.hasNext()) {

			FileItem item = iter.next();

			// 1) 빈 파일 처리
			if(item.getSize() <= 0)		continue;

			// 2) 일반적인 요청 데이터 처리
			if(item.isFormField()) {

				String key = item.getFieldName(); //키 추출
				//			            
				if("title".equals(key)) { //전달파라미터 name이 "title"
					try {
						post.setTitle(item.getString("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				} else if ("content".equals(key)) {
					try {
						post.setContent(item.getString("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

				} else if ("dealProgress".equals(key)) {
					try {
						post.setDealProgress(item.getString("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				} 

				//key값 비교 if end
			}
			// 파일 처리
			if( !item.isFormField()) {

				// --- UUID 생성 ---
				UUID uuid = UUID.randomUUID(); //랜덤 UID 생성

				String u = uuid.toString().split("-")[0]; //8자리 uid
				// -----------------

				// --- 로컬 저장소의 파일 객체 생성 ---
				File up = new File( 
						req.getServletContext().getRealPath("upload") // 업로드될 폴더 경로
						, item.getName()+"_"+u // 원본 파일명_uid
						); 
				// ------------------------------------

				// --- 첨부파일 정보 객체 ---
				//첨부파일 정보 저장할 객체 생성
				helpFile = new HelpFile();
				helpFile.setOrigin_name(item.getName()); //원본파일명
				helpFile.setStored_name(item.getName()+"_"+u); //저장파일명
				helpFile.setFilesize((int)item.getSize());

				// --------------------------


				// --- 처리 완료된 파일 업로드 하기 ---
				try {
					item.write(up); //실제 업로드
					item.delete(); //임시 파일 삭제
				} catch (Exception e) {
					e.printStackTrace();
				}

			} // 파일 처리 end
		} // while (iter.hasNext() ) end - FileItem 반복 처리


		//DB데이터 입력

		//게시글 작성자 id 입력
		post.setUserNo((int)req.getSession().getAttribute("userno"));

		//게시글 번호 생성 - Dao 이용
		int postno = helpDao.selectPostno();
		//		String range = board.getRange();

		//게시글 정보가 있을 경우
		if(post!=null) {

			//게시글 번호 입력
			post.setPostNo(postno);
			//게시글 삽입
			helpDao.insert(post);
		}

		//첨부파일 정보가 있을 경우
		if(helpFile != null) {
			//게시글 번호 입력
			helpFile.setPost_no(postno);

			//첨부파일 삽입
			helpDao.insertFile(helpFile);
		}

	}

	@Override
	public HelpComment getComment(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String postno = req.getParameter("postno");
		HttpSession session = req.getSession();
		session.getAttribute("userno");
		String content = (String) req.getParameter("content");

		HelpComment comment = new HelpComment();
		comment.setPost_no( Integer.parseInt(postno) );
		comment.setUser_no((int)session.getAttribute("userno"));
		comment.setContent(content);

		return comment;
	}

	@Override
	public void insertComment(HelpComment comment) {
		helpCommentDao.insertComment(comment);
	}

	@Override
	public boolean checkId(HttpServletRequest req) {
		//로그인한 세션 ID 얻기
		int loginuserno = (int)req.getSession().getAttribute("userno");

		//작성한 게시글 번호 얻기
		HelpPost post = getPostno(req);

		//게시글 얻어오기
		post = helpDao.selectBoardByBoardNo(post);

		//게시글의 작성자와 로그인 아이디 비교
		if( loginuserno != post.getUserNo())  {
			return false;
		}

		return true;
	}

	@Override
	public void update(HttpServletRequest req) {
		HelpPost post = null;
		HelpFile helpFile = null;
		
		
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		//multipart/form-data 인코딩으로 전송되지 않았을 경우
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			
			return; //fileupload() 메소드 실행 중지
		}
	
		
		//파일업로드를 사용하고 있을 경우
		post = new HelpPost();
	
		//디스크팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();
	
		//메모리처리 사이즈
		factory.setSizeThreshold(1 * 1024 * 1024); //1MB
	
		//임시 저장소
		File repository=new File(req.getServletContext().getRealPath("tmp"));
		factory.setRepository(repository);
		
		//업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//용량 제한 설정 : 10MB
		upload.setFileSizeMax(10 * 1024 * 1024);
		
		//form-data 추출 
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//파싱된 데이터 처리 반복자
		Iterator<FileItem> iter = items.iterator();
		
		//요청정보 처리
		while( iter.hasNext() ) {
			FileItem item = iter.next();
			
			// 빈 파일 처리
			if( item.getSize() <= 0 )	continue;
			
			// 빈 파일이 아닐 경우
			if( item.isFormField() ) {
				try {
					if( "postno".equals( item.getFieldName() ) ) {
						post.setPostNo( Integer.parseInt(item.getString()) );
					}
	
					else if( "title".equals( item.getFieldName() ) ) {
						post.setTitle( item.getString("utf-8") ); 
					}
					else if( "content".equals( item.getFieldName() ) ) {
						post.setContent( item.getString("utf-8") );
					}else if("dealProgress".equals(item.getFieldName())) {
						post.setDealProgress( item.getString("utf-8") );
					}
					
					post.setUserNo((int)req.getSession().getAttribute("userno"));
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
			} else {
				UUID uuid = UUID.randomUUID();
				String u = uuid.toString().split("-")[4];
	
				//로컬 저장소 파일
				String stored = item.getName() + "_" + u;
				File up = new File(
					req.getServletContext().getRealPath("upload")
					, stored);
				
				helpFile = new HelpFile();
				helpFile.setOrigin_name(item.getName());
				helpFile.setStored_name(stored);
				helpFile.setFilesize((int)item.getSize());
				
				try {
					// 실제 업로드
					item.write(up);
					
					// 임시 파일 삭제
					item.delete();
					
				} catch (Exception e) {
					e.printStackTrace();
				} // try end
			} //if end
		} //while end
		
	
		
		if(post != null) {
			helpDao.update(post);
		}
		
		if(helpFile != null) {
			helpFile.setPost_no(post.getPostNo());
			helpDao.insertFile(helpFile);
		}

	}

	@Override
	public void delete(HelpPost post) {
		helpDao.deleteFile(post);
		
		helpDao.delete(post); 
		
	}

	@Override
	public boolean deleteComment(HelpComment comment) {
		helpCommentDao.deleteComment(comment); 
		
		if( helpCommentDao.countComment(comment) > 0 ) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isRecommend(Help_calls calls) {
		int cnt = helpDao.selectCntRecommend(calls);
		
		if( cnt >0) {
			
			return true;
		}else {
			
			return false;
		}
	}

	@Override
	public Help_calls getRecommend(HttpServletRequest req) {

		int postno = 0;
		String param = req.getParameter("postno");
		if(param!=null && !"".equals(param)) {
			postno = Integer.parseInt(param);
		}
		
		int userid = (int) req.getSession().getAttribute("userno");
		
		Help_calls calls = new Help_calls();
		calls.setPost_no(postno);
		calls.setUser_id(userid);
		
		return calls;
	}

	@Override
	public boolean recommend(Help_calls calls) {
		System.out.println(calls);
		System.out.println(isRecommend(calls));
		if( isRecommend(calls)) {
			helpDao.deleteRecommend(calls);
			
			return false;
		}else {
			helpDao.insertRecommend(calls);
			
			return true;
		}
	}

	@Override
	public List<UserID> list() {
		return helpDao.selectChart();
	}

	


}
