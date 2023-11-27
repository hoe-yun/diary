package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.Member;
import vo.Notice;

public class NoticeDao {
	//member_level > 0
	public int insertNotice(Notice notice) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		try {
			//톰캣 context.xml 설정 로드
			Context context = new InitialContext();
			//context.xml 커넥션 풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			//conn 디버깅
			System.out.println(conn+" <--conn");
			
			String sql1 = """
					SELECT member_level memberLevel
					FROM member
					WHERE member_id = ?
					""";
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, notice.getMemberId());
			System.out.println(stmt1 + " <-- stmt1");
			rs = stmt1.executeQuery();
			Member m = new Member();
			if(rs.next()) {
				m.setMemberLevel(rs.getInt("memberLevel"));
			}
			System.out.println(m.getMemberLevel() + "   <-----level");
			if(m.getMemberLevel() != 0) {
				String sql = """
						INSERT INTO notice(
							member_id,
							notice_title,
							notice_content,
							notice_pw)
						VALUES(
							?,?,?,PASSWORD(?))
				""";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, notice.getMemberId());
				stmt.setString(2, notice.getNoticeTitle());
				stmt.setString(3, notice.getNoticeContent());
				stmt.setString(4, notice.getNoticePw());
				System.out.println(stmt + " <-- stmt");
				row = stmt.executeUpdate();
				if(row != 0) {
					System.out.println("추가 성공");
				}else {
					System.out.println("추가 실패");
				}
				stmt.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt1.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	// 
	public Notice noticeOne(int noticeNo) {
		Notice n = new Notice();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//톰캣 context.xml 설정 로드
			Context context = new InitialContext();
			//context.xml 커넥션 풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			//conn 디버깅
			System.out.println(conn+" <--conn");
			
			String sql ="""
					SELECT notice_no noticeNo,
							member_id memberId,
							notice_title noticeTitle,
							notice_content noticeContent,
							createdate
					FROM notice
					WHERE notice_no = ?
					""";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,noticeNo);
			rs = stmt.executeQuery();
			while(rs.next()) {
				n.setNoticeNo(rs.getInt("noticeNo"));
				n.setMemberId(rs.getString("memberId"));
				n.setNoticeTitle(rs.getString("noticeTitle"));
				n.setNoticeContent(rs.getString("noticeContent"));
				n.setCreatedate(rs.getString("createdate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return n;
	}
	//
	public List<Notice> selectNoticeList(Map<String, Object> paramMap){
		List<Notice> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//톰캣 context.xml 설정 로드
			Context context = new InitialContext();
			//context.xml 커넥션 풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			//conn 디버깅
			System.out.println(conn+" <--conn");
			
			String sql ="""
					SELECT notice_no noticeNo,
							member_id memberId,
							notice_title noticeTitle,
							notice_content noticeContent,
							createdate
					FROM notice
					ORDER BY notice_no DESC
					LIMIT ?,?
					""";
			stmt= conn.prepareStatement(sql);
			stmt.setObject(1, paramMap.get("beginRow"));
			stmt.setObject(2, paramMap.get("rowPerPage"));
			System.out.println(stmt+" <-- stmt noticeList");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rs.getInt("noticeNo"));
				n.setMemberId(rs.getString("memberId"));
				n.setNoticeTitle(rs.getString("noticeTitle"));
				n.setNoticeContent(rs.getString("noticeContent"));
				n.setCreatedate(rs.getString("createdate"));
				list.add(n);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	//member_level > 0 and pw일치
	public int updateNotice(Notice notice) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		try {
			//톰캣 context.xml 설정 로드
			Context context = new InitialContext();
			//context.xml 커넥션 풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			//conn 디버깅
			System.out.println(conn+" <--conn");
			
			String sql1 = """
					SELECT member_level memberLevel
					FROM member
					WHERE member_id = ?
					""";
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, notice.getMemberId());
			System.out.println(stmt1 + " <-- stmt1");
			rs = stmt1.executeQuery();
			Member m = new Member();
			if(rs.next()) {
				m.setMemberLevel(rs.getInt("memberLevel"));
			}
			System.out.println(m.getMemberLevel()+" <-level");
			if(m.getMemberLevel() != 0) {
				String sql = """
						UPDATE notice SET
							notice_title = ?,
							notice_content = ?
						WHERE notice_no = ?
						AND notice_pw = PASSWORD(?)
				""";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, notice.getNoticeTitle());
				stmt.setString(2, notice.getNoticeContent());
				stmt.setInt(3, notice.getNoticeNo());
				stmt.setString(4, notice.getNoticePw());
				System.out.println(stmt + " <-- stmt");
				row = stmt.executeUpdate();
				if(row != 0) {
					System.out.println("수정 성공");
				}else {
					System.out.println("수정 실패");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	//member_level > 0 and pw일치
	public int deleteNotice(Notice notice) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		try {
			//톰캣 context.xml 설정 로드
			Context context = new InitialContext();
			//context.xml 커넥션 풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			//conn 디버깅
			System.out.println(conn+" <--conn");
			
			String sql1 = """
					SELECT member_level memberLevel
					FROM member
					WHERE member_id = ?
					""";
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, notice.getMemberId());
			rs = stmt1.executeQuery();
			Member m = new Member();
			if(rs.next()) {
				m.setMemberLevel(rs.getInt("memberLevel"));
			}
			if(m.getMemberLevel() != 0) {
				String sql = """
						DELETE FROM notice
						WHERE notice_no = ?
						AND notice_pw = PASSWORD(?)
						""";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, notice.getNoticeNo());
				stmt.setString(2, notice.getNoticePw());
				System.out.println(stmt + " <-- stmt");
				row = stmt.executeUpdate();
				if(row != 0) {
					System.out.println("삭제 성공");
				}else {
					System.out.println("삭제 실패");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	public int noticeCNT() {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//톰캣 context.xml 설정 로드
			Context context = new InitialContext();
			//context.xml 커넥션 풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			//conn 디버깅
			System.out.println(conn+" <--conn");
			
			String sql = """
					SELECT COUNT(*)
					FROM notice
					""";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt("COUNT(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}
