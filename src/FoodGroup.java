import java.util.ArrayList;
import java.util.HashMap;

/***
 * This class is a collection of foods for one meal.
 */
public class FoodGroup implements Nutrition {
	/**
	 * @param meal list of food 
	 * @param foodDetail given food name, return the Food object
	 * @param foodPortion given food portion, return the number of 100g
	 */
	private ArrayList<String> meal;
	private HashMap<String,Food> foodDetail;
	private HashMap<String,Double> foodPortion;
	
	
	/**
	 * Initializes the FoodGroup Object.
	 */
	public FoodGroup() {
		meal = new ArrayList<String>();
		foodDetail = new HashMap<String,Food>();
		foodPortion = new HashMap<String,Double>();
	}
	
	/**
	 * Adds a food and its portion to the food group
	 * @param food the food object
	 * @param serving number of servings 
	 */
	public void addFood(Food food, double serving) {
		
		String foodName = food.getName();
		double servingWeight = food.getServingWeight();
		double portion = serving * servingWeight / 100; // calculate how many 100g has been consumed.
		
		if (foodDetail.containsKey(foodName)) {
			foodPortion.put(foodName, foodPortion.get(foodName) + portion);
		}
		else {
			meal.add(foodName);
			foodDetail.put(foodName, food);
			foodPortion.put(foodName, portion);
		}
	}
	
	/**
	 * Delete a food and its portion to the food group
	 * @param food the food object
	 * @param serving number of servings 
	 */
	public void deleteFood(Food food, double serving) {
		
		String foodName = food.getName();
		double servingWeight = food.getServingWeight();
		double portion = serving * servingWeight / 100; // calculate how many 100g has been consumed.
		
		if (foodDetail.containsKey(foodName)) {
			foodPortion.put(foodName, foodPortion.get(foodName) + portion);
		}
		else {
			meal.add(foodName);
			foodDetail.put(foodName, food);
			foodPortion.put(foodName, portion);
		}
	}
	
	
	/**
	 * Calculates the total calories consumed
	 * @return the total calories consumed
	 */
	public double getCalories() {
		double mealCalories = 0;
		for (String foodName : meal) {
			double calories = foodDetail.get(foodName).getCalories();
			double portion = foodPortion.get(foodName);
			mealCalories += calories * portion;
		}
		return mealCalories;
	}
	
	/**
	 * Calculates the total protein consumed
	 * @return the total protein consumed
	 */
	public double getProtein() {
		double mealProtein = 0;
		for (String foodName : meal) {
			double protein = foodDetail.get(foodName).getProtein();
			double portion = foodPortion.get(foodName);
			mealProtein += protein * portion;
		}
		return mealProtein;
	}
	
	/**
	 * Calculates the total fat consumed
	 * @return the total fat consumed
	 */
	public double getFat() {
		double mealFat = 0;
		for (String foodName : meal) {
			double fat = foodDetail.get(foodName).getFat();
			double portion = foodPortion.get(foodName);
			mealFat += fat * portion;
		}
		return mealFat;
	}
	
	/**
	 * Calculates the total carbs consumed
	 * @return the total carbs consumed
	 */
	public double getCarbs() {
		double mealCarbs = 0;
		for (String foodName : meal) {
			double carbs = foodDetail.get(foodName).getCarbs();
			double portion = foodPortion.get(foodName);
			mealCarbs += carbs * portion;
		}
		return mealCarbs;
	}

	
	public HashMap<String, Food> getFoodDetail() {
		return foodDetail;
	}

	public HashMap<String, Double> getFoodPortion() {
		return foodPortion;
	}

	// test
	public static void main(String[] args) {
		FoodLibrary foods = new FoodLibrary();
		HashMap<String, Food> foodLib = foods.getLibrary();
		Food food = foodLib.get("Bagels Wheat");
		FoodGroup meal = new FoodGroup(); 
		System.out.print(food.getName());
		meal.addFood(food, 1.0);
		meal.getCalories();
	}
}