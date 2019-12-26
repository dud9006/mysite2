package com.bigdata2019.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bigdata2019.mysite.vo.BoardVo;

public class BoardDao {

	public List<BoardVo> findAll() {

		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. Statement 객체 생성

			// 4. SQL문 실행
			String sql = "select no," + "       title," + "       contents," + "  from board";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery(sql);

			// 5. 결과 가져오기
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);

				list.add(vo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 6.자원 정리
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public Boolean insert(BoardVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			System.out.println("Check_1");
			// 3. Statement 객체 생성

			// 4. SQL문 실행
			String sql = " insert" + "  into board  values (null, ?, ?, now(), 0,  (select IF(max(g_no) is null, 0, max(g_no)) from board a)+1 , 1, 0, ?)";
			System.out.println("Check_2");
			pstmt = conn.prepareStatement(sql);
			System.out.println("Check_3");
			pstmt.setString(1,vo.getTitle());
			pstmt.setString(2,vo.getContents());
			pstmt.setLong(3,vo.getUserNo());
			System.out.println("Check_4");
			
			System.out.println(pstmt);
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
			System.out.println("Check_5");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	   
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		// 1. JDBC Driver(Mysql) 로딩
		Class.forName("com.mysql.jdbc.Driver");

		// 2. 연결하기
		String url = "jdbc:mysql://localhost:3306/webdb";
		Connection conn = DriverManager.getConnection(url, "webdb", "webdb");

		return conn;
	}
}