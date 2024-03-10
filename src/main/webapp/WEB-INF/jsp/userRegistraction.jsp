<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">

<head th:include="layout :: head(title=~{::title},links=~{})">
<style type="text/css">
* {box-sizing: border-box}

/* Add padding to containers */
.container {
  padding: 16px;
}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 30%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Overwrite default styles of hr */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* Set a style for the submit/register button */
.registerbtn {
  background-color: #04AA6D;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 30%;
  opacity: 0.9;
}

.registerbtn:hover {
  opacity:1;
}

/* Add a blue text color to links */
a {
  color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
  background-color: #f1f1f1;
  text-align: center;
}
</style>
<meta charset="ISO-8859-1">
<title>User Register</title>
</head>
<body th:include="layout :: body" th:with="content=~{::content}">
	
	 <form method="get" action="/createUser">
  		<div class="container">
	    <h1>Register</h1>
	    <hr>
	
	    <label for="name"><b>Name</b></label>
	    <input type="text" placeholder="name please" name="name" id="name" required><br>
	
	    <label for="mobNo"><b>Mobile number</b></label>
	   <input type="text" placeholder="mobile number" name="mobNo" id="mobNo" required><br>
	
	    <label for="password"><b>Password</b></label>
	    <input type="password" placeholder="Password" name="password" id="password" required>
	    <hr>
	
  	  <button type="submit" class="registerbtn">Register</button>
  </div>

  <div class="container signin">
    <p>Already have an account? <a href="/loginPage">Sign in</a>.</p>
  </div>
</form> 
</body>
</html>