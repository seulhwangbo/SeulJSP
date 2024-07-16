<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>인적사항</h2>
<jsp:useBean id="person" class="och08.Person" scope="request"/>
<jsp:setProperty  property="name"   name="person"  param="name"/>
<jsp:setProperty  property="gender" name="person"  value="남성"/>
<jsp:setProperty  property="age"    name="person"  value="23"/>
<jsp:forward page="personResult.jsp"/>
</body>
</html>