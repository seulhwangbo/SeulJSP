<%@page import="java.sql.PreparedStatement"%>
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
	<h2>스크리틀릿 + PreparedStatement</h2>
	<%	
		int    dno   = Integer.parseInt(request.getParameter("dno"));
		String dname = request.getParameter("dname");
		String phone = request.getParameter("phone");
		String position = request.getParameter("position");
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url    = "jdbc:mysql://localhost:3306/scottdb?serverTimezone=UTC";
		String sql = "Update division set dname =?, phone =?, position=? where dno =?";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,"root","mysql84");
		PreparedStatement psmt = conn.prepareStatement(sql);
		
		psmt.setString(1, dname);
		psmt.setString(2, phone);
		psmt.setString(3, position);
		psmt.setInt(4, dno);
		
		int result = psmt.executeUpdate();
		if (result>0) out.println("입력 성공");
		else		  out.println("입력 실패");
		
		psmt.close();
		conn.close();
	
	
	%>
</body>
</html>