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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
	<div id = "formDisplay">
		<h1>Form: ${form.name}</h1>
		<h2>Answer:</h2>
		<form method="post" >
		<div id ="questionDisplay">
			<c:forEach var="ques" items = "${form.list}" varStatus = "questionLoopCount">
				<c:set var="currQuestion" scope = "request" value = "${ques}" />
				<c:set var="currQuestionID" scope = "request" value = "${ques.ID}" />
				<c:if test = "${not empty '${answerMap}' }">
					<c:set var="currAnswer" scope = "request" value = "${answersMap[currQuestionID]}"  />
				</c:if>
				<c:if test = "${empty '${answerMap}' }">
					<c:set var="currAnswer" scope = "request" value = ""  />
				</c:if>
				
				<c:import url = "${currQuestion.handler}"></c:import>
			</c:forEach>
		</div>
		
		<br><br>
		<input type="submit" value ="submit"/>
		</form>
	</div>
	
</body>
</html>