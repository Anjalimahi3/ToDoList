<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="dto.User"%>
      <%@ page import="java.util.List"%>
       <%@ page import="dto.Task"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
div{
background-image: url("https://static8.depositphotos.com/1258191/1030/i/450/depositphotos_10301580-stock-photo-touchscreen-interface.jpg");
background-repeat:no-repeat;
background-size:100%;
height: 150vh;
}
section{
text-align:center;
font-size: 25px;
background-color:white;
color:balck;
border: 1px solid black ;
padding-left: 70px;
padding-right: 70px;

}
table,th,td{
border:1px solid black;
border-collapse: collapse;
margin-left: 400px;
margin-top: 180px;

}
td,th{
padding: 20px;
}
table{
background-color: purple;
color: white;
}
th{
color: black;
font-size: 20px;
}

</style>
</head>
<body>
<div>
<section>
     <% User u=(User)session.getAttribute("user");
     String image=new String(Base64.getEncoder().encode(u.getUserimage()));
     %>
    <img alt="" src="data:image/jpeg;base64, <%=image %>"width="200px" height="300px"/>
      <h3>hello  :<%=u.getUsername() %></h3> 
      <h3>Email  :<%=u.getUseremail() %></h3>
      <h3>Contact:<%=u.getUsercontact()%></h3>
      
      
      <a href="addtask.jsp">add task</a> 
   <% List<Task> tasks=(List)request.getAttribute("tasks");
     
      if(!tasks.isEmpty())
      {
   %>
   </section>
   <table >
   <thead>
   <tr>
   <th>id</th>
   <th>title</th>
   <th>description</th>
   <th>priority</th>
   <th>due date</th>
   <th>status</th>
   <th>Edit</th>
   <th>delete</th>
   
   </tr>
   
   </thead>
   <tbody>
   <% for(Task task:tasks){ %>
   
   <tr>
   <td><%= task.getTaskid() %> </td>
   <td><%=  task.getTasktitle() %></td>
   <td><%= task.getTaskdescription()%></td>
   <td><%= task.getTaskpriority() %></td>
   <td><%= task.getTaskduedate() %></td>
   <td><%= task.getTaskstatus() %></td>
   <td><a href="edit?taskid=<%= task.getTaskid()%>">Edit</a></td>
   <td> <a href="deletetask?id=<%= task.getTaskid()%>">Delete</a></td>
   <td> <a href="logout">Logout</a></td>
   </tr>
   <%}
   
      } %>
   </tbody>
   </table>
   </div>
</body>
</html>