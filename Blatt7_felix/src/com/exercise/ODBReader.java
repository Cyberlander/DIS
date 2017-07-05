package com.exercise;

import java.sql.*;
import com.exercise.tables.*;

public class ODBReader
{
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName( "com.ibm.db2.jcc.DB2Driver" );
		} catch ( ClassNotFoundException e ){
			e.printStackTrace();
		}
		try { 
		connection = DriverManager.getConnection( "jdbc:db2://vsisls4.informatik.uni-hamburg.de:50001/VSISP",
				"",
				"");
		} catch ( SQLException ex ){
			ex.printStackTrace();
		}
		
		return connection;
	}

	
	public static ArticleIdTable getArticleIdTable( Connection connection ){
		Statement statement = null;
		String sql = "SELECT * FROM DB2INST1.ARTICLEID";
		ArticleIdTable table = new ArticleIdTable();
		
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery( sql );
			
			while ( resultSet.next() ){
				
				int id = resultSet.getInt( "ARTICLEID" );
				int group = resultSet.getInt( "PRODUCTGROUPID" );
				String name = resultSet.getString( "NAME" );
				double preis = resultSet.getDouble( "PREIS" );
				// System.out.println( "Article-Id:" + id + " Product-Group: " + group + " Name: " + name + " Preis: " + preis);
				ArticleIdTable.Entry entry = table.new Entry( id, group, name, preis );
				table.addEntry( entry );
			}
			
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		
		/*
		for ( ArticleIdTable.Entry e : table.getEntryList() ) {
			System.out.println( e.getName() );
		}
		*/
		return table;
	}
	
	
	
	public static ProductGroupIdTable getProductGroupIdTable( Connection connection ){
		Statement statement = null;
		String sql = "SELECT * FROM DB2INST1.PRODUCTGROUPID";
		ProductGroupIdTable table = new ProductGroupIdTable();
		
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery( sql );
			
			while ( resultSet.next() ){
				
				int id = resultSet.getInt( "PRODUCTGROUPID" );
				int family = resultSet.getInt( "PRODUCTFAMILYID" );
				String name = resultSet.getString( "NAME" );
				
				// System.out.println( "Article-Id:" + id + " Product-Group: " + group + " Name: " + name + " Preis: " + preis);
				ProductGroupIdTable.Entry entry = table.new Entry( id, family, name );
				table.addEntry( entry );
			}
			
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		
		/*
		for ( ProductGroupIdTable.Entry e : table.getEntryList() ) {
			System.out.println( e.getName() );
		}
		*/
		return table;
	}
	
	
	
	
	
	public static ProductFamilyIdTable getProductFamilyIdTable( Connection connection ){
		Statement statement = null;
		String sql = "SELECT * FROM DB2INST1.PRODUCTFAMILYID";
		ProductFamilyIdTable table = new ProductFamilyIdTable();
		
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery( sql );
			
			while ( resultSet.next() ){
				
				int id = resultSet.getInt( "PRODUCTFAMILYID" );
				int category = resultSet.getInt( "PRODUCTCATEGORYID" );
				String name = resultSet.getString( "NAME" );
				
				// System.out.println( "Article-Id:" + id + " Product-Group: " + group + " Name: " + name + " Preis: " + preis);
				ProductFamilyIdTable.Entry entry = table.new Entry( id, category, name );
				table.addEntry( entry );
			}
			
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		
		/*
		for ( ProductFamilyIdTable.Entry e : table.getEntryList() ) {
			System.out.println( e.getName() );
		}
		*/
		return table;
	}
	
	
	
	
	
	
	
	
	
	
	
	public static ProductCategoryIdTable getProductCategoryIdTable( Connection connection ){
		Statement statement = null;
		String sql = "SELECT * FROM DB2INST1.PRODUCTCATEGORYID";
		ProductCategoryIdTable table = new ProductCategoryIdTable();
		
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery( sql );
			
			while ( resultSet.next() ){
				
				int id = resultSet.getInt( "PRODUCTCATEGORYID" );
				String name = resultSet.getString( "NAME" );
				
				// System.out.println( "Article-Id:" + id + " Product-Group: " + group + " Name: " + name + " Preis: " + preis);
				ProductCategoryIdTable.Entry entry = table.new Entry( id, name );
				table.addEntry( entry );
			}
			
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		
		/*
		for ( ProductCategoryIdTable.Entry e : table.getEntryList() ) {
			System.out.println( e.getName() );
		}
		*/
		return table;
	}
	
	
	
	
	
	
	
	public static ShopIdTable getShopIdTable( Connection connection ){
		Statement statement = null;
		String sql = "SELECT * FROM DB2INST1.SHOPID";
		ShopIdTable table = new ShopIdTable();
		
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery( sql );
			
			while ( resultSet.next() ){
				
				int id = resultSet.getInt( "SHOPID" );
				int stadtId = resultSet.getInt( "STADTID" );
				String name = resultSet.getString( "NAME" );
				
				// System.out.println( "Article-Id:" + id + " Product-Group: " + group + " Name: " + name + " Preis: " + preis);
				ShopIdTable.Entry entry = table.new Entry( id, stadtId, name );
				table.addEntry( entry );
			}
			
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		return table;
	}
	
	
	
	
	
	
	public static StadtIdTable getStadtIdTable( Connection connection ){
		Statement statement = null;
		String sql = "SELECT * FROM DB2INST1.STADTID";
		StadtIdTable table = new StadtIdTable();
		
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery( sql );
			
			while ( resultSet.next() ){
				
				int id = resultSet.getInt( "STADTID" );
				int regionId = resultSet.getInt( "REGIONID" );
				String name = resultSet.getString( "NAME" );
				
				// System.out.println( "Article-Id:" + id + " Product-Group: " + group + " Name: " + name + " Preis: " + preis);
				StadtIdTable.Entry entry = table.new Entry( id, regionId, name );
				table.addEntry( entry );
			}
			
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		
		return table;
	}
	
	
	
	
	
	
	
	
	public static RegionIdTable getRegionIdTable( Connection connection ){
		Statement statement = null;
		String sql = "SELECT * FROM DB2INST1.REGIONID";
		RegionIdTable table = new RegionIdTable();
		
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery( sql );
			
			while ( resultSet.next() ){
				
				int id = resultSet.getInt( "REGIONID" );
				int regionId = resultSet.getInt( "LANDID" );
				String name = resultSet.getString( "NAME" );
				
				// System.out.println( "Article-Id:" + id + " Product-Group: " + group + " Name: " + name + " Preis: " + preis);
				RegionIdTable.Entry entry = table.new Entry( id, regionId, name );
				table.addEntry( entry );
			}
			
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		
		/*
		for ( RegionIdTable.Entry e : table.getEntryList() ) {
			System.out.println( e.getName() );
		}
		*/
		return table;
	}
	
	
	
	
	
	
	
	

	
	public static LandIdTable getLandIdTable( Connection connection ){
		Statement statement = null;
		String sql = "SELECT * FROM DB2INST1.LANDID";
		LandIdTable table = new LandIdTable();
		
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery( sql );
			
			while ( resultSet.next() ){
				
				int id = resultSet.getInt( "LANDID" );
				String name = resultSet.getString( "NAME" );
				
				// System.out.println( "Article-Id:" + id + " Product-Group: " + group + " Name: " + name + " Preis: " + preis);
				LandIdTable.Entry entry = table.new Entry( id, name );
				table.addEntry( entry );
			}
			
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		
		
		for ( LandIdTable.Entry e : table.getEntryList() ) {
			System.out.println( e.getName() );
		}
		
		return table;
	}
}
