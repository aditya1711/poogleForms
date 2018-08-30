<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

	<form id = "mainForm" method = "post"  >
		<input type="text" name="formName">Enter form Name<BR>
		
		<p id ="addQuestions">
			
		</p>
		
		<button type="button" onclick= "addMCQ('addQuestions')" >add MCQ</button>
		<button type="button" onclick= "addTextQuestion('addQuestions')" >add Text</button><br>
		
		<input type="submit"	>
	</form>
	
	
	<!-- <button type="button" onclick="submitAll()">SUBMIT</button> -->
	
	
	
<script>
	var optionIndex = [];
	var questionIndex=0;
	
	function addOption(id,questionNumber){
		document.getElementById(id).append(document.createElement("br"));
		var i = document.createElement("INPUT");
		i.setAttribute("type", "text");
		i.setAttribute("name" , "option@" + questionNumber + ":" + optionIndex[questionNumber]++);
		document.getElementById(id).appendChild(i);
	}

	function addMCQ(id){
		optionIndex.push(0);
		
		var para = document.createElement("p");
		para.setAttribute("id", "question:" + questionIndex);
		
		var qNameField = document.createElement("input");
		qNameField.setAttribute("type", "text");
		qNameField.setAttribute("name", "questionNumber:" + questionIndex+ " MCQ");
		qNameField.setAttribute("placeholder", "Question Promt for question: ?" + questionIndex);
		//qNameField.append("Enter Question Prompt");
		console.log(qNameField);
		
		var optionsPara = document.createElement("p");
		optionsPara.setAttribute("id", "options@" + questionIndex);
		
		var addOptionButton = document.createElement("button");
		addOptionButton.setAttribute("onclick", "addOption('options@" + questionIndex + "'," + questionIndex +")");
		addOptionButton.setAttribute("type", "button");
		addOptionButton.append("add option");
		
		optionsPara.appendChild(addOptionButton);
		
		para.appendChild(qNameField);
		para.appendChild(optionsPara);
		
		document.getElementById(id).appendChild(para);
		
		questionIndex++;
	}
	
	function addTextQuestion(id){
		optionIndex.push(0);
		
		var para = document.createElement("p");
		para.setAttribute("id", "question:" + questionIndex);
		
		var qNameField = document.createElement("input");
		qNameField.setAttribute("type", "text");
		qNameField.setAttribute("name", "questionNumber:" + questionIndex + " Text");
		qNameField.setAttribute("placeholder", "Question Promt for question: ?" + questionIndex);
		
		para.appendChild(qNameField);
		document.getElementById(id).appendChild(para);
		
		questionIndex++;
	}
</script>

</body>
</html>