package com.hy.wo.exception;

public class PasswordReqErrorException extends Exception {
	 private static final long serialVersionUID = 8305721352893375071L;
	 
	    public PasswordReqErrorException() {
	        super();
	    }

	    public PasswordReqErrorException(String msg) {
	        super(msg);
	    }
//	    public PasswordReqErrorException(String msg,String message) {
//	        super(msg);
//	        this.message=message;
//	    }
//	    public String getMessage(){
//	    	return this.message;
//	    }
//	    private String message;
}
