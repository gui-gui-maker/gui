package com.gui.exception;

import com.gui.jwt.ResultEnum;

public class VeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
    private String message;
    
    
    
    public VeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public VeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public VeException(String message) {
		super(message);
		this.message = message;
	}

	public VeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public VeException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public VeException(ResultEnum result) {
        super(String.valueOf(result.getCode()));
        this.code = String.valueOf(result.getCode());
        this.message = result.getMessage();
    }
    
    public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
