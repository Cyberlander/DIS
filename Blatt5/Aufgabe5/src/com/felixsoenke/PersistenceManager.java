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
		try {
			br = new BufferedReader( new InputStreamReader( new FileInputStream( "log/logfile.txt" ) ) );
			
			String firstLine = br.readLine();
			if ( firstLine == null ){
				LSNCounter.setValue( 0 );
			} else {
				String line = null;
				String lastLine = firstLine;
				while ( ( line = br.readLine() ) != null){
					lastLine = line;
				}
				String[] entries = lastLine.split( "," );
				lsn = Integer.parseInt( entries[0] );			
				LSNCounter.setValue( lsn );
			}			
			
			System.out.println( "Last LSN: " + LSNCounter.getCounter());
			
		} catch ( IOException e ){
			e.printStackTrace();
		}
	}
	
	
	public static int beginTransaction() {
		TransactionIdCounter.increment();
		int tid = TransactionIdCounter.getCounter();
		LSNCounter.increment();
		// BOT
		createLogEntry( LSNCounter.getCounter(), tid, 0, 0 );
		return tid;	
	}
	
	public static void commit( int taid ){
		
		//write to logData
	}
	
	public static void write( int taid, int pageid, String data ){
		
	}
	
	public static void createLogEntry( int lsn, int taid,int PageID,int Redo ) {
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
