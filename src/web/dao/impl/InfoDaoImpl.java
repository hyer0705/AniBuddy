package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.InfoDao;
import web.dbutil.JDBCTemplate;
import web.dto.BookMarkHangOut;
import web.dto.BookMarkInfo;
import web.dto.Info;
import web.dto.UserTB;

public class InfoDaoImpl implements InfoDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<Info> selectAll() {

		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "SELECT * FROM info ORDER BY i_no DESC";
		
		List<Info> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Info info = new Info();
				
				info.setiNo(rs.getInt("i_no"));
				info.setiName(rs.getString("i_name"));
				info.setiCity1(rs.getString("i_city1"));
				info.setiCity2(rs.getString("i_city2"));
				info.setiAddress(rs.getString("i_address"));
				info.setiTime(rs.getString("i_time"));
				info.setiTel(rs.getString("i_tel"));
				info.setiDomain(rs.getString("i_domain"));
				info.setiFilter(rs.getString("i_filter"));
				info.setiContent(rs.getString("i_content"));
				info.setUserNo(rs.getInt("user_no"));
				
				list.add(info);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return list;
		
	}

	@Override
	public List<Info> selectByCity1(Info info) {

		conn = JDBCTemplate.getConnection();
		
		String sql="";
		sql += "SELECT * FROM info";
		sql += " WHERE i_city1=? ORDER BY i_no DESC";
		
		List<Info> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getiCity1());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Info place = new Info();
				
				place.setiNo(rs.getInt("i_no"));
				place.setiName(rs.getString("i_name"));
				place.setiCity1(rs.getString("i_city1"));
				place.setiCity2(rs.getString("i_city2"));
				place.setiAddress(rs.getString("i_address"));
				place.setiTime(rs.getString("i_time"));
				place.setiTel(rs.getString("i_tel"));
				place.setiDomain(rs.getString("i_domain"));
				place.setiContent(rs.getString("i_content"));
				place.setiFilter(rs.getString("i_filter"));
				place.setUserNo(rs.getInt("user_no"));
				
				list.add(place);			
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}

	@Override
	public List<Info> selectByCity(Info info) {

		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "SELECT * FROM info";
		sql += " WHERE i_city1=? AND i_city2=? ORDER BY i_no DESC";
		
		List<Info> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getiCity1());
			ps.setString(2, info.getiCity2());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Info place = new Info();
				
				place.setiNo(rs.getInt("i_no"));
				place.setiName(rs.getString("i_name"));
				place.setiCity1(rs.getString("i_city1"));
				place.setiCity2(rs.getString("i_city2"));
				place.setiAddress(rs.getString("i_address"));
				place.setiTime(rs.getString("i_time"));
				place.setiTel(rs.getString("i_tel"));
				place.setiDomain(rs.getString("i_domain"));
				place.setiContent(rs.getString("i_content"));
				place.setiFilter(rs.getString("i_filter"));
				place.setUserNo(rs.getInt("user_no"));
				
				list.add(place);			
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}

	@Override
	public List<Info> selectByFilter(String[] filters) {

		conn = JDBCTemplate.getConnection();
		
		String totalFilter = "";
		
		for(int i=0; i<filters.length; i++) {
			totalFilter += "'";
			totalFilter += filters[i];
			totalFilter += "'";
			if(i!=filters.length-1) {
				totalFilter += ", ";
			}
		}
		
		String sql = "";
		sql += "SELECT * FROM info";
		sql += " WHERE i_filter IN("+totalFilter+") ORDER BY i_no DESC";
		
		List<Info> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Info info = new Info();
				
				info.setiNo(rs.getInt("i_no"));
				info.setiName(rs.getString("i_name"));
				info.setiCity1(rs.getString("i_city1"));
				info.setiCity2(rs.getString("i_city2"));
				info.setiAddress(rs.getString("i_address"));
				info.setiTime(rs.getString("i_time"));
				info.setiTel(rs.getString("i_tel"));
				info.setiDomain(rs.getString("i_domain"));
				info.setiContent(rs.getString("i_content"));
				info.setiFilter(rs.getString("i_filter"));
				info.setUserNo(rs.getInt("user_no"));
				
				list.add(info);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}

	@Override
	public List<Info> selectByCity1(Info info, String[] filters) {

		conn = JDBCTemplate.getConnection();
		
		String totalFilter = "";
		
		for(int i=0; i<filters.length; i++) {
			totalFilter += "'";
			totalFilter += filters[i];
			totalFilter += "'";
			if(i!=filters.length-1) {
				totalFilter += ", ";
			}
		}
		
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT * FROM info";
		sql += "	WHERE i_filter IN("+totalFilter+") )";
		sql += " WHERE i_city1=? ORDER BY i_no DESC";
		
		List<Info> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getiCity1());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Info place = new Info();
				
				place.setiNo(rs.getInt("i_no"));
				place.setiName(rs.getString("i_name"));
				place.setiCity1(rs.getString("i_city1"));
				place.setiCity2(rs.getString("i_city2"));
				place.setiAddress(rs.getString("i_address"));
				place.setiTime(rs.getString("i_time"));
				place.setiTel(rs.getString("i_tel"));
				place.setiDomain(rs.getString("i_domain"));
				place.setiContent(rs.getString("i_content"));
				place.setiFilter(rs.getString("i_filter"));
				place.setUserNo(rs.getInt("user_no"));
				
				list.add(place);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;

	}

	@Override
	public List<Info> selectByCityFilter(Info info, String[] filters) {
		
		conn = JDBCTemplate.getConnection();
		
		String totalFilter = "";
		
		for(int i=0; i<filters.length; i++) {
			totalFilter += "'";
			totalFilter += filters[i];
			totalFilter += "'";
			if(i!=filters.length-1) {
				totalFilter += ", ";
			}
		}
		
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT * FROM info";
		sql += "	WHERE i_filter IN("+totalFilter+") )";
		sql += " WHERE i_city1=? AND i_city2=? ORDER BY i_no DESC";
		
		List<Info> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getiCity1());
			ps.setString(2, info.getiCity2());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Info place = new Info();
				
				place.setiNo(rs.getInt("i_no"));
				place.setiName(rs.getString("i_name"));
				place.setiCity1(rs.getString("i_city1"));
				place.setiCity2(rs.getString("i_city2"));
				place.setiAddress(rs.getString("i_address"));
				place.setiTime(rs.getString("i_time"));
				place.setiTel(rs.getString("i_tel"));
				place.setiDomain(rs.getString("i_domain"));
				place.setiContent(rs.getString("i_content"));
				place.setiFilter(rs.getString("i_filter"));
				place.setUserNo(rs.getInt("user_no"));
				
				list.add(place);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}

	@Override
	public void insert(Info info) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "INSERT INTO info ";
		sql += "(i_no, i_name, i_city1, i_city2, i_address, i_time, i_tel, i_domain, i_filter, i_content, user_no)";
		sql += " VALUES(Info_seq.nextval, ?,?,?,?,?,?,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getiName());
			ps.setString(2, info.getiCity1());
			ps.setString(3, info.getiCity2());
			ps.setString(4, info.getiCity1() + " " + info.getiCity2() + " " + info.getAddress());
			ps.setString(5, info.getStartTime() + " - " + info.getEndTime());
			ps.setString(6, info.getTel1() + "-" + info.getTel2() + "-" + info.getTel3());
			ps.setString(7, info.getiDomain());
			ps.setString(8, info.getiFilter());
			ps.setString(9, info.getiContent());
			ps.setInt(10, info.getUserNo());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}

	@Override
	public List<Info> selectByName(Info info) {

		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "SELECT * FROM info";
		sql += " WHERE i_name LIKE '%'||?||'%' ORDER BY i_no DESC";
		
		List<Info> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getiName());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Info place = new Info();
				
				place.setiNo(rs.getInt("i_no"));
				place.setiName(rs.getString("i_name"));
				place.setiCity1(rs.getString("i_city1"));
				place.setiCity2(rs.getString("i_city2"));
				place.setiAddress(rs.getString("i_address"));
				place.setiTime(rs.getString("i_time"));
				place.setiTel(rs.getString("i_tel"));
				place.setiDomain(rs.getString("i_domain"));
				place.setiFilter(rs.getString("i_filter"));
				place.setiContent(rs.getString("i_content"));
				place.setUserNo(rs.getInt("user_no"));
				
				list.add(place);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}

	@Override
	public Info selectByNo(Info info) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "SELECT * FROM info";
		sql += " WHERE i_no=?";
		
		Info place = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, info.getiNo());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				place = new Info();
				
				place.setiNo(rs.getInt("i_no"));
				place.setiName(rs.getString("i_name"));
				place.setiCity1(rs.getString("i_city1"));
				place.setiCity2(rs.getString("i_city2"));
				place.setiAddress(rs.getString("i_address"));
				place.setiTime(rs.getString("i_time"));
				place.setiTel(rs.getString("i_tel"));
				place.setiDomain(rs.getString("i_domain"));
				place.setiFilter(rs.getString("i_filter"));
				place.setiContent(rs.getString("i_content"));
				place.setUserNo(rs.getInt("user_no"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}				
				
		return place;
	}

	@Override
	public void delete(Info info) {

		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "DELETE FROM info";
		sql += " WHERE i_no=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, info.getiNo());
			
			ps.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}

	@Override
	public List<Info> selectByNameCity1(Info info) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "SELECT * FROM info";
		sql += " WHERE i_city1=? AND i_name LIKE '%'||?||'%'";
		sql += " ORDER BY i_no DESC";
		
		List<Info> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getiCity1());
			ps.setString(2, info.getiName());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Info place = new Info();
				
				place.setiNo(rs.getInt("i_no"));
				place.setiName(rs.getString("i_name"));
				place.setiCity1(rs.getString("i_city1"));
				place.setiCity2(rs.getString("i_city2"));
				place.setiAddress(rs.getString("i_address"));
				place.setiTime(rs.getString("i_time"));
				place.setiTel(rs.getString("i_tel"));
				place.setiDomain(rs.getString("i_domain"));
				place.setiFilter(rs.getString("i_filter"));
				place.setiContent(rs.getString("i_content"));
				place.setUserNo(rs.getInt("user_no"));
				
				list.add(place);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}

	@Override
	public void insertBookMark(BookMarkInfo bm) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "INSERT INTO bookmarkinfo(bm_no, i_no, user_no)";
		sql += " VALUES (bookmarkinfo_seq.nextval, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bm.getiNo());
			ps.setInt(2, bm.getUserNo());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}

	@Override
	public void deleteBookMark(BookMarkInfo bm) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "DELETE FROM bookmarkinfo";
		sql += " WHERE user_no=? and i_no=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bm.getUserNo());
			ps.setInt(2, bm.getiNo());
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
	}

	
	@Override
	public List<BookMarkInfo> selectBookMarkList() {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "SELECT * FROM bookmarkinfo";
		sql += " ORDER BY bm_no DESC";
		
		List<BookMarkInfo> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BookMarkInfo bm = new BookMarkInfo();
				
				bm.setBmNo(rs.getInt("bm_no"));
				bm.setiNo(rs.getInt("i_no"));
				bm.setUserNo(rs.getInt("user_no"));
				
				list.add(bm);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
	
		return list;
	}
	
	
	@Override
	public void delete(String names) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql +="DELETE bookmarkinfo";
		sql +=" WHERE bm_no IN(" +names+" )";

		try {
			ps = conn.prepareStatement(sql);

			ps.executeQuery();


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		
	}
	
	
	@Override
	public void deleteBookMarkNo(BookMarkInfo bm) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "DELETE FROM bookmarkinfo";
		sql += " WHERE bm_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bm.getBmNo());
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		 
	}

	@Override
	public int selectCntAllByUserno(UserTB currUser) {
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "SELECT count(*) FROM bookmarkinfo";
		sql += " WHERE user_no = ?";
		sql += " ORDER BY bm_no DESC";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, currUser.getUserNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
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
	
	
}
