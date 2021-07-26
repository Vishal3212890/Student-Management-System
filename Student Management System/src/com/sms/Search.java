package com.sms;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.io.FileOutputStream;

public class Search implements ActionListener {
	
	JFrame frame, mainform;
	JLabel closeLabel, photoLabel;
	JLabel idLabel, nameLabel, fatherNameLabel, genderLabel, dobLabel, houseAddressLabel, mobileNumberLabel, emailLabel, intermediateLabel, graduationLabel, postGraduationLabel, diplomaLabel, courseLabel, durationLabel, totalFeesLabel, discountLabel, paymentModeLabel, netFeesLabel;
	JTextField idTextField, nameTextField, fatherNameTextField, genderTextField, dobTextField, houseAddressTextField, mobileTextField, emailTextField, intermediateTextField, graduationTextField, postGraduationTextField, diplomaTextField, courseTextField, durationTextField, totalFeesTextField, discountTextField, netFeesTextField, paymentModeTextField;
	JTextField searchTextField;
	JButton searchButton;
	Dimension dimension;

	public Search(JFrame mainform) {
		this.mainform = mainform;
		
		dimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame = new JFrame("Search Record");
		frame.setBounds((dimension.width - 1000) / 2, (dimension.height - 550) / 2, 1000, 600);
		frame.setLayout(new BorderLayout());
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/student.png"));
		frame.setContentPane(new JLabel(new ImageIcon("src/images/SearchImage.png")));
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

//		personal details
		photoLabel = new JLabel(new ImageIcon("src/images/photoSearch.png"));
		photoLabel.setBounds(320, 130, 100, 100);
		frame.add(photoLabel);

		idLabel = new JLabel("ID :");
		idLabel.setFont(new Font("verdana", Font.PLAIN, 16));		
		idLabel.setForeground(Color.pink);
		idLabel.setBounds(140, 170, 40, 30);
		frame.add(idLabel);
		
		nameLabel = new JLabel("Student's Name");
		nameLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		nameLabel.setForeground(Color.pink);
		nameLabel.setBounds(100, 250, 150, 30);
		frame.add(nameLabel);
		
		fatherNameLabel = new JLabel("Father's Name");
		fatherNameLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		fatherNameLabel.setForeground(Color.pink);
		fatherNameLabel.setBounds(100, 290, 150, 30);
		frame.add(fatherNameLabel);
		
		genderLabel = new JLabel("Gender/Sex");
		genderLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		genderLabel.setForeground(Color.pink);
		genderLabel.setBounds(100, 330, 150, 30);
		frame.add(genderLabel);
		
		dobLabel = new JLabel("Date of Birth");
		dobLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		dobLabel.setForeground(Color.pink);
		dobLabel.setBounds(100, 370, 150, 30);
		frame.add(dobLabel);
		
		houseAddressLabel = new JLabel("House Address");
		houseAddressLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		houseAddressLabel.setForeground(Color.pink);
		houseAddressLabel.setBounds(100, 410, 150, 30);
		frame.add(houseAddressLabel);
		
		mobileNumberLabel = new JLabel("Mobile Number");
		mobileNumberLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		mobileNumberLabel.setForeground(Color.pink);
		mobileNumberLabel.setBounds(100, 450, 150, 30);
		frame.add(mobileNumberLabel);
		
		emailLabel = new JLabel("Email - ID");
		emailLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		emailLabel.setForeground(Color.pink);
		emailLabel.setBounds(100, 490, 150, 30);
		frame.add(emailLabel);
		
		idTextField = new JTextField();
		idTextField.setEditable(false);
		idTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		idTextField.setForeground(Color.green);
		idTextField.setBounds(180, 170, 120, 30);
		idTextField.setOpaque(false);
		frame.add(idTextField);
		
		nameTextField = new JTextField();
		nameTextField.setEditable(false);
		nameTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		nameTextField.setForeground(Color.green);
		nameTextField.setBounds(250, 250, 200, 30);
		nameTextField.setOpaque(false);
		frame.add(nameTextField);
		
		fatherNameTextField = new JTextField();
		fatherNameTextField.setEditable(false);
		fatherNameTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		fatherNameTextField.setForeground(Color.green);
		fatherNameTextField.setBounds(250, 290, 200, 30);
		fatherNameTextField.setOpaque(false);
		frame.add(fatherNameTextField);
		
		genderTextField = new JTextField();
		genderTextField.setEditable(false);
		genderTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		genderTextField.setForeground(Color.green);
		genderTextField.setBounds(250, 330, 200, 30);
		genderTextField.setOpaque(false);
		frame.add(genderTextField);
		
		dobTextField = new JTextField();
		dobTextField.setEditable(false);
		dobTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		dobTextField.setForeground(Color.green);
		dobTextField.setBounds(250, 370, 200, 30);
		dobTextField.setOpaque(false);
		frame.add(dobTextField);
		
		houseAddressTextField = new JTextField();
		houseAddressTextField.setEditable(false);
		houseAddressTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		houseAddressTextField.setForeground(Color.green);
		houseAddressTextField.setBounds(250, 410, 200, 30);
		houseAddressTextField.setOpaque(false);
		frame.add(houseAddressTextField);
		
		mobileTextField = new JTextField();
		mobileTextField.setEditable(false);
		mobileTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		mobileTextField.setForeground(Color.green);
		mobileTextField.setBounds(250, 450, 200, 30);
		mobileTextField.setOpaque(false);
		frame.add(mobileTextField);
		
		emailTextField = new JTextField();
		emailTextField.setEditable(false);
		emailTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		emailTextField.setForeground(Color.green);
		emailTextField.setBounds(250, 490, 200, 30);
		emailTextField.setOpaque(false);
		frame.add(emailTextField);

//		academic details
		intermediateLabel = new JLabel("Intermediate");
		intermediateLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		intermediateLabel.setForeground(Color.pink);
		intermediateLabel.setBounds(540, 140, 150, 30);
		frame.add(intermediateLabel);
		
		graduationLabel = new JLabel("Graduation");
		graduationLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		graduationLabel.setForeground(Color.pink);
		graduationLabel.setBounds(540, 180, 150, 30);
		frame.add(graduationLabel);
		
		postGraduationLabel = new JLabel("Post Graduation");
		postGraduationLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		postGraduationLabel.setForeground(Color.pink);
		postGraduationLabel.setBounds(540, 220, 150, 30);
		frame.add(postGraduationLabel);
		
		diplomaLabel = new JLabel("Other Diploma");
		diplomaLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		diplomaLabel.setForeground(Color.pink);
		diplomaLabel.setBounds(540, 260, 150, 30);
		frame.add(diplomaLabel);
		
//		course details
		courseLabel = new JLabel("Select Course");
		courseLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		courseLabel.setForeground(Color.pink);
		courseLabel.setBounds(540, 340, 150, 25);
		frame.add(courseLabel);
		
		durationLabel = new JLabel("Duration");
		durationLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		durationLabel.setForeground(Color.pink);
		durationLabel.setBounds(540, 370, 150, 25);
		frame.add(durationLabel);
		
		totalFeesLabel = new JLabel("Total Fees");
		totalFeesLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		totalFeesLabel.setForeground(Color.pink);
		totalFeesLabel.setBounds(540, 400, 150, 25);
		frame.add(totalFeesLabel);
		
		discountLabel = new JLabel("Discount");
		discountLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		discountLabel.setForeground(Color.pink);
		discountLabel.setBounds(540, 430, 150, 25);
		frame.add(discountLabel);
		
		paymentModeLabel = new JLabel("Net Fees");
		paymentModeLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		paymentModeLabel.setForeground(Color.pink);
		paymentModeLabel.setBounds(540, 460, 150, 25);
		frame.add(paymentModeLabel);
		
		netFeesLabel = new JLabel("Payment Mode");
		netFeesLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		netFeesLabel.setForeground(Color.pink);
		netFeesLabel.setBounds(540, 490, 150, 25);
		frame.add(netFeesLabel);

		intermediateTextField = new JTextField();
		intermediateTextField.setEditable(false);
		intermediateTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		intermediateTextField.setForeground(Color.green);
		intermediateTextField.setBounds(710, 140, 200, 30);
		intermediateTextField.setOpaque(false);
		frame.add(intermediateTextField);
		
		graduationTextField = new JTextField();
		graduationTextField.setEditable(false);
		graduationTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		graduationTextField.setForeground(Color.green);
		graduationTextField.setBounds(710, 180, 200, 30);
		graduationTextField.setOpaque(false);
		frame.add(graduationTextField);
		
		postGraduationTextField = new JTextField();
		postGraduationTextField.setEditable(false);
		postGraduationTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		postGraduationTextField.setForeground(Color.green);
		postGraduationTextField.setBounds(710, 220, 200, 30);
		postGraduationTextField.setOpaque(false);
		frame.add(postGraduationTextField);
		
		diplomaTextField = new JTextField();
		diplomaTextField.setEditable(false);
		diplomaTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		diplomaTextField.setForeground(Color.green);
		diplomaTextField.setBounds(710, 260, 200, 30);
		diplomaTextField.setOpaque(false);
		frame.add(diplomaTextField);
		
		courseTextField = new JTextField();
		courseTextField.setEditable(false);
		courseTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		courseTextField.setForeground(Color.green);
		courseTextField.setBounds(710, 340, 200, 25);
		courseTextField.setOpaque(false);
		frame.add(courseTextField);
		
		durationTextField = new JTextField();
		durationTextField.setEditable(false);
		durationTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		durationTextField.setForeground(Color.green);
		durationTextField.setBounds(710, 370, 200, 25);
		durationTextField.setOpaque(false);
		frame.add(durationTextField);
		
		totalFeesTextField = new JTextField();
		totalFeesTextField.setEditable(false);
		totalFeesTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		totalFeesTextField.setForeground(Color.green);
		totalFeesTextField.setBounds(710, 400, 200, 25);
		totalFeesTextField.setOpaque(false);
		frame.add(totalFeesTextField);
		
		discountTextField = new JTextField();
		discountTextField.setEditable(false);
		discountTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		discountTextField.setForeground(Color.green);
		discountTextField.setBounds(710, 430, 200, 25);
		discountTextField.setOpaque(false);
		frame.add(discountTextField);
		
		netFeesTextField = new JTextField();
		netFeesTextField.setEditable(false);
		netFeesTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		netFeesTextField.setForeground(Color.green);
		netFeesTextField.setBounds(710, 460, 200, 25);
		netFeesTextField.setOpaque(false);
		frame.add(netFeesTextField);
		
		paymentModeTextField = new JTextField();
		paymentModeTextField.setEditable(false);
		paymentModeTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		paymentModeTextField.setForeground(Color.green);
		paymentModeTextField.setBounds(710, 490, 200, 25);
		paymentModeTextField.setOpaque(false);
		frame.add(paymentModeTextField);
		
//		search field
		searchTextField = new JTextField();
		searchTextField.setBorder(BorderFactory.createTitledBorder("Student ID"));
		searchTextField.setHorizontalAlignment(SwingConstants.CENTER);
		searchTextField.setBounds(600, 10, 200, 45);
		searchTextField.setFont(new Font("verdana", Font.BOLD, 18));
		searchTextField.setForeground(Color.white);
		searchTextField.setCaretColor(Color.white);
		searchTextField.setOpaque(false);
		frame.add(searchTextField);

		searchButton = new JButton(" Search", new ImageIcon("src/images/search-icon1.png"));
		searchButton.setBounds(810, 17, 130, 35);
		searchButton.setFont(new Font("verdana", Font.BOLD, 16));
		searchButton.addActionListener(this);
		frame.add(searchButton);

		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		clear();
		if (searchTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please Enter Student ID");
			searchTextField.requestFocus();
		} else if (!searchTextField.getText().contains("JTP-") || searchTextField.getText().length() != 10) {
			JOptionPane.showMessageDialog(null,
					"<html>Invalid Student ID !<br>Please Check the Student ID and try again.<br>@example : JTP-XXXXXX</html>");
			searchTextField.setText("");
			searchTextField.requestFocus();
		} else {
			try {
				Connection con = DAO.createConnection();

				PreparedStatement ps = con.prepareStatement("SELECT * FROM STUDENTRECORD WHERE STUDENT_ID = ?");
				ps.setString(1, searchTextField.getText());

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					idTextField.setText(rs.getString(1));
					nameTextField.setText(rs.getString(2));
					fatherNameTextField.setText(rs.getString(3));
					genderTextField.setText(rs.getString(4));
					dobTextField.setText(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(5)));
					houseAddressTextField.setText(rs.getString(6));
					mobileTextField.setText("" + rs.getLong(7));
					emailTextField.setText(rs.getString(8));
					intermediateTextField.setText(rs.getString(9));
					graduationTextField.setText(rs.getString(10));
					postGraduationTextField.setText(rs.getString(11));
					diplomaTextField.setText(rs.getString(12));
					courseTextField.setText(rs.getString(13));
					durationTextField.setText("" + rs.getInt(14));
					totalFeesTextField.setText("" + rs.getInt(15));
					discountTextField.setText("" + rs.getInt(16));
					netFeesTextField.setText("" + rs.getInt(17));
					paymentModeTextField.setText(rs.getString(18));

					Blob b = rs.getBlob(19);
					byte[] byt = b.getBytes(1, (int)b.length());

					FileOutputStream fout = new FileOutputStream("src/images/retrivedPhoto.jpg");
					fout.write(byt);
					fout.close();

					Image original = Toolkit.getDefaultToolkit().getImage("src/images/retrivedPhoto.jpg");
					Image scaled = original.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
					photoLabel.setIcon(new ImageIcon(scaled));
				}
				rs.close();
				con.close();

				if (idTextField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Record doesen't exist!");
					searchTextField.requestFocus();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, "Error while searching details !");
			}
		}
	}

	public void clear() {
		idTextField.setText("");
		nameTextField.setText("");
		fatherNameTextField.setText("");
		genderTextField.setText("");
		dobTextField.setText("");
		houseAddressTextField.setText("");
		mobileTextField.setText("");
		emailTextField.setText("");
		intermediateTextField.setText("");
		graduationTextField.setText("");
		postGraduationTextField.setText("");
		diplomaTextField.setText("");
		courseTextField.setText("");
		durationTextField.setText("");
		totalFeesTextField.setText("");
		discountTextField.setText("");
		netFeesTextField.setText("");
		paymentModeTextField.setText("");
		photoLabel.setIcon(new ImageIcon("src/images/photoSearch.png"));
	}
}