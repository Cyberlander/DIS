package com.exercise;

import java.util.*;
import java.io.*;

public class CSVReader
{
	
	public static List<CSVEntry> loadCSVFile( String path ){
		List<CSVEntry> entries = new ArrayList<>();
		String seperator = ";";
		int lineNumber = 0;
		
		BufferedReader br = null;
		try {
			br = new BufferedReader( new InputStreamReader( new FileInputStream( path ) ) );
			String headLine = br.readLine();
			lineNumber ++;
			
			String line;
			while ( ( line = br.readLine() ) != null ){
				lineNumber++;
				// TODO System.out.println( line );
				// line 35906 is buggy
				if ( lineNumber == 35906 ){
					line = br.readLine();
				}
				String[] entryParts = line.split( seperator );
				CSVEntry entry = new CSVEntry( entryParts[0], entryParts[1], entryParts[2],
						Integer.parseInt(entryParts[3]),Double.parseDouble(entryParts[4].replace(",", ".")) );
				entries.add( entry );
			}
			
		} catch ( IOException e ){
			
		} finally {
			
			try {
				if ( null != br ){
					br.close();
				}
				
			} catch ( IOException ex ){
				
			}
		}
		
		return entries;
		
	}

}
