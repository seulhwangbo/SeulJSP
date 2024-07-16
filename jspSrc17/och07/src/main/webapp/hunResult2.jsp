<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> 1. Expression ----> SUM1</h4>
<%
	String sum1 = request.getAttribute("sum1").toString();

%>
합계는 <%=sum1 %><p>
<h4> 2. EL ----> SUM2</h4>
합계는 ${sum2}<p>
</body>
</html>