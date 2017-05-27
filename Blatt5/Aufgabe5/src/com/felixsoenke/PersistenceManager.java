package com.felixsoenke;

import java.util.concurrent.ConcurrentHashMap;
import java.io.*;
import java.util.Map.Entry;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import com.sun.javafx.collections.MappingChange.Map;

public class PersistenceManager
{
	static final private PersistenceManager persistenceManager;
	static final private ConcurrentHashMap<Integer, Page> buffer;
	
	static {
		
		try {
			persistenceManager = new PersistenceManager();
			buffer = new ConcurrentHashMap<>();
			Database.prepareStructure();
			makeConsistencyCheck();
			
			
			
		} catch ( Throwable e ){
			throw new RuntimeException( e.getMessage() );
		}
		synchronizeLSNCounter();
		
	}
	
	private PersistenceManager() {
		
	}
	
	static public PersistenceManager getInstance() {
		return persistenceManager;
	}
	
	
	
	public static void synchronizeLSNCounter(){
		BufferedReader br = null;
		int lsn;
		int taid;
		try {
			br = new BufferedReader( new InputStreamReader( new FileInputStream( "log/logfile.txt" ) ) );
			
			String firstLine = br.readLine();
			if ( firstLine == null ){
				LSNCounter.setValue( 0 );
				TransactionIdCounter.setValue( 0 );
			} else {
				String line = null;
				String lastLine = firstLine;
				while ( ( line = br.readLine() ) != null){
					lastLine = line;
				}
				String[] entries = lastLine.split( "," );
				lsn = Integer.parseInt( entries[0] );
				taid = Integer.parseInt( entries[1] );
				LSNCounter.setValue( lsn );
				TransactionIdCounter.setValue( taid );
			}			
			
			System.out.println( "[DEBUG] Last LSN : " + LSNCounter.getCounter() );
			System.out.println( "[DEBUG] Last TaID: " + TransactionIdCounter.getCounter() );
			
		} catch ( IOException e ){
			e.printStackTrace();
		}
	}
	
	
	
	
	public int beginTransaction() {
		TransactionIdCounter.increment();
		int tid = TransactionIdCounter.getCounter();		
		// BOT
		createLogEntry( tid, 0, "BOT" );
		return tid;	
	}
	
	public void commit( int taid ){
		
		//write to logData
		createLogEntry( taid, 0, "COMMIT" );
	}
	
	public void write( int taid, int pageid, String data ){
		Page page = loadPageFromDatabaseInBuffer( taid, pageid );
		
		//getInstance().listBufferContent();
		String currentData = page.getData();
		System.out.println("Current page data: " + currentData );
		String newData = data;
		createLogEntry( taid, page.getPageID(), newData );
		
	} 
	
	public static void createLogEntry( int taid,int PageID,String Redo ) {
		LSNCounter.increment();
		int lsn = LSNCounter.getCounter();
		String path = "log/logfile.txt";
		String entry = "" + lsn + "," + taid + "," + PageID + "," + Redo;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		File file = new File( path );
		try
		{	
			bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( file, true )));
			pw = new PrintWriter( bw );
			//pw.println();
			pw.println( entry );
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally {
			try {
				if ( null != bw ) {
					bw.close();
				}
				if ( null != pw ){
					pw.close();
				}
				
			} catch ( IOException e ){
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static Page loadPageFromDatabaseInBuffer(int taid, int pageid ){
		Page page = Database.getPageForId( pageid );
		buffer.put( taid, page);
		return page;
		
	}
	
	public static void checkForFullBuffer() {
		if ( buffer.size() > 5 ) {
			
		}
	}
	
	public static void makeConsistencyCheck() {
		System.out.println( "[Persistence Manager] make consistency check");
		List<LogEntry> logEntries = getAllLogEntries();
		for ( LogEntry le : logEntries ){
			
		}
	}
	
	public static List<LogEntry> getAllLogEntries(){
		List<LogEntry> entries = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader( new InputStreamReader( new FileInputStream( "log/logfile.txt" ) ) ); 	
			String line;
			while ( ( line = br.readLine() ) != null ){
				String[] pieces = line.split( "," );
				LogEntry le = new LogEntry( Integer.parseInt(pieces[0]), Integer.parseInt(pieces[1]), Integer.parseInt(pieces[2]), pieces[3]);
				entries.add( le );
			}
		} catch ( IOException e ){
			e.printStackTrace();
		} finally {
			try {
				if ( null != br ){
					br.close();
				}
			} catch ( IOException e ){
				
			}
		}
		
		return entries;
	}
	
	
	public static void writeCommitedTransactionsToDatabase() {
		
	}
	
	public void listBufferContent() {
		System.out.println( "SIZE: " + buffer.size());
		
		for ( int i = 0; i < buffer.size(); i ++  ) {
			Page p = buffer.get(i);
			System.out.println( "[BUFFER] " + "Page-Id: " +  p.getPageID());
		}
	}
	

}
