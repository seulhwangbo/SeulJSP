package och15_ajax;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//Singleton + DBCP
public class MemberDao {
	// Singleton ==> resource 절감
	private static MemberDao instance;
	private MemberDao() {
		// default
	}
	
	public static MemberDao getInstance() {
	 if(instance == null) {
		 instance = new MemberDao();
	 }
	 // 한 사람이 두 번 세 번 인스턴스를 중복해서 만들지 않게 하기 위해서 
	 // 인스턴스가 0일 때만 생성이 된다
		return instance;
	}
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
		conn = ds.getConnection();
		}catch(NamingException e){
			e.printStackTrace();
			
		}
		return conn;
		
	}
	public int confirm(String id) throws SQLException {
		int result = 1;
		Connection conn = null;
		String sql = "Select id From member1 where id = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) result = 1;
			else 	      result = 0;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs !=null) 	  rs.close();
			if (pstmt !=null) pstmt.close();
			if (conn !=null)  conn.close();
		}
		return result;
		
	}

}

