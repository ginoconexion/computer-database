package com.formation.computerdatabase.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DAOFactory {
	private static final String FICHIER_PROPERTIES       = "dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "utilisateur";
    private static final String PROPERTY_MOT_DE_PASSE    = "password";
    private Properties properties;
	
	// méthode déclarée statique car sinon nous devons l'appeler depuis une instance alors que ce n'sst pas le but
	// permet d'éviter la création d'un nouvel objet à chaque appel
	public DAOFactory() {
		properties = new Properties();
		
		try {
			properties.load(DAOFactory.class.getClassLoader().getResourceAsStream(FICHIER_PROPERTIES));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// chargement du driver jdbc
		try {
			Class.forName(properties.getProperty(PROPERTY_DRIVER));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(properties.getProperty(PROPERTY_URL), properties.getProperty(PROPERTY_NOM_UTILISATEUR), properties.getProperty(PROPERTY_MOT_DE_PASSE));
	}
    
	public static void close(Connection conn, ResultSet rs, Statement stmt, PreparedStatement pstmt ){
		try {
			if (conn != null){
				conn.close();
			} 
			if (rs != null){
				rs.close();
			}
			if (stmt != null){
				stmt.close();
			}
			if (pstmt != null){
				pstmt.close();
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
