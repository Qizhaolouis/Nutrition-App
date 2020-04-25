package NutritionRecommender;
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
	 * Adds/deletes a food and its portion to the food group
	 * @param food the food object
	 * @param serving number of servings (positive for add, negative for deletes)
	 */
	public void addFood(Food food, double serving) {
		
		String foodName = food.getName();
		
		if (foodDetail.containsKey(foodName)) {
			if (foodPortion.get(foodName) + serving==0) {
				foodPortion.remove(foodName);
				foodDetail.remove(foodName);
				meal.remove(foodName);
			} else {
				foodPortion.put(foodName, foodPortion.get(foodName) + serving);
			}
		}
		else {
			meal.add(foodName);
			foodDetail.put(foodName, food);
			foodPortion.put(foodName, serving);
		}
	}
	
	/**
	 * Gets the portion for the given Food
	 * @param food the food object
	 * @param serving number of servings 
	 */
	public double getPortion(Food food) {
		String foodName = food.getName();
		if (foodPortion.containsKey(foodName)) {
			return foodPortion.get(foodName);
		} else {
			return 0;
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
	
	public ArrayList<String> getMeal() {
		return meal;
	}

	public HashMap<String, Double> getFoodPortion() {
		return foodPortion;
	}
}
