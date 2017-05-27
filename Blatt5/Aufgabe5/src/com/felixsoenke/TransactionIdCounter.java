package com.felixsoenke;

public class TransactionIdCounter
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
	
	public static synchronized void setValue( int value ) {
		counter = value;
	}
	
	
	
}
