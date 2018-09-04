
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
	
</script>
</head>
<body>
	${emailError}
	<form name="myform" action="UserSignUp" method="post"
		onsubmit="return validateNulls()">

		UserType: <select name="userType">
			<option value="LEVEL1">Level1</option>
			<option value="LEVEL2">Level2</option>
		</select> <BR>
		FirstName: <input type="text" id="firstnameInputID" name="firstname"><BR>
		Lastname: <input type="text" id="lastnameInputID" name="lastname"><BR>
		USERNAME: <input type="text" id="usernameInputID" name="username"><BR>
		Password: <input type="password" id="passwordInputID" name="password"><BR>
		<input type="submit" id="btn" value="signUp"><BR>
	</form>


	<script>
		function validateNulls() {
			var firstname = document.forms["myform"]["firstname"].value;
			var lastname = document.forms["myform"]["lastname"].value;
			var username = document.forms["myform"]["username"].value;
			var password = document.forms["myform"]["password"].value;
			var pattern = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

			if ((firstname == null || firstname == "")
					&& (lastname == null || lastname == "")
					&& (username == null || username == "")
					&& (password == null || password == "")) {
				alert('All fields are mandatory');
				return false;
			} else if(checkForExistingUsername(username)){
				alert("Username Already Exists");
				return false;
			}else{
				alert("Login To continue");
				return true;
			}
		}
		function checkForExistingUsername(username){
			var check;
			var result = $.post("UsernameCheck",{username: username},function(){
				console.log(result);
				if (result=='false'){
					check= false;
				}
				else if(result=="true"){
					check= true;
				}
				else{
					check=true;
				}
			});
			
			return check;
		}
	</script>
</body>
</html>