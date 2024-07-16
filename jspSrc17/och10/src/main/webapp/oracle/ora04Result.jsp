<%@page import="och10.Dept"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	Dept dept = (Dept)request.getAttribute("dept");


%> 
<body>
<h1>Expression 부서 정보</h1>

부서코드 : <%=dept.getDeptno() %><p>
부서명  : <%=dept.getDname() %><p>
근무지  : <%=dept.getLoc() %><p>

<%-- 필드로 담아도 되고 다르게 담아도 된다
${dept.getDeptno() } --%>
<h1> EL 표기법 부서 정보</h1>
부서코드 :${dept.deptno }<p>
부서명  :${dept.dname }<p>
근무지  : ${dept.loc }
</body>
</html>