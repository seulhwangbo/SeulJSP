<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <% 
	String deptno = request.getAttribute("deptno").toString();
	String dname  = request.getAttribute("dname").toString();
	String loc    = request.getAttribute("loc").toString();
%>

<h1> 부서 정보 </h1>
부서코드 : <%=deptno %><p>
부서명  : <%=dname %><p>
근무지  : <%=loc %> <p> --%>
<h1> 부서 정보 </h1>
부서 코드 : ${deptno } <p>
부서명 : ${dname }<p>
근무지 :${loc }<p>
</body>
</html>