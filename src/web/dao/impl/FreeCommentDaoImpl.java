package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.FreeCommentDao;
import web.dbutil.JDBCTemplate;
import web.dto.FreeBoard;
import web.dto.FreeComment;

public class FreeCommentDaoImpl implements FreeCommentDao{

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List selectComment(FreeBoard board) {
		conn = JDBCTemplate.getConnection();

		String sql
		= "SELECT * FROM ("
				+ "SELECT rownum rnum, B.* FROM ("
				+ "	SELECT"
				+ "		comment_no,"
				+ "		post_no,"
				+ "		p.user_no,"
				+ "		content,"
				+ "		write_date,"
				+ "		user_id"
				+ "	FROM free_comment p, user_tb u"
				+ "	WHERE post_no = ?"
				+ "		AND p.user_no = u.user_no"
				+ "	ORDER BY write_date"
				+ "	) B"
				+ ") ORDER BY rnum";

		List commentList = new ArrayList();
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, board.getPostno() );

			// ResultSet 반환
			rs = ps.executeQuery();

			while( rs.next() ) {
				FreeComment comment = new FreeComment();

				comment.setRnum(rs.getInt("rnum"));
				comment.setPostno(rs.getInt("post_no"));
				comment.setCommentno(rs.getInt("comment_no"));
				comment.setUserno(rs.getInt("user_no"));
				comment.setContent(rs.getString("content"));
				comment.setWritedate(rs.getDate("write_date"));
				comment.setUserid(rs.getString("user_id"));
				
				commentList.add(comment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);

		}

		return commentList;
	}
	
	@Override
	public void insertComment(FreeComment comment) {
		
		conn = JDBCTemplate.getConnection();

		String sql
		= "INSERT INTO free_comment ("
				+ "		comment_no,"
				+ "		post_no,"
				+ "		user_no,"
				+ "		content )"
				+ "	VALUES ("
				+ "		free_comment_seq.nextval,"
				+ "		?,"
				+ "		?,"
				+ "		? )";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, comment.getPostno());
			ps.setInt(2, comment.getUserno());
			ps.setString(3, comment.getContent());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		
	}

	@Override
	public void deleteComment(FreeComment comment) {
		conn = JDBCTemplate.getConnection();

		String sql
		= "DELETE free_comment"
				+ "	WHERE comment_no = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getCommentno());
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
	}

	@Override
	public int countComment(FreeComment comment) {
		conn = JDBCTemplate.getConnection();

		String sql = "SELECT COUNT(*) FROM free_comment WHERE comment_no=?";
		
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getCommentno());
			rs = ps.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return cnt;
	}

}
