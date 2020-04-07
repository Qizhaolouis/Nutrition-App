
/***
 * This class includes the methods to calculate the difference between foods inputed and nutrition needed, 
 * and recommend foods adds/deletes for the user, based on user's target, and user's input daily meals
 * @author Team-70
 *
 */
public class NutritionCalculator {
	
	/**
	 * Calculates the difference between the foods user inputed and guideline for calories.
	 * @param meal
	 * @param guide
	 * @return difference
	 */
	public static int caloriesDifferenceToGuide(FoodGroup meal, NutritionGuideline guide) {
		// To be developed
		return 0;
	}
	
	/**
	 * Calculates the difference between the foods user inputed and guideline for protein.
	 * @param meal
	 * @param guide
	 * @return difference
	 */
	public static int proteinDifferenceToGuide(FoodGroup meal, NutritionGuideline guide) {
		// To be developed
		return 0;
	}
	
	/**
	 * Calculates the difference between the foods user inputed and guideline for fat.
	 * @param meal
	 * @param guide
	 * @return difference
	 */
	public static int fatDifferenceToGuide(FoodGroup meal, NutritionGuideline guide) {
		// To be developed
		return 0;
	}
	
	/**
	 * Calculates the difference between the foods user inputed and guideline for carbs.
	 * @param meal
	 * @param guide
	 * @return difference
	 */
	public static int carbsDifferenceToGuide(FoodGroup meal, NutritionGuideline guide) {
		// To be developed
		return 0;
	}
	
	/**
	 * Gives the suggested food to be added to the meal
	 * @param calories difference from guideline
	 * @param protein difference from guideline
	 * @param fat difference from guideline
	 * @param carbs difference from guideline
	 * @return suggested Food
	 */
	public static Food getSuggestedFood(int caloriesDif, int proteinDif, int fatDif, int carbsDif) {
		// To be developed
		Food userFood = new Food("");
		return userFood;
	}
}