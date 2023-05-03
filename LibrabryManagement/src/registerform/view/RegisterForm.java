package registerform.view;
import javax.swing.*;

import database.JDBCUtil;
import home.view.MainView;
import login.view.LoginForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterForm extends JFrame implements ActionListener {
	private JLabel adminIdLabel, adminNameLabel, usernameLabel, passwordLabel, confirmPasswordLabel;
	private JTextField adminIdField, adminNameField, usernameField;
	private JPasswordField passwordField, confirmPasswordField;
	private JButton registerButton;

	public RegisterForm() {
		// Thiết lập thông tin cho JFrame
		setTitle("Đăng ký tài khoản");
		setSize(400, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font font2 = new Font("Tahoma", Font.PLAIN, 14);
		// Thiết lập Layout cho JFrame
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 5, 10, 5);

		
		// Tạo JLabel và JTextField cho mã admin
//		adminIdLabel = new JLabel("Mã admin:");
//		
//		c.gridx = 0;
//		c.gridy = 0;
//		add(adminIdLabel, c);
//
//		adminIdField = new JTextField(20);
//		c.gridx = 1;
//		c.gridy = 0;
//		add(adminIdField, c);

		// Tạo JLabel và JTextField cho tên admin
		adminNameLabel = new JLabel("Tên admin:");
		adminNameLabel.setFont(font2);
		c.gridx = 0;
		c.gridy = 1;
		add(adminNameLabel, c);

		adminNameField = new JTextField(20);
		c.gridx = 1;
		c.gridy = 1;
		add(adminNameField, c);

		// Tạo JLabel và JTextField cho tên đăng nhập
		usernameLabel = new JLabel("Tên đăng nhập:");
		usernameLabel.setFont(font2);
		c.gridx = 0;
		c.gridy = 2;
		add(usernameLabel, c);

		usernameField = new JTextField(20);
		c.gridx = 1;
		c.gridy = 2;
		add(usernameField, c);

		// Tạo JLabel và JPasswordField cho mật khẩu
		passwordLabel = new JLabel("Mật khẩu:");
		passwordLabel.setFont(font2);
		c.gridx = 0;
		c.gridy = 3;
		add(passwordLabel, c);

		passwordField = new JPasswordField(20);
		c.gridx = 1;
		c.gridy = 3;
		add(passwordField, c);

		// Tạo JLabel và JPasswordField cho xác nhận mật khẩu
		confirmPasswordLabel = new JLabel("Xác nhận mật khẩu:");
		confirmPasswordLabel.setFont(font2);
		c.gridx = 0;
		c.gridy = 4;
		add(confirmPasswordLabel, c);

		confirmPasswordField = new JPasswordField(20);
		c.gridx = 1;
		c.gridy = 4;
		add(confirmPasswordField, c);

		// Tạo JButton đăng ký
		registerButton = new JButton("Đăng ký");
		registerButton.setFont(font2);
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		add(registerButton, c);
		setVisible(true);

		// Đăng ký sự kiện cho JButton đăng ký
		registerButton.addActionListener(new ActionListener() {
			private Connection conn;

			@Override
			public void actionPerformed(ActionEvent e) {
			//	String adminId = adminIdField.getText().trim();
				String adminName = adminNameField.getText().trim();
				String username = usernameField.getText().trim();
				String password = String.valueOf(passwordField.getPassword());
				String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

				if (adminName.isEmpty() || username.isEmpty() || password.isEmpty()
						|| confirmPassword.isEmpty()) {
					JOptionPane.showMessageDialog(RegisterForm.this, "Vui lòng nhập đầy đủ thông tin.");
				} else if (!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(RegisterForm.this, "Mật khẩu không khớp. Vui lòng kiểm tra lại.");
				} else {
					try {
						// Tạo kết nối đến cơ sở dữ liệu
//						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin_manager",
//								"root", "");
						conn = JDBCUtil.getConnection();

						PreparedStatement stmt = conn.prepareStatement(
								"INSERT INTO admin (name, accountName, password) VALUES (?, ?, ?)");
						//stmt.setString(1, adminId);
						stmt.setString(1, adminName);
						stmt.setString(2, username);
						stmt.setString(3, password);

						int rowsInserted = stmt.executeUpdate();
						if (rowsInserted > 0) {
							JOptionPane.showMessageDialog(RegisterForm.this, "Đăng ký thành công!");
							dispose();
							new LoginForm();
							
						} else {
							JOptionPane.showMessageDialog(RegisterForm.this, "Đăng ký thất bại. Vui lòng thử lại.");
						}

						// Đóng kết nối
						conn.close();

					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(RegisterForm.this, "Đăng ký thất bại. Vui lòng thử lại.");
						ex.printStackTrace();
					}
				}
			}
		});
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
	    new RegisterForm();
	    
	}
}
