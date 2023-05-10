[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/HdlooWVU)
# IMPORTANT

For each lab assignment, **you must include and sign (by writing your name and student id number) the following Pledge of Honor statement at the beginning of your main method source code file. After including this statement, make sure that you do the commit and push operation on GitHub. Otherwise, your lab solution will not be graded.**
```
/* *********** Pledge of Honor ************************************************ *

I hereby certify that I have completed this lab assignment on my own
without any help from anyone else. I understand that the only sources of authorized
information in this lab assignment are (1) the course textbook, (2) the
materials posted at the course website and (3) any study notes handwritten by myself.
I have not used, accessed or received any information from any other unauthorized
source in taking this lab assignment. The effort in the assignment thus belongs
completely to me.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <Name Surname, Student id>
********************************************************************************/
```
## IMPORTANT NOTE 2: After you complete the tasks, do not forget to commit your changes and push them to your repository on Github.

## IMPORTANT NOTE 3: This README.md file only contains the instructions for PRELAB. You will also have INLAB questions.


## COMP 132: Advanced Programming, Spring 2023

# Lab-7 Pre-Lab :  Books Are Us

In this programming lab, you will be mainly practicing **Exception Handling**, and **Files and Streams** concepts by implementing an application (Books Are Us) that simulates a library management system. The library management system involves adding books to the library, borrowing books, and returning books. Each borrowing/returning operation is called a "Loan".  

You are provided with a Java project template and you should do your development on this template. **In this lab we only provide input text and sample output. You are expected to implement the Main.java to obtain this output using the Scanner class to read the input from the file.** Note that we may test your codes with other test cases as well.  

You are free to add extra helper methods (such as getters, setters and toString) or variables if you think they are needed.  

You should also decide on the visibility of the variables (public, protected, private) yourself.  

Here is the folder structure that you should have when you finish implementing the assignment:

src/  
- book/  
-- Book.java  
-- BookValidator.java  
- loan/  
-- Loan.java  
-- LoanValidator.java  
- exception/  
-- InvalidBookException.java  
-- InvalidLoanException.java  
-- NoSuchBookException.java  
- main/  
-- Input.txt  
-- Main.java  
-- Library.java  

### Book Class:

- iSBN, type String.
- title, type String.
- author, type String.
- isAvailable, type boolean.

- A constructor with the signature Book(String ISBN, String title, String author, boolean isAvailable)

### BookValidator Class:

validateISBN method with signature (Book book) validates:
- If the length of the ISBN is different than 13, it throws an InvalidBookException (note: all custom exception classes are explained below).
- If the ISBN contains any non-numeric characters, it throws an InvalidBookException.

validateExistence method with signature (Map<String, Book> books, String ISBN, String libraryName) validates:
- If books Map does not contain a book with the given ISBN, it throws NoSuchBookException.

### Loan Class:

- borrower, type String.
- book, type Book.
- dueDate, type LocalDate.
- isReturned, type boolean.

- A constructor with the signature Loan(String borrower, Book book, LocalDate dueDate)

### LoanValidator Class:

validateDueDate method with signature (LocalDate dueDate). 
- If the dueDate is before the current date, it throws InvalidLoanException.

### InvalidBookException Class:

This is a custom exception class. It takes an error message and prints "Book is invalid because " + error message which states why this exception was thrown.   
**Examples:**  
- If the length of the ISBN of a book is different than 13, it should print "Book is invalid because the length of the ISBN should be 13".  
- If the ISBN contains any non-numeric characters, it should print "Book is invalid because the ISBN should contain only numeric characters".  

### InvalidLoanException Class:

This is a custom exception class. It takes an error message and prints "Loan is invalid because " + error message which states why this exception was thrown.  
**Example:**  
- If the dueDate of the loan is before the current date, it should print "Loan is invalid because the dueDate should be after the current date".

### NoSuchBookException Class:

This is a custom exception class. It takes an error message and prints "Loan aborted because " + error message which states why this exception was thrown. 
**Example:**
- A book with ISBN … is given and this book does not exist in the specified library, it should print "Loan aborted because the book with ISBN … does not exist in the … library".

### Main Class:

This class exists in your template and includes some instructions that you need to follow to test your implemented project.

### Library Class:

- name, type String.
- bookValidator, type BookValidator.
- loanValidator, type LoanValidator.
- books, type HashMap<String, Book>. The keys of the map are the ISBNs of the books. The values of the map are the Book objects.
- loans, type ArrayList.

- A constructor with the signature public Library(String name)

- addBook method with signature (Book book)  
Validate book  
Add
- borrowBook method with signature (String borrower, String ISBN, LocalDate dueDate)  
Validate book and loan  
Update availability of book  
Loan
- returnBook method with signature (String ISBN)  
Validate book  
Update availability of book  
Update isReturned field of loan
- printAllLoans method prints the information of all loans.
- printMatchingBooks method with signature (String titlePattern) and prints the title of all books that match the pattern.

### Input Text that is given as a txt file
--------------------------------------------------------------------------------------------------------
```
1234567890123 Java Programming John Smith
2345678901234 Python Programming Jane Doe
123456789012X JavaScript Programming William Brown
3456789012345 Ruby Programming Lucy Adams
5678901234567 Reinforcement Learning Richard Sutton
```
--------------------------------------------------------------------------------------------------------

### Sample Output
--------------------------------------------------------------------------------------------------------
```
Book added: Java Programming, ISBN: 1234567890123, Author: John Smith
Book added: Python Programming, ISBN: 2345678901234, Author: Jane Doe
InvalidBookException: Book is invalid because the ISBN should contain only numeric characters
Book added: Ruby Programming, ISBN: 3456789012345, Author: Lucy Adams
Book added: Reinforcement Learning, ISBN: 5678901234567, Author: Richard Sutton

Loan created: Borrower: Fatma, Book: Java Programming, Due date: 2023-06-05
Loan created: Borrower: Vahideh, Book: Python Programming, Due date: 2023-06-10
NoSuchBookException: Loan aborted because the book with ISBN 123456789012X does not exist in the MyLibrary library
NoSuchBookException: Loan aborted because the book with ISBN 1111111111111 does not exist in the MyLibrary library
InvalidLoanException: Loan is invalid because the due date should be a future date

Book returned: Java Programming, ISBN: 1234567890123
Book returned: Python Programming, ISBN: 2345678901234
NoSuchBookException: Loan aborted because the book with ISBN 1111111111111 does not exist in the MyLibrary library

All loans:
Loan 1: Borrower: Fatma, Book: Java Programming, Due date: 2023-06-05, Returned: true
Loan 2: Borrower: Vahideh, Book: Python Programming, Due date: 2023-06-10, Returned: true

Matching books with 'Programming':
Book: Java Programming, ISBN: 1234567890123, Author: John Smith
Book: Python Programming, ISBN: 2345678901234, Author: Jane Doe
Book: Ruby Programming, ISBN: 3456789012345, Author: Lucy Adams
```
--------------------------------------------------------------------------------------------------------



