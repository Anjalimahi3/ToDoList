<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="signup.css">
</head>
<body>
<div>
<form action="Saveuser" method="post" enctype="multipart/form-data">
id:              <input type="text" name="id">
<br>
<br>
name:<input type="text" name="name">
<br>
<br>
email:<input type="email" name="email">
<br>
<br>
contact:<input type="tel" name="contact">
<br>
<br>
password:<input type="text" name="password">
<br>
<br>
image:<input type="file" name="image">
<br>
<br>
<input type="submit">
</div>





</form>

</body>
</html>