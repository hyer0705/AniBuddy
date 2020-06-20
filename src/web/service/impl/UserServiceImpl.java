package web.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.UserDao;
import web.dao.impl.UserDaoImpl;
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
		
		user.setEmail(req.getParameter("email"));
		user.setTel(req.getParameter("tel"));
		user.setFirstAddr(req.getParameter("first_addr"));
		user.setSecondAddr(req.getParameter("second_addr"));
		user.setAnimal(req.getParameter("animal"));
		user.setIsExpert(req.getParameter("is_expert").charAt(0));
		
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
			System.out.println("UserServiceImpl getFindUser() - userid: " + userid);
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
	
}
