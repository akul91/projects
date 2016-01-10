<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="../js/SpryMenuBar.js" type="text/javascript"></script>
<link href="../css/SpryMenuBarVertical.css" rel="stylesheet" type="text/css" />
</head>

<body>
<ul id="MenuBar1" class="MenuBarVertical">  
  <li><strong>MENU</strong></li>
  <li><a href="taddVenue.htm">Add a Venue</a></li>
  <li><a href="clistTraining.htm">Post Training</a></li>
  <li><a href="tstatus.htm">View Current Status of Training</a></li>
  <li><a href="cancelTraining.htm">Cancel Training</a></li>
  <li><a href="tdeleteFromList.htm">Delete User</a></li>
  <li><a href="cviewFeedback.htm">View Feedback of Posted Training</a></li>
  <li><a href="thistory.htm">View History</a></li>
  <li><a href="changePassword.htm">Change Password</a></li>
  <li><a href="clogout.htm">Logout</a></li>
</ul>
<script type="text/javascript">
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgRight:"../images/SpryMenuBarRightHover.gif"});
</script>
</body>
</html>