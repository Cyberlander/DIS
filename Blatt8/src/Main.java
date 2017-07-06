import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		TransactionReader transactionReader = new TransactionReader("transactions.txt");
	 	List<int[]> itemSets = transactionReader.getItemSets();
	 
	 	Apriori apriori = new Apriori(itemSets);
	}
}
