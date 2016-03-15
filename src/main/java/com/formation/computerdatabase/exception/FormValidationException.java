package com.formation.computerdatabase.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class FormValidationException.
 */
public class FormValidationException extends Exception {
	
    /**
     * Instantiates a new form validation exception.
     *
     * @param message the message
     */
    public FormValidationException( String message ) {
        super( message );
    }

    /**
     * Instantiates a new form validation exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public FormValidationException( String message, Throwable cause ) {
        super( message, cause );
    }

    /**
     * Instantiates a new form validation exception.
     *
     * @param cause the cause
     */
    public FormValidationException( Throwable cause ) {
        super( cause );
    }
}
