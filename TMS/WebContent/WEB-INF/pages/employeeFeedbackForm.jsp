<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
         <c:if test="${employeeDetails.employeeCategory!='e'}">
	<% response.sendRedirect("clogout.htm"); %>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Feedback Form</title>
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
<jsp:include page="employeeMenu.jsp" flush="true" />
<!-- end .sidebar1 -->
</div>
<div class="content">
<div style='float:right;margin-right:10px'> Welcome <a href="cloggedInEmployee.htm">${employeeDetails.employeeName}</a></div>

<form:form action="egiveFeedbackForm.htm" commandName="feedback">
	<h2>Feedback Form</h2><br>
	<table width=60%>
	<tr>
		<th>Training Program</th>
		<td>${trainingInfo.trainingInformation.trainingProgram}</td>
	</tr>
	<tr>	
		<th>Training Technology</th>
		<td>${trainingInfo.trainingInformation.technology}</td>
	</tr>
	<tr>		
		<th>Training Date</th>
		<td><c:set var="date" value='${trainingInfo.schedule.date}'></c:set>
			<fmt:formatDate type="date" value="${date}" /></td>
	</tr>
	</table><br><br>
	<input type="hidden" name="trainingId" value="${trainingInfo.trainingId}">
	<table>
		<tr>
			<td>How was the content presented by the trainer</td>
			<td><form:radiobutton path="optionOne" value="1" />1
			<td><form:radiobutton path="optionOne" value="2" />2
			<td><form:radiobutton path="optionOne" value="3" />3
			<td><form:radiobutton path="optionOne" value="4" />4
			<td><form:radiobutton path="optionOne" value="5" />5
		</tr>
		<tr>
		<td>How were his presentation skills?</td>
		    <td><form:radiobutton path="optionTwo" value="1" />1
			<td><form:radiobutton path="optionTwo" value="2" />2
			<td><form:radiobutton path="optionTwo" value="3" />3
			<td><form:radiobutton path="optionTwo" value="4" />4
			<td><form:radiobutton path="optionTwo" value="5" />5
		</tr>
		 <tr>
		<td>How was the trainer's interaction with the participants?</td>
		    <td><form:radiobutton path="optionThree" value="1" />1
			<td><form:radiobutton path="optionThree" value="2" />2
			<td><form:radiobutton path="optionThree" value="3" />3
			<td><form:radiobutton path="optionThree" value="4" />4
			<td><form:radiobutton path="optionThree" value="5" />5
		</tr>
		<tr>
		    <td>How was his time management?</td>
			<td><form:radiobutton path="optionFour" value="1" />1
			<td><form:radiobutton path="optionFour" value="2" />2
			<td><form:radiobutton path="optionFour" value="3" />3
			<td><form:radiobutton path="optionFour" value="4" />4
			<td><form:radiobutton path="optionFour" value="5" />5
		</tr>
	<tr>
	<td>Please comment your suggestions/improvements in the program</td>
	</tr>
	</table>
	<form:textarea rows="5" cols="50" path="comments"></form:textarea><br/>
	<button type="submit" class="sexybutton sexylarge"><span><span><span class="ok">Submit</span></span></span></button>
	
</form:form>

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