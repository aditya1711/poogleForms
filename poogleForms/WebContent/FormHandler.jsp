<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = 'java.util.*' %>
<%@ page import = "poogleForms.model.form.*" %>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<h1>Form: ${form.name}</h1>
	<h2>Answer:</h2>
	<form method="post" >
	<c:forEach var="ques" items = "${form.list}">
		<c:set var="currQuestion" scope = "request" value = "${ques}"  />
		<c:import url = "${currQuestion.handler}"/>
	</c:forEach>
	<br><br>
	<input type="submit" value ="submit"/>
	</form>
</body>
</html>