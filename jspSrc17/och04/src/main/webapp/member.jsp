<%@page import="java.io.FileWriter"%>
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
	request.setCharacterEncoding("utf-8");
	String hobbys   = "";
	String id 		= request.getParameter("id");
	String password = request.getParameter("password");
	String name		= request.getParameter("name");
	String [] hobby = request.getParameterValues("hobby");
	String gender   = request.getParameter("gender");
	response.setContentType("text/html;charset=utf-8");
	String real = application.getRealPath("/WEB-INF/file/"+id+".txt");
	String str = "아이디 : " + id+"\r\n 비밀번호 : " + password + 
			      "\r\n 이름	  : " + name;
	System.out.println("real - > " + real);

	for (int i = 0; i < hobby.length; i++){
		hobbys += hobby[i] + "";
	}
	str += "\r\n 취미" + hobbys + "\r\n 성별" + gender;
	FileWriter fw = new FileWriter(real);
	fw.write(str);
	fw.close();
%>
<h2> 회원 정보</h2>
아이디: <%=id %><p>
암호 : <%=password %><p>
이름 : <%=name %><p>
취미 : <%=hobbys %><p>
성별 : <%=gender %><p>
</body>
</html>