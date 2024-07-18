<%@page import="java.sql.Types"%>
<%@page import="java.sql.CallableStatement"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
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
	Context ctx = new InitialContext();
	DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
	Connection conn = ds.getConnection();
	//Procedure Call           empno/sal
	String sql = "{call Emp_Info3(?,?)}";
	CallableStatement cs = conn.prepareCall(sql);
	
	int empno = Integer.parseInt(request.getParameter("empno"));
	System.out.println("empno -> " + empno);
	//급여					애초에 선언이 double
	cs.registerOutParameter(2,Types.DOUBLE);
	//사번 
	cs.setInt(1, empno);
	cs.execute();
	// double로 선언한 이후에야 double로 가지고 올 수 있다
	out.println("급여 : " + cs.getDouble(2));
	cs.close();
	conn.close();



%>
</body>
</html>