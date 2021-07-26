package com.sms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class Mainform implements ActionListener {

	JFrame frame;
	JPanel header, footer;
	Dimension dimension;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem insertMenuItem, searchMenuItem, deleteMenuItem, updateMenuItem, allStudentsMenuItem, exitMenuItem;
	JToolBar toolBar;
	JButton insertButton, searchButton, deleteButton, updateButton, allStudentsButton;
	JLabel timeLabel;

	public Mainform() {
		dimension = Toolkit.getDefaultToolkit().getScreenSize();

		frame = new JFrame("Student Management System");
		frame.setSize(dimension.width, dimension.height - 40);
		frame.setLayout(new BorderLayout());
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/student.png"));

		// menu bar
		menuBar = new JMenuBar();
		menu = new JMenu("Operations");
		menu.setMnemonic((int) 'O');
		menu.setFont(new Font("verdana", Font.BOLD, 16));
		menu.setForeground(Color.blue);

		insertMenuItem = new JMenuItem("  Insert    ");
		insertMenuItem.setMnemonic((int)'I');
		insertMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
		insertMenuItem.setIcon(new ImageIcon("src/images/add1.png"));
		insertMenuItem.setForeground(Color.blue);
		insertMenuItem.setFont(new Font("verdana", Font.PLAIN, 16));
		insertMenuItem.addActionListener(this);

		searchMenuItem = new JMenuItem("  Search    ");
		searchMenuItem.setMnemonic((int)'S');
		searchMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		searchMenuItem.setIcon(new ImageIcon("src/images/search.png"));
		searchMenuItem.setForeground(Color.blue);
		searchMenuItem.setFont(new Font("verdana", Font.PLAIN, 16));
		searchMenuItem.addActionListener(this);

		deleteMenuItem = new JMenuItem("  Delete    ");
		deleteMenuItem.setMnemonic((int)'D');
		deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
		deleteMenuItem.setIcon(new ImageIcon("src/images/remove1.png"));
		deleteMenuItem.setForeground(Color.blue);
		deleteMenuItem.setFont(new Font("verdana", Font.PLAIN, 16));
		deleteMenuItem.addActionListener(this);

		updateMenuItem = new JMenuItem("  Update    ");
		updateMenuItem.setMnemonic((int)'U');
		updateMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK));
		updateMenuItem.setIcon(new ImageIcon("src/images/edit1.png"));
		updateMenuItem.setForeground(Color.blue);
		updateMenuItem.setFont(new Font("verdana", Font.PLAIN, 16));
		updateMenuItem.addActionListener(this);

		allStudentsMenuItem = new JMenuItem("  All Students   ");
		allStudentsMenuItem.setMnemonic((int)'A');
		allStudentsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
		allStudentsMenuItem.setIcon(new ImageIcon("src/images/table.png"));
		allStudentsMenuItem.setForeground(Color.blue);
		allStudentsMenuItem.setFont(new Font("verdana", Font.PLAIN, 16));
		allStudentsMenuItem.addActionListener(this);

		exitMenuItem = new JMenuItem("  Exit      ");
		exitMenuItem.setMnemonic((int)'E');
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
		exitMenuItem.setIcon(new ImageIcon("src/images/close2.png"));
		exitMenuItem.setForeground(Color.blue);
		exitMenuItem.setFont(new Font("verdana", Font.PLAIN, 16));
		exitMenuItem.addActionListener(this);

		menu.add(insertMenuItem);
		menu.addSeparator();
		menu.add(searchMenuItem);
		menu.addSeparator();
		menu.add(deleteMenuItem);
		menu.addSeparator();
		menu.add(updateMenuItem);
		menu.addSeparator();
		menu.add(allStudentsMenuItem);
		menu.addSeparator();
		menu.add(exitMenuItem);
		menuBar.add(menu);

		// tool bar
		toolBar = new JToolBar("Operations");

		insertButton = new JButton(new ImageIcon("src/images/add1.png"));
		insertButton.setToolTipText("Insert");
		insertButton.addActionListener(this);
		toolBar.add(insertButton);

		searchButton = new JButton(new ImageIcon("src/images/search.png"));
		searchButton.setToolTipText("Search");
		searchButton.addActionListener(this);
		toolBar.add(searchButton);

		deleteButton = new JButton(new ImageIcon("src/images/remove1.png"));
		deleteButton.addActionListener(this);
		deleteButton.setToolTipText("Remove");
		toolBar.add(deleteButton);

		updateButton = new JButton(new ImageIcon("src/images/edit1.png"));
		updateButton.setToolTipText("Update");
		updateButton.addActionListener(this);
		toolBar.add(updateButton);

		allStudentsButton = new JButton(new ImageIcon("src/images/table.png"));
		allStudentsButton.setToolTipText("All Students");
		allStudentsButton.addActionListener(this);
		toolBar.add(allStudentsButton);


		// adding to header
		header = new JPanel();
		header.setLayout(new BorderLayout());
		header.add(menuBar, BorderLayout.NORTH);
		header.add(toolBar, BorderLayout.SOUTH);

		timeLabel = new JLabel();
		timeLabel.setFont(new Font("verdana", Font.PLAIN, 14));
		timeLabel.setForeground(Color.red);

		// setting thread to display current time
		new Thread(() -> {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
				while (true) {
					Date date = new Date();
					timeLabel.setText(sdf.format(date) + "  ");
					Thread.sleep(1000);
				}
			} catch (Exception e) {
			}
		}).start();

		// adding to footer
		footer = new JPanel();
		footer.setLayout(new BorderLayout());
		footer.add(timeLabel, BorderLayout.EAST);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				int ch = JOptionPane.showConfirmDialog(frame, "Are you sure to want to exit ?", "Select an Option",
						JOptionPane.YES_NO_OPTION);
				if (ch == 0) {
					System.exit(0);
				} else {
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});

		frame.add(header, BorderLayout.NORTH);
		frame.add(footer, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == insertMenuItem || ae.getSource() == insertButton) {
			// insert
			frame.setEnabled(false);
			new Insert(frame);
		}
		if (ae.getSource() == searchMenuItem || ae.getSource() == searchButton) {
			// search
			frame.setEnabled(false);
			new Search(frame);
		}
		if (ae.getSource() == deleteMenuItem || ae.getSource() == deleteButton) {
			// delete
			new Delete();
		}
		if (ae.getSource() == updateMenuItem || ae.getSource() == updateButton) {
			// update
			frame.setEnabled(false);
			new Update(frame);

		}
		if (ae.getSource() == allStudentsMenuItem || ae.getSource() == allStudentsButton) {
			// display table
			frame.setEnabled(false);
			new AllRecord(frame);
		}
		if (ae.getSource() == exitMenuItem) {
			int ch = JOptionPane.showConfirmDialog(frame, "Are you sure to want to exit", "Select an Option",
					JOptionPane.YES_NO_OPTION);
			if (ch == 0) {
				System.exit(0);
			} else {
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		}
	}
}