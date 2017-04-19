package com.froehli.estatemanager;

public class Person
{
	int _id;
	String _firstName;
	String _name;
	String _address;
	
	public Person( int id, String firstName, String name, String address ) {
		this._id = id;
		this._firstName = firstName;
		this._name = name;
		this._address = address;
	}
	
	public int getID() {
		return this._id;
	}
	
	public String getFirstName() {
		return this._firstName;
	}
	
	public String getName() {
		return this._name;
	}

	public String getAddress() {
		return this._address;
	}
}
