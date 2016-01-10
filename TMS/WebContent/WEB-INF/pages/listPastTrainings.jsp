<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
	      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Past Training</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/sexybuttons.css" type="text/css" />
<style type="text/css" title="currentStyle">
			@import "../css/demo_page.css";
			@import "../css/demo_table.css";
		</style>
		<script type="text/javascript" language="javascript" src="../js/jquery.js"></script>
		<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.js"></script>

<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$('#example').dataTable( {
					"sPaginationType": "full_numbers"
				} );
			} );
		</script>
<script type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
<header>
<jsp:include page="header.jsp" flush="true" />
<!-- end .header -->
</header>
<div class="container">
<div class="sidebar1">
<c:choose>
			<c:when test="${employeeDetails.employeeCategory =='e'}">
				<jsp:include page="employeeMenu.jsp" flush="true" />
			</c:when>
			<c:when test="${employeeDetails.employeeCategory =='p'}">
				<jsp:include page="projectManagerMenu.jsp" flush="true" />
			</c:when>
			<c:otherwise>
				<jsp:include page="trainingAdminMenu.jsp" flush="true" />
			</c:otherwise>
		</c:choose>
<!-- end .sidebar1 -->
</div>
<div class="content">
<div style='float:right;margin-right:10px'> Welcome ${employeeDetails.employeeName}</a></div>
<h2><b>Select a training from the existing ones or post a new training:-</b></h2>
<table>
<tr>
<td>
<c:choose>
<c:when test="${fn:length(trainingList) > 0}">
	<form:form  action="crepostTraining.htm" commandName="employee" >
	<table class="display" id="example">
		<thead>
		<tr>	
			<th>Select</th>	
			<th>Program</th>
			<th>Technology</th>
			<th>Topics</th>
			<th>Tier</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${trainingList}" var="trainings" varStatus="status">
			<tr class="<c:if test="${status.count % 2 == 0}">even</c:if>">
			
		<td>	<input type="radio" name="training" value='${trainings.trainingInformationId}' checked></td>
				<td>${trainings.trainingProgram}</td>
				<td>${trainings.technology}</td>
				<td>${trainings.trainingDescription}</td>
				<td>${trainings.tier}</td>   
			</tr>
		</c:forEach>
	</tbody>
	</table>
<br/><br/><br/><button type="submit" class="sexybutton sexylarge"><span><span><span class="ok">Select</span></span></span></button>
<a href="cpostTraining.htm" style="text-decoration:none;"><button type="button" class="sexybutton sexylarge"><span><span><span class="ok">Add a new Training</span></span></span></button></a>
</form:form>	
</c:when>
<c:otherwise>
	<h2>No past training available.</h2>
	<center><a href="cpostTraining.htm" style="text-decoration:none;"><button type="button" class="sexybutton sexylarge"><span><span><span class="ok">Add a new Training</span></span></span></button></a></center>
</c:otherwise>
</c:choose>
</td>
</tr>
</table>

<!-- end .content -->
</div>


<!-- end .container -->
</div>

<div class="footer">
<jsp:include page="footer.jsp" flush="true" />
<!-- end .footer -->
</div>

</body>
</html>