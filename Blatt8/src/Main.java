import java.util.List;

public class Main {
	public static void main(String[] args) {
		TransactionReader transactionReader = new TransactionReader("transactions.txt");
	 	List<int[]> itemSets = transactionReader.getItemSets();
	 	new Apriori(itemSets);
	}
}
