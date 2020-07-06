package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.ShareCommentDao;
import web.dbutil.JDBCTemplate;
import web.dto.ShareComment;
import web.dto.SharePost;
import web.dto.UserID;

public class ShareCommentDaoImpl implements ShareCommentDao{

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<ShareComment> selectComment(UserID board) {
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
				+ "	FROM share_comment p, user_tb u"
				+ "	WHERE post_no = ?"
				+ "		AND p.user_no = u.user_no"
				+ "	ORDER BY write_date"
				+ "	) B"
				+ ") ORDER BY rnum";

		List<ShareComment> commentList = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, board.getPostno() );

			// ResultSet 반환
			rs = ps.executeQuery();

			while( rs.next() ) {
				ShareComment comment = new ShareComment();

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
	public void insertComment(ShareComment comment) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "INSERT INTO share_comment (";
		sql +=	 "		comment_no,";
		sql += "		post_no,";
		if( comment.getUser_no() ==0) {
			sql += "		admin_no,";
		}else {
			sql += "		user_no,";
		}
		sql += "		content )";
		sql += "	VALUES (";
		sql += "		share_comment_seq.nextval,";
		sql += "		?,";
		sql += "		?,";
		sql += "		? )";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, comment.getPost_no());
			if( comment.getUser_no() ==0) {
				ps.setInt(2, comment.getAdmin_no());
			}else {
				ps.setInt(2, comment.getUser_no());
			}
			ps.setString(3, comment.getContent());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
	}

	@Override
	public void deleteComment(ShareComment comment) {
		conn = JDBCTemplate.getConnection();

		String sql
		= "DELETE share_comment"
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
	public int countComment(ShareComment comment) {
		conn = JDBCTemplate.getConnection();

		String sql = "SELECT COUNT(*) FROM share_comment WHERE comment_no=?";

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

	@Override
	public List<ShareComment> selectAdminComment(UserID post) {
		conn = JDBCTemplate.getConnection();

		String sql
		= "SELECT * FROM ("
				+ "SELECT rownum rnum, B.* FROM ("
				+ "	SELECT"
				+ "		comment_no,"
				+ "		post_no,"
				+ "		admin_no,"
				+ "		content,"
				+ "		write_date,"
				+ "		u.admin_id"
				+ "	FROM share_comment p, admin u"
				+ "	WHERE post_no = ?"
				+ "		AND p.admin_no = u.admin_no"
				+ "	ORDER BY write_date"
				+ "	) B"
				+ ") ORDER BY rnum";

		List<ShareComment> commentList = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, post.getAdminno() );

			// ResultSet 반환
			rs = ps.executeQuery();

			while( rs.next() ) {
				ShareComment comment = new ShareComment();

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

}
