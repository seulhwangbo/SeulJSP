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
	String hiredate = request.getParameter("hiredate");
%>

Expression 입사일 1 : <%=hiredate %><p>

EL 입사일 2 : ${param.hiredate}
</body>
</html>