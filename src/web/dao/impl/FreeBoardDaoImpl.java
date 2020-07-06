package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.FreeBoardDao;
import web.dbutil.JDBCTemplate;
import web.dto.FreeBoard;
import web.dto.FreeBoardFile;
import web.dto.UserID;
import web.util.Paging;

public class FreeBoardDaoImpl implements FreeBoardDao{

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int selectCntAll() {
		
		conn = JDBCTemplate.getConnection();
		
		//SQL 작성
		String sql ="";
		sql += "SELECT count(*) FROM free_post";
		
		//최종 결과값
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			//조회결과 처리
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

	@Override
	public List<UserID> selectAll(Paging paging) {
		
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += "		SELECT";
		sql += "			post_no, title, u.user_id, hit, write_date, range";
		sql += "			FROM free_post p, user_tb u";
		sql += "			WHERE u.user_no = p.user_no";
		sql += "				AND title LIKE '%'||?||'%'";
		sql += "		ORDER BY post_no DESC";
		sql += "	) B";
		sql += " ) free_post";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<UserID> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, paging.getSearch());
			ps.setInt(2, paging.getStartNo()); 	//페이징 게시글 시작 번호
			ps.setInt(3, paging.getEndNo());	//페이징 게시글 끝 번호
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				UserID board = new UserID();
				
				board.setPostno(rs.getInt("post_no"));
				board.setTitle(rs.getString("title"));
				board.setUserid(rs.getString("user_id"));
//				board.setUserno(rs.getInt("user_no"));
//				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setWritedate(rs.getDate("write_date"));
				board.setRange(rs.getString("range"));
				board.setRange(rs.getString("range"));
				
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
	public FreeBoard selectBoardByBoardNo(FreeBoard postno) {

		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql+= "SELECT " + 
				"    u.user_id, u.nick, p.*" + 
				"	FROM user_tb u, free_post p" + 
				"	WHERE u.user_no = p.user_no" + 
				"		AND post_no = ?" +
				"	ORDER BY post_no DESC";
		
		FreeBoard result = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체 생성
			ps.setInt(1, postno.getPostno() ); //첫번째 물음표에 매개변수의 empno 삽입
			rs = ps.executeQuery(); //SQL수행 및 결과 반환
			
			while( rs.next() ) { 
				
				result = new FreeBoard();
				
				result.setPostno(rs.getInt("post_no"));
				result.setTitle(rs.getString("title"));
				result.setUserno(rs.getInt("user_no"));
				result.setUserid(rs.getString("user_id"));
				result.setUsernick(rs.getString("nick"));
				result.setContent(rs.getString("content"));
				result.setHit(rs.getInt("hit"));
				result.setWritedate(rs.getDate("write_date"));
				result.setRange(rs.getString("range"));
				
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
	public void updateHit(FreeBoard board) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "UPDATE free_post SET hit";
		sql += " = hit+1 WHERE post_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, board.getPostno());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}

	@Override
	public FreeBoardFile selectFile(FreeBoard result) {
		conn = JDBCTemplate.getConnection();
		
		String sql = ""; 
		sql += "SELECT * FROM free_file";
		sql += " WHERE post_no=?";
		
		//결과 저장할 BoardFile 객체
		FreeBoardFile boardFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, result.getPostno()); //조회할 boardno 적용
			
			rs = ps.executeQuery();
			
			//조회 결과 처리
			while(rs.next()) {
				boardFile = new FreeBoardFile();
				
				boardFile.setFileno(rs.getInt("fileno"));
				boardFile.setPostno(rs.getInt("post_no"));
				boardFile.setOriginname(rs.getString("origin_name"));
				boardFile.setStoredname(rs.getString("stored_name"));
				boardFile.setFilesize(rs.getInt("filesize"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return boardFile;
	}

	@Override
	public int selectPostno() {
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "SELECT free_seq.nextval FROM dual";
		
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
	public void insert(FreeBoard board) {
		conn = JDBCTemplate.getConnection();
		
		String sql = ""; 
		sql += "INSERT INTO free_post (post_no, board_no, title, user_no, content, hit, range)";
		sql += " VALUES ( ?,5,?,?,?,0,? )";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, board.getPostno());
			ps.setString(2, board.getTitle());
			ps.setInt(3, board.getUserno());
			ps.setString(4, board.getContent());
			ps.setString(5, board.getRange());
			
			
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
	}

	@Override
	public void insertFile(FreeBoardFile boardFile) {
		conn = JDBCTemplate.getConnection();
		
		String sql = ""; 
		sql += "INSERT INTO free_file (fileno, post_no, origin_name, stored_name, filesize)";
		sql += " VALUES ( free_file_seq.nextval,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, boardFile.getPostno());
			ps.setString(2, boardFile.getOriginname());
			ps.setString(3, boardFile.getStoredname());
			ps.setInt(4, boardFile.getFilesize());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
	}
	
	@Override
	public void update(FreeBoard board) {
		
		conn = JDBCTemplate.getConnection();

		String sql = ""; 
		sql += "UPDATE free_post SET title = ?, content = ?";
		sql += " WHERE post_no = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getPostno());

			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}

	@Override
	public void deleteFile(FreeBoard board) {

		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE free_file";
		sql += " WHERE post_no = ?";

		//DB 객체
		PreparedStatement ps = null; 

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getPostno());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

	}

	@Override
	public void delete(FreeBoard board) {
		
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE free_post";
		sql += " WHERE post_no = ?";

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getPostno());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}
		
	}

	   @Override
	   public List<UserID> selectChart() {
	      conn = JDBCTemplate.getConnection();
	      
	      String sql="";
	      sql += "SELECT rownum, F.* FROM";
	      sql += " (SELECT post_no, title, u.user_id, hit, write_date, range";
	      sql += " FROM free_post p, user_tb u";
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
	            board.setRange(rs.getString("range"));
	            
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
	public int selectCntFreeSearch(String search) {
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM free_post";
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
