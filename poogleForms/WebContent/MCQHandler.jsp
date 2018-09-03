<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<p>Question: ${currQuestion.prompt}</p>
<p>Options:</p>

<c:forEach var="option" items="${currQuestion.options}"
	varStatus="optionLoopCount">
	<input type="radio" name="${currQuestion.ID}" value="${option}">${optionLoopCount.count}: ${option}<BR>
</c:forEach>
<c:if test="${not empty currAnswer }">
				Previous Answer: ${currAnswer.answers}
</c:if>

