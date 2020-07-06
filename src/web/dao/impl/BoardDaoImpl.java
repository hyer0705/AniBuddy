package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.BoardDao;
import web.dbutil.JDBCTemplate;
import web.dto.Email;
import web.dto.ExpertBoard;
import web.dto.FreeBoard;
import web.dto.HelpPost;
import web.dto.SharePost;
import web.dto.UserID;
import web.util.Paging;

public class BoardDaoImpl implements BoardDao{

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
	public List<UserID> selectShare(Paging paging) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, O.* FROM (";
		sql += "        SELECT * FROM share_post";
		sql += "        WHERE 1=1";
		if( paging.getSearch() != null && !"".equals(paging.getSearch())) {
		sql += "		AND title LIKE '%'||?||'%'";
		}
		sql += "        ORDER BY write_date DESC, post_no DESC";
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
	public List<ExpertBoard> selectExpert(Paging paging) {
		
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, O.* FROM (";
		sql += "        SELECT * FROM expert_post";
		sql += "        WHERE 1=1";
		if( paging.getSearch() != null && !"".equals(paging.getSearch())) {
		sql += "		AND title LIKE '%'||?||'%'";
		}
		sql += "        ORDER BY write_date DESC, post_no DESC";
		sql += "    ) O";
		sql += " ) One";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<ExpertBoard> list = new ArrayList<ExpertBoard>();

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

				ExpertBoard expert = new ExpertBoard();
				
				expert.setPostno(rs.getInt("post_no"));
				expert.setBoardno(rs.getInt("board_no"));
				expert.setTitle(rs.getString("title"));
				expert.setContent(rs.getString("content"));
				expert.setWritedate(rs.getDate("write_date"));
				expert.setHit(rs.getInt("hit"));
				expert.setRange(rs.getString("range"));
				expert.setGroupno(rs.getInt("group_no"));
				expert.setGroupord(rs.getInt("group_ord"));
				expert.setDepth(rs.getInt("depth"));
				expert.setUserno(rs.getInt("user_no"));
				
				
				list.add(expert);
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
	public List<FreeBoard> selectFree(Paging paging) {
		
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, O.* FROM (";
		sql += "        SELECT * FROM free_post";
		sql += "        WHERE 1=1";
		if( paging.getSearch() != null && !"".equals(paging.getSearch())) {
		sql += "		AND title LIKE '%'||?||'%'";
		}
		sql += "        ORDER BY write_date DESC, post_no DESC";
		sql += "    ) O";
		sql += " ) One";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<FreeBoard> list = new ArrayList<FreeBoard>();

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

				FreeBoard free = new FreeBoard();
				
				free.setPostno(rs.getInt("post_no"));
				free.setBoardno(rs.getInt("board_no"));
				free.setTitle(rs.getString("title"));
				free.setContent(rs.getString("content"));
				free.setWritedate(rs.getDate("write_date"));
				free.setHit(rs.getInt("hit"));
				free.setRange(rs.getString("range"));
				free.setUserno(rs.getInt("user_no"));
				
				list.add(free);
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
	public void deleteShare(String param) {
		
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql +="DELETE share_post";
		sql +=" WHERE post_no IN("+param+" )";

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
	public void deleteExpert(String param) {
		
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql +="DELETE expert_post";
		sql +=" WHERE post_no IN("+param+" )";

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
	public void deleteFree(String param) {
		
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql +="DELETE free_post";
		sql +=" WHERE post_no IN("+param+" )";

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
	public int selectCntBySearch(String search) {
		
		// DB 연결 객체
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM SHARE_POST";
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

	@Override
	public int selectCntExpert() {
		conn = JDBCTemplate.getConnection(); //DB연결
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM expert_POST";
		
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
	public int selectCntExpert(String search) {
		conn = JDBCTemplate.getConnection(); //DB연결
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM expert_post";
		sql += " WHERE title LIKE '%'||?||'%'";
		
		//최종 결과값
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setString(1, search);
			
			
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
	public int selectCntFree() {
		conn = JDBCTemplate.getConnection(); //DB연결
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM free_POST";
		
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
	public int selectCntFree(String search) {
		conn = JDBCTemplate.getConnection(); //DB연결
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM free_POST";
		sql += " WHERE title LIKE '%'||?||'%'";
		
		//최종 결과값
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setString(1, search);
			
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
	public int selectCntHelp(String search) {
		conn = JDBCTemplate.getConnection(); //DB연결
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM help_POST";
		sql += " WHERE title LIKE '%'||?||'%'";
		
		//최종 결과값
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setString(1, search);
			
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
	public int selectCntHelp() {
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
	public List<HelpPost> selectHelp(Paging paging) {
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

		List<HelpPost> list = new ArrayList<>();

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

				HelpPost help = new HelpPost();
				
				help.setPostNo(rs.getInt("post_no"));
				help.setBoardNo(rs.getInt("board_no"));
				help.setTitle(rs.getString("title"));
				help.setContent(rs.getString("content"));
				help.setWriteDate(rs.getDate("write_date"));
				help.setHit(rs.getInt("hit"));
				help.setDealProgress(rs.getString("deal_progress"));
				help.setUserNo(rs.getInt("user_no"));
				
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
	public void deleteHelp(String param) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql +="DELETE help_post";
		sql +=" WHERE post_no IN("+param+" )";

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
	public List<Email> selectMail(Paging paging) {
		conn = JDBCTemplate.getConnection();

	      String sql = "";
	      sql += "SELECT * FROM (";
	      sql += "    SELECT rownum rnum, O.* FROM (";
	      sql += "        SELECT * FROM email";
	      sql += "        WHERE 1=1";
	      if( paging.getSearch() != null && !"".equals(paging.getSearch())) {
	      sql += "      AND title LIKE '%'||?||'%'";
	      }
	      sql += "        ORDER BY email_no DESC";
	      sql += "    ) O";
	      sql += " ) One";
	      sql += " WHERE rnum BETWEEN ? AND ?";

	      List<Email> list = new ArrayList<Email>();

	      try {
	         ps = conn.prepareStatement(sql);

	         int idx = 1;
	         if( paging.getSearch() != null && !"".equals(paging.getSearch())) {
	            ps.setString(idx++, paging.getSearch());   
	         }
	         ps.setInt(idx++, paging.getStartNo());   //페이징 게시글 시작 번호
	         ps.setInt(idx++, paging.getEndNo());   //페이징 게시글 끝 번호


	         rs = ps.executeQuery();

	         while(rs.next()) {

	            Email email = new Email();
	            
	            email.setEmailno(rs.getInt("email_no"));
	            email.setTitle(rs.getString("title"));
	            email.setContent(rs.getString("content"));
	            email.setIsall(rs.getString("isall"));
	            email.setWritedate(rs.getDate("write_date"));
	            
	            list.add(email);
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
	public int selectCntMailSearch(String search) {
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM email";
		sql += " WHERE 1=1";
		sql += "    AND title LIKE '%'||?||'%'";
		sql += " ORDER BY write_date DESC, email_no DESC";
		
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

	@Override
	public int selectCntMail() {
		conn = JDBCTemplate.getConnection(); //DB연결
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM email";
		
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
	
}









