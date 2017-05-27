package com.felixsoenke;

public class ClientSimulator
{
	public static void start() {
		
		int numOfThreads = 5;
		Thread[] threads = new Thread[numOfThreads];
		
		for ( int i = 0; i < numOfThreads; i ++ ){
			int threadNo = i + 1;
			int startPage = (threadNo * 2) - 1;
			int secondPage = threadNo * 2;
			
			String name = "Client " + i;
			threads[i] = new Thread( new Client( name, startPage, secondPage ) );
			threads[i].start();
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
		
		
		
	}

}
