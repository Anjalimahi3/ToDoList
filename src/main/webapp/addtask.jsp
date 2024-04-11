<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="addtask.css">
</head>
<body>
<div>
<form action="createtask" method="post">
	<label for="taskid">Task id:</label>
    <input type="text" name="taskid" id="taskid">
   <br>
   
   <label for="tasktitle">Task Title:</label>
    <input type="text" name="tasktitle" id="tasktitle">
     <br>
   
   <label for="taskdes">Task Description:</label>
    <input type="text" name="taskdescription" id="taskdes">
    <br>
  Options:
  <br>
  
   <label for="option1">Low:
    <input type="radio" name="taskpriority" id="option1" value="low">
    </label>
    <br>
    <label for="option2">Medium:
    <input type="radio" name="taskpriority" id="option2" value="medium">
    </label>
    <br>
    <label for="option3">High:
    <input type="radio" name="taskpriority" id="option3" value="high">
    </label>
    <br>
    
    <label for="date">Due Date:</label>
    <input type="date" name="taskduedate" id="date">
    <br>
    
    <input id="sub" type="submit">
    
    </div>
</form>
</body>
</html>