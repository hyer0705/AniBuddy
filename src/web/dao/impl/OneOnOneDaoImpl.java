package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.OneOnOneDao;
import web.dbutil.JDBCTemplate;
import web.dto.OneOnOne;
import web.util.Paging;

public class OneOnOneDaoImpl implements OneOnOneDao{

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	@Override
	public int selectCntAll(String search) {
		conn = JDBCTemplate.getConnection(); //DB연결

		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM oneonone";
		sql += " WHERE 1=1";
		
		// search 가 null이 아닐 때
		if( search != null && !"".equals(search) ) {
			sql += "    AND title LIKE '%'||?||'%'";
		}

		//최종 결과값
		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			if(search != null && !"".equals(search)) {
				ps.setString(1, search);
			}

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
	public List<OneOnOne> selectOneOnOne(Paging paging) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	    SELECT rownum rnum, O.* FROM (";
		sql += "	        SELECT * FROM oneonone";
		sql += "	        WHERE 1=1";
		if( paging.getSearch() != null && !"".equals(paging.getSearch())) {
			sql += "	            AND title LIKE '%'||?||'%'";
		}
		sql += "	        START WITH reply_no IS NULL";
		sql += "	        CONNECT BY reply_no = PRIOR oneonone_no";
		sql += "	        ORDER SIBLINGS BY oneonone_no DESC";
		sql += "	    ) O";
		sql += "	) One";
		sql += "	WHERE rnum BETWEEN ? AND ?";
		
//		String sql = "";
//		sql += "SELECT * FROM (";
//		sql += "    SELECT rownum rnum, O.* FROM (";
//		sql += "        SELECT * FROM oneonone";
//		sql += "        WHERE 1=1";
//		if( paging.getSearch() != null && !"".equals(paging.getSearch())) {
//		sql += "		AND title LIKE '%'||?||'%'";
//		}
//		sql += "        ORDER BY write_date DESC";
//		sql += "    ) O";
//		sql += " ) One";
//		sql += " WHERE rnum BETWEEN ? AND ?";

		List<OneOnOne> list = new ArrayList<OneOnOne>();

		try {
			ps = conn.prepareStatement(sql);

			int idx = 0;
			if( paging.getSearch() != null && !"".equals(paging.getSearch())) {
				ps.setString(++idx, paging.getSearch());	
			}
			ps.setInt(++idx, paging.getStartNo());	//페이징 게시글 시작 번호
			ps.setInt(++idx, paging.getEndNo());	//페이징 게시글 끝 번호


			rs = ps.executeQuery();

			while(rs.next()) {

				OneOnOne one = new OneOnOne();

				one.setOneononeNo(rs.getInt("oneonone_no"));
				one.setTitle(rs.getString("title"));
				one.setContent(rs.getString("content"));
				one.setWriteDate(rs.getDate("write_date"));
				one.setReplyProgress(rs.getBoolean("reply_progress"));
				one.setReplyNo(rs.getInt("reply_no"));
				one.setCondition(rs.getString("condition"));
				one.setUserNo(rs.getInt("user_no"));
				one.setAdminNo(rs.getInt("admin_no"));

				list.add(one);
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
	public OneOnOne selectOneOnOneByno(OneOnOne viewOneOnOne) {
		conn = JDBCTemplate.getConnection();

		String sql = "SELECT * FROM oneonone";
		sql += " WHERE oneonone_no = ?";

		OneOnOne oneOnOne = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, viewOneOnOne.getOneononeNo());

			rs = ps.executeQuery();

			while(rs.next()) {

				oneOnOne = new OneOnOne();

				oneOnOne.setOneononeNo(rs.getInt("oneonone_no"));
				oneOnOne.setTitle(rs.getString("title"));
				oneOnOne.setContent(rs.getString("content"));
				oneOnOne.setWriteDate(rs.getDate("write_date"));
				oneOnOne.setReplyProgress(rs.getBoolean("reply_progress"));
				oneOnOne.setReplyNo(rs.getInt("reply_no"));
				oneOnOne.setCondition(rs.getString("condition"));
				oneOnOne.setUserNo(rs.getInt("user_no"));
				oneOnOne.setAdminNo(rs.getInt("admin_no"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return oneOnOne;
	}

	@Override
	public OneOnOne insertCondition(OneOnOne viewOneOnOne) {
		conn = JDBCTemplate.getConnection();

		String sql = " update oneonone set condition = 'checked' where oneonone_no = ?";

		OneOnOne oneOnOne = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, viewOneOnOne.getOneononeNo());

			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return oneOnOne;
	}
	
	@Override
	public void deleteOne(String names) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql +="DELETE oneonone";
		sql +=" WHERE oneonone_no IN(" +names+" )";

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
	public void insert(OneOnOne o3) {
		
		// DB 연결
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "INSERT INTO oneonone ( oneonone_no, title, content, write_date, reply_progress, reply_no, condition, user_no, admin_no)";
		sql += " VALUES (oneonone_seq.nextval, ?, ?, sysdate, '1', ?, 'checked', null, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, o3.getTitle());
			ps.setString(2, o3.getContent());
			ps.setInt(3, o3.getReplyNo());
			ps.setInt(4, o3.getAdminNo());
			
			// 쿼리 수행
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
	}

	@Override
	public void updateQuestion(OneOnOne q) {
		
		// DB 연결 객체
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "UPDATE oneonone";
		sql += " SET";
		sql += "    condition = 'checked'";
		sql += "    , reply_progress = '1'";
		sql += " WHERE oneonone_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, q.getOneononeNo());
			
			// 쿼리문 처리
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
	}
	
	@Override
	public OneOnOne selectOneOnOneByReplyno(OneOnOne o3) {
		
		// DB 연결
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM oneonone";
		sql += " WHERE oneonone_no = (";
		sql += "    SELECT reply_no FROM oneonone";
		sql += "    WHERE oneonone_no= ?)";
		
		// 결과 반환 객체
		OneOnOne result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, o3.getOneononeNo());
			
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
		}
		
		return result;
	}
	
	@Override
	public void update(OneOnOne q) {
		
		// DB 연결
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "UPDATE oneonone";
		sql += " SET";
		sql += "	title = ?";
		sql += "	, content = ?";
		sql += " WHERE oneonone_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, q.getTitle());
			ps.setString(2, q.getContent());
			ps.setInt(3, q.getOneononeNo());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
