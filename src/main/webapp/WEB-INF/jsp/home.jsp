<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	fetch('/auth/getAllNotes', {
		  headers: {
		    'Authorization': 'Bearer ' + ${token} 
		  }
		});
/* $.ajax({
	  url: "/auth/getNotesByUser?userId="+"1",
	  datatype:"json",
  	  mtype : "GET",
	  success: function (resp) {
		  console.log("-------inside11");
		  var trHTML = '';
		  $.each(resp, function (i, userData) {
              trHTML +=
                  '<tr><td>'
                  + userData.userId
                  + '</td><td>'
                  + userData.note
                  + '</td><td> <img src="userData.imageUrl">'
                  + userData.imageUrl 
                  + '</td></tr>';
          
      });
      $('#tBody').append(trHTML);
      
		  console.log("------");
	    $("#userId").html(location.userId);
	    $("#notes").html(location.note);
	    $("#image").html(location.imageUrl);
	  }
	}); */
});

</script>
	 <table id="tBody">
 
</table> 
	<h1>Home page cal</h1>
</body>
</html>