package NutritionRecommender;
import java.util.*;

/***
 * The user interface for the user interaction
 * @author Team-70
 */
public class NutritionRecommender {
	
	User newUser;

	/**
	 * The interface for user input
	 */
	public NutritionRecommender(String userName, int age, String gender, String activityLevel, int sleepHours) {
		// make user
		newUser = new User(userName, age, gender, activityLevel);
		newUser.setSleepingHours(sleepHours);
	}
	
	/**
	 * Asks user the food they ate
	 * needs to be completed
	 * if a food user input is not in the key of the foodLibrary
	 * ask "Do you mean the food: XXX" using the foodFider class
	 */
	public void addMeal(HashMap<String, Double> meal) {
		FoodLibrary foodLib = new FoodLibrary();
		for (String key: meal.keySet()) {
			if (foodLib.getLibrary().containsKey(key)) {
				Food food = foodLib.getLibrary().get(key);
				newUser.addFood(food, meal.get(key));
			}
			else {
				// use foodFinder to ask for suggestions
				System.out.println("Cannot add the food " + key + ".");
			}
		}
	}

	/**
	 * give suggestions based on user input
	 */
	public void giveSuggestions() {
		ArrayList<String> suggestions = newUser.getGeneralSuggestions();
		System.out.println("Hi " + newUser.getName() + ", here is your general suggestions:");
		int i = 1;
		for (String suggestion : suggestions) {
			System.out.println(String.valueOf(i) + ". " + suggestion);
			i+=1;
		}
		// the suggestion of meals to be developed
		// place holder
	}
	
	/**
	 * return suggestions as a list
	 */
	public ArrayList<String> getSuggestions() {
		ArrayList<String> suggestions = newUser.getGeneralSuggestions();
		FoodGroup suggestedFood = this.getSuggestedFood();
		String foodSuggestion = "This is a modified version of foods that you need to eat daily: \n<div>";
		for (String foodName :  suggestedFood.getFoodDetail().keySet()) {
			foodSuggestion += "<li class=\"list-group-item active\">- \n\n\n" + String.format("%.2f",suggestedFood.getFoodPortion().get(foodName)) + " serving of " + foodName
					+ "\n (Each serving is " + suggestedFood.getFoodDetail().get(foodName).getServingDes()+ ") </li>";
		}
		foodSuggestion += "</div>";
		suggestions.add(foodSuggestion);
		return suggestions;
	}
	
	/**
	 * Get suggested Food
	 * @return the suggested food 
	 */
	public FoodGroup getSuggestedFood() {
		FoodGroup foodResultList;
		FoodLibrary foodlib = new FoodLibrary();
		//System.out.print(caloriesSuggestion);
		foodResultList = NutritionCalculator.getSuggestedFood(newUser.getFoods(), newUser.getGuide(), foodlib.getLibrary(), 0.1);
		return foodResultList;
	}
}
