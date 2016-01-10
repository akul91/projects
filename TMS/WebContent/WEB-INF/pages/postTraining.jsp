<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Post Training</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/sexybuttons.css" type="text/css" />
<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />


<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script>
	$(document).ready(function() {
		$("#datepicker").datepicker({
			dateFormat : 'dd-mm-yy',
			minDate : 1,
			maxDate : '+1M',
			numberOfMonths : 2
		});
	});
	$(document).ready(function() {
		$("#datepickerrepost").datepicker({
			dateFormat : 'dd-mm-yy',
			minDate : 1,
			maxDate : '+1M',
			numberOfMonths : 2
		});
	});
	$(document).ready(function() {
		$("#datetry").hide();
	});

	$(document).ready(function() {
		$("#datetryrepost").hide();
	});

	$(document).ready(function() {
		$("#getLoc").click(function() {

			$("#getLoc").hide();
			$("#addSes").hide();
		});
	});

	$(document).ready(function() {
		$("#addSes").click(function() {

			$("#addSes").hide();
			$("#getLoc").hide();
		});
	});

	function click() {
		$(document).ready(function() {
			/* $("#datediv").click(function(){
			 */
			$("#datediv").hide();
			$("#datetry").show();
			/* })
			 */});

	}

	function clickrepost() {
		$(document).ready(function() {
			/* $("#datediv").click(function(){
			 */
			$("#datediv").hide();
			$("#datetryrepost").show();
			/* })
			 */});

	}
</script>

<script>
	function selectTo() {
		var firstFrom = document.getElementById("firstFrom").value;

		if (firstFrom == 0) {
			/*
			
			
			 Some more to write
			 */
		}

		if (window.XMLHttpRequest) {

			xmlhttp = new XMLHttpRequest();
		} else {

			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("timeFirstTodiv").innerHTML = xmlhttp.responseText;
			}
		}
		xmlhttp.open("POST", "cSelectTo.htm?firstFrom=" + firstFrom, true);
		xmlhttp.send();
	}

	function selectTorepost() {
		var firstFrom = document.getElementById("firstFrom").value;

		if (firstFrom == 0) {
			document.getElementById("timeFirstFromdiv").innerHTML = "select starting time of session";
			return;
			/*
			
			
			 Some more to write
			 */
		}

		if (window.XMLHttpRequest) {

			xmlhttp = new XMLHttpRequest();
		} else {

			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("timeFirstTodiv").innerHTML = xmlhttp.responseText;
			}
		}
		xmlhttp
				.open("POST", "cSelectTorepost.htm?firstFrom=" + firstFrom,
						true);
		xmlhttp.send();
	}

	function time() {

		var date = document.getElementById("datepicker").value;

		if (date != 0) {
			if (window.XMLHttpRequest) {

				xmlhttp = new XMLHttpRequest();
			} else {

				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

					document.getElementById("timeFirstFromdiv").innerHTML = xmlhttp.responseText;
				}
			}
			document.getElementById("locationdiv").innerHTML = "";
			document.getElementById("error").innerHTML = "";
			document.getElementById("retrieveslotdiv").innerHTML = "";
			document.getElementById("successdiv").innerHTML = "";
			xmlhttp.open("POST", "ctime.htm?date= " + date, true);
			xmlhttp.send();
		} else {
			document.getElementById("timediv").innerHTML = "";
		}
		document.getElementById("locationdiv").innerHTML = "";
		document.getElementById("error").innerHTML = "";
		document.getElementById("retrieveslotdiv").innerHTML = "";
		document.getElementById("successdiv").innerHTML = "";

	}

	function timerepost() {

		var date = document.getElementById("datepickerrepost").value;

		if (date != 0) {
			if (window.XMLHttpRequest) {

				xmlhttp = new XMLHttpRequest();
			} else {

				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

					document.getElementById("timeFirstFromdiv").innerHTML = xmlhttp.responseText;
				}
			}
			document.getElementById("locationdiv").innerHTML = "";
			document.getElementById("error").innerHTML = "";
			document.getElementById("retrieveslotdiv").innerHTML = "";
			document.getElementById("successdiv").innerHTML = "";
			xmlhttp.open("POST", "ctimerepost.htm?date=" + date, true);
			xmlhttp.send();
		} else {
			document.getElementById("timediv").innerHTML = "date should not be left";
		}
		document.getElementById("locationdiv").innerHTML = "";
		document.getElementById("error").innerHTML = "";
		document.getElementById("retrieveslotdiv").innerHTML = "";
		document.getElementById("successdiv").innerHTML = "";

	}

	function trainName() {
		var trainName = document.getElementById("trainName").value;

		if (/[!@#$%^&*()]/.test(trainName)) {
			document.getElementById("spanTrainName").innerHTML = "Training Program should not contain  special characters";
			return false;
		} else {
			document.getElementById("spanTrainName").innerHTML = "";

		}

	}

	function tech() {
		//("ok");
		var tech = document.getElementById("tech").value;
		if (tech == 0) {
			document.getElementById("spanTier").innerHTML = "Tier should not be left empty";
			return;
		}
		if (tech == 7) {
			document.getElementById("spanTech").innerHTML = "<br><input type='text' id='techtext' onblur='return techbox()'>";
		} else {
			document.getElementById("spanTech").innerHTML = "";
		}

	}

	function techbox() {
		var technology = document.getElementById("techtext").value;
		//(technology);
		if (technology.length == 0) {

			document.getElementById("spanTechText").innerHTML = "Technology Text Box  should not be left empty";
			return false;

		} else {
			document.getElementById("spanTechText").innerHTML = "";
			return true;
		}
	}

	function start() {
		var tier = "0,";
		try {
			if (document.getElementById("tier1").value == "1") {

				if (document.getElementById("tier1").checked) {
					tier = tier + "1,";
				}
			}
		} catch (err) {

		}
		try {
			if (document.getElementById("tier2").value == "2") {
				if (document.getElementById("tier2").checked) {
					tier = tier + "2,";
				}
			}
		} catch (err) {

		}
		try {
			if (document.getElementById("tier3").value == "3") {
				if (document.getElementById("tier3").checked) {
					tier = tier + "3,";
				}
			}
		} catch (err) {

		}
		try {
			if (document.getElementById("tier4").value == "4") {
				if (document.getElementById("tier4").checked) {
					tier = tier + "4";

				}
			}
		} catch (err) {

		}

		if ((tier.length - 2) > 0) {
			document.getElementById("spanTier").innerHTML = "";
		} else {
			document.getElementById("spanTier").innerHTML = "Please select a tier";
			return;
		}

		var trainName = document.getElementById("trainName").value;
		if (trainName.length == 0) {
			document.getElementById("spanTrainName").innerHTML = "Training Program  should not be left empty";
			return;
		} else {
			document.getElementById("spanTrainName").innerHTML = "";
		}

		var txtArea = document.getElementById("description");
		if (txtArea.length == 0) {
			document.getElementById("spanDescription").innerHTML = "Description should not be left empty";
			return;

		} else {
			document.getElementById("spanDescription").innerHTML = "";
		}

		var tech = document.getElementById("tech").value;
		if (tech == 0) {
			document.getElementById("spanTech").innerHTML = "Technology  should not be left empty";
			return;
		} else if (tech == 7) {
			if (!techbox()) {
				//(false);
				return false;
			} else {
				click();
			}
		} else {
			document.getElementById("spanTech").innerHTML = "";
		}

		click();

	}
	function start1() {
		clickrepost();
	}
	function datetry() {
		document.getElementById("timeFirstFromdiv").innerHTML = "";
		document.getElementById("timeFirstTodiv").innerHTML = "";
		document.getElementById("locationdiv").innerHTML = "";
		document.getElementById("error").innerHTML = "";
		document.getElementById("retrieveslotdiv").innerHTML = "";
		document.getElementById("successdiv").innerHTML = "";

		time();

	}

	function datetryrepost() {

		document.getElementById("timeFirstFromdiv").innerHTML = "";
		document.getElementById("timeFirstTodiv").innerHTML = "";
		document.getElementById("locationdiv").innerHTML = "";
		document.getElementById("error").innerHTML = "";
		document.getElementById("retrieveslotdiv").innerHTML = "";
		document.getElementById("successdiv").innerHTML = "";

		timerepost();

	}

	function fromTo() {

		var toTime = document.getElementById("firstTo").value;

		if (toTime == 0) {
			document.getElementById("toTime").innerHTML == "please enter the first session end time";
			return;
		}
		if (window.XMLHttpRequest) {

			xmlhttp = new XMLHttpRequest();
		} else {

			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var check = xmlhttp.responseText;
				if (check.indexOf("Sorry123") != -1) {
					document.getElementById("locationdiv").innerHTML = "No Location available. ";
					return;
				} else {
					document.getElementById("locationdiv").innerHTML = xmlhttp.responseText;
				}
			}
		}
		xmlhttp.open("POST", "clocationUsingSingleSession.htm?firstTo="
				+ toTime);
		xmlhttp.send();

	}

	function fromTorepost() {

		var toTime = document.getElementById("firstTo").value;

		if (toTime == 0) {
			document.getElementById("toTime").innerHTML == "please enter the first session end time";
			return;
		}
		if (window.XMLHttpRequest) {

			xmlhttp = new XMLHttpRequest();
		} else {

			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var check = xmlhttp.responseText;
				if (check.indexOf("Sorry123") != -1) {
					document.getElementById("locationdiv").innerHTML = "No Location available. ";
					return;
				} else {
					document.getElementById("locationdiv").innerHTML = xmlhttp.responseText;
				}
			}
		}
		xmlhttp.open("POST", "clocationUsingSingleSessionrepost.htm?firstTo="
				+ toTime);
		xmlhttp.send();

	}
	function storeToDatabase() {

		var tier = "0";
		try {
			if (document.getElementById("tier1").value == "1") {

				if (document.getElementById("tier1").checked) {
					tier = tier + "1,";
				}
			}
		} catch (err) {

		}
		try {
			if (document.getElementById("tier2").value == "2") {
				if (document.getElementById("tier2").checked) {
					tier = tier + "2,";
				}
			}
		} catch (err) {

		}
		try {
			if (document.getElementById("tier3").value == "3") {
				if (document.getElementById("tier3").checked) {
					tier = tier + "3,";
				}
			}
		} catch (err) {

		}
		try {
			if (document.getElementById("tier4").value == "4") {
				if (document.getElementById("tier4").checked) {
					tier = tier + "4";

				}
			}
		} catch (err) {

		}

		if ((tier.length - 1) > 0) {
			document.getElementById("spanTier").innerHTML = "";
		} else {
			document.getElementById("spanTier").innerHTML = "Please select a tier";
			return;
		}

		var trainName = document.getElementById("trainName").value;
		var tech = document.getElementById("tech").value;
		var txtArea = document.getElementById("description").value;
		//var Tier = document.getElementById("Tier").value;

		if (tech == "7") {
			tech = document.getElementById("techtext").value;
		}
		var listOfLocation = document.getElementById("listOfLocation").value;

		if (window.XMLHttpRequest) {

			xmlhttp = new XMLHttpRequest();
		} else {

			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				if (xmlhttp.responseText.indexOf("ctrainingsuccessrepost") != -1) {

					window.location = "ctrainingsuccessrepost.htm";
				}
				if (xmlhttp.responseText.indexOf("ctrainingsuccess") != -1) {

					window.location = "ctrainingsuccess.htm";
				}

			}
		}
		xmlhttp.open("POST", "cfirstSlotDatabase.htm?trainName=" + trainName
				+ "&tech=" + tech + "&txtArea=" + txtArea + "&Tier=" + tier
				+ "&listOfLocation=" + listOfLocation, true);
		xmlhttp.send();
	}

	function storeToDatabaserepost() {
		var listOfLocation = document.getElementById("listOfLocationrepost").value;

		if (window.XMLHttpRequest) {

			xmlhttp = new XMLHttpRequest();
		} else {

			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				if (xmlhttp.responseText.indexOf("ctrainingsuccessrepost") != -1) {

					window.location = "ctrainingsuccessrepost.htm";
				}
				if (xmlhttp.responseText.indexOf("ctrainingsuccess") != -1) {

					window.location = "ctrainingsuccess.htm";
				}
				
				}
		}
		xmlhttp.open("POST",
				"cfirstSlotDatabaserepost.htm?listOfLocationrepost="
						+ listOfLocation, true);
		xmlhttp.send();
	}
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
			<div style='float: right; margin-right: 10px'>
				Welcome ${employeeDetails.employeeName}</a>
			</div>
			<table>
				<c:choose>
					<c:when test="${trainingInfoObject.tier ==null}">
						<tr>
							<td>Training Program:</td>
							<td><input type="text" id="trainName" onchange="trainName()"></td>
							<td><font color="red"><h5>
										<span id="spanTrainName"></span>
									</h5></font></td>
						</tr>

						<tr>
							<td>Technology:</td>
							<!-- <td><input type="text" id="tech" onchange="tech()"></td>
								 -->
							<td><select id="tech" onchange="tech()">
									<option value="0">---select---</option>
									<option value="1">Java</option>
									<option value="2">SAP</option>
									<option value="3">Cloud Computing</option>
									<option value="4">Web Designing</option>
									<option value="5">Scripting Languages</option>
									<option value="6">.Net</option>
									<option value="7">Others</option>
							</select></td>
							<td><font color="red"><h5>
										<span id="spanTech"></span>
									</h5></font></td>
						</tr>
						<tr>
							<td><font color="red"><h5>
										<span id="spanTechText"></span>
									</h5></font></td>
						</tr>
						<tr>
							<td>Topics to be covered:
								<td><textarea id="description"
										onkeypress="return ( this.value.length < 250 );" rows="10"
										cols="25"></textarea></td>
								<td><font color="blue">Description can be of 250
										characters only </font></td>
								</h5>
								</font>
							</td>

						</tr>
						<tr>
							<td></td>
							<td></td>
							<td><font color="red"><h5>
										<span id="spanDescription"></span>
						</tr>
						<tr>
							<td>Tier of the nominees:</td>

							<td><input type="checkbox" value="1" id="tier1">Tier
									1</input></td>
							<td><font color="red"><h5>
										<span id="spanTier"></span>
									</h5></font></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="checkbox" value="2" id="tier2">Tier
									2</input></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="checkbox" value="3" id="tier3">Tier
									3</input></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="checkbox" value="4" id="tier4">Tier
									4</input></td>

						</tr>

					</c:when>
					<c:otherwise>
						<tr>
							<td>Training Program:</td>
							<td>${trainingInfoObject.trainingProgram }</td>

						</tr>

						<tr>
							<td>Technology:</td>
							<td>${trainingInfoObject.technology }</td>

						</tr>

						<tr>
							<td>Topics to be covered:</td>
							<td>${trainingInfoObject.trainingDescription}</td>

						</tr>
						<tr>
							<td>Tier of the nominees:</td>
							<td>${trainingInfoObject.tier}</td>
						</tr>

					</c:otherwise>
				</c:choose>
			</table>
			<c:choose>
				<c:when test="${trainingInfoObject.tier ==null}">
					<div id="datediv">
						<button type="button" class="sexybutton sexylarge"
							onclick="return start()">
							<span><span><span class="ok">Click to Add
										Venue</span></span></span>
						</button>
					</div>
				</c:when>
				<c:otherwise>
					<div id="datediv">
						<button type="button" class="sexybutton sexylarge"
							onclick="return start1()">
							<span><span><span class="ok">Click to Add
										Venue</span></span></span>
						</button>
					</div>
				</c:otherwise>
			</c:choose>

			<div id="datetry">
				Select date on which you want to give Training:<input type="text"
					name="date" id="datepicker" onchange="datetry()"></input>
			</div>
			<div id="datetryrepost">
				Select date on which you want to give Training:<input type="text"
					name="date" id="datepickerrepost" onchange="datetryrepost()"></input>
			</div>

			<div id="dateerror"></div>
			<div id="timeFirstFromdiv"></div>
			<div id="timeFirstTodiv"></div>
			<div id="timeSecondFromdiv"></div>
			<div id="timeSecondTodiv"></div>


			<div id="locationdiv"></div>
			<font color="red"><div id="error"></div></font>
			<div id="retrieveslotdiv"></div>
			<div id="successdiv"></div>

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