package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/Login")
public class Login extends javax.servlet.http.HttpServlet
{
	protected void doPost(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, IOException
	{
		Enumeration<String> test = request.getParameterNames();
		
		String nextJSP;
		// TODO: Korrekte Abfrage einfügen!
		if(request.getParameter("username") == request.getParameter("password"))
		{
			nextJSP = "/calendar.jsp";
		}
		else
		{
			nextJSP = "/login.jsp?failure=true&error=aaaaaaaaaaaaaaa aaaaa aaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaa aaaa aaaaaaaaa";
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
	}
	
	protected void doGet(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, IOException
	{
		
	}
}
