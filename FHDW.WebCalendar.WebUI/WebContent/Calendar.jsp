<%@page import="Controller.CalendarController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	Cookie calendarCookie = null;
	String userId = null;
	String redirect = null;
	Cookie[] cookies = request.getCookies();
	
	if (cookies != null)
	{
		for (Cookie cookie : cookies)
		{
			if (cookie.getName().equals("FHDW.WebCalendar")) calendarCookie = cookie;
		}
	}
	
	if (calendarCookie == null)
	{
		redirect = "Login.jsp?message=Sie muessen sich zuerst einloggen!";
	}
	else
	{
		String cookieValue = calendarCookie.getValue();
		String[] cookieValues = cookieValue.split("&");
		
		for (String value : cookieValues)
		{
			String[] val = value.split("=");
			
			if (val[0].toLowerCase().equals("userid"))
			{
				userId = val[1];
			}
		}
		
		if (userId == null)
		{
			redirect = "Login.jsp?message=UserId konnte nicht gefunden werden!";
			CalendarController.Logout(response, cookies);
		}
	}
	
	if (redirect != null)
	{
		response.sendRedirect(redirect);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kalender</title>


<link rel="stylesheet" type="text/css" href="stylesheets/custom/all.css" />
<link rel="stylesheet" type="text/css" href="stylesheets/custom/calendar.css" />


<script src="scripts/framework/jquery-1.11.2.min.js"></script>
<script src="scripts/custom/calendar.js"></script>

</head>
<body>
	<div id="header">
		<input id="btnLogout" type="button" value="Logout" onClick="Logout()" />
	</div>
	<div id="calendarBox">
		<div id="userControls">
			<div id="divSearchBox">
				<input id="txtSearchBox" type="text" placeholder="Suche" />
			</div>
			<div id="divNewEvent">
				<input id="btnNewEvent" type="button" value="Neuer Termin" onClick="newEvent()" />
			</div>
			<div id="divArrows">
				<input id="btnLastWeek" type="button" value="&uarr;" onClick="getLastWeek()" /> <br />
				<input id="btnNextWeek" type="button" value="&darr;" onClick="getNextWeek()" />
			</div>
			<input id="ckiUserId" type="hidden" value="<%=userId%>" />
			<input id="hidCurrentWeek" type="hidden" />
			<input id="hidCurrentYear" type="hidden" />
		</div>
		<div id="calendar">
			<table id="tabCalendar">
				<thead>
					<tr>
						<td id="tdTime"></td>
						<th class="claHead">Mo.</th>
						<th class="claHead">Di.</th>
						<th class="claHead">Mi.</th>
						<th class="claHead">Do.</th>
						<th class="claHead">Fr.</th>
						<th class="claHead">Sa.</th>
						<th class="claHead">So.</th>
					</tr>
				</thead>
				<tbody>
					<tr id="trAllDay">
						<td class="claAllDay"></td>
						<td class="claAllDay"></td>
						<td class="claAllDay"></td>
						<td class="claAllDay"></td>
						<td class="claAllDay"></td>
						<td class="claAllDay"></td>
						<td class="claAllDay"></td>
						<td class="claAllDay"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">00</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">01</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">02</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">03</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">04</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">05</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">06</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">07</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">08</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">09</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">10</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">11</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">12</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">13</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">14</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">15</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">16</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">17</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">18</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">19</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">20</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">21</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">22</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claFullHour claTimeColumn">23</td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
						<td class="claFullHour"></td>
					</tr>
					<tr class="claTimeFrame">
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
						<td class="claHalfHour"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>