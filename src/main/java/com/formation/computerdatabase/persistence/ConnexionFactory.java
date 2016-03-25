package com.formation.computerdatabase.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.persistence.impl.ComputerDaoImpl;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;


/**
 * A factory for creating Connexion objects.
 */
public enum ConnexionFactory {
	
	/** The instance. */
	INSTANCE;
	
	private static ThreadLocal<Connection> context = new ThreadLocal<>();
	
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
    BoneCP connectionPool;
	
    /**
     * Constructeur privé.
     */
	private ConnexionFactory() {
		
		properties = new Properties();
		connectionPool = null;
		
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
		
		
		
		try {
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(properties.getProperty(PROPERTY_URL));
			config.setUsername(properties.getProperty(PROPERTY_NOM_UTILISATEUR));
			config.setPassword(properties.getProperty(PROPERTY_MOT_DE_PASSE));
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(15);
			config.setPartitionCount(2);
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			String message = "Erreur de configuration du pool de connexion";
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
	public Connection getConnection() throws SQLException {
		Connection connexion = context.get();
		// la connexion ne sera pas nulle uniquement dans le cas de transcation
		if (connexion != null) {
			return connexion;
		}
		// dans l'autre cas on en instancie une nouvelle
		else {
			return connectionPool.getConnection();
		}
		
	}
	/**
	 * Beginning of a Transaction, enable to set in the context a connexion with autocommit set to false
	 * @throws SQLException
	 */
	public static void initTransaction() throws SQLException {
		Connection connexion = ConnexionFactory.INSTANCE.getConnection();
		connexion.setAutoCommit(false);
		context.set(connexion);
	}
	
	/**
	 * Enables to close and to remove connexion from context
	 * @throws SQLException 
	 */
	public static void closeTransaction() throws SQLException {
		Connection connexion = context.get();
		connexion.close();
		context.remove();
	}
	
	public static void commit() throws SQLException {
		context.get().commit();
	}
	
	public static void rollback() throws SQLException {
		context.get().rollback();
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
