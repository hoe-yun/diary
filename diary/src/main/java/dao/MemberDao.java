package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.Member;

public class MemberDao {
	public int insertMember(Member member) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		//커넥션 풀
		try {
			//톰캣 context.xml 설정 로드
			Context context = new InitialContext();
			//context.xml 커넥션 풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			//conn 디버깅
			System.out.println(conn+" <--conn");
			
			String sql = "INSERT INTO member (member_id,member_pw) VALUES (?,PASSWORD(?))";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			row = stmt.executeUpdate();
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
	public Member loginMember(Member paramMember) {
		Member resultMember = new Member();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//커넥션 풀
		try {
			//톰캣 context.xml 설정 로드
			Context context = new InitialContext();
			//context.xml 커넥션 풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			//conn 디버깅
			System.out.println(conn+" <--conn");
			
			String sql = "SELECT member_no memberNo,member_id memberId FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			rs = stmt.executeQuery();
			while(rs.next()) {
				resultMember.setMemberNo(rs.getInt("memberNo"));
				resultMember.setMemberId(rs.getString("memberId"));
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
		
		return resultMember;	//로그인 실패 시 null 성공 시 memberNo속성 memberId속성 출력
	}
	public int modifyPwMember(String memberId, String newMemberPw, String memberPw) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		//커넥션 풀
			try {
				//톰캣 context.xml 설정 로드
				Context context = new InitialContext();
				//context.xml 커넥션 풀 객체 로드
				DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
				conn = ds.getConnection();
				//conn 디버깅
				System.out.println(conn+" <--conn");
				
				String sql = "UPDATE member SET member_pw = PASSWORD(?) WHERE member_id = ? AND member_pw = PASSWORD(?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, newMemberPw);
				stmt.setString(2, memberId);
				stmt.setString(3, memberPw);
				row = stmt.executeUpdate();
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
	public int removeMember(String memberId, String memberPw) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		//커넥션 풀
			try {
				//톰캣 context.xml 설정 로드
				Context context = new InitialContext();
				//context.xml 커넥션 풀 객체 로드
				DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
				conn = ds.getConnection();
				//conn 디버깅
				System.out.println(conn+" <--conn");
				
				String sql = "DELETE FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, memberId);
				stmt.setString(2, memberPw);
				row = stmt.executeUpdate();
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
}