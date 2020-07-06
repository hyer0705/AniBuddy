package web.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.sun.mail.imap.protocol.MailboxInfo;

import web.dao.face.AdminDao;
import web.dao.face.UserManageDao;
import web.dao.impl.AdminDaoImpl;
import web.dao.impl.UserManageDaoImpl;
import web.dto.Admin;
import web.dto.Email;
import web.dto.UserTB;
import web.service.face.AdminService;

public class AdminServiceImpl implements AdminService{

	private AdminDao adminDao = new AdminDaoImpl();
	private UserManageDao userDao = new UserManageDaoImpl();
	
	@Override
	public Admin getLoginAdmin(HttpServletRequest req) {
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Admin param = new Admin();
		param.setAdminId(req.getParameter("adminid"));
		param.setAdminPw(req.getParameter("adminpw"));
		
		return param;
	}
	
	@Override
	public boolean login(Admin admin) {
		
		int cnt = adminDao.selectAdminByAdminidAdminpw(admin);
		
		// dao 에서 select 된 횟수에 따라 true, false 반환
		if( cnt > 0) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public Admin info(Admin admin) {
		return adminDao.selectAdminByAdminid(admin);
	}
	
	@Override
	public Email mail(HttpServletRequest req) {
		
		Email email = new Email();
		
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String host = "smtp.naver.com";
		String user = "anibuddy@naver.com"; // 자신의 네이버 계정
		String password = "Ani1234!!";// 자신의 네이버 패스워드
		

		// SMTP 서버 정보를 설정한다.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		List<UserTB> users = null;
		
		users = userDao.selectUser();
		
		Iterator<UserTB> iter = users.iterator();
		
		while(iter.hasNext()) {
			UserTB usermail = iter.next();
			
			String title = email.getTitle();
			String content = email.getContent();
			
			try {
				MimeMessage msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(user, "Anibuddy"));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(usermail.getEmail()));
				
				// 메일 제목
				msg.setSubject(req.getParameter("title"));
				// 메일 내용
				msg.setText(req.getParameter("content"));
				
				Transport.send(msg);
				System.out.println("이메일 전송");
				
				email.setTitle(title);
				email.setContent(content);
				
				
			} catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
			
		}
		
		String param = null;
		
		param = req.getParameter("title");
		email.setTitle(param);
		
		param = req.getParameter("content");
		email.setContent(param);
		
		System.out.println("email" + email);
		
		return email;

	}
	
	@Override
	public Email onemail(HttpServletRequest req) {
		Email email = new Email();
		
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String host = "smtp.naver.com";
		String user = "anibuddy@naver.com"; // 자신의 네이버 계정
		String password = "Ani1234!!";// 자신의 네이버 패스워드
		String mail = req.getParameter("email");
		

		// SMTP 서버 정보를 설정한다.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(user, "Anibuddy"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
			
			// 메일 제목
			msg.setSubject(req.getParameter("title"));
			// 메일 내용
			msg.setText(req.getParameter("content"));
			
			Transport.send(msg);
			System.out.println("이메일 전송");
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		
		String param = null;
		
		param = req.getParameter("title");
		email.setTitle(param);
		
		param = req.getParameter("content");
		email.setContent(param);
		
		param = req.getParameter("email");
		email.setUseremail(param);
		
		return email;
	}
	
	@Override
	public void savemail(Email email) {
		adminDao.mail(email);
	}
	
	
	@Override
	public void saveonemail(Email email) {
		adminDao.onemail(email);
	}
	
	@Override
	public Email getMailno(HttpServletRequest req) {
		Email mailno = new Email();
		
		String param = req.getParameter("emailno");
		if(param!=null && !"".equals(param)) {
			mailno.setEmailno(Integer.parseInt(param));
		}
		
		return mailno;
	}
	
	@Override
	public Email mailview(Email emailno) {
		Email email = adminDao.selectEmailByEmailno(emailno);
		return email;
	}
	
	
}
