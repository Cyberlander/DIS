package com.froehli.estatemanager;

public class EstateAgent
{
	String _name;
	String _address;
	String _login;
	String _password;
	
	public EstateAgent() {
		
	}
	
	public EstateAgent( String name, String address, String login, String password ) {
		this._name = name;
		this._address = address;
		this._login = login;
		this._password = password;
	}
	
	public void setName( String name ){
		this._name = name;
	}
	
	public void setAddress( String address ){
		this._address = address;
	}
	
	public void setLogin( String login ){
		this._login = login;
	}
	
	public void setPassword( String password ){
		this._password = password;
	}
	
	public String getName(){
		return this._name;
	}
	
	public String getAddress(){
		return this._address;
	}
	
	public String getLogin(){
		return this._login;
	}
	
	public String getPassword(){
		return this._password;
	}
	
	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("ESTATE AGENT\n");
		stringBuilder.append("Name:     ");
		stringBuilder.append( getName() );
		stringBuilder.append( "\n");
		stringBuilder.append("Address:  ");
		stringBuilder.append( getAddress() );
		stringBuilder.append( "\n");
		stringBuilder.append("Login:    ");
		stringBuilder.append( getLogin() );
		stringBuilder.append( "\n");
		stringBuilder.append("Password: ");
		stringBuilder.append( getPassword() );
		stringBuilder.append( "\n");
		return stringBuilder.toString();
	}

}
