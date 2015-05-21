<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FHDW Webkalender</title>
</head>
<body>
	<jsp:forward page="Controller">
		<%
			if(session == null)
			{
		%>
		<jsp:param value="null" name="sessionID" />
		<%
			}
			else
			{
		%>
		<jsp:param value="<%=session.getId()%>" name="sessionID" />
		<%
			}
		%>
	</jsp:forward>
</body>
</html>