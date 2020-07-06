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

import web.dao.face.FreeBoardDao;
import web.dao.face.FreeCommentDao;
import web.dao.impl.FreeBoardDaoImpl;
import web.dao.impl.FreeCommentDaoImpl;
import web.dto.FreeBoard;
import web.dto.FreeBoardFile;
import web.dto.FreeComment;
import web.dto.UserID;
import web.service.face.FreeBoardService;
import web.util.Paging;

public class FreeBoardServiceImpl implements FreeBoardService{

	private FreeBoardDao freeboardDao = new FreeBoardDaoImpl();
	private FreeCommentDao freecommentDao = new FreeCommentDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {

		//전달파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage=0;
		if( param!=null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}

		//검색어
		String search = (String)req.getParameter("search");

		//Board 테이블의 총 게시글 수를 조회한다
		int totalCount = 0;
	      if( search != null && !"".equals(search) ) {
	         totalCount = freeboardDao.selectCntFreeSearch(search); 
	      } else {
	         totalCount = freeboardDao.selectCntAll(); 
	      }

		//Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
		Paging paging = new Paging(totalCount, curPage);

		//검색어
		paging.setSearch(search);

		//Paging 객체 반환
		return paging;

	}

	@Override
	public List<UserID> list(Paging paging) {
		return freeboardDao.selectAll(paging);
	}

	@Override
	public FreeBoard getPostno(HttpServletRequest req) {

		FreeBoard postno = new FreeBoard();
		
		String param = req.getParameter("postno");
		if(param!=null && !"".equals(param)) {
			
			//post 전달파라미터 추출
			postno.setPostno( Integer.parseInt(param) );
		}
				
		return postno;
		
	}

	@Override
	public FreeBoard view(FreeBoard postno) {

		FreeBoard board = freeboardDao.selectBoardByBoardNo(postno); 
		if(board!=null) {
			//게시글 조회수 증가
			freeboardDao.updateHit(board);
		}
		return board;
		
	}

	@Override
	public FreeBoardFile viewFile(FreeBoard result) {
		return freeboardDao.selectFile(result);
	}

	@Override
	public List getCommentList(FreeBoard board) {
		
		return freecommentDao.selectComment(board);
	}

	@Override
	public void write(HttpServletRequest req) {
		
		//게시글 정보 저장할 객체
		FreeBoard board = null;
				
		//첨부파일 정보 저장할 객체
		FreeBoardFile boardFile = null;
				
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
						
		//multipart/form-data 인코딩으로 전송되지 않았을 경우
		if( !isMultipart ) {
			System.out.append("<h1>enctype이 multipart/form-data가 아닙니다</h1>");
			return; //fileupload() 메소드 실행 중지
		}
				
		//게시글 정보 저장할 객체 생성
		board = new FreeBoard();
						
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
		            	   board.setTitle(item.getString("UTF-8"));
		               } catch (UnsupportedEncodingException e) {
		            	   e.printStackTrace();
		               }
		           	} else if ("content".equals(key)) {
		           		try {
		           			board.setContent(item.getString("UTF-8"));
		           		} catch (UnsupportedEncodingException e) {
		           			e.printStackTrace();
		           		}
		           	
		           } else if ("range".equals(key)) {
		           		try {
		           			board.setRange(item.getString("UTF-8"));
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
				boardFile = new FreeBoardFile();
				boardFile.setOriginname(item.getName()); //원본파일명
				boardFile.setStoredname(item.getName()+"_"+u); //저장파일명
				boardFile.setFilesize((int)item.getSize());
				
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
		board.setUserno((int)req.getSession().getAttribute("userno"));
				
		//게시글 번호 생성 - Dao 이용
		int postno = freeboardDao.selectPostno();
//		String range = board.getRange();
				
		//게시글 정보가 있을 경우
		if(board!=null) {
					
			//게시글 번호 입력
			board.setPostno(postno);
			//게시글 삽입
			freeboardDao.insert(board);
		}
				
		//첨부파일 정보가 있을 경우
		if(boardFile != null) {
			//게시글 번호 입력
			boardFile.setPostno(postno);
					
			//첨부파일 삽입
			freeboardDao.insertFile(boardFile);
		}
		
	}
	
	@Override
	public boolean checkId(HttpServletRequest req) {
		
		//로그인한 세션 ID 얻기
		int loginuserno = (int)req.getSession().getAttribute("userno");

		//작성한 게시글 번호 얻기
		FreeBoard board = getPostno(req);

		//게시글 얻어오기
		board = freeboardDao.selectBoardByBoardNo(board);

		//게시글의 작성자와 로그인 아이디 비교
		if( loginuserno != board.getUserno())  {
			return false;
		}

		return true;
	}
	
	@Override
	public void update(HttpServletRequest req) {
		FreeBoard board = null;
		FreeBoardFile boardFile = null;
		
		
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		//multipart/form-data 인코딩으로 전송되지 않았을 경우
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			
			return; //fileupload() 메소드 실행 중지
		}
	
		
		//파일업로드를 사용하고 있을 경우
		board = new FreeBoard();
	
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
						board.setPostno( Integer.parseInt(item.getString()) );
					}
	
					else if( "title".equals( item.getFieldName() ) ) {
						board.setTitle( item.getString("utf-8") ); 
					}
					else if( "content".equals( item.getFieldName() ) ) {
						board.setContent( item.getString("utf-8") );
					}
					
					board.setUserno((int)req.getSession().getAttribute("userno"));
					
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
				
				boardFile = new FreeBoardFile();
				boardFile.setOriginname(item.getName());
				boardFile.setStoredname(stored);
				boardFile.setFilesize((int)item.getSize());
				
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
		
	
		
		if(board != null) {
			freeboardDao.update(board);
		}
		
		if(boardFile != null) {
			boardFile.setPostno(board.getPostno());
			freeboardDao.insertFile(boardFile);
		}
		
	}
	
	@Override
	public void delete(FreeBoard board) {
		
		freeboardDao.deleteFile(board);
		
		freeboardDao.delete(board); 
		
//		System.out.println(board.getPostno());
		
	}

	@Override
	public FreeComment getComment(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		String postno = req.getParameter("postno");
		HttpSession session = req.getSession();
		
		Object obj = session.getAttribute("userno");
		
		if(obj == null) {
			obj = session.getAttribute("adminno");
		}
		
		int tmpno = (int)obj;
		
		String content = (String) req.getParameter("content");
		
		FreeComment comment = new FreeComment();
		comment.setPostno( Integer.parseInt(postno) );
		comment.setUserno(tmpno);
		comment.setContent(content);
		
		return comment;
	}

	@Override
	public void insertComment(FreeComment comment) {
		freecommentDao.insertComment(comment);
	}
	
	@Override
	public boolean deleteComment(FreeComment comment) {
		freecommentDao.deleteComment(comment); 
		
		if( freecommentDao.countComment(comment) > 0 ) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<UserID> list() {
		      return freeboardDao.selectChart();
	}
	
}
