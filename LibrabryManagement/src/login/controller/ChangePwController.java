package login.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import login.view.ChangePasswordForm;

public class ChangePwController implements ActionListener{
	private ChangePasswordForm view;
	
	public ChangePwController(ChangePasswordForm view) {
		super();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		if(cm.equals("Lưu")) {
			this.view.changePw();
		}
	}

}
