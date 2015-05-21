
public class TestConnection
{

	public static void main(String[] args) {
		
		WebCalendarRepo test = new WebCalendarRepo();
		test.CheckUser(null);
		/*
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl = "jdbc:mysql://localhost/";
			String connectionUser = "root";
			String connectionPassword = "";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=rootpassword"); 
					stmt=conn.createStatement();
					int Result=stmt.executeUpdate("CREATE DATABASE databasename");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM employees");
			while (rs.next()) {
				String id = rs.getString("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				System.out.println("ID: " + id + ", First Name: " + firstName
						+ ", Last Name: " + lastName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		*/
	}

}
