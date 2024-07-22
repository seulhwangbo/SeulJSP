<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>0722</title>
<style type="text/css">
	.button { color:red; 
	          font-size:20px; 
	     }
</style>
<script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript">
	$(function(){
		$('#chk').click(function() {
			var sendData = $('form').serialize();
			alert('sendData -->' +sendData)
			location.href="jsRunTestResult.jsp?"+sendData;
		});
	});
	$(function(){
		$('#chk3').click(function() {
			var id = $('#id').val();
			var sendData ='id='+id;
			location.href="jsRunTestResult3.jsp?"+sendData;
		});
	});
	</script>
</head>
<body>
		<h1> 회원 가입 </h1>
		<form action="">
		아이디 : <input type="text" name="id" id = "id" value=""><p>
		암호	 : <input type="password" name="pass" id = "pass" value=""><p>
		이름	 : <input type="text" name="name" id = "name" value=""><p>
		<input type="button" class="button" value="확인1" id = "chk">		
		<input type="button" class="button" value="확인2" id = "chk3">		
		</form>
</body>
</html>