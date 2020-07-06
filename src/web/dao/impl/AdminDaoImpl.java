package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.dao.face.AdminDao;
import web.dbutil.JDBCTemplate;
import web.dto.Admin;
import web.dto.Email;

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
	
	@Override
	public void mail(Email email) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql="";
		sql+="INSERT INTO email(email_no, title, content, isall)";
		sql+=" VALUES (email_no_seq.nextval, ?,?,1)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, email.getTitle());
			ps.setString(2, email.getContent());
			
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	}
	
	@Override
	public void onemail(Email email) {

		conn = JDBCTemplate.getConnection();
		
		String sql="";
		sql+="INSERT INTO email(email_no, title, content, isall, user_email)";
		sql+=" VALUES (email_no_seq.nextval, ?,?,0, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, email.getTitle());
			ps.setString(2, email.getContent());
			ps.setString(3, email.getUseremail());
			
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	}
	
	@Override
	public Email selectEmailByEmailno(Email email) {

		// DB 연결 객체
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM email";
		sql += " WHERE email_no=?";
		
		Email result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, email.getEmailno());
			
			rs = ps.executeQuery();
			
			// 조회 결과 처리
			while(rs.next()) {
				result = new Email();
				result.setEmailno(rs.getInt("email_no"));
				result.setTitle(rs.getString("title"));
				result.setContent(rs.getString("content"));
				result.setIsall(rs.getString("isall"));
				result.setUseremail(rs.getString("user_email"));
				result.setWritedate(rs.getDate("write_date"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return result;
	}
}
