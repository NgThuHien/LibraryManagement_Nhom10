package login.view;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import admin.dao.AdminDAOImp;
import admin.model.AdminModel;
import login.controller.ChangePwController;

public class ChangePasswordForm extends JFrame {
	private JPasswordField oldPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField reNewPasswordField;

	public ChangePasswordForm() {
		

		Font font2 = new Font("Tahoma", Font.PLAIN, 14);
		// Tạo panel chứa các trường nhập liệu và nút đăng nhập
		JPanel panel = new JPanel(new GridBagLayout());
		add(panel);

		

		// Tiêu đề
		JLabel titleLabel = new JLabel("Đổi mật khẩu ");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.add(BorderLayout.NORTH,titleLabel);

		// Trường nhập liệu Email
		JLabel oldPassword = new JLabel("Mật khẩu cũ:");
		oldPassword.setFont(font2);
		oldPasswordField = new JPasswordField(20);
		oldPasswordField.setFont(font2);
		

		// Trường nhập liệu Password
		JLabel newPasswordLabel = new JLabel("Mật khẩu mới:");
		newPasswordLabel.setFont(font2);
		newPasswordField = new JPasswordField(20);
		newPasswordField.setFont(font2);
		

		JLabel reNewPasswordLabel = new JLabel("Nhập lại mật khẩu mới:");
		reNewPasswordLabel.setFont(font2);
		reNewPasswordField = new JPasswordField(20);
		reNewPasswordField.setFont(font2);
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 5, 10, 5);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.1;
		panel.add(oldPassword, c);
		c.gridy = 1;
		panel.add(newPasswordLabel, c);
		c.gridy = 2;
		panel.add(reNewPasswordLabel, c);
		

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.9;
		c.fill = GridBagConstraints.BOTH;
		panel.add(oldPasswordField, c);
		c.gridy = 1;
		panel.add(newPasswordField, c);
		c.gridy = 2;
		panel.add(reNewPasswordField, c);
		
		ActionListener action = new ChangePwController(this);
		// Nút đăng nhập
		JButton buttonSave = new JButton("Lưu");
		buttonSave.setFont(font2);
		buttonSave.addActionListener(action);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(buttonSave, c);

		this.setTitle("Đổi mật khẩu");
		this.setSize(400, 300);
		((JComponent) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		new ChangePasswordForm();
	}
	public void changePw() {
		String oldPw = new String(this.oldPasswordField.getPassword());
		String newPw = new String(this.newPasswordField.getPassword());
		String reNewPw = new String(this.reNewPasswordField.getPassword());
		
		if (oldPw.equals("") || newPw.equals("") || reNewPw.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ ");
		} else if(!oldPw.equals(LoginForm.getAd().getPassword())){
			JOptionPane.showMessageDialog(this, "Mật khẩu cũ không đúng");
		}else if(!newPw.equals(reNewPw)){
			JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không đúng");
		}
		else{
			
			AdminModel ad = new AdminModel(LoginForm.getAd().getName(), LoginForm.getAd().getDateOfBirth(),
					LoginForm.getAd().getGender(), LoginForm.getAd().getAddress(), LoginForm.getAd().getPhoneNumber(),
					LoginForm.getAd().getAdminId(), LoginForm.getAd().getAccountName(), newPw) ;
			AdminDAOImp.getInstance().update(ad);
			JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công.");
			dispose();
		}
	}
}
