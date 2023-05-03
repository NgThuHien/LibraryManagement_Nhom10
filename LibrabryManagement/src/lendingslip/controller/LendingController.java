package lendingslip.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import home.view.MainView;
import lendingslip.view.AddLendingDialog;
import lendingslip.view.LendingView;
import lendingslip.view.EditLendingDiaLog;

public class LendingController implements ActionListener {
	private LendingView view;

	public LendingController(LendingView view) {
		super();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();

		// JOptionPane.showMessageDialog(view, "Bạn vừa nhấn vào: " + cm);
		if (cm.equals("Thêm")) {
			AddLendingDialog addLend = new AddLendingDialog();
			addLend.setVisible(true);
		} else if (cm.equals("Sửa")) {
			this.view.checkSelected();
		} else if (cm.equals("Xóa")) {
			this.view.deleteLendingSlip();
		} else if (cm.equals("Tìm kiếm")) {
			this.view.searchLendingSlip();
		} else if (cm.equals("Đặt lại")) {
			this.view.resetView();
		} else if (cm.equals("Xem chi tiết phiếu")) {
			this.view.detail();
		} else if (cm.equals("Xem thông tin sinh viên")) {
			this.view.detailST();
		}else if (cm.equals("Thoát")) {
			this.view.dispose();
			MainView mainView = new MainView();
			mainView.setVisible(true);
		}

	}

}
