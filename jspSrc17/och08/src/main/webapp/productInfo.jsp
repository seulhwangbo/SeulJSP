<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>제품 정보</h2>
	<jsp:useBean id="pt" scope="request" class="och08.Product"/>
	코드 : <jsp:getProperty property="code" name="pt"/> <p>
	이름 : <jsp:getProperty property="name" name="pt"/> <p>
	가격 : <jsp:getProperty property="price" name="pt"/><p>
</body>
</html>