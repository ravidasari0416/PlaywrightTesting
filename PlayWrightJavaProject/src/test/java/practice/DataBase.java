package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBase {
	private static final String DB_URL = "jdbc:mysql//localhost:3306/creds";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "Ravi1996@";

	public static String[] getCredsByRole(String role) {
		String[] creds = new String[2];

		String query = "SELECT username, password FROM creds.user_credentials WHERE role = ?";

		try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = con.prepareStatement(query);) {

			ps.setString(1, role);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				creds[0] = rs.getString("username");
				creds[1] = rs.getString("password");

				return creds;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
