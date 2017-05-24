package com.felixsoenke;

public class Client implements Runnable
{
	String _name;
	PersistenceManager pm;
	
	public Client( String name ){
		this._name = name;
	}

	@Override
	public void run()
	{
		talking( "started" );
		pm = PersistenceManager.getInstance();
		int taid = pm.beginTransaction();
		
		
	}
	
	public void talking( String talk ){
		System.out.println( "[" + _name + "]: " + talk);
	}
	
	public void startTransaction(){
		
	}

}
