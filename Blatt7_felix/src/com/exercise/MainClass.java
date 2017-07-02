package com.exercise;

import java.util.*;
import java.sql.*;

import com.exercise.tables.*;

public class MainClass
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		List<CSVEntry> entries = CSVReader.loadCSVFile( "sales.csv" );
		System.out.println( entries.size() );
		
		Connection connection = ODBReader.getConnection();
		ArticleIdTable articleIdTable = ODBReader.getArticleIdTable( connection );
		ProductGroupIdTable productGroupIdTable = ODBReader.getProductGroupIdTable( connection );
		ProductFamilyIdTable productFamilyIdTable = ODBReader.getProductFamilyIdTable( connection );
		ProductCategoryIdTable productCategoryIdTable = ODBReader.getProductCategoryIdTable( connection );
		ShopIdTable shopIdTable = ODBReader.getShopIdTable( connection );
		StadtIdTable stadtIdTable = ODBReader.getStadtIdTable( connection );
		RegionIdTable regionIdTable = ODBReader.getRegionIdTable( connection );
		LandIdTable landIdTable =ODBReader.getLandIdTable( connection );
		
		Connection wareHouseConnection = DataWarehouseWriter.getConnection();
		
		// DataWarehouseWriter.createTables( wareHouseConnection );

		
		try {
			
			DataWarehouseWriter.fillDimensionTablesArticle(articleIdTable, productGroupIdTable, productFamilyIdTable, productCategoryIdTable, wareHouseConnection);
			DataWarehouseWriter.fillDimensionTablesShop(shopIdTable, stadtIdTable, regionIdTable, landIdTable, wareHouseConnection);
			DataWarehouseWriter.fillTransactionsTable( entries, connection );
			
		} catch ( SQLException e ){
			e.printStackTrace();
		} 
		
		
		FactTable factTable = DataWarehouseWriter.combineData( entries,
										articleIdTable,
										productGroupIdTable,
										productFamilyIdTable,
										productCategoryIdTable,
										shopIdTable,
										stadtIdTable,
										regionIdTable,
										landIdTable );
		

		
	}

}
