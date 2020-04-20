package NutritionRecommender;
/***
 * Nutrition interface for class Food, Food Group, Nutrition Guideline
 * @author Team-70
 *
 */
interface Nutrition {
	/**
	 * Gets calories
	 * @return calories (cal)
	 */
	public double getCalories();
	/**
	 * Gets protein
	 * @return protein (g)
	 */
	public double getProtein();
	/**
	 * Gets carbs
	 * @return carbs (g)
	 */
	public double getCarbs();
	/**
	 * Gets fat
	 * @return fat (g)
	 */
	public double getFat();
}
