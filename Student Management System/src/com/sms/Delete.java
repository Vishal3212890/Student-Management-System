package com.sms;

import javax.swing.*;
import java.sql.*;

public class Delete {
	public Delete() {

		UIManager.put("OptionPane.okButtonText", "Delete");

		String studentId = JOptionPane.showInputDialog(null, "Enter Student ID", "Delete Record", JOptionPane.YES_NO_OPTION);

		UIManager.put("OptionPane.okButtonText", "OK");
		if (studentId != null) {
			if (studentId.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please Enter Student ID");
			} else if (!studentId.contains("JTP-") || studentId.length() != 10) {
				JOptionPane.showMessageDialog(null,
						"<html>Invalid Student ID !<br>Please Check the Student ID and try again.<br>@example : JTP-XXXXXX</html>");
			} else {
				try {
					Connection con = DAO.createConnection();

					PreparedStatement ps = con.prepareStatement("DELETE FROM STUDENTRECORD WHERE STUDENT_ID = ?");
					ps.setString(1, studentId);

					int z = JOptionPane.showConfirmDialog(null, "Are you sure to want to delete the Record ?",
							"Select an Option", JOptionPane.YES_NO_OPTION);
					if (z == 0) {
						if (ps.executeUpdate() == 1) {
							JOptionPane.showMessageDialog(null, "Deleted sucessfully !");
						} else {
							JOptionPane.showMessageDialog(null, "Student does not exist!");
						}
					}
					con.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error while deleting !");
				}
			}
		}
	}
}