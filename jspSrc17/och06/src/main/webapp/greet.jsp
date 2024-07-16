<%@page import="java.io.FileWriter"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.GregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! 

	private PrintWriter pw;
	String date;
	
	public void jspInit(){
	// Method, Member 변수 --> Init
	GregorianCalendar gc = new GregorianCalendar();
	date = String.format("%TF %TT",gc, gc);
	System.out.println("JSP Init date => " + date);
	String fileName = "c:/log/" +date.replaceAll(":", "") +".txt";
	try{
	  pw = new PrintWriter(new FileWriter(fileName, true));
	}catch(Exception e){
		System.out.println("으이구");
	}
	}
%>
<% 
	// doGET, doPOST
	String name = request.getParameter("name");
	System.out.println(name + "사회 활동 ");
	String msg  = name + "님 반가워";
	
	out.println(msg + "<p> 현재 시간 : " + date);
	pw. println(msg + "\r\n 현재 시간 : " + date + "\r\n");
%>
<%! 
// Method, Member 변수 --> destory
	public void jspDestroy(){
		System.out.println("greet의 유언 활동 ");
		pw.flush();
		if(pw !=null)
		   pw.close();
	}
%>
</body>
</html>