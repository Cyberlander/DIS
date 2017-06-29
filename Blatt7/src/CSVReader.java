import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CSVReader {
	private List<String[]> rows;
	
	public CSVReader(String path, String splitChar) {
		readFile(path, splitChar);
	}
	
	public List<String[]> getRows(){
		return rows;
	}

	private void readFile(String path, String splitChar) {
		rows = new LinkedList<String[]>();
		BufferedReader reader = null;
			
		try {
			reader = new BufferedReader(new FileReader(path));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] column = line.split(splitChar);
				rows.add(column);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}