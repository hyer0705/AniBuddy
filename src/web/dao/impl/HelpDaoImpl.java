package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.HelpDao;
import web.dbutil.JDBCTemplate;
import web.dto.HelpFile;
import web.dto.HelpPost;
import web.dto.Help_calls;
import web.dto.UserID;
import web.util.Paging;

public class HelpDaoImpl implements HelpDao{

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	@Override
	public int selectCntAll() {
		conn = JDBCTemplate.getConnection(); //DB연결

		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM help_POST";

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
		sql += "        SELECT * FROM help_post";
		sql += "        WHERE 1=1";
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

				UserID help = new UserID();

				help.setPostno(rs.getInt("post_no"));
				help.setTitle(rs.getString("title"));
				help.setContent(rs.getString("content"));
				help.setWritedate(rs.getDate("write_date"));
				help.setHit(rs.getInt("hit"));
				help.setDealProgress(rs.getString("deal_progress"));
				help.setDealType(rs.getString("deal_type"));
				help.setUserno(rs.getInt("user_no"));

				list.add(help);
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
	public HelpPost selectBoardByBoardNo(HelpPost help) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql+= "SELECT " + 
				"    u.user_id, u.nick, p.*" + 
				"	FROM user_tb u, help_post p" + 
				"	WHERE u.user_no = p.user_no" + 
				"		AND post_no = ?" +
				"	ORDER BY post_no DESC";

		HelpPost result = null;

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체 생성
			ps.setInt(1, help.getPostNo() ); //첫번째 물음표에 매개변수의 empno 삽입
			rs = ps.executeQuery(); //SQL수행 및 결과 반환

			while( rs.next() ) { 

				result = new HelpPost();

				result.setPostNo(rs.getInt("post_no"));
				result.setTitle(rs.getString("title"));
				result.setUserNo(rs.getInt("user_no"));
				result.setUserid(rs.getString("user_id"));
				result.setUsernick(rs.getString("nick"));
				result.setContent(rs.getString("content"));
				result.setHit(rs.getInt("hit"));
				result.setWriteDate(rs.getDate("write_date"));
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
	public void updateHit(HelpPost help) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "UPDATE help_post SET hit";
		sql += " = hit+1 WHERE post_no = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, help.getPostNo());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

	}

	@Override
	public HelpFile selectFile(HelpPost result) {
		conn = JDBCTemplate.getConnection();

		String sql = ""; 
		sql += "SELECT * FROM help_file";
		sql += " WHERE post_no=?";

		//결과 저장할 BoardFile 객체
		HelpFile helpFile = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, result.getPostNo()); //조회할 boardno 적용

			rs = ps.executeQuery();

			//조회 결과 처리
			while(rs.next()) {
				helpFile = new HelpFile();

				helpFile.setFileno(rs.getInt("fileno"));
				helpFile.setPost_no(rs.getInt("post_no"));
				helpFile.setOrigin_name(rs.getString("origin_name"));
				helpFile.setStored_name(rs.getString("stored_name"));
				helpFile.setFilesize(rs.getInt("filesize"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return helpFile;
	}

	@Override
	public int selectPostno() {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "SELECT help_POST_seq.nextval FROM dual";

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
	public void insert(HelpPost post) {
		conn = JDBCTemplate.getConnection();

		String sql = ""; 
		sql += "INSERT INTO help_post (post_no, board_no, title, user_no, content, hit, deal_progress)";
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
	public void insertFile(HelpFile helpFile) {
		conn = JDBCTemplate.getConnection();

		String sql = ""; 
		sql += "INSERT INTO help_file (fileno, post_no, origin_name, stored_name, filesize)";
		sql += " VALUES ( help_file_seq.nextval,?,?,?,?)";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, helpFile.getPost_no());
			ps.setString(2, helpFile.getOrigin_name());
			ps.setString(3, helpFile.getStored_name());
			ps.setInt(4, helpFile.getFilesize());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	}

	@Override
	public void update(HelpPost post) {
		conn = JDBCTemplate.getConnection();

		String sql = ""; 
		sql += "UPDATE help_post SET title = ?, content = ?, deal_progress=?" ;
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
	public void deleteFile(HelpPost post) {
		conn = JDBCTemplate.getConnection();

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE help_file";
		sql += " WHERE post_no = ?";

		//DB 객체
		PreparedStatement ps = null; 

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, post.getPostNo());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

	}

	@Override
	public void delete(HelpPost post) {
		conn = JDBCTemplate.getConnection();

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE help_post";
		sql += " WHERE post_no = ?";

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, post.getPostNo());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}



	}

	@Override
	public int selectCntRecommend(Help_calls calls) {
conn = JDBCTemplate.getConnection();
		
		String sql ="";
		sql += "SELECT count(*) FROM help_call_dibs";
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
	public void deleteRecommend(Help_calls calls) {
		conn = JDBCTemplate.getConnection();
		
		String sql ="";
		sql += "DELETE help_call_dibs";
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
	public void insertRecommend(Help_calls calls) {
		conn = JDBCTemplate.getConnection();
		
		String sql ="";
		sql += "INSERT INTO help_call_dibs";
		sql += " VALUES(HELP_CALL_DIBS_SEQ.nextval,?,?)";
		
		
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
	      sql += " FROM help_post p, user_tb u";
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
	public int selectCntHelpSearch(String search) {
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM help_post";
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
