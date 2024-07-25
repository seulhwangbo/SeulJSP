package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardDao {
	private static BoardDao instance;
	private BoardDao() {
		
	}
	public static BoardDao getInstance() {
		if(instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		
		try {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
		conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}			
		return conn;
	}
	
	public int getTotalCnt() throws SQLException {
			int tot = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "select COUNT(*) From board";
			ResultSet rs = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) tot = rs.getInt(1);
			} catch (Exception e) {
			  System.out.println(e.getMessage());
			} finally {
				rs.close();
				conn.close();
				pstmt.close();
			}					
			return tot;
	}
	
	public List<Board> boardList(int startRow, int endRow) throws SQLException{
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		 String sql = "SELECT *  "
		 	    	+ "FROM (Select rownum rn ,a.*  "
		 		    + "From (select * from board order by ref desc,re_step) a ) "
		 		    + "WHERE rn BETWEEN ? AND ? " ;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs    = pstmt.executeQuery();
			while(rs.next()){
					Board board = new Board();
					board.setNum(rs.getInt("num"));
					board.setWriter(rs.getString("writer"));
					board.setSubject(rs.getString("subject"));
					board.setEmail(rs.getString("email"));
					board.setReadcount(rs.getInt("readCount"));
					board.setIp(rs.getString("ip"));
					board.setRef(rs.getInt("ref"));
					board.setRe_level((rs.getInt("re_level")));					
					board.setRe_step(rs.getInt("re_step"));					
					board.setReg_date(rs.getDate("reg_date"));					
					list.add(board);
				} 
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		} finally {
			if(rs != null) 		rs.close();
			if(conn != null) 	conn.close();
			if(pstmt != null) 	pstmt.close();
			
		}
		return list;
	}
	
	public Board select(int num) throws SQLException{
		Connection 		  conn  = null;
		Statement 		  stmt  = null;
		ResultSet		  rs	= null;
		String 			  sql   = "Select * From board where num=" + num;
		Board board = new Board();
		try {
			conn 	= getConnection();
			stmt 	= conn.createStatement();
			rs		= stmt.executeQuery(sql);
			if(rs.next()) {
				board.setNum(rs.getInt("num"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setEmail(rs.getString("email"));
				board.setContent(rs.getString("content"));
				board.setReadcount(rs.getInt("readCount"));
				board.setIp(rs.getString("ip"));
				board.setRef(rs.getInt("ref"));
				board.setRe_level((rs.getInt("re_level")));					
				board.setRe_step(rs.getInt("re_step"));					
				board.setReg_date(rs.getDate("reg_date"));								
			}
		} catch (Exception e) {
				System.out.println(e.getMessage());		
		} finally {
			if(rs 	 !=null) rs.close();
			if(stmt  !=null)  stmt.close();
			if(conn  !=null) conn.close();
		}
		return board;
	}
	public void readCount(int num) throws SQLException {
		Connection conn = null;
		PreparedStatement  pstmt = null;
		String 	   sql  = "Update board set readcount=readcount+1 where num =?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(conn !=null) conn.close();
			if(pstmt !=null) pstmt.close();
		}
	
 }
}
