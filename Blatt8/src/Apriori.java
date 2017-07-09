import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Apriori {
	private int _numItems = 0;
	private double _minSup = 0.01;

	public Apriori(List<int[]> itemSets) {
		for (int[] set : itemSets) {
			_numItems += set.length;
		}

		runApriori(itemSets);
	}

	private void runApriori(List<int[]> initialItemSets) {
		List<int[]> itemSets = createItemSetsOfSizeOne();
		
		while (itemSets.size() > 0) {
			itemSets = frequentItemSets(initialItemSets, itemSets);

			if (itemSets.size() != 0) {
				itemSets = createItemSetsFromPrevious(itemSets);
			}
		}
	}

	private List<int[]> createItemSetsOfSizeOne() {
		List<int[]> itemSets = new ArrayList<int[]>();
		for (int i = 0; i < _numItems; i++) {
			itemSets.add(new int[] {i});
		}
		
		return itemSets;
	}

	private List<int[]> createItemSetsFromPrevious(List<int[]> previousItemSet) {
		Map<String, int[]> tmpCandidates = new HashMap<String, int[]>();

		for (int i = 0; i < previousItemSet.size(); i++) {
			for (int j = i + 1; j < previousItemSet.size(); j++) {
				int[] setA = previousItemSet.get(i);
				int[] setB = previousItemSet.get(j);
				int[] newCandidate = getCandidate(setA, setB);
				if (newCandidate == null) {
					continue;
				}
				tmpCandidates.put(Arrays.toString(newCandidate), newCandidate);
			}
		}

		return new ArrayList<int[]>(tmpCandidates.values());
	}

	private int[] getCandidate(int[] setA, int[] setB) {
		int itemSetSize = setA.length;
		int[] newCandidate = new int[itemSetSize + 1];
		int difference = 0;

		for (int i = 0; i < itemSetSize; i++) {
			newCandidate[i] = setA[i];
		}

		for (int i = 0; i < setB.length; i++) {
			boolean found = false;
			for (int j = 0; j < setA.length; j++) {
				if (setA[j] == setB[i]) {
					found = true;
					break;
				}
			}

			if (!found) {
				difference++;
				newCandidate[itemSetSize] = setB[i];
			}
		}

		if (difference == 1) {
			Arrays.sort(newCandidate);
			return newCandidate;
		}

		return null;
	}

	private List<int[]> frequentItemSets(List<int[]> initialItemSet, List<int[]> previousItemSet) {
		List<int[]> frequentCandidates = new ArrayList<int[]>();
		int count[] = new int[previousItemSet.size()];
		int numTransactions = initialItemSet.size();

		for (int transaction = 0; transaction < numTransactions; transaction++) {
			int[] line = initialItemSet.get(transaction);
			boolean[] transactions = new boolean[_numItems];
			Arrays.fill(transactions, false);
			for (int value : line) {
				transactions[value] = true;
			}

			for (int i = 0; i < previousItemSet.size(); i++) {
				boolean match = true;
				int[] candidate = previousItemSet.get(i);

				for (int item : candidate) {
					if (transactions[item] == false) {
						match = false;
						break;
					}
				}

				if (match) {
					count[i]++;
				}
			}
		}

		for (int i = 0; i < previousItemSet.size(); i++) {
			if ((count[i] / (double) (numTransactions)) >= _minSup) {
				System.out.println(Arrays.toString(previousItemSet.get(i)) + "  (" + ((count[i] / (double) numTransactions))
						+ " " + count[i] + ")");
				frequentCandidates.add(previousItemSet.get(i));
			}
		}

		return frequentCandidates;
	}
}
