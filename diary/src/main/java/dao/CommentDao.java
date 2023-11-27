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

import vo.*;

public class CommentDao {
	public int insertComment(Comment comment) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			//톰캣 context.xml 설정 로드
			Context context = new InitialContext();
			//context.xml 커넥션 풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			//conn 디버깅
			System.out.println(conn+" <--conn");
			
			String sql = """
					INSERT INTO comment(
						notice_no,
						member_id,
						comment_content,
						is_secret)
					VALUES(
						?,?,?,?)
			""";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, comment.getNoticeNo());
			stmt.setString(2, comment.getMemberId());
			stmt.setString(3, comment.getCommentContent());
			stmt.setString(4, comment.getIsSecret());
			System.out.println(stmt + " <-- stmt");
			row = stmt.executeUpdate();
			if(row != 0) {
				System.out.println("추가 성공");
			}else {
				System.out.println("추가 실패");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	//글쓴이 or member_level > 0
	public int deleteComment(int commentNo, String memberId) {
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
			stmt1.setString(1, memberId);
			rs = stmt1.executeQuery();
			Member m = new Member();
			if(rs.next()) {
				m.setMemberLevel(rs.getInt("memberLevel"));
			}
			if(m.getMemberLevel() == 0) {
				String sql = """
						DELETE FROM comment
						WHERE comment_no = ?
						AND member_id = ?
						""";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, commentNo);
				stmt.setString(2, memberId);
				System.out.println(stmt + " <-- stmt");
				row = stmt.executeUpdate();
				if(row != 0) {
					System.out.println("삭제 성공");
				}else {
					System.out.println("삭제 실패");
				}
			}else {
				String sql = """
						DELETE FROM comment
						WHERE comment_no = ?
						""";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, commentNo);
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
				stmt1.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	//글쓴이만
	public int updateComment(Comment comment) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			//톰캣 context.xml 설정 로드
			Context context = new InitialContext();
			//context.xml 커넥션 풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			//conn 디버깅
			System.out.println(conn+" <--conn");
			
			String sql = """
					UPDATE comment SET
						comment_content = ?,
						is_secret = ?
					WHERE comment_no = ?
					AND member_id = ?
			""";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comment.getCommentContent());
			stmt.setString(2, comment.getIsSecret());
			stmt.setInt(3, comment.getNoticeNo());
			stmt.setString(4, comment.getMemberId());
			System.out.println(stmt + " <-- stmt");
			row = stmt.executeUpdate();
			if(row != 0) {
				System.out.println("수정 성공");
			}else {
				System.out.println("수정 실패");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	//비밀글인 경우: 본인 or member_level > 0 만 조회가능, 대체문자: 비밀글입니다.
	//공개글인 경우: 누구나
	public List<Comment> selectCommentList(Map<String, Object> paramMap){
		List<Comment> list = new ArrayList<>();
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
							comment_no commentNo,
							member_id memberId,
							comment_content commentContent,
							createdate,
							is_secret isSecret
					FROM comment
					WHERE notice_no = ?
					ORDER BY comment_no DESC
					LIMIT ?,?
					""";
			stmt= conn.prepareStatement(sql);
			stmt.setObject(1, paramMap.get("noticeNo"));
			stmt.setObject(2, paramMap.get("beginCommentRow"));
			stmt.setObject(3, paramMap.get("rowPerCommentPage"));
			System.out.println(stmt + "  <---stmt CommentList");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Comment c = new Comment();
				c.setNoticeNo(rs.getInt("noticeNo"));
				c.setCommentNo(rs.getInt("commentNo"));
				c.setMemberId(rs.getString("memberId"));
				c.setCommentContent(rs.getString("commentContent"));
				c.setCreatedate(rs.getString("createdate"));
				c.setIsSecret(rs.getString("isSecret"));
				list.add(c);
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
	public int commentCnt(int noticeNo) {
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
					FROM comment
					WHERE notice_no = ?
					""";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, noticeNo);
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
	public Comment selectComment(int commentNo) {
		Comment comment = new Comment();
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
					SELECT comment_no commentNo,
							comment_content commentContent,
							is_secret isSecret
					FROM comment
					WHERE comment_no = ?
					""";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, commentNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				comment.setCommentNo(rs.getInt("commentNo"));
				comment.setCommentContent(rs.getString("commentContent"));
				comment.setIsSecret(rs.getString("isSecret"));
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
		return comment;
	}
}
