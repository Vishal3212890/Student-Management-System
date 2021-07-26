package com.sms;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.border.Border;
import java.sql.*;
import org.jdesktop.swingx.JXDatePicker;
import java.text.SimpleDateFormat;
import java.util.regex.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;

class Update implements FocusListener, ActionListener, KeyListener {
	String duration[] = { "3", "6", "8", "6", "4", "6", "6", "8", "3", "3" };
	String fees[] = { "3000", "5000", "16000", "3500", "3500", "5500", "4500", "4500", "1800", "2400" };
	JFrame frame, mainform;
	JLabel closeLabel, photoLabel, idLabel, nameLabel, fatherNameLabel, genderLabel, dobLabel, houseAddressLabel, mobileNumberLabel, emailLabel, intermediateLabel, graduationLabel, postGraduationLabel, diplomaLabel, courseLabel, durationLabel, totalFeesLabel, discountLabel, netFeesLabel, paymentModeLabel;
	JTextField idTextField, nameTextField, fatherNameTextField, houseAddressTextField, mobileNumberTextField, emailAddressTextField, durationTextField, totalFeesTextField, netFeesTextField, searchTextField;
	JRadioButton maleRadioButton, femaleRadioButton;
	JXDatePicker dobPicker;
	JComboBox<String> intermediateComboBox, graduationComboBox, postGraduationComboBox, diplomaComboBox, courseComboBox, discountComboBox, paymentModeComboBox;
	Dimension dimension;
	Border defaultBorder, borderOnFocus;
	JButton searchButton, updateButton;
	ButtonGroup buttonGroup;
	Image originalImage, scaledImage;
	String photoPath;
	boolean isImageUploaded;

	public Update(JFrame mainform) {
		this.mainform = mainform;
		dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Insert Record");
		frame.setBounds((dimension.width - 1000) / 2, (dimension.height - 550) / 2, 1000, 600);
		frame.setLayout(new BorderLayout());
		Image img = Toolkit.getDefaultToolkit().getImage("src/images/student.png");
		frame.setIconImage(img);
		frame.setContentPane(new JLabel(new ImageIcon("src/images/updateImage.png")));
		
		defaultBorder = BorderFactory.createLineBorder(Color.white);
		borderOnFocus = BorderFactory.createLineBorder(Color.green, 2);

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
		photoLabel = new JLabel(new ImageIcon("src/images/photoUpdate.png"));
		photoLabel.setBounds(320, 130, 100, 100);
		photoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		photoLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				try {
					FileDialog fd = new FileDialog(frame, "Select Photo", FileDialog.LOAD);
					fd.setVisible(true);

					photoPath = fd.getDirectory() + fd.getFile();
					if (photoPath.equals("nullnull")) {
						isImageUploaded = false;
					} else {
						isImageUploaded = true;
						originalImage = Toolkit.getDefaultToolkit().getImage(photoPath);
						scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
						photoLabel.setIcon(new ImageIcon(scaledImage));
					}
				} catch (Exception e) {
				}
			}
		});
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
		idTextField.setBorder(BorderFactory.createCompoundBorder(defaultBorder, BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		idTextField.setFont(new Font("verdana", Font.PLAIN, 18));
		idTextField.setForeground(Color.green);
		idTextField.setBounds(180, 170, 120, 30);
		idTextField.setCaretColor(Color.green);
		idTextField.setOpaque(false);
		frame.add(idTextField);
		
		nameTextField = new JTextField();
		nameTextField.setBorder(BorderFactory.createCompoundBorder(defaultBorder, BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		nameTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		nameTextField.setForeground(Color.green);
		nameTextField.setBounds(250, 250, 200, 30);
		nameTextField.setCaretColor(Color.green);
		nameTextField.setOpaque(false);
		nameTextField.addFocusListener(this);
		nameTextField.addKeyListener(this);
		frame.add(nameTextField);
		
		fatherNameTextField = new JTextField();
		fatherNameTextField.setBorder(BorderFactory.createCompoundBorder(defaultBorder, BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		fatherNameTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		fatherNameTextField.setForeground(Color.green);
		fatherNameTextField.setBounds(250, 290, 200, 30);
		fatherNameTextField.setCaretColor(Color.green);
		fatherNameTextField.setOpaque(false);
		fatherNameTextField.addFocusListener(this);
		fatherNameTextField.addKeyListener(this);
		frame.add(fatherNameTextField);
		
		buttonGroup = new ButtonGroup();
		maleRadioButton = new JRadioButton("Male");
		maleRadioButton.setFont(new Font("verdana", Font.PLAIN, 17));
		maleRadioButton.setBounds(250, 330, 70, 30);
		maleRadioButton.setOpaque(false);
		maleRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		maleRadioButton.setForeground(Color.green);
		buttonGroup.add(maleRadioButton);
		frame.add(maleRadioButton);

		femaleRadioButton = new JRadioButton("Female");
		femaleRadioButton.setFont(new Font("verdana", Font.PLAIN, 17));
		femaleRadioButton.setBounds(340, 330, 90, 30);
		femaleRadioButton.setOpaque(false);
		femaleRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		femaleRadioButton.setForeground(Color.green);
		buttonGroup.add(femaleRadioButton);
		frame.add(femaleRadioButton);
		
		dobPicker = new JXDatePicker();
		dobPicker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dobPicker.setForeground(Color.green);
		dobPicker.setFont(new Font("verdana", Font.PLAIN, 16));
		dobPicker.setBounds(250, 370, 200, 30);
		dobPicker.setOpaque(false);
		frame.add(dobPicker);
		
		houseAddressTextField = new JTextField();
		houseAddressTextField.setBorder(BorderFactory.createCompoundBorder(defaultBorder, BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		houseAddressTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		houseAddressTextField.setForeground(Color.green);
		houseAddressTextField.setBounds(250, 410, 200, 30);
		houseAddressTextField.setCaretColor(Color.green);
		houseAddressTextField.setOpaque(false);
		houseAddressTextField.addFocusListener(this);
		houseAddressTextField.addKeyListener(this);
		frame.add(houseAddressTextField);
		
		mobileNumberTextField = new JTextField();
		mobileNumberTextField.setBorder(BorderFactory.createCompoundBorder(defaultBorder, BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		mobileNumberTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		mobileNumberTextField.setForeground(Color.green);
		mobileNumberTextField.setBounds(250, 450, 200, 30);
		mobileNumberTextField.setCaretColor(Color.green);
		mobileNumberTextField.setOpaque(false);
		mobileNumberTextField.addFocusListener(this);
		mobileNumberTextField.addKeyListener(this);
		frame.add(mobileNumberTextField);
		
		emailAddressTextField = new JTextField();
		emailAddressTextField.setBorder(BorderFactory.createCompoundBorder(defaultBorder, BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		emailAddressTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		emailAddressTextField.setForeground(Color.green);
		emailAddressTextField.setBounds(250, 490, 200, 30);
		emailAddressTextField.setCaretColor(Color.green);
		emailAddressTextField.setOpaque(false);
		emailAddressTextField.addFocusListener(this);
		emailAddressTextField.addKeyListener(this);
		frame.add(emailAddressTextField);
		
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
		
		intermediateComboBox = new JComboBox<>();
		intermediateComboBox.addItem("UP Board");
		intermediateComboBox.addItem("CBSE Board");
		intermediateComboBox.addItem("ICSE Board");
		intermediateComboBox.addItem("Other Board");
		intermediateComboBox.setFont(new Font("verdana", Font.PLAIN, 16));
		intermediateComboBox.setForeground(Color.black);
		intermediateComboBox.setBounds(710, 140, 190, 30);
		frame.add(intermediateComboBox);

		graduationComboBox = new JComboBox<>();
		graduationComboBox.addItem("None");
		graduationComboBox.addItem("BA");
		graduationComboBox.addItem("B.Com");
		graduationComboBox.addItem("B.Sc");
		graduationComboBox.addItem("BCA");
		graduationComboBox.addItem("BBA");
		graduationComboBox.addItem("B.Tech");
		graduationComboBox.addItem("Other");
		graduationComboBox.setFont(new Font("verdana", Font.PLAIN, 16));
		graduationComboBox.setForeground(Color.black);
		graduationComboBox.setBounds(710, 180, 190, 30);
		frame.add(graduationComboBox);

		postGraduationComboBox = new JComboBox<>();
		postGraduationComboBox.addItem("None");
		postGraduationComboBox.addItem("MA");
		postGraduationComboBox.addItem("M.Com");
		postGraduationComboBox.addItem("M.Sc");
		postGraduationComboBox.addItem("MCA");
		postGraduationComboBox.addItem("MBA");
		postGraduationComboBox.addItem("M.Tech");
		postGraduationComboBox.addItem("Other");
		postGraduationComboBox.setFont(new Font("verdana", Font.PLAIN, 16));
		postGraduationComboBox.setForeground(Color.black);
		postGraduationComboBox.setBounds(710, 220, 190, 30);
		frame.add(postGraduationComboBox);

		diplomaComboBox = new JComboBox<>();
		diplomaComboBox.addItem("None");
		diplomaComboBox.addItem("ITI");
		diplomaComboBox.addItem("Polytechnic");
		diplomaComboBox.addItem("DOEACC");
		diplomaComboBox.addItem("Other");
		diplomaComboBox.setFont(new Font("verdana", Font.PLAIN, 16));
		diplomaComboBox.setForeground(Color.black);
		diplomaComboBox.setBounds(710, 260, 190, 30);
		frame.add(diplomaComboBox);
		
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
		
		netFeesLabel = new JLabel("Payment Mode");
		netFeesLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		netFeesLabel.setForeground(Color.pink);
		netFeesLabel.setBounds(540, 490, 150, 25);
		frame.add(netFeesLabel);
		
		paymentModeLabel = new JLabel("Net Fees");
		paymentModeLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		paymentModeLabel.setForeground(Color.pink);
		paymentModeLabel.setBounds(540, 460, 150, 25);
		frame.add(paymentModeLabel);
		
		courseComboBox = new JComboBox<>();
		courseComboBox.addItem("Basic");
		courseComboBox.addItem("MS-Office");
		courseComboBox.addItem("Tally");
		courseComboBox.addItem("DTP");
		courseComboBox.addItem("C & C++");
		courseComboBox.addItem("JAVA");
		courseComboBox.addItem("DotNet");
		courseComboBox.addItem("Python");
		courseComboBox.addItem("Data Structure");
		courseComboBox.addItem("RDBMS");
		courseComboBox.setFont(new Font("verdana", Font.PLAIN, 16));
		courseComboBox.setForeground(Color.black);
		courseComboBox.setBounds(710, 340, 190, 25);
		courseComboBox.addActionListener(this);
		frame.add(courseComboBox);

		durationTextField = new JTextField();
		durationTextField.setBorder(BorderFactory.createCompoundBorder(defaultBorder, BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		durationTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		durationTextField.setForeground(Color.green);
		durationTextField.setBounds(710, 370, 190, 25);
		durationTextField.setCaretColor(Color.green);
		durationTextField.setOpaque(false);
		durationTextField.addFocusListener(this);
		durationTextField.addKeyListener(this);
		frame.add(durationTextField);
		
		totalFeesTextField = new JTextField();
		totalFeesTextField.setBorder(BorderFactory.createCompoundBorder(defaultBorder, BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		totalFeesTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		totalFeesTextField.setForeground(Color.green);
		totalFeesTextField.setBounds(710, 400, 190, 25);
		totalFeesTextField.setCaretColor(Color.green);
		totalFeesTextField.setOpaque(false);
		totalFeesTextField.addFocusListener(this);
		totalFeesTextField.addKeyListener(this);
		frame.add(totalFeesTextField);
		
		discountComboBox = new JComboBox<>();
		discountComboBox.addItem("0");
		discountComboBox.addItem("5");
		discountComboBox.addItem("10");
		discountComboBox.addItem("15");
		discountComboBox.setFont(new Font("verdana", Font.PLAIN, 16));
		discountComboBox.setForeground(Color.black);
		discountComboBox.setBounds(710, 430, 190, 25);
		discountComboBox.addActionListener(this);
		frame.add(discountComboBox);

		netFeesTextField = new JTextField();
		netFeesTextField.setBorder(BorderFactory.createCompoundBorder(defaultBorder, BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		netFeesTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		netFeesTextField.setForeground(Color.green);
		netFeesTextField.setBounds(710, 460, 190, 25);
		netFeesTextField.setCaretColor(Color.green);
		netFeesTextField.setOpaque(false);
		netFeesTextField.addFocusListener(this);
		netFeesTextField.addKeyListener(this);
		frame.add(netFeesTextField);
		
		paymentModeComboBox = new JComboBox<>();
		paymentModeComboBox.addItem("Cash");
		paymentModeComboBox.addItem("Cheque");
		paymentModeComboBox.addItem("Draft");
		paymentModeComboBox.addItem("Online Payment");
		paymentModeComboBox.setFont(new Font("verdana", Font.PLAIN, 16));
		paymentModeComboBox.setForeground(Color.black);
		paymentModeComboBox.setBounds(710, 490, 190, 25);
		paymentModeComboBox.addActionListener(this);
		frame.add(paymentModeComboBox);

		
		searchTextField = new JTextField();
		searchTextField.setBorder(BorderFactory.createTitledBorder("Student ID"));
		searchTextField.setHorizontalAlignment(SwingConstants.CENTER);
		searchTextField.setBounds(500, 10, 200, 45);
		searchTextField.setFont(new Font("verdana", Font.BOLD, 18));
		searchTextField.setForeground(Color.white);
		searchTextField.setCaretColor(Color.white);
		searchTextField.setOpaque(false);
		frame.add(searchTextField);

		searchButton = new JButton(" Search", new ImageIcon("src/images/search-icon1.png"));
		searchButton.setBounds(705, 17, 130, 35);
		searchButton.setFont(new Font("verdana", Font.BOLD, 16));
		searchButton.addActionListener(this);
		frame.add(searchButton);

		updateButton = new JButton("Update");
		updateButton.setBounds(840, 17, 100, 35);
		updateButton.setFont(new Font("verdana", Font.BOLD, 16));
		updateButton.addActionListener(this);
		frame.add(updateButton);

		frame.setUndecorated(true);
		frame.setVisible(true);
		disableComponents();
	}

	public void focusGained(FocusEvent fe) {
		JTextField txt = (JTextField) fe.getComponent();
		txt.setBorder(BorderFactory.createCompoundBorder(borderOnFocus, BorderFactory.createEmptyBorder(1, 5, 1, 1)));
	}

	public void focusLost(FocusEvent fe) {
		JTextField txt = (JTextField) fe.getComponent();
		txt.setBorder(BorderFactory.createCompoundBorder(defaultBorder, BorderFactory.createEmptyBorder(1, 5, 1, 1)));
	}

	public void keyTyped(KeyEvent ke) {
		if (ke.getSource() == nameTextField || ke.getSource() == fatherNameTextField) {
			char ch = ke.getKeyChar();
			if ((ch < 'a' || ch > 'z') && (ch < 'A' || ch > 'Z') && ch != ' ') {
				ke.consume();
			}

		}

		if (ke.getSource() == mobileNumberTextField || ke.getSource() == durationTextField || ke.getSource() == totalFeesTextField || ke.getSource() == netFeesTextField) {
			char ch = ke.getKeyChar();

			if (ch < '0' || ch > '9') {
				ke.consume();
			}

			if (mobileNumberTextField.getText().trim().length() > 9) {
				ke.consume();
			}
		}
	}

	public void keyPressed(KeyEvent ke) {
	}

	public void keyReleased(KeyEvent ke) {
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == searchButton) {
			clear();
			disableComponents();
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

						if (rs.getString(4).equals("Male")) {
							maleRadioButton.setSelected(true);
						} else {
							femaleRadioButton.setSelected(true);
						}

						java.sql.Date dt = rs.getDate(5);
						dobPicker.setDate(new java.util.Date(dt.getTime()));

						houseAddressTextField.setText(rs.getString(6));
						mobileNumberTextField.setText("" + rs.getLong(7));
						emailAddressTextField.setText(rs.getString(8));

						intermediateComboBox.setSelectedItem((Object) rs.getString(9));
						graduationComboBox.setSelectedItem((Object) rs.getString(10));
						postGraduationComboBox.setSelectedItem((Object) rs.getString(11));
						diplomaComboBox.setSelectedItem((Object) rs.getString(12));
						courseComboBox.setSelectedItem((Object) rs.getString(13));

						durationTextField.setText("" + rs.getInt(14));
						totalFeesTextField.setText("" + rs.getInt(15));

						discountComboBox.setSelectedItem((Object) rs.getString(16));
						netFeesTextField.setText("" + rs.getInt(17));

						paymentModeComboBox.setSelectedItem((Object) rs.getString(18));

						Blob b = rs.getBlob(19);
						byte[] byt = b.getBytes(1, (int) b.length());

						photoPath = "src/images/retrivedPhoto.jpg";
						FileOutputStream fout = new FileOutputStream(photoPath);
						fout.write(byt);
						fout.close();

						Image original = Toolkit.getDefaultToolkit().getImage(photoPath);
						Image scaled = original.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
						photoLabel.setIcon(new ImageIcon(scaled));
						isImageUploaded = true;
						enableComponents();
					}

					rs.close();
					con.close();

					if (idTextField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Record doesen't exist!");
						searchTextField.requestFocus();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "Error while fetching details !");
				}
			}
		}

		if (ae.getSource() == updateButton) {
			if (idTextField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Please Search Record!");
				searchTextField.requestFocus();
			} else if (nameTextField.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Pease Enter Student's Name");
				nameTextField.requestFocus();
			} else if (fatherNameTextField.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Pease Enter Father's Name");
				fatherNameTextField.requestFocus();
			} else if (!(maleRadioButton.isSelected() || femaleRadioButton.isSelected())) {
				JOptionPane.showMessageDialog(frame, "Pease Select Gender");
				maleRadioButton.requestFocus();
			} else if (dobPicker.getDate() == null) {
				JOptionPane.showMessageDialog(frame, "Pease Enter DOB");
				dobPicker.requestFocus();
			} else if (houseAddressTextField.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Pease Enter House Address");
				houseAddressTextField.requestFocus();
			}

			else if (mobileNumberTextField.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Pease Enter Mobile Number");
				mobileNumberTextField.requestFocus();
			}

			else if (emailAddressTextField.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Pease Enter E-mail Address");
				emailAddressTextField.requestFocus();
			}

			else if (durationTextField.getText().trim().isEmpty() || totalFeesTextField.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Pease Select Course");
			}

			else if (netFeesTextField.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Please Select Discount");
			}

			else if (isImageUploaded == false) {
				JOptionPane.showMessageDialog(frame, "Please Select Photo");
			} else {
				Pattern pattern = Pattern.compile(
						"^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

				Matcher matcher = pattern.matcher(emailAddressTextField.getText());

				if (mobileNumberTextField.getText().length() != 10) {
					JOptionPane.showMessageDialog(frame, "Invalid mobile number");
					mobileNumberTextField.requestFocus();
				} else if (!matcher.matches()) {
					JOptionPane.showMessageDialog(frame, "Invalid email address");
					emailAddressTextField.requestFocus();
				} else {
					try {
						Connection con = DAO.createConnection();

						String query = "UPDATE STUDENTRECORD SET STUDENT_NAME = ?, FATHER_NAME = ?, GENDER = ?, DOB = ?, HOUSE_ADDRESS = ?, MOBILE_NO = ?, EMAIL = ?, INTERMEDIATE = ?, GRADUATION = ?, POST_GRADUATION = ?, OTHER_DIPLOMA = ?, COURSE = ?, DURATION = ?, TOTAL_FEES = ?, DISCOUNT = ?, NET_FEES = ?, PAYMENT_MODE = ?, PHOTO = ? WHERE STUDENT_ID = ?";

						PreparedStatement ps = con.prepareStatement(query);

						ps.setString(1, nameTextField.getText());
						ps.setString(2, fatherNameTextField.getText());
						ps.setString(3, (maleRadioButton.isSelected() ? "Male" : "Female"));

						java.util.Date date = dobPicker.getDate();
						ps.setDate(4, (new java.sql.Date(date.getTime())));

						ps.setString(5, houseAddressTextField.getText());
						ps.setLong(6, Long.parseLong(mobileNumberTextField.getText()));
						ps.setString(7, emailAddressTextField.getText());
						ps.setString(8, intermediateComboBox.getSelectedItem().toString());
						ps.setString(9, graduationComboBox.getSelectedItem().toString());
						ps.setString(10, postGraduationComboBox.getSelectedItem().toString());
						ps.setString(11, diplomaComboBox.getSelectedItem().toString());
						ps.setString(12, courseComboBox.getSelectedItem().toString());
						ps.setInt(13, Integer.parseInt(durationTextField.getText()));
						ps.setInt(14, Integer.parseInt(totalFeesTextField.getText()));
						ps.setInt(15, Integer.parseInt(discountComboBox.getSelectedItem().toString()));
						ps.setDouble(16, Double.parseDouble(netFeesTextField.getText()));
						ps.setString(17, paymentModeComboBox.getSelectedItem().toString());

						FileInputStream fis = new FileInputStream(photoPath);
						ps.setBinaryStream(18, fis, fis.available());
						ps.setString(19, idTextField.getText());

						if (ps.executeUpdate() == 1) {
							JOptionPane.showMessageDialog(frame, "Updated sucessfully!");
						}

						con.close();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(frame, "Error while updating details !");
					}
				}
			}
		}

		if (ae.getSource() == courseComboBox) {
			int indx = courseComboBox.getSelectedIndex();
			double fee = Double.parseDouble(fees[indx]);
			double dis = fee * Double.parseDouble(discountComboBox.getSelectedItem().toString()) / 100;
			durationTextField.setText(duration[indx]);
			totalFeesTextField.setText(fees[indx]);
			netFeesTextField.setText("" + (fee - dis));
		}

		if (ae.getSource() == discountComboBox) {
			courseComboBox.setSelectedIndex(courseComboBox.getSelectedIndex());
		}
	}

	public void clear() {
		idTextField.setText("");
		nameTextField.setText("");
		fatherNameTextField.setText("");
		houseAddressTextField.setText("");
		mobileNumberTextField.setText("");
		emailAddressTextField.setText("");
		dobPicker.setDate(new java.util.Date(0L));
		buttonGroup.clearSelection();
		intermediateComboBox.setSelectedIndex(0);
		graduationComboBox.setSelectedIndex(0);
		postGraduationComboBox.setSelectedIndex(0);
		diplomaComboBox.setSelectedIndex(0);
		courseComboBox.setSelectedIndex(0);
		discountComboBox.setSelectedIndex(0);
		paymentModeComboBox.setSelectedIndex(0);
		durationTextField.setText("");
		totalFeesTextField.setText("");
		netFeesTextField.setText("");
		photoLabel.setIcon(new ImageIcon("src/images/photoUpdate.png"));
	}

	public void disableComponents() {
		nameTextField.setEnabled(false);
		fatherNameTextField.setEnabled(false);
		dobPicker.setEnabled(false);
		houseAddressTextField.setEnabled(false);
		mobileNumberTextField.setEnabled(false);
		emailAddressTextField.setEnabled(false);
		durationTextField.setEnabled(false);
		totalFeesTextField.setEnabled(false);
		netFeesTextField.setEnabled(false);
		maleRadioButton.setEnabled(false);
		femaleRadioButton.setEnabled(false);
		intermediateComboBox.setEnabled(false);
		graduationComboBox.setEnabled(false);
		postGraduationComboBox.setEnabled(false);
		diplomaComboBox.setEnabled(false);
		courseComboBox.setEnabled(false);
		discountComboBox.setEnabled(false);
		paymentModeComboBox.setEnabled(false);
		photoLabel.setEnabled(false);
	}

	public void enableComponents() {
		nameTextField.setEnabled(true);
		fatherNameTextField.setEnabled(true);
		dobPicker.setEnabled(true);
		houseAddressTextField.setEnabled(true);
		mobileNumberTextField.setEnabled(true);
		emailAddressTextField.setEnabled(true);
		durationTextField.setEnabled(true);
		totalFeesTextField.setEnabled(true);
		netFeesTextField.setEnabled(true);
		maleRadioButton.setEnabled(true);
		femaleRadioButton.setEnabled(true);
		intermediateComboBox.setEnabled(true);
		graduationComboBox.setEnabled(true);
		postGraduationComboBox.setEnabled(true);
		diplomaComboBox.setEnabled(true);
		courseComboBox.setEnabled(true);
		discountComboBox.setEnabled(true);
		paymentModeComboBox.setEnabled(true);
		photoLabel.setEnabled(true);
	}
}