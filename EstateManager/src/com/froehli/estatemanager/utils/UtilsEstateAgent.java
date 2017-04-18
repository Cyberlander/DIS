package com.froehli.estatemanager.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.froehli.estatemanager.EstateAgent;

public class UtilsEstateAgent
{
	public static  List<EstateAgent> getEstateAgents( Connection connection ) throws SQLException {
		List<EstateAgent> list = new ArrayList<>();
		String sql = "SELECT * FROM ESTATE_AGENT";
		
		//Connection connection = DriverManager.getConnection( Constants.DB_URL, Constants.DB_USERNAME, Constants.DB_PASSWORD );
		Statement statement = connection.createStatement();
		ResultSet r = statement.executeQuery(sql);
		while (r.next()) {
			EstateAgent estateAgent = new EstateAgent( 
					r.getString( "NAME" ), 
					r.getString( "ADDRESS" ), 
					r.getString( "LOGIN" ),
					r.getString( "PASSWORD" ));
			list.add( estateAgent );		
		}
		return list;
	}
	
	public static void createEstateAgent( EstateAgent estateAgent, Connection connection  ) {
		String sql = "INSERT INTO ESTATE_AGENT(NAME,ADDRESS,LOGIN,PASSWORD) VALUES (?,?,?,?)";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setString(1, estateAgent.getName() );
			preparedStatement.setString(2, estateAgent.getAddress() );
			preparedStatement.setString(3, estateAgent.getLogin() );
			preparedStatement.setString(4, estateAgent.getPassword() );
			preparedStatement.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static boolean deleteEstateAgent( EstateAgent estateAgent, Connection connection ) {
		String name = estateAgent.getName();
		String sql = "DELETE FROM ESTATE_AGENT WHERE NAME='" + name + "';";
		boolean successful = false;
		try {
			Statement statement = connection.createStatement();
			successful = statement.execute( sql );
		} catch ( SQLException e ) {
			
		}		
		return successful;
	
	}
	

	public static void deleteEstateAgent( String estateAgent, Connection connection ) {
		String name = estateAgent;
		
		String sql = "DELETE FROM ESTATE_AGENT WHERE NAME=?";
		boolean successful;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setString( 1, name );
			preparedStatement.executeUpdate();


		} catch ( SQLException e ) {
			e.printStackTrace();
		}			
	}
	
	public static void updateEstateAgent( String estateAgent,String address, 
			String login, String password, Connection connection ) {
		String sql = "UPDATE ESTATE_AGENT SET ADDRESS=?, LOGIN=?, PASSWORD=? WHERE NAME=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setString(1, address );
			preparedStatement.setString(2, login );
			preparedStatement.setString(3, password );
			preparedStatement.setString(4, estateAgent );
			preparedStatement.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}		
	
	}
	
	

}
