package web.service.impl;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.AdminDao;
import web.dao.impl.AdminDaoImpl;
import web.dto.Admin;
import web.service.face.AdminService;

public class AdminServiceImpl implements AdminService{

	private AdminDao adminDao = new AdminDaoImpl();
	
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
	
}
