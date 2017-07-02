package com.exercise;

import java.sql.*;
import java.util.*;
import com.exercise.tables.*;

public class DataWarehouseWriter
{
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection( "",
					"",
					"");
			
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		return connection;
		
	}
	
	public static void createTables( Connection connection ){
		String sqlCreateTableArticleId = "CREATE TABLE ARTICLEID( "
				+ "ARTICLEID INTEGER NOT NULL,"
				+ "PRODUCTGROUPID INTEGER NOT NULL,"
				+ "NAME VARCHAR(255) NOT NULL, PREIS DOUBLE NOT NULL, PRIMARY KEY(ARTICLEID))";
		
		String sqlCreateTableProductGroupId = "CREATE TABLE PRODUCTGROUPID( "
				+ "PRODUCTGROUPID INTEGER NOT NULL,"
				+ "PRODUCTFAMILYID INTEGER NOT NULL,"
				+ "NAME VARCHAR(255) NOT NULL, PRIMARY KEY(PRODUCTGROUPID))";
		
		String sqlCreateTableProductFamilyId = "CREATE TABLE PRODUCTFAMILYID( "
				+ "PRODUCTFAMILYID INTEGER NOT NULL,"
				+ "PRODUCTCATEGORYID INTEGER NOT NULL,"
				+ "NAME VARCHAR(255) NOT NULL, PRIMARY KEY(PRODUCTFAMILYID))";
		
		String sqlCreateTableProductCategoryId = "CREATE TABLE PRODUCTCATEGORYID( "
				+ "PRODUCTCATEGORYID INTEGER NOT NULL,"
				+ "NAME VARCHAR(255) NOT NULL, PRIMARY KEY(PRODUCTCATEGORYID))";
		

		String sqlCreateTableShopId = "CREATE TABLE SHOPID( "
				+ "SHOPID INTEGER NOT NULL,"
				+ "STADTID INTEGER NOT NULL,"
				+ "NAME VARCHAR(255) NOT NULL, PRIMARY KEY(SHOPID))";
		
		String sqlCreateTableStadtId = "CREATE TABLE STADTID( "
				+ "STADTID INTEGER NOT NULL,"
				+ "REGIONID INTEGER NOT NULL,"
				+ "NAME VARCHAR(255) NOT NULL, PRIMARY KEY(STADTID))";
		
		String sqlCreateTableRegionId = "CREATE TABLE REGIONID( "
				+ "REGIONID INTEGER NOT NULL,"
				+ "LANDID INTEGER NOT NULL,"
				+ "NAME VARCHAR(255) NOT NULL, PRIMARY KEY(REGIONID))";
		
		String sqlCreateTableLandId = "CREATE TABLE LANDID( "
				+ "LANDID INTEGER NOT NULL,"
				+ "NAME VARCHAR(255) NOT NULL, PRIMARY KEY(LANDID))";
		
		String sqlCreateTableTranscactions =" CREATE TABLE TRANSACTIONS( "
				+ "ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),"
				+ "TRANSACTION_DATE VARCHAR(255) NOT NULL,"
				+ "SHOP VARCHAR(255) NOT NULL,"
				+ "ARTICLE VARCHAR(255) NOT NULL,"
				+ "SOLD INTEGER NOT NULL,"
				+ "SALES DOUBLE NOT NULL,"
				+ "PRIMARY KEY(ID))";
				
		
		
		String sqlCreateFactTable = "CREATE TABLE THE_FACT_TABLE( "
				+ "TAID INTEGER NOT NULL,"
				+ "ARTICLE_ID INTEGER NOT NULL,"
				+ "PRODUCT_GROUP_ID INTEGER NOT NULL,"
				+ "PRODUCT_FAMILY_ID INTEGER NOT NULL,"
				+ "PRODUCT_CATEGORY_ID INTEGER NOT NULL,"
				+ "SHOP_ID INTEGER NOT NULL,"
				+ "STADT_ID INTEGER NOT NULL,"
				+ "REGION_ID INTEGER NOT NULL,"
				+ "LAND_ID INTEGER NOT NULL,"
				+ "SALES DOUBLE NOT NULL,"
				+ "PRIMARY KEY(TAID, ARTICLE_ID, PRODUCT_GROUP_ID, PRODUCT_FAMILY_ID,"
				+ "PRODUCT_CATEGORY_ID, SHOP_ID, STADT_ID, REGION_ID, LAND_ID),"
				+ "CONSTRAINT fk_taid FOREIGN KEY(TAID) REFERENCES TRANSACTIONS(ID),"
				+ "CONSTRAINT fk_article_id FOREIGN KEY(ARTICLE_ID) REFERENCES ARTICLEID(ARTICLEID),"
				+ "CONSTRAINT fk_product_group_id FOREIGN KEY(PRODUCT_GROUP_ID) REFERENCES PRODUCTGROUPID(PRODUCTGROUPID),"
				+ "CONSTRAINT fk_product_family_id   FOREIGN KEY(PRODUCT_FAMILY_ID)   REFERENCES PRODUCTFAMILYID(PRODUCTFAMILYID),"
				+ "CONSTRAINT fk_product_category_id FOREIGN KEY(PRODUCT_CATEGORY_ID) REFERENCES PRODUCTCATEGORYID(PRODUCTCATEGORYID),"
				+ "CONSTRAINT fk_shop_id FOREIGN KEY(SHOP_ID) REFERENCES SHOPID(SHOPID),"
				+ "CONSTRAINT fk_stadt_id FOREIGN KEY(STADT_ID) REFERENCES STADTID(STADTID),"
				+ "CONSTRAINT fk_region_id FOREIGN KEY(REGION_ID) REFERENCES REGIONID(REGIONID),"
				+ "CONSTRAINT fk_land_id FOREIGN KEY(LAND_ID) REFERENCES LANDID(LANDID))";
		
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate( sqlCreateTableArticleId );
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate( sqlCreateTableProductGroupId );
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate( sqlCreateTableProductFamilyId );
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate( sqlCreateTableProductCategoryId );
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate( sqlCreateTableShopId );
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate( sqlCreateTableStadtId );
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate( sqlCreateTableRegionId );
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate( sqlCreateTableLandId );
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate( sqlCreateTableTranscactions );
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate( sqlCreateFactTable );
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	public static void fillDimensionTablesArticle( 
			ArticleIdTable articleIdTable,
			ProductGroupIdTable productGroupIdTable,
			ProductFamilyIdTable productFamilyIdTable,
			ProductCategoryIdTable productCategoryIdTable, Connection connection
			)  throws SQLException {
		
		
		PreparedStatement psTableArticleId = connection.prepareStatement( "INSERT INTO ARTICLEID(ARTICLEID, PRODUCTGROUPID, NAME, PREIS)" +
															"VALUES (?,?,?,?)" );
		
		
		int i_psTableArticleId = 0;
		for ( ArticleIdTable.Entry entry : articleIdTable.getEntryList() ){
			psTableArticleId.setInt( 1, entry.getId() );
			psTableArticleId.setInt( 2, entry.getProductGroupId() );
			psTableArticleId.setString( 3, entry.getName() );
			psTableArticleId.setDouble( 4, entry.getPreis() );
			psTableArticleId.addBatch();
			i_psTableArticleId++;
			if ( i_psTableArticleId %1000 == 0 || i_psTableArticleId == articleIdTable.getEntryList().size() ){
				psTableArticleId.executeBatch();
			}
			
		}
		
		
		PreparedStatement psTableProductGroupId = connection.prepareStatement( "INSERT INTO PRODUCTGROUPID(PRODUCTGROUPID, PRODUCTFAMILYID, NAME) " +
				"VALUES (?,?,?)" );
		
		int i_psTableProductGroupId = 0;
		for ( ProductGroupIdTable.Entry entry : productGroupIdTable.getEntryList() ){
			psTableProductGroupId.setInt( 1, entry.getId() );
			psTableProductGroupId.setInt( 2, entry.getFamilyId() );
			psTableProductGroupId.setString( 3, entry.getName() );
			psTableProductGroupId.addBatch();
			i_psTableProductGroupId++;
			if ( i_psTableProductGroupId %1000 == 0 || i_psTableProductGroupId == productGroupIdTable.getEntryList().size() ){
				psTableProductGroupId.executeBatch();
			}
			
		}
		
		
		
		
		
		
		
		PreparedStatement psTableProductFamilyId = connection.prepareStatement( "INSERT INTO PRODUCTFAMILYID(PRODUCTFAMILYID, PRODUCTCATEGORYID, NAME) " +
				"VALUES (?,?,?)" );
		
		int i_psTableProductFamiliyId = 0;
		for ( ProductFamilyIdTable.Entry entry : productFamilyIdTable.getEntryList() ){
			psTableProductFamilyId.setInt( 1, entry.getId() );
			psTableProductFamilyId.setInt( 2, entry.getCategoryId() );
			psTableProductFamilyId.setString( 3, entry.getName() );
			psTableProductFamilyId.addBatch();
			i_psTableProductFamiliyId++;
			if ( i_psTableProductFamiliyId %1000 == 0 || i_psTableProductFamiliyId == productFamilyIdTable.getEntryList().size() ){
				psTableProductFamilyId.executeBatch();
			}
			
		}
		
		
		
		PreparedStatement psTableProductCategoryId = connection.prepareStatement( "INSERT INTO PRODUCTCATEGORYID(PRODUCTCATEGORYID, NAME) " +
				"VALUES (?,?)" );
		
		int i_psTableProductCategoryId = 0;
		for ( ProductCategoryIdTable.Entry entry : productCategoryIdTable.getEntryList() ){
			psTableProductCategoryId.setInt( 1, entry.getId() );
			psTableProductCategoryId.setString( 2, entry.getName() );
			psTableProductCategoryId.addBatch();
			i_psTableProductCategoryId++;
			if ( i_psTableProductCategoryId %1000 == 0 || i_psTableProductCategoryId == productCategoryIdTable.getEntryList().size() ){
				psTableProductCategoryId.executeBatch();
			}
			
		}
				
		
	}
	
	
	
	
	
	public static void fillDimensionTablesShop( 
			ShopIdTable shopIdTable,
			StadtIdTable stadtIdTable,
			RegionIdTable regionIdTable,
			LandIdTable landIdTable, Connection connection
			)  throws SQLException {
	
		
		PreparedStatement psTableShopId = connection.prepareStatement( "INSERT INTO SHOPID(SHOPID, STADTID, NAME) " +
				"VALUES (?,?,?)" );
		
		int i_psTableShopId = 0;
		for ( ShopIdTable.Entry entry : shopIdTable.getEntryList() ){
			psTableShopId.setInt( 1, entry.getId() );
			psTableShopId.setInt( 2, entry.getStadtId() );
			psTableShopId.setString( 3, entry.getName() );
			psTableShopId.addBatch();
			i_psTableShopId++;
			if ( i_psTableShopId %1000 == 0 || i_psTableShopId == shopIdTable.getEntryList().size() ){
				psTableShopId.executeBatch();
			}
			
		}
		
		
		
		PreparedStatement psTableStadtId = connection.prepareStatement( "INSERT INTO STADTID(STADTID, REGIONID, NAME) " +
				"VALUES (?,?,?)" );
		
		int i_psTableStadtId = 0;
		for ( StadtIdTable.Entry entry : stadtIdTable.getEntryList() ){
			psTableStadtId.setInt( 1, entry.getId() );
			psTableStadtId.setInt( 2, entry.getRegionId() );
			psTableStadtId.setString( 3, entry.getName() );
			psTableStadtId.addBatch();
			i_psTableStadtId++;
			if ( i_psTableStadtId %1000 == 0 || i_psTableStadtId == stadtIdTable.getEntryList().size() ){
				psTableStadtId.executeBatch();
			}
			
		}
		
		
		PreparedStatement psTableRegionId = connection.prepareStatement( "INSERT INTO REGIONID(REGIONID, LANDID, NAME) " +
				"VALUES (?,?,?)" );
		
		int i_psTableRegionId = 0;
		for ( RegionIdTable.Entry entry : regionIdTable.getEntryList() ){
			psTableRegionId.setInt( 1, entry.getId() );
			psTableRegionId.setInt( 2, entry.getLandId() );
			psTableRegionId.setString( 3, entry.getName() );
			psTableRegionId.addBatch();
			i_psTableRegionId++;
			if ( i_psTableRegionId %1000 == 0 || i_psTableRegionId == regionIdTable.getEntryList().size() ){
				psTableRegionId.executeBatch();
			}			
		}
		
		
		
		PreparedStatement psTableLandId = connection.prepareStatement( "INSERT INTO LANDID(LANDID, NAME) " +
				"VALUES (?,?)" );
		
		int i_psTableLandId = 0;
		for ( LandIdTable.Entry entry : landIdTable.getEntryList() ){
			psTableLandId.setInt( 1, entry.getId() );
			psTableLandId.setString( 3, entry.getName() );
			psTableLandId.addBatch();
			i_psTableLandId++;
			if ( i_psTableLandId %1000 == 0 || i_psTableLandId == landIdTable.getEntryList().size() ){
				psTableLandId.executeBatch();
			}			
		}
	
		
	}
	
	
	
	public static void fillTransactionsTable( List<CSVEntry> entries, Connection connection ) throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement( "INSERT INTO TRANSACTIONS(TRANSACTION_DATE, SHOP,ARTICLE, SOLD, SALES)"
				+ "VALUES (?,?,?,?,?)");
		
		int i = 0;
		for ( CSVEntry entry : entries ){
			preparedStatement.setString(1, entry.getDate());
			preparedStatement.setString(2, entry.getShop());
			preparedStatement.setString(3, entry.getArticle());
			preparedStatement.setInt(4,  entry.getSoldCount());
			preparedStatement.setDouble(5, entry.getSales());
			preparedStatement.addBatch();
			i++;
			/*
			if ( i % 1000 == 0 || i == entries.size() ){
				preparedStatement.executeBatch();
			} */
		}
		preparedStatement.executeBatch();
	}
	
	
	
	
	
	
	
	
	
	
	public static FactTable combineData( List<CSVEntry> csventries, 
									ArticleIdTable articleIdTable,
									ProductGroupIdTable productGroupIdTable,
									ProductFamilyIdTable productFamilyIdTable,
									ProductCategoryIdTable productCategoryTable,
									ShopIdTable shopIdTable,
									StadtIdTable stadtIdTable,
									RegionIdTable regionIdTable,
									LandIdTable landIdTable
									) {
		FactTable factTable = new FactTable();
		
		int taid = 1;
		for ( CSVEntry csvEntry : csventries ){
			FactTable.Entry factTableEntry = factTable.new Entry();
			factTableEntry.setTaid( taid );
			taid++;
			for ( ArticleIdTable.Entry articleIdTableEntry : articleIdTable.getEntryList()){
				
				//System.out.println( e.getName() );
				
				if ( articleIdTableEntry.getName().equals(csvEntry.getArticle())){
					factTableEntry.setArticleId( articleIdTableEntry.getId());
					factTableEntry.setProductGroupId( articleIdTableEntry.getProductGroupId() );
				}
			}
			
			for ( ProductGroupIdTable.Entry productGroupIdTableEntry : productGroupIdTable.getEntryList() ){
				if ( factTableEntry.getProductGroupId() == productGroupIdTableEntry.getId() ){
					factTableEntry.setProductFamilyId( productGroupIdTableEntry.getFamilyId() );
				}
			}
			
			for ( ProductFamilyIdTable.Entry productFamilyIdTableEntry : productFamilyIdTable.getEntryList() ){
				if ( factTableEntry.getProductFamilyId() == productFamilyIdTableEntry.getId() ){
					factTableEntry.setProductCategoryId( productFamilyIdTableEntry.getCategoryId() );
				}
			}
			
			
			for ( ShopIdTable.Entry shopIdTableEntry : shopIdTable.getEntryList() ) {
				if ( shopIdTableEntry.getName().equals( csvEntry.getShop() ) ){
					factTableEntry.setShopId( shopIdTableEntry.getId() );
					factTableEntry.setStadtId( shopIdTableEntry.getStadtId() );
				}
			}
			for ( StadtIdTable.Entry stadtIdTableEntry : stadtIdTable.getEntryList() ){
				if ( factTableEntry.getStadtId() == stadtIdTableEntry.getId() ){
					factTableEntry.setRegionId( stadtIdTableEntry.getRegionId() );
				}
			}
			for ( RegionIdTable.Entry regionIdTableEntry: regionIdTable.getEntryList() ){
				if ( factTableEntry.getRegionId() == regionIdTableEntry.getLandId() ){
					factTableEntry.setLandId( regionIdTableEntry.getLandId() );
				}
			}
			factTableEntry.setSales( csvEntry.getSales());
			
			
			factTable.addEntry( factTableEntry );
		}
		
		// print merged data
		for ( FactTable.Entry entry : factTable.getEntryList() ){
			System.out.println("-------------------------------------------------------------------");
			System.out.println("TAID:    " + entry.getTaid() + " Article-Id: " + entry.getArticleId()
					+ " Product-Group-Id: " + entry.getProductGroupId()
					+ " Product-Family-Id: " + entry.getProductFamilyId()
					+ " Product-Category-Id: " + entry.getProductCategoryId());
			System.out.println("              Shop-Id: " + entry.getShopid() 
					+ " Stadt-Id: " + entry.getStadtId() + " Region-Id: " + entry.getRegionId() 
					+ " Land-Id: " + entry.getLandId() + " Sales: " + entry.getSales() );
		}
		return factTable;
	}
	
	

}
