package com.felixsoenke;

import java.util.concurrent.ConcurrentHashMap;
import java.io.*;

public class PersistenceManager
{
	static final private PersistenceManager persistenceManager;
	static final private ConcurrentHashMap<String, String> buffer;
	
	static {
		
		try {
			persistenceManager = new PersistenceManager();
			buffer = new ConcurrentHashMap<>();
			Database.prepareStructure();
			
			
		} catch ( Throwable e ){
			throw new RuntimeException( e.getMessage() );
		}
		
	}
	
	private PersistenceManager() {
		
	}
	
	static public PersistenceManager getInstance() {
		return persistenceManager;
	}
	
	
	
	public static void getLSNCounter(){
		
	}
	
	public static int beginTransaction() {
		TransactionIdCounter.increment();
		int tid = TransactionIdCounter.getCounter();
		return tid;		
	}
	
	public static void commit( int taid ){
		
		//write to logData
	}
	
	public static void write( int taid, int pageid, String data ){
		
	}
	
	public static void createLogEntry( int lsn, int taid,int PageID,String Redo ) {
		String path = "/logfile/log.txt";
		
		File file = new File( path );
		try
		{
			RandomAccessFile raf = new RandomAccessFile( file, "rw");
			long fileLength = file.length();
			raf.seek(fileLength);
			raf.writeByte( lsn );
			raf.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void loadPageFromDatabaseInBuffer( int pageid ){
		Page page = Database.getPageForId( pageid );
		
	}
	
	public static void checkForFullBuffer() {
		if ( buffer.size() > 5 ) {
			
		}
	}
	
	
	public static void writeCommitedTransactionsToDatabase() {
		
	}
	

}
