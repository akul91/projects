<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <c:if test="${employeeDetails.employeeCategory!='e'}">
	<% response.sendRedirect("clogout.htm"); %>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Enrollment Status</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<style type="text/css" title="currentStyle">
@import "../css/demo_page.css";

@import "../css/demo_table.css";
</style>
<script type="text/javascript" language="javascript"
	src="../js/jquery.js"></script>
<script type="text/javascript" language="javascript"
	src="../js/jquery.dataTables.js"></script>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#example').dataTable({
			"sPaginationType" : "full_numbers"
		});
	});
</script>
<script type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
	<header> <jsp:include page="header.jsp" flush="true" /> <!-- end .header -->
	</header>
	<div class="container">
		<div class="sidebar1">
			<jsp:include page="employeeMenu.jsp" flush="true" />
			<!-- end .sidebar1 -->
		</div>
		<div class="content">
<div style='float:right;margin-right:10px'> Welcome <a href="cloggedInEmployee.htm">${employeeDetails.employeeName}</a></div>
<h2><b>Your current status of enrollment:-</b></h2>
			<table>
				<tr>
					<td><c:choose>
							<c:when test="${fn:length(appliedList) > 0}">
								<table class="display" id="example">
									<thead>
										<tr>
											<th>Program</th>
											<th>Technology</th>
											<th>Topics</th>
											<th>Trainer's Name</th>
											<th>Date</th>
											<th>Venue</th>
											<th>Timings</th>
											<th>Status</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${appliedList}" var="enrollment"
											varStatus="status">
											<tr class="<c:if test="${status.count % 2 == 0}">even</c:if>">
												<td>${enrollment.trainings.trainingInformation.trainingProgram}</td>
												<td>${enrollment.trainings.trainingInformation.technology}</td>
												<td>${enrollment.trainings.trainingInformation.trainingDescription}</td>
												<c:forEach items="${employees}" var="employee"
														varStatus="status">
														<c:if test="${enrollment.trainings.trainerId==employee.employeeId}">
															<td>${employee.employeeName}</td> 			
														</c:if>
													</c:forEach>
												<c:set var="timings" value=' '></c:set>
													<c:set var="date" value='${enrollment.trainings.schedule.date}'></c:set>

													<c:forEach items="${location}" var="location"
														varStatus="status">
														<c:if test="${enrollment.trainings.schedule.locationId==location.locationId}">
															<c:set var="combined"
																value='${location.location},${location.floor}'></c:set>
														</c:if>
													</c:forEach>

													<c:set var="timings" value=' '></c:set>
				
														<c:choose>
					<c:when test="${enrollment.trainings.schedule.timings=='1'}">
						<c:set var="timings" value='9AM-11AM'></c:set>  
					</c:when>
					<c:when test="${enrollment.trainings.schedule.timings=='2'}">
						<c:set var="timings" value='11AM-1PM'></c:set>  
					</c:when>
					<c:when test="${enrollment.trainings.schedule.timings=='3'}">
						<c:set var="timings" value='2PM-4PM'></c:set>  
					</c:when>
					<c:when test="${enrollment.trainings.schedule.timings=='4'}">
						<c:set var="timings" value='4PM-6PM'></c:set>  
					</c:when>
					<c:when test="${enrollment.trainings.schedule.timings=='12'}">
						<c:set var="timings" value='9AM-1PM'></c:set>  
					</c:when>
					<c:when test="${enrollment.trainings.schedule.timings=='23'}">
						<c:set var="timings" value='11AM-1PM, 2PM-4PM'></c:set>  
					</c:when>
					<c:when test="${enrollment.trainings.schedule.timings=='34'}">
						<c:set var="timings" value='2PM-6PM'></c:set>  
					</c:when>
					<c:when test="${enrollment.trainings.schedule.timings=='1234'}">
						<c:set var="timings" value='9AM-6PM'></c:set>  
					</c:when>
					<c:when test="${enrollment.trainings.schedule.timings=='234'}">
						<c:set var="timings" value='11AM-1PM, 2PM-6PM'></c:set>  
					</c:when>
					<c:when test="${enrollment.trainings.schedule.timings=='123'}">
						<c:set var="timings" value='9AM-1PM, 2PM-4PM'></c:set>  
					</c:when>
				</c:choose> 

												<td width=15%><fmt:formatDate type="date" value="${date}" /></td>
												<td>${combined}</td>
												<td>${timings}</td>
							
												<c:if test="${enrollment.statusId==1}">
													<td>Waiting for Approval</td>
												</c:if>
												<c:if test="${enrollment.statusId==2}">
													<td>Approved</td>
												</c:if>
												<c:if test="${enrollment.statusId==3}">
													<td>Rejected</td>
												</c:if>
												<c:if test="${enrollment.statusId==4}">
													<td>Canceled</td>
												</c:if>
												<c:if test="${enrollment.statusId==5}">
													<td>Feedback Pending</td>
												</c:if>
												<c:if test="${enrollment.statusId==6}">
													<td>Feedback Submitted</td>
												</c:if>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
								<h2>You have not enrolled in any training yet.</h2>
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