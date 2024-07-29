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
	public int update(Board board) throws SQLException {
		int result = 0;
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		String			  sql   = "Update board set subject=?,writer=?,email=?,passwd=?,content=? where num=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,board.getSubject());
			pstmt.setString(2,board.getWriter());
			pstmt.setString(3,board.getEmail());
			pstmt.setString(4,board.getPasswd());
			pstmt.setString(5,board.getContent());
			pstmt.setInt(6, board.getNum());
			result = pstmt.executeUpdate();

			if(result>0) result = 1;
			else 	     result = 0;
		} catch (Exception e) {
			System.out.println("updateDao e.getMessage : "+e.getMessage());
			// TODO: handle exception
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn  != null) conn.close();
		}
		return result;
	} 
	
	   public int insert(Board board) throws SQLException {
		      Connection conn = null;
		      PreparedStatement pstmt = null;
		      int result = board.getNum();
		      int num = 0;
		      ResultSet rs = null;
		      // 신규글 pk
		      String sql1 = "SELECT nvl(MAX(num),0) FROM board";
		      // 신규 + 댓글 공통 실행
		      String sql3 = "INSERT INTO board VALUES(?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		      //홍해의 기적 : 
		      String sql2 ="update board set re_step = re_step+1 where ref=? and re_step>?";
		 
		      
		      System.out.println("DAO insert sql1->"+sql1);
		      
		      
		      try {
		    	  //sql1
		         conn = getConnection();
		         pstmt = conn.prepareStatement(sql1);
		         rs = pstmt.executeQuery();
		         rs.next();
		         //key인 num 1씩 증가, mysql auto_increment 또는 oracle sequence
		         //sequence를 사용 : values(시퀀스명(board_seq).nextval,?,?...)
		         int number = rs.getInt(1)+1;
		         rs.close();
		         pstmt.close();
		         
		         //sql2
		         if (num !=0) {
		        	 System.out.println("BoardDAO insert 댓글 sql2=>" + sql2);
		        	 System.out.println("BoardDAO insert 댓글 board.getRef()=>" + board.getRef());
		        	 System.out.println("BoardDAO insert 댓글 board.getRe_step()=>" + board.getRe_step());
		        	 pstmt = conn.prepareStatement(sql1);
		        	 pstmt.setInt(1, board.getRef());
		        	 pstmt.setInt(2, board.getRe_step());
		        	 pstmt.executeUpdate();
		        	 pstmt.close();
		        	 
		        	 board.setRe_step(board.getRe_step()+1);
		        	 board.setRe_level(board.getRe_level()+1);
		         }
		         
		         //sql3
		         if (num == 0) board.setRef(number);
		         pstmt = conn.prepareStatement(sql3);
		         pstmt.setInt(1, number);
		         pstmt.setString(2, board.getWriter());
		         pstmt.setString(3, board.getSubject());
		         pstmt.setString(4, board.getContent());
		         pstmt.setString(5, board.getEmail());
		         pstmt.setInt(6, board.getReadcount());
		         pstmt.setString(7, board.getPasswd());
		         pstmt.setInt(8, board.getRef());
		         pstmt.setInt(9, board.getRe_step());
		         pstmt.setInt(10, board.getRe_level());
		         pstmt.setString(11, board.getIp());
		         
		         result = pstmt.executeUpdate();
		    	 System.out.println("DAO insert result->"+result);
	         
		      } catch (Exception e) {
		         System.out.println(e.getMessage());
		      } finally {
		         if (rs != null)      rs.close();
		         if (pstmt != null)   pstmt.close();
		         if (conn != null)   conn.close();
		      }
		      return result;
		   } 
	   
	   public int delete(Board board) throws SQLException
	   		{
		   int result = 0;

		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   String sql = "Delete From Board Where num=? AND passwd=?";
		   
		   try {
			   
			   conn = getConnection();
			   pstmt= conn.prepareStatement(sql);
			   pstmt.setInt(1, board.getNum());
			   pstmt.setString(2, board.getPasswd());
			   
			   result = pstmt.executeUpdate();
			   
			   if(result>0) result = 1;
			   else			result = 0;

		} catch (Exception e) {
			System.out.println("BoardDao delete e.getMessage => " + e.getMessage());
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn  != null) conn.close();
		}
		   
		   return result;
	   }

}
