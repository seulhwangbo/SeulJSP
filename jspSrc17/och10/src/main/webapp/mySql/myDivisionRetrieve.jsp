<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
   String driver = "com.mysql.cj.jdbc.Driver";
   String url = "jdbc:mysql://localhost:3306/scottdb?serverTimezone=UTC";
   Class.forName(driver);
   Connection conn = DriverManager.getConnection(url, "root" , "mysql84");
   // 1. dno 받아 division 조회 SQL 작성
   String dno = request.getParameter("dno");
   String sql = "SELECT * FROM division WHERE dno="+dno;
   Statement stmt = conn.createStatement();
   ResultSet rs = stmt.executeQuery(sql);
   
   //2. request 저장 -> dno, dname, phone, position
   if (rs.next()) {
      String dname = rs.getString("dname");
      String phone = rs.getString("phone");
      String position = rs.getString("position");
      
      request.setAttribute("dno", dno);
      request.setAttribute("dname", rs.getString(2));
      request.setAttribute("phone", phone);
      request.setAttribute("position", position);
      
      rs.close();
      stmt.close();
      conn.close();
      
      RequestDispatcher rd = request.getRequestDispatcher("myDivisionInput.jsp");
      rd.forward(request, response);

   }
   stmt.close();
   conn.close();
%>
<script type="text/javascript">
   alert("없는 부서야 바보야");
   location.href = "myUpdate.html";
</script>
</body>
</html>