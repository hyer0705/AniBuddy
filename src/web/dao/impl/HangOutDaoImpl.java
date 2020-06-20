package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.HangOutDao;
import web.dbutil.JDBCTemplate;
import web.dto.HangOut;
import web.dto.HangOutFile;

public class HangOutDaoImpl implements HangOutDao{

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<HangOut> selectAll() {

		// DB 연결
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM HangOut";
		sql += " ORDER BY h_no DESC";

		// 조회된 결과를 저장할 List
		List<HangOut> list = new ArrayList<>();
		
		// 조회된 결과를 저장할 HangOut 객체
		HangOut hangout = null;
		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			// 조회된 결과 수행
			while(rs.next()) {
				hangout = new HangOut();

				hangout.sethNo(rs.getInt("h_no"));
				hangout.sethName(rs.getString("h_name"));
				hangout.sethCity1(rs.getString("h_city1"));
				hangout.sethCity2(rs.getString("h_city2"));
				hangout.sethAddress(rs.getString("h_address"));
				hangout.sethTime(rs.getString("h_time"));
				hangout.sethTel(rs.getString("h_tel"));
				hangout.sethEmail(rs.getString("h_email"));
				hangout.sethDomain(rs.getString("h_domain"));
				hangout.sethContent(rs.getString("h_content"));
				hangout.sethFilter(rs.getString("h_filter"));
				hangout.setUserNo(rs.getInt("user_no"));

				list.add(hangout);
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
	public List<HangOutFile> selectFileAll() {
		
		// DB 연결 객체
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql ="";
		sql += "SELECT * FROM hangoutfile";
		
		// 결과 저장할 변수 선언
		List<HangOutFile> fileList = new ArrayList<>();
		HangOutFile hangoutFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				hangoutFile = new HangOutFile();
				
				hangoutFile.setFileNo(rs.getInt("file_no"));
				hangoutFile.setOriginName(rs.getString("origin_name"));
				hangoutFile.setStoredName(rs.getString("stored_name"));
				hangoutFile.setFileSize(rs.getInt("file_size"));
				hangoutFile.sethNo(rs.getInt("h_no"));
				hangoutFile.setUserNo(rs.getInt("user_no"));
				
				fileList.add(hangoutFile);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		// 결과 반환
		return fileList;
	}

	@Override
	public List<HangOut> selectByCity(HangOut hangout) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "SELECT * FROM HangOut";
		sql += " WHERE h_city1=? AND h_city2=? ORDER BY h_no DESC";
		
		List<HangOut> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, hangout.gethCity1());
			ps.setString(2, hangout.gethCity2());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				HangOut place = new HangOut();
				
				place.sethNo(rs.getInt("h_no"));
				place.sethName(rs.getString("h_name"));
				place.sethCity1(rs.getString("h_city1"));
				place.sethCity2(rs.getString("h_city2"));
				place.sethAddress(rs.getString("h_address"));
				place.sethTime(rs.getString("h_time"));
				place.sethTel(rs.getString("h_tel"));
				place.sethEmail(rs.getString("h_email"));
				place.sethDomain(rs.getString("h_domain"));
				place.sethContent(rs.getString("h_content"));
				place.sethFilter(rs.getString("h_filter"));
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
	public List<HangOut> selectByFilter(String[] filters) {
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
		sql += "SELECT * FROM HangOut";
		sql += " WHERE h_filter IN("+totalFilter+") ORDER BY h_no DESC";
		
		List<HangOut> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				HangOut hangout = new HangOut();
				
				hangout.sethNo(rs.getInt("h_no"));
				hangout.sethName(rs.getString("h_name"));
				hangout.sethCity1(rs.getString("h_city1"));
				hangout.sethCity2(rs.getString("h_city2"));
				hangout.sethAddress(rs.getString("h_address"));
				hangout.sethTime(rs.getString("h_time"));
				hangout.sethTel(rs.getString("h_tel"));
				hangout.sethEmail(rs.getString("h_email"));
				hangout.sethDomain(rs.getString("h_domain"));
				hangout.sethContent(rs.getString("h_content"));
				hangout.sethFilter(rs.getString("h_filter"));
				hangout.setUserNo(rs.getInt("user_no"));
				
				list.add(hangout);
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
	public List<HangOut> selectByCity1(HangOut hangout, String[] filters) {
		
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
		sql += "	SELECT * FROM hangout";
		sql += "	WHERE h_filter IN("+totalFilter+") )";
		sql += " WHERE h_city1=? ORDER BY h_no DESC";
		
		List<HangOut> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, hangout.gethCity1());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				HangOut place = new HangOut();
				
				place.sethNo(rs.getInt("h_no"));
				place.sethName(rs.getString("h_name"));
				place.sethCity1(rs.getString("h_city1"));
				place.sethCity2(rs.getString("h_city2"));
				place.sethAddress(rs.getString("h_address"));
				place.sethTime(rs.getString("h_time"));
				place.sethTel(rs.getString("h_tel"));
				place.sethEmail(rs.getString("h_email"));
				place.sethDomain(rs.getString("h_domain"));
				place.sethContent(rs.getString("h_content"));
				place.sethFilter(rs.getString("h_filter"));
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
	public List<HangOut> selectByCityFilter(HangOut hangout, String[] filters) {
		
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
		sql += "	SELECT * FROM hangout";
		sql += "	WHERE h_filter IN("+totalFilter+") )";
		sql += " WHERE h_city1=? AND h_city2=? ORDER BY h_no DESC";
		
		List<HangOut> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, hangout.gethCity1());
			ps.setString(2, hangout.gethCity2());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				HangOut place = new HangOut();
				
				place.sethNo(rs.getInt("h_no"));
				place.sethName(rs.getString("h_name"));
				place.sethCity1(rs.getString("h_city1"));
				place.sethCity2(rs.getString("h_city2"));
				place.sethAddress(rs.getString("h_address"));
				place.sethTime(rs.getString("h_time"));
				place.sethTel(rs.getString("h_tel"));
				place.sethEmail(rs.getString("h_email"));
				place.sethDomain(rs.getString("h_domain"));
				place.sethContent(rs.getString("h_content"));
				place.sethFilter(rs.getString("h_filter"));
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
	public HangOut selectByhNo(HangOut hangout) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "SELECT * FROM hangout";
		sql += " WHERE h_no= ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, hangout.gethNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				hangout.sethName(rs.getString("h_name"));
				hangout.sethCity1(rs.getString("h_city1"));
				hangout.sethCity2(rs.getString("h_city2"));
				hangout.sethAddress(rs.getString("h_address"));
				hangout.sethTime(rs.getString("h_time"));
				hangout.sethTel(rs.getString("h_tel"));
				hangout.sethEmail(rs.getString("h_email"));
				hangout.sethDomain(rs.getString("h_domain"));
				hangout.sethContent(rs.getString("h_content"));
				hangout.sethFilter(rs.getString("h_filter"));
				hangout.setUserNo(rs.getInt("user_no"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return hangout;
	}
	
	@Override
	public HangOutFile selectFileByhNo(HangOut hangout) {
		
		conn = JDBCTemplate.getConnection();
		
		HangOutFile hangoutFile = null;
		
		String sql = "";
		sql += "SELECT * FROM hangoutfile";
		sql += " WHERE h_no=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, hangout.gethNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				hangoutFile = new HangOutFile();
				
				hangoutFile.setFileNo(rs.getInt("file_no"));
				hangoutFile.setOriginName(rs.getString("origin_name"));
				hangoutFile.setStoredName(rs.getString("stored_name"));
				hangoutFile.setFileSize(rs.getInt("file_size"));
				hangoutFile.sethNo(rs.getInt("h_no"));
				hangoutFile.setUserNo(rs.getInt("user_no"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return hangoutFile;
	}
	
	@Override
	public int selecthNo() {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "SELECT HangOut_seq.nextval FROM dual";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				res = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}
	
	@Override
	public void insert(HangOut hangout) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "INSERT INTO hangout";
		sql += "(h_no, h_name, h_city1, h_city2, h_address, h_time, h_tel, h_email, h_domain, h_content, h_filter, user_no)";
		sql += " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, hangout.gethNo());
			ps.setString(2, hangout.gethName());
			ps.setString(3, hangout.gethCity1());
			ps.setString(4, hangout.gethCity2());
			ps.setString(5, hangout.gethAddress());
			ps.setString(6, hangout.gethTime());
			ps.setString(7, hangout.gethTel());
			ps.setString(8, hangout.gethEmail());
			ps.setString(9, hangout.gethDomain());
			ps.setString(10, hangout.gethContent());
			ps.setString(11, hangout.gethFilter());
			ps.setInt(12, hangout.getUserNo());
			
			System.out.println(hangout);
			
			ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}
	
	@Override
	public void insertFile(HangOutFile hangoutFile) {
		
conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "INSERT INTO hangoutfile (file_no, origin_name, stored_name, file_size, h_no, user_no)";
		sql += " VALUES (hangoutfile_seq.nextval, ?,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, hangoutFile.getOriginName());
			ps.setString(2, hangoutFile.getStoredName());
			ps.setInt(3, hangoutFile.getFileSize());
			ps.setInt(4, hangoutFile.gethNo());
			ps.setInt(5, hangoutFile.getUserNo());
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}
	
	@Override
	public void deleteFile(HangOutFile hangoutFile) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "DELETE FROM hangoutfile";
		sql += " WHERE h_no=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, hangoutFile.gethNo());
			
			ps.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}
	
	@Override
	public void delete(HangOut hangout) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "DELETE FROM hangout";
		sql += " WHERE h_no=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, hangout.gethNo());
			
			ps.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}
	
}
