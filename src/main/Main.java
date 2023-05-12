package main;

import java.util.Scanner;

import book.Book;
import exception.InvalidBookException;
import exception.InvalidLoanException;
import exception.NoSuchBookException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

// Import necessary classes

/* *********** Pledge of Honor ************************************************ *

I hereby certify that I have completed this lab assignment on my own
without any help from anyone else. I understand that the only sources of authorized
information in this lab assignment are (1) the course textbook, (2) the
materials posted at the course website and (3) any study notes handwritten by myself.
I have not used, accessed or received any information from any other unauthorized
source in taking this lab assignment. The effort in the assignment thus belongs
completely to me.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <Bartu Birol, 79227>
********************************************************************************/



public class Main {
    public static void main(String[] args) throws IOException, InvalidBookException, NoSuchBookException, InvalidLoanException {
        // Create a library
    	Library lib = new Library("Koç University");

        // ToDo: Add books to the library via reading from text file with Scanner
        // !! Do not forget to handle exceptions
    	
    	File filename = new File("/Users/student/eclipse-workspace/spring2023-lab07-bbirol21/bin/main/Input.txt");
    	Scanner scan = null;
    	try {
    		scan = new Scanner(filename);
    		
    		while(scan.hasNextLine()) {
    			String line = scan.nextLine();
                String[] bookData = line.split(" ");
    			
                String isbn = bookData[0];
                String title = bookData[1]+" "+bookData[2];
                String author = bookData[3]+" "+bookData[4];
                boolean isAvailable = true;
                
                Book book = new Book(isbn, title, author, isAvailable);
                lib.addBook(book);
    		}
    		scan.close();
    		
    	}catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	System.out.println();
        // ToDo: Create loans via calling borrowBook method of library
        // !! Do not forget to handle exceptions
    	lib.borrowBook("Fatma", "1234567890123", LocalDate.parse("2023-06-05"));
    	lib.borrowBook("Vahideh","2345678901234" , LocalDate.parse("2023-06-10"));
    	lib.borrowBook("Bartu", "123456789012X", LocalDate.parse("2023-06-10"));
    	lib.borrowBook("Barış", "1111111111111", LocalDate.parse("2023-06-10"));
    	lib.borrowBook("Güneş", "3456789012345", LocalDate.parse("2023-04-10"));
    	
    	
    	System.out.println();
        // ToDo: Get returns via calling returnBook method of library
        // !! Do not forget to handle exceptions
    	lib.returnBook("1234567890123");
    	//lib.returnBook("2345678901234");
    	lib.returnBook("1111111111111");
    	

        System.out.println();
        // ToDo: Print all loans
        lib.printAllLoans();

        System.out.println();
        // ToDo: Print all books that includes "Programming" in its name
        lib.printMatchingBooks("Programming");

        
        System.out.println();
        lib.printLibraryCatalog();
    }
}

