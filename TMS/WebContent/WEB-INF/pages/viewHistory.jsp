<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View History</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
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
<h2><b>History of the training:-</b></h2>
<table>
	<tr>
		<td><c:choose>
			<c:when test="${fn:length(history) > 0}">
				<table class="display" id="example">
					<thead>
						
						<tr>
								<th>Program</th>
								<th>Technology</th>
								<th>Topics</th>
								<th>Tier</th>
								<th>Trainer Name</th>
								<th>Date</th>
								<th>Status</th>
								
							</tr>
						</thead>
						<tbody>
			<c:forEach items="${history}" var="trainingScheduled" varStatus="status">
			<tr class="<c:if test="${status.count % 2 == 0}">even</c:if>">
			
				<td>${trainingScheduled.trainingInformation.trainingProgram}</td>
				<td>${trainingScheduled.trainingInformation.technology}</td>
				<td>${trainingScheduled.trainingInformation.trainingDescription}</td>  
				<td>${trainingScheduled.trainingInformation.tier}</td> 
				<td><c:forEach items="${employees}" var="employee" varStatus="status">
						<c:if test="${trainingScheduled.trainerId==employee.employeeId}">
							${employee.employeeName}		
						</c:if>  
					</c:forEach></td>
				<c:set var="date" value='${trainingScheduled.schedule.date}'></c:set>						
				<td><fmt:formatDate type="date" value="${date}" /></td>   
				<td>${trainingScheduled.status}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</c:when>
<c:otherwise>
		<h2>No History Available.</h2>
</c:otherwise>
</c:choose></td>
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