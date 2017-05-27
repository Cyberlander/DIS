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
		sleep( 1000 );
		writing( taid, 5, "8" );
		commit( taid );
		
	}
	
	public void writing( int taid, int page, String data ){
		talking( "writing with TaID " + taid + " in page " +page + " value " + data );
		pm.write(taid, page, data);
		sleep( 1000 );
	}
	
	public void talking( String talk ){
		System.out.println( "[" + _name + "]: " + talk);
	}
	
	public void commit(int taid ){
		talking( "Transaction " + taid + " commited." );
		pm.commit( taid );
	}
	
	
	public void startTransaction(){
		
	}
	
	public void sleep( int millis ){

		try
		{
			Thread.sleep( millis );
		} catch ( InterruptedException e )
		{
			e.printStackTrace();
		}
	}

}
