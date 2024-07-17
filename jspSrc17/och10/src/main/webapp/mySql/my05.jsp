<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="och10.Professor"%>
<%@page import="java.util.ArrayList"%>
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
	String driver = "com.mysql.cj.jdbc.Driver";
	String url    = "jdbc:mysql://localhost:3306/scottdb?serverTimezone=UTC";
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url,"root","mysql84");
	Statement stmt = conn.createStatement();
	String sql 	  = "Select profno,name,position,sal from professor";
	ResultSet rs   = stmt.executeQuery(sql);
	
	ArrayList<Professor> al = new ArrayList<Professor>();
	if (rs.next()){
	do  {
		Professor professor = new Professor();
		professor.setProfno(rs.getInt(1));
		professor.setName(rs.getString(2));
		professor.setPosition(rs.getString(3));
		professor.setSal(rs.getInt(4));
		al.add(professor);
	} while(rs.next());
		request.setAttribute("al",al);
		rs.close();
		stmt.close();
		conn.close();
/* 		RequestDispatcher rd = request.getRequestDispatcher("my05Result.jsp");
 */		RequestDispatcher rd = request.getRequestDispatcher("my05Result2.jsp");
		rd.forward(request, response);
	}else{
		out.println("데이터가 없습니다");
		stmt.close();
		conn.close();
	}
	
		
	
%>
</body>
</html>