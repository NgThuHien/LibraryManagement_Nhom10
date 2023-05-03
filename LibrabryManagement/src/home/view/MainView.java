package home.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import book.view.BookView;
import lendingslip.view.LendingView;
import login.view.ChangePasswordForm;
import login.view.LoginForm;
import registerform.view.RegisterForm;

public class MainView extends JFrame {

	private JButton bt1;
	private JButton bt2;
	private JButton bt3;
	private JLabel label;
	private JButton bt4;
	private JButton bt5;

	public MainView() {
		//JFrame frame = new JFrame();
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		Font font = new Font("Tahoma", Font.BOLD, 40);
		Font font1 = new Font("Tahoma", Font.BOLD, 20);
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER; // CĂN TRÁI
		c.insets = new Insets(20, 10, 20, 10); // khoảng hở

		c.gridx = 0;
		c.gridy = 0;
		label = new JLabel("Quản lý thư viện");
		label.setFont(font);
		panel1.add(label, c);
		c.gridy = 1;
		bt1 = new JButton("Quản lý sách");
		bt1.setPreferredSize(new Dimension(250, 60)); // thiết lập kích thước ưu tiên cho button
		bt1.setFont(font1);
		panel1.add(bt1, c);

		c.gridy = 2;
		bt2 = new JButton("Quản lý mượn trả");
		bt2.setPreferredSize(new Dimension(250, 60));
		bt2.setFont(font1);
		panel1.add(bt2, c);

		c.gridy = 3;
		bt3 = new JButton("Đăng xuất");
		bt3.setPreferredSize(new Dimension(250, 60));
		bt3.setFont(font1);
		panel1.add(bt3, c);
		LineBorder border1 = new LineBorder(Color.RED, 3);
		panel1.setBorder(border1);
		EmptyBorder border = new EmptyBorder(new Insets(10, 10, 10, 10));
		panel1.setBorder(border);
		
//		c.gridy = 4;
//		bt4 = new JButton("Đăng ký tài khoản");
//		bt4.setPreferredSize(new Dimension(250, 60));
//		bt4.setFont(font1);
//		panel1.add(bt4, c);
//		//LineBorder border1 = new LineBorder(Color.RED, 3);
//		panel1.setBorder(border1);
//		//EmptyBorder border = new EmptyBorder(new Insets(10, 10, 10, 10));
//		panel1.setBorder(border);
		
		c.gridy = 4;
		bt5 = new JButton("Đổi mật khẩu");
		bt5.setPreferredSize(new Dimension(250, 60));
		bt5.setFont(font1);
		panel1.add(bt5, c);
		//LineBorder border1 = new LineBorder(Color.RED, 3);
		panel1.setBorder(border1);
		//EmptyBorder border = new EmptyBorder(new Insets(10, 10, 10, 10));
		panel1.setBorder(border);
		
		this.add(BorderLayout.CENTER, panel1);
		this.setTitle("Quản lý thư viện");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);

		// xử lý sự kiện cho bt1
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// xử lý sự kiện khi bt1 được click
				dispose();
				new BookView();
			}

		});

		// xử lý sự kiện cho bt2
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// xử lý sự kiện khi bt2 được click
				dispose();
				new LendingView();
			}
		});

		// xử lý sự kiện cho bt3
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// xử lý sự kiện khi bt3 được click
				  dispose(); // đóng cửa sổ hiện tại
//			      
				  new LoginForm();
			}
		});
		
//		// Xử lý sự kiện cho nút 4 
//		bt4.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// xử lý sự kiện khi bt4 được click
//				dispose();
//				new RegisterForm();
//			}
//		});
		
		bt5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ChangePasswordForm();
			}
		});

	}
}