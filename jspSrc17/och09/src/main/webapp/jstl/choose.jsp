<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>인사말 Switch 유사</h2>
	<c:choose>
	<c:when test ="${param.num1 ==0 }"> 안녕하세요  </c:when>
	<c:when test ="${param.num1 ==1 }"> hi 	     </c:when>
	<c:when test ="${param.num1 ==2 }"> 곤니지와   </c:when>
	<c:otherwise>헐 누구! </c:otherwise>
	</c:choose>
</body>
</html>