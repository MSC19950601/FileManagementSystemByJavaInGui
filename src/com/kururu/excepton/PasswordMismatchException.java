package exception;

public class PasswordMismatchException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordMismatchException() {
        super();
    }
    
    public PasswordMismatchException(String msg) {
        super(msg);
    }
    
    public PasswordMismatchException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public PasswordMismatchException(Throwable cause) {
        super(cause);
    }
}
