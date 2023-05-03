package book.model;

import java.util.ArrayList;
import java.util.Objects;

import book.dao.BookDAOImp;
import category.model.CategoryModel;

public class BookModel {
	private String bookId;
	private String bookName ;
	CategoryModel category;
	private String author;
	private String publisher;
	private double price;
	private int amount;
	private int remainingAmount;
	public BookModel() {
	}
	
	
	public BookModel(String bookId, String bookName, CategoryModel category, String author, String publisher,
			double price, int amount, int remainingAmount) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.category = category;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.amount = amount;
		this.remainingAmount = remainingAmount;
	}


	public String getBookId() {
		return bookId;
	}


	public void setBookId(String bookId) {
		this.bookId = bookId;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public CategoryModel getCategory() {
		return category;
	}


	public void setCategory(CategoryModel category) {
		this.category = category;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getRemainingAmount() {
		return remainingAmount;
	}


	public void setRemainingAmount(int remainingAmount) {
		this.remainingAmount = remainingAmount;
	}


	public static ArrayList<BookModel> getAllBook(){
		return BookDAOImp.getInstance().selectAll();
		
	}
	
	public static void addBook(BookModel book) {
		BookDAOImp.getInstance().insert(book);
	}
	
	public static void updateBook(BookModel book) {
		BookDAOImp.getInstance().update(book);
	}
	public static void deleteBook(BookModel book) {
		BookDAOImp.getInstance().delete(book);
	}
	public static BookModel getBookById(String id){
		return BookDAOImp.getInstance().selectById(id);
		
	}
	public static ArrayList<BookModel> getBookName(String name){
		return BookDAOImp.getInstance().selectByName(name);
		
	}
	public static ArrayList<BookModel> getBookByCtg(int ctgId){
		return BookDAOImp.getInstance().selectByCtg(ctgId);
		
	}
	public static ArrayList<BookModel> getBookByAuthor(String au){
		return BookDAOImp.getInstance().selectByAuthor(au);
		
	}
	public static ArrayList<BookModel> getBookByPublisher(String pub){
		return BookDAOImp.getInstance().selectByPublisher(pub);
		
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, author, bookId, bookName, category, price, publisher);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookModel other = (BookModel) obj;
		return amount == other.amount && Objects.equals(author, other.author) && Objects.equals(bookId, other.bookId)
				&& Objects.equals(bookName, other.bookName) && Objects.equals(category, other.category)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(publisher, other.publisher);
	}
	@Override
	public String toString() {
		return "BookModel [bookId=" + bookId + ", bookName=" + bookName + ", category=" + category + ", author="
				+ author + ", publisher=" + publisher + ", price=" + price + ", amount=" + amount + "]";
	}
	
	
	
}
