package lendingslip.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;

import admin.model.AdminModel;
import book.model.BookModel;
import lendingslip.controller.AddLendingController;
import lendingslip.controller.ValidateFormLD;
import lendingslip.model.LendingSlip;
import lendingslip.model.LendingSlipDetail;
import student.model.StudentModel;

public class AddLendingDialog extends JDialog {

	private JTextField jTextAdName;
	private JTextField jTextStdId;
	private JTextField jTextBookId;
	private JSpinner spinnerAmount;
	private JSpinner spinnerLendD;
	private JSpinner spinnerDueD;
	private JButton buttonAddBook;
	private ArrayList<String> note = new ArrayList<>();
	private JTextArea textNote;

	public AddLendingDialog() {
		this.init();
	}

	private void init() {

		JLabel labelTitel = new JLabel("Thêm phiếu mượn: ");
		labelTitel.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelTitel.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(labelTitel, BorderLayout.NORTH);

		ActionListener action = new AddLendingController(this);

		JPanel panelInput = new JPanel();
		panelInput.setLayout(new GridBagLayout());
		JLabel labelAdName = new JLabel("Mã admin lập phiếu:");
		JLabel labelStdId = new JLabel("Mã sinh viên:");
		JLabel labelLendDate = new JLabel("Ngày mượn:");
		JLabel labelDueDate = new JLabel("Hạn trả:");
		JLabel labelBookId = new JLabel("Mã sách:");
		JLabel labelAmount = new JLabel("Số lượng:");

		

		Font font2 = new Font("Tahoma", Font.PLAIN, 14);
		labelAdName.setFont(font2);
		labelStdId.setFont(font2);
		labelLendDate.setFont(font2);
		labelDueDate.setFont(font2);
		labelBookId.setFont(font2);
		labelAmount.setFont(font2);
		

		jTextAdName = new JTextField();
		jTextAdName.setFont(font2);

		jTextStdId = new JTextField();
		jTextStdId.setFont(font2);

		// Tạo SpinnerDateModel cho lend date với giá trị mặc định là ngày hiện tại
//		java.sql.Timestamp currentTimestamp1 = new java.sql.Timestamp(System.currentTimeMillis());
//		java.sql.Date currentDate1 = new java.sql.Date(currentTimestamp1.getTime());
//		SpinnerDateModel lendDateModel = new SpinnerDateModel(currentDate1, null, null, Calendar.DAY_OF_MONTH);
//
//		// Tạo JSpinner và truyền vào SpinnerDateModel cho lend date
//		spinnerLendD = new JSpinner(lendDateModel);
//		spinnerLendD.setFont(font2);
//
//		// Đặt định dạng ngày tháng cho JSpinner cho lend date
//		JSpinner.DateEditor lendDateEditor = new JSpinner.DateEditor(spinnerLendD, "dd/MM/yyyy");
//		spinnerLendD.setEditor(lendDateEditor);

		// Tạo SpinnerDateModel cho due date với giá trị mặc định là ngày hiện tại
//		java.sql.Timestamp currentTimestamp2 = new java.sql.Timestamp(System.currentTimeMillis());
//		java.sql.Date currentDate2 = new java.sql.Date(currentTimestamp2.getTime());
//		SpinnerDateModel dueDateModel = new SpinnerDateModel(currentDate2, null, null, Calendar.DAY_OF_MONTH);
//
//		// Tạo JSpinner và truyền vào SpinnerDateModel cho due date
//		spinnerDueD = new JSpinner(dueDateModel);
//		spinnerDueD.setFont(font2);
//
//		// Đặt định dạng ngày tháng cho JSpinner cho due date
//		JSpinner.DateEditor dueDateEditor = new JSpinner.DateEditor(spinnerDueD, "dd/MM/yyyy");
//		spinnerDueD.setEditor(dueDateEditor);

		spinnerLendD = new JSpinner(new SpinnerDateModel());
		spinnerLendD.setFont(font2);
		JSpinner.DateEditor lendDateEditor = new JSpinner.DateEditor(spinnerLendD, "dd/MM/yyyy");
		spinnerLendD.setEditor(lendDateEditor);
		
		spinnerDueD = new JSpinner(new SpinnerDateModel());
		spinnerDueD.setFont(font2);
		JSpinner.DateEditor dueDateEditor = new JSpinner.DateEditor(spinnerDueD, "dd/MM/yyyy");
		spinnerDueD.setEditor(dueDateEditor);
		
		jTextBookId = new JTextField();
		jTextBookId.setFont(font2);

		spinnerAmount = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
		// chỉnh cho giá trị sang bên trái.
		((JSpinner.DefaultEditor) spinnerAmount.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
		spinnerAmount.setFont(font2);

		textNote = new JTextArea("");
		textNote.setFont(font2);
		textNote.setWrapStyleWord(true);
		textNote.setLineWrap(true);
		textNote.setEditable(false);
		textNote.setBorder(null);
		textNote.setOpaque(false);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 15, 10, 15);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.1;
		panelInput.add(labelAdName, c);
		c.gridy = 1;
		panelInput.add(labelStdId, c);
		c.gridy = 2;
		panelInput.add(labelLendDate, c);
		c.gridy = 3;
		panelInput.add(labelDueDate, c);
		c.gridy = 4;
		panelInput.add(labelBookId, c);
		c.gridy = 5;
		panelInput.add(labelAmount, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.9;
		c.fill = GridBagConstraints.BOTH;
		panelInput.add(jTextAdName, c);
		c.gridy = 1;
		panelInput.add(jTextStdId, c);
		c.gridy = 2;
		panelInput.add(spinnerLendD, c);
		c.gridy = 3;
		panelInput.add(spinnerDueD, c);
		c.gridy = 4;
		panelInput.add(jTextBookId, c);
		c.gridy = 5;
		panelInput.add(spinnerAmount, c);
		c.gridy = 6;
		//c.gridwidth = 2;
		//c.gridheight = 5;
		//c.fill = GridBagConstraints.BOTH;
		//if(textNote.getLineCount() >= 5) {
		//	 JScrollPane scrollPane = new JScrollPane(textNote);
		//	 scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		//	 panelInput.add(scrollPane, c);
		//}
		panelInput.add(textNote, c);

		c.gridy = 7;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		buttonAddBook = new JButton("Thêm sách vào phiếu");
		buttonAddBook.setFont(font2);
		buttonAddBook.addActionListener(action);
		panelInput.add(buttonAddBook, c);

		this.getContentPane().add(BorderLayout.CENTER, panelInput);

		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

		JButton buttonAdd = new JButton("Thêm");
		buttonAdd.setFont(font2);
		panelButtons.add(buttonAdd);
		buttonAdd.addActionListener(action);

		JButton buttonClear = new JButton("Đặt lại");
		buttonClear.setFont(font2);
		panelButtons.add(buttonClear);
		buttonClear.addActionListener(action);

		JButton buttonExit = new JButton("Hủy");
		buttonExit.setFont(font2);
		panelButtons.add(buttonExit);
		buttonExit.addActionListener(action);

		this.getContentPane().add(BorderLayout.SOUTH, panelButtons);

		this.setTitle("Thêm phiếu mượn");
		this.setSize(500, 600);
		((JComponent) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new AddLendingDialog();
	}

	public void clearForm() {
		jTextAdName.setText("");
		jTextStdId.setText("");
		// Thiết lập lại giá trị mặc định của SpinnerDateModel cho lend date
		java.sql.Timestamp currentTimestamp1 = new java.sql.Timestamp(System.currentTimeMillis());
		java.sql.Date currentDate1 = new java.sql.Date(currentTimestamp1.getTime());
		((SpinnerDateModel) spinnerLendD.getModel()).setValue(currentDate1);
		// Thiết lập lại giá trị mặc định của SpinnerDateModel cho due date
		java.sql.Timestamp currentTimestamp2 = new java.sql.Timestamp(System.currentTimeMillis());
		java.sql.Date currentDate2 = new java.sql.Date(currentTimestamp2.getTime());
		((SpinnerDateModel) spinnerDueD.getModel()).setValue(currentDate2);
		jTextBookId.setText("");
		spinnerAmount.setValue(1);
		textNote.setText("");
		note = new ArrayList<String>();
	}

	public void addLSlip() {
		String adId = this.jTextAdName.getText().trim();
		String studentId = this.jTextStdId.getText().trim();
		String bookId = this.jTextBookId.getText().trim();
		java.sql.Date lendDate = new java.sql.Date(((java.util.Date) spinnerLendD.getValue()).getTime());
		java.sql.Date dueDate = new java.sql.Date(((java.util.Date) spinnerDueD.getValue()).getTime());
		int amount = (int) this.spinnerAmount.getValue();

		if (ValidateFormLD.validateForm(adId, studentId, bookId, lendDate, dueDate, amount)) {
			int adminId = Integer.valueOf(adId);

			LendingSlip l1 = new LendingSlip(AdminModel.getAdminById(adminId), StudentModel.getStudentById(studentId),
					lendDate, dueDate, null, "Đang mượn");
			LendingSlip.addLD(l1);
			
			LendingSlip l2 = new LendingSlip(LendingSlip.getIDMax(), AdminModel.getAdminById(adminId),
					StudentModel.getStudentById(studentId), lendDate, dueDate, null, "Đang mượn");
			
			if (note.isEmpty()) {
				LendingSlipDetail ld = new LendingSlipDetail(l2, BookModel.getBookById(bookId), amount);
				LendingSlipDetail.addLD(ld);
			} else {
				for (String string : note) {
					String[] str = string.split(", Số lượng: ");
					BookModel b = BookModel.getBookById(str[0].trim());
					int a = Integer.valueOf(str[1].trim());
					LendingSlipDetail ld = new LendingSlipDetail(l2, b, a);
					LendingSlipDetail.addLD(ld);
				}
			}
			
			LendingView.addToTable(l2);
			JOptionPane.showMessageDialog(this, "Thêm phiếu mượn thành công.");
		}

	}

	public void addBookToLS() {
		String bookId = this.jTextBookId.getText().trim();
		int amount = (int) this.spinnerAmount.getValue();
		boolean isOK = true;
		if (!bookId.matches("^[a-zA-Z0-9]{1,15}$")) {
			JOptionPane.showMessageDialog(null, "Mã sách không hợp lệ.");
			isOK = false;
		} else if (BookModel.getBookById(bookId) == null) {
			JOptionPane.showMessageDialog(null, "Không tồn tại sách có mã " + bookId);
			isOK = false;
		} else if (amount > 5) {
			JOptionPane.showMessageDialog(null, "Chỉ được mượn tối đa 5 quyển sách.");
			isOK = false;
		} else if (BookModel.getBookById(bookId).getRemainingAmount() < amount) {
			JOptionPane.showMessageDialog(null, "Số lượng sách sẵn có không đủ");
			isOK = false;
		}
		if (isOK) {
			int S = 0;
			ArrayList<String> listId = new ArrayList<>();
			for (String string : note) {
				String[] str = string.split(", Số lượng: ");
				listId.add(str[0].trim());
				int a = Integer.valueOf(str[1].trim());
				S += a;
			}
			if(listId.contains(bookId)) {
				JOptionPane.showMessageDialog(this, "Sách này đã có trong phiếu mượn này rồi.");	
			}else if((amount + S) > 5) {
				JOptionPane.showMessageDialog(this, "Chỉ được mượn tối đa 5 quyển sách, "
						+ "phiếu mượn này đã cho mượn "+ S + " sách rồi.");	
			}else {
			String n = bookId + ", Số lượng: " + amount;
			note.add(n);
			String text = "";
			for (String string : note) {
				text += "Mã sách: " + string + "\n";
			}
			textNote.setText(text);
			}
		}
	}
}
