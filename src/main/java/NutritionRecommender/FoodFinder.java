package NutritionRecommender;
import java.util.ArrayList;
import java.util.HashMap;

/***
 * This class is a String match system to find matched foods and return match results 
 * by user's input
 * @author Team-70
 *
 */
public class FoodFinder {
	HashMap<String, Food> library = new FoodLibrary().getLibrary();
	
	/**
	 * Given the user's input name. This method is to find a list matched names from FoodLibrary.
	 * Here the matched names define as the ones which contains the input name.
	 * (complex logic to be developed: input key word, find matches by key words)
	 * @return The list of matched names
	 */
	public ArrayList<String> findMatchedNames(String keywords) {
		String[] tokens = keywords.split(" ");

		ArrayList<String> matchedNames = new ArrayList<String>();
		for (String name : library.keySet()) {
			if (isMatched(tokens, name)) {
				matchedNames.add(name);
			}
		}
		return matchedNames;
	}
	
	/**
	 * Returns the Top N matches based on the similarity score, 
	 * given the user's input food name
	 * @param name
	 * @param topN
	 * @return The TopN matched names from Food Library for user selection
	 */
	public ArrayList<String> getTopNMatched(String keywords, int topN) {
		ArrayList<String> matchedNames = findMatchedNames(keywords);
		matchedNames.sort((s1, s2) -> s1.length() - s2.length());
		ArrayList<String> selection = new ArrayList<String>(matchedNames.subList(0, Math.min(matchedNames.size(),topN)));
		return selection;
	}
	
	/**
	 * Checks if the name including all the tokens (after cleaning the name and tokens).
	 * @param tokens, ArrayList of tokens
	 * @param name
	 * @return true for the name including all the tokens, otherwise return false. 
	 */
	private boolean isMatched(String[] tokens, String name) {
		name = cleanString(name);
		for (String token : tokens) {
			if (!name.contains(cleanString(token))) {
				return false;
			}	
		}
		return true;
	}
	
	/**
	 * Remove the punctuation from the string, and changes to lower cases.
	 * @param String
	 * @return cleaned String
	 */
	private String cleanString(String value) {
		return value.toLowerCase().replaceAll("\\p{Punct}", "");
	}
	
	public static void main(String[] args) {
		FoodFinder finder = new FoodFinder();
		ArrayList<String> matchedNames = finder.getTopNMatched("whole  milk",3);
		System.out.println(matchedNames.toString());
	}

}
