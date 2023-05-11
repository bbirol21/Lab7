package book;

public class Book {

	private String ISBN;
	private String title;
	private String author;
	private boolean isAvailable;
	
	public Book(String iSBN, String title, String author, boolean isAvailable) {
		ISBN = iSBN;
		this.title = title;
		this.author = author;
		this.isAvailable = isAvailable;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Title: " + title + " ISBN: " + ISBN + " Author: " + author;
	}
	
	
	
	
}
