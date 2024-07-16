<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:set value="red,orange,yellow,green,blue,navy,violet" var="color"/> 
<c:set value="1-2-3-4-5-6-7" var="num"/>
<body>
	<!-- 현장 work -->	
	<c:forTokens var="nu"  items="${num}" delims="-">
 		<c:forTokens var="col" items="${color}" delims=",">
			<font color="${col}" size="${nu}"> 혜선이 </font>
		</c:forTokens>
	<p>
	</c:forTokens>
</body>
</html>
