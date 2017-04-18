package com.froehli.estatemanager.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.froehli.estatemanager.Estate;
import com.froehli.estatemanager.EstateAgent;

public class UtilsEstate
{
	public static void createEstate( Estate estate, Connection connection ) {
		
		String sql = "INSERT INTO ESTATE(ID,CITY,POSTAL_CODE,STREET,STREET_NUMBER,SQUARE_AREA,ESTATE_AGENT) VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setInt( 1, estate.getID() );
			preparedStatement.setString( 2, estate.getCity() );
			preparedStatement.setInt( 3, estate.getPostalCode() );
			preparedStatement.setString( 4, estate.getStreet() );
			preparedStatement.setString( 5, estate.getStreetNumber() );
			preparedStatement.setInt( 6, estate.getSquareArea() );
			preparedStatement.setString( 7, estate.getEstateAgent() );
			preparedStatement.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	public static void deleteEstate( int estateId, Connection connection ){
		int id = estateId;
		String sql = "DELETE FROM ESTATE WHERE ID=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setInt(1, id );
			preparedStatement.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		
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

}
