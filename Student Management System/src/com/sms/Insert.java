package com.sms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.jdesktop.swingx.JXDatePicker;

public class Insert implements FocusListener, ActionListener, KeyListener {
	
	String duration[] = { "3", "6", "8", "6", "4", "6", "6", "8", "3", "3" };
	String fees[] = { "3000", "5000", "16000", "3500", "3500", "5500", "4500", "4500", "1800", "2400" };
	JFrame frame, mainform;
	JLabel closeLabel, photoLabel, idLabel, nameLabel, fatherNameLabel, genderLabel, dobLabel, houseAddressLabel, mobileNumberLabel, emailLabel, intermediateLabel, graduationLabel, postGraduationLabel, diplomaLabel, courseLabel, durationLabel, totalFeesLabel, discountLabel, netFeesLabel, paymentModeLabel;
	JTextField idTextField, nameTextField, fatherNameTextField, houseAddressTextField, mobileNumberTextField, emailAddressTextField, durationTextField, totalFeesTextField, netFeesTextField;
	JRadioButton maleRadioButton, femaleRadioButton;
	JXDatePicker dobPicker;
	JComboBox<String> intermediateComboBox, graduationComboBox, postGraduationComboBox, diplomaComboBox, courseComboBox, discountComboBox, paymentModeComboBox;
	Dimension dim;
	Border defaultBorder, borderOnFocus;
	String photoPath;
	boolean isImageUploded;
	Image originalImage, scaledImage;
	ButtonGroup buttonGroup;
	JButton saveButton, resetButton;

	public Insert(JFrame mainform) {
		this.mainform = mainform;
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		
//		borders for textfield
		defaultBorder = BorderFactory.createLineBorder(Color.white);
		borderOnFocus = BorderFactory.createLineBorder(Color.green, 2);
		
		frame = new JFrame("Insert Record");
		frame.setBounds((dim.width - 1000) / 2, (dim.height - 550) / 2, 1000, 600);
		frame.setLayout(new BorderLayout());
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/student.png"));
		frame.setContentPane(new JLabel(new ImageIcon("src/images/insert.png")));

//		close icon
		closeLabel = new JLabel(new ImageIcon("src/images/close1.png"));
		closeLabel.setToolTipText("Close");
		closeLabel.setBounds(955, 20, 32, 32);
		closeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.add(closeLabel);
		closeLabel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				closeLabel.setIcon(new ImageIcon("src/images/close2.png"));
			}

			public void mouseExited(MouseEvent me) {
				closeLabel.setIcon(new ImageIcon("src/images/close1.png"));
			}

			public void mouseClicked(MouseEvent me) {
				frame.dispose();
				mainform.setEnabled(true);
			}

		});
		frame.add(closeLabel);

//		personal details
		photoLabel = new JLabel(new ImageIcon("src/images/photo.png"));
		photoLabel.setBounds(320, 130, 100, 100);
		photoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		photoLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				try {
					FileDialog fileDialog = new FileDialog(frame, "Select Photo", FileDialog.LOAD);
					fileDialog.setVisible(true);

					photoPath = fileDialog.getDirectory() + fileDialog.getFile();
					if (photoPath.equals("nullnull")) {
						isImageUploded = false;
					} else {
						isImageUploded = true;
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
		idLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		idLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				setRandomId();
			}
		});
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

		emailLabel = new JLabel("email address");
		emailLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		emailLabel.setForeground(Color.pink);
		emailLabel.setBounds(100, 490, 150, 30);
		frame.add(emailLabel);

		idTextField = new JTextField();
		idTextField.setEditable(false);
		idTextField.setText("JTP-"+UUID.randomUUID().toString().substring(0,6));
		idTextField.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		idTextField.setFont(new Font("verdana", Font.PLAIN, 16));
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
		dobPicker.setFont(new Font("verdana", Font.PLAIN, 17));
		dobPicker.setDate(Calendar.getInstance().getTime());
		dobPicker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dobPicker.setForeground(Color.green);
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
		mobileNumberTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		mobileNumberTextField.setBorder(BorderFactory.createCompoundBorder(defaultBorder, BorderFactory.createEmptyBorder(1, 5, 1, 1)));
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
		
		diplomaLabel = new JLabel("Diploma");
		diplomaLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		diplomaLabel.setForeground(Color.pink);
		diplomaLabel.setBounds(540, 180, 150, 30);
		frame.add(diplomaLabel);

		graduationLabel = new JLabel("Graduation");
		graduationLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		graduationLabel.setForeground(Color.pink);
		graduationLabel.setBounds(540, 220, 150, 30);
		frame.add(graduationLabel);

		postGraduationLabel = new JLabel("Post Graduation");
		postGraduationLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		postGraduationLabel.setForeground(Color.pink);
		postGraduationLabel.setBounds(540, 260, 150, 30);
		frame.add(postGraduationLabel);
		
		intermediateComboBox = new JComboBox<>();
		intermediateComboBox.addItem("CBSE Board");
		intermediateComboBox.addItem("ICSE Board");
		intermediateComboBox.addItem("UP Board");
		intermediateComboBox.addItem("Other Board");
		intermediateComboBox.setFont(new Font("verdana", Font.PLAIN, 16));
		intermediateComboBox.setForeground(Color.black);
		intermediateComboBox.setBounds(710, 140, 190, 30);
		frame.add(intermediateComboBox);

		diplomaComboBox = new JComboBox<>();
		diplomaComboBox.addItem("None");
		diplomaComboBox.addItem("ITI");
		diplomaComboBox.addItem("Polytechnic");
		diplomaComboBox.addItem("DOEACC");
		diplomaComboBox.addItem("Other");
		diplomaComboBox.setFont(new Font("verdana", Font.PLAIN, 16));
		diplomaComboBox.setForeground(Color.black);
		diplomaComboBox.setBounds(710, 180, 190, 30);
		frame.add(diplomaComboBox);

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
		graduationComboBox.setBounds(710, 220, 190, 30);
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
		postGraduationComboBox.setBounds(710, 260, 190, 30);
		frame.add(postGraduationComboBox);


//		course details
		courseLabel = new JLabel("Course");
		courseLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		courseLabel.setForeground(Color.pink);
		courseLabel.setBounds(540, 340, 170, 25);
		frame.add(courseLabel);
		
		durationLabel = new JLabel("Duration(in Months)");
		durationLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		durationLabel.setForeground(Color.pink);
		durationLabel.setBounds(540, 370, 170, 25);
		frame.add(durationLabel);
		
		totalFeesLabel = new JLabel("Total Fees(in Rs.)");
		totalFeesLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		totalFeesLabel.setForeground(Color.pink);
		totalFeesLabel.setBounds(540, 400, 170, 25);
		frame.add(totalFeesLabel);
		
		discountLabel = new JLabel("Discount(%)");
		discountLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		discountLabel.setForeground(Color.pink);
		discountLabel.setBounds(540, 430, 170, 25);
		frame.add(discountLabel);
		
		netFeesLabel = new JLabel("Net Fees(in Rs.)");
		netFeesLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		netFeesLabel.setForeground(Color.pink);
		netFeesLabel.setBounds(540, 460, 170, 25);
		frame.add(netFeesLabel);

		paymentModeLabel = new JLabel("Payment Mode");
		paymentModeLabel.setFont(new Font("verdana", Font.PLAIN, 16));
		paymentModeLabel.setForeground(Color.pink);
		paymentModeLabel.setBounds(540, 490, 150, 25);
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

		durationTextField = new JTextField(duration[0]);
		durationTextField.setBorder(BorderFactory.createCompoundBorder(defaultBorder, BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		durationTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		durationTextField.setForeground(Color.green);
		durationTextField.setBounds(710, 370, 190, 25);
		durationTextField.setCaretColor(Color.green);
		durationTextField.setOpaque(false);
		durationTextField.setEditable(false);
		durationTextField.addFocusListener(this);
		durationTextField.addKeyListener(this);
		frame.add(durationTextField);
		
		totalFeesTextField = new JTextField(fees[0]);
		totalFeesTextField.setBorder(BorderFactory.createCompoundBorder(defaultBorder, BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		totalFeesTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		totalFeesTextField.setForeground(Color.green);
		totalFeesTextField.setBounds(710, 400, 190, 25);
		totalFeesTextField.setCaretColor(Color.green);
		totalFeesTextField.setOpaque(false);
		totalFeesTextField.setEditable(false);
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
	
		netFeesTextField = new JTextField("" + (Double.parseDouble(fees[0]) - Double.parseDouble(fees[0]) * 0.05));
		netFeesTextField.setBorder(BorderFactory.createCompoundBorder(defaultBorder, BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		netFeesTextField.setFont(new Font("verdana", Font.PLAIN, 16));
		netFeesTextField.setForeground(Color.green);
		netFeesTextField.setBounds(710, 460, 190, 25);
		netFeesTextField.setCaretColor(Color.green);
		netFeesTextField.setOpaque(false);
		netFeesTextField.setEditable(false);
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

		saveButton = new JButton("Save");
		saveButton.setBounds(700, 20, 120, 30);
		saveButton.setFont(new Font("verdana", Font.PLAIN, 18));
		saveButton.addActionListener(this);
		frame.add(saveButton);

		resetButton = new JButton("Reset");
		resetButton.setBounds(830, 20, 120, 30);
		resetButton.setFont(new Font("verdana", Font.PLAIN, 18));
		resetButton.addActionListener(this);
		frame.add(resetButton);

		frame.setUndecorated(true);
		frame.setVisible(true);
	}
	
	public void setRandomId() {
		String id = UUID.randomUUID().toString();
		id = id.substring(0, 6);
		idTextField.setText("JTP-"+id);
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

		if (ae.getSource() == saveButton) {
			if (idTextField.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Pease Generate ID");
			}

			else if (nameTextField.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Pease Enter Student's Name");
				nameTextField.requestFocus();
			}

			else if (fatherNameTextField.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Pease Enter Father's Name");
				fatherNameTextField.requestFocus();
			}

			else if (!(maleRadioButton.isSelected() || femaleRadioButton.isSelected())) {
				JOptionPane.showMessageDialog(frame, "Pease Select Gender");
				maleRadioButton.requestFocus();
			}

			else if (dobPicker.getDate() == null) {
				JOptionPane.showMessageDialog(frame, "Pease Enter DOB");
				dobPicker.requestFocus();
			}

			else if (houseAddressTextField.getText().trim().isEmpty()) {
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

			else if (isImageUploded == false) {
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

						String query = "INSERT INTO STUDENTRECORD VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

						PreparedStatement ps = con.prepareStatement(query);

						ps.setString(1, idTextField.getText());
						ps.setString(2, nameTextField.getText());
						ps.setString(3, fatherNameTextField.getText());
						ps.setString(4, (maleRadioButton.isSelected() ? "Male" : "Female"));

						java.util.Date date = dobPicker.getDate();
						ps.setDate(5, (new java.sql.Date(date.getTime())));

						ps.setString(6, houseAddressTextField.getText());
						ps.setLong(7, Long.parseLong(mobileNumberTextField.getText()));
						ps.setString(8, emailAddressTextField.getText());
						ps.setString(9, intermediateComboBox.getSelectedItem().toString());
						ps.setString(10, graduationComboBox.getSelectedItem().toString());
						ps.setString(11, postGraduationComboBox.getSelectedItem().toString());
						ps.setString(12, diplomaComboBox.getSelectedItem().toString());
						ps.setString(13, courseComboBox.getSelectedItem().toString());
						ps.setInt(14, Integer.parseInt(durationTextField.getText()));
						ps.setInt(15, Integer.parseInt(totalFeesTextField.getText()));
						ps.setInt(16, Integer.parseInt(discountComboBox.getSelectedItem().toString()));
						ps.setDouble(17, Double.parseDouble(netFeesTextField.getText()));
						ps.setString(18, paymentModeComboBox.getSelectedItem().toString());

						FileInputStream img = new FileInputStream(photoPath);
						ps.setBinaryStream(19, img, img.available());

						if (ps.executeUpdate() == 1) {
							JOptionPane.showMessageDialog(frame, "Student details saved sucessfully !");
						}

						con.close();
						clear();

					} catch (Exception e) {
						JOptionPane.showMessageDialog(frame, "Error while saving details !");
					}
				}
			}

		}

		if (ae.getSource() == resetButton) {
			clear();
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
		setRandomId();
		nameTextField.setText("");
		fatherNameTextField.setText("");
		houseAddressTextField.setText("");
		mobileNumberTextField.setText("");
		emailAddressTextField.setText("");
		durationTextField.setText("");
		dobPicker.setDate(Calendar.getInstance().getTime());
		buttonGroup.clearSelection();
		intermediateComboBox.setSelectedIndex(0);
		graduationComboBox.setSelectedIndex(0);
		postGraduationComboBox.setSelectedIndex(0);
		diplomaComboBox.setSelectedIndex(0);
		courseComboBox.setSelectedIndex(0);
		discountComboBox.setSelectedIndex(0);
		paymentModeComboBox.setSelectedIndex(0);
		photoLabel.setIcon(new ImageIcon("src/images/photo.png"));	
	}
}