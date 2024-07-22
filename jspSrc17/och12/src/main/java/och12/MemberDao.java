package och12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.cj.protocol.Resultset;

public class MemberDao {
	// singleton + dbcp 
	
	private static MemberDao instance;
	private MemberDao() {
	}
	
	public static MemberDao getInstance() {
		if(instance ==null) {
			instance = new MemberDao();
		}
		return instance;
	}
		// TODO Auto-generated method stub
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}
	
	public int check(String id, String passwd) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "Select passwd from member2 where id = ? ";
		try {
			conn = getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs   = pstmt.executeQuery();
			if(rs.next()) {
				String dbPassword = rs.getString(1);
				if(dbPassword.equals(passwd)) result = 1;
				else result= 0;
			} else result = -1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			if ( rs    != null) rs.close();
			if ( conn  != null) conn.close();
			if ( pstmt != null) pstmt.close();
		}
		return result;
	}
}
