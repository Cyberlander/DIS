package com.felixsoenke;

public class ClientSimulator
{
	public static void start() {
		
		Thread client1 = new Thread( new Client( "Client 1") );
		try
		{
			client1.sleep(1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		client1.start();
	}

}
