package com.sms;

import java.sql.*;

public class DAO {
	public static Connection createConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem", "root",
				"33365627613");

		return con;
	}
}