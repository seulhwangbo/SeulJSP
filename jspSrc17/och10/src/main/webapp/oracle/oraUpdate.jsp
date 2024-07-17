<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.beans.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1> 스크릿틀릿 + preparedStatement 수정</h1>
<% 
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		String dname = request.getParameter("dname");
		String loc   = request.getParameter("loc");
		
		String sql     = "Update dept set dname = ?, loc =? where deptno =?";
		String driver = "oracle.jdbc.driver.OracleDriver";	
		String url    = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,"scott","tiger");
		PreparedStatement psmt = conn.prepareStatement(sql);

		psmt.setString(1, dname);
		psmt.setString(2, loc);
		psmt.setInt(3, deptno);
		
		int result = psmt.executeUpdate();
		if (result>0) out.println("입력 성공");
		else		  out.println("입력 실패");
		psmt.close();
		conn.close();
%>

</body>
</html>