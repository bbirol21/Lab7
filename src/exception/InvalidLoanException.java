package exception;

public class InvalidLoanException extends Exception{
	
	private String message;

	public InvalidLoanException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
	
	
}
