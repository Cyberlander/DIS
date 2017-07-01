import java.io.File;

import data.ETLService;

public class Main {
	public static void main(String[] args) {
		ETLService etlService = new ETLService();
		File file = new File("sales.csv");
		etlService.importCSV(file);
		etlService.importDatabase();
		etlService.extractFacts();
		etlService.uploadData();
	}
}
