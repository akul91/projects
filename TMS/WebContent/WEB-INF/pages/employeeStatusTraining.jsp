<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
	            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${employeeDetails.employeeCategory!='e'}">
	<% response.sendRedirect("clogout.htm"); %>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Training Status</title>
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
<jsp:include page="employeeMenu.jsp" flush="true" />
<!-- end .sidebar1 -->
</div>
<div class="content">
<div style='float:right;margin-right:10px'> Welcome <a href="cloggedInEmployee.htm">${employeeDetails.employeeName}</a></div>
<h2><b>Your current status of training:-</b></h2>
<table>
<tr>
<td>
<c:choose>
<c:when test="${fn:length(postedList) > 0}">
	<table class="display" id="example">
		<thead>
		<tr>
			<th>Program</th>
			<th>Technology</th>
			<th>Topics</th>
			<th>Date</th>
			<th>Venue</th>
			<th>Timings</th>
			<th>Status</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${postedList}" var="trainingRequested" varStatus="status">
			<tr class="<c:if test="${status.count % 2 == 0}">even</c:if>">
			<td>${trainingRequested.trainingInformation.trainingProgram}</td>
			<td>${trainingRequested.trainingInformation.technology}</td>
			<td>${trainingRequested.trainingInformation.trainingDescription}</td>
			
			<c:set var="timings" value=' '></c:set>
					
					<c:set var="date" value='${trainingRequested.schedule.date}'></c:set>					
					
					<c:forEach items="${location}" var="location" varStatus="status">
						<c:if test="${trainingRequested.schedule.locationId==location.locationId}">
							<c:set var="combined" value='${location.location},${location.floor}'></c:set>		
						</c:if>  
					</c:forEach>
					
				
				<c:choose>
					<c:when test="${trainingRequested.schedule.timings=='1'}">
						<c:set var="timings" value='9AM-11AM'></c:set>  
					</c:when>
					<c:when test="${trainingRequested.schedule.timings=='2'}">
						<c:set var="timings" value='11AM-1PM'></c:set>  
					</c:when>
					<c:when test="${trainingRequested.schedule.timings=='3'}">
						<c:set var="timings" value='2PM-4PM'></c:set>  
					</c:when>
					<c:when test="${trainingRequested.schedule.timings=='4'}">
						<c:set var="timings" value='4PM-6PM'></c:set>  
					</c:when>
					<c:when test="${trainingScheduled.schedule.timings=='12'}">
						<c:set var="timings" value='9AM-1PM'></c:set>  
					</c:when>
					<c:when test="${trainingRequested.schedule.timings=='23'}">
						<c:set var="timings" value='11AM-1PM, 2PM-4PM'></c:set>  
					</c:when>
					<c:when test="${trainingRequested.schedule.timings=='34'}">
						<c:set var="timings" value='2PM-6PM'></c:set>  
					</c:when>
					<c:when test="${trainingRequested.schedule.timings=='1234'}">
						<c:set var="timings" value='9AM-6PM'></c:set>  
					</c:when>
					<c:when test="${trainingRequested.schedule.timings=='234'}">
						<c:set var="timings" value='11AM-1PM, 2PM-6PM'></c:set>  
					</c:when>
					<c:when test="${trainingRequested.schedule.timings=='123'}">
						<c:set var="timings" value='9AM-1PM, 2PM-4PM'></c:set>  
					</c:when>
				</c:choose>

				<td><fmt:formatDate type="date" value="${date}" /></td>   
				<td><c:out value="${combined}"/></td> 
				<td><c:out value="${timings}"/></td> 
			
			<td>${trainingRequested.status}</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
</c:when>
<c:otherwise>
	<h2>You have not posted any training</h2>
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