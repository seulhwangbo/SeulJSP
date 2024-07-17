<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
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
<%
	int dno 	  = Integer.parseInt(request.getParameter("dno"));
	String driver = "com.mysql.cj.jdbc.Driver";
	String url    = "jdbc:mysql://localhost:3306/scottdb?serverTimezone=UTC";
	String sql = "Select * From division Where dno = " + dno;
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url,"root","mysql84");
	Statement stmt = conn.createStatement();
	ResultSet rs   = stmt.executeQuery(sql);
	
	if(rs.next())	{
		String dname = rs.getString(2);
		String phone = rs.getString(3);
		String position = rs.getString(4);
		out.println("부서 코드 : " + dno + "<p>");
		out.println("부서명 : "   + dname + "<p>");
		out.println("전화 번호 : " + phone + "<p>");
		out.println("직급  : "   + position + "<p>");
	}else out.println("그게 무슨 말이야");
	
	rs.close();
	stmt.close();
	conn.close();
	
	
	
%>

</body>
</html>