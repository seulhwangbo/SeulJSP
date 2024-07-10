<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<% 
 	int num = Integer.parseInt(request.getParameter("num"));
	int sum = 0;
	for(int i=1; i<=num; i++){
		sum +=i;
		out.println(i + "일 때 합계는 " + sum+"<p>");
	}
	
	/* 
	request.setAttribute("sum", sum);
	요기는 연산만 하고 결과는 다음 페이지 
	//선언 == 이동하겠다고 선언한 것
	RequestDispatcher rd = request.getRequestDispatcher("numResult.jsp");
	// 진짜 이동 == 뒤에 있는 것들을 불러서 이동한다
	rd.forward(request, response); 
	*/
%>
<body>

</body>
</html>