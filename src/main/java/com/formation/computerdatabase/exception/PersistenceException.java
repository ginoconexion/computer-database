package com.formation.computerdatabase.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class PersistenceException.
 */
public class PersistenceException extends Exception {
	
    /**
     * Instantiates a new persistence exception.
     *
     * @param message the message
     */
    public PersistenceException( String message ) {
        super( message );
    }

    /**
     * Instantiates a new persistence exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public PersistenceException( String message, Throwable cause ) {
        super( message, cause );
    }

    /**
     * Instantiates a new persistence exception.
     *
     * @param cause the cause
     */
    public PersistenceException( Throwable cause ) {
        super( cause );
    }
}
