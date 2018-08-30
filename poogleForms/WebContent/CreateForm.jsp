<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function addMCQ(){
		
	}
	function addTextQuestion(){
		
	}
</script>

</head>
<body>
	<div id = "header">
		<c:import url="header.jsp"></c:import>
	</div>
	<div id="userDisplay">
		<c:import url="userHeader.jsp"></c:import>
	</div>
	<div id ="allPageFunctionalities">
		<c:import url="allPageFunctionalities.jsp"></c:import>
	</div>
	<BR>
	
	<input id = "formNameInputId" type="text" name="formName" value = "${form.name }">Enter form Name<BR>
	
	<div id= "alreadyCreatedForm">
		
	</div>
	
	<div id ="addButtons">
		<button type="button" onclick= "addMCQ('alreadyCreatedForm')" >add MCQ</button>
		<button type="button" onclick= "addTextQuestion('alreadyCreatedForm')" >add Text</button><br>
	</div>
	
		
	<input type="submit"	>
</body>
</html>