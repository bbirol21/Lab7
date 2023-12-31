package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;

import book.*;
import exception.InvalidBookException;
import exception.InvalidLoanException;
import exception.NoSuchBookException;
import loan.*;

public class Library {

	private String name;
	private BookValidator bookValidator;
	private LoanValidator loanValidator;
	private HashMap<String, Book> books;
	private ArrayList<Loan> loans;

	public Library(String name) {
		this.name = name;
		this.bookValidator = new BookValidator();
		this.loanValidator = new LoanValidator();
        this.books = new HashMap<String, Book>();
        this.loans = new ArrayList<Loan>();
	}
	
	public void addBook(Book book) throws InvalidBookException, NoSuchBookException {
		//validate book
		
		boolean passesValidity = true;
		
		try {
			bookValidator.validateISBN(book);
		}
		catch(InvalidBookException e) {
			passesValidity = false;
			System.err.println("InvalidBookException: " + e.toString());
		}
		if(passesValidity) {
			books.put(book.getISBN(),book);
			System.out.println("Book added: " + book.toString());
		}
		
	}

	public void borrowBook(String borrower, String ISBN, LocalDate dueDate) throws NoSuchBookException, InvalidLoanException {
		
		boolean passesValidity = true;
		try {
			//validate book
			bookValidator.validateExistence(books, ISBN, name);
			//validate loan
			loanValidator.validateDueDate(dueDate);
			loanValidator.validateBookExistance(books.get(ISBN));
		}
		catch(InvalidLoanException e) {
			passesValidity = false;
			System.err.println("InvalidLoanException: " + e.toString());
		}
		
		catch(NoSuchBookException e) {
			passesValidity = false;
			System.err.println("NoSuchBookException: " + "Loan aborted because the book with ISBN " + ISBN + " does not exist in the " + name + " library");
		}
		
		if(passesValidity) {
			// Update availability of book
	        Book book = books.get(ISBN);
	        book.setAvailable(false);
	        // Create loan and add to the list
	        Loan loan = new Loan(borrower, book, dueDate);
	        loans.add(loan);
	        System.out.println("Loan created: " + loan.toString() );
		}
        
        
	}
	
	public void returnBook(String ISBN) throws NoSuchBookException {
		
		boolean passesValidity = true;
		try {
			//validate book
			bookValidator.validateExistence(books, ISBN, name);
		}
		catch(NoSuchBookException e) {
			passesValidity = false;
			System.err.println("NoSuchBookException: " + "Loan aborted because the book with ISBN " + ISBN + " does not exist in the " + name + " library");
		}
		if(passesValidity) {
			Book book = books.get(ISBN);
			book.setAvailable(true);
			//Update isReturned
	        for (Loan loan : loans) {
	            if (loan.getBook().getISBN().equals(ISBN)) {
	                loan.setReturned(true);
	                break;
	            }
	        }
	        System.out.println("Book returned: " + book.getTitle() + ", ISBN: " + book.getISBN());
		}
	}

	public void printAllLoans() {
		System.out.println("All loans:");
		for(Loan loan: loans) {
			System.out.println(loan.toString());
		}
	}


	public void printMatchingBooks(String titlePattern) {
		System.out.println("Matching books with '" + titlePattern + "':");
		for (Book book : books.values()) {
            if (book.getTitle().contains(titlePattern)) {
                System.out.println(book.toString());
            }
        }
	}
	
	public void printLibraryCatalog() throws FileNotFoundException{
//		System.out.println("Library catalog:");
//		for (Book book: books.values()) {
//			if(book.isAvailable()) {
//				System.out.println("Book name: " + book.getTitle() + ", ISBN: " + book.getISBN() + "Status: Available");
//			}
//			else {
//				System.out.println("Book name: " + book.getTitle() + ", ISBN: " + book.getISBN() + "Status: On Loan, ");
//			}
//		}
		
		try {
            Formatter formatter = new Formatter(new File("libraryCatalog.txt"));
            for (Book book : books.values()) {
                String bookName = book.getTitle();
                String isbn = book.getISBN();
                boolean isAvailable = book.isAvailable();
                
                Loan desiredLoan = null;
                for(Loan loan : loans) {
                	if(loan.getBook().equals(book))
                		desiredLoan = loan;
                }
                
                
                String borrower = null;
                String dueDate = null;

                if (desiredLoan != null) {
                    borrower = desiredLoan.getBorrower();
                    dueDate = desiredLoan.getDueDate().toString();
                }

                if (isAvailable) {
                    String message = String.format("Book Name: %s, ISBN: %s, Status: Available", bookName, isbn);
                    System.out.println(message);
                    formatter.format("%s%n", message);
                } 
                else {
                    String message = String.format("Book Name: %s, ISBN: %s, Status: On Loan - Borrower: %s, Due Date: %s", bookName, isbn, borrower, dueDate);
                    System.out.println(message);
                    formatter.format("%s%n", message);
                }
            }
		}catch (FileNotFoundException e) {
            System.out.println("Error writing to the library catalog file.");
        }
	}
}
