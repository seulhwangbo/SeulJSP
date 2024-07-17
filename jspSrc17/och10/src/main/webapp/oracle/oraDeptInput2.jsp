<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
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
<% 	
		int    deptno   = Integer.parseInt(request.getParameter("deptno"));
		String dname 	= request.getParameter("dname");
		String loc 		= request.getParameter("loc");
		String driver 	= "oracle.jdbc.driver.OracleDriver";	
		String url    	= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String sql     	= "Insert into dept values(?,?,?)";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,"scott","tiger");
		PreparedStatement psmt = conn.prepareStatement(sql);
		
		psmt.setInt(1, deptno);
		psmt.setString(2, dname);
		psmt.setString(3, loc);
		
		int result = psmt.executeUpdate();
		if (result>0) out.println("입력 성공");
		else 		  out.println("실패");
		psmt.close();
		conn.close();
		

%>
</body>
</html>