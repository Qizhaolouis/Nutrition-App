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
	public ArrayList<String> findMatchedNames(String foodName) {
		ArrayList<String> matchedNames = new ArrayList<String>();
		for (String name : library.keySet()) {
			// need to more cleaning process before compare two strings
			//complex logic to be developed: input key word, find matches by key words
			if (name.toLowerCase().contains(foodName.toLowerCase())) {
				matchedNames.add(name);
			}
		}
		return matchedNames;
	}
	
	public static void main(String[] args) {
		FoodFinder finder = new FoodFinder();
		ArrayList<String> matchedNames = finder.findMatchedNames("");
		System.out.println(matchedNames.toString());
	}
	
	/**
	 * Gives a similarity score based on the 2 food names
	 * @param name1
	 * @param name2
	 * @return The similarity score from 0 to 1
	 */
	public double compareTwoNames(String name1, String name2) {
		// To be developed
		return 1;
	}
	
	/**
	 * Returns the Top N matches based on the similarity score, 
	 * given the user's input food name
	 * @param name
	 * @param topN
	 * @return The TopN matched names from Food Library for user selection
	 */
	public ArrayList<String> getTopNMatched(String name, int topN) {
		ArrayList<String> matchedNames = findMatchedNames(name);
		// To be developed
		// just return the first N from the matched array for current test
		ArrayList<String> selection = new ArrayList<String>(matchedNames.subList(0, topN));
		return selection;
	}
	
	
	/**
	 * Gives a similarity score based on the 2 foods
	 * do NOT compare calories because we can reduce or increase calories by changing portion
	 * compare the percent of fat/protein/carbs
	 * @param food1
	 * @param food2
	 * @return
	 */
	public double getSimilarityScore(Food food1, Food food2) {
		return 1;
	}

}
