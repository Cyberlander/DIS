
public class Main {
	public static void main(String[] args) {
		DB2ConnectionManager connection = DB2ConnectionManager.getInstance();
		
		CSVReader reader = new CSVReader("Sales.csv", ";");
		reader.getRows();
	}
}
