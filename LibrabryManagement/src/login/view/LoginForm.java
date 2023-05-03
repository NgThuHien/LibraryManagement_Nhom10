package login.view;

import javax.swing.*;


import admin.dao.AdminDAOImp;
import admin.model.AdminModel;
import database.JDBCUtil;
import home.view.MainView;
import registerform.view.RegisterForm;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginForm extends JFrame implements ActionListener {
	private static JPasswordField passwordField;
	private JButton loginButton;
	private Connection conn;
	private ResultSet rs;
	private Connection stmt;
	private JButton regButton;
	private static JTextField accountNameField;

	public LoginForm() {
		// Thiết lập cửa sổ form
		setTitle("Đăng nhập");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Font font2 = new Font("Tahoma", Font.PLAIN, 14);
		// Tạo panel chứa các trường nhập liệu và nút đăng nhập
		JPanel panel = new JPanel(new GridBagLayout());
		add(panel);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 5, 10, 5);

		// Tiêu đề
		JLabel titleLabel = new JLabel("Đăng nhập");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(titleLabel, c);

		// Trường nhập liệu Account
		JLabel accountNameLabel = new JLabel("Tài khoản:");
		accountNameLabel.setFont(font2);
		accountNameField = new JTextField(20);
		accountNameField.setFont(font2);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.LINE_START;
		panel.add(accountNameLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(accountNameField, c);

		// Trường nhập liệu Password
		JLabel passwordLabel = new JLabel("Mật khẩu:");
		passwordLabel.setFont(font2);
		passwordField = new JPasswordField(20);
		passwordField.setFont(font2);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.LINE_START;
		panel.add(passwordLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(passwordField, c);

		// Nút đăng nhập
		loginButton = new JButton("Đăng nhập");
		loginButton.setFont(font2);
		loginButton.setPreferredSize(new Dimension(150, 28));
		loginButton.addActionListener(this);
		
		regButton = new JButton("Đăng ký tài khoản");
		regButton.setPreferredSize(new Dimension(150, 28));
		regButton.setFont(font2);
		regButton.addActionListener(this);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(loginButton, c);

		c.gridy = 4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(regButton, c);

		setVisible(true);
	}

	// Xử lý sự kiện khi người dùng nhấn nút đăng nhập
	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		if(cm.equals("Đăng nhập")) {
		String accountName = accountNameField.getText();
		String password = new String(passwordField.getPassword());
		if (accountName.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tên tài khoản và mật khẩu", "Lỗi đăng nhập",
					JOptionPane.ERROR_MESSAGE);
		} else {
		}
		// Xử lý đăng nhập
		conn = JDBCUtil.getConnection();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM Admin WHERE accountName=? AND password=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, accountNameField.getText());
			statement.setString(2, new String(passwordField.getPassword()));
			rs = statement.executeQuery();

			if (rs.next()) {
				// Đăng nhập thành công
				new MainView();

				JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");

				// Đóng cửa sổ đăng nhập
				dispose();

			} else {
				// Đăng nhập không thành công
				JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không đúng. Vui lòng thử lại.");

			}

			// Đóng kết nối cơ sở dữ liệu
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}else if(cm.equals("Đăng ký tài khoản")) {
		this.dispose();
		new RegisterForm();
	}
		
	}


	public static AdminModel getAd() {
		return AdminDAOImp.getInstance().selectByAccAndPw(accountNameField.getText(), new String(passwordField.getPassword())) ;
	}
//	public static String getAccountName() {
//		return accountNameField.getText();
//	}
//	

	public static void main(String[] args) {
		new LoginForm();
	}
}
