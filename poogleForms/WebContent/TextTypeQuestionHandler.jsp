<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->

<!-- <html>
	<body>
		<form method="post"> -->
		<p> Question: ${currQuestion.prompt} </p>
		
		<p>Answer:</p>
		<input type="text" name= ${currQuestion.ID} /> <BR>
		
		<c:if test = "${not empty currAnswer }">
				Previous Answer: ${currAnswer.answers}
		</c:if>
		
		<!-- <input type="submit" value ="submit"/> -->
	<!-- </form>
	</body>
</html> -->
	
