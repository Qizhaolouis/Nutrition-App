import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
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
	public static double caloriesGap(Nutrition meal, Nutrition guide) {
		// To be developed
		return guide.getCalories() - meal.getCalories();
	}
	
	/**
	 * Calculates the difference between the foods user inputed and guideline for protein.
	 * @param meal
	 * @param guide
	 * @return difference
	 */
	public static double proteinGap(Nutrition meal, Nutrition guide) {
		// To be developed
		return guide.getProtein() - meal.getProtein();
	}
	
	public static double proteinRatio(Nutrition meal) {
		// To be developed
		return meal.getProtein()/meal.getCalories();
	}
	
	/**
	 * Calculates the difference between the foods user inputed and guideline for fat.
	 * @param meal
	 * @param guide
	 * @return difference
	 */
	public static double fatGap(Nutrition meal, Nutrition guide) {
		// To be developed
		return guide.getFat() - meal.getFat();
	}
	
	public static double fatRatio(Nutrition meal) {
		// To be developed
		return meal.getFat()/meal.getCalories();
	}
	
	/**
	 * Calculates the difference between the foods user inputed and guideline for carbs.
	 * @param meal
	 * @param guide
	 * @return difference
	 */
	public static double carbsGap(Nutrition meal, Nutrition guide) {
		// To be developed
		return guide.getCarbs() - meal.getCarbs();
	}
	
	public static double carbsRatio(Nutrition meal) {
		// To be developed
		return meal.getCarbs()/meal.getCalories();
	}
	
	public static double nutritionRatioSimiliarity(Nutrition meal1, Nutrition meal2) {
		double proteinRatioDist = proteinRatio(meal1) - proteinRatio(meal2);
		double fatRatioDist = fatRatio(meal1) - fatRatio(meal2);
		double carbsRatioDist = carbsRatio(meal1) - carbsRatio(meal2);
		double similiarilty = Math.sqrt(Math.pow(proteinRatioDist,2) 
				                      + Math.pow(fatRatioDist,2) 
				                      + Math.pow(carbsRatioDist,2));
		
		// To be developed
		return similiarilty;
	}
	
	public static boolean isSimiliar(Nutrition meal, Nutrition guide, double error) {
		if (Math.abs(caloriesGap(meal,guide)/guide.getFat())>error) return false;
		if (Math.abs(fatGap(meal,guide))/guide.getFat()>error) return false;
		if (Math.abs(carbsGap(meal,guide))/guide.getCarbs()>error) return false;
		return true;
	}
	
	public static Food nutritionGap(Nutrition meal, Nutrition guide) {
		double caloriesGap = caloriesGap(meal,guide);
		double proteinGap = proteinGap(meal,guide);
		double fatGap = fatGap(meal,guide);
		double carbsGap = carbsGap(meal,guide);
		return new Food(caloriesGap,fatGap,proteinGap,carbsGap);
	}
	
	/**
	 * Gives the suggested food to be added to the meal
	 * @param calories difference from guideline
	 * @param protein difference from guideline
	 * @param fat difference from guideline
	 * @param carbs difference from guideline
	 * @return suggested Food
	 */
	public static Food getSuggestedFood(Nutrition meal, Nutrition guide, HashMap<String, Food> library) {
		Food nutritionGap = nutritionGap(meal, guide);
		double range = 0.1;
		ArrayList<String> matchedNames = new ArrayList<String>();
		for (String name : library.keySet()) {
			library.get(name);
				}
		
		// To be developed
		Food userFood = new Food("");
		return userFood;
	}
	
	
	public static FoodGroup getSuggestedFoodNames(FoodGroup meal, NutritionGuideline guide) {
		FoodGroup foodSuggestions = new FoodGroup();
		return foodSuggestions;
	}
}