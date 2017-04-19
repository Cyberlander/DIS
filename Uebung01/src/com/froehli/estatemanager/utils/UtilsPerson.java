package com.froehli.estatemanager.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.froehli.estatemanager.EstateAgent;
import com.froehli.estatemanager.Person;

public class UtilsPerson
{
	public static void createPerson( Person person , Connection connection  ) {
		String sql = "INSERT INTO PERSON(ID,FIRST_NAME,NAME,ADDRESS) VALUES (?,?,?,?)";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setInt(1, person.getID() );
			preparedStatement.setString(2, person.getFirstName()  );
			preparedStatement.setString(3, person.getName() );
			preparedStatement.setString(4, person.getAddress() );
			preparedStatement.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

}
