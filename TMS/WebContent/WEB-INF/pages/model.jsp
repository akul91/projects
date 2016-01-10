<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${flag=='time'}">
Select time of the session<br>From:
<select id='firstFrom' onchange='selectTo()' ><option value='0'>--select--</option>
<c:forEach var="start" begin="0" end="${fn:length(timeFrom)-1 }">
<c:if test="${start<=1}">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 AM</option>
</c:if>
<c:if test="${start>1}">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 PM</option>
</c:if>
</c:forEach>
</select>
</c:if>


<c:if test="${flag=='timerepost'}">
Select time of the session<br>From:
<select id='firstFrom' onchange='selectTorepost()()' ><option value='0'>--select--</option>
<c:forEach var="start" begin="0" end="${fn:length(timeFrom)-1 }">
<c:if test="${start<=1}">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 AM</option>
</c:if>
<c:if test="${start>1}">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 PM</option>
</c:if>
</c:forEach>
</select>
</c:if>


<c:if test="${flag=='timeTo'}">
&nbsp;&nbsp;&nbsp;To:  <select id='firstTo'  ><option value='0'>--select--</option>
<c:choose>
<c:when test="${from==9}">

<c:forEach var="start" begin="0" end="${fn:length(timeFrom)-1 }">
<c:if test="${start<1}">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 AM</option>
</c:if>
<c:if test="${start>=1}">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 PM</option>
</c:if>
</c:forEach>

</c:when>


<c:when test="${from==11}">
<c:forEach var="start" begin="1" end="${fn:length(timeFrom)-1 }">
<c:if test="${start<1}">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 AM</option>
</c:if>
<c:if test="${start>=1}">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 PM</option>
</c:if>
</c:forEach>

</c:when>

<c:when test="${from==2}">
<c:forEach var="start" begin="2" end="${fn:length(timeFrom)-1 }">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 PM</option>
</c:forEach>

</c:when>

<c:when test="${from==4}">
<c:forEach var="start" begin="3" end="${fn:length(timeFrom)-1 }">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 PM</option>
</c:forEach>

</c:when>


</c:choose>
</select><br>
<input type='button' name='Click to Get Location' id='getLoc' value='Click to Get Location' onclick='fromTo()' >
</c:if>



<c:if test="${flag=='timeTolocation'}">
&nbsp;&nbsp;&nbsp;To:  <select id='firstTo'  ><option value='0'>--select--</option>
<c:choose>
<c:when test="${from==9}">

<c:forEach var="start" begin="0" end="${fn:length(timeFrom)-1 }">
<c:if test="${start<1}">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 AM</option>
</c:if>
<c:if test="${start>=1}">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 PM</option>
</c:if>
</c:forEach>
</c:when>


<c:when test="${from==11}">
<c:forEach var="start" begin="1" end="${fn:length(timeFrom)-1 }">
<c:if test="${start<1}">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 AM</option>
</c:if>
<c:if test="${start>=1}">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 PM</option>
</c:if>
</c:forEach>
</c:when>
<c:when test="${from==2}">
<c:forEach var="start" begin="2" end="${fn:length(timeFrom)-1 }">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 PM</option>
</c:forEach>
</c:when>
<c:when test="${from==4}">
<c:forEach var="start" begin="3" end="${fn:length(timeFrom)-1 }">
<option value="${timeFrom[start] }">${timeFrom[start] }:00 PM</option>
</c:forEach>
</c:when>
</c:choose>
</select><br>
<input type='button' name='Click to Get Location' id='getLoc' value='Click to Get Location' onclick='fromTorepost()' >
</c:if>

<c:if test="${flag== 'location'}">
<c:if test="${locationlist.size()>0 }">
Select Location in which you want to give training :<select id='listOfLocation'><option value='0'>--select--</option>
<c:forEach items='${locationlist}' var='location'>
<option value='${location.locationId}'> ${location.location}</option>

</c:forEach>
</select><br>
<input type='button' value='Submit Training'  onclick='storeToDatabase()' ></input>
</c:if>
<c:if test="${locationlist.size()==0 }">
<c:out value="Sorry123"></c:out>
</c:if>
</c:if>

<c:if test="${flag== 'locationrepost'}">
<c:if test="${locationlist.size()>0 }">
Select Location in which you want to give training :<select id='listOfLocationrepost'><option value='0'>--select--</option>
<c:forEach items='${locationlist}' var='location'>
<option value='${location.locationId}'> ${location.location}</option>

</c:forEach>
</select><br>
<input type='button' value='Submit Training' onclick="storeToDatabaserepost()"></input>
</c:if>
<c:if test="${locationlist.size()==0 }">
<c:out value="Sorry123"></c:out>
</c:if>
</c:if>


<c:if test="${flag== 'success'}">

<c:out value='${message }'></c:out>

</c:if>

</body>
</html>