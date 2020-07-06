package web.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import web.dao.face.UserDao;
import web.dao.impl.UserDaoImpl;
import web.dto.UserFile;
import web.dto.UserTB;
import web.service.face.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userDao = new UserDaoImpl();
	
	@Override
	public UserTB getLoginUser(HttpServletRequest req) {
		
		try {
			// 한글 인코딩
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// 로그인 유저 정보를 담은 객체
		UserTB user = new UserTB();
		user.setUserId(req.getParameter("uid"));
		user.setUserPw(req.getParameter("upw"));
		
		return user;
	}

	@Override
	public UserTB getLoginUserByUserno(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 결과 반환 객체
		UserTB user = null;
		
		if( req.getSession().getAttribute("userno") != null 
				&& !"".equals(req.getSession().getAttribute("userno")) ) {
			int userno = (int) req.getSession().getAttribute("userno");
			user = new UserTB();
			user.setUserNo(userno);
		}
		
		return user;
	}
	
	@Override
	public boolean login(UserTB user) {
		
		int cnt = userDao.selectCntUserByUseridUserpw(user);
//		System.out.println("UserServiceImpl - login() cnt: " + cnt );
		
		// dao 에서 select 된 횟수에 따라 true, false 반환
		if( cnt > 0 ) {
			return true;
		} else {
			return false;
		}
		
	}
	
	@Override
	public UserTB info(UserTB user) {
		return userDao.selectUserByUserid(user);
	}

	@Override
	public UserTB getJoinUser(HttpServletRequest req) {
		
		// 한글 인코딩
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 회원 정보를 저장하는 객체
		UserTB user = new UserTB();
		
		user.setUserId(req.getParameter("user_id"));
		user.setUserPw(req.getParameter("user_pw"));
		user.setUserName(req.getParameter("user_name"));
		user.setNick(req.getParameter("nick"));
		user.setGender(req.getParameter("gender").charAt(0));
		
		// 생년월일
		String birth = req.getParameter("bir_yy")
						+ "-"
						+ req.getParameter("bir_mm")
						+ "-"
						+ req.getParameter("bir_dd");

//		System.out.println("birth: " + birth);
		Date bday = null;
		try {
			
			// date 로 바꿔주기
			bday = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setBirth(bday);
		
		// email 고치기
		String email = req.getParameter("email") + "@" + req.getParameter("e-domain");
//		System.out.println("UserJoinController email: " + email);
		user.setEmail(email);
		user.setTel(req.getParameter("tel"));
		user.setFirstAddr(req.getParameter("first_addr"));
		user.setSecondAddr(req.getParameter("second_addr"));
		user.setAnimal(req.getParameter("animal"));
		user.setIsExpert(req.getParameter("is_expert"));
		
		return user;
	}
	
	@Override
	public void join(UserTB user) {
		userDao.insert(user);
		
	}

	@Override
	public boolean chkId(UserTB user) {
		
		UserTB isId = userDao.selectUserByUserid(user);
		
		if( isId == null ) {
			return false;
		} else {
			return true;
		}
		
	}
	
	@Override
	public boolean chkNick(UserTB user) {
		
		UserTB isNick = userDao.selectUserByUserNick(user);
		
		if(isNick == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean chkEmail(String userEmail) {
		
		int isEmail = userDao.selectUserByUserEmail(userEmail);
		
		if(isEmail > 0 ) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public UserTB getFindUser(HttpServletRequest req) {
		
		// 한글 인코딩
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 아이디 찾기에서 입력한 정보를 담은 객체
		UserTB user = new UserTB();
		user.setUserName(req.getParameter("name"));
		user.setEmail(req.getParameter("email"));
		
		String userid = null;
		if( req.getParameter("userid") != null ) { // 파라미터 값 아이디가 비어있지 않을 때(존재할 때)
			userid = req.getParameter("userid");
//			System.out.println("UserServiceImpl getFindUser() - userid: " + userid);
			user.setUserId(userid);
		}
		
		return user;
	}
	
	@Override
	public UserTB findId(UserTB user) {
		return userDao.selectUserByNameEmail(user);
	}
	
	@Override
	public UserTB findPw(UserTB user) {
		return userDao.selectUserByUserIdEmailName(user);
	}
	
	@Override
	public UserTB getFindUserByUserno(HttpServletRequest req) {

		// 한글 인코딩
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 아이디 찾기에서 입력한 정보를 담은 객체
		UserTB user = new UserTB();
		user.setUserPw(req.getParameter("userpw"));
		

		String userno = null;
		if( req.getParameter("userno") != null ) { // 파라미터 값 userno이 비어있지 않을 때(존재할 때)
			userno = req.getParameter("userno");
			System.out.println("UserServiceImpl getFindUser() - userno: " + userno);
			user.setUserNo(Integer.parseInt(userno));
		}

		return user;
	}

	@Override
	public boolean updatePw(UserTB user) {
		
		int isUpdate = userDao.updateUserPw(user);
		
		if( isUpdate > 0 ) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public UserTB getFindUserByUserno(UserTB user) {
		return userDao.selectUserByUserno(user);
	}
	
	@Override
	public void updateUserInfo(HttpServletRequest req) {
//		System.out.println("userupdate userno: " + req.getSession().getAttribute("userno"));
		
		UserTB user = null;
		UserFile userFile = null;
		String birth = "";
		String email = "";
		
		// 파일 업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		// multipart/form-data 인코딩으로 전송되지 않았을 경우
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			return; // updateUserInfo() 메소드 실행 중지
		}
		
		// 파일 업로드를 사용하고 있을 경우
		int userno = (int) req.getSession().getAttribute("userno");
		user = new UserTB();
		user.setUserNo(userno);
		
		// 디스크 팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 메모리처리 사이즈
		factory.setSizeThreshold(1 * 1024 * 1024); // 1MB
		
//		System.out.println(req.getServletContext().getRealPath("tmp"));
		// 임시 저장소
		File repository = new File(req.getServletContext().getRealPath("tmp"));
		factory.setRepository(repository);
		
		// 업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// 용량 제한 설정 : 10MB
		upload.setFileSizeMax(10 * 1024 * 1024);
		
		// form-data 추출
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		// 파싱된 데이터 처리 반복자
		Iterator<FileItem> iter = items.iterator();
		
		// 요청 정보 처리
		while( iter.hasNext() ) {
			FileItem item = iter.next();
			
			// 빈 파일 처리
			if(item.getSize() <= 0) continue;
			
			// 빈 파일이 아닐 경우
			if( item.isFormField() ) { // 파일이 아닌 경우
				
				try {
					// 이름
					if( "user_name".equals( item.getFieldName() ) ) {
							user.setUserName(item.getString("utf-8"));
					}
					
					// 닉네임
					if( "nick".equals( item.getFieldName() ) ) {
						user.setNick( item.getString("utf-8"));
					}
					
					// 성별
					if( "gender".equals( item.getFieldName() ) ) {
						user.setGender( item.getString("utf-8").toLowerCase().charAt(0));
					}
					
					// 생년월일
					if( "bir_yy".equals(item.getFieldName() ) ) {
						birth += item.getString("utf-8") + "-";
					}
					
					if( "bir_mm".equals(item.getFieldName() ) ) {
						birth += item.getString("utf-8") + "-";
					}
					
					if( "bir_dd".equals(item.getFieldName() ) ) {
						birth += item.getString("utf-8");
						
						Date bday = null;
						try {
							
							// date 로 바꿔주기
							bday = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						user.setBirth(bday);
//						System.out.println("userudpate user.getBirth(): " + user.getBirth());
					} // if( "bir_dd".equals(item.getFieldName() ) ) { - end
					
					// 이메일
					if( "email".equals(item.getFieldName() ) ) {
						email += item.getString("utf-8") + "@";
					}
					
					if( "e-domain".equals(item.getFieldName() ) ) {
						email += item.getString("utf-8");
//						System.out.println("userupdate email: " + email);
						
						user.setEmail(email);
					}
					
					// 첫 번째 주소
					if( "first_addr".equals(item.getFieldName() ) ) {
						user.setFirstAddr(item.getString("utf-8"));
					}
					
					// 두번째 주소 - 상세주소
					if( "second_addr".equals(item.getFieldName() ) ) {
						user.setSecondAddr(item.getString("utf-8"));
					}
					
					// --- 비밀번호 입력 폼에 입력했을 때 ---
					// 비밀번호
					if( "user_pw".equals(item.getFieldName() ) ) {
						user.setUserPw(item.getString("utf-8"));
					}
					
					// --- 휴대폰 번호 입력했을 때 ---
					// 휴대폰
					if( "tel".equals(item.getFieldName() ) ) {
						user.setTel(item.getString("utf-8"));
					}
					
					// --- 반려동물 선택했을 때 ---
					// 반려동물
					if( "animal".equals(item.getFieldName() ) ) {
						user.setAnimal(item.getString("utf-8"));
					}
					
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // catch - end
				
			} // if( item.isFormField() ) { - end
			
			else { // 파일인 경우
				UUID uuid = UUID.randomUUID();
				String u = uuid.toString().split("-")[4];
				
				int beginIdx = item.getName().lastIndexOf(".");
				String origin = item.getName().substring(0, beginIdx);
				String ext = item.getName().substring(beginIdx + 1);
//				System.out.println(origin + "_" + u +"." + ext);
				
				// 로컬 저장소 파일
				String stored = origin + "_" + u +"." + ext;
				File up = new File(
						req.getServletContext().getRealPath("upload")
						, stored
						);
				
				userFile = new UserFile();
				userFile.setUserNo(userno);
				userFile.setOriginName(item.getName());
				userFile.setStoreName(stored);
				userFile.setFilesize((int)item.getSize());
				
				try {
					// 실제 업로드
					item.write(up);
					
					// 임시 파일 삭제
					item.delete();
					
				} catch (Exception e) {
					e.printStackTrace();
				}// try - end
				
			} //else { - end
			
		} // while( iter.hasNext() ) { - end!
		
//		System.out.println(user);
//		System.out.println(userFile);
		
		if(user != null) { // user가 null이 아닐 때!
			if(user.getUserPw() != null) {
//				System.out.println("비밀번호를 입력했을 때, 즉 비밀번호 정보가 있을 때");
				// 비밀번호 업데이트
				userDao.updateUserPw(user);
			}
			if(user.getTel() != null) {
//				System.out.println("휴대폰 번호를 입력했을 때, 즉 휴대폰 번호 정보가 있을 때");
				System.out.println(user.getTel());
				// 휴대폰 번호 업데이트
				userDao.updateUserTel(user);
			}
			if(user.getAnimal() != null) {
				System.out.println("반려동물을 선택했을 때, 즉 반려동물 선택을 다시 했을 때");
				System.out.println(user.getAnimal());
				
				// 반려동물 업데이트
				userDao.updateAnimal(user);
			}
			userDao.update(user);
		}
		
		if(userFile != null) {
			userFile.setFileno(user.getUserNo());
			userDao.insertFile(userFile);
		}
		
	}
	
	@Override
	public UserFile viewProfileFile(UserTB user) {
		return userDao.selectUserFile(user);
	}
	
	@Override
	public UserTB getFindDeleteUser(HttpServletRequest req) {
		
		// 한글 인코딩
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 결과 반환 객체
		UserTB deleteUser = new UserTB();
		String param = String.valueOf(req.getSession().getAttribute("userno"));
		if( param != null && !"".equals(param) ) {
			deleteUser.setUserNo(Integer.parseInt(param));
		}
		deleteUser.setUserPw(req.getParameter("upw"));
		
		return deleteUser;
	}
	
	@Override
	public boolean chkPw(UserTB deleteUser) {
		
		int cnt = userDao.selectCntChkPw(deleteUser);
		if(cnt > 0) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void deleteUser(UserTB deleteUser) {
		userDao.deleteUser(deleteUser);
	}
}
