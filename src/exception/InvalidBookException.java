package exception;

public class InvalidBookException extends Exception{

	private String message;
	
	public InvalidBookException(String message) {
		this.message = message;
	}
	
	@Override
    public String toString() {
        return message;
    }
}
