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
		breakfast.put("Bagels Wheat", 2.0);
		breakfast.put("Dutch Apple Pie", 1.0);
		
		System.out.print(breakfast.toString());
		nutritionApp.addMeal(breakfast);
		nutritionApp.giveSuggestions();
		
		String mealString = "{nurti=1.2}";
		mealString = mealString.substring(1, mealString.length()-1); 
		String[] keyValuePairs = mealString.split(",");              //split the string to creat key-value pairs
		HashMap<String, Double> meal = new HashMap<String, Double>();
		for (String pair : keyValuePairs)                        //iterate over the pairs
		{
		    String[] entry = pair.split("=");                   //split the pairs to get key and value 
		    meal.put(entry[0].trim(), Double.parseDouble(entry[1].trim()));          //add them to the hashmap and trim whitespaces
		}
		System.out.print(meal.toString());
	}
}
