package com.froehli.estatemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import com.froehli.estatemanager.utils.UtilsEstateAgent;

public class MainClass
{
	
	
	
	public static void main( String[] args ) {
		
		MainClass mainClass = new MainClass();
		try
		{
			Class.forName( "com.ibm.db2.jcc.DB2Driver" );
			Connection connection = mainClass.getConnection();
			
			//EstateAgent bla = new EstateAgent("bla", "bla", "bla", "bla");
			//UtilsEstateAgent.createEstateAgent(bla, connection);
			//List<EstateAgent> estateAgents = UtilsEstateAgent.getEstateAgents( connection );
			//System.out.println(estateAgents.get(1).toString());
			
			UtilsEstateAgent.updateEstateAgent( "bla", "foo", "bar", "bar",connection  );
			
			
			
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		
		
		
	}
	
	public Connection getConnection() {
		Connection connection;
		try {
			connection = DriverManager.getConnection( Constants.DB_URL, Constants.DB_USERNAME, Constants.DB_PASSWORD );
			return connection;
		} catch ( SQLException e ) {
			
		}
		return null;
		
	}
	
	
	
	
	

}
