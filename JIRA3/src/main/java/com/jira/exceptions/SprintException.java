/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jira.exceptions;

/**
 *
 * @author ivo
 */
public class SprintException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SprintException() {
    }

    public SprintException(String message) {
        super(message);
    }

    public SprintException(String message, Throwable cause) {
        super(message, cause);
    }

    public SprintException(Throwable cause) {
        super(cause);
    }

    public SprintException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
