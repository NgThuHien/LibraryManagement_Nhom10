package lendingslip.view;

import java.awt.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import book.model.BookModel;
import lendingslip.controller.LendingController;
import lendingslip.model.LendingSlip;
import lendingslip.model.LendingSlipDetail;
import student.model.StudentModel;

public class LendingView extends JFrame {

	private static ArrayList<LendingSlip> listdata;
	private static ArrayList<LendingSlipDetail> listdetail;
	private JTable tableDetail;
	private JTable tableList;
	private static int selectedIndex;
	private DefaultTableModel tableModelDetai;
	private JTextField jTextLId;
	private JTextField jTextSId;
	private JComboBox<String> statusComboBox;
	private static DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LendingView frame = new LendingView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LendingView() {
		this.init();
		listdata = LendingSlip.getAll();
		tableModel = (DefaultTableModel) tableList.getModel();
		tableModelDetai = (DefaultTableModel) tableDetail.getModel();
		LendingView.showListLs(listdata);
	}

	public void init() {

		getContentPane().setLayout(new BorderLayout(10, 20));

		Font font1 = new Font("Tahoma", Font.BOLD, 14);
		Font font2 = new Font("Tahoma", Font.PLAIN, 14);

		ActionListener action = new LendingController(this);

		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 1), "Tìm kiếm", TitledBorder.LEADING,
				TitledBorder.TOP, font1, Color.BLACK));
		panelSearch.setLayout(new GridBagLayout());
		JLabel labelLId = new JLabel("Mã phiếu:");
		JLabel labelSId = new JLabel("Mã sinh viên:");
		JLabel labelStatus = new JLabel("Trạng thái:");

		labelLId.setFont(font2);
		labelSId.setFont(font2);
		labelStatus.setFont(font2);

		jTextLId = new JTextField();
		jTextLId.setFont(font2);
		jTextSId = new JTextField();
		jTextSId.setFont(font2);

		statusComboBox = new JComboBox<String>();
		statusComboBox.addItem("");
		statusComboBox.addItem("Đang mượn");
		statusComboBox.addItem("Đã trả");
		statusComboBox.addItem("Quá hạn");
		statusComboBox.setFont(font2);

		JButton buttonSearch = new JButton("Tìm kiếm");
		buttonSearch.setFont(font2);
		buttonSearch.addActionListener(action);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 10, 10, 10);

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.2;
		panelSearch.add(labelLId, c);
		c.gridy = 1;
		panelSearch.add(labelSId, c);
		c.gridy = 2;
		panelSearch.add(labelStatus, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.8;
		c.fill = GridBagConstraints.BOTH;
		panelSearch.add(jTextLId, c);
		c.gridy = 1;
		panelSearch.add(jTextSId, c);
		c.gridy = 2;
		panelSearch.add(statusComboBox, c);

		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 3;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		panelSearch.add(buttonSearch, c);

		this.getContentPane().add(panelSearch, BorderLayout.NORTH);

		JPanel panelInfor = new JPanel();
		panelInfor.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 1), "Danh sách phiếu mượn",
				TitledBorder.LEADING, TitledBorder.TOP, font1, Color.BLACK));

		tableList = new JTable();
		tableList.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Mã phiếu", "Người lập phiếu",
				"Mã sinh viên", "Ngày mượn", "Ngày hạn trả", "Ngày trả", "Trạng thái" }));
		tableList.setFont(font2);
		tableList.setDefaultEditor(Object.class, null);
		tableList.getTableHeader().setReorderingAllowed(false);
		panelInfor.setLayout(new GridLayout(2, 1, 0, 20));
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JScrollPane scrollPaneList = new JScrollPane(tableList);
		panelInfor.add(scrollPaneList);

		JPanel panelDetail = new JPanel();
		panelDetail.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 0), "Chi tiết phiếu mượn",
				TitledBorder.LEADING, TitledBorder.TOP, font1, Color.BLACK));
		panelDetail.setLayout(new BorderLayout(0, 0));

		tableDetail = new JTable();
		tableDetail
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Mã phiếu", "Mã sách", "Số lượng" }));
		tableDetail.setFont(font2);
		tableDetail.setDefaultEditor(Object.class, null);
		tableDetail.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollPaneDetail = new JScrollPane(tableDetail);
		panelDetail.add(scrollPaneDetail);

		panelInfor.add(panelDetail);

		this.getContentPane().add(panelInfor, BorderLayout.CENTER);

		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

		JButton buttonAdd = new JButton("Thêm");
		buttonAdd.setFont(font2);
		panelButtons.add(buttonAdd);
		buttonAdd.addActionListener(action);

		JButton buttonUpdate = new JButton("Sửa");
		buttonUpdate.setFont(font2);
		panelButtons.add(buttonUpdate);
		buttonUpdate.addActionListener(action);

		JButton buttonDelete = new JButton("Xóa");
		buttonDelete.setFont(font2);
		panelButtons.add(buttonDelete);
		buttonDelete.addActionListener(action);

		JButton buttonReset = new JButton("Đặt lại");
		buttonReset.setFont(font2);
		panelButtons.add(buttonReset);
		buttonReset.addActionListener(action);

		JButton buttonDetail = new JButton("Xem chi tiết phiếu");
		buttonDetail.setFont(font2);
		panelButtons.add(buttonDetail);
		buttonDetail.addActionListener(action);

		JButton buttonStudent = new JButton("Xem thông tin sinh viên");
		buttonStudent.setFont(font2);
		panelButtons.add(buttonStudent);
		buttonStudent.addActionListener(action);

		JButton buttonExit = new JButton("Thoát");
		buttonExit.setFont(font2);
		panelButtons.add(buttonExit);
		buttonExit.addActionListener(action);
		this.getContentPane().add(panelButtons, BorderLayout.SOUTH);

		this.setTitle("Quản lý mượn trả");
		this.setSize(1200, 700);
		((JComponent) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void showListLs(ArrayList<LendingSlip> list) {
		tableModel.setRowCount(0);
		for (LendingSlip ls : list) {
			tableModel.addRow(
					new Object[] { ls.getLendingSlipId(), ls.getAdmin().getName(), ls.getStudent().getStudentId(),
							ls.getLendDate(), ls.getDueDate(), ls.getReturnDate(), ls.getStatus() });
		}
		tableModel.fireTableDataChanged();
	}

	public void showListDetail(ArrayList<LendingSlipDetail> list) {
		tableModelDetai.setRowCount(0);
		for (LendingSlipDetail ls : list) {
			tableModelDetai.addRow(
					new Object[] { ls.getLendingSlip().getLendingSlipId(), ls.getBook().getBookId(), ls.getAmount() });
		}
		tableModelDetai.fireTableDataChanged();
	}

	public static void addToTable(LendingSlip ls) {

		tableModel.addRow(new Object[] { ls.getLendingSlipId(), ls.getAdmin().getName(), ls.getStudent().getStudentId(),
				ls.getLendDate(), ls.getDueDate(), ls.getReturnDate(), ls.getStatus() });
		tableModel.fireTableDataChanged();
		listdata.add(ls);
	}

	public static int getSizeList() {
		return listdata.size();
	}

	public void detail() {
		selectedIndex = tableList.getSelectedRow();
		if (selectedIndex == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu mượn để xem chi tiết!");
		} else {
			listdetail = LendingSlipDetail.getByLSId(listdata.get(selectedIndex).getLendingSlipId());
			this.showListDetail(listdetail);
		}

	}

	public void searchLendingSlip() {
		// int lsId = Integer.valueOf(this.jTextLId.getText());
		Set<LendingSlip> notSatisfied = new HashSet<>();
		if (!this.jTextLId.getText().isEmpty()) {
			try {
				int lsId = Integer.parseInt(this.jTextLId.getText());

				if (lsId > 0) {
					LendingSlip ls_id = LendingSlip.getById(lsId);
					for (LendingSlip ls : LendingSlip.getAll()) {
						if (!ls.equals(ls_id)) {
							notSatisfied.add(ls);
						}
					}
				}
			} catch (NumberFormatException e) {
				// Nếu nhập liệu không phải số, thực hiện xử lý lỗi ở đây, ví dụ:
				tableModel.setRowCount(0);
				JOptionPane.showMessageDialog(this, "Không có phiếu nào được tìm thấy");
			}
		}
		String stdId = this.jTextSId.getText();
		String status = (String) this.statusComboBox.getSelectedItem();
		if (stdId.trim().length() > 0) {
			ArrayList<LendingSlip> b_name = LendingSlip.getByStId(stdId);
			for (LendingSlip ls : LendingSlip.getAll()) {
				if (!b_name.contains(ls)) {
					notSatisfied.add(ls);
				}
			}
		}
		if (status.trim().length() > 0) {
			ArrayList<LendingSlip> b_status = LendingSlip.getByStatus(status);
			for (LendingSlip ls : LendingSlip.getAll()) {
				if (!b_status.contains(ls)) {
					notSatisfied.add(ls);
				}
			}
		}

		ArrayList<LendingSlip> satisfied = new ArrayList<>();
		for (LendingSlip ls : LendingSlip.getAll()) {
			if (!notSatisfied.contains(ls)) {
				satisfied.add(ls);
			}
		}
		if (satisfied.isEmpty()) {
			tableModel.setRowCount(0);
			JOptionPane.showMessageDialog(this, "Không có phiếu nào được tìm thấy");
		} else {
			showListLs(satisfied);
		}
	}

	public void checkSelected() {
		selectedIndex = tableList.getSelectedRow();
		// System.out.println(selectedIndex);
		if (listdata.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa có phiếu nào trong danh sách.");
		} else if (selectedIndex == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 phiếu để sửa!");
		} else {
			EditLendingDiaLog edit = new EditLendingDiaLog();
			edit.setData(listdata.get(selectedIndex));
			edit.setVisible(true);
		}
	}

	public static void removeRowSelect() {
		tableModel.removeRow(selectedIndex);
		tableModel.fireTableDataChanged();
	}

	public static void insertRow(Object[] row) {
		tableModel.insertRow(selectedIndex, row);
		tableModel.fireTableDataChanged();
	}

	public static void updateList(LendingSlip ls) {
		listdata.remove(selectedIndex);
		listdata.add(selectedIndex, ls);
	}

	public static void deleteListDetail(LendingSlipDetail lsd) {
		listdetail.remove(lsd);
	}

	public void resetView() {
		jTextLId.setText("");
		jTextSId.setText("");
		statusComboBox.setSelectedItem("");
		showListLs(listdata);
		tableModelDetai.setRowCount(0);

	}

	public void detailST() {
		selectedIndex = tableList.getSelectedRow();
		if (selectedIndex == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 sinh viên để xem thông tin!");
		} else {
			String infor = StudentModel.getStudentById(listdata.get(selectedIndex).getStudent().getStudentId())
					.toString();
			JOptionPane.showMessageDialog(this, infor);
		}

	}

	public void deleteLendingSlip() {
		selectedIndex = tableList.getSelectedRow();
		if (listdata.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Danh sách phiếu mượn rỗng.");
		} else if (selectedIndex == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn sách để xóa!");
		} else {
			int choose = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?");
			if (choose == JOptionPane.YES_OPTION) {
				// boolean flag = checkDelLS(listdata.get(selectedIndex));
				if (listdata.get(selectedIndex).getStatus().equals("Đã trả")) {
					LendingSlipDetail.deleteLSDById(listdata.get(selectedIndex).getLendingSlipId());
					LendingSlip.deleteLS(listdata.get(selectedIndex));
					listdata.remove(selectedIndex);
					tableModel.removeRow(selectedIndex);
					tableModel.fireTableDataChanged();
					JOptionPane.showMessageDialog(this, "Xóa thành công");

				} else {
					JOptionPane.showMessageDialog(this, "Không thể xóa phiếu mượn này vì sách chưa được trả.");
				}
			}

		}

	}

//	private boolean checkDelLS(LendingSlip lendingSlip) {
//		
//	}

}
