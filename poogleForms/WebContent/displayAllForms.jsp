<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<div id ="displayForms">
	page: ${param.displayIndex }
	 <table style="width:100%">
	 	<tr>
	 		<th>Sl.No</th>
	 		<th>FORM NAME</th>
	 		<th>BY</th>
	 	</tr>
		
		<c:forEach var="currForm" items = "${forms}" varStatus = "formsListIndex">
				<td>${formsListIndex.count }</td>
				<td><a onclick = 'displayForm(${currForm.ID})'>${currForm.name }</a></td>
				<%-- <td id ="formID">${currForm.name }</td> --%>
				<td>${currForm.adminUsername } </td>
			<tr>
		</c:forEach>
	 </table>
	</div>
	<form action= "DisplayAllForms">
		Go to page: <input type = "text" name = "displayIndex"/>
		<input type="submit"/>
	</form>
<script>
	function displayForm(currFormID){
		
		window.location.assign("FormHandler?formID=" + currFormID);
	}
	 /* $(document).ready(function(){
		$("#formID").click(function(){
			window.location.replace("formHandler?formID=" + ) 
			window.alert("haha");
		});
	});  */
</script>
</html>

