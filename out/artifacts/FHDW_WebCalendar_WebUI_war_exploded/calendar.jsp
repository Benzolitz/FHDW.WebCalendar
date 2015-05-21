<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kalender</title>


<link rel="stylesheet" type="text/css" href="stylesheets/all.css" />
<link rel="stylesheet" type="text/css" href="stylesheets/calender.css" />


<script src="scripts/jquery-1.11.2.min.js"></script>
<script src="scripts/calender.js"></script>

</head>
<body>
	<div id="header"></div>

	<div id="info"></div>

	<div id="calender">
		<%
			Date date = new Date();
			for(int i = 0; i < 7; i++)
			{
				out.print("<div class='calenderDay'>");
				out.print("<div class='calenderDayHead'>" + date.toString() + "</div>");
				out.print("<div class='calenderDayBody'>");
				
				for(int j = 0; j < 24; j++)
				{
					out.print("<div class='calenderDayBodyHour'>");
					
					out.print("<div class='calenderDayBodyHourHalf'>");
					out.print("</div>"); // .calenderDayBodyHourHalf
					
					if(j != 23)
					{ 
						
						out.print("<div class='calenderDayBodyHourHalf'>");
						out.print("</div>"); // .calenderDayBodyHourHalf						
					}
					else
					{
						
						out.print("<div class='calenderDayBodyHourHalfLast'>");
						out.print("</div>"); // .calenderDayBodyHourHalfLast							
					}
					
					out.print("</div>"); // .calenderDayBodyHour
				}
				
				out.print("</div>"); // .calenderDayBody
				out.print("</div>"); // .calenderDay
			}
		%>
	</div>

</body>
</html>