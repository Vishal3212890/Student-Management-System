package com.sms;

import java.sql.*;

public class TableCreator {
	public static void createUserTable() throws Exception {
		Connection con = DAO.createConnection();

		PreparedStatement ps = con.prepareStatement(
				"create table if not exists users(username varchar(50) primary key, password varchar(50), email varchar(50))");

		ps.executeUpdate();
	}

	public static void createStudentTable() throws Exception {
		Connection con = DAO.createConnection();

		PreparedStatement ps = con.prepareStatement(
				"CREATE TABLE IF NOT EXISTS STUDENTRECORD (STUDENT_ID VARCHAR(100) PRIMARY KEY, STUDENT_NAME VARCHAR(100),FATHER_NAME VARCHAR(100),GENDER VARCHAR(100), DOB DATE, HOUSE_ADDRESS VARCHAR(255), MOBILE_NO BIGINT, EMAIL VARCHAR(100), INTERMEDIATE VARCHAR(100), GRADUATION VARCHAR(100), POST_GRADUATION VARCHAR(100), OTHER_DIPLOMA VARCHAR(100), COURSE VARCHAR(100), DURATION INT, TOTAL_FEES INT NOT NULL, DISCOUNT INT, NET_FEES INT, PAYMENT_MODE VARCHAR(100), PHOTO LONGBLOB)");

		ps.executeUpdate();
	}
}