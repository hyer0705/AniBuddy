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
	public List<OneOnOne> selectOneOnOne(Paging paging) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, O.* FROM (";
		sql += "        SELECT * FROM oneonone";
		sql += "        WHERE 1=1";
		if( paging.getSearch() != null && !"".equals(paging.getSearch())) {
		sql += "		AND title LIKE '%'||?||'%'";
		}
		sql += "        ORDER BY write_date DESC";
		sql += "    ) O";
		sql += " ) One";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<OneOnOne> list = new ArrayList<OneOnOne>();

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

				OneOnOne one = new OneOnOne();

				one.setOneononeNo(rs.getInt("oneonone_no"));
				one.setTitle(rs.getString("title"));
				one.setContent(rs.getString("content"));
				one.setWriteDate(rs.getDate("write_date"));
				one.setReplyProgress(rs.getBoolean("reply_progress"));
				one.setReplyNo(rs.getInt("reply_no"));
				one.setCondition(rs.getString("condition"));
				one.setUserNo(rs.getInt("user_no"));

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

}
