/***
 * This class is to find matched foods and return match results by user's input
 * @author Team-70
 *
 */
public class FoodFinder {
	private FoodLibrary foodLibrary = new FoodLibrary();
	
	public Food[] findMatch() {
		return null;
	}
	
	/**
	 * Give a similarity score based on the 2 foods
	 * do NOT compare calories because we can reduce or increase calories by changing portion
	 * compare the percent of fat/protein/carbs
	 * @param food1
	 * @param food2
	 * @return
	 */
	public double getSimilarityScore(Food food1, Food food2) {
		return 0;
	}

}
