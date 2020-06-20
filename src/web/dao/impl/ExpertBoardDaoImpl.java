package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.ExpertBoardDao;
import web.dbutil.JDBCTemplate;
import web.dto.ExpertBoard;
import web.dto.ExpertBoardFile;
import web.dto.UserID;
import web.util.Paging;

public class ExpertBoardDaoImpl implements ExpertBoardDao{

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int selectCntAll() {
		
		conn = JDBCTemplate.getConnection();
		
		//SQL 작성
		String sql ="";
		sql += "SELECT count(*) FROM expert_post";
		
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
		sql += "			post_no, title, u.user_id, hit, write_date, range, is_expert, group_no, group_ord, depth";
		sql += "			FROM expert_post p, user_tb u";
		sql += "			WHERE u.user_no = p.user_no";
		sql += "				AND title LIKE '%'||?||'%'";
		sql += "		ORDER BY group_no DESC, depth, post_no ";
		sql += "	) B";
		sql += " ) expert_post";
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
				board.setIsexpert(rs.getString("is_expert"));
				board.setGroupno(rs.getInt("group_no"));
				board.setGroupord(rs.getInt("group_ord"));
				board.setDepth(rs.getInt("depth"));
//				board.setRange(rs.getString("range"));
				
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
	public ExpertBoard selectBoardByBoardNo(ExpertBoard postno) {
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql+= "SELECT " + 
				"    u.user_id, u.nick, u.is_expert, p.*" + 
				"	FROM user_tb u, expert_post p" + 
				"	WHERE u.user_no = p.user_no" + 
				"		AND post_no = ?" +
				"	ORDER BY post_no DESC";
		
		ExpertBoard result = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체 생성
			ps.setInt(1, postno.getPostno() ); //첫번째 물음표에 매개변수의 empno 삽입
			rs = ps.executeQuery(); //SQL수행 및 결과 반환
			
			while( rs.next() ) { 
				
				result = new ExpertBoard();
				
				result.setPostno(rs.getInt("post_no"));
				result.setTitle(rs.getString("title"));
				result.setUserno(rs.getInt("user_no"));
				result.setUserid(rs.getString("user_id"));
				result.setUsernick(rs.getString("nick"));
				result.setIsexpert(rs.getString("is_expert"));
				result.setContent(rs.getString("content"));
				result.setHit(rs.getInt("hit"));
				result.setWritedate(rs.getDate("write_date"));
				result.setRange(rs.getString("range"));
				result.setGroupno(rs.getInt("group_no"));
				result.setGroupord(rs.getInt("group_ord"));
				result.setDepth(rs.getInt("depth"));
				
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
	public void updateHit(ExpertBoard board) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "UPDATE expert_post SET hit";
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
	public ExpertBoardFile selectFile(ExpertBoard result) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = ""; 
		sql += "SELECT * FROM expert_file";
		sql += " WHERE post_no=?";
		
		//결과 저장할 BoardFile 객체
		ExpertBoardFile boardFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, result.getPostno()); //조회할 boardno 적용
			
			rs = ps.executeQuery();
			
			//조회 결과 처리
			while(rs.next()) {
				boardFile = new ExpertBoardFile();
				
				boardFile.setFileno(rs.getInt("fileno"));
				boardFile.setPostno(rs.getInt("post_no"));
				boardFile.setOriginname(rs.getString("origin_name"));
				boardFile.setStoredname(rs.getString("stored_name"));
				boardFile.setFilesize(rs.getInt("filesize"));
//				boardFile.setWritedate(rs.getDate("writedate"));
				
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
		sql += "SELECT expert_seq.nextval FROM dual";
		
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
	public void insert(ExpertBoard board) {
		conn = JDBCTemplate.getConnection();
		
		String sql = ""; 
		sql += "INSERT INTO expert_post (post_no, board_no, title, user_no, content, hit, range, group_no, depth, group_ord)";
		sql += " VALUES ( ?,4,?,?,?,0,?,?,0,0 )";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, board.getPostno());
			ps.setString(2, board.getTitle());
			ps.setInt(3, board.getUserno());
			ps.setString(4, board.getContent());
			ps.setString(5, board.getRange());
			ps.setInt(6, board.getPostno());
			
			
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	}

	@Override
	public void insertFile(ExpertBoardFile boardFile) {
		conn = JDBCTemplate.getConnection();
		
		String sql = ""; 
		sql += "INSERT INTO expert_file (fileno, post_no, origin_name, stored_name, filesize)";
		sql += " VALUES ( boardfile_seq.nextval,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, boardFile.getPostno());
			ps.setString(2, boardFile.getOriginname());
			ps.setString(3, boardFile.getStoredname());
			ps.setInt(4, boardFile.getFilesize());
			
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
	}
	
	@Override
	public void update(ExpertBoard board) {
		
		System.out.println("ExpertBoardDaoImpl board: " + board);
		
		conn = JDBCTemplate.getConnection();
		
		String sql = ""; 
		sql += "UPDATE expert_post SET title = ?, content = ?";
		sql += " WHERE post_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getPostno());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}

	@Override
	public void deleteFile(ExpertBoard board) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE expert_file";
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
	public void delete(ExpertBoard board) {

		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE expert_post";
		sql += " WHERE post_no = ?";

		//DB 객체
		//				PreparedStatement ps = null; 

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
	public void replyinsert(ExpertBoard board) {
		
		conn = JDBCTemplate.getConnection();
		
		
		String sql = ""; 
		sql += "INSERT INTO expert_post (post_no, board_no, title, user_no, content, hit, range, group_no, depth, group_ord)";
		sql += " VALUES ( ?,4,?,?,?,0,?,?,1,1)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, board.getPostno());
			ps.setString(2, board.getTitle());
			ps.setInt(3, board.getUserno());
			ps.setString(4, board.getContent());
			ps.setString(5, board.getRange());
			ps.setInt(6, board.getGroupno());
			
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
	}

}
