<%@page import="och12.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="memberCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String passwd = request.getParameter("passwd");
	MemberDao md  = MemberDao.getInstance();
	System.out.println("DeletePro id -> " + id);
	int result    = md.delete(id, passwd);
	if(result == 1 ){
		session.invalidate();
%>
	<script type="text/javascript">
	alert("탈퇴되었습니다. 안녕");
	location.href="main.jsp";
	</script>
	<% } else if (result==0) { %>
	<script type="text/javascript">
	alert("암호 잘하자?");
	history.go(-1);
	</script>
	<% } else { %>
	<script type="text/javascript">
	alert("유저 달랑");
	history.go(-1);
	</script>
	<%} %>
	</body>
	</html>