<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<script>
	function goToPage(inputDOM){
		//window.alert(displayIndex);
		console.log(inputDOM);
		var val = inputDOM.value;
		var noOfPages =  ${noOfPages};
		console.log(val);
		
		if((!isNaN(val)) && val <= noOfPages){
			console.log(window.location.href);
			$('#displayForms').load('${callingPage}' +  ' #displayForms' ,
					{
						displayIndex: val
					});
		}
		else{
			$("#goToPageDiv").append("invalid page number");
		}
	}
	
	function displayForm(currFormID){
		window.location.assign("FormHandler?formID=" + currFormID);
	}

	
</script>

<div id ="displayForms">
page: ${displayIndex } of ${noOfPages }
 <table style="width:100%">
 	<tr>
 		<th>Sl.No</th>
 		<th>FORM NAME</th>
 		<th>BY</th>
 	</tr>
	
	<c:forEach var="currForm" items = "${forms}" varStatus = "formsListIndex">
		<tr>	
			<td>${formsListIndex.count }</td>
			<td><a onclick = 'displayForm(${currForm.ID})'>${currForm.name }</a></td>
			<%-- <td id ="formID">${currForm.name }</td> --%>
			<td>${currForm.adminUsername } </td>
		</tr>
	</c:forEach>
 </table>
</div>

<div id ="goToPageDiv">
	<script >
			$(document).ready(function(){
				var input = $("<input id='displayIndex' type = 'text'>");
				$("#goToPageDiv").append("Go To Page:");
				$("#goToPageDiv").append(input);
				var x = document.getElementById('displayIndex').value;
				
				//$("#goToPageDiv").append('<button type= "button" onClick  = "goToPage(' + x.value + ')">GO</button>');
				$("#goToPageDiv").append('<button type= "button" onClick  ="goToPage(' + "displayIndex" + ')">GO</button>');
				//$("#goToPageDiv").append('<button type= "button" onClick  ="goToPage()">GO</button>');
			
			});
	</script>
</div>




