package com.felixsoenke;

import java.util.concurrent.ThreadLocalRandom;

public class Client implements Runnable
{
	String _name;
	int _page1;
	int _page2;
	PersistenceManager pm;
	
	public Client( String name,int page1,int page2 ){
		this._name = name;
		this._page1 = page1;
		this._page2 = page2;
	}

	@Override
	public void run()
	{
		talking( "started" );
		pm = PersistenceManager.getInstance();
		
		int taid = pm.beginTransaction();
		
		String randomNum = "" +  ThreadLocalRandom.current().nextInt( 0, 10 );
		
		writing( taid, _page1, randomNum );
		sleep( 5000 );
		writing( taid, _page2, randomNum );
		sleep( Integer.parseInt( randomNum ) * 10 );
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
