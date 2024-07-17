<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

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
		String dname 	 = rs.getString("dname");
		String phone  	 =  rs.getString("phone");
		String position  =  rs.getString("position");
		request.setAttribute("dno", dno);
		request.setAttribute("dname", dname);
		request.setAttribute("phone", phone);
		request.setAttribute("position", position);
	
	}else out.println("그게 무슨 말이야");
	
	rs.close();
	stmt.close();
	conn.close();
	
	RequestDispatcher rd = request.getRequestDispatcher("my03Result.jsp");
	rd.forward(request, response);
	
	
	
%>

</body>
</html>

</body>
</html>