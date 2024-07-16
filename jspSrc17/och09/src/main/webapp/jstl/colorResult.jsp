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
<!-- 	"red","orange","yellow","green","blue","navy","violet"-->
	<c:forEach var="col" items="${color}" > 
<%-- 	<d:forEach var="col" items="${color}" >  
		d로 사용하고 위도 d로 바꿔도 사용이 가능하지만 
		다른 사람들과의 커뮤니케이션이 용이하지 않기 때문에 d로 쓰지 않는다
--%>
	<font color="${col}"> 혜써니 </font><p>
	</c:forEach>
</body>
</html>
