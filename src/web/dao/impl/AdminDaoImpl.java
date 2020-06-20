package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.dao.face.AdminDao;
import web.dbutil.JDBCTemplate;
import web.dto.Admin;

public class AdminDaoImpl implements AdminDao{

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int selectAdminByAdminidAdminpw(Admin admin) {
		
		// DB 연결 객체
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM admin";
		sql += " WHERE admin_id = ?";
		sql += "     AND admin_pw = ?";
		
		// 결과 저장 변수
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, admin.getAdminId());
			ps.setString(2, admin.getAdminPw());
			
			rs = ps.executeQuery();
			
			// 조회 결과 처리
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return cnt;
	}
	
	@Override
	public Admin selectAdminByAdminid(Admin admin) {
		
		// DB 연결 객체
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM admin";
		sql += " WHERE admin_id = ?";
		
		// 조회 결과를 저장학 객체
		Admin result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, admin.getAdminId());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				result = new Admin();
				result.setAdminNo(rs.getInt("admin_no"));
				result.setAdminId(rs.getString("admin_id"));
				result.setAdminPw(rs.getString("admin_pw"));
				result.setEmail(rs.getString("email"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		return result;
	}
}
