package com.sms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class AllRecord {
	JFrame frame, mainform;
	JLabel closeLabel;
	JScrollPane scrollPane;
	JTable table;
	Dimension dimension;
	DefaultTableModel model;
	ResultSet rs;
	ResultSetMetaData rsmd;
	int col;
	String heading[];

	AllRecord(JFrame mainform) {
		this.mainform = mainform;
		dimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame = new JFrame("Insert Record");
		frame.setBounds((dimension.width - 1000) / 2, (dimension.height - 550) / 2, 1000, 600);
		frame.setLayout(new BorderLayout());
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/student.png"));
		frame.setContentPane(new JLabel(new ImageIcon("src/images/allRecordImage.png")));
		frame.setUndecorated(true);

		closeLabel = new JLabel(new ImageIcon("src/images/close1.png"));
		closeLabel.setToolTipText("Close");
		closeLabel.setBounds(955, 20, 32, 32);
		closeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeLabel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				closeLabel.setIcon(new ImageIcon("src/images/close2.png"));
			}

			public void mouseExited(MouseEvent me) {
				closeLabel.setIcon(new ImageIcon("src/images/close1.png"));
			}

			public void mouseClicked(MouseEvent me) {
				mainform.setEnabled(true);
				frame.dispose();
			}
		});
		frame.add(closeLabel);
		
		model = new DefaultTableModel(0, 18);

		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(20, 80, 960, 500);
		frame.add(scrollPane);
		for (int i = 0; i <= 17; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		table.getColumnModel().getColumn(5).setPreferredWidth(250);
		table.getColumnModel().getColumn(7).setPreferredWidth(200);
		insertData();

		frame.setVisible(true);
	}

	void insertData() {
		try {
			Connection con = DAO.createConnection();

			PreparedStatement ps = con.prepareStatement("Select * from STUDENTRECORD");

			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			col = rsmd.getColumnCount();
			heading = new String[col];
			for (int i = 0; i < col; i++) {
				heading[i] = rsmd.getColumnName(i + 1);
			}
			model.setColumnIdentifiers(heading);
			Object[] obj = new Object[col];
			while (rs.next()) {
				for (int i = 0; i < col; i++) {
					obj[i] = rs.getObject(i + 1);
				}
				model.addRow(obj);
			}
			rs.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error while fetching details !");
		}
	}
}