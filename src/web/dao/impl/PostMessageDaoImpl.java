package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.PostMessageDao;
import web.dbutil.JDBCTemplate;
import web.dto.PostMessage;
import web.dto.UserTB;
import web.service.face.PostMessageService;
import web.util.Paging;

public class PostMessageDaoImpl implements PostMessageDao{

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int selectCntAllByUserno(UserTB currUser) {
		
		// DB 연결
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM post_message";
		sql += " WHERE pm_recipient_id = ?";
		sql += " ORDER BY pm_no DESC";
		
		// 결과 저장 변수 선언
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, currUser.getUserNo());
			
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
	public List<PostMessage> selectPmByRecipientNo(Paging paging, UserTB currUser) {
		
		// DB 연결 객체
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	    SELECT rownum rnum, P.* FROM (";
		sql += "	        SELECT * FROM post_message";
		sql += "	        WHERE pm_recipient_id = ?";
		sql += "	        ORDER BY pm_no DESC";
		sql += "	    ) P";
		sql += "	) PM";
		sql += "	WHERE rnum BETWEEN ? AND ?";
		
		
		// 결과 반환 리스트
		List<PostMessage> pmList = new ArrayList<PostMessage>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, currUser.getUserNo());
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PostMessage pm = new PostMessage();
				pm.setPmNo(rs.getInt("pm_no"));
				pm.setPmSenderId(rs.getInt("pm_sender_id"));
				pm.setTitle(rs.getString("title"));
				pm.setContent(rs.getString("content"));
				pm.setSendDate(rs.getDate("send_date"));
				pm.setPmRecipientId(rs.getInt("pm_recipient_id"));
				pm.setIsCheck(rs.getString("is_check"));
				
				pmList.add(pm);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return pmList;
	}
	
	@Override
	public PostMessage selectPMByNo(PostMessage pm) {
		
		// DB 연결 객체
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM post_message";
		sql += " WHERE pm_no = ?";
		
		// 결과 반환 객체
		PostMessage result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pm.getPmNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result = new PostMessage();
				result.setPmNo(rs.getInt("pm_no"));
				result.setPmSenderId(rs.getInt("pm_sender_id"));
				result.setTitle(rs.getString("title"));
				result.setContent(rs.getString("content"));
				result.setSendDate(rs.getDate("send_date"));
				result.setPmRecipientId(rs.getInt("pm_recipient_id"));
				result.setIsCheck(rs.getString("is_check"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
	
	@Override
	public UserTB selectPmSender(PostMessage pm) {
		
		// DB 연결 객체
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM user_tb";
		sql += " WHERE user_no = ? ";
		
		// 결과 반환 객체
		UserTB result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pm.getPmSenderId());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result = new UserTB();
				result.setUserNo(rs.getInt("user_no"));
				result.setUserId(rs.getString("user_id"));
				result.setUserName(rs.getString("user_name"));
				result.setBirth(rs.getDate("birth"));
				result.setGender(rs.getString("gender").charAt(0));
				result.setNick(rs.getString("nick"));
				result.setEmail(rs.getString("email"));
				result.setTel(rs.getString("tel"));
				result.setFirstAddr(rs.getString("first_addr"));
				result.setSecondAddr(rs.getString("second_addr"));
				result.setAnimal(rs.getString("animal"));
				result.setIsExpert(rs.getString("is_expert"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public void updateIsChk(PostMessage pm) {
		// DB 연결
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "UPDATE post_message";
		sql += " SET";
		sql += "    is_check = 'checked'";
		sql += " WHERE pm_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pm.getPmNo());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}
	
	@Override
	public void insert(PostMessage pm) {
		
		// DB 연결
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check)";
		sql += " VALUES ( post_message_seq.nextval, ?, ?, ?, sysdate, ?, 'unchecked')";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pm.getPmSenderId());
			ps.setString(2, pm.getTitle());
			ps.setString(3, pm.getContent());
			ps.setInt(4, pm.getPmRecipientId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}
	
	@Override
	public void delete(PostMessage pm) {
		
		// DB 연결 객체
		conn = JDBCTemplate.getConnection();
		
		// SQL 작성
		String sql = "";
		sql += "DELETE FROM post_message";
		sql += " WHERE pm_no = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pm.getPmNo());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}
	
	@Override
	public void delete(String names) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql +="DELETE post_message";
		sql +=" WHERE pm_no IN(" +names+" )";

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
	public int selectCntAllByPMSenderId(UserTB currUser) {
		// DB 연결
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM post_message";
		sql += " WHERE pm_sender_id = ?";
		sql += " ORDER BY pm_no DESC";

		// 결과 저장 변수 선언
		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, currUser.getUserNo());

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
	public List<PostMessage> selectPmBySenderNo(Paging paging, UserTB currUser) {
		// DB 연결 객체
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	    SELECT rownum rnum, P.* FROM (";
		sql += "	        SELECT * FROM post_message";
		sql += "	        WHERE pm_sender_id = ?";
		sql += "	        ORDER BY pm_no DESC";
		sql += "	    ) P";
		sql += "	) PM";
		sql += "	WHERE rnum BETWEEN ? AND ?";

		
		// 결과 반환 리스트
		List<PostMessage> pmSendList = new ArrayList<PostMessage>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, currUser.getUserNo());
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());

			rs = ps.executeQuery();

			while(rs.next()) {
				PostMessage pm = new PostMessage();
				pm.setPmNo(rs.getInt("pm_no"));
				pm.setPmSenderId(rs.getInt("pm_sender_id"));
				pm.setTitle(rs.getString("title"));
				pm.setContent(rs.getString("content"));
				pm.setSendDate(rs.getDate("send_date"));
				pm.setPmRecipientId(rs.getInt("pm_recipient_id"));
				pm.setIsCheck(rs.getString("is_check"));

				pmSendList.add(pm);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return pmSendList;
	}
	
	@Override
	public UserTB selectPmRecipient(PostMessage pm) {
		// DB 연결 객체
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM user_tb";
		sql += " WHERE user_no = ? ";

		// 결과 반환 객체
		UserTB result = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pm.getPmRecipientId());

			rs = ps.executeQuery();

			while(rs.next()) {
				result = new UserTB();
				result.setUserNo(rs.getInt("user_no"));
				result.setUserId(rs.getString("user_id"));
				result.setUserName(rs.getString("user_name"));
				result.setBirth(rs.getDate("birth"));
				result.setGender(rs.getString("gender").charAt(0));
				result.setNick(rs.getString("nick"));
				result.setEmail(rs.getString("email"));
				result.setTel(rs.getString("tel"));
				result.setFirstAddr(rs.getString("first_addr"));
				result.setSecondAddr(rs.getString("second_addr"));
				result.setAnimal(rs.getString("animal"));
				result.setIsExpert(rs.getString("is_expert"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
}
