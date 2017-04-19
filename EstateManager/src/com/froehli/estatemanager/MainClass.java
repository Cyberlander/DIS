package com.froehli.estatemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import com.froehli.estatemanager.utils.UtilsContract;
import com.froehli.estatemanager.utils.UtilsEstate;
import com.froehli.estatemanager.utils.UtilsEstateAgent;
import com.froehli.estatemanager.utils.UtilsPerson;

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
			
			//UtilsEstate.updateEstate( 1, "Hamburg", 54321, "Examplestreet", "5", 100, "bla", connection  );
			//UtilsPerson.createPerson( new Person(1, "Froehli", "Felix", "Examplestreet"), connection );
			//UtilsContract.createContract(new Contract( 1, "19.04.2017", "Hamburg"), connection);
			//Estate e = new Estate( 1, "Hamburg", 12345, "Examplestreet", "5", 100, "bla");
			//UtilsEstate.createEstate( e, connection );
			//UtilsEstate.deleteEstate(2, connection);
			List<Contract> list = UtilsContract.getContracts(connection);
			System.out.println( list.get(0).getDate());
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
