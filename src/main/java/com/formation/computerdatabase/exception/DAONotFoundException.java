package com.formation.computerdatabase.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class DAONotFoundException.
 */
public class DAONotFoundException extends Exception {
	
	  /**
  	 * Instantiates a new DAO not found exception.
  	 *
  	 * @param message the message
  	 */
  	public DAONotFoundException( String message ) {
	        super( message );
	    }

	    /**
    	 * Instantiates a new DAO not found exception.
    	 *
    	 * @param message the message
    	 * @param cause the cause
    	 */
    	public DAONotFoundException( String message, Throwable cause ) {
	        super( message, cause );
	    }

	    /**
    	 * Instantiates a new DAO not found exception.
    	 *
    	 * @param cause the cause
    	 */
    	public DAONotFoundException( Throwable cause ) {
	        super( cause );
	    }
}
