import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionReader {
	private List<int[]> _itemSets;
	
	public TransactionReader(String path) {
		readFile(path);
	}
	
	public List<int[]> getItemSets(){
		return _itemSets;
	}
	
	private void readFile(String path) {
		_itemSets = new ArrayList<int[]>();
		BufferedReader reader = null;
			
		try {
			reader = new BufferedReader(new FileReader(path));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] itemsStrs = line.split(" ");
				int[] items = new int[itemsStrs.length];
				for(int i = 0; i < itemsStrs.length; i++) {
					items[i] = Integer.parseInt(itemsStrs[i]);
				}
				_itemSets.add(items);
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
