<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	double[] temp = {9.8, 30.2, 24.7, 23.7, 23.1};
		for (int i = 0; i <temp.length; i++){
		out.println(temp[i]);
		// 밑 로직에 -1을 하지 않으면 마지막에 숫자가 없어도 , 가 나오기 때문에 한 것이다
		if (i !=(temp.length-1)){
			out.print(" , ");
		}
	}
%>
