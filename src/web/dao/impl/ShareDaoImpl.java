package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.ShareDao;
import web.dbutil.JDBCTemplate;
import web.dto.FreeBoard;
import web.dto.FreeBoardFile;
import web.dto.ShareFile;
import web.dto.SharePost;
import web.dto.Share_calls;
import web.dto.UserID;
import web.util.Paging;

public class ShareDaoImpl implements ShareDao{

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	@Override
	public int selectCntAll() {
		conn = JDBCTemplate.getConnection(); //DB연결

		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM SHARE_POST";

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
	public List<UserID> selectAll(Paging paging) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, O.* FROM (";
		sql += "        SELECT  ";
		sql += "		u.user_id, p.*";
		sql +="			FROM share_post p, user_tb u";
		sql += "        WHERE u.user_no = p.user_no";
		if( paging.getSearch() != null && !"".equals(paging.getSearch())) {
			sql += "		AND title LIKE '%'||?||'%'";
		}
		sql += "        ORDER BY write_date DESC";
		sql += "    ) O";
		sql += " ) One";
		sql += " WHERE rnum BETWEEN ? AND ?";

		
		
		List<UserID> list = new ArrayList<>();

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

				UserID share = new UserID();

				share.setPostno(rs.getInt("post_no"));
				share.setUserid(rs.getString("user_id"));
				share.setTitle(rs.getString("title"));
				share.setContent(rs.getString("content"));
				share.setWritedate(rs.getDate("write_date"));
				share.setHit(rs.getInt("hit"));
				share.setDealProgress(rs.getString("deal_progress"));
				share.setDealType(rs.getString("deal_type"));
				share.setUserno(rs.getInt("user_no"));

				list.add(share);
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
	public UserID selectBoardByBoardNo(UserID share) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql+= "SELECT " + 
				"    u.user_id, u.nick, p.*" + 
				"	FROM user_tb u, share_post p" + 
				"	WHERE u.user_no = p.user_no" + 
				"		AND post_no = ?" +
				"	ORDER BY post_no DESC";

		UserID result = null;

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체 생성
			ps.setInt(1, share.getPostno() ); //첫번째 물음표에 매개변수의 empno 삽입
			rs = ps.executeQuery(); //SQL수행 및 결과 반환

			while( rs.next() ) { 

				result = new UserID();

				result.setPostno(rs.getInt("post_no"));
				result.setTitle(rs.getString("title"));
				result.setUserno(rs.getInt("user_no"));
				result.setUserid(rs.getString("user_id"));
				result.setUsernick(rs.getString("nick"));
				result.setContent(rs.getString("content"));
				result.setHit(rs.getInt("hit"));
				result.setWritedate(rs.getDate("write_date"));
				result.setDealProgress(rs.getString("deal_progress"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//----- 자원 해제 -----
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//----- 결과 반환 -----
		return result;
	}

	@Override
	public void updateHit(UserID share) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "UPDATE share_post SET hit";
		sql += " = hit+1 WHERE post_no = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, share.getPostno());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

	}

	@Override
	public ShareFile selectFile(UserID result) {
		conn = JDBCTemplate.getConnection();

		String sql = ""; 
		sql += "SELECT * FROM share_file";
		sql += " WHERE post_no=?";

		//결과 저장할 BoardFile 객체
		ShareFile shareFile = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, result.getPostno()); //조회할 boardno 적용

			rs = ps.executeQuery();

			//조회 결과 처리
			while(rs.next()) {
				shareFile = new ShareFile();

				shareFile.setFileno(rs.getInt("fileno"));
				shareFile.setPost_no(rs.getInt("post_no"));
				shareFile.setOrigin_name(rs.getString("origin_name"));
				shareFile.setStored_name(rs.getString("stored_name"));
				shareFile.setFilesize(rs.getInt("filesize"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return shareFile;
	}

	@Override
	public int selectPostno() {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "SELECT SHARE_POST_seq.nextval FROM dual";

		//결과 저장할 String 변수
		int postno = 0;

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()) {
				postno = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//결과 반환
		return postno;
	}

	@Override
	public void insert(SharePost post) {
		conn = JDBCTemplate.getConnection();

		String sql = ""; 
		sql += "INSERT INTO share_post (post_no, board_no, title, user_no, content, hit, deal_progress)";
		sql += " VALUES ( ?,5,?,?,?,0,? )";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, post.getPostNo());
			ps.setString(2, post.getTitle());
			ps.setInt(3, post.getUserNo());
			ps.setString(4, post.getContent());
			ps.setString(5, post.getDealProgress());


			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	}

	@Override
	public void insertFile(ShareFile shareFile) {
		conn = JDBCTemplate.getConnection();

		String sql = ""; 
		sql += "INSERT INTO share_file (fileno, post_no, origin_name, stored_name, filesize)";
		sql += " VALUES ( share_file_seq.nextval,?,?,?,?)";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, shareFile.getPost_no());
			ps.setString(2, shareFile.getOrigin_name());
			ps.setString(3, shareFile.getStored_name());
			ps.setInt(4, shareFile.getFilesize());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	}

	@Override
	public void update(SharePost post) {
		conn = JDBCTemplate.getConnection();

		String sql = ""; 
		sql += "UPDATE share_post SET title = ?, content = ?, deal_progress=?" ;
		sql += " WHERE post_no = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, post.getTitle());
			ps.setString(2, post.getContent());
			ps.setString(3, post.getDealProgress());
			ps.setInt(4, post.getPostNo());

			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

	}

	@Override
	public void deleteFile(UserID post) {
		conn = JDBCTemplate.getConnection();

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE share_file";
		sql += " WHERE post_no = ?";

		//DB 객체
		PreparedStatement ps = null; 

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, post.getPostno());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

	}

	@Override
	public void delete(UserID post) {
		conn = JDBCTemplate.getConnection();

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE share_post";
		sql += " WHERE post_no = ?";

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, post.getPostno());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}



	}

	@Override
	public int selectCntRecommend(Share_calls calls) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql ="";
		sql += "SELECT count(*) FROM share_call_dibs";
		sql += " WHERE post_no =?";
		sql += " AND user_id =?";
		
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, calls.getPost_no());
			ps.setInt(2, calls.getUser_id());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return cnt;
	}

	@Override
	public void deleteRecommend(Share_calls calls) {
		conn = JDBCTemplate.getConnection();
		
		String sql ="";
		sql += "DELETE share_call_dibs";
		sql += " WHERE user_id=?";
		sql += " AND post_no=?";
		
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, calls.getUser_id());
			ps.setInt(2, calls.getPost_no());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(ps);
		}
		
	}

	@Override
	public void insertRecommend(Share_calls calls) {
		conn = JDBCTemplate.getConnection();
		
		String sql ="";
		sql += "INSERT INTO share_call_dibs";
		sql += " VALUES(SHARE_CALL_DIBS_SEQ.nextval,?,?)";
		
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, calls.getUser_id());
			ps.setInt(2, calls.getPost_no());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(ps);
		}
		
	}

	@Override
	public List<UserID> selectChart() {
		 conn = JDBCTemplate.getConnection();
	      
	      String sql="";
	      sql += "SELECT rownum, F.* FROM";
	      sql += " (SELECT post_no, title, u.user_id, hit, write_date, deal_progress";
	      sql += " FROM share_post p, user_tb u";
	      sql += " WHERE u.user_no = p.user_no";
	      sql += " ORDER BY hit DESC) F";
	      sql += " WHERE rownum<4";
	            
	      List<UserID> list = new ArrayList<>();
	      
	      try {
	         ps = conn.prepareStatement(sql);
	         
	         rs = ps.executeQuery();
	         
	         while(rs.next()) {
	            UserID board = new UserID();
	            
	            board.setPostno(rs.getInt("post_no"));
	            board.setTitle(rs.getString("title"));
	            board.setUserid(rs.getString("user_id"));
	            board.setHit(rs.getInt("hit"));
	            board.setWritedate(rs.getDate("write_date"));
	            board.setDealProgress(rs.getString("deal_progress"));
	            
	            list.add(board);
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
	public int selectCntMailSearch(String search) {
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM share_post";
		sql += " WHERE 1=1";
		sql += "    AND title LIKE '%'||?||'%'";
		sql += " ORDER BY write_date DESC, post_no DESC";
		
		// 결과 반환 변수
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, search);
			
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






}
