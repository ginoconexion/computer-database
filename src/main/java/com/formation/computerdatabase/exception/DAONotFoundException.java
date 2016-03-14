package com.formation.computerdatabase.exception;

public class DAONotFoundException extends Exception {
	
	  public DAONotFoundException( String message ) {
	        super( message );
	    }

	    public DAONotFoundException( String message, Throwable cause ) {
	        super( message, cause );
	    }

	    public DAONotFoundException( Throwable cause ) {
	        super( cause );
	    }
}
