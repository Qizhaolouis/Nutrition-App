import java.util.HashMap;
/***
 * Runner class for the app.
 * @author qizhao
 *
 */
public class NutritionRunner {
	public static void main(String[] args) {
		NutritionRecommender nutritionApp = new NutritionRecommender();
		// just an example
		// in reality we should as user to input
		HashMap<String, Double> breakfast = new HashMap<String, Double>();
		breakfast.put("Bagels Wheat", 2.0);
		breakfast.put("Dutch Apple Pie", 1.0);
		nutritionApp.addMeal(breakfast);
		nutritionApp.giveSuggestions();
	}
}
