package com.kururu.excepton;

public class NoUserFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoUserFoundException() {
        super();
    }
    
    public NoUserFoundException(String msg) {
        super(msg);
    }
    
    public NoUserFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public NoUserFoundException(Throwable cause) {
        super(cause);
    }

}
