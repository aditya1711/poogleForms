<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<title>Insert title here</title>


</head>

<body>
	<form method = "post" >
		<input type="text" name="formName">Enter form Name<BR>
			
			
		
	</form>
<script>
	function addQuestion(id){
		var x = "<input type='text' name="formName">Enter form Name<BR>"
		document.getElementById(id).innerHTML = "haha <br /> " + document.getElementById(id).innerHTML;
		/* for(i=0;i<35;i++){
			
		} */
	}
</script>
</body>
</html>