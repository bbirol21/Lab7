package loan;
import book.Book;
import java.time.LocalDate;

public class Loan {
	
	private String borrower;
	private Book book;
	private LocalDate dueDate;
	private boolean isReturned;
	private static int loanCounter = 1;
	private int loanNo;
	
	public Loan(String borrower, Book book, LocalDate dueDate) {
		this.borrower = borrower;
		this.book = book;
		this.dueDate = dueDate;
		this.isReturned = false;
		this.loanNo = loanCounter++;
		
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isReturned() {
		return isReturned;
	}

	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}

	@Override
	public String toString() {
		return "Loan " + loanNo + ": Borrower: " + borrower + ", Book: "+ book.getTitle() + ", Due date: " + dueDate + ", Returned: " + isReturned;
	}
	

	

}
