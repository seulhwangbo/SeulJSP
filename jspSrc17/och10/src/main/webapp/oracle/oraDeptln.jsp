<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>부서 정보 수정</h2>
 부서코드: ${deptno}<p>
 <form action="oraUpdate.jsp">
 <input type="hidden" name="deptno" value="${deptno }">
 부서정보: <input type="text" name="dname" value="${dname }" required="required"><p>
 근무지 : <input  type="text" name="loc" value="${loc }" required="required"> <p>
 		<input type="submit" value="수정완료">
 </form>
</body>
</html>