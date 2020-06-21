package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.UserManageDao;
import web.dbutil.JDBCTemplate;
import web.dto.UserTB;
import web.util.Paging;

public class UserManageDaoImpl implements UserManageDao{

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	@Override
	public int selectCntAll() {
		conn = JDBCTemplate.getConnection(); //DB연결

		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM user_tb";

		//최종 결과값
		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			rs = ps.executeQuery(); //SQL수행 및 결과집합 반환

			//조회결과 처리
			while( rs.next() ) {
				cnt = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return cnt;
	}

	@Override
	public List<UserTB> selectUser(Paging paging) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, O.* FROM (";
		sql += "        SELECT * FROM user_tb";
		sql += "        WHERE 1=1";
		if( paging.getSearch() != null && !"".equals(paging.getSearch())) {
		sql += "		AND user_id LIKE '%'||?||'%'";
		}
		sql += "        ORDER BY user_no DESC";
		sql += "    ) O";
		sql += " ) One";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<UserTB> list = new ArrayList<UserTB>();

		try {
			ps = conn.prepareStatement(sql);

			int idx = 1;
			if( paging.getSearch() != null && !"".equals(paging.getSearch())) {
				ps.setString(idx++, paging.getSearch());	
			}
			ps.setInt(idx++, paging.getStartNo());	//페이징 게시글 시작 번호
			ps.setInt(idx++, paging.getEndNo());	//페이징 게시글 끝 번호


			rs = ps.executeQuery();

			while(rs.next()) {

				UserTB user = new UserTB();
				
				user.setUserNo(rs.getInt("user_no"));
				user.setUserId(rs.getString("user_id"));
				user.setUserPw(rs.getString("user_pw"));
				user.setUserName(rs.getString("user_name"));
				user.setBirth(rs.getDate("birth"));
				user.setGender(rs.getString("gender").charAt(0));
				user.setNick(rs.getString("nick"));
				user.setEmail(rs.getString("email"));
				user.setTel(rs.getString("tel"));
				user.setFirstAddr(rs.getString("first_addr"));
				user.setSecondAddr(rs.getString("second_addr"));
				user.setAnimal(rs.getString("animal"));
				user.setIsExpert(rs.getString("is_expert"));
				
				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);

		}


		return list;
	}

	@Override
	public void deleteUser(String param) {
		
		System.out.println("UserManageDaoImpl deleteUser() param: " + param);
		
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql +="DELETE user_tb";
		sql +=" WHERE user_no IN( " + param + " )";

		try {
			ps = conn.prepareStatement(sql);
			
			ps.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		
	}
	
}
