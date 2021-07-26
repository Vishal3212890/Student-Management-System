package com.sms;

import java.awt.*;
import javax.swing.*;

public class Splash {
	JFrame frame;
	Dimension dimension;
	JLabel loader;
	int loaderWidth = 0;

	public Splash() {
		dimension = Toolkit.getDefaultToolkit().getScreenSize();

		frame = new JFrame();
		frame.setLayout(null);
		frame.setBounds((dimension.width - 500) / 2, (dimension.height - 300) / 2, 500, 300);
		frame.setContentPane(new JLabel(new ImageIcon("src/images/splash.png")));

		loader = new JLabel(new ImageIcon("src/images/line.png"));
		loader.setBounds(3, 240, loaderWidth, 5);
		frame.add(loader);

		frame.setUndecorated(true);
		frame.setVisible(true);

		Thread th = new Thread(() -> {
			try {
				TableCreator.createUserTable();
				TableCreator.createStudentTable();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Database Error!");
				System.exit(0);
			}

		});
		th.start();

		try {
			for (int i = 1; i <= 495; i++) {
				loaderWidth++;
				loader.setSize(loaderWidth, 3);
				Thread.sleep(5);
			}
			th.join();
		} catch (Exception e) {
		}

		new Mainform();
		frame.dispose();
	}
}