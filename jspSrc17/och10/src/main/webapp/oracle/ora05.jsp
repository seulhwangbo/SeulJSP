<%@page import="och10.Emp"%>
<%@page import="java.util.ArrayList"%>
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
	String driver = "oracle.jdbc.driver.OracleDriver";	
	String url    = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String sql     = "select empno,ename,job,sal from emp";
	System.out.println("sql -> " + sql);
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url,"scott","tiger");
	Statement  stmt = conn.createStatement();
	ResultSet  rs   = stmt.executeQuery(sql);	
	// collection 선언 ArrayList 생성 
	ArrayList<Emp> al = new ArrayList<Emp>();
	if(rs.next()){
		do{
			Emp emp =  new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob  (rs.getString(3));
			emp.setSal(rs.getInt(4));
			al.add(emp);
		}while(rs.next());
		request.setAttribute("al", al);
		rs.close();
		stmt.close();
		conn.close();
/* 		RequestDispatcher rd = request.getRequestDispatcher("ora05Result.jsp");*/		
		RequestDispatcher rd = request.getRequestDispatcher("ora05Result2.jsp");
		rd.forward(request, response);
	}else {
		out.println("데이터가 없읍니당");
		stmt.close();
		conn.close();
	}

%>
</body>
</html>