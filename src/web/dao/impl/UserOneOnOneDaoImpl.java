package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.UserOneOnOneDao;
import web.dbutil.JDBCTemplate;
import web.dto.OneOnOne;
import web.dto.UserTB;
import web.util.Paging;

public class UserOneOnOneDaoImpl implements UserOneOnOneDao{

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int selectCntByUserno(String search, UserTB currUser) {

		// DB 연결
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM oneonone";
		sql += " WHERE 1=1";
		sql += "    AND user_no = ?";
		// search 가 null 이 아닐 때
		if(search != null && !"".equals(search)) {
			sql += "    AND title LIKE '%'||?||'%'";
		}
		sql += " ORDER BY write_date DESC, oneonone_no DESC";
		
		// 최종 결과값 
		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, currUser.getUserNo());
			int idx = 1;
			if(search != null && !"".equals(search)) {
				ps.setString(++idx, search);
			}

			rs = ps.executeQuery();

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
	public int selectCntAll(String search) {
		
		// DB 연결
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM oneonone";
		
		// 최종 결과값 
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
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
	public List<OneOnOne> selectOneOnOne(Paging paging, UserTB currUser) {
//		System.out.println("UserO3DaoImpl paging.getSearch(): " + paging.getSearch());
		
		// DB 연결객체
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, O.* FROM (";
		sql += "        SELECT * FROM oneonone";
		sql += "        WHERE 1=1";
		sql += "            AND user_no = ?";
		
		if( paging.getSearch() != null && !"".equals(paging.getSearch() ) ) {
			sql += "            AND title LIKE '%'||?||'%'";
		}
		sql += "        ORDER BY write_date DESC, oneonone_no DESC";
		sql += "    ) O";
		sql += " ) One";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		// 결과를 저장할 List 객체
		List<OneOnOne> list = new ArrayList<OneOnOne>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, currUser.getUserNo());
			
			int idx = 1;
			if( paging.getSearch() != null && !"".equals(paging.getSearch() ) ) {
				ps.setString(++idx, paging.getSearch());
			}
			ps.setInt(++idx,  paging.getStartNo()); // 페이징 게시글 시작 번호
			ps.setInt(++idx, paging.getEndNo()); // 페이징 게시글 끝 번호
			
			rs = ps.executeQuery();
			
			// 결과 처리
			while(rs.next()) {
				
				OneOnOne result = new OneOnOne();
				result.setOneononeNo(rs.getInt("oneonone_no"));
				result.setTitle(rs.getString("title"));
				result.setContent(rs.getString("content"));
				result.setWriteDate(rs.getDate("write_date"));
				result.setReplyProgress(rs.getBoolean("reply_progress"));
				result.setReplyNo(rs.getInt("reply_no"));
				result.setCondition(rs.getString("condition"));
				result.setUserNo(rs.getInt("user_no"));
				
				list.add(result);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}
	
	@Override
	public void insert(OneOnOne o3) {
		
		// DB 연결
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "INSERT INTO oneonone ( oneonone_no, title, content, write_date, reply_progress, reply_no, condition, user_no, admin_no)";
		sql += " VALUES (oneonone_seq.nextval, ?, ?, sysdate, '0', null, 'true', ?, null)";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, o3.getTitle());
			ps.setString(2, o3.getContent());
			ps.setInt(3, o3.getUserNo());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}
	
	@Override
	public OneOnOne selectOneOnOneByNo(OneOnOne detailO3) {
		
		// DB 연결 객체
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM oneonone";
		sql += " WHERE oneonone_no = ?";
		
		// 결과 반환 객체 변수 선언
		OneOnOne result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, detailO3.getOneononeNo());
			
			rs = ps.executeQuery();
			
			// 결과 조회 처리
			while( rs.next() ) {
				result = new OneOnOne();
				
				result.setOneononeNo(rs.getInt("oneonone_no"));
				result.setTitle(rs.getString("title"));
				result.setContent(rs.getString("content"));
				result.setWriteDate(rs.getDate("write_date"));
				result.setReplyProgress(rs.getBoolean("reply_progress"));
				result.setCondition(rs.getString("condition"));
				result.setUserNo(rs.getInt("user_no"));
				
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
	public void update(OneOnOne o3) {
		
		// DB 연결
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "UPDATE oneonone";
		sql += " SET";
		sql += "    title = ?";
		sql += "    , content = ?";
		sql += " WHERE oneonone_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, o3.getTitle());
			ps.setString(2, o3.getContent());
			ps.setInt(3, o3.getOneononeNo());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}
	
	@Override
	public void delete(OneOnOne o3) {
		
		//DB 연결 객체
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "DELETE oneonone";
		sql += " WHERE oneonone_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, o3.getOneononeNo());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}
	
	@Override
	public OneOnOne selectReplyByNo(OneOnOne detailO3) {
		
		// DB 연결 객체
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM oneonone";
		sql += " WHERE reply_no = ?";
		
		// 결과 반환 객체
		OneOnOne result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, detailO3.getOneononeNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result = new OneOnOne();
				result.setOneononeNo(rs.getInt("oneonone_no"));
				result.setTitle(rs.getString("title"));
				result.setContent(rs.getString("content"));
				result.setWriteDate(rs.getDate("write_date"));
				result.setReplyProgress(rs.getBoolean("reply_progress"));
				result.setReplyNo(rs.getInt("reply_no"));
				result.setCondition(rs.getString("condition"));
				result.setUserNo(rs.getInt("user_no"));
				result.setAdminNo(rs.getInt("admin_no"));
				
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
