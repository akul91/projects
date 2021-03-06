<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
         <c:if test="${employeeDetails.employeeCategory!='p'}">
	<% response.sendRedirect("clogout.htm"); %>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Training Information</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/sexybuttons.css" type="text/css" />
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

<table cellpadding="2">
	<tr>
			<th>EmployeeId</th>	
				<td>${trainingRequested.trainerId}</td>	</tr>
			<tr>
			<th>Employee Name</th>	
			<td>	<c:forEach items="${employees}" var="employee" varStatus="status">
						<c:if test="${employee.employeeId==trainingRequested.trainerId}">
							${employee.employeeName}
						</c:if>  
					</c:forEach></td>
			</tr>
			<tr>
			<th>Program</th>	
			<td>${trainingRequested.trainingInformation.trainingProgram}</td></tr>
			<tr>
			<th>Technology</th>	
			<td>${trainingRequested.trainingInformation.technology}</td></tr>
			<tr>
			<th>Description</th>	
			<td>${trainingRequested.trainingInformation.trainingDescription}</td></tr>
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
					<c:when test="${trainingRequested.schedule.timings=='12'}">
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
					
			<tr>
				<th>Date</th>	
				<td><fmt:formatDate type="date" value="${date}" /></td>
			</tr>
			<tr>
				<th>Timings</th>	
				<td>${timings}</td>
			</tr> 
			<tr>
				<th>Venue</th>	
				<td>${combined}</td>
			</tr>
<td>
<form:form  action="pPostApprov.htm" commandName="employee" >
<td colspan="2"><button type="submit" class="sexybutton sexylarge"><span><span><span class="accept">Accept</span></span></span></button>
</form:form> </td>
<td>
<form:form  action="pPostReject.htm" commandName="employee" >
<td colspan="2"><button type="submit" class="sexybutton sexylarge"><span><span><span class="decline">Decline</span></span></span></button>	
</form:form>
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