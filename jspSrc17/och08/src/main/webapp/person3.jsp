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
 	String name = request.getParameter("name");
 	int age = Integer.parseInt(request.getParameter("age"));
 	String gender = request.getParameter("gender");
%>
	<jsp:useBean id="person" class="och08.Person" scope="request"/>
	<jsp:setProperty property="name"   name="person" value="<%=name %>"/>
	<jsp:setProperty property="age"    name="person" value="<%=age %>"/>
	<jsp:setProperty property="gender" name="person" value="<%=gender %>"/>
	<jsp:forward page="personResult.jsp"/>
	</body>
</html>