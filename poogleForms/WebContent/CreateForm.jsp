<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/themes/ui-darkness/jquery-ui.css"
	rel="stylesheet">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>

<script>
	var optionNumber = 0;
	var currQuestionType = "TextTypeQuestion";
	function addQuestion() {
		optionNumber = 0;
		openDialog();
		$("#optionsDiv").hide();

	}

	function openDialog() {
		$("#dialog").dialog("open");
		$("#addQuestionForm").hide();
	}
</script>

<script>
	function addMCQ() {
		currQuestionType = "MCQ";
		$("#dialogAddQuestionButtons").hide();
		$("#addQuestionForm").show();
		$("#optionsDiv").show();
		/* $("#dialog").load("CreateForm.jsp" + " #addQuestionForm", {},
				function() {
					$("#addQuestionForm").show();
					$("#optionsDiv").show();
				}); */
	}

	function addTQ() {
		currQuestionType = "TextTypeQuestion";
		$("#dialogAddQuestionButtons").hide();
		$("#addQuestionForm").show();
		$("#optionsDiv").hide();
		/* $("#dialog").load("CreateForm.jsp" + " #addQuestionForm", {},
				function() {
					$("#addQuestionForm").show();
					$("#optionsDiv").hide();
				}); */
	}

	function addOption(id) {
		document.getElementById(id).append(document.createElement("br"));
		var i = document.createElement("input");
		i.setAttribute("type", "text");
		console.log("optionNumber= " + optionNumber)
		//i.setAttribute("id", "option@" + optionNumber);
		i.setAttribute("class", "option");
		optionNumber++;
		//i.setAttribute("name", "option@" + optionNumber++);
		$("#" + id).append(i);
		//document.getElementById(id).append(i);
	}

	function submitQuestionFunc() {
		console.log("submit question funciotn reached");
		/* var options = "";
		for (var i = 0; i < optionNumber; i++) {
			options = options + $("#option@" + i).val();
		} */
		var options = $(".option");
		var optionsSTring = "";
		for (var i = 0; i < optionNumber; i++) {
			optionsSTring = optionsSTring + $(options[i]).val() + ";";
		}

		console.log(currQuestionType);
		console.log($("#questionPrompt").val());
		console.log(optionsSTring);

		var newQuestionHTML = $.post("CreateQuestion", {
			command:"createQuestion",
			formID : '${form.ID}',
			questionPrompt : $("#questionPrompt").val(),
			questionType : currQuestionType,
			options : optionsSTring,
		}, function(data) {
			$("#dialog").dialog('close');
			$("#alreadyCreatedForm").append(data + "<br><br>");
			$("#dialogAddQuestionButtons").show();
		});
		console.log(newQuestionHTML);
		
	}
	
	function deleteForm(){
		$.post("CreateForm", {
			command:"deleteForm",
			formID:"${form.ID}"
		},function(){
			window.location.assign("Dashboard");
		});
	}
</script>

</head>
<body>
	<div id="header">
		<c:import url="header.jsp"></c:import>
	</div>
	<div id="userDisplay">
		<c:import url="userHeader.jsp"></c:import>
	</div>
	<div id="allPageFunctionalities">
		<c:import url="allPageFunctionalities.jsp"></c:import>
	</div>
	<BR>

	<div id="alreadyCreatedForm">
		<button onclick="deleteForm()">DELETE THIS FORM</button>

		<input id="formNameInputId" type="text" name="formName"
			value="${form.name }">Enter form Name<BR>

		<c:forEach var="ques" items="${form.list}"
			varStatus="questionLoopCount">
			<c:set var="currQuestion" scope="request" value="${ques}" />
			<c:set var="callingPage" scope="request" value="CreateForm_jsp" />
			<c:import url="${currQuestion.handler}"></c:import>
			<BR>
			<BR>
		</c:forEach>
	</div>


	<div id="dialog">
		<div id="dialogAddQuestionButtons">
			<button type="button" onclick="addMCQ()">add MCQ</button>
			<button type="button" onclick="addTQ()">add Text</button>
			<br> <BR>
		</div>

		<form id="addQuestionForm">
			<label>Question Prompt:</label> <input id="questionPrompt"
				name="questionPrompt" type="text">

			<div id="optionsDiv">
				<button type="button" onclick="addOption('optionsDiv')">add
					option</button>
				<br>
			</div>

			<button id="submitQuestion" type="button"
				onclick="submitQuestionFunc()">Submit</button>
		</form>
	</div>

	<button id="addQuestionButton" type="button" onclick="addQuestion()">ADD
		QUESTION</button>

	<BR>
	<BR>
	<BR>
	<button id="submitFormButton" type="submit">SumitForm</button>
</body>

<script>
	$(document).ready(function() {

		$("#dialog").dialog({
			autoOpen : false,
			modal : true
		});
		$("#submitFormButton").click(function(e){
			e.preventDefault();
			$.post("CreateForm", {
				command:"createForm",
				formID: "${form.ID}",
				formName: $("#formNameInputId").val()
			},
			window.location.assign("FormHandler?formID=${form.ID}"));
		});
	});
</script>


</html>