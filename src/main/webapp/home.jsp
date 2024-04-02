<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="dto.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
     <% User u=(User)session.getAttribute("user");
     String image=new String(Base64.getEncoder().encode(u.getUserimage()));
     %>
    <img alt="" src="data:image/jpeg;base64, <%=image %>"width="200px" height="300px"/>
      <h1>hello <%=u.getUsername() %></h1> 
      <h1>Email --<%=u.getUseremail() %></h1> 
   
</body>
</html>