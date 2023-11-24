package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.Schedule;

public class ScheduleDao {
	public List<Map<String, Object>> selelctScheduleByMonth(String memberId, int year, int month) {
		List<Map<String, Object>> list = new ArrayList<>();
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
			
			String sql = """
					SELECT
						DAY(schedule_date) scheduleDay,
						COUNT(*) cnt,
						GROUP_CONCAT(substr(schedule_memo, 1, 5),'...') memo
					FROM schedule
					WHERE member_id = ? 
					AND YEAR(schedule_date) = ?
					AND MONTH(schedule_date) = ?
					GROUP BY schedule_date
					ORDER BY schedule_date ASC
			""";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, year);
			stmt.setInt(3, month+1);
			System.out.println(stmt + " <-- stmt");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("scheduleDay", rs.getString("scheduleDay"));
				m.put("cnt", rs.getInt("cnt"));
				m.put("memo", rs.getString("memo"));
				list.add(m);
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
		return list;
		
	}
	
	public List<Schedule> selectScheduleByDay(String memberId, String scheduleDate){
		List<Schedule> list = new ArrayList<>();
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
			
			String sql = """
					SELECT
						schedule_no scheduleNo,
						schedule_memo scheduleMemo
					FROM schedule
					WHERE member_id = ? 
					AND schedule_date = ?
			""";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, scheduleDate);
			System.out.println(stmt + " <-- stmt");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Schedule s = new Schedule();
				s.setScheduleNo(rs.getInt("scheduleNo"));
				s.setScheduleMemo(rs.getString("scheduleMemo"));
				list.add(s);
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
		return list;
		
	}
	public List<Schedule> selectScheduleOne(int scheduleNo){
		List<Schedule> list = new ArrayList<>();
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
			
			String sql = """
					SELECT
						schedule_no scheduleNo,
						schedule_date scheduleDate,
						schedule_memo scheduleMemo,
						schedule_emoji scheduleEmogi
					FROM schedule
					WHERE schedule_no = ?
			""";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, scheduleNo);
			System.out.println(stmt + " <-- stmt");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Schedule s = new Schedule();
				s.setScheduleNo(rs.getInt("scheduleNo"));
				s.setScheduleDate(rs.getString("scheduleDate"));
				s.setScheduleMemo(rs.getString("scheduleMemo"));
				s.setScheduleEmoji(rs.getString("scheduleEmogi"));
				list.add(s);
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
		return list;
		
	}
	
	public int modifySchedule(int scheduleNo,String scheduleDate,String scheduleMemo,String scheduleEmoji) {
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
					UPDATE schedule SET
						schedule_date = ?,
						schedule_memo = ?,
						schedule_emoji = ?
					WHERE schedule_no = ?
			""";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, scheduleDate);
			stmt.setString(2, scheduleMemo);
			stmt.setString(3, scheduleEmoji);
			stmt.setInt(4, scheduleNo);
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
	
	public int addSchedule(String memberId, String scheduleDate, String scheduleMemo, String scheduleEmoji) {
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
					INSERT INTO schedule(
						member_id,
						schedule_date,
						schedule_memo,
						schedule_emoji)
					VALUES(
						?,?,?,?)
			""";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, scheduleDate);
			stmt.setString(3, scheduleMemo);
			stmt.setString(4, scheduleEmoji);
			System.out.println(stmt + " <-- stmt");
			row = stmt.executeUpdate();
			if(row != 0) {
				System.out.println("입력 성공");
			}else {
				System.out.println("입력 실패");
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
	
	public int removeSchedule(int scheduleNo) {
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
					DELETE FROM schedule
					WHERE schedule_no = ?
					""";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, scheduleNo);
			System.out.println(stmt + " <-- stmt");
			row = stmt.executeUpdate();
			if(row != 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
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
}
