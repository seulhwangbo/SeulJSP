package och11;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDao {
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context    ctx  = new InitialContext();
		    DataSource ds   = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
		    conn            = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.out.println("e.getMessage()->"+e.getMessage());
		}
		
		return conn;
	}
	
	public int insert(MemberDto member) {
//		String sql     = String.format("Insert into Dept VALUES(%s,'%s','%s')",id,password,name);
	
		return 0;
	}

}
