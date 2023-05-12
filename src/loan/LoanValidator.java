package loan;

import java.time.LocalDate;

import book.Book;
import exception.*;

public class LoanValidator {

	public void validateDueDate(LocalDate dueDate) throws InvalidLoanException {
		LocalDate currentDate = LocalDate.now();

        if (dueDate.isBefore(currentDate)) {
            throw new InvalidLoanException("Loan is invalid because the due date is a future date");
        }
	}
	
	public void validateBookExistance(Book book) throws InvalidLoanException {
		
		if (!book.isAvailable()) {
            throw new InvalidLoanException("The book with given ISBN is not available");
        }
	}
}
