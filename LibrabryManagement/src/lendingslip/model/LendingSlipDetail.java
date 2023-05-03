package lendingslip.model;

import java.util.ArrayList;

import book.model.BookModel;
import lendingslip.dao.LDetailDAOImp;

public class LendingSlipDetail {
	private LendingSlip lendingSlip;
	private BookModel book;
	private int amount;

	public LendingSlipDetail(LendingSlip lendingSlip, BookModel book, int amount) {
		super();
		this.lendingSlip = lendingSlip;
		this.book = book;
		this.amount = amount;
	}

	public LendingSlip getLendingSlip() {
		return lendingSlip;
	}

	public void setLendingSlip(LendingSlip lendingSlip) {
		this.lendingSlip = lendingSlip;
	}

	public BookModel getBook() {
		return book;
	}

	public void setBook(BookModel book) {
		this.book = book;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public static void addLD(LendingSlipDetail ld) {
		LDetailDAOImp.getInstance().insert(ld);
	}

	public static void updateLSD(LendingSlipDetail ld) {
		LDetailDAOImp.getInstance().update(ld);
	}
	
//	public static void updateLSDById(LendingSlipDetail ld) {
//		LDetailDAOImp.getInstance().updateByLSId(ld);
//	}
	public static void deleteLSD(LendingSlipDetail ld) {
		LDetailDAOImp.getInstance().delete(ld);
	}
	public static void deleteLSDById(int ldId) {
		LDetailDAOImp.getInstance().deleteById(ldId);
	}
	
	public static ArrayList<LendingSlipDetail> getByBookId(String id) {
		return LDetailDAOImp.getInstance().selectByBookId(id);
	}
	public static ArrayList<LendingSlipDetail> getByLSId(int id) {
		return LDetailDAOImp.getInstance().selectByLSId(id);
	}
	public static LendingSlipDetail getByPK(int LendId, String BookId) {
		return LDetailDAOImp.getInstance().selectByPK(LendId, BookId);
	}

}
