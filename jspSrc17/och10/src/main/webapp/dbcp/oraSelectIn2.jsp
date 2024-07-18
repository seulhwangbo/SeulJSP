<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.Connection"%>
<%@page import="och10.Emp"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../dbError.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   //al만들기
   // 1. DBCP를 이용해서 만들어준다.
   // 2. emp.empno와 emp.ename을 가지고 
   // 3. setAttribute("al",al)
   ArrayList<Emp> al = new ArrayList<Emp>();
   Context ctx = new InitialContext();
   DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
   Connection conn = ds.getConnection();

   String sql = "SELECT empno, ename FROM emp" ;
   Statement stmt = conn.createStatement();
   ResultSet rs = stmt.executeQuery(sql);

   while (rs.next()) {
      Emp emp = new Emp();
      emp.setEmpno(rs.getInt(1));
      emp.setEname(rs.getString(2));
      al.add(emp);
   }
   request.setAttribute("al", al);
   rs.close();
   stmt.close();
   conn.close();
%>
   <h2>보고싶은 사원 번호를 선택하세요</h2>
<!--    <form action="oraSelect.jsp">
 -->   <form action="oraCallEmpInfo.jsp">
      <select name="empno">
         <c:forEach var="emp" items="${al }">
            <option value="${emp.empno }">${emp.empno } ${emp.ename }</option>
         </c:forEach>
      </select><p>
      <input type="submit" value="확인">
   </form>
</body>
</html>