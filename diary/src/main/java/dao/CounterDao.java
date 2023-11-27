package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.Counter;

public class CounterDao {
	// 오늘 날짜의 카운트가 존재하는지
	// 오늘 접속자 수 출력
	public Counter selectCntByToday() {
		Counter cnt = new Counter();
		//SELECT * FROM counter WHERE cnt_date = CURDATE()
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
					SELECT cnt_date cntDate,
							cnt_num cntNum
					FROM counter
					WHERE cnt_date = CURDATE()
					""";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				cnt.setCntNum(rs.getInt("cntNum"));
				cnt.setCntDate(rs.getString("cntDate"));
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
		return cnt;
	}
	
	//selectCntByToday() 결과가 없을 때 insert
	public int insertCnt() {
		int row = 0;
		//INSERT INTO counter(cnt_date, cnt_num) VALUES (CURDATE(),1)
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
					INSERT INTO counter(
						cnt_date,
						cnt_num)
					VALUES(
						CURDATE(),1)
			""";
			stmt = conn.prepareStatement(sql);
			System.out.println(stmt + " <-- stmt");
			row = stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	//selectCntByToday() 결과가 있을 때 update
	public int updateCnt() {
		int row = 0;
		//UPDATE counter SET cnt_num = cnt_num + 1 WHERE cnt_date = CURDATE()
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
					UPDATE counter SET
							cnt_num = cnt_num + 1
						WHERE cnt_date = CURDATE()
			""";
			stmt = conn.prepareStatement(sql);
			System.out.println(stmt + " <-- stmt");
			row = stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	//누적 접속자 수 
	public int selectCntSum() {
		int row = 0;
		//SELECT SUM(cnt_num) FROM counter
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
					SELECT SUM(cnt_num)
					FROM counter
					""";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt("SUM(cnt_num)");
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

