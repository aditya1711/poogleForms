<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- <html>
	<body>
		<form method="post" > -->
		<p> Question: ${currQuestion.prompt} </p>
		<p>Options:</p>
		
		<c:forEach var="option"  items= "${currQuestion.options}" varStatus = "optionLoopCount" >
			<input type="radio" name= ${currQuestion.ID} value="${option}">${optionLoopCount.count}: ${option}<BR>
		</c:forEach>

		<!-- <input type="submit" value ="submit"/> -->
	<!-- </form>
	</body>
</html> -->
	
