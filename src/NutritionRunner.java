import java.util.HashMap;
/***
 * Runner class for the app.
 * @author Team-70
 *
 */
public class NutritionRunner {
	
	public static void main(String[] args) {
		NutritionRecommender nutritionApp = new NutritionRecommender("Louis", 18, "F", "M");
		// just an example
		// in reality we should as user to input
		HashMap<String, Double> breakfast = new HashMap<String, Double>();
		breakfast.put("Bagels Wheat", 100.0);
		breakfast.put("Dutch Apple Pie", 0.0);
		
		System.out.println(breakfast.toString());
		nutritionApp.addMeal(breakfast);
		nutritionApp.giveSuggestions();
		
		
		FoodGroup meal = nutritionApp.getSuggestedFood();
		
		System.out.print(meal.getFoodPortion().toString());
	}
}
