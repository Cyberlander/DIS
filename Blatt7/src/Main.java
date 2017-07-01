import data.ETLService;

public class Main {
	public static void main(String[] args) {
		ETLService etlService = new ETLService();
		etlService.importDatabase();
		etlService.importCSV();
		etlService.transformData();
		etlService.uploadData();
	}
}
