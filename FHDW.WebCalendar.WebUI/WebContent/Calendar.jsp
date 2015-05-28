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
				<input id="btnNewEvent" type="button" value="Neuer Termin" />
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
			<table style="width: 100%">
				<thead>
					<tr>
						<td style="width: 2%;"></td>
						<th style="width: 14%;">Mo.</th>
						<th style="width: 14%;">Di.</th>
						<th style="width: 14%;">Mi.</th>
						<th style="width: 14%;">Do.</th>
						<th style="width: 14%;">Fr.</th>
						<th style="width: 14%;">Sa.</th>
						<th style="width: 14%;">So.</th>
					</tr>
				</thead>
				<tbody>
					<tr style="height: 45px;">
						<td style="border-bottom: 2px solid #000;"></td>
						<td style="border-bottom: 2px solid #000;"></td>
						<td style="border-bottom: 2px solid #000;"></td>
						<td style="border-bottom: 2px solid #000;"></td>
						<td style="border-bottom: 2px solid #000;"></td>
						<td style="border-bottom: 2px solid #000;"></td>
						<td style="border-bottom: 2px solid #000;"></td>
						<td style="border-bottom: 2px solid #000;"></td>
					</tr>
					<tr style="height: 10px;">
						<td>00</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>01</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>02</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>03</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>04</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>05</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>06</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>07</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>08</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>09</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>10</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>11</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>12</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>13</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>14</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>15</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>16</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>17</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>18</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>19</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>20</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>21</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>22</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td>23</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>