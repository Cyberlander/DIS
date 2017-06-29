
public class Main {
	public static void main(String[] args) {
		CSVReader reader = new CSVReader("Sales.csv", ";");
		reader.getRows();
	}
}
