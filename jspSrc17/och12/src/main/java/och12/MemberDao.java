package och12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;

public class MemberDao {
	// singleton + dbcp

	private static MemberDao instance;

	private MemberDao() {
	}

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}

	// TODO Auto-generated method stub
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
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
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbPassword = rs.getString(1);
				if (dbPassword.equals(passwd))
					result = 1;
				else
					result = 0;
			} else
				result = -1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
		}
		return result;
	}

	public int insert(Member member) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Insert into member2 values(?,?,?,?,?,sysdate)";
		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getTel());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result;
	}

	public int confirm(String id) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Select id From member2 where id=?";
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next())
				result = 1;
			else
				result = 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			rs.close();
			conn.close();
			pstmt.close();
		}
		return result;
	}

	public List<Member> list() throws SQLException {
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * From member2";
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					Member member = new Member();
					member.setId(rs.getString(1));
					member.setPasswd(rs.getString(2));
					member.setName(rs.getString(3));
					member.setAddress(rs.getString(4));
					member.setTel(rs.getString(5));
					member.setReg_date(rs.getDate(6));
					list.add(member);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			rs.close();
			conn.close();
			pstmt.close();
		}
		return list;
	}
	
	public Member select(String id) throws SQLException {
		Member member = new Member();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Select * from member2 where id=?";
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs   = pstmt.executeQuery();
			if (rs.next()) {
				member.setId(rs.getString(1));
				member.setPasswd(rs.getString(2));
				member.setName(rs.getString(3));
				member.setAddress(rs.getString(4));
				member.setTel(rs.getString(5));
				member.setReg_date(rs.getDate(6));
			}
		}catch (Exception e) {
				System.out.println(e.getMessage());
		}finally {
				rs.close();
				pstmt.close();
				conn.close();
				 }
		return member;
	}
	
	public int update(Member member) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Update member2 set passwd=?,name=?,address=?,tel=? where id=?";

		
		try {
			conn = getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getTel());
			pstmt.setString(5, member.getId());
			result = pstmt.executeUpdate();
			
			if (result>0)  result=1;
			else 		   result=0;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			pstmt.close();
			conn.close();
		}
		return result;
	}
	
	public int delete(String id, String passwd) throws SQLException {
	    int result = 0;    
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "DELETE FROM member2 WHERE id=? AND passwd=?";
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, id);  // Set id as the first parameter
	        pstmt.setString(2, passwd);  // Set passwd as the second parameter
	        
	        // Execute the update query
	        result = pstmt.executeUpdate(); 
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        // Optionally, log the exception or handle it in an appropriate way
	    } finally {
	        // Close resources in reverse order of creation
	            pstmt.close();
	            conn.close();
	  
	    }
	    return result;
	}
}
