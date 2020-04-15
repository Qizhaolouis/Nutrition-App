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
	public static FoodGroup getSuggestedFood(Nutrition meal, Nutrition guide, HashMap<String, Food> library) {
		FoodGroup suggestedFoodGroup = new FoodGroup();
		Food nutritionGap = nutritionGap(meal, guide);
		double range = 0.1;
		ArrayList<String> matchedNames = new ArrayList<String>();
		double min = 1000000;
		double similarity;
		Food suggestedFood = null;
		for (String food : library.keySet()) {
			similarity = nutritionRatioSimiliarity(nutritionGap,library.get(food));
			if (similarity <min) {
				min = similarity;
				suggestedFood = library.get(food);
			}
		}
		String foodName = suggestedFood.getName();
		double servingWeight = suggestedFood.getServingWeight();
		double portion = servingWeight / 100;
		suggestedFoodGroup.addFood(suggestedFood, nutritionGap.getCalories()/(suggestedFood.getCalories()*portion));
		System.out.println(suggestedFood.getName()+":" + nutritionGap.getCalories()/(suggestedFood.getCalories()*portion));
		return suggestedFoodGroup;
	}
	
	public static FoodGroup FoodGroupDifference(FoodGroup group1, FoodGroup group2) {
		HashMap<String,Food> foodDetail2 = group2.getFoodDetail();
		HashMap<String,Double> foodPortion2 = group2.getFoodPortion();
		ArrayList<Food> foods = new ArrayList<Food>();
		for (String foodName : foodDetail2.keySet()) {
			group1.addFood(foodDetail2.get(foodName), -foodPortion2.get(foodName));
		}
		return group1;
	}
	
	
	public static FoodGroup getSuggestedFoodNames(FoodGroup meal, NutritionGuideline guide) {
		FoodGroup foodSuggestions = new FoodGroup();
		return foodSuggestions;
	}
	
	public static void main(String[] args) {
		FoodLibrary foods = new FoodLibrary();
		HashMap<String, Food>  foodList = foods.getLibrary();
		FoodGroup a = new FoodGroup();
		a.addFood(foodList.get("Bagels Wheat"),1);
		int age = 88;
		String activityLevel = "M";
		String gender = "F";
		String userName = "Louis";
		User p1 = new User(userName, age, gender,activityLevel);
		NutritionGuideline guide = new NutritionGuideline(p1);
		FoodGroup add = getSuggestedFood(a, guide,foodList);
		add.addFood(foodList.get("Bagels Wheat"),1);
		boolean out = isSimiliar(add,guide,0.1);
		System.out.println(out);
	}
}