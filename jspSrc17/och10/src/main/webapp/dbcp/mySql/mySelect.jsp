<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="och10.Professor"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
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
	String pno = request.getParameter("pno");
	Context ctx = new InitialContext();
	DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySql");
	Connection conn = ds.getConnection();
	Statement stmt = conn.createStatement();
	String sql = "Select profno, name, sal, hiredate From professor where profno =" +  pno;
	ResultSet rs  = stmt.executeQuery(sql);
	
	Professor professor = new Professor();
	
	while(rs.next()){
		professor.setProfno(rs.getInt(1));
		professor.setName(rs.getString(2));
		professor.setSal(rs.getInt(3));
		professor.setHiredate(rs.getDate(4));
		request.setAttribute("professor", professor);
	}
	rs.close();
	stmt.close();
	conn.close();
	
	RequestDispatcher rd = request.getRequestDispatcher("myResult.jsp");
	rd.forward(request, response);
	
	

%>
</body>
</html>