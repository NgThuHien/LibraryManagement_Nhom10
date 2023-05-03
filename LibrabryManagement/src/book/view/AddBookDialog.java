package book.view;

import java.awt.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import book.controller.AddBookController;
import book.controller.ValidateForm;
import book.model.BookModel;
import category.model.CategoryModel;

public class AddBookDialog extends JDialog {

	private JTextField jTextId;
	private JTextField jTextName;
	private JComboBox<String> jCbbCtg;
	private JTextField jTextAuthor;
	private JTextField jTextPublisher;
	private JTextField jTextPrice;
	private JSpinner spinnerAmount;

	public AddBookDialog() {
		this.init();
	}

	private void init() {

		JLabel label1 = new JLabel("Thêm sách");
		label1.setFont(new Font("Tahoma", Font.BOLD, 20));
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(label1, BorderLayout.NORTH);

		JPanel panelInput = new JPanel();
		panelInput.setLayout(new GridBagLayout());
		JLabel labelId = new JLabel("Mã sách:");
		JLabel labelName = new JLabel("Tên sách:");
		JLabel labelCtg = new JLabel("Thể loại:");
		JLabel labelAuthor = new JLabel("Tác giả:");
		JLabel labelPublisher = new JLabel("Nhà xuất bản:");
		JLabel labelPrice = new JLabel("Giá tiền:");
		JLabel labelAmount = new JLabel("Số lượng:");
		Font font2 = new Font("Tahoma", Font.PLAIN, 14);
		labelId.setFont(font2);
		labelName.setFont(font2);
		labelCtg.setFont(font2);
		labelAuthor.setFont(font2);
		labelPublisher.setFont(font2);
		labelPrice.setFont(font2);
		labelAmount.setFont(font2);

		jTextId = new JTextField();
		jTextId.setFont(font2);

		jTextName = new JTextField();
		jTextName.setFont(font2);

		jCbbCtg = new JComboBox<String>();
		jCbbCtg.setFont(font2);
		jCbbCtg.addItem("");
		new CategoryModel();
		ArrayList<CategoryModel> listCtg = CategoryModel.getAllCategory();
		for (CategoryModel category : listCtg) {
			jCbbCtg.addItem(category.getCategoryName());
		}
		jCbbCtg.setPrototypeDisplayValue("");
		jCbbCtg.setMaximumRowCount(5);

		jTextAuthor = new JTextField();
		jTextAuthor.setFont(font2);

		jTextPublisher = new JTextField();
		jTextPublisher.setFont(font2);

		jTextPrice = new JTextField();
		jTextPrice.setFont(font2);

		spinnerAmount = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
		// chỉnh cho giá trị sang bên trái.
		((JSpinner.DefaultEditor) spinnerAmount.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
		spinnerAmount.setFont(font2);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.2;
		panelInput.add(labelId, c);
		c.gridy = 1;
		panelInput.add(labelName, c);
		c.gridy = 2;
		panelInput.add(labelCtg, c);
		c.gridy = 3;
		panelInput.add(labelAuthor, c);
		c.gridy = 4;
		panelInput.add(labelPublisher, c);
		c.gridy = 5;
		panelInput.add(labelPrice, c);
		c.gridy = 6;
		panelInput.add(labelAmount, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.8;
		c.fill = GridBagConstraints.BOTH;
		panelInput.add(jTextId, c);
		c.gridy = 1;
		panelInput.add(jTextName, c);
		c.gridy = 2;
		panelInput.add(jCbbCtg, c);
		c.gridy = 3;
		panelInput.add(jTextAuthor, c);
		c.gridy = 4;
		panelInput.add(jTextPublisher, c);
		c.gridy = 5;
		panelInput.add(jTextPrice, c);
		c.gridy = 6;
		panelInput.add(spinnerAmount, c);

		this.getContentPane().add(BorderLayout.CENTER, panelInput);

		ActionListener action = new AddBookController(this);

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
		this.getContentPane().add(BorderLayout.SOUTH, panelButtons);

		this.setTitle("Thêm sách");
		this.setSize(500, 500);
		((JComponent) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//this.setVisible(true);
	}

	public void addBook() {
		String id = this.jTextId.getText().trim();
		String name = this.jTextName.getText().trim();
		int ctg = this.jCbbCtg.getSelectedIndex();
		CategoryModel category = CategoryModel.getCtgById(ctg);
		String author = this.jTextAuthor.getText().trim();
		String publisher = this.jTextPublisher.getText().trim();
		String p = this.jTextPrice.getText().trim();
		int amount = (Integer) this.spinnerAmount.getValue();

		boolean isOK = ValidateForm.validateForm(id, name, ctg, author, publisher, p);
		if (BookModel.getBookById(id) != null) {
			JOptionPane.showMessageDialog(this, "Đã tồn tại sách có ID này.");
			isOK = false;
		}
		if (isOK) {
			double price = Double.valueOf(p);
			BookModel b = new BookModel(id, name, category, author, publisher, price, amount, amount);
			BookView.addBookToTable(b);
			BookModel.addBook(b);
			JOptionPane.showMessageDialog(this, "Thêm sách thành công.");
		}
	}

	public void clearForm() {
		jTextId.setText("");
		jTextName.setText("");
		jCbbCtg.setSelectedIndex(0);
		jTextAuthor.setText("");
		jTextPublisher.setText("");
		jTextPrice.setText("");
		spinnerAmount.setValue(1);
	}

}
