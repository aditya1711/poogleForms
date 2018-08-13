<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<form method = "post" >
		<input type="text" name="formName">Enter form Name<BR>
	
	
	<p id ="addQuestions">
		
	</p>
	
	</form>
	<button type="button" onclick= "addMCQ()" >add MCQ</button>
	<button type="button" onclick= "addTextQuestion()" >add Text</button><br>
	<input type = "submit">
	
<script>

	function addMCQ(){
		var x = document.getElementById("addQuestions");
		var importTo = document.createElement("c:import");
		importTo.setAttribute("url", "CreateMCQ.jsp");
		x.appendChild(importTo);
		console.log(importTo);
		console.log(x);
		//document.getElementById("addQuestions").innerHTML += "<c:import url ='CreateMCQ'/>";
		//document.getElementById("addQuestions").append("<jsp:include page='CreateMCQ'/>");
		//<c:import url="CreateMCQ"/>
	}
	
	function addTextQuestion(){
		
	}
</script>

</body>
</html>