import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

/***
 * This is for function validation test
 * @author Team-70
 *
 */
class ValidationTest {
		
	@Test
	public void getCalories() {
		FoodLibrary foods = new FoodLibrary();
		HashMap<String, Food>  foodList = foods.getLibrary();
		Food a = foodList.get("Bagels Wheat");
		double calories = a.getCalories();
		assertEquals( 250.0, calories);
	}
	
	@Test
	public void getGuideLine() {
		int age = 88;
		String activityLevel = "M";
		String gender = "F";
		String userName = "Louis";
		User p1 = new User(userName, age, gender);
		p1.setActivityLevel(activityLevel);
		NutritionGuideline test1 = new NutritionGuideline();
		int calorieNeeded = test1.getCalorieNeeded(p1);
		assertEquals(1800, calorieNeeded);
	}


}
