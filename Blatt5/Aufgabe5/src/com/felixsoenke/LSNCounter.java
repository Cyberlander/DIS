package com.felixsoenke;

public class LSNCounter
{
	private static int counter = 0;
	
	public static synchronized void increment() {
		counter++;
	}
	
	public static synchronized void decrement() {
		counter--;
	}
	
	public static synchronized int getCounter() {
		return counter;
	}
	
	public static void setValue( int value ){
		counter = value;
	}
	
	
}
