<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>createmcq</title>


</head>

<body>

	<form id="mainForm" method="post">
		<input type = "text" name = "questionName">Enter Question Name<BR>
		
		<p id = "options">
			<button type="button" onclick="addOption('options')">add option</button>
		</p>
		
		<input type = "submit" value = "submit">
	</form>

<script type="text/javascript">
	var optionIndex = 0;
	
	function addOption(id){
		console.log(id);
		document.getElementById("mainForm").append(document.createElement("br"));
		var i = document.createElement("INPUT");
		i.setAttribute("type", "text");
		i.setAttribute("placeholder", "option@" + optionIndex++);
		document.getElementById("mainForm").append(i);
		
		console.log(i);
		return false;
	}
</script>	
</body>

</html>