<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>0711</title>
</head>
<body>
<% 
	String info = application.getServerInfo();
	int major 	= application.getMajorVersion();
	int minor 	= application.getMinorVersion();
	String path = application.getRealPath("/");
	// ** 중요한 개념 : 기준점을 잡아주는 느낌이다
%>
	<h2>Application 내장 객체 예제</h2>
	팀 컨테이너의 이름과 버전 :
	<%=info %><p>
	서블릿의 버전 : <%=major %>.<%=minor %><p>
	웹 어플리케이션 폴더의 로컬 시스템 경로 : <%=path %>
</body>
</html>