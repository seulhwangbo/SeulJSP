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
	<form action="myUpdate.jsp">
	부서 코드 : ${dno}<p>
	<input type="hidden" name="dno" value="${dno}">
	부서명 :   <input type="text" name="dname"    value="${dname}" ><p>
	전화번호 :  <input type="text" name="phone"    value="${phone}"><p>
	직급   : <input type="text" name="position" value="${position}"><p>
	<input type="submit" value="수정 완료">
	</form>
</body>
</html>