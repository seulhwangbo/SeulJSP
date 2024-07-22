<%@page import="och15_ajax.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
 	String id = request.getParameter("id");
	MemberDao memberDao = MemberDao.getInstance();
	
	// member1  Read 
    // id 존재  --> return 1
    // id 존재X --> return 0
	int result = memberDao.confirm(id);
	if(result == 0){
		out.println("사용할 수 있는 id입니다");
	}else{
		out.println("중복 id이니 다른 id를 입력하세요");
	}

%>
</body>
</html>