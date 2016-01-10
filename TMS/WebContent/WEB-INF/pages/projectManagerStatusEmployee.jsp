<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
     <c:if test="${employeeUser.employeeCategory!='p'}">
	<% response.sendRedirect("clogout.htm"); %>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Status</title>
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
				$('#example1').dataTable( {
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
<jsp:include page="projectManagerMenu.jsp" flush="true" />
<!-- end .sidebar1 -->
</div>
<div class="content">
<div style='float:right;margin-right:10px'> Welcome <a href="cloggedInPM.htm">${employeeDetails.employeeName}</a></div>
<h2><b>Select the employee to see the training enrolled by him:-</b></h2>

<table>
<tr>
<td>
<c:choose>
<c:when test="${fn:length(empList) > 0}">
	<form:form  action="pviewEmployeeInfo.htm" method="post">
	<table class="display" id="example">
		<thead>
		<tr>	
			<th>Select</th>
			<th>Employee Id</th>
			<th>Employee Name</th>
			<th>Employee Number</th>
			<th>Employee Designation</th>
			<th>Employee Tier</th>
			<th>Employee Email Id</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach items="${empList}" var="employee" varStatus="status">
			<tr class="<c:if test="${status.count % 2 == 0}">even</c:if>">
			
				<td><input type="radio" name="EmployeeNames" value='${employee.employeeId}' checked></td>
				<td>${employee.employeeId}</td>
				<td>${employee.employeeName}</td>
				<td>${employee.employeeNumber}</td>
				<td>${employee.employeeDesignation}</td>	
				<td>${employee.employeeTier}</td>
				<td>${employee.employeeEmail}</td> 
			</tr>
			</c:forEach>
		</tbody>
		</table>
<br>
<center>
<button type="submit" class="sexybutton sexylarge"><span><span><span class="ok">Submit</span></span></span></button>
</center>
</form:form>
</c:when>
<c:otherwise>
	<h2>There are no employees under you.</h2>
</c:otherwise>
</c:choose>
</td>
</tr>
<tr>
<td>
<br/><br/>
</td>
</tr>
<tr>	
<td>
<c:if test="${flag==1 }">
<h2><b>List of training enrolled by him are:-</b></h2>
<c:choose>	
	<c:when test="${fn:length(trainingList) > 0}">
	<table class="display" id="example1">
		<thead>
		<tr>
			<!-- <th>Trainer Name</th> -->
			<th>Training Program</th>
			<th>Technology</th>
			<th>Training Description</th>
			<th>Status</th>
			<th>Tier</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${trainingList}" var="trainingDetails" varStatus="status">
			 <tr class="<c:if test="${status.count % 2 == 0}">even</c:if>"> 
			
			   <%--  <td>${details.employeeId}</td>  --%>
			    <td>${trainingDetails.trainings.trainingInformation.trainingProgram}</td>
				<td>${trainingDetails.trainings.trainingInformation.technology}</td>
				<td>${trainingDetails.trainings.trainingInformation.trainingDescription}</td>
				<td>${trainingDetails.trainings.status}</td>
				<td>${trainingDetails.trainings.trainingInformation.tier}</td>	
			</tr>
			</c:forEach>
			</tbody>
		</table>
</c:when>
<c:otherwise>
	<h2>No training enrolled by this employee.</h2>
</c:otherwise>
</c:choose>
</c:if>
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