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
	Person person = new Person();
%> --%>
	<jsp:useBean id="person" class="och08.Person"/>
	<!-- person.setName 과 같다 parameter에 NAME 과 같다 -->
	<jsp:setProperty property="name"   name="person" param="name"/>
	<jsp:setProperty property="age"    name="person" param="age"/>
<%-- 	<jsp:setProperty property="gender" name="person" value="남자"/>--%>	
	<jsp:setProperty property="gender" name="person" param="gender"/>
	
	<h2> 인적사항 </h2>
	<!--  person.getName과 같다 -->
	이름 : <jsp:getProperty property="name"   name="person"/><p>
	성별 : <jsp:getProperty property="gender" name="person"/><p>
	나이 : <jsp:getProperty property="age"    name="person"/>
</body>
</html>