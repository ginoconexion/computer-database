package com.formation.computerdatabase.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.persistence.impl.ComputerDaoImpl;
import com.formation.computerdatabase.service.impl.CompanyDaoServiceImpl;


// TODO: Auto-generated Javadoc
/**
 * A factory for creating Connexion objects.
 */
public enum ConnexionFactory {
	
	/** The instance. */
	INSTANCE;
	
	/** The Constant FICHIER_PROPERTIES. */
	private static final String FICHIER_PROPERTIES       = "dao.properties";
    
    /** The Constant PROPERTY_URL. */
    private static final String PROPERTY_URL             = "url";
    
    /** The Constant PROPERTY_DRIVER. */
    private static final String PROPERTY_DRIVER          = "driver";
    
    /** The Constant PROPERTY_NOM_UTILISATEUR. */
    private static final String PROPERTY_NOM_UTILISATEUR = "utilisateur";
    
    /** The Constant PROPERTY_MOT_DE_PASSE. */
    private static final String PROPERTY_MOT_DE_PASSE    = "password";
    
    /** The properties. */
    private Properties properties;
    
    /** The computer dao impl. */
    private static ComputerDaoImpl computerDaoImpl = ComputerDaoImpl.INSTANCE;
    
    /** The company dao impl. */
    private static CompanyDaoImpl companyDaoImpl = CompanyDaoImpl.INSTANCE;
	
	
    /**
     * Constructeur privé.
     */
	private ConnexionFactory() {
		
		properties = new Properties();
		try {
			properties.load(ConnexionFactory.class.getClassLoader().getResourceAsStream(FICHIER_PROPERTIES));
		} catch (IOException e) {
			String message = "Le fichier de propriété n'a pas pu être chargé";
			System.err.println(message);
			// on lève une exception runtime car le logiciel ne peut fonctionner sinon
			throw new RuntimeException(message, e);
		}
		
		// chargement du driver jdbc
		try {
			Class.forName(properties.getProperty(PROPERTY_DRIVER));
		} catch (ClassNotFoundException e) {
			String message = "Le driver n'a pas pu être chargé";
			System.err.println(message);
			throw new RuntimeException(message, e);
		}
	}
	
	

	/**
	 * Gets the computer dao impl.
	 *
	 * @return the computer dao impl
	 */
	public static ComputerDaoImpl getComputerDaoImpl() {
		return computerDaoImpl;
	}
	
	/**
	 * Gets the company dao impl.
	 *
	 * @return the company dao impl
	 */
	public static CompanyDaoImpl getCompanyDaoImpl() {
		return companyDaoImpl;
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(properties.getProperty(PROPERTY_URL), properties.getProperty(PROPERTY_NOM_UTILISATEUR), properties.getProperty(PROPERTY_MOT_DE_PASSE));
	}
    
	/**
	 * Close.
	 *
	 * @param conn the conn
	 * @param rs the rs
	 * @param stmt the stmt
	 * @param pstmt the pstmt
	 */
	public static void close(Connection conn, ResultSet rs, Statement stmt, PreparedStatement pstmt ) { 
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				String message = "Le resultset n'a pas pu être fermé";
				System.err.println(message);
				throw new DAOException(message);
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				String message = "Le statement n'a pas pu être fermé";
				System.err.println(message);
				throw new DAOException(message);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				String message = "Le prepared statement n'a pas pu être fermé";
				System.err.println(message);
				throw new DAOException(message);
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				String message = "La connexion statement n'a pas pu être fermé";
				System.err.println(message);
				throw new DAOException(message);
			}
		}
	}
}
