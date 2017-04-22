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
	
	public static void updateEstate( int id, String city, int postalCode, 
			String street, String streetNo, int squareArea, String estateAgent, Connection connection ) {
		String sql = "UPDATE ESTATE SET CITY=?, POSTAL_CODE=?,  STREET=?, STREET_NUMBER=?, SQUARE_AREA=?, ESTATE_AGENT=? WHERE ID=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setString( 1, city );
			preparedStatement.setInt( 2, postalCode );
			preparedStatement.setString( 3, street );
			preparedStatement.setString( 4,  streetNo );
			preparedStatement.setInt( 5, squareArea);
			preparedStatement.setString( 6, estateAgent );	
			preparedStatement.setInt( 7, id );
			preparedStatement.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}		
	
	}
	

}
