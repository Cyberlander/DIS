package com.froehli.estatemanager.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.froehli.estatemanager.Contract;
import com.froehli.estatemanager.EstateAgent;

public class UtilsContract
{
	public static void createContract( Contract contract , Connection connection  ) {
		String sql = "INSERT INTO CONTRACT(CONTRACT_NO,DATE_,PLACE) VALUES (?,?,?)";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setInt(1, contract.getContractNo() );
			preparedStatement.setString(2, contract.getDate()  );
			preparedStatement.setString(3, contract.getPlace() );
			preparedStatement.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static  List<Contract> getContracts( Connection connection ) {
		List<Contract> list = new ArrayList<>();
		String sql = "SELECT * FROM CONTRACT";
		
		//Connection connection = DriverManager.getConnection( Constants.DB_URL, Constants.DB_USERNAME, Constants.DB_PASSWORD );
		try {
			Statement statement = connection.createStatement();
			ResultSet r = statement.executeQuery(sql);
			while (r.next()) {
				Contract contract = new Contract( 
						r.getInt( "CONTRACT_NO" ), 
						r.getString( "DATE_" ), 
						r.getString( "PLACE" ));
				list.add( contract );		
				} 
		} catch ( SQLException e ) {
			e.printStackTrace();	
		}
		return list;
	}

}
