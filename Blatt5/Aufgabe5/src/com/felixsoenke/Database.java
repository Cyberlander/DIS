package com.felixsoenke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Database
{
	public static void prepareStructure() {	
		prepareDirectoryStructure();
		preparePageFiles();	
		prepareLogFile();
	}
	
	private static void prepareDirectoryStructure() {		
		File pageDirectory = new File( "pages" );
		File logDirectory = new File( "log" );
		
		if ( !pageDirectory.exists() ) {
			pageDirectory.mkdir();
		}
		if ( !logDirectory.exists() ){
			logDirectory.mkdir();
		}
	}
	
	private static void prepareLogFile() {
		File logFile = new File( "log/logfile.txt" );
		BufferedReader logFileBr = null;
		BufferedWriter logFileBw = null;
		
		if ( !logFile.exists() ){
			try
			{
				logFile.createNewFile();
				logFileBr = new BufferedReader( new InputStreamReader( new FileInputStream( logFile )));
				logFileBw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( logFile )));
				
			} catch ( IOException e ){
				e.printStackTrace();
			} finally {
				try {
					if ( logFileBr != null ){
					logFileBr.close();
					}	
					if ( logFileBw != null ){
						logFileBw.close();
						}
				} catch ( IOException e ){
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public static void preparePageFiles() {
		File[] pages = new File[10];
		BufferedReader pagesBr = null;
		BufferedWriter pagesBw = null;
		for ( int i=0; i<pages.length; i++ ){
			int pagecount = i + 1;
			String filePath = "pages/" + pagecount + ".txt";
			pages[i] = new File( filePath );
		}
		try {			
			for ( int i=0; i<pages.length; i++ ){
				File f = pages[i];
				if ( !f.exists() ){
					f.createNewFile();
					pagesBr = new BufferedReader( new InputStreamReader( new FileInputStream( f )));
					pagesBw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( f )));
					
					String firstLine = pagesBr.readLine();
					
					if ( firstLine == null ){
						int id = i + 1;
						String firstEntry = "" + id +",00,0";
						pagesBw.write( firstEntry );
						if ( null != pagesBw ){
							pagesBw.close();
						}
					}
				}
			}
		}
		catch ( IOException e ){
			e.printStackTrace();
		} finally {
			
			try {
				if ( null != pagesBr ){
					pagesBr.close();
				}
				
			} catch ( IOException e ){
				e.printStackTrace();
			}
		}
	}
	
	public static Page getPageForId(int pageId ) {
		Page page = null;
		BufferedReader br = null;
		String path = "pages/" + pageId + ".txt";
		String pageEntry = null;
		try {
			br = new BufferedReader( new InputStreamReader( new FileInputStream( path )));
			pageEntry = br.readLine();
			if ( pageEntry != null ){
				String[] entries = pageEntry.split( "," );
				if ( entries.length == 3 ){
					page = new Page();
					page.setPageID( Integer.parseInt(entries[0]));
					page.setLSN( Integer.parseInt( entries[1]));
					page.setData(  entries[2] );
				}
				
			}
		} catch ( IOException e ){
		}
		finally {
			try{
				if ( null != br) {
					br.close();
				}
			} catch( IOException e ){
				e.printStackTrace();
			}
		}
		return page;
	}
}
