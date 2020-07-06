package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.HelpCommentDao;
import web.dbutil.JDBCTemplate;
import web.dto.HelpComment;
import web.dto.HelpPost;

public class HelpCommentDaoImpl implements HelpCommentDao{

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<HelpComment> selectComment(HelpPost board) {
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
				+ "	FROM help_comment p, user_tb u"
				+ "	WHERE post_no = ?"
				+ "		AND p.user_no = u.user_no"
				+ "	ORDER BY write_date"
				+ "	) B"
				+ ") ORDER BY rnum";

		List<HelpComment> commentList = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, board.getPostNo() );

			// ResultSet 반환
			rs = ps.executeQuery();

			while( rs.next() ) {
				HelpComment comment = new HelpComment();

				comment.setRnum(rs.getInt("rnum"));
				comment.setPost_no(rs.getInt("post_no"));
				comment.setComment_no(rs.getInt("comment_no"));
				comment.setUser_no(rs.getInt("user_no"));
				comment.setContent(rs.getString("content"));
				comment.setWrite_date(rs.getDate("write_date"));
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
	public void insertComment(HelpComment comment) {
		conn = JDBCTemplate.getConnection();

		String sql
		= "INSERT INTO help_comment ("
				+ "		comment_no,"
				+ "		post_no,"
				+ "		user_no,"
				+ "		content )"
				+ "	VALUES ("
				+ "		help_comment_seq.nextval,"
				+ "		?,"
				+ "		?,"
				+ "		? )";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, comment.getPost_no());
			ps.setInt(2, comment.getUser_no());
			ps.setString(3, comment.getContent());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
	}

	@Override
	public void deleteComment(HelpComment comment) {
		conn = JDBCTemplate.getConnection();

		String sql
		= "DELETE help_comment"
				+ "	WHERE comment_no = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getComment_no());
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}

	@Override
	public int countComment(HelpComment comment) {
		conn = JDBCTemplate.getConnection();

		String sql = "SELECT COUNT(*) FROM help_comment WHERE comment_no=?";
		
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getComment_no());
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
