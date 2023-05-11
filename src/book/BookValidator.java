package book;

import java.util.Map;

import exception.*;

public class BookValidator {

	public void validateISBN(Book book) throws InvalidBookException {
		
		if(book.getISBN().length() != 13) {
			throw new InvalidBookException("Book is invalid because the ISBN should contain 13 characters");
			
		}
		
		boolean containsNonDigit = false;
	    for (char c : book.getISBN().toCharArray()) {
	        if (!Character.isDigit(c)) {
	            containsNonDigit = true;
	            break;
	        }
	    }
	    
	    if (containsNonDigit) {
	        throw new InvalidBookException("Book is invalid because the ISBN should contain only numeric characters");
	    }

	}
	
	public void validateExistence(Map<String, Book> books, String ISBN, String libraryName) throws NoSuchBookException {
		
		if (!books.containsKey(ISBN)) {
            throw new NoSuchBookException("Doesnt exist.");
        }
	}
}
